package application.controller;

import org.springframework.web.bind.annotation.RestController;

import application.model.Tarefa;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController // spring natualmente converte a resposta em JSON
@RequestMapping("/tarefas")
public class TarefaController {

    @PostMapping
    public Tarefa insert(@RequestBody Tarefa novaTarefa) {
        
        return novaTarefa;
    }
}
