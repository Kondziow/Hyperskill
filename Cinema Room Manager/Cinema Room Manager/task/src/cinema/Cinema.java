package cinema;

import java.util.Scanner;

public class Cinema {
    private static final String SEAT = "S";
    private static final String BUSY = "B";
    private static final int SMALL_HALL = 60;
    private static final int FRONT_PRICE = 10;
    private static final int BACK_PRICE = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions[] = readDimensions();
        int rows = dimensions[0];
        int columns = dimensions[1];
        String cinema[][] = new String[rows][columns];
        makeCinema(rows, columns, cinema);
        printCinema(rows, columns, cinema);

        selectSeat(cinema);
        printCinema(rows, columns, cinema);


    }

    private static void calculatePrice(int row, int countOfRows, int countOfColumns) {
        System.out.println();
        if (countOfRows * countOfColumns <= SMALL_HALL) {
            System.out.println("Ticket price: $" + FRONT_PRICE);
        } else {
            if (row <= countOfRows / 2) System.out.println("Ticket price: $10");
            else System.out.println("Ticket price: $" + BACK_PRICE);
        }
        System.out.println();
    }

    private static void selectSeat(String cinema[][]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int column = scanner.nextInt();

        if (cinema[row][column] == SEAT) {
            cinema[row][column] = BUSY;
            calculatePrice(row, cinema.length-1, cinema[0].length-1);
        }

    }

    private static void makeCinema(int rows, int columns, String cinema[][]) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0) {
                    if (j == 0) cinema[i][j] = " ";
                    else cinema[i][j] = String.valueOf(j);
                } else {
                    if (j == 0) cinema[i][j] = String.valueOf(i);
                    else cinema[i][j] = SEAT;
                }
            }
        }
    }

    private static int[] readDimensions() {
        Scanner scanner = new Scanner(System.in);
        int tab[] = new int[2];
        System.out.println("Enter the number of rows:");
        tab[0] = scanner.nextInt() + 1;
        System.out.println("Enter the number of seats in each row:");
        tab[1] = scanner.nextInt() + 1;
        return tab;
    }

    private static void calculateAllIncome(int rows, int columns) {
        System.out.println("Total income:");
        if (rows * columns <= SMALL_HALL) {
            System.out.println("$" + (rows * columns * 10));
        } else {
            System.out.println("$" + ((rows / 2) * columns * 10 + (rows - rows / 2) * columns * 8));
        }
    }

    private static void printCinema(int rows, int columns, String cinema[][]) {
        System.out.println("Cinema:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}