import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void connect() throws SQLException {
        // Class.forName("com.mysql.jdbc.Driver").newInstance(); // loading JDBC driver (unnesessary in newer versions)

        conn =
                DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl:3306/kosecki?useLegacyDatetimeCode=false&serverTimezone=Europe/Warsaw",
                        "kosecki", "Hb3TbCxcX4ooohMD");
    }

    public List<Book> getBooks() throws SQLException {

        List<Book> books = new ArrayList<Book>();

        stmt = conn.createStatement();
        String sqlQuery = "SELECT * FROM books";

        ResultSet rs = stmt.executeQuery(sqlQuery); // execute query - not modyfing data in DB

        while (rs.next()) { // while next row exists
            books.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }

        return books;
    }

    public Book getBookByIsbn(Long isbn) throws SQLException { // non-modyfing, unique
        Book book = new Book();
        book.setIsbn(isbn);

        stmt = conn.createStatement();
        String sqlQuery = "SELECT * FROM books WHERE isbn ='" + isbn + "'";

        ResultSet rs = stmt.executeQuery(sqlQuery);

        while (rs.next()) {
            book.setTitle(rs.getString(2));
            book.setAuthor(rs.getString(3));
            book.setYear(rs.getInt(4));
        }

        return book;
    }

    public List<Book> getBooksByAuthor(String author) throws SQLException { // non-modyfing, can have multiple books
        List<Book> books = new ArrayList<Book>();

        stmt = conn.createStatement();
        String sqlQuery = "SELECT * FROM books WHERE author ='" + author + "'";

        rs = stmt.executeQuery(sqlQuery);

        while (rs.next()) {
            books.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }

        return books;
    }

    public void deleteBook(Long isbn) throws SQLException {
        String sqlQuery = "DELETE FROM books WHERE isbn ='" + isbn + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sqlQuery);
    }

    public void deleteBook(String author) throws SQLException {
        String sqlQuery = "DELETE FROM books WHERE author ='" + author + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sqlQuery);

    }

    public static void main(String[] args) {
        DB db = new DB();

        int connections = 0;
        int attempts = 3;

        while (connections < attempts) {
            try {
                db.connect();
                connections = attempts;

                List<Book> books = db.getBooks();

                for (Book i : books) {
                    System.out.println("ISBN: " + i.getIsbn());
                    System.out.println("Title: " + i.getTitle());
                    System.out.println("Author: " + i.getAuthor());
                    System.out.println("Year: " + i.getYear());
                    System.out.println();
                }
                System.out.println("Book by isbn test: ");
                System.out.println(db.getBookByIsbn(1234567891234L).getTitle());
                System.out.println();

                System.out.println("Books by author test: ");

                List<Book> authorBooks = db.getBooksByAuthor("Antoni Burgess");

                for (Book i : authorBooks) {
                    System.out.println(i.getTitle());
                }

                System.out.println("Deleting: ");
                db.deleteBook("Ernest Hemingway");

                System.out.println(db.getBookByIsbn(1234567891234L).getTitle());


            } catch (SQLException e) {
                System.out.println("Couldn't connect to database. Retrying in 10s");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println("Timer interupted");
                }
                connections++;

            } finally {
                if (db.rs != null) {
                    try {
                        db.rs.close();
                    } catch (SQLException e) {
                        System.out.println("rs.close()");
                    }
                    db.rs = null;
                }
                if (db.stmt != null) {
                    try {
                        db.stmt.close();
                    } catch (SQLException e) {
                        System.out.println("stmt.close()");
                    }
                    db.stmt = null;
                }
                if (db.conn != null) {
                    try {
                        db.conn.close();
                    } catch (SQLException e) {
                        System.out.println("conn.close()");
                    }
                }
            }
            System.out.println("Connection closed.");
        }
    }
}
