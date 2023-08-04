/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * Read in and ignore any leading whitespace.
 *
 * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either.
 * This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
 *
 * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
 *
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 *
 * If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1], then clamp the integer so that it remains in the range.
 * Specifically, integers less than -2^31 should be clamped to -2^31, and integers greater than 2^31 - 1 should be clamped to 2^31 - 1.
 *
 * Return the integer as the final result.
 *
 * Note:
 *
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 *
 * E.g.
 *
 * Input: s = "   -42"
 * Output: -42
 *
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class Main {

  /**
   * For this problem, everything that we need to do is given within the description in a great detail. The solution below is a product of strictly following these
   * steps.
   *
   * Few of them requires to think more deeply:
   *
   * 1. Building the end result, excluding any unnecessary character but digits
   * 2. Make sure we handle the sign one position before the first occurrence of a digit, if any
   * 3. processing and building the result as we go, while keeping track of not overflowing.
   *
   * Our initial goal is to find the correct start index. That is when all the whitespaces are trimmed and the sign (+/-, if such exist) is taken into account
   * and won't be processed anymore during the iteration.
   *
   * Our start index is initially 0 as every element could be digit and we don't want to lose an element.
   *
   * Step 1.
   * Update the start index to the first non-whitespace position, if there are any.
   * If after the trim operation we reached the end of the string -> return 0 (in case the input was "      ")
   * Check if after the trim, the character at position <b>start</b> in <b>s</b> is +/-.
   *  - If so -> update the sign for the end result and increment start with <b>1</b>
   *  - assume it's + otherwise.
   *
   * Step 2.
   * Now that we've taken care of sanitizing our working sequence of characters, we start visiting the elements one by one
   * from the start index. If at any point a non-digit character is encountered -> we return the built number up to this point along with the correct sign.
   *
   * As long as we visit numeric characters, we approach it in a similar way as we did in: <link>Reverse a signed integer</link> with few modifications.
   * - not reversing but building as is and instead of number, the input is string.
   *
   * Step 3.
   * To make sure we won't overflow we could continuously check if there is enough space to perform the next operation, just like we did in <link>Reverse a signed integer</link>.
   * In this challenge, the difference between Integer.MAX_VALUE and <b>number * 10</b> matters, so we need to make sure the next digit we add won't exceed the limits by
   * checking if: <b>Integer.MAX_VALUE - (10 * number) >= Character.getNumericValue(s.charAt(i))</b>
   *
   * Also, since the number we're building is always positive until it's returned, we must guarantee that:
   *  - if the sign is <b>-</b>, it overflows after 2147483648
   *  - if <b>+</b> -> 2147483647
   *
   * This code handles both of the cases:
   *
   * if (number > 0 && !((Integer.MAX_VALUE / number) >= 10 && (Integer.MAX_VALUE - (10 * number) >= Character.getNumericValue(s.charAt(i))))) {
   *    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
   * }
   *
   * If the number >= 2147483647 it takes the Integer.MAX/MIN value based on the sign
   *  E.g.
   *  Sign = +, number = 2147483647 -> 2147483647 (Integer.MAX_VALUE)
   *  Sign = +, number = 2147483649 -> 2147483647 (Integer.MAX_VALUE)
   *  Sign = -, number = 2147483648 -> -2147483648 (Integer.MIN_VALUE)
   *  Sign = -, number = 2147483649 -> -2147483648 (Integer.MIN_VALUE)
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public static int myAtoiV1(String s)  {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    int start = getIndexAfterTrim(s);

    if (start >= s.length()) {
      return 0;
    }

    boolean positive = true;
    char ch = s.charAt(start);

    if (ch == '-' || ch == '+') {
      positive = ch == '+';
      start++;
    }

    int number = 0;
    for (int i = start; i < s.length(); i++) {
      if (!Character.isDigit(s.charAt(i))) {
        return positive ? number : -number;
      }

      if (number > 0 && !((Integer.MAX_VALUE / number) >= 10 && (Integer.MAX_VALUE - (10 * number) >= Character.getNumericValue(s.charAt(i))))) {
        return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }

      number = (number * 10) + Character.getNumericValue(s.charAt(i));
    }

    return positive ? number : -number;
  }

  // helper method to return the index after left trimming
  private static int getIndexAfterTrim(String s) {
    int i;

    for (i = 0; i < s.length(); i++) {
      if (s.charAt(i) != ' ') {
        break;
      }
    }

    return i;
  }

  /**
   * Slight optimization could be achieved by:
   *
   * 1. Instead of checking !Character.isDigit(..) we could check if it's NOT in the range 48-57 (the numbers 0-9 positions in the ASCII table)
   * 2. Since we know that the highest number which could fit 10 times without overflow is Integer.MAX_VALUE / 10 (THRESHOLD), we could conclude the following:
   *  - If it's less than that THRESHOLD -> there will be enough space no matter what's the next digit
   *  - If it's bigger -> overflow
   *  - If it's equal -> only digits 0-7 could fit without overflow
   *
   * So.. we are within the range if <b>number < MAX_THRESHOLD || (number == MAX_THRESHOLD && digit <= 7)</b>
   */
  public static int myAtoiV2(String s)  {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    int start = getIndexAfterTrim(s);

    if (start >= s.length()) {
      return 0;
    }

    int MAX_THRESHOLD = Integer.MAX_VALUE / 10;

    boolean positive = true;
    char ch = s.charAt(start);
    int digit;

    if (ch == '-' || ch == '+') {
      positive = ch == '+';
      start++;
    }

    int number = 0;
    for (int i = start; i < s.length(); i++) {
      ch = s.charAt(i);
      digit = Character.getNumericValue(ch);

      if (ch < 48 || ch > 57) {
        return positive ? number : -number;
      }

      if (number < MAX_THRESHOLD || (number == MAX_THRESHOLD && digit <= 7)) {
        number = (number * 10) + digit;
      } else {
        return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
    }

    return positive ? number : -number;
  }

  /**
   * In this solution, we're doing everything in one for loop, unlike what we did in V1-2.
   *
   * In summary:
   *
   * Considering that we haven't visited any digits yet, as we iterate through the input characters:
   * 1. If we encounter 1 or more whitespaces -> ignore them and proceed to next character
   * 2. If a sign is encountered -> store it for the end result and jump to next character (which MUST be digit, otherwise we return 0)
   * 3. If we didn't pass through any of the above and the character is non-digit -> return the number built up to this point along with sign, 0 if none.
   * 4. If digit is encountered, digit MUST follow. If the next character is different from 0-9 -> return number built up to this point along with sign, 0 if none.
   *
   * The rest is similar to V2.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public static int myAtoiV3(String s)  {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    int MAX_THRESHOLD = Integer.MAX_VALUE / 10;

    char ch;
    boolean digits = false;
    boolean positive = true;

    int number = 0;
    for (int i = 0; i < s.length(); i++) {
      ch = s.charAt(i);

      if (digits && (ch < 48 || ch > 57)) {
        return positive ? number : -number;
      } else if (!digits) {
        if (ch == ' ') {
          // jump through whitespaces
        } else if (ch == '+' || ch == '-') {
          positive = ch == '+';

          digits = true; // digits should follow
        } else if (ch < 48 || ch > 57) {
          // non digit character is encountered -> return number
          return positive ? number : -number;
        } else {
          // a number encountered, so number MUST follow
          digits = true;
          i--;
        }

        continue;
      }

      if (number < MAX_THRESHOLD || (number == MAX_THRESHOLD && Character.getNumericValue(ch) <= 7)) {
        number = (number * 10) + Character.getNumericValue(ch);
      } else {
        return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
    }

    return positive ? number : -number;
  }

}
