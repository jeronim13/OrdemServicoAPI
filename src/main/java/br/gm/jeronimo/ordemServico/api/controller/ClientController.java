
package br.gm.jeronimo.ordemServico.api.controller;

import br.gm.jeronimo.ordemServico.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */

@RestController
public class ClientController {
    
    @PersistenceContext
    //INSTANCIANDO E INJETANDO O ENTITYMANAGER (CAREGA O ATRIBUTO)
    private EntityManager manager;//Manager = interface jakarta persistence usado para consulta nas entities
    
    
    @GetMapping("/clientes")
    public List<Cliente>listas() {
        
        //Linguagem JPQL (tipo um sql do JAKARTA)
        
        return manager.createQuery("from Cliente", Cliente.class)//(create query)cria uma consulta
                .getResultList();//getResultLst ta retornando a lista tipo cliente
    }
    
    

}
