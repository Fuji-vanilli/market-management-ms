package mg.marketmanagement.billing.service.dto;

import mg.marketmanagement.billing.service.model.Bill;


public interface BillMapper {
    Bill mapToBill(BillRequest request);
    BillResponse mapToBillResponse(Bill bill);
}
