package br.gm.jeronimo.ordemServico.domain.service;

import br.gm.jeronimo.ordemServico.domain.exception.DomainException;
import br.gm.jeronimo.ordemServico.domain.model.OrdemServico;
import br.gm.jeronimo.ordemServico.domain.model.StatusOrdemServico;
import br.gm.jeronimo.ordemServico.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    
    public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status) {
        
        Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);
        
        if (optOrdemServico.isPresent()) {

        OrdemServico ordemServico = optOrdemServico.get();

        // Verifica se ordem está ABERTA.
        if (ordemServico.getStatus()==StatusOrdemServico.ABERTA 
                && status != StatusOrdemServico.ABERTA) {

            ordemServico.setStatus(status);
            ordemServico.setDataFinalizacao(LocalDateTime.now());
            ordemServicoRepository.save(ordemServico);
            return Optional.of(ordemServico);

        } else {

            // ops.. ordem FINALIZADA ou CANCELADA. Não alterar.
            return Optional.empty();
        }

    } else {
        // Lança exception se ID não encontrado.
        throw new DomainException("Não existe OS com o id " + ordemServicoID);
    }
}
    
    //metodo com a nva lista
    public List<OrdemServico> buscaPorCliente(Long clienteId) {
        return ordemServicoRepository.findByClienteId(clienteId);
}
    
    public List<OrdemServico> listarTodas() {
        return ordemServicoRepository.findAll();}
    
    
    public OrdemServico buscarPorId(Long id) {
        return ordemServicoRepository.findById(id).orElse(null);
}
    
    public OrdemServico alterar(Long id, OrdemServico ordem) {
    //Busca a ordem do jeito que ela está hoje no banco
    OrdemServico ordemExistente = ordemServicoRepository.findById(id).orElse(null);
    
    if (ordemExistente == null) {
        return null;
         //Se não existe
    }
    
    //Pega a data de abertura antiga e coloca no objeto novo
    ordem.setDataAbertura(ordemExistente.getDataAbertura());
    ordem.setId(id); // Garante o ID correto
    
    //Salva com a data
    return ordemServicoRepository.save(ordem);
}

    public void excluir(Long id) {
    ordemServicoRepository.deleteById(id);
}


}
