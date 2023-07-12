package mg.marketmanagement.linecommandservice.dto;

import mg.marketmanagement.linecommandservice.model.LineCommand;

public interface LineCommandMapper {
    LineCommand mapToLineCommand(LineCommandRequest request);
    LineCommandResponse mapToLineCommandResponse(LineCommand lineCommand);
}
