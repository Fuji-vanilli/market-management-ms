package mg.marketmanagement.entrepriseservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.entrepriseservice.dto.EntrepriseMapper;
import mg.marketmanagement.entrepriseservice.dto.EntrepriseRequest;
import mg.marketmanagement.entrepriseservice.microservice.Client;
import mg.marketmanagement.entrepriseservice.microservice.Fournisseur;
import mg.marketmanagement.entrepriseservice.model.Entreprise;
import mg.marketmanagement.entrepriseservice.repository.EntrepriseRespository;
import mg.marketmanagement.entrepriseservice.utils.Response;
import mg.marketmanagement.entrepriseservice.validator.EntrepriseValidator;
import mg.marketmanagement.entrepriseservice.webClient.WebClientGetter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements EntrepriseService{
    private final EntrepriseRespository entrepriseRespository;
    private final EntrepriseMapper entrepriseMapper;
    private final WebClientGetter webClient;

    @Override
    public Response add(EntrepriseRequest request) {
        List<String> errors= EntrepriseValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("some field not valid!!! please try again!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "some field not valid, please try again!!!!"
            );
        }
        if(entrepriseRespository.existsByCode(request.getCode())){
            log.error("entreprise already exist on the database, please try again!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "entreprise already exist on the database, please try again!"
            );
        }
        Entreprise entreprise= entrepriseMapper.mapToEntreprise(request);

        List<Client> client= webClient.getClients(entreprise.getCodeClients());
        List<Fournisseur> fournisseurs= webClient.getFournisseurs(entreprise.getCodeFournisseurs());

        entreprise.setCreationDate(Instant.now());
        entreprise.setLastModifiedDate(Instant.now());
        entreprise.setClients(client);
        entreprise.setFournisseurs(fournisseurs);

        entrepriseRespository.save(entreprise);

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/entreprise/get/"+entreprise.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "entreprise", entrepriseMapper.mapToEntrepriseResponse(entreprise)
                ),
                "new entreprise created successfully!"
        );
    }

    @Override
    public Response addClient(Map<String, String> data) {
        Optional<Entreprise> entrepriseOptional= entrepriseRespository.findByCode(data.get("codeEntreprise"));
        if(entrepriseOptional.isEmpty()){
            log.error("Sorry the entreprise doesn't exist on the database!!! Try again!!!!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "Sorry the entreprise doesn't exist on the database!!! Try again!!!!"
            );
        }

        Entreprise entreprise= entrepriseOptional.get();
        List<String> codeClients= entreprise.getCodeClients();

        if (codeClients.contains(data.get("codeClient"))){
            log.error("sorry the client already exist for this entreprise! Try again!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry the client already exist for this entreprise! Try again!"
            );
        }
        codeClients.add(data.get("codeClient"));

        entreprise.setCodeClients(codeClients);
        entreprise.setLastModifiedDate(Instant.now());

        entrepriseRespository.save(entreprise);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "entreprise", entrepriseMapper.mapToEntrepriseResponse(entreprise)
                ),
                "new client added to the entreprise with the code: "+data.get("codeEntreprise")
        );
    }

    @Override
    public Response addFournisseur(Map<String, String> data) {
        Optional<Entreprise> entrepriseOptional= entrepriseRespository.findByCode(data.get("codeEntreprise"));
        if(entrepriseOptional.isEmpty()){
            log.error("Sorry the entreprise doesn't exist on the database!!! Try again!!!!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "Sorry the entreprise doesn't exist on the database!!! Try again!!!!"
            );
        }

        Entreprise entreprise= entrepriseOptional.get();
        List<String> codeFournisseurs= entreprise.getCodeFournisseurs();

        if(codeFournisseurs.contains(data.get("codeFournisseur"))){
            log.error("sorry the fournisseur already exist for this entreprise! Try again!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry the fournisseur already exist for this entreprise! Try again!"
            );
        }
        codeFournisseurs.add(data.get("codeFournisseur"));
        entreprise.setCodeFournisseurs(codeFournisseurs);
        entreprise.setLastModifiedDate(Instant.now());

        entrepriseRespository.save(entreprise);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "entreprise", entrepriseMapper.mapToEntrepriseResponse(entreprise)
                ),
                "new fournisseur added to the entreprise with the code: "+data.get("codeEntreprise")
        );
    }

    @Override
    public Response get(String code) {
        Optional<Entreprise> entrepriseOptional= entrepriseRespository.findByCode(code);
        if(entrepriseOptional.isEmpty()){
            log.error("Sorry the entreprise doesn't exist on the database!!! Try again!!!!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "Sorry the entreprise doesn't exist on the database!!! Try again!!!!"
            );
        }
        Entreprise entreprise= entrepriseOptional.get();

        List<Client> clients= webClient.getClients(entreprise.getCodeClients());
        List<Fournisseur> fournisseurs= webClient.getFournisseurs(entreprise.getCodeFournisseurs());

        entreprise.setClients(clients);
        entreprise.setFournisseurs(fournisseurs);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "entreprise", entrepriseMapper.mapToEntrepriseResponse(entreprise)
                ),
                "entreprise getting succesfully!!!"
        );
    }

    @Override
    public Response all() {

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "entreprises", entrepriseRespository.findAll().stream()
                                .peek(entreprise -> {
                                    List<Client> clients= webClient.getClients(entreprise.getCodeClients());
                                    List<Fournisseur> fournisseurs= webClient.getFournisseurs(entreprise.getCodeFournisseurs());

                                    entreprise.setClients(clients);
                                    entreprise.setFournisseurs(fournisseurs);
                                })
                                .map(entrepriseMapper::mapToEntrepriseResponse)
                                .toList()
                ),
                "all entreprise getted successfully!"
        );
    }

    @Override
    public Response delete(String code) {
        if(entrepriseRespository.findByCode(code).isEmpty()){
            log.error("Sorry the entreprise doesn't exist on the database!!! Try again!!!!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "Sorry the entreprise doesn't exist on the database!!! Try again!!!!"
            );
        }
        entrepriseRespository.deleteByCode(code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "entreprise deleted successfully!"
        );
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message) {
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
