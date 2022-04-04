package com.zallpy.bd_api_rest.resouces;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

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

import com.zallpy.bd_api_rest.dto.UserDTO;
import com.zallpy.bd_api_rest.entities.User;
import com.zallpy.bd_api_rest.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> list = service.findAll();
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for(UserDTO user : list) {
				long id = user.getId();
				user.add(linkTo(methodOn(UserResource.class).findById(id)).withSelfRel());
			}
			return ResponseEntity.ok().body(list);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO obj = service.findById(id);
		if(obj.equals(null)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			obj.add(linkTo(methodOn(UserResource.class).findAll()).withRel("Lista de Usuários:"));
			return ResponseEntity.ok().body(obj);
		}
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		if(obj.equals(null)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			obj.add(linkTo(methodOn(UserResource.class).findAll()).withRel("Lista de Usuários:"));
		}
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO obj) {
		obj = service.update(id, obj);
		if(obj.equals(null)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			obj.add(linkTo(methodOn(UserResource.class).findAll()).withRel("Lista de Usuários:"));
		}
		return ResponseEntity.ok().body(obj);
	}
}
