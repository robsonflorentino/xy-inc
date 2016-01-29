package br.com.robsonflorentino.service.exceptions;

public class CEPNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 5913737045019875344L;
	
	public CEPNaoEncontradoException(int numeroCEP, Throwable cause) {
		super("O CEP " + numeroCEP + " não foi encontrado.", cause);
	}

	public CEPNaoEncontradoException(String query, Throwable cause) {
		super("Busca por '" + query + "' não encontrou nada.", cause);
	}
	
}
