package ma.beldifood.User.service;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import ma.beldifood.User.dto.ResetPasswordDTO;
import ma.beldifood.User.dto.UserRequestDto;
import ma.beldifood.User.dto.UserResponseDto;
import ma.beldifood.User.entities.User;
import ma.beldifood.User.exception.EmailAlreadyExistsException;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface UserService {
    public List<UserResponseDto> getAllUsers();
    public UserResponseDto createUser(UserRequestDto userDto) throws EmailAlreadyExistsException, MessagingException;
    public UserResponseDto getUserById(Long id) throws EntityNotFoundException;
    public UserResponseDto getUserByUserName(String userName) throws EntityNotFoundException;
    UserResponseDto getUserByEmail(String email) throws EntityNotFoundException;
    public void deleteUserById(Long id) throws EntityNotFoundException;
    public UserResponseDto updateUser( UserRequestDto userDto) throws EntityNotFoundException;

    public User getUserByLogin(String login) throws EntityNotFoundException;

    boolean confirmEmail(String confirmationToken);
    void sendResetPasswordMail(@NotNull String email) throws MessagingException;
    void resetPassword(ResetPasswordDTO dto);
}

