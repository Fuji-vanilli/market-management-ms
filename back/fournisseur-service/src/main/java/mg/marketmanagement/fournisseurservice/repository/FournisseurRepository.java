package mg.marketmanagement.fournisseurservice.repository;

import mg.marketmanagement.fournisseurservice.model.Fournisseur;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FournisseurRepository extends MongoRepository<Fournisseur, String> {
    Optional<Fournisseur> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);
    List<Fournisseur> findByCodeIn(List<String> codes);
}
