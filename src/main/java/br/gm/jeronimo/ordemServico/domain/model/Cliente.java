
package br.gm.jeronimo.ordemServico.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author digma
 */
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Long para numeros maiores do tp primitivo convertendo pra json nao aceita o nulo (null)
    //ctrl H = modifiquei o long para Long,  repleace all.
   
    @NotBlank
    @Size(max = 60)
    private String nome;
    
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    
    @NotBlank
    @Size(max = 20)
    @Column(name = "telefone")
    private String fone;

   
   //constructor vazio permite criar objeto "em branco"
   //porta de entrada e constructor padrão, a base;
   //constructor default
    public Cliente() {
    }
    //constructor all(com parâmetros)
    //Obriga a passar os dados na criação
    public Cliente(Long id, String nome, String email, String fone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }
   
    //getters and setters
    //os controladores quem vê e quem mexe no que está la dentro
    
    //getters (informante)= Serve apenas para ler o valor. Ele responde à pergunta: "Qual é o valor atual?"
    
    //setters (porteiro)= Serve para alterar o valor. Ele diz: "Vou guardar esse novo valor para você, 
    //mas só se ele seguir as minhas regras."
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    

    //hashcode e equals= um não funciona sem o outro;
    //hash= é uma conta matemática rápida que diz em qual prateleira o objeto deve ser guardado.(localizador)
    //equals=comparação detalhada, serve para ter certeza se dois objetos são identicos.(a lupa, aprofundado)
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }
   
   
    
}
