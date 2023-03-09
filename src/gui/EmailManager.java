package gui;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailManager {

    public static void sendEmail(String recipient, String subject, String message) throws MessagingException {
        final String username = "ounihamdi4@gmail.com"; // remplacer par votre adresse email Gmail
        final String password = "wrbmcentlffabrxg"; // remplacer par votre mot de passe Gmail

        // Configurer les propriétés de l'email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Créer une session pour l'authentification
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Créer le message
            MimeMessage mailMessage = new MimeMessage(session);
            mailMessage.setFrom(new InternetAddress(username));
            mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            mailMessage.setSubject(subject);
            mailMessage.setText(message);

            // Envoyer l'email
            Transport.send(mailMessage);

            System.out.println("Email envoyé avec succès à " + recipient);

        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
            throw e;
        }
    }
}
