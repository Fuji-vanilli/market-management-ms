package mg.marketmanagement.linecommandservice.service;

import mg.marketmanagement.linecommandservice.dto.LineCommandRequest;
import mg.marketmanagement.linecommandservice.model.LineCommand;
import mg.marketmanagement.linecommandservice.utils.Response;
import org.json.JSONException;

import java.util.List;

public interface LineCommandService {
    Response add(LineCommandRequest request) throws JSONException;
    Response get(String code) throws JSONException;
    List<LineCommand> getByList(List<String> codes);
    Response all();
    Response delete(String code);
}
