package br.com.robsonflorentino.factory;

import br.com.robsonflorentino.service.ICEPService;
import br.com.robsonflorentino.service.impl.CEPService;

/**
 * Representa uma fábrica de serviços.
 * 
 * @author Robson Florentino <robson.florentino.silva@gmail.com>
 *
 */
public class CEPServiceFactory {

	/**
	 * @return Retorna uma instância de CEPService.
	 */
	public static ICEPService getCEPService() {
		return new CEPService();
	}
	
}
