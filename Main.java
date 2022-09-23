// Assumptions:
//1. Lower case characters are not included in the reference table and will be mapped back to the same character.
//2. The encoder will use a random character from the reference table as an offset character.

//To input hardcoded offset character instead of random character from reference table, delete line 13 - 15, and replace all "randomChar" with hardcoded offset character.

import java.util.Random; // import random class for random number generator

class Main {
  private static String refTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

  public static String encode(String plainText){
  // get random offset character and offset index from reference table
    Random random = new Random();
    int randomCharIndex = random.nextInt(plainText.length());
    char randomChar = plainText.charAt(randomCharIndex);
    

  // using index of random character, find the offset value 
  // to use pre-assigned character, replace all references below of "randomChar" to pre-assigned character
    int offset = refTable.indexOf(randomChar);


  // declare new string builder to store encoded text
    StringBuilder newEncodedTextBuilder = new StringBuilder();

  // add random character to list
    newEncodedTextBuilder.append(randomChar);
    
  // get indices of each character in plainText
    for (int i = 0; i < plainText.length(); i++){
      
      
      char c = plainText.charAt(i);
      // if refTable does not contain character, add unchanged character to list of characters
      if (refTable.indexOf(c) == -1){
        newEncodedTextBuilder.append(c);
      } else {
        // if refTable contains character, find its index in refTable and apply offset value, then add new character to list of characters
        int cIndex = refTable.indexOf(c);
      int newCIndex = cIndex - offset;

      // if newCIndex is negative, bring number to back of refTable
      if (newCIndex < 0) {
        newCIndex = newCIndex + 44;
      }
      char newC = refTable.charAt(newCIndex);
        newEncodedTextBuilder.append(newC);
      }
    }

    String newEncodedText = newEncodedTextBuilder.toString();



    return (newEncodedText);
  }

  public static String decode(String encodedText) {
    // get the offset character
    char offsetChar = encodedText.charAt(0);

    // get the offset value from reference table
    int offset = refTable.indexOf(offsetChar);

    // get the encodedText without the offset character
    String encodedNoOffset = encodedText.substring(1);

    // create string builder to store new string
    StringBuilder plainTextReturn = new StringBuilder();
    for (int i = 0; i < encodedNoOffset.length(); i++) {

      // get each character in encodedNoOffset
      char c = encodedNoOffset.charAt(i);

      // if character is not in reference table, add immediately to string builder
      if (refTable.indexOf(c) == -1) {
        plainTextReturn.append(c);
      } else {
        // get index of each character in encodedNoOffset
      int cIndex = refTable.indexOf(c);

      // add offset
      int newCIndex = cIndex + offset;

      // if number exceeds 43, bring index to front of refTable
      if (newCIndex > 43) {
        newCIndex = newCIndex - 44;
      }

      char newC = refTable.charAt(newCIndex);

      plainTextReturn.append(newC);
      }

      
      
    }

    //convert string builder into string
    String newPlainText = plainTextReturn.toString();

    // start for loop
    return(newPlainText);
  }

  public static void main(String[] args) {
    // encoder test
    System.out.println("Testing encoder with string - `ALL IN FAVOUR NUMBER 5 lowercase`: ");
    String test1 = encode("ALL IN FAVOUR NUMBER 5 lowercase");
    System.out.println(test1);

    // decoder test on encoded string
    System.out.println("Testing decoder on above encoded string:");
    System.out.println(decode(test1));

    // decoder test on given string in question
    System.out.println("Testing decoder on encoded string - `BGDKKN VNQKC` --> which is `HELLO WORLD` encoded with offset character `B`:");
    System.out.println(decode("BGDKKN VNQKC"));

    // decoder test on second given string in question
    System.out.println("Testing decoder on encoded string - `FC/GGJ RJMG.` --> which is `HELLO WORLD` encoded with offset character `F`");
    System.out.println(decode("FC/GGJ RJMG."));

    // decoder test on A (without an offset)
    System.out.println("Testing decoder on encoded string with offset character `A` (offset index = 0):");
    System.out.println(decode("AIT IS A WONDERFUL WORLD"));
    
  }
  
}