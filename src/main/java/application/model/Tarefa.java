package application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

// As anotações aqui presentes são do ecosistema javas presentes no JPA 

@Entity
@Getter
@Setter
public class Tarefa {
    @Id
    private long id;
    private String descricao;
}
