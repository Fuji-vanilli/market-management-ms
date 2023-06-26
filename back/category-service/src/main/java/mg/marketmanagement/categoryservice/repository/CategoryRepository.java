package mg.marketmanagement.categoryservice.repository;

import mg.marketmanagement.categoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);
}
