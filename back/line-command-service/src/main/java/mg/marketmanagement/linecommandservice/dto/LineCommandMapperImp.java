package mg.marketmanagement.linecommandservice.dto;

import mg.marketmanagement.linecommandservice.model.LineCommand;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LineCommandMapperImp implements LineCommandMapper{
    @Override
    public LineCommand mapToLineCommand(LineCommandRequest request) {
        return LineCommand.builder()
                .code(request.getCode())
                .codeArticle(request.getCodeArticle())
                .quantity(request.getQuantity())
                .build();
    }

    @Override
    public LineCommandResponse mapToLineCommandResponse(LineCommand lineCommand) {
        return LineCommandResponse.builder()
                .id(lineCommand.getId())
                .code(lineCommand.getCode())
                .article(lineCommand.getArticle())
                .price(lineCommand.getPrice())
                .quantity(lineCommand.getQuantity())
                .build();
    }
}
