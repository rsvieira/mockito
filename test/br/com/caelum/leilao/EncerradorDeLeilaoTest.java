/**
 * 
 */
package br.com.caelum.leilao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.dao.LeilaoDao;
import br.com.caelum.leilao.servico.EncerradorDeLeilao;


/**
 * @author Ramon Vieira
 *
 */

public class EncerradorDeLeilaoTest {

	@Test
	public void deveEncerrarLeiloesQueComecaramUmaSemanaAtras() {

		Calendar antiga = Calendar.getInstance();
		antiga.set(1989, 4, 16);

		Leilao leilao1 = new CriadorDeLeilao().para("TV Plasma").naData(antiga).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();
		
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1, leilao2);

		LeilaoDao daoFalso = mock(LeilaoDao.class);
		
		// retorna uma lista mockada simulando BD
		when(daoFalso.correntes()).thenReturn(leiloesAntigos);

		daoFalso.salva(leilao1);
		daoFalso.salva(leilao2);

		EncerradorDeLeilao encerradorDeLeilao = new EncerradorDeLeilao(daoFalso);
		encerradorDeLeilao.encerra();

		assertEquals(2, encerradorDeLeilao.getTotalEncerrados());
		assertTrue(leilao1.isEncerrado());
		assertTrue(leilao2.isEncerrado());

	}

}
