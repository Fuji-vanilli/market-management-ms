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
import java.util.Optional;

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
                .buildAndExpand("api/line-command/get/"+request.getCode())
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
    public Response get(String code) throws JSONException {
        Optional<LineCommand> lineCommand= lineCommandRepository.findByCode(code);
        if(lineCommand.isEmpty()){
            log.error("the line command with the code :"+code+"doesn't exist on the database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the line command with the code :\"+code+\"doesn't exist on the database"
            );
        }

        Article article= webClient.getArticle(lineCommand.get().getCodeArticle());
        lineCommand.get().setArticle(article);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "line-command", lineCommandMapper.mapToLineCommandResponse(lineCommand.get())
                ),
                "line command getting successfully"
        );
    }

    @Override
    public List<LineCommand> getByList(List<String> codes) {

        List<LineCommand> lineCommands= lineCommandRepository.findByCodeIn(codes);
        if(lineCommands.isEmpty()){
            log.error("the line command with the codes enter doesn't exist on the database!");
            throw new RuntimeException("the line command with the codes enter doesn't exist on the database!");
        }

        return lineCommands.stream()
                .peek(lineCommand -> {
                    try {
                        Article article= webClient.getArticle(lineCommand.getCodeArticle());
                        lineCommand.setArticle(article);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }})
                .toList();
    }

    @Override
    public Response all() {

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "allLineCommand", lineCommandRepository.findAll().stream()
                                .peek(lineCommand -> {
                                    Article article;
                                    try {
                                        article= webClient.getArticle(lineCommand.getCodeArticle());
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                    lineCommand.setArticle(article);
                                })
                ),
                "all line command getting successfully!"
        );
    }

    @Override
    public Response delete(String code) {
        if(lineCommandRepository.findByCode(code).isEmpty()){
            log.error("Sorry the line command with the code {} doesn't exist on the database ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "Sorry the line command with the code"+code+" doesn't exist on the database "
            );
        }
        lineCommandRepository.deleteByCode(code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "Line command with the code: "+code+" deleted successfully!"
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
