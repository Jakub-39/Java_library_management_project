package people;

public class User {
    private int id;
    private String name;
    private String surname;

    public User(String name, String surname, int id){
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    public int getID(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public void setID(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
}
