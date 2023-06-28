package mg.marketmanagement.billing.service.dto;

import mg.marketmanagement.billing.service.model.Bill;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillMapperImpl implements BillMapper{
    @Override
    public Bill mapToBill(BillRequest request) {
        return Bill.builder()
                .methodPayement(request.getMethodPayement())
                .detailProduct(request.getDetailProduct())
                .emailUser(request.getEmailUser())
                .productsCode(request.getProductsCode())
                .build();
    }

    @Override
    public BillResponse mapToBillResponse(Bill bill) {
        return BillResponse.builder()
                .id(bill.getId())
                .uuid(bill.getUuid())
                .methodPayement(bill.getMethodPayement())
                .total(bill.getTotal())
                .detailProduct(bill.getDetailProduct())
                .user(bill.getUser())
                .date(bill.getDate())
                .subTotal(bill.getSubTotal())
                .products(bill.getProducts())
                .build();

    }
}
