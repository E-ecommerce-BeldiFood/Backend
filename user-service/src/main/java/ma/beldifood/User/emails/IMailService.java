package ma.beldifood.User.emails;


import jakarta.mail.MessagingException;
import ma.beldifood.User.entities.User;

public interface IMailService {
    void sendConfirmationEmail(User user, String senderEmail) throws MessagingException;
    void sendResetPasswordMail(String to, User user) throws MessagingException;
}
