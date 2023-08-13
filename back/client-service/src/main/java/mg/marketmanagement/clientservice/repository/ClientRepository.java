package mg.marketmanagement.clientservice.repository;

import mg.marketmanagement.clientservice.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);
    List<Client> findByCodeIn(List<String> codes);
    Optional<Client> findByFirstname(String firstname);
}
