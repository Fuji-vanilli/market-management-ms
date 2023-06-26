package mg.marketmanagement.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.userservice.Utils.Response;
import mg.marketmanagement.userservice.dto.UserMapper;
import mg.marketmanagement.userservice.dto.UserRequest;
import mg.marketmanagement.userservice.exception.ErrorCode;
import mg.marketmanagement.userservice.exception.UserNotValidException;
import mg.marketmanagement.userservice.model.User;
import mg.marketmanagement.userservice.repository.UserRepository;
import mg.marketmanagement.userservice.validator.UserValidator;
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
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailUtils emailUtils;
    @Override
    public Response add(UserRequest request) {
        List<String> errors= UserValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("request not valid");
            return generateResponse(
                    HttpStatus.NOT_FOUND,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "request not valid!!!!"
            );
        }

        if(userRepository.existsByEmail(request.getEmail())){
            log.error("user already exist on the database!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "User with the email: "+request.getEmail()+" already exist on the database! please try again"
            );
        }

        User user= userMapper.mapToUser(request);
        userRepository.save(user);

        emailUtils.sendMail(user.getEmail(), "BIENVENUE DANS META-MARKET", "You are welcom to our plateform");

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{email}")
                .buildAndExpand("api/user/get/"+user.getEmail())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "user", user
                ),
                "user added successfuly!!!"
        );
    }

    @Override
    public Response get(String email) {
        Optional<User> user= userRepository.findByEmail(email);
        if(user.isEmpty()){
            log.error("user doesn't exist on the database!");
            return generateResponse(
                    HttpStatus.NOT_FOUND,
                    null,
                    null,
                    "user with the email: "+email+" doesn't exist on the database!"
            );
        }

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "user", userMapper.mapToUserResponse(user.get())
                ),
                "user with the email: "+email+" getting successfully!"
        );
    }

    @Override
    public Response all() {
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "users", userRepository.findAll().stream()
                                .map(userMapper::mapToUserResponse)
                                .toList()
                ),
                "all user getted successfully!"
        );
    }

    @Override
    public Response delete(String id) {
        if(userRepository.findById(id).isEmpty()){
            log.error("user doesn't exist on the database!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "user with the id: "+id+"not present on the database!"
            );
        }
        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("all")
                .buildAndExpand("api/user/")
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                null,
                "user with the id: "+id+" deleted successfully!"
        );
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message){
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .location(location)
                .data(data)
                .message(message)
                .build();
    }
}
