package mg.marketmanagement.billing.service.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.billing.service.dto.BillRequest;
import mg.marketmanagement.billing.service.service.BillService;
import mg.marketmanagement.userservice.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static mg.marketmanagement.billing.service.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class BillApi implements BillController{
    private final BillService billService;

    @Override
    public ResponseEntity<Response> add(BillRequest request) {
        return ResponseEntity.ok(billService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String uuid) {
        return ResponseEntity.ok(billService.get(uuid));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(billService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String uuid) {
        return ResponseEntity.ok(billService.delete(uuid));
    }
}
