// Chapter 14 Exercise 1
/* 
  This is something of a trick question.
  You can use the static fill() method in the Arrays class to fill a char[]
  array with a given character. All you need to do to fill an array with some other
  value is to cast the value to type char and use the library method.
*/
import java.util.Arrays;
public class FillArray {
  public static void fill(char[] array, short value) {
    java.util.Arrays.fill(array, (char)value);
  }

  public static void main(String[] args) {
    char[] chars = new char[20];
    short value = 90;     // This is equivalent to 'Z'
    fill(chars, value);
    System.out.print("The array contains: ");
    for(int i = 0 ; i<chars.length ; i++)
      System.out.print(chars[i]);
  }
}