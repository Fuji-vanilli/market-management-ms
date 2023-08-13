package mg.marketmanagement.linecommandservice.controller;

import mg.marketmanagement.linecommandservice.dto.LineCommandRequest;
import mg.marketmanagement.linecommandservice.model.LineCommand;
import mg.marketmanagement.linecommandservice.utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface LineCommandController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody LineCommandRequest request) throws JSONException;
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code) throws JSONException;
    @GetMapping("byCodes")
    List<LineCommand> getByList(@RequestParam("codes") List<String> codes);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
