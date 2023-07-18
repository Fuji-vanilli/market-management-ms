package mg.marketmanagement.linecommandservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.linecommandservice.dto.LineCommandRequest;
import mg.marketmanagement.linecommandservice.model.LineCommand;
import mg.marketmanagement.linecommandservice.service.LineCommandService;
import mg.marketmanagement.linecommandservice.utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static mg.marketmanagement.linecommandservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class LineCommandApi implements LineCommandController {

    private final LineCommandService lineCommandService;
    @Override
    public ResponseEntity<Response> add(LineCommandRequest request) throws JSONException {
        return ResponseEntity.ok(lineCommandService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) throws JSONException {
        return ResponseEntity.ok(lineCommandService.get(code));
    }

    @Override
    public List<LineCommand> getByList(List<String> codes) {
        return lineCommandService.getByList(codes);
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(lineCommandService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(lineCommandService.delete(code));
    }
}
