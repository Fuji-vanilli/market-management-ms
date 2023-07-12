package mg.marketmanagement.clientservice.repository;

import mg.marketmanagement.clientservice.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByFirstname(String firstname);
    boolean existsByEmail(String email);
}
