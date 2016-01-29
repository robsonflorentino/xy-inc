package br.com.robsonflorentino.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.robsonflorentino.factory.CEPServiceFactory;
import br.com.robsonflorentino.model.CEP;
import br.com.robsonflorentino.service.ICEPService;
import br.com.robsonflorentino.service.exceptions.CEPNaoEncontradoException;
import br.com.robsonflorentino.service.exceptions.CEPServiceFailureException;
import br.com.robsonflorentino.service.impl.CEPService;

public class CEPServiceTest {

	private static final String CEP_VALIDO = "38408218";
	private static final CEP CEP_COMPLETO_1 = new CEP("38408218", "Rua Nordau Gonçalves de Melo", "Santa Mônica", "Uberlândia", "MG");
	private static final CEP CEP_COMPLETO_2 = new CEP("38400675", "Rua Max Nordau de Rezende Alvim ", "Brasil", "Uberlândia", "MG");
	
	private ICEPService cepService;
	
	@Before
	public void setUp() throws Exception {
		cepService = CEPServiceFactory.getCEPService();
	}
	
	@After
	public void tearDown() throws Exception {
		cepService = null;
	}
	
	@Test
	public void buscaPorCepValido() throws CEPServiceFailureException, CEPNaoEncontradoException {
		CEP cep = cepService.buscaPorNumeroCEP(CEP_VALIDO);
		System.out.println(cep);
		Assert.assertThat(cep, equalTo(CEP_COMPLETO_1));
	}
	
	@Test
	public void buscaMultiplaPorCepValido() throws CEPServiceFailureException, CEPNaoEncontradoException {

		// Primeira Tentativa
		CEP cep = cepService.buscaPorNumeroCEP(CEP_VALIDO);
		Assert.assertThat(cep, equalTo(CEP_COMPLETO_1));
		
		// Segunda Tentativa
		cep = cepService.buscaPorNumeroCEP(CEP_VALIDO);
		Assert.assertThat(cep, equalTo(CEP_COMPLETO_1));
		
		// Terceira Tentativa
		cep = cepService.buscaPorNumeroCEP(CEP_VALIDO);
		Assert.assertThat(cep, equalTo(CEP_COMPLETO_1));
		
	}
	
	@Test(expected=CEPNaoEncontradoException.class)
	public void buscaPorCepInvalido() throws CEPServiceFailureException, CEPNaoEncontradoException {
		cepService.buscaPorNumeroCEP("1308444000");
	}
	
	@Test
	public void buscaPorEndereco() {
		List<CEP> ceps = cepService.buscaPorEndereco("Rua Nordau Goncalves de Melo");
		assertNotNull(ceps);
		assertThat(ceps.size(), equalTo(1));
		assertThat(ceps.get(0), equalTo(CEP_COMPLETO_1));
	}
	
	@Test
	public void buscaPorEnderecoComCaracteresEspeciais() {
		List<CEP> ceps = cepService.buscaPorEndereco("Rua Nordau Gonçalves de Melo");
		assertNotNull(ceps);
		assertThat(ceps.size(), equalTo(1));
		assertThat(ceps.get(0), equalTo(CEP_COMPLETO_1));
	}
	
	@Test
	public void biscaPorVariosCepsMesmoEndereco() {
		List<CEP> ceps = cepService.buscaPorEndereco("Nordau");
		assertNotNull(ceps);
		assertThat(ceps.size(), equalTo(48));
		assertThat(ceps.get(0), equalTo(CEP_COMPLETO_1));
		assertThat(ceps.get(1), equalTo(CEP_COMPLETO_2));
	}
}
