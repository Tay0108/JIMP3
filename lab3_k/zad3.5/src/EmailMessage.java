import java.util.LinkedList;
import java.util.Properties;

import javax.mail.Message; // for composing message
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session; // represents an email session
import javax.mail.Authenticator;
import javax.mail.Transport; // for sending email
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailMessage {

    // required parameters:
    private final String from; //required (must be e-mail)
    private final LinkedList<String> to; //required at least one (must be e-mail)

    // optional parameters:
    private final String subject; //optional
    private final String content; //optional
    private final String mimeType;  // optional
    private final LinkedList<String> cc; //optional
    private final LinkedList<String> bcc; // optional

    public String getFrom() {
        return from;
    }

    public LinkedList<String> getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getMimeType() {
        return mimeType;
    }

    public LinkedList<String> getCc() {
        return cc;
    }

    public LinkedList<String> getBcc() {
        return bcc;
    }

    private EmailMessage(EmailMessageBuilder builder) { // private, wiec mozna stworzyc obiekt tylko z uzyciem buildera
        this.from = builder.from;
        this.to = builder.to;
        this.subject = builder.subject;
        this.content = builder.content;
        this.mimeType = builder.mimeType;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
    }

    void send(String username, String password) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getFrom()));

            // TO:
            for (String recipient : getTo()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }

            // CC:
            if (getCc() != null) {
                for (String recipient : getCc()) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
                }
            }

            // BCC:
            if (getBcc() != null) {
                for (String recipient : getBcc()) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(recipient));
                }
            }

            if (getSubject() != null) {
                message.setSubject(getSubject());
            } else {
                message.setSubject("");
            }
            if (getContent() != null) {
                if (getMimeType() != null) {
                    message.setContent(getContent(), getMimeType());
                }
            } else {
                message.setText("empty");
            }


            Transport.send(message); // sending message
            System.out.println("E-mail sent.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // builder
    public static class EmailMessageBuilder {

        // required parameters:
        private String from; //required (must be e-mail)
        private LinkedList<String> to; //required at least one (must be e-mail)

        // optional parameters:
        private String subject; //optional
        private String content; //optional
        private String mimeType;  // optional
        private LinkedList<String> cc; //optional
        private LinkedList<String> bcc; // optional

        public EmailMessageBuilder(String from,
                                   LinkedList<String> to) {
            this.from = from;
            this.to = to;
        }

        public EmailMessageBuilder addSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailMessageBuilder addContent(String content) {
            this.content = content;
            return this;
        }

        public EmailMessageBuilder addMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public EmailMessageBuilder addCC(LinkedList<String> cc) {
            this.cc = cc;
            return this;
        }

        public EmailMessageBuilder addBCC(LinkedList<String> bcc) {
            this.bcc = bcc;
            return this;
        }

        public EmailMessage build() {
            return new EmailMessage(this);
        }
    }
}