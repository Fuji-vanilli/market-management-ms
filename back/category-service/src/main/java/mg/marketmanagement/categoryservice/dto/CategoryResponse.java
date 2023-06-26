package mg.marketmanagement.categoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String code;
    private String name;
}
