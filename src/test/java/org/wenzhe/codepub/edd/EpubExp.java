package org.wenzhe.codepub.edd;

import java.io.FileOutputStream;
import java.io.IOException;

import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Identifier;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubWriter;

/**
 * @author liuwenzhe2008@gmail.com
 *
 */
public class EpubExp {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    // Create new Book
    Book book = new Book();

    book.getMetadata().addTitle("Epublib test book 1");
    book.getMetadata().addTitle("test2");

    book.getMetadata().addIdentifier(new Identifier(Identifier.Scheme.ISBN, "987654321"));
    book.getMetadata().addAuthor(new Author("Joe", "Tester"));
    book.setCoverPage(new Resource(EpubExp.class.getResourceAsStream("/book1/cover.html"), "cover.html"));
    book.setCoverImage(new Resource(EpubExp.class.getResourceAsStream("/book1/cover.png"), "cover.png"));
    book.addSection("Chapter 1", new Resource(EpubExp.class.getResourceAsStream("/book1/chapter1.html"), "chapter1.html"));
    book.addResource(new Resource(EpubExp.class.getResourceAsStream("/book1/book1.css"), "book1.css"));
    TOCReference chapter2 = book.addSection("Second chapter", new Resource(EpubExp.class.getResourceAsStream("/book1/chapter2.html"), "chapter2.html"));
    book.addResource(new Resource(EpubExp.class.getResourceAsStream("/book1/flowers_320x240.jpg"), "flowers.jpg"));
    book.addSection(chapter2, "Chapter 2 section 1", new Resource(EpubExp.class.getResourceAsStream("/book1/chapter2_1.html"), "chapter2_1.html"));
    book.addSection("Chapter 3", new Resource(EpubExp.class.getResourceAsStream("/book1/chapter3.html"), "chapter3.html"));
    
    EpubWriter epubWriter = new EpubWriter();
    epubWriter.write(book, new FileOutputStream("test1_book1.epub"));
    System.out.println("done");
  }

}
