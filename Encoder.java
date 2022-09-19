class Main {
  public static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

  public static void main(String[] args) {
    // Test cases
    System.out.println(encode("BHELLO WORLD"));
    System.out.println(encode("FHELLO WORLD"));
    System.out.println(encode("/HELLO WORLD"));
    
    System.out.println("---------");

    System.out.println(decode("BGDKKN VNQKC"));
    System.out.println(decode("FC/GGJ RJMG."));
    System.out.println(decode("/IFMMP XPSME"));
  }

  public static String encode(String plainText) {
    // Get offset char and offset amount
    char offsetChar = plainText.charAt(0);
    int offset = characters.indexOf(offsetChar);

    StringBuilder encodedText = new StringBuilder();
    encodedText.append(offsetChar);

    for (int i = 1; i < plainText.length(); i++) {
      // Check that plainText char is in table, else retain original char
      char plainTextChar = plainText.charAt(i);
      if (characters.indexOf(plainTextChar) == -1) {
        encodedText.append(plainTextChar);
        continue;
      }

      // Shift index down by offset amount
      int index = characters.indexOf(plainTextChar) - offset;
      // Wrap index back to 43 if index is negative
      if (index < 0) {
        index = 44 + index;
      }

      encodedText.append(characters.charAt(index));
    }

    return encodedText.toString();
  }

  public static String decode(String encodedText) {
    // Get offset char and offset amount
    char offsetChar = encodedText.charAt(0);
    int offset = characters.indexOf(offsetChar);

    StringBuilder decodedText = new StringBuilder();
    decodedText.append(offsetChar);

    for (int i = 1; i < encodedText.length(); i++) {
      // Check that encodedText char is in table, else retain original char
      char encodedTextChar = encodedText.charAt(i);
      if (characters.indexOf(encodedTextChar) == -1) {
        decodedText.append(encodedTextChar);
        continue;
      }

      // Shift index up by offset amount (reverse of encoding)
      int index = characters.indexOf(encodedTextChar) + offset;
      // Wrap index back to 0 if index is greater than 43
      if (index > 43) {
        index = index - 44;
      }

      decodedText.append(characters.charAt(index));
    }

    return decodedText.toString();
  }
}