package mg.marketmanagement.billing.service.service;

import mg.marketmanagement.billing.service.dto.BillRequest;
import mg.marketmanagement.userservice.Utils.Response;

public interface BillService {
    Response add(BillRequest request);
    Response get(String uuid);
    Response all();
    Response delete(String uuid);
}
