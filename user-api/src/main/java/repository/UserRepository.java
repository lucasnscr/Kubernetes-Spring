package repository;

import java.util.List;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByCpfAndKey(String cpf, String key);

	List<User> queryByNomeLike(String name);


}
