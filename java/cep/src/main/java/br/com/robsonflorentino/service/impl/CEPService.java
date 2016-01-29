package br.com.robsonflorentino.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.robsonflorentino.model.CEP;
import br.com.robsonflorentino.service.ICEPService;
import br.com.robsonflorentino.service.exceptions.CEPNaoEncontradoException;
import br.com.robsonflorentino.service.exceptions.CEPServiceFailureException;

/**
 * Acessa via HTTP, a busca de CEP disponibilizada pelos correios e extrai os
 * resultados do html retornado utilizando jsoup.
 * 
 * A url utilizada para obter os resultados é a seguinde:
 * http://www.buscacep.correios.com.br/servicos/dnec/consultaEnderecoAction.do
 * 
 * @author Robson Florentino <robson.florentino.silva@gmail.com>
 *
 */
public class CEPService implements ICEPService {

	private static final int IDX_LOGRADOURO = 0;
	private static final int IDX_BAIRRO = 1;
	private static final int IDX_LOCALIDADE = 2;
	private static final int IDX_UF = 3;
	private static final int IDX_NUM = 4;
	private static final String HIFEN = "-";

	//public static final String BASE_URL = "http://www.buscacep.correios.com.br/servicos/dnec/consultaEnderecoAction.do?relaxation=%s&TipoCep=ALL&semelhante=S&cfm=1&Metodo=listaLogradouro&TipoConsulta=relaxation";
	//public static final String BASE_URL = "http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm";
	public static final String BASE_URL = "http://www.callink.com.br";

	public CEP buscaPorNumeroCEP(String numCEP)
			throws CEPNaoEncontradoException, CEPServiceFailureException {
		Elements rows = busca(numCEP);
		for (Element row : rows) {
			CEP cep = cep(row);
			return cep;
		}

		throw new CEPNaoEncontradoException(numCEP, null);
	}

	private CEP cep(Element row) {
		Elements cols = row.getElementsByTag("td");
		CEP cep = new CEP(getNumero(cols), getLogradouro(cols),
				GetBairro(cols), GetLocalidade(cols), getUf(cols));
		return cep;
	}

	private String getUf(Elements cols) {
		return cols.get(IDX_UF).text();
	}

	private String getNumero(Elements cols) {
		String num = cols.get(IDX_NUM).text();
		return num.contains(HIFEN) ? num.replace(HIFEN, "") : num;
	}

	private String GetBairro(Elements cols) {
		return cols.get(IDX_BAIRRO).text();
	}

	private String getLogradouro(Elements cols) {
		return cols.get(IDX_LOGRADOURO).text();
	}

	private String GetLocalidade(Elements cols) {
		return cols.get(IDX_LOCALIDADE).text();
	}

	private Elements busca(String strQuery) {
		try {

			Document doc = Jsoup.connect(String.format(BASE_URL, URLEncoder.encode(strQuery, "ISO-8859-1")))
							.timeout(10 * 1000)
							.data("relaxation", strQuery)
							.data("tipoCEP", "ALL")
							.data("semelhante","N")
							.post();
			
			Elements rows = doc.getElementsByAttributeValueMatching("onclick",
					"javascript:detalharCep.*");
			return rows;
		} catch (IOException ex) {
			throw new CEPServiceFailureException(ex);
		}
	}

	public List<CEP> buscaPorEndereco(String endereco) {
		Elements rows = busca(endereco);
		List<CEP> ceps = new ArrayList<CEP>(rows.size());
		for (Element row : rows) {
			CEP cep = cep(row);
			ceps.add(cep);
		}
		return ceps;
	}

}
