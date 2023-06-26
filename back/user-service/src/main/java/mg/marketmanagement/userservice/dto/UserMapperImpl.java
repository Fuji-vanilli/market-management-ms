package mg.marketmanagement.userservice.dto;

import mg.marketmanagement.userservice.model.User;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperImpl implements UserMapper{
    @Override
    public User mapToUser(UserRequest request) {
        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .contact(request.getContact())
                .photo(request.getPhoto())
                .role(request.getRole())
                .password(request.getPassword())
                .build();
    }

    @Override
    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .contact(user.getContact())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .role(user.getRole())
                .build();
    }
}
