package com.tadas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static int startYear,endYear;
    private static LocalDate dateStart, dateEnd;
    private static DateTimeFormatter format;

    public static void main(String[] args) {
        userInputCheck();
    }

    public static void userInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter range of years. \nStart year: ");
         startYear = scanner.nextInt();
        System.out.println("End year: ");
         endYear = scanner.nextInt();
    }

    public static void userInputCheck(){
        userInput();
        System.out.println(startYear+" "+ endYear);
        if (startYear<0 || endYear<0) {
            System.out.println("You entered incorrect year");
        }else if(startYear > endYear){
            System.out.println("Start year cannot be greater than end year");
        } else {
            printBonusDatesBetween(startYear, endYear);
        }
    }

    public static void startParser(String start){
        yearFormat(startYear);
        start = start+"-01-01";
        dateStart = LocalDate.parse(start, format);
    }

    public static void endParser(String end){
        yearFormat(endYear);
        end = end+"-12-31";
        dateEnd = LocalDate.parse(end, format);
    }

    public static void yearFormat(int year){
        if(year<10){
            format = DateTimeFormatter.ofPattern("y-MM-dd");
        }else if(year<100){
            format = DateTimeFormatter.ofPattern("yy-MM-dd");
        }else if (year<1000){
            format = DateTimeFormatter.ofPattern("yyy-MM-dd");
        }else {
            format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
    }

    public static void printBonusDatesBetween(int fromYear, int toYear) {
        String start = String.valueOf(fromYear);
        String end = String.valueOf(toYear);
        startParser(start);
        endParser(end);
            for(LocalDate localDate = dateStart; localDate.isBefore(dateEnd); localDate = localDate.plusDays(1)){
                String reverseDate="";
                String originalDate = String.valueOf(localDate);
                for (int i= originalDate.length()-1; i>=0; i--){
                    reverseDate += originalDate.charAt(i);
                }
                StringBuilder strBuilder = new StringBuilder(reverseDate);
                strBuilder.replace(2,3,"");
                reverseDate = String.valueOf(strBuilder.insert(7,"-"));
                if(originalDate.equals(reverseDate)){
                    System.out.println("Dates are matching when reversed: " +reverseDate);
                }
            }
    }
}
