package ma.beldifood.User.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.beldifood.User.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
@Slf4j
@RequiredArgsConstructor
public class MailService implements IMailService {


    private final JavaMailSender javaMailSender;

    @Value("${site.address}")
    private String site_address;

    @Override
    public void sendConfirmationEmail(User user, String senderEmail) throws MessagingException {
        //MIME - HTML message
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(senderEmail);
        helper.setTo(user.getEmail());
        helper.setSubject("Confirm Your Email - BaldiFood");
        helper.setText("<html>" +
                        "<body>" +
                        "<h2>Dear "+ user.getFirstName()+ ",</h2>"
                        + "<br/> We're excited to have you get started. " +
                        "Please click the link below to confirm your account."
                        + "<br/> <a href="+site_address+"/auth/confirm-account?token="+user.getConfirmationToken()+">Confirm Email</a>" +
                        "<br/> Regards,<br/>" +
                        "BeldiFood Team" +
                        "</body>" +
                        "</html>"
                , true);
        javaMailSender.send(message);
        log.info("Confirmation email sent to {}", user.getEmail());
    }



    @Override
    public void sendResetPasswordMail(String to, User user) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("noura.boudra1@gmail.com");
        helper.setTo(to);
        helper.setSubject("Reset Your Password - BeldiFood");
        helper.setText("<html>" +
                        "<body>" +
                        "<h2>Dear "+ user.getFirstName() + ",</h2>"
                        + "<br/> We have sent you this email in response to your password reset request. " +
                        " <br/> To reset your password, Here is the code :  "
                        + user.getResetPasswordToken()+" to Reset your Password</a>" +
                        "<br/> Regards,<br/>" +
                        "BeldiFood Team" +
                        "</body>" +
                        "</html>"
                , true);
        javaMailSender.send(message);
        log.info("Reset password email sent to {}", to);
    }




}

