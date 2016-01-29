package br.com.robsonflorentino.service;

import java.util.List;

import br.com.robsonflorentino.model.CEP;

/**
 * Expoe os serviços de busca de cep
 * @author Robson Florentino <robson.florentino.silva@gmail.com>
 *
 */
public interface ICEPService {

	/**
	 * Obtem um <code>{@link CEP}</code> pelo número de CEP.
	 * @param numCEP o número do CEP a ser procurado.
	 * @return o <code>{@link CEP}</code> que foi encontrado.
	 */
	public CEP buscaPorNumeroCEP(String numCEP);
	
	/**
	 * Obtem os <code>{@link CEP}s</code> que foram encontrados 
	 * conforme o endereço fornecido.
	 * @param endereco - o endereço para realizar a pesquisa por CEPs.
	 * @return Uma lista de <code>{@link CEP}s</code> encontrados.
	 */
	public List<CEP> buscaPorEndereco(String endereco);
	
}
