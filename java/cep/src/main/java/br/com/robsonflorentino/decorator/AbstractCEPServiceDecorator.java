package br.com.robsonflorentino.decorator;

import br.com.robsonflorentino.service.ICEPService;

/**
 * Decorador base para a criação de {@link ICEPService}s decorados
 * @author Robson Florentino <robson.florentino.silva@gmail.com>
 *
 */
public abstract class AbstractCEPServiceDecorator implements ICEPService {
	protected ICEPService cepServiceDecorator;
	
	public AbstractCEPServiceDecorator(ICEPService cepServiceDecoIcepService) {
		super();
		this.cepServiceDecorator = cepServiceDecoIcepService;
	}
}
