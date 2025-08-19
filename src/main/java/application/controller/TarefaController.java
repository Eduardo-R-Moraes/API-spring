package application.controller;

import org.springframework.web.bind.annotation.RestController;

import application.model.Tarefa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import application.repository.TarefaRepository;

@RestController // spring natualmente converte a resposta em JSON
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @PostMapping
    public Tarefa insert(@RequestBody Tarefa novaTarefa) {
        return tarefaRepo.save(novaTarefa);
    }

    @GetMapping("/{id}")
    public Tarefa getOne(@PathVariable long id) {
        Optional<Tarefa> resultado = tarefaRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada."
            );
        }

        return resultado.get();
    }

    @GetMapping
    public Iterable<Tarefa> getAll() {
        return tarefaRepo.findAll();
    }

    @PutMapping("/{id}")
    public Tarefa update(@PathVariable long id, @RequestBody Tarefa novosDados) {
        Optional<Tarefa> resultado = tarefaRepo.findById(id);

        if (resultado.isPresent()) {
            resultado.get().setDescricao(novosDados.getDescricao());
            return tarefaRepo.save(resultado.get());
        }

        return new Tarefa();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        tarefaRepo.deleteById(id);
    }
}
