package mg.marketmanagement.linecommandservice.repository;

import mg.marketmanagement.linecommandservice.model.LineCommand;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LineCommandRepository extends MongoRepository<LineCommand, String> {
    boolean existsByCode(String code);
    Optional<LineCommand> findByCode(String code);
    List<LineCommand> findByCodeIn(List<String> codes);
    void deleteByCode(String code);
}
