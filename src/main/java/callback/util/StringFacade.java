package callback.util;

import org.apache.commons.text.RandomStringGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

public class StringFacade {

  public static String randomNumeric(final int length) {
    return randomAlphabetic(length, '0', '9');
  }

  public static String randomAlphabetic(final int length) {
    return randomAlphabetic(length, 'a', 'z');
  }

  public static String randomAlphabetic(final int length, final char low, final char high) {
    return new RandomStringGenerator.Builder().withinRange(low, high).build().generate(length);
  }

  public static String randomAlphanumeric(final int length) {
    return randomAlphanumeric(length, '0', 'z');
  }

  public static String randomAlphanumeric(final int length, final char low, final char high) {
    return new RandomStringGenerator.Builder()
        .withinRange(low, high)
        .filteredBy(LETTERS, DIGITS)
        .build()
        .generate(length);
  }

  public static List<String> getListOfRandomStringsOfSize(int sizeOfList, int maxSizeOfWord) {
    if (sizeOfList != -1) {
      List<String> words = new ArrayList<>();
      for (int i = 0; i < sizeOfList; i++) {
        words.add(randomAlphabetic(maxSizeOfWord));
      }
      return words;
    } else {
      return null;
    }
  }

  public static String randomAlphanumericIncludingSpecialCharacters(final int length) {
    return new RandomStringGenerator.Builder().withinRange('0', 'z').build().generate(length);
  }

  public static List<String> getListOfRandomStringsIncludingSpecialCharOfSize(
      int sizeOfList, int maxSizeOfWord) {
    if (sizeOfList != -1) {
      List<String> words = new ArrayList<>();
      for (int i = 0; i < sizeOfList; i++) {
        words.add(randomAlphanumericIncludingSpecialCharacters(maxSizeOfWord));
      }
      return words;
    } else {
      return null;
    }
  }

  public static String getWithoutLineSeparator(String input) {
    return input.replace(System.lineSeparator(), "");
  }
}
