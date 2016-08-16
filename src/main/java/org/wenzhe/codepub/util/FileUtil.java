package org.wenzhe.codepub.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * @author wen-zhe.liu@asml.com
 *
 */
public class FileUtil {

  /**
   * Guess whether given file is binary. Just checks for anything under 0x09.<br/>
   * It can be used in any OS platform, but only support text with latin-based languages. <br/>
   * <br/>
   * 
   * @param f
   *          file, should be existed normal file (not folder, link) and should not be null
   * @return true for binary file, and false for text file (latin-based languages).
   * @throws IOException
   */
  public static boolean isBinaryFile(@Nonnull File f) throws IOException {
    FileInputStream in = new FileInputStream(f);
    int size = in.available();
    if(size > 1024) {
      size = 1024;
    }
    byte[] data = new byte[size];
    in.read(data);
    in.close();

    int ascii = 0;
    int other = 0;

    for(int i = 0; i < data.length; i++) {
      byte b = data[i];
      if( b < 0x09 ) {
        return true;
      }

      if( b == 0x09 || b == 0x0A || b == 0x0C || b == 0x0D ) {
        ascii++;
      } else if( b >= 0x20  &&  b <= 0x7E ) {
        ascii++;
      } else {
        other++;
      }
    }

    if( other == 0 ) {
      return false;
    }
    return 100 * other / (ascii + other) > 95;
  }

}
