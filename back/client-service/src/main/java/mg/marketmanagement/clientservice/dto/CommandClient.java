package mg.marketmanagement.clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CommandClient {
    private String code;
    private Instant date;
    private List<LineCommandClient> lineCommandClient= new ArrayList<>();
}
