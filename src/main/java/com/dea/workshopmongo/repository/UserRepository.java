package com.dea.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dea.workshopmongo.domain.User;

//implementando o repository usando o springdata colocando a anotacao Repository

@Repository // Em UserResource, refatorar o código, usando o UserService para buscar os
			// //usuários
public interface UserRepository extends MongoRepository<User, String> { // Essa interface exetende ou herda da interface
																		// MongoRepository. Esse MongoRepository precisa
																		// de dois dados, o tipo da classe de dominio
																		// que vai gerenciar (Nesse caso é um
																		// UserRepositoty para gerenciar classes User).
																		// Em segundo lugar preciso dizer qual o tipo do
																		// ID dessa classe. (Nesse caso o tipo do id é
																		// String).

}

//Somente com isso um objeto UserRepository vai ser capaz de fazer varias operações básicas com os meus usuários (salvar, recuperar, deletar, atuaizar), tudo isso já está embutido no MongoRepository.