package Bookstore;

import Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookrepo;

    @RequestMapping("/books")
    public List<Book> Home()
    {
        List<Book> books = new ArrayList<Book>();
        books = bookrepo.findAll();
        return books;
    }

//    @RequestMapping("search/{title}")
//    @ResponseBody
//    public  Book getTitle (@PathVariable("title") String title){
//        Book book = bookrepo.findByTitle(title);
//        if (book == null){
//            return null;
//        }
//        return book;
//    }

    @RequestMapping("/books/{id}")
    public ResponseEntity<Optional<Book>> getBook(@PathVariable("id") Long id)
    {
        Optional <Book> book = bookrepo.findById(id);
        if (book == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Book>>(book, HttpStatus.OK);
    }
}
