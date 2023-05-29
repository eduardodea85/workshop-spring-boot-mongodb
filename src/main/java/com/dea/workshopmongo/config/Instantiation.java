package com.dea.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dea.workshopmongo.domain.Post;
import com.dea.workshopmongo.domain.User;
import com.dea.workshopmongo.repository.PostRepository;
import com.dea.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;//Repository injetado
	
	@Autowired
	private PostRepository postRepository;//Repository injetado
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();//Limpa as coleções
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");//Instancia os usuários
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", maria);//Instancia os post associando com o autor.
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", maria);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));//Salva tudo.
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
