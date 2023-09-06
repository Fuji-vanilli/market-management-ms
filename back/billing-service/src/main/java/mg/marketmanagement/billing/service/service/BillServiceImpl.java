package mg.marketmanagement.billing.service.service;

import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.billing.service.dto.BillMapper;
import mg.marketmanagement.billing.service.dto.BillRequest;
import mg.marketmanagement.billing.service.dto.Product;
import mg.marketmanagement.billing.service.dto.User;
import mg.marketmanagement.billing.service.model.Bill;
import mg.marketmanagement.billing.service.repository.BillRepository;
import mg.marketmanagement.billing.service.validator.BillValidator;
import mg.marketmanagement.billing.service.webClient.WebClientGetting;
import mg.marketmanagement.userservice.Utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.UnknownServiceException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BillServiceImpl implements BillService{
    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final WebClientGetting webClient;

    @Override
    public Response add(BillRequest request) {
        List<String> errors= BillValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("same field required! Please try again!!!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "same field required!!! Try again!!!"
            );
        }
        if(billRepository.existsByEmailUserAndDetailProduct(request.getEmailUser(), request.getDetailProduct())){
            log.error("bill already exist!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "bill already exist...Please verify and try again!"
            );
        }
        Bill bill= billMapper.mapToBill(request);

        Map<Product, Integer> products= new HashMap<>();
        User user=  User.builder()
                .firstname("rakoto")
                .lastname("nirina")
                .contact("252585452")
                .build();

        double total= 0.0;
        try{
            //user= webClient.getUser(bill.getEmailUser());
            for(Map.Entry<String, Integer> entry: request.getProductsCode().entrySet()){
                Product product= webClient.getProduct(entry.getKey());
                product.setQuantity(entry.getValue());
                product.setSubTotal(entry.getValue()*product.getPrice());
                products.put(product, entry.getValue());

                total+= product.getPrice()* entry.getValue();
            }
        }catch (Exception e){
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "user with the email: "+bill.getEmailUser()+" doesn't exist on the database"
            );
        }
        bill.setUser(user);
        bill.setProducts(products);
        bill.setTotal(total);
        bill.setUuid(UUID.randomUUID().toString());
        bill.setDate(LocalDateTime.now());
        billRepository.save(bill);

        try{
            GenerateReport.generate(bill);
        }catch (Exception e){
            log.error("error to generate the bill report");
            throw new RuntimeException("error to generate the bill report!");
        }
        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{uuid}")
                .buildAndExpand("api/bill/get/"+bill.getUuid())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "bill", billMapper.mapToBillResponse(bill)
                ),
                "bill added successfully!"
        );
    }

    @Override
    public Response get(String uuid) {
        Bill bill= new Bill();
        User user= new User();
        try{
            bill= billRepository.findByUuid(uuid).orElse(null);
            assert bill != null;
            user= webClient.getUser(bill.getEmailUser());
        }catch (Exception e){
            log.error("bill with the uuid: {} doesn't exist on the database!", uuid);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "bill with the uuid: "+uuid+" doesn't exist on the database!"
            );
        }
        bill.setUser(user);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "bill", bill
                ),
                "bill with the uuid: "+uuid+" getting successfully!"
        );
    }

    @Override
    public Response all() {
        List<Bill> bills= billRepository.findAll().stream()
                .peek(bill -> {
                    User user= new User();
                    try{
                        user= webClient.getUser(bill.getEmailUser());
                    }catch (Exception e){
                        log.error("user not fetch");
                    }
                    bill.setUser(user);
                })
                .toList();

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "bills", bills
                ),
                "all bill getting successfully!"
        );
    }

    @Override
    public Response delete(String uuid) {
        if(billRepository.findByUuid(uuid).isEmpty()){
            log.error("sorry the bill with the uuid {} doesn't exist", uuid);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry  the bill with the uuid! "+uuid+" doesn't exist on the database!"
            );
        }
        billRepository.deleteByUuid(uuid);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "bill deleted successfully!"
        );
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message){
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .data(data)
                .location(location)
                .message(message)
                .build();
    }
}
