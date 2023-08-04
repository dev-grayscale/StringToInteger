import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

  @Test
  public void testV1() {
    Assertions.assertEquals(0, Main.myAtoiV1("-test"));
    Assertions.assertEquals(0, Main.myAtoiV1("+test"));
    Assertions.assertEquals(0, Main.myAtoiV1("-0"));
    Assertions.assertEquals(0, Main.myAtoiV1("+0"));
    Assertions.assertEquals(0, Main.myAtoiV1(" "));
    Assertions.assertEquals(0, Main.myAtoiV1("0 hello there"));
    Assertions.assertEquals(0, Main.myAtoiV1("h123ello there"));
    Assertions.assertEquals(0, Main.myAtoiV1("h-123ello there"));
    Assertions.assertEquals(0, Main.myAtoiV1("0"));
    Assertions.assertEquals(0, Main.myAtoiV1("____4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV1("test"));
    Assertions.assertEquals(0, Main.myAtoiV1("   aaa dummy 25"));
    Assertions.assertEquals(0, Main.myAtoiV1("____4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV1("~4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV1("---4193 123 with words"));
    Assertions.assertEquals(123, Main.myAtoiV1("123hello there"));
    Assertions.assertEquals(-123, Main.myAtoiV1("-123hello there"));
    Assertions.assertEquals(42, Main.myAtoiV1("42"));
    Assertions.assertEquals(-42, Main.myAtoiV1("   -42"));
    Assertions.assertEquals(4193, Main.myAtoiV1("4193 with words"));
    Assertions.assertEquals(4193, Main.myAtoiV1("4193 123 with words"));
    Assertions.assertEquals(32, Main.myAtoiV1("0032"));
    Assertions.assertEquals(Integer.MAX_VALUE, Main.myAtoiV1("9999999999"));
    Assertions.assertEquals(Integer.MIN_VALUE, Main.myAtoiV1("-9999999999"));
    Assertions.assertEquals(Integer.MAX_VALUE, Main.myAtoiV1("2147483648"));
    Assertions.assertEquals(2147483646, Main.myAtoiV1("2147483646"));
  }

  @Test
  public void testV2() {
    Assertions.assertEquals(0, Main.myAtoiV2("-test"));
    Assertions.assertEquals(0, Main.myAtoiV2("+test"));
    Assertions.assertEquals(0, Main.myAtoiV2("-0"));
    Assertions.assertEquals(0, Main.myAtoiV2("+0"));
    Assertions.assertEquals(0, Main.myAtoiV2(" "));
    Assertions.assertEquals(0, Main.myAtoiV2("0 hello there"));
    Assertions.assertEquals(0, Main.myAtoiV2("h123ello there"));
    Assertions.assertEquals(0, Main.myAtoiV2("h-123ello there"));
    Assertions.assertEquals(0, Main.myAtoiV2("0"));
    Assertions.assertEquals(0, Main.myAtoiV2("____4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV2("test"));
    Assertions.assertEquals(0, Main.myAtoiV2("   aaa dummy 25"));
    Assertions.assertEquals(0, Main.myAtoiV2("____4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV2("~4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV2("---4193 123 with words"));
    Assertions.assertEquals(123, Main.myAtoiV2("123hello there"));
    Assertions.assertEquals(-123, Main.myAtoiV2("-123hello there"));
    Assertions.assertEquals(42, Main.myAtoiV2("42"));
    Assertions.assertEquals(-42, Main.myAtoiV2("   -42"));
    Assertions.assertEquals(4193, Main.myAtoiV2("4193 with words"));
    Assertions.assertEquals(4193, Main.myAtoiV2("4193 123 with words"));
    Assertions.assertEquals(32, Main.myAtoiV2("0032"));
    Assertions.assertEquals(Integer.MAX_VALUE, Main.myAtoiV2("9999999999"));
    Assertions.assertEquals(Integer.MIN_VALUE, Main.myAtoiV2("-9999999999"));
    Assertions.assertEquals(Integer.MAX_VALUE, Main.myAtoiV2("2147483648"));
    Assertions.assertEquals(2147483646, Main.myAtoiV2("2147483646"));
  }

  @Test
  public void testV3() {
    Assertions.assertEquals(0, Main.myAtoiV3("-test"));
    Assertions.assertEquals(0, Main.myAtoiV3("+test"));
    Assertions.assertEquals(0, Main.myAtoiV3("-0"));
    Assertions.assertEquals(0, Main.myAtoiV3("+0"));
    Assertions.assertEquals(0, Main.myAtoiV3(" "));
    Assertions.assertEquals(0, Main.myAtoiV3("0 hello there"));
    Assertions.assertEquals(0, Main.myAtoiV3("h123ello there"));
    Assertions.assertEquals(0, Main.myAtoiV3("h-123ello there"));
    Assertions.assertEquals(0, Main.myAtoiV3("0"));
    Assertions.assertEquals(0, Main.myAtoiV3("____4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV3("test"));
    Assertions.assertEquals(0, Main.myAtoiV3("   aaa dummy 25"));
    Assertions.assertEquals(0, Main.myAtoiV3("____4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV3("~4193 123 with words"));
    Assertions.assertEquals(0, Main.myAtoiV3("---4193 123 with words"));
    Assertions.assertEquals(123, Main.myAtoiV3("123hello there"));
    Assertions.assertEquals(-123, Main.myAtoiV3("-123hello there"));
    Assertions.assertEquals(42, Main.myAtoiV3("42"));
    Assertions.assertEquals(-42, Main.myAtoiV3("   -42"));
    Assertions.assertEquals(4193, Main.myAtoiV3("4193 with words"));
    Assertions.assertEquals(4193, Main.myAtoiV3("4193 123 with words"));
    Assertions.assertEquals(32, Main.myAtoiV3("0032"));
    Assertions.assertEquals(Integer.MAX_VALUE, Main.myAtoiV3("9999999999"));
    Assertions.assertEquals(Integer.MIN_VALUE, Main.myAtoiV3("-9999999999"));
    Assertions.assertEquals(Integer.MAX_VALUE, Main.myAtoiV3("2147483648"));
    Assertions.assertEquals(2147483646, Main.myAtoiV3("2147483646"));
  }
}
