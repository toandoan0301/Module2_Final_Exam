package view;

import java.util.Scanner;

public class Validate {
    static Scanner input = new Scanner(System.in);

    public static int inputChoice() {
        int choice;
        do {
            try {
                System.out.println("Nhập Lựa chọn:");
                choice = Integer.parseInt(input.nextLine());
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        } while (true);
    }

    public static int inputNumber(String str) {
        int number;
        do {
            try {
                System.out.println(str);
                number = Integer.parseInt(input.nextLine());
                return number;
            }catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số");
            }
        }while (true);
    }
    public static String inputString(String str) {
        String rerulst;
        do {
            try {
                System.out.println(str);
                rerulst = input.nextLine();
                if(!rerulst.matches("(?=.*[a-zA-Z0-9])[\\w( )]*")){
                    throw new RuntimeException("Không được để trống!");
                }
                return rerulst;
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }while (true);
    }
}
