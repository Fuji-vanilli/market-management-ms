package mg.marketmanagement.billing.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BillRequest {
    private String methodPayement;
    private String detailProduct;
    private String emailUser;
    private Map<String, Integer> productsCode;
}
