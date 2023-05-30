package com.dea.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dea.workshopmongo.domain.Post;
import com.dea.workshopmongo.services.PostService;

@RestController // Informo que essa classe é um recurso Rest
@RequestMapping(value = "/posts/") // Caminho do endpoint usando o recurso
public class PostResource {

	// Pensando nas camadas, o Controlador Rest precisa conversar com o serviço. Da
	// mesma forma que injetei o repository la no service, aqui vou injetar ou
	// declarar o serviço.
	@Autowired
	private PostService service;

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}

