package mg.marketmanagement.commandefournisseurservice.repository;

import mg.marketmanagement.commandefournisseurservice.model.CommandFournisseur;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommandFournisseurRepository extends MongoRepository<CommandFournisseur, String> {
    boolean existsByCode(String code);

    Optional<CommandFournisseur> findByCode(String code);
    void deleteByCode(String code);
    List<CommandFournisseur> findByCodeIn(List<String> codes);
}
