package mg.marketmanagement.linecommandservice.repository;

import mg.marketmanagement.linecommandservice.model.LineCommand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LineCommandRepository extends MongoRepository<LineCommand, String> {
    boolean existsByCode(String code);
}
