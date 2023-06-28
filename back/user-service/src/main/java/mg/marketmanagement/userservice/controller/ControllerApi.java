package mg.marketmanagement.userservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.userservice.Utils.Response;
import mg.marketmanagement.userservice.dto.UserRequest;
import mg.marketmanagement.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static mg.marketmanagement.userservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControllerApi implements UserController{
    private final UserService userService;

    @Override
    public ResponseEntity<Response> add(UserRequest request) {
        return ResponseEntity.ok(userService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String email) {
        return ResponseEntity.ok(userService.get(email));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(userService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
