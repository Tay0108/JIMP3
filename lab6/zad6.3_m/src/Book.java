public class Book {

    private Long isbn;
    private String title;
    private String author;
    private Integer year;

    public Book() {
    }

    public Book(Long isbn, String title, String author, Integer year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    void printBook() {
        System.out.println("Author: " + author);
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("ISBN: " + isbn);
    }
}
