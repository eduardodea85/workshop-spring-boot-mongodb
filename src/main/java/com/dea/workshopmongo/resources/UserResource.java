package com.dea.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dea.workshopmongo.domain.User;
import com.dea.workshopmongo.dto.UserDTO;
import com.dea.workshopmongo.services.UserService;

@RestController // Informo que essa classe é um recurso Rest
@RequestMapping(value = "/users") // Caminho do endpoint usando o recurso /users
public class UserResource {

	// Pensando nas camadas, o Controlador Rest precisa conversar com o serviço. Da
	// mesma forma que injetei o repository la no service, aqui vou injetar ou
	// declarar o serviço.
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() { // Crio um método que vai retornar uma lista de usuários dando o nome
													// do método de findAll(busca todos)
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());//CONVERTE UMA LISTA USER PRA UMA LISTA USERDTO
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST) //Também aceita a assinatura PostMapping
	public ResponseEntity<Void> insert(@RequestBody	UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//Pega o objeto do novo endereço que iseriu
		return ResponseEntity.created(uri).build();//created retorna o código 201, que é o código de resposta http quando se cria o novo recurso. E aí passa o uri como argumento.Esse comando vai retornar uma resposta vazia, com o código 201 e com o cabeçalho contendo a localização do novo recurso criado.
	} 
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {//Resposta do método vazia Void.
		service.delete(id); //chamando o service.delete passando o id como argumento. Se tudo der certe, retorna uma resposta.
		return ResponseEntity.noContent().build();//Essa resposta quando faz uma operação e não precisa retornar nada, vai ser uma resposta com o código 204. O código 204 no ResonseEntity é o noContent
	}

}

//Aqui fica claro as camadas, onde um controlador Rest acessa os serviços.