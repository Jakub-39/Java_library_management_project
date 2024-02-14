package library;

import people.Librarian;
import people.NormalUser;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<NormalUser> users = new ArrayList<>();
    private ArrayList<Librarian> librarians = new ArrayList<>();
    public Library(){};
    public Library(ArrayList<Book> books,ArrayList<NormalUser> users,ArrayList<Librarian> librarians ){
        this.books = books;
        this.users = users;
        this.librarians = librarians;
    }
    public void addBook(Book book){
        this.books.add(book);
    }
    public void addLibrarian(Librarian librarian){
        this.librarians.add(librarian);
    }
    public void addUser(NormalUser user){
        this.users.add(user);
    }
    public Book getBook(int id) throws Exception {
        for(Book book : books){
            if(book.getId()==id){
                return book;
            }
        }
        throw new Exception("No_such_book");
    }
    public NormalUser getUser(int id) throws Exception {
        for(NormalUser user : users){
            if(user.getID()==id){
                return user;
            }
        }
        throw new Exception("No_such_user");
    }
    public Librarian getLibrarian(int id) throws Exception {
        for(Librarian librarian : librarians){
            if(librarian.getID()==id){
                return librarian;
            }
        }
        throw new Exception("No_such_librarian");
    }
    public void getMoreBook(int bookId,int ammount) throws Exception {
        boolean isBook = false;
        for(Book bookFromCollection: books){
            if(bookFromCollection.getId() == bookId){
                isBook = true;
                break;
            }
        }
        if(isBook == true){
            books.forEach(book -> {
                if(book.getId()==bookId){
                    book.addAmmount(ammount);
                }
            });
        }
        else{
            throw new Exception("No_such_Book");
        }
    }
    private boolean[] checks(Book book, int userId){
        boolean isLibrarian = false;
        boolean isBook = false;
        boolean isUser = false;
        boolean[] isChecked = new boolean[3];
        for(Librarian librarian : librarians){
            if(librarian.isWorking() == true){
                isLibrarian = true;
                break;
            }
        }
        for(NormalUser user : users){
            if(user.getID()==userId){
                isUser = true;
                break;
            }
        }
        for(Book bookFromCollection: books){
            if(bookFromCollection.getId() == book.getId()){
                isBook = true;
                break;
            }
        }
        isChecked[0] = isBook;
        isChecked[1] = isUser;
        isChecked[2] = isLibrarian;
        return isChecked;

    }
    public void shareBooks(int ammount, Book book, int userId) throws Exception {
        boolean[] checkMarks = checks(book,userId);
        if(checkMarks[0] == true && checkMarks[1] == true && checkMarks[2] == true) {
            books.forEach(bookFromCollection -> {
                if (bookFromCollection.getId() == book.getId()) {
                    if (bookFromCollection.getAmmount() >= ammount) {
                        bookFromCollection.reduceAmmount(ammount);
                        users.forEach(user ->{
                            if(user.getID() == userId){
                                user.borrowBooks(ammount,book);
                            }
                        });
                    } else {
                        try {
                            throw new Exception("Not_Enough_Books");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
       else if(checkMarks[0] == false){
           throw new Exception("No_such_book");
        }
       else if(checkMarks[1] == false){
           throw new Exception("No_such_user");
        }
       else{
           throw new Exception("No_working_librarian");
        }
    }
    public void takeBooks(int ammount, Book book, int userId) throws Exception {
        boolean[] checkMarks = checks(book,userId);
        if(checkMarks[0] == true && checkMarks[1] == true && checkMarks[2] == true){
            books.forEach(bookFromCollection -> {
                if(bookFromCollection.getId() == book.getId()){
                    bookFromCollection.addAmmount(ammount);
                    users.forEach(user ->{
                        if(user.getID() == userId){
                            try {
                                Book newBook = new Book(book.getId(),book.getTitle(),book.getAmmount());
                                user.returnBooks(ammount, newBook);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            });
       }

        else if(checkMarks[0] == false && checkMarks[1] == true && checkMarks[2] == true){
            Book newBook = new Book(book.getId(),book.getTitle(),ammount);
            books.add(newBook);
            users.forEach(user ->{
                if(user.getID() == userId){
                    try {
                        Book bookForUser = new Book(book.getId(),book.getTitle(),book.getAmmount());
                        user.returnBooks(ammount, bookForUser);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else if(checkMarks[1] == false){
            throw new Exception("No_such_user");
        }
        else{
            throw new Exception("No_working_librarian");
        }


    }

}
