package com.company.dymrin23.jar;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;


public class AverageValueFromFilesCalculator {
    private String line;
    private final ArrayList<Double> listOfDoubles = new ArrayList<>();

    public void showTheAverage(String string) {
        File folder = new File(string);
        System.out.println("Please write the Letter Code of value");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().toUpperCase(Locale.ROOT);
        boolean index = false;
        try {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                scanner = new Scanner(Paths.get(file.getPath()), StandardCharsets.UTF_8.name());
                while (scanner.hasNext()) {
                    line = scanner.nextLine();
                    if (line.contains(str)) {
                        index = true;
                        fromStringToDouble();
                    }
                }
            }
            if (index == true) {
                calculating(listOfDoubles, str);
            } else {
                System.out.println("There is no your Letter Code ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void fromStringToDouble() {
        StringBuilder numbers = new StringBuilder();
        for (int i = line.length() / 2; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                numbers.append(line.charAt(i));
            }
        }
        numbers.deleteCharAt(0);
        listOfDoubles.add(Double.parseDouble(String.valueOf(numbers.insert(numbers.length() - 4, '.'))));
    }

    private void calculating(ArrayList<Double> arrayList, String str) {
        double sumOfValues = 0;
        for (double value : arrayList) {
            sumOfValues += value;
        }
        System.out.println("Средняя стоимость UAH к " + str.toUpperCase(Locale.ROOT) + " -> " + sumOfValues / arrayList.size());
    }

}

