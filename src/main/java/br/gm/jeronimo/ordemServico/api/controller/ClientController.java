
package br.gm.jeronimo.ordemServico.api.controller;

import br.gm.jeronimo.ordemServico.domain.model.Cliente;
import br.gm.jeronimo.ordemServico.domain.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */

@RestController
public class ClientController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    
    @GetMapping("/clientes/{clienteID}")
    public ResponseEntity <Cliente> buscar(@PathVariable Long clienteID) {
        
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        
        if (cliente.isPresent()){
        
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();}
    }
    
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente) {
        
        return clienteRepository.save(cliente);
    }
        
    
    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar (@PathVariable Long clienteID,
                                             @RequestBody Cliente cliente){
        //verifica se o cliente existe
        if (!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }
        
        cliente.setId(clienteID);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
    
    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir (@PathVariable Long clienteID){
        //verifica se o cliente existe ou nao
        
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(clienteID);
        return ResponseEntity.noContent().build();
    }
        


}
