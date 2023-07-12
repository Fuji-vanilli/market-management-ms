package mg.marketmanagement.commandeclientservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.commandeclientservice.dto.LineCommand;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "command-client")
public class CommandClient {
    private String id;
    private String code;
    private BigDecimal totalPrice;
    private List<String> codeLineCommands= new ArrayList<>();
    private List<LineCommand> lineCommands= new ArrayList<>();
}
