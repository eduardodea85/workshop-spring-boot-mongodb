package com.dea.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dea.workshopmongo.domain.User;
import com.dea.workshopmongo.services.UserService;

@RestController // Informo que essa classe é um recurso Rest
@RequestMapping(value = "/users") // Caminho do endpoint usando o recurso /users
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() { // Crio um método que vai retornar uma lista de usuários dando o nome
													// do método de findAll(busca todos)

		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}