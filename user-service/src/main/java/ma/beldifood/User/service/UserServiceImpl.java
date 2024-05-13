package ma.beldifood.User.service;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.beldifood.User.dto.ResetPasswordDTO;
import ma.beldifood.User.dto.UserRequestDto;
import ma.beldifood.User.dto.UserResponseDto;
import ma.beldifood.User.emails.IMailService;
import ma.beldifood.User.entities.Status;
import ma.beldifood.User.entities.User;
import ma.beldifood.User.exception.EmailAlreadyExistsException;
import ma.beldifood.User.exception.EmptyEntityException;
import ma.beldifood.User.mappers.Mapping;
import ma.beldifood.User.repository.UserRepository;
import ma.beldifood.User.utils.UserInputValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service

@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private  final UserRepository userRepository;
    private final  UserInputValidation userInputValidation;
    private final  IMailService iMailService;
    private final  PasswordEncoder passwordEncoder;
    private  final int  TOKEN_EXPIRATION_TIME=24;

    @Value("${spring.mail.email}")
    private String senderMail;



    @Override
    public List<UserResponseDto> getAllUsers() throws EntityNotFoundException{
        log.info("Fetching all users");
        if(userRepository.findAll().isEmpty()){
            throw new EntityNotFoundException("No user found!");
        }else {
            return userRepository.findAll()
                    .stream()
                    .map(Mapping::mapToUserResponseDto)
                    .toList();
        }
    }

    @Override
    public UserResponseDto getUserById(Long id) throws EntityNotFoundException, EmptyEntityException {
        log.info("Fetching user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return Mapping.mapToUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userDto) throws EmailAlreadyExistsException, MessagingException {
        log.info("Creating new user with email : {}", userDto.getEmail());
        if (!UserInputValidation.isValidUsername(userDto.getUserName())) {
            throw new IllegalArgumentException("UserName is required");
        }
        if (!UserInputValidation.isValidFirstName(userDto.getFirstName())) {
            throw new IllegalArgumentException("FirstName is required");
        }
        if (!UserInputValidation.isValidLastName(userDto.getLastName())) {
            throw new IllegalArgumentException("LastName is required");
        }
        if (!UserInputValidation.isValidEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
        }
        User user = Mapping.mapToUserEntity(userDto);
        user.setActive(false);
        user.setStatus(Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String code = UUID.randomUUID().toString();
        user.setConfirmationToken(code);
        iMailService.sendConfirmationEmail(user, senderMail);
        return Mapping.mapToUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserByUserName(String userName) throws EntityNotFoundException {
        log.info("Fetching user by userName: {}", userName);
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with userName: " + userName));
        return Mapping.mapToUserResponseDto(user);
    }
    @Override
    public UserResponseDto getUserByEmail(String email) throws EntityNotFoundException {
        log.info("Fetching user by email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        return Mapping.mapToUserResponseDto(user);
    }


    @Override
    public void deleteUserById(Long id) throws EntityNotFoundException, EmptyEntityException{
        log.info("delete user by id : {}", id); // Logging the deletion operation
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus(Status.DISABLED); // Deactivate the user instead of deleting
            userRepository.save(user); // Update the user entity
        } else  throw new EntityNotFoundException("User not found with id: " + id);

    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userDto) throws EntityNotFoundException {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getId()));


        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return Mapping.mapToUserResponseDto(updatedUser);

    }

    @Override
    public User getUserByLogin(String login) throws EntityNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException("User not found with Login: " + login));
    }

    //for register
    @Override
    public boolean confirmEmail(String confirmationToken) {
        User user = userRepository.findByConfirmationToken(confirmationToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid token!"));
        user.setConfirmedAt(new Date());
        user.setActive(true);
        userRepository.save(user);
        return true;
    }
    //mail for resetting password

    @Override
    public void sendResetPasswordMail( String  email) throws MessagingException {
        User user = userRepository.findByEmail(email.toLowerCase()).orElseThrow(()->new EntityNotFoundException("User not found with the email :"+email));
        if(!user.isActive() ||  user.getConfirmedAt() == null)
            throw new IllegalArgumentException("the user is inactive or didnt confirm his mail ") ;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,TOKEN_EXPIRATION_TIME);
        Date expiryDate = calendar.getTime();
        user.setResetPasswordTokenExpiryDate(expiryDate);
        String code = UUID.randomUUID().toString();
        user.setResetPasswordToken(code);
        userRepository.save(user);
        iMailService.sendResetPasswordMail(email,user);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        log.info("Change password : {}","********");
        //bghina check wach user b had mail + check token
        User user = userRepository.findByEmailIgnoreCase(resetPasswordDTO.getEmail()).orElseThrow(()->new EntityNotFoundException("User not found with the email :"+resetPasswordDTO.getEmail()));
        if (user.getResetPasswordToken()== null
                || !user.getResetPasswordToken().equals(resetPasswordDTO.getToken())
                || user.getResetPasswordTokenExpiryDate().before(new Date()))
                throw new IllegalArgumentException("Sorry your token has expired.");

            user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            user.setResetPasswordToken(null);
            user.setResetPasswordTokenExpiryDate(null);
            userRepository.save(user);
        }


}





