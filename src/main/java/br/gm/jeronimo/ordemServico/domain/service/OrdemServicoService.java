package br.gm.jeronimo.ordemServico.domain.service;

import br.gm.jeronimo.ordemServico.domain.model.OrdemServico;
import br.gm.jeronimo.ordemServico.domain.model.StatusOrdemServico;
import br.gm.jeronimo.ordemServico.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */

@Service
public class OrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar (OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }
    
    
}
