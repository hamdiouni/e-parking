package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SendSMSController {

    @FXML
    private TextField numberField;

    @FXML
    private TextField messageField;


// Find your Account Sid and Token at twilio.com/console 
public static final String ACCOUNT_SID = "AC05821fd96a8f1f328ca260ee08c33e91"; 
public static final String AUTH_TOKEN = "ab3660e2c3f30e414c83578ec4d6b05a"; 

@FXML
private void sendSMS() {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    String phoneNumber = numberField.getText();
    String message = messageField.getText();

    Message sms = Message.creator(
        new PhoneNumber(phoneNumber),
        new PhoneNumber("+12762624071"),
        message)
        .create();

    System.out.println(sms.getSid());
}

private static String generatePassword() {
    // Generate a random 6-digit number
    Random random = new Random();
    int passwordInt = random.nextInt(900000) + 100000;
    // Convert the number to a string
    return Integer.toString(passwordInt);
}
 

    
}

