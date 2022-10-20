package library.entities;

public class Press {

    String title;
    String author;
    String dateOfCreate;
    String type;
    String genre;
    Integer length;
    String absolutePath;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public String getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getLength() {
        return Integer.parseInt(String.valueOf(length));
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public String toString() {
        return author + '_' + title;
    }

    public Press(String title, String author, String dateOfCreate, String type, String genre, Integer length, String absolutePath) {
        this.title = title;
        this.author = author;
        this.dateOfCreate = dateOfCreate;
        this.type = type;
        this.genre = genre;
        this.length = length;
        this.absolutePath = absolutePath;
    }
}