package labs.sem1.lab6.task2;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import java.io.File;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// Пример вызова:
// java EncodingConverter in.txt out.txt UTF-8 windows-1251

public class EncodingConverter {
  public static void main(String[] args) {
    // check if args length is 4
    if (args.length != 4) {
      System.out.println("Invalid number of arguments");
      return;
    }

    // check if args[0] and args[1] are valid file paths
    if (!args[0].matches(".*\\.txt") || !args[1].matches(".*\\.txt")) {
      System.out.println("Invalid file path");
      return;
    }

    // get all possible encodings
    List<String> encodings = Arrays.asList(Charset.availableCharsets().keySet().toArray(new String[0]));

    // check if args[2] and args[3] are in encodings
    if (!encodings.contains(args[2]) || !encodings.contains(args[3])) {
      System.out.println("Invalid encoding");
      return;
    }

    try {
      convert(new File(args[0]), new File(args[1]), args[2], args[3]);
    } catch (IOException e) {
      System.out.println("Error while converting file");
    }
  }

  public static void convert(File source, File target, String fromEncoding, String toEncoding) throws IOException {
    try (
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), fromEncoding));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), toEncoding));) {
      char[] buffer = new char[1024];
      int read;
      while ((read = br.read(buffer)) != -1) {
        bw.write(buffer, 0, read);
      }
    }
  }
}
