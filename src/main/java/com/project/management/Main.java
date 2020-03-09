package com.project.management;

import com.project.management.console.Console;
import com.project.management.console.Controller;
import com.project.management.console.View;

public class Main {
    public static void main(String[] args) {

        View view = new Console();
        Controller controller = new Controller(view);
        controller.readOption();
    }
}

