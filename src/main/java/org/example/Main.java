package org.example;

import java.util.Scanner;

public final class Main {
  public static void main(String[] args) {
    var menu = new Menu();

    menu.PrintOptions();
    menu.ReadOption();
  }
}
