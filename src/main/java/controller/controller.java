package controller;

import model.usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.repository;

import java.util.List;

@RestController
@RequestMapping({"/usuario"})
public class controller {
    @Autowired
    private repository repo;

    //Listar todos os usuários
    @GetMapping
    public List findAll() {
        return repo.findAll();
    }
    //Retorna usuário por id
    @GetMapping(value = "{id}")
    public ResponseEntity<usuarios> findById(@PathVariable long id){
        return (repo.findById(id))
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    //Criar novo usuário
    @PostMapping
    public usuarios create(@RequestBody usuarios usuario) {
        usuario.setSenha(usuario.getSenha());
        return repo.save(usuario);
    }

    //Atualizar usuário
    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody usuarios usuario){
        return (repo.findById(id))
                .map(record -> {
                    record.setUsuario(usuario.getUsuario());
                    record.setSenha(usuario.getSenha());
                    usuarios update = repo.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }

    //Deletar usuário
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id){
        return repo.findById(id)
                .map(record -> {
                    repo.deleteById(id);
                    return ResponseEntity.ok().body("Deletado com sucesso!");
                }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/")
    public String teste() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
