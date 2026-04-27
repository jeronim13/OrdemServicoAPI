package br.gm.jeronimo.ordemServico.domain.repository;

import br.gm.jeronimo.ordemServico.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author digma
 */
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    
    
}
