package com.col.ark4ez.app;

import java.util.*;

class Calculator {

  // 計算式文字列から結果文字列を返す
  public static String Calc(String target) throws Exception {

    StringBuilder str = new StringBuilder(target);

    if (target.contains("(") || target.contains(")")) {
      // かっこ用
      for (int i = 0; i < str.length(); i++) {
        String s = str.substring(i, i + 1);
        if (s.equals(")")) {
          for (int j = 0; j <= i; j++) {
            if (str.substring(i - j, i - j + 1).equals("(")) {
              str.replace(i - j, i - j + j + 1, Calc((str.substring(i - j + 1, i - j + j))));
              j=0;
              break;
            }
          }
          i=0;
        }
      }
      str = new StringBuilder(additionAndSubtraction(multiplicationAndDivision(str.toString())));
      // System.out.println("output0:" + str.toString());
      return str.toString();
    } else {

      // 計算
      str = new StringBuilder(additionAndSubtraction(multiplicationAndDivision(str.toString())));
      // System.out.println("output1:" + str.toString());
      return str.toString();

    }

  }

  public static String additionAndSubtraction(String target) {

    StringBuilder sb = new StringBuilder();

    // System.out.println(" +, - target is : " + target);

    if (target.contains("+") || target.contains("-")) {

      List<String> list = makeList(target);

      // System.out.println(list);

      for (int i = 0; i < list.size(); i++) {
        String string = list.get(i);
        if (string.equals("+")) {
          // System.out.println(list.get(i - 1) + " + " + list.get(i + 1));

          List<String> rList = new ArrayList<>();

          rList.addAll(list.subList(0, i - 1));
          rList.add(
              String.valueOf(Math.addExact(Integer.parseInt(list.get(i - 1)), Integer.parseInt(list.get(i + 1)))));
          rList.addAll(list.subList(i + 2, list.size()));

          StringBuilder rsb = new StringBuilder();

          for (String rs : rList) {
            rsb.append(rs);
          }

          sb.append(additionAndSubtraction(rsb.toString()));

          break;

        } else if (string.equals("-")) {

          // System.out.println(list.get(i - 1) + " - " + list.get(i + 1));

          List<String> rList = new ArrayList<>();

          rList.addAll(list.subList(0, i - 1));
          rList.add(
              String.valueOf(Math.addExact(Integer.parseInt(list.get(i - 1)), -Integer.parseInt(list.get(i + 1)))));
          rList.addAll(list.subList(i + 2, list.size()));

          StringBuilder rsb = new StringBuilder();

          for (String rs : rList) {
            rsb.append(rs);
          }

          sb.append(additionAndSubtraction(rsb.toString()));

          break;

        } else {
          continue;
        }
      }
      if(sb.length()==0){
        sb.append(target);
      }
      return sb.toString();

    } else {
      return target;
    }
  }

  public static String multiplicationAndDivision(String target) {

    StringBuilder sb = new StringBuilder();

    // System.out.println(" *, / target is : " + target);

    if (target.contains("×") || target.contains("÷")) {

      List<String> list = makeList(target);

      // System.out.println(list);

      for (int i = 0; i < list.size(); i++) {
        String string = list.get(i);
        if (string.equals("×")) {
          // System.out.println(list.get(i - 1) + " * " + list.get(i + 1));

          List<String> rList = new ArrayList<>();

          rList.addAll(list.subList(0, i - 1));
          rList.add(
              String.valueOf(Math.multiplyExact(Integer.parseInt(list.get(i - 1)), Integer.parseInt(list.get(i + 1)))));
          rList.addAll(list.subList(i + 2, list.size()));

          StringBuilder rsb = new StringBuilder();

          for (String rs : rList) {
            rsb.append(rs);
          }

          sb.append(multiplicationAndDivision(rsb.toString()));

          break;

        } else if (string.equals("÷")) {
          // System.out.println(list.get(i - 1) + " / " + list.get(i + 1));

          List<String> rList = new ArrayList<>();

          rList.addAll(list.subList(0, i - 1));
          rList.add(
              String.valueOf(Integer.parseInt(list.get(i - 1)) / Integer.parseInt(list.get(i + 1))));
          rList.addAll(list.subList(i + 2, list.size()));

          StringBuilder rsb = new StringBuilder();

          for (String rs : rList) {
            rsb.append(rs);
          }

          sb.append(multiplicationAndDivision(rsb.toString()));

          break;

        } else {
          continue;
        }
      }

      return sb.toString();

    } else {
      return target;
    }
  }

  // 数字、演算子を識別し配列の文字列ごとに分ける
  public static List<String> makeList(String target) {

    char[] chars = target.toCharArray();

    List<String> list = new ArrayList<>();
    StringBuilder operand = new StringBuilder();

    // make list
    for (int i = 0; i < chars.length; i++) {

      switch (chars[i]) {
        case '+':
          if (operand.length() > 0) {
            list.add(operand.toString());
            operand.delete(0, operand.length());
          }
          list.add("+");
          break;
        case '-':
          if (operand.length() > 0) {
            list.add(operand.toString());
            operand.delete(0, operand.length());
          }else{
            operand.append(chars[i]);
            break;
          }
          list.add("-");
          break;
        case '×':
          if (operand.length() > 0) {
            list.add(operand.toString());
            operand.delete(0, operand.length());

          }
          list.add("×");
          break;
        case '÷':
          if (operand.length() > 0) {
            list.add(operand.toString());
            operand.delete(0, operand.length());
          }
          list.add("÷");
          break;
        default:
          operand.append(chars[i]);
          break;
      }

      if (i == chars.length - 1) {
        if (operand.length() != 0) {
          list.add(operand.toString());
        }
      }
    }

    return list;
  }

}
