import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class ProgramDB {
    public static void main(String[] args) {

        DB db = new DB();

        int connections = 0;
        int noMoreConnections = 3;

        while (connections < noMoreConnections) {
            try {
                db.connect();
                connections = noMoreConnections;

                //by isbn
                Book book = db.getBookByIsbn(1234567891236L);
                book.printBook();

                //by author
                System.out.println("\n\n__________________________________________________");
                System.out.println("Search by author: ");
                for (Book b : db.getBookByAuthor("Ernest Hemingway")){
                    b.printBook();
                }
                System.out.println("\n\n__________________________________________________");

                //deleting
                db.deleteBookByIsbn(1234567891234L);
                book = db.getBookByIsbn(1234567891234L);
                book.printBook();

            } catch (SQLException e) {
                System.out.println("Couldn't connect to database. Retrying in 10s");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println("Timer interupted");
                }
                connections++;
            }
        }
    }
}