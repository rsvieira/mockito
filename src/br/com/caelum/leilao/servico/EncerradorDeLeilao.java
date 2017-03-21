package br.com.caelum.leilao.servico;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.repository.RepositorioDeLeiloes;

public class EncerradorDeLeilao implements RepositorioDeLeiloes{

	private int total = 0;

	private final RepositorioDeLeiloes dao;
	
    public EncerradorDeLeilao(RepositorioDeLeiloes dao) {
        this.dao = dao;
    }

	public void encerra() {
		
		List<Leilao> todosLeiloesCorrentes = dao.correntes();

		for (Leilao leilao : todosLeiloesCorrentes) {
			if (comecouSemanaPassada(leilao)) {
				total++;
				leilao.encerra();
				dao.atualiza(leilao);
			}
		}
	}

	private boolean comecouSemanaPassada(Leilao leilao) {
		return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
	}

	private int diasEntre(Calendar inicio, Calendar fim) {
		Calendar data = (Calendar) inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim)) {
			data.add(Calendar.DAY_OF_MONTH, 1);
			diasNoIntervalo++;
		}

		return diasNoIntervalo;
	}

	public int getTotalEncerrados() {
		return total;
	}

	/* (non-Javadoc)
	 * @see br.com.caelum.leilao.repository.RepositorioDeLeiloes#salva(br.com.caelum.leilao.dominio.Leilao)
	 */
	public void salva(Leilao leilao) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.com.caelum.leilao.repository.RepositorioDeLeiloes#encerrados()
	 */
	public List<Leilao> encerrados() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.caelum.leilao.repository.RepositorioDeLeiloes#correntes()
	 */
	public List<Leilao> correntes() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.caelum.leilao.repository.RepositorioDeLeiloes#atualiza(br.com.caelum.leilao.dominio.Leilao)
	 */
	public void atualiza(Leilao leilao) {
		// TODO Auto-generated method stub
		
	}
}
