package br.gm.jeronimo.ordemServico.domain.service;

import br.gm.jeronimo.ordemServico.domain.exception.DomainException;
import br.gm.jeronimo.ordemServico.domain.model.Cliente;
import br.gm.jeronimo.ordemServico.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */

@Service
public class ClienteServer {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente){
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            //lançar exception
            throw new DomainException ("Já existe um cliente com esse email cadastrado");
        }
        
        return clienteRepository.save(cliente);
    }
    
    public void excluir (Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
