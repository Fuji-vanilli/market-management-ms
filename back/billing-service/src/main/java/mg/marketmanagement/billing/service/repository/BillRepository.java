package mg.marketmanagement.billing.service.repository;

import mg.marketmanagement.billing.service.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Optional<Bill> findByUuid(String uuid);
    void deleteByUuid(String uuid);
    boolean existsByEmailUserAndDetailProduct(String emailUser, String detailProduct);
}
