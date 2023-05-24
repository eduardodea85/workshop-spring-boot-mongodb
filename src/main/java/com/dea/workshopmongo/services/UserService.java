package com.dea.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dea.workshopmongo.domain.User;
import com.dea.workshopmongo.dto.UserDTO;
import com.dea.workshopmongo.repository.UserRepository;
import com.dea.workshopmongo.services.exception.ObjectNotFoundException;

//Criar o serviço responsável por trabalhar com os usuários.
//Para eu dizer ao spring que essa vai ser um serviço que pode ser injetavel em outras classes tenho que por a anotação @Service 
@Service
public class UserService {

	@Autowired // Essa anotação instancia automaticamente um objeto no serviço. Quando
				// declaramaos um objeto numa classe usando o Autowired, o próprio spring vai
				// procurar a definição desse objeto que nesse caso é o repositorio e vai
				// instanciar o objeto. Não precisa fazer mais nada porque é um mecanismo de
				// injeção dependencia automatica do spring.
	private UserRepository repo; // Como implementar no seriço uma operação para retornar todos os usuários do
									// banco? Seguindo o raciocinio das camadas, o meu serviço precisa conversar com
									// o repositório. Por isso estou instanciando um objeto do tipo UserRepository.

	public List<User> findAll() { // Esse findAll é o método dentro da classe de serviço responsável por retornar
									// todos os usuários do banco
		return repo.findAll(); // chamando o retorno da instancia que criamos para puxar do banco as
								// informações.
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));		
	}
	
	public User insert(User obj) { //public User porque vai retornar o objeto inserido, recebendo o User obj como argumento
		return repo.insert(obj); //Esse método retorna o repositorio insert passando o obj como argumento. Como o repositorio ele retorna o objeto, mantivemos o padrão User no serviço 
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail()); //new User recebendo os dados do DTO como paramêtro. Já temos um construtor que pega esses dados.
	}

}

//fica claro que o serviço acessa o repositorio.