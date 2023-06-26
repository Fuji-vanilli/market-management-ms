package mg.marketmanagement.billing.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BillRequest {
    private String methodPayement;
    private Double total;
    private String detailProduct;
    private String emailUser;
}
