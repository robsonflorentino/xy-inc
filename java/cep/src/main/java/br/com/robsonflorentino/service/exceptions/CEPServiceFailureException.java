package br.com.robsonflorentino.service.exceptions;

/**
 * Esta exceção deve ser lançada caso algum erro inesperado aconteça ao consultar um CEP nos correios.
 * 
 * @author Robson Florentino <robson.florentino.silva@gmail.com>
 *
 */
public class CEPServiceFailureException extends RuntimeException {

	private static final long serialVersionUID = -5261563073904740778L;

	public CEPServiceFailureException(Throwable cause) {
		super(cause);
	}
}
