package com.col.ark4ez.app;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CalculatorTest {

  @Test
  public void TestCalculator() {
    String[] targets = {
        "10",
        "-10×-4",
        "(-10)",
        "1+2+3",
        "2-3",
        "3×3+4÷2+3",
        "((-3)+4)×3",
        "(3+4×3×3)",
        "1÷0",
        "(-4)÷2",
        "4×3×3",
        "(4÷2)÷2×3",
        "(15-(4+3))×(-10)×(10+4)",
        "(-4)×(-10)"
    };

    for (String target : targets) {
      try {
        System.out.println("---------------------------------------");
        System.out.println(String.format("%s", target) + " = " + Calculator.Calc(target));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
