package mg.marketmanagement.entrepriseservice.repository;

import mg.marketmanagement.entrepriseservice.model.Entreprise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EntrepriseRespository extends MongoRepository<Entreprise, String> {
    Optional<Entreprise> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);
}
