package mg.marketmanagement.clientservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.clientservice.dto.ClientRequest;
import mg.marketmanagement.clientservice.utils.Response;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface ClientService {
    Response add(ClientRequest request);
    Response get(String code);
    Response getWithCommand(String code);
    Response all();
    Response allWithCommand();
    Response delete(String code);
}
