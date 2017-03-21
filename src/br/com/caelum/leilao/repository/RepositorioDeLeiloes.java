
package br.com.caelum.leilao.repository;

import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;

/**
 * @author Ramon Vieira
 *
 */
public interface RepositorioDeLeiloes {
	
	void salva(Leilao leilao);
    List<Leilao> encerrados();
    List<Leilao> correntes();
    void atualiza(Leilao leilao);

}
