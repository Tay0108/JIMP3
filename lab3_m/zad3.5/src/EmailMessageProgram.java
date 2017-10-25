import java.util.LinkedList;

public class EmailMessageProgram {
    public static void main(String[] args) {

        LinkedList<String> listTo = new LinkedList<>();
        listTo.addLast("student1@agh.edu.pl");
        listTo.addLast("student2@agh.edu.pl");

        LinkedList<String> listBcc = new LinkedList<>();
        listBcc.addLast("dziekanat1@agh.edu.pl");
        listBcc.addLast("dziekanat2@agh.edu.pl");

        EmailMessage emailMessage = new EmailMessage.Builder("sbobek@agh.edu.pl", listTo)
                .bcc(listBcc)
                .content("Help me Obi Wan Kenobi, you're my only hope.")
                .subject("[IMPORTANT] Tu jest ważna wiadomość.")
                .build();

        System.out.println("NADAWCA: " + emailMessage.getFrom());
        System.out.println("ODBIORCA: " + emailMessage.getTo());
        System.out.println("UDW: " + emailMessage.getBcc());
        System.out.println("TEMAT: " + emailMessage.getSubject());
        System.out.println("TREŚĆ: " + emailMessage.getContent());
    }
}
