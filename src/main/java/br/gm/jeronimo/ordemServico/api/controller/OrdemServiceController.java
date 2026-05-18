package br.gm.jeronimo.ordemServico.api.controller;

import br.gm.jeronimo.ordemServico.domain.dto.AtualizaStatusDTO;
import br.gm.jeronimo.ordemServico.domain.model.OrdemServico;
import br.gm.jeronimo.ordemServico.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServiceController {
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar  (@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping("/clientes/{clienteId}/ordem-servico")
    public List<OrdemServico> listaPorCliente(@PathVariable Long clienteId) {
    return ordemServicoService.buscaPorCliente(clienteId);
}
    
    @GetMapping
    public List<OrdemServico> listar() {
    return ordemServicoService.listarTodas();
}
    
    @GetMapping("/{ordemServicoId}")
    public OrdemServico buscar(@PathVariable Long ordemServicoId) {
    return ordemServicoService.buscarPorId(ordemServicoId);
}
    
    @PutMapping("/{ordemServicoId}")
    public OrdemServico alterar(@PathVariable Long ordemServicoId, @RequestBody OrdemServico ordemServico) {
    return ordemServicoService.alterar(ordemServicoId, ordemServico);
}
    
    @DeleteMapping("/{ordemServicoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long ordemServicoId) {
    ordemServicoService.excluir(ordemServicoId);
}
    
    @PutMapping("/atualiza-status/{ordemServicoID}")
public ResponseEntity<OrdemServico> atualizaStatus(
        @PathVariable Long ordemServicoID,
        @Valid @RequestBody AtualizaStatusDTO statusDTO) {

    Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
            ordemServicoID,
            statusDTO.status());


    if (optOS.isPresent()) {
        return ResponseEntity.ok(optOS.get());

    } else {
        return ResponseEntity.notFound().build();

    }

}
    
}
