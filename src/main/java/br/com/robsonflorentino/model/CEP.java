package br.com.robsonflorentino.model;

import java.io.Serializable;


/**
 * Este modelo representa um CEP
 * @author Robson Florentino <robson.florentino.silva@gmail.com>
 *
 */
public class CEP implements Serializable {
	
	private static final long serialVersionUID = -5452961292581191880L;
	
	private final String numero;
	private final String logradouro;
	private final String bairro;
	private final String localidade;
	private final String uf;
	
	public CEP(String numero, String logradouro, String bairro,
			String localidade, String uf) {
		super();
		this.numero = numero;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public String getUf() {
		return uf;
	}
	

	@Override
	public String toString() {
		return "CEP [numero=" + numero + ", logradouro=" + logradouro
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", uf="
				+ uf + "]";
	}
	
}
