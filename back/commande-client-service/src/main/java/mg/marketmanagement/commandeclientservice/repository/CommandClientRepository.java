package mg.marketmanagement.commandeclientservice.repository;

import mg.marketmanagement.commandeclientservice.model.CommandClient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommandClientRepository extends MongoRepository<CommandClient, String> {
    Optional<CommandClient> findByCode(String code);
    boolean existsByCode(String code);
    List<CommandClient> findByCodeIn(List<String> codes);
    void deleteByCode(String code);
}
