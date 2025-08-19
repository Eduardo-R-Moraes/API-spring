package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Aluno;
import application.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping
    public Iterable<Aluno> getAll() {
        return alunoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Aluno getbyid(@PathVariable long id) {
        return alunoRepo.findById(id).get();
    }

    @PostMapping
    public Aluno create(@RequestBody Aluno novoAluno) {
        return alunoRepo.save(novoAluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable long id, @RequestBody Aluno update) {
        Optional<Aluno> aluno = alunoRepo.findById(id);

        if (aluno.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado."
            );
        }

        aluno.get().setIdade(update.getIdade());
        aluno.get().setNome(update.getNome());
        return alunoRepo.save(aluno.get());
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        if (!alunoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }

        alunoRepo.deleteById(id);
    }

}
