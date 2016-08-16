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
public class EpubExp2 {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    // Create new Book
    Book book = new Book();

    book.getMetadata().addTitle("Epublib test book 2");
    book.getMetadata().addTitle("test2");

    book.getMetadata().addIdentifier(new Identifier(Identifier.Scheme.ISBN, "987654321"));
    book.getMetadata().addAuthor(new Author("Wenzhe", "Liu"));
    //book.setCoverPage(new Resource(EpubExp2.class.getResourceAsStream("/book2/cover.html"), "cover.html"));
    //book.setCoverImage(new Resource(EpubExp2.class.getResourceAsStream("/book2/cover.png"), "cover.png"));
    book.addSection("Chapter 1", new Resource(EpubExp2.class.getResourceAsStream("/book2/chapter1.html"), "chapter1.html"));
    book.addResource(new Resource(EpubExp2.class.getResourceAsStream("/book2/book1.css"), "book1.css"));
    TOCReference chapter2 = book.addSection("Second chapter", new Resource(EpubExp2.class.getResourceAsStream("/book2/chapter2.html"), "chapter2.html"));
    book.addResource(new Resource(EpubExp2.class.getResourceAsStream("/book2/flowers_320x240.jpg"), "flowers.jpg"));
    TOCReference c2s1 = book.addSection(chapter2, "Chapter 2 section 1 dsf/adsa/adfa/ad/gafafa/fa/afda/adf/da.java", new Resource(EpubExp2.class.getResourceAsStream("/book2/chapter2_1.html"), "chapter2_1.html"));
    book.addSection(c2s1, "Chapter 3", new Resource(EpubExp2.class.getResourceAsStream("/book2/chapter3.html"), "chapter3.html"));
    
    EpubWriter epubWriter = new EpubWriter();
    epubWriter.write(book, new FileOutputStream("test2_book2.epub"));
    System.out.println("done");
  }

}
