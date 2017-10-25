import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<String> recipients = new LinkedList<String>();
        LinkedList<String> recipientsCC = new LinkedList<String>();
        LinkedList<String> recipientsBCC = new LinkedList<String>();

        recipients.add("junnex.tay@gmail.com");
        recipients.add("kjkosecki@gmail.com");

        recipientsCC.add("maddie34@o2.pl");

        recipientsBCC.add("monikabaranns@gmail.com");
        recipientsBCC.add("kosecki@student.agh.edu.pl");

        String from = "tayplayer@gmail.com";
        String password = "haslo";
        String mimeType = "text/html; charset=utf-8";
        String subject = "Temat niespamowy";
        String content = "Wiadomosc niespamowa: <a href=\"http://google.pl\">google.pl</a>";

        EmailMessage emailMessage = new EmailMessage.EmailMessageBuilder(from, recipients).addSubject(subject).addContent(content).addMimeType(mimeType).addCC(recipientsCC).addBCC(recipientsBCC).build();
        // every method in line above returns this (EmailMessageBuilder), so we can call it in a row.
        emailMessage.send(from, password);
    }
}

