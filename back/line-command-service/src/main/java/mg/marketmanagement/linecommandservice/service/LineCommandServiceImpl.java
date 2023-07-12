package mg.marketmanagement.linecommandservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.linecommandservice.dto.Article;
import mg.marketmanagement.linecommandservice.dto.LineCommandMapper;
import mg.marketmanagement.linecommandservice.dto.LineCommandRequest;
import mg.marketmanagement.linecommandservice.model.LineCommand;
import mg.marketmanagement.linecommandservice.repository.LineCommandRepository;
import mg.marketmanagement.linecommandservice.utils.Response;
import mg.marketmanagement.linecommandservice.validator.LineCommandValidator;
import mg.marketmanagement.linecommandservice.webClient.WebClientGetter;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LineCommandServiceImpl implements LineCommandService{

    private final LineCommandRepository lineCommandRepository;
    private final LineCommandMapper lineCommandMapper;
    private final WebClientGetter webClient;
    @Override
    public Response add(LineCommandRequest request) throws JSONException {
        List<String> errors= LineCommandValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("more field not valid");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "more filed not valid...Please try again!!!"
            );
        }

        if(lineCommandRepository.existsByCode(request.getCode())){
            log.error("line command already exist on the database");
            return generateResponse(
                    HttpStatus.CONFLICT,
                    null,
                    null,
                    "line command with the code: "+request.getCode()+" already exist on the database"
            );
        }
        Article article= webClient.getArticle(request.getCodeArticle());;

        LineCommand lineCommand= lineCommandMapper.mapToLineCommand(request);
        lineCommand.setArticle(article);
        lineCommand.setPrice(article.getUnitPriceTTC().multiply(request.getQuantity()));

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/line-command/"+request.getCode())
                .toUri();

        lineCommandRepository.save(lineCommand);
        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "lineCommand", lineCommand
                ),
                "new line command added successfully"
        );
    }

    @Override
    public Response get(String code) {


        return null;
    }

    @Override
    public Response getByList(List<String> codes) {
        return null;
    }

    @Override
    public Response all() {
        return null;
    }

    @Override
    public Response delete(String code) {
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
