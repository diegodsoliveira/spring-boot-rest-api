package projeto_spring_data.spring.rest.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import projeto_spring_data.spring.rest.api.model.Usuario;
import projeto_spring_data.spring.rest.api.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired // Injeção de dependência
	private UsuarioRepository usuarioRepository;
	
   
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String olaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario);
    	
    	return "Olá mundo " + nome;
    }
    
    @ResponseBody // retorna os dados para o corpo da resposta
    @GetMapping(value = "listatodos") // mapeamento da URL
    public ResponseEntity<List<Usuario>> listaUsuarios() {
    	List<Usuario> usuarios = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // retorna a lista em JSON
    	
    }
    
    @ResponseBody
    @PostMapping(value = "salvar")
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @ResponseBody
    @DeleteMapping(value = "delete")
    public ResponseEntity<String> delete(@RequestParam Long idUser) {
    	
    	usuarioRepository.deleteById(idUser);
    	
    	return new ResponseEntity<String>("Usuário deletado com sucesso!", HttpStatus.OK);
    	
    }
    
    @ResponseBody
    @GetMapping(value = "buscaruserid")
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name = "idUser") Long idUser) {
    	
    	Optional<Usuario> usuario = usuarioRepository.findById(idUser);
    	
    	return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    	
    }
    
    @ResponseBody
    @PutMapping(value = "atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("ID não foi informado", HttpStatus.OK);
    	} else {
    		
    		Usuario user = usuarioRepository.saveAndFlush(usuario);
    		
    		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	}
    }
    
    @ResponseBody
    @GetMapping(value = "buscarpornome")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome) {
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
    
}
