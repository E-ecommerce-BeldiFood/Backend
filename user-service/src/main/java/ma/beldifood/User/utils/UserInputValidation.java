package ma.beldifood.User.utils;

import org.springframework.stereotype.Component;

@Component
public class UserInputValidation {
    public static Boolean isValidUsername(String userName) {
        return userName != null && !userName.isEmpty();
    }

    public static Boolean isValidFirstName(String firstName) {
        return firstName != null && !firstName.isEmpty();
    }

    public static Boolean isValidLastName(String lastName) {
        return lastName != null && !lastName.isEmpty();
    }

    public static Boolean isValidEmail(String email) {
        // Regular expression pattern for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }



    //Minimum length of 8 characters.
    //Contains at least one digit.
    //Contains at least one uppercase letter.
    //Contains at least one lowercase letter.
    //Contains at least one special character from the defined set !@#$%^&*()-+.
    public static boolean isValidPassword(String password) {
        // Check for minimum length
        if (password.length() < 8) {
            return false;
        }

        // Check for at least one digit
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                break;
            }
        }
        if (!hasDigit) {
            return false;
        }

        // Check for at least one uppercase letter
        boolean hasUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
                break;
            }
        }
        if (!hasUppercase) {
            return false;
        }

        // Check for at least one lowercase letter
        boolean hasLowercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
                break;
            }
        }
        if (!hasLowercase) {
            return false;
        }

        // Check for at least one special character
        String specialChars = "!@#$%^&*()-+";
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (specialChars.contains(String.valueOf(c))) {
                hasSpecialChar = true;
                break;
            }
        }
        if (!hasSpecialChar) {
            return false;
        }

        return true;
    }


   }
