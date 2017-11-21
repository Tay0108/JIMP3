import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Book> getBooks() {

        List<Book> books = new ArrayList<Book>();

        try {
            //connect();
            stmt = conn.createStatement();
            String sqlQuery = "SELECT * FROM books";

            ResultSet rs = stmt.executeQuery(sqlQuery); // execute query - not modyfing data in DB

            while (rs.next()) { // while next row exists
                books.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally { // closing db connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("rs.close()");
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("stmt.close()");
                }
                stmt = null;
            }
            /*if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("conn.close()");
                }
            }*/
        }
        return books;
    }

    public Book getBookByIsbn(Long isbn) { // non-modyfing, unique
        //connect();
        Book book = new Book();
        book.setIsbn(isbn);
        try {
            stmt = conn.createStatement();
            String sqlQuery = "SELECT * FROM books WHERE isbn ='" + isbn + "'";

            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next()) {
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setYear(rs.getInt(4));
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return book;
    }

    public List<Book> getBooksByAuthor(String author) { // non-modyfing, can have multiple books
        //connect();
        List<Book> books = new ArrayList<Book>();

        try {
            stmt = conn.createStatement();
            String sqlQuery = "SELECT * FROM books WHERE author ='" + author + "'";

            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next()) {
                books.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return books;
    }

    public void deleteBook(Long isbn) {
        //connect();
        String sqlQuery = "DELETE FROM books WHERE isbn ='" + isbn + "'";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {

            if (rs != null) { // TODO: zapytac, czy tu i wyzej mozna zamykanie zalatwic jednym try'em
                try {
                    rs.close();
                } catch (SQLException e) {
                } // ignoring
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            /*if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }*/
        }
    }

    public void deleteBook(String author) {
        //connect();
        String sqlQuery = "DELETE FROM books WHERE author ='" + author + "'";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            /*if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }*/
        }
    }

    public static void main(String[] args) {
        DB db = new DB();

        int connections = 0;

        while (connections < 3) {
            try {
                db.connect();

                connections = 3;
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
                System.out.println("Couldn't connect to database.");
                connections++;
            } finally {
                if (db.conn != null) {
                    try {
                        db.conn.close();
                    } catch (SQLException e) {
                        System.out.println("conn.close()");
                    }
                }
            }
        }
    }
}
