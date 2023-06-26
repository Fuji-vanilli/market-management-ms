package mg.marketmanagement.userservice.service;

import mg.marketmanagement.userservice.Utils.Response;
import mg.marketmanagement.userservice.dto.UserRequest;

public interface UserService {
    Response add(UserRequest request);
    Response get(String email);
    Response all();
    Response delete(String id);
}
