package projeto_spring_data.spring.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import projeto_spring_data.spring.rest.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "select p from Usuario p where upper(trim(p.nome)) like %?1%")
	List<Usuario> buscarPorNome(String nome);
	
}
