package mg.marketmanagement.userservice.dto;

import mg.marketmanagement.userservice.model.User;

public interface UserMapper {
    User mapToUser(UserRequest request);
    UserResponse mapToUserResponse(User user);
}
