package people;

public class Librarian extends User {
    private boolean isWorking;
    public Librarian(User user,boolean isWorking){
        super(user.getName(), user.getSurname(), user.getID());
        this.isWorking = isWorking;
    }
    public void setState(boolean inWork){
        this.isWorking = inWork;
    }

    public boolean isWorking() {
        return isWorking;
    }
}
