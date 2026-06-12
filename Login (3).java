
package app;


/**
 * Login class implements user registration and authentication system.
 * Contains all validation methods required by the assignment specification.
 */
public class Login {
    
    // Instance fields for user data storage
    private String firstNameValue;
    private String lastNameValue;
    private String usernameValue;
    private String passwordValue;
    private String phoneValue;

    /**
     * Constructor initializes Login object with user provided data.
     * 
     * @param firstNameValue User's first name
     * @param lastNameValue User's last name
     * @param usernameValue Desired username
     * @param passwordValue Desired password
     * @param phoneValue Cell phone number with international code
     */
    public Login(String firstNameValue, String lastNameValue, String usernameValue, String passwordValue, String phoneValue) {
        this.firstNameValue = firstNameValue;
        this.lastNameValue = lastNameValue;
        this.usernameValue = usernameValue;
        this.passwordValue = passwordValue;
        this.phoneValue = phoneValue;
    }

    /**
     * Validates username according to assignment rules.
     * Rule 1: Must contain underscore character (_)
     * Rule 2: Maximum length of 5 characters
     * 
     * @return true if both conditions satisfied, false otherwise
     */
    public boolean checkUserName() {
        boolean underscorePresent = usernameValue.contains("_");
        boolean lengthAppropriate = usernameValue.length() <= 5;
        return underscorePresent && lengthAppropriate;
    }

    /**
     * Validates password against complexity requirements.
     * Requirement 1: At least 8 characters total
     * Requirement 2: Contains at least one uppercase letter
     * Requirement 3: Contains at least one numeric digit
     * Requirement 4: Contains at least one special character
     * 
     * @return true if all requirements met, false otherwise
     */
    public boolean checkPasswordComplexity() {
        boolean upperExists = false;
        boolean numberExists = false;
        boolean specialExists = false;

        if (passwordValue.length() < 8) {
            return false;
        }

        for (int idx = 0; idx < passwordValue.length(); idx++) {
            char ch = passwordValue.charAt(idx);

            if (Character.isUpperCase(ch)) {
                upperExists = true;
            }
            if (Character.isDigit(ch)) {
                numberExists = true;
            }
            if (!Character.isLetterOrDigit(ch)) {
                specialExists = true;
            }
        }

        return upperExists && numberExists && specialExists;
    }

    /**
     * Validates South African cell phone number format.
     * Required format: +27 followed by exactly 9 digits
     * Example: +27831234567
     * 
     * @return true if format matches, false otherwise
     */
    public boolean checkCellPhoneNumber() {
        // Pattern matching using regular expression
        boolean matchesPattern = phoneValue.matches("^\\+27[0-9]{9}$");
        return matchesPattern;
    }

    /**
     * Performs registration validation in sequential order.
     * Checks username, then password, then phone number.
     * Returns first error encountered or success message.
     * 
     * @return Appropriate message based on validation results
     */
    public String registerUser() {
        // Username validation phase
        boolean usernameValidFlag = this.checkUserName();
        if (usernameValidFlag == false) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        // Password validation phase
        boolean passwordValidFlag = this.checkPasswordComplexity();
        if (passwordValidFlag == false) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        // Phone number validation phase
        boolean phoneValidFlag = this.checkCellPhoneNumber();
        if (phoneValidFlag == false) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // All validations successful
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    /**
     * Authenticates user by comparing login credentials with stored credentials.
     * 
     * @param enteredUsername Username from login form
     * @param enteredPassword Password from login form
     * @return true if credentials match stored values
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        boolean usernameMatches = usernameValue.equals(enteredUsername);
        boolean passwordMatches = passwordValue.equals(enteredPassword);
        return usernameMatches && passwordMatches;
    }

    /**
     * Generates login status message based on authentication result.
     * 
     * @param enteredUsername Username from login form
     * @param enteredPassword Password from login form
     * @return Welcome message for successful login, error message for failed login
     */
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        boolean authenticationResult = this.loginUser(enteredUsername, enteredPassword);
        
        if (authenticationResult == true) {
            String welcomeMsg = "Welcome " + firstNameValue + ", " + lastNameValue + " it is great to see you again.";
            return welcomeMsg;
        }
        
        return "Username or password incorrect, please try again.";
    }
}
