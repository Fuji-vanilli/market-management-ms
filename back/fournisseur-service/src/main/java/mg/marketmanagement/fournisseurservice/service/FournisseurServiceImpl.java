package mg.marketmanagement.fournisseurservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.fournisseurservice.dto.CommandFournisseur;
import mg.marketmanagement.fournisseurservice.dto.FournisseurMapper;
import mg.marketmanagement.fournisseurservice.dto.FournisseurRequest;
import mg.marketmanagement.fournisseurservice.dto.LineCommandFournisseur;
import mg.marketmanagement.fournisseurservice.model.Fournisseur;
import mg.marketmanagement.fournisseurservice.repository.FournisseurRepository;
import mg.marketmanagement.fournisseurservice.utils.Response;
import mg.marketmanagement.fournisseurservice.validator.FournisseurValidator;
import mg.marketmanagement.fournisseurservice.webClient.WebConfigGetter;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FournisseurServiceImpl implements FournisseurService{
    private final FournisseurRepository fournisseurRepository;
    private final FournisseurMapper fournisseurMapper;
    private final WebConfigGetter webClient;
    @Override
    public Response add(FournisseurRequest request) {
        List<String> errors= FournisseurValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("some field doesn't valid!!!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "some field doesn't valid!!!"
            );
        }
        if(fournisseurRepository.existsByCode(request.getCode())){
            log.error("the fournisseur is already exist on the database!");
            return generateResponse(
                    HttpStatus.CONFLICT,
                    null,
                    Map.of(
                            "fournisseur", fournisseurMapper.mapToFournisseurResponse(fournisseurRepository.findByCode(request.getCode()).get())
                    ),
                    "the fournisseur is already exist on the database!"
            );
        }

        Fournisseur fournisseur= fournisseurMapper.mapToFournisseur(request);
        List<CommandFournisseur> commandFournisseurs= new ArrayList<>();
        try {
            commandFournisseurs= webClient.getCommandFournisseurs(fournisseur.getCodeCommands());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        fournisseur.setCreationDate(Instant.now());
        fournisseur.setLastModifiedDate(Instant.now());
        fournisseur.setCommandFournisseurs(commandFournisseurs);

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/fournisseur/get/"+fournisseur.getCode())
                .toUri();

        fournisseurRepository.save(fournisseur);
        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "fournisseur", fournisseurMapper.mapToFournisseurResponse(fournisseur)
                ),
                "new fournisseur added successfully!!!"
        );
    }

    @Override
    public Response get(String code) throws JSONException {
        Optional<Fournisseur> fournisseurOptional= fournisseurRepository.findByCode(code);
        if(fournisseurOptional.isEmpty()){
            log.error("sorry, no fournisseur with the code {}", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry, no fournisseur with the code: "+code
            );
        }
        Fournisseur fournisseur= fournisseurOptional.get();
        List<CommandFournisseur> commandFournisseurs= webClient.getCommandFournisseurs(fournisseur.getCodeCommands());

        fournisseur.setCommandFournisseurs(commandFournisseurs);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "fournisseur", fournisseurMapper.mapToFournisseurResponse(fournisseur)
                ),
                "fournisseur getted successfully!"
        );
    }

    @Override
    public List<Fournisseur> getFournisseurs(List<String> codes) {
        List<Fournisseur> fournisseurs= fournisseurRepository.findByCodeIn(codes);
        if(fournisseurs.isEmpty()){
            log.error("sorry no fournisseurs getting, please try again!!!");
        }

        return fournisseurs.stream()
                .peek(fournisseur -> {
                    List<CommandFournisseur> commandFournisseurs= null;
                    try {
                      commandFournisseurs=  webClient.getCommandFournisseurs(fournisseur.getCodeCommands());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    fournisseur.setCommandFournisseurs(commandFournisseurs);
                })
                .toList();
    }

    @Override
    public Response addCommand(Map<String, String> data) throws JSONException {
        Optional<Fournisseur> fournisseurOptional= fournisseurRepository.findByCode(data.get("codeFournisseur"));
        if(fournisseurOptional.isEmpty()){
            log.error("sorry the fournisseur with the code {} doesn't exist ",data.get("codeFournisseur"));
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry the fournisseur with the code "+data.get("codeFournisseur")+"doesn't exist"
            );
        }

        Fournisseur fournisseur= fournisseurOptional.get();
        if(fournisseur.getCodeCommands().contains(data.get("codeCommand"))){
            log.error("sorry, the commend is already passed");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry the command is already passed!!!"
            );
        }
        List<String> codeCommands= fournisseur.getCodeCommands();
        codeCommands.add(data.get("codeCommand"));

        fournisseur.setCodeCommands(codeCommands);
        fournisseurRepository.save(fournisseur);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "fournisseur", fournisseurMapper.mapToFournisseurResponse(fournisseur)
                ),
                "new command with the fournisseur"+data.get("codeFournisseur")+" added succesfully"
        );
    }

    @Override
    public Response all() {

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "fournisseurs", fournisseurRepository.findAll().stream()
                                .peek(fournisseur -> {
                                    List<CommandFournisseur> commandFournisseurs;
                                    try {
                                        commandFournisseurs= webClient.getCommandFournisseurs(fournisseur.getCodeCommands());
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                    fournisseur.setCommandFournisseurs(commandFournisseurs);
                                })
                                .map(fournisseurMapper::mapToFournisseurResponse)
                                .toList()
                ),
                "all fournisseur getted successfully!"
        );
    }

    @Override
    public Response delete(String code) {
        if(!fournisseurRepository.existsByCode(code)){
            log.error("sorry, no fournisseur with the code {}", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry, no fournisseur with the code: "+code
            );
        }

        fournisseurRepository.deleteByCode(code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "fournisseur deleted successfully!!!"
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
