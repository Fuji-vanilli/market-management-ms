package mg.marketmanagement.billing.service.service;

import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.billing.service.dto.BillMapper;
import mg.marketmanagement.billing.service.dto.BillRequest;
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
        bill.setUuid(UUID.randomUUID().toString());
        bill.setDate(LocalDateTime.now());

        User user= new User();
        try{
            user= webClient.getUser(bill.getEmailUser());
        }catch (Exception e){
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "user with the email: "+bill.getEmailUser()+" doesn't exist on the database"
            );
        }
        bill.setUser(user);
        billRepository.save(bill);

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
        return null;
    }

    @Override
    public Response all() {
        return null;
    }

    @Override
    public Response delete(String uuid) {
        return null;
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
