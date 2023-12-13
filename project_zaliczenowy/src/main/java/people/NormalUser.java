package people;

import library.Book;

import java.util.ArrayList;
import java.util.Arrays;

public class NormalUser extends User {
    private ArrayList<Book> books = new ArrayList<>();
    private String birthDate;
    public NormalUser(User user, ArrayList<Book> books, String birthDate){
        super(user.getName(),user.getSurname(), user.getID());
        this.books = books;
        this.birthDate = birthDate;
    }
    public Book getBook(int id) throws Exception {
        for(Book book : books){
            if(book.getId()==id){
                return book;
            }
        }
        throw new Exception("No_such_book");
    }
    public void removeBook(int id) throws Exception {
        books.remove(getBook(id));
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void borrowBooks(int amount, Book book) {
        boolean isBook = false;
        for (Book userBook : books) {
            if (userBook.getId() == book.getId()) {
                isBook = true;
                userBook.addAmmount(amount);
                break;
            }
        }
        if (!isBook) {
            Book new_book = new Book(book.getId(),book.getTitle(),amount);
            books.add(new_book);

        }
        /*
        in this method we don't check if ammount of books we take in greater than avaliable ones
        because we check that in the method of Library class
         */
    }
    public void returnBooks(int ammount,Book book, boolean wasCreated) throws Exception {
        boolean isBook = false;
        for(Book bookFromCollection : books){
            if(bookFromCollection.getId() == book.getId()){
                isBook = true;
                break;
            }
        }
        if(isBook == true){
            Book newBook = new Book(getBook(book.getId()).getId(),getBook(book.getId()).getTitle(),getBook(book.getId()).getAmmount());
            newBook.reduceAmmount(ammount);
            if(wasCreated) {
                newBook.reduceAmmount(ammount);
            }
            removeBook(book.getId());
            addBook(newBook);
            /*
            we reduce the ammount two times, because of how memory allocation works in Java
            causing it to treat the book which we modified in Library to the same book
            I know I should find a better fix for that, but I am unable to
             */
        }
        else{
            throw new Exception("No_such_book");
        }
    }
}
