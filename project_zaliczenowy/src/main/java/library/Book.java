package library;

public class Book{
    private int id;
    private String title;
    private int ammount;
    public Book(int id, String title, int ammount){
        this.id = id;
        this.title = title;
        this.ammount = ammount;
    }

    public int getId() {
        return id;
    }

    public int getAmmount() {
        return ammount;
    }

    public String getTitle() {
        return title;
    }
    public void addAmmount(int ammount){
        this.ammount += ammount;
    }
    public void reduceAmmount(int ammount){
        this.ammount -= ammount;
    }
}
