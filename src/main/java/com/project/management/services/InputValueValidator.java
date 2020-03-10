package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.DeveloperGender;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValueValidator {
    public static String value = "";

    public static String validateString(View view) {
        value = view.read();
        while (value.trim().isEmpty()) {
            view.write("Please enter the correct title");
            value = view.read();
        }
        return value;
    }

    public static String validateGender(View view) {
        value = view.read();
        boolean trueGender = false;
        while (!trueGender) {
            try {
                DeveloperGender.valueOf(value);
                trueGender = true;
            } catch (IllegalArgumentException e) {

                view.write("Please enter the correct value");
                value = view.read();
            }
        }
        return value;
    }

    public static int validateInt(View view) {
        value = view.read();
        while (!value.matches("[0-9]*")) {
            System.out.println("Please enter correct value (number)");
            value = view.read();
        }
        return Integer.valueOf(value);
    }

    public static LocalDate validateDate(View view) {
        LocalDate localDate = null;
        //boolean trueDate = false;
        while (localDate==null) {
            try {
                localDate = LocalDate.parse(view.read());
              //  trueDate = true;
               // break;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter correct value (date)");
                localDate = LocalDate.parse(view.read());
            }
        }
        return localDate;
    }
}
