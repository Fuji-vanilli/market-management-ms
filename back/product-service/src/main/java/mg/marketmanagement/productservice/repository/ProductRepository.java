package mg.marketmanagement.productservice.repository;

import mg.marketmanagement.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findByCode(String code);
    void deleteByCode(String code);
    boolean existsByCodeAndName(String code, String name);
}
