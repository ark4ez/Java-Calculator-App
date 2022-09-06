package com.col.ark4ez.app;

public class App {
  public static void main(String[] args) {

    for (String target : args) {
      try {
        System.out.println("---------------------------------------");
        System.out.println(String.format("%s", target) + " = " + Calculator.Calc(target));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
}

