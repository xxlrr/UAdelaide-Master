public class Book{
    private String title;
    private int year;
    private String publish;
    private int pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    // Basic Constructor
    public Book() {
    }

    // Paramatic Constructor
    public Book(String title, int year, String publish, int pages) {
        this.title = title;
        this.year = year;
        this.publish = publish;
        this.pages = pages;
    }
        
}
