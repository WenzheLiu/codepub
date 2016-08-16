package org.wenzhe.codepub;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

import org.wenzhe.codepub.util.FileUtil;

/**
 * @author liuwenzhe2008@gmail.com
 *
 */
@Slf4j
public class Main {

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    val rootDir = Paths.get("C:\\Users\\weliu\\code\\codepub");
    val tmpRootDir = Files.createTempDirectory("epub");
    
    Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (FileUtil.isBinaryFile(file.toFile())) {
          log.debug("Skip binary file {}", file);
          return FileVisitResult.CONTINUE;
        }
        val relative = file.relativize(rootDir).toString();
        val htmlPath = tmpRootDir.resolve(relative + ".html");
        try (val writter = Files.newBufferedWriter(htmlPath)) { 
          Files.lines(htmlPath)
          .map(line -> line.replace("<", "&lt;").replace(">", "&gt;"))
          .forEach(line -> {
            try {
              writter.write(line);
              writter.write('\n');
            } catch (IOException e) {
              log.warn("Cannot write to {} for line: {}", htmlPath, line);
            }
          });  
        }
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        log.warn("Fail to visit file {}, caused: {}", file, exc.getMessage());
        return FileVisitResult.CONTINUE;
      }
    });
  }

}
