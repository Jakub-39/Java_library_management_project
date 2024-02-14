import library.Book;
import library.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import people.Librarian;
import people.NormalUser;
import people.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test_class {

    @Test
    public void bookAdditionTest() throws Exception {
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        Library library = new Library();
        library.addBook(book);
        Assertions.assertNotNull(library.getBook(0));
    }
    @Test
    public void bookExceptionTest(){
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        Library library = new Library();
        library.addBook(book);
        assertThrows(Exception.class,()->library.getBook(1));
    }
    @Test
    public void userAdditionTest() throws Exception {
        User user = new User("Tom","Stevens",0);
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Library library = new Library();
        library.addUser(user1);
        Assertions.assertNotNull(library.getUser(0));
    }
    @Test
    public void userExceptionTest(){
        User user = new User("Tom","Stevens",0);
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Library library = new Library();
        library.addUser(user1);
        assertThrows(Exception.class,()->library.getUser(1));
    }
    @Test
    public void librarianAdditionTest() throws Exception {
        User user = new User("Tom","Stevens",0);
        Librarian librarian = new Librarian(user,true);
        Library library = new Library();
        library.addLibrarian(librarian);
        Assertions.assertNotNull(library.getLibrarian(0));
    }
    @Test
    public void librarianExceptionTest(){
        User user = new User("Tom","Stevens",0);
        Librarian librarian = new Librarian(user,true);
        Library library = new Library();
        library.addLibrarian(librarian);
        assertThrows(Exception.class,()->library.getLibrarian(1));
    }
    @Test
    public void moreBookTest() throws Exception {
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        Library library = new Library();
        library.addBook(book);
        library.getMoreBook(0,6);
        Assertions.assertEquals(11,library.getBook(0).getAmmount());
    }
    @Test
    public void moreBookExceptionTest(){
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        Library library = new Library();
        library.addBook(book);
        assertThrows(Exception.class,()->library.getMoreBook(1,7));
    }
    @Test
    public void shareBookTest() throws Exception {
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",8);
        ArrayList<Book> books = new ArrayList<>();
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,true);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        library.shareBooks(3,book,0);
        Assertions.assertEquals(3,user1.getBook(0).getAmmount());
        Assertions.assertEquals(5,library.getBook(0).getAmmount());
    }
    @Test
    public void shareWrongAmmountTest(){
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",8);
        ArrayList<Book> books = new ArrayList<>();
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,true);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        assertThrows(Exception.class,()->library.shareBooks(9,book,0));
    }
    @Test
    public void shareBookLibrarianExceptionTest(){
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",8);
        ArrayList<Book> books = new ArrayList<>();
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,false);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        assertThrows(Exception.class,()->library.shareBooks(3,book,0));
    }
    @Test
    public void shareWrongBookTest(){
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",8);
        Book book2 = new Book(1,"Obiektowe pisanie w C#",8);
        ArrayList<Book> books = new ArrayList<>();
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,true);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        assertThrows(Exception.class,()->library.shareBooks(3,book2,0));
    }
    @Test
    public void shareToWrongUserTest(){
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",8);
        ArrayList<Book> books = new ArrayList<>();
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,true);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        assertThrows(Exception.class,()->library.shareBooks(3,book,1));
    }
    @Test
    public void takeBookTest() throws Exception {
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        Book book2 = new Book(0,"Obiektowe pisanie w Java",5);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book2);
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,true);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        library.takeBooks(3,book,0);
        Assertions.assertEquals(8,library.getBook(0).getAmmount());
        Assertions.assertEquals(2,user1.getBook(0).getAmmount());
    }
    @Test
    public void takeNewBookTest() throws Exception {
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,true);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.takeBooks(3,book,0);
        Assertions.assertEquals(3,library.getBook(0).getAmmount());
        Assertions.assertEquals(2,user1.getBook(0).getAmmount());
    }
    @Test
    public void takeBookLibrarianExceptionTest() throws Exception {
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,false);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        Assertions.assertEquals(5,library.getBook(0).getAmmount());
        assertThrows(Exception.class,()-> library.takeBooks(3,book,0));
    }
    @Test
    public void takeBookWrongUserTest(){
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,true);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        assertThrows(Exception.class,()->library.takeBooks(3,book,1));
    }
    @Test
    public void takeBookWrongBookTest(){
        User user = new User("Tom","Stevens",0);
        User user2 = new User("Jon","Alberts",1);
        Book book = new Book(0,"Obiektowe pisanie w Java",5);
        Book book2 = new Book(1,"Obiektowe pisanie w C#",8);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        NormalUser user1 = new NormalUser(user,books,"18.06.2000");
        Librarian librarian = new Librarian(user2,true);
        Library library = new Library();
        library.addUser(user1);
        library.addLibrarian(librarian);
        library.addBook(book);
        assertThrows(Exception.class,()->library.takeBooks(3,book2,0));
    }
}
