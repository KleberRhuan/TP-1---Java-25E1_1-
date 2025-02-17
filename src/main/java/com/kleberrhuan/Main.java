package com.kleberrhuan;
import com.kleberrhuan.view.ConsoleInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleInterface cli = new ConsoleInterface(); 
        cli.printMenu();
    }
}