package mg.marketmanagement.entrepriseservice.microservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CommandFournisseur {
    private String code;
    private Instant date;
    private BigDecimal totalPrice;
    private List<String> codeLineCommands= new ArrayList<>();
    private List<LineCommand> lineCommands= new ArrayList<>();
}
