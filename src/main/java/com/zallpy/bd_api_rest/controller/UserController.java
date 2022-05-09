package com.zallpy.bd_api_rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import com.zallpy.bd_api_rest.services.UserService;
import lombok.AllArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zallpy.bd_api_rest.dto.UserFullDTO;
import com.zallpy.bd_api_rest.services.exceptions.ResourceNotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserFullDTO>> findAll() {
        List<UserFullDTO> list = service.findAll();
        for (UserFullDTO user : list) {
            user.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserFullDTO> findById(@PathVariable Long id) {
        UserFullDTO obj = service.findById(id);
        obj.add(linkTo(methodOn(UserController.class).findAll()).withRel("Lista de Usuários:"));
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<UserFullDTO> insert(@Valid @RequestBody UserFullDTO obj) {
        try {
            obj = service.insert(obj);
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri()).body(obj);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "Usuário não foi salvo");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UserFullDTO> update(@Valid @RequestBody UserFullDTO obj) {
        obj = service.update(obj);
        obj.add(linkTo(methodOn(UserController.class).findAll()).withRel("Lista de Usuários:"));
        return ResponseEntity.ok().body(obj);
    }
}
