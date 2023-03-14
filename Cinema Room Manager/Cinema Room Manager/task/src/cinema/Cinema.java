package cinema;

import java.util.Scanner;

public class Cinema {
    private static final String SEAT = "S";
    private static final String BUSY = "B";
    private static final int SMALL_HALL = 60;
    private static final int FRONT_PRICE = 10;
    private static final int BACK_PRICE = 8;

    public static void main(String[] args) {
        int[] dimensions = readDimensions();
        int rows = dimensions[0];
        int columns = dimensions[1];
        String[][] cinema = new String[rows][columns];
        makeCinema(cinema);

        while (true) {
            boolean exit = false;
            int option = showMenu();

            switch (option) {
                case 0 -> exit = true;
                case 1 -> printCinema(cinema);
                case 2 -> selectSeat(cinema);
                case 3 -> statistics(cinema);
            }
            if (exit)
                break;
        }

    }

    private static int showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }

    public static void currentIncome(String[][] cinema) {
        int current = 0;
        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[0].length; j++) {
                if(cinema[i][j].equals(BUSY))
                    current += checkPrice(cinema, i);
            }
        }
        System.out.println("Current income: $" + current);
    }

    public static int checkPrice(String[][] cinema, int row) {
        if (cinema.length * cinema[0].length <= SMALL_HALL) {
            return FRONT_PRICE;
        } else {
            if (row <= (cinema.length - 1) / 2) return FRONT_PRICE;
            else return BACK_PRICE;
        }
    }

    private static void numberOfPurchasedTicketsAsPercentage(String[][] cinema, int purchased) {
        float totalSeats = (cinema.length - 1) * (cinema[0].length - 1);
        //Rounded to 2 2 decimal places
        float percent = purchased * 100F / totalSeats;
        String percentFormatted = String.format("%.2f", percent);
        System.out.println("Percentage: " + percentFormatted + "%");
    }

    private static int numberOfPurchasedTickets(String[][] cinema) {
        int purchased = 0;
        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[0].length; j++) {
                if (cinema[i][j].equals(BUSY)) purchased++;
            }
        }
        System.out.println("Number of purchased tickets: " + purchased);
        return purchased;
    }

    private static void statistics(String[][] cinema) {
        int purchased = numberOfPurchasedTickets(cinema);
        numberOfPurchasedTicketsAsPercentage(cinema, purchased);
        currentIncome(cinema);
        totalIncome(cinema);
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

    private static void selectSeat(String[][] cinema) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int column = scanner.nextInt();

        if (row >= cinema.length || column >= cinema[0].length) {
            System.out.println("Wrong input!");
            selectSeat(cinema);
        } else if (cinema[row][column].equals(BUSY)) {
            System.out.println("That ticket has already been purchased!");
            selectSeat(cinema);
        } else {
            cinema[row][column] = BUSY;
            calculatePrice(row, cinema.length - 1, cinema[0].length - 1);
        }

    }

    private static void makeCinema(String[][] cinema) {
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
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
        int[] tab = new int[2];
        System.out.println("Enter the number of rows:");
        tab[0] = scanner.nextInt() + 1;
        System.out.println("Enter the number of seats in each row:");
        tab[1] = scanner.nextInt() + 1;
        return tab;
    }

    private static void totalIncome(String[][] cinema) {
        int rows = cinema.length - 1;
        int columns = cinema[0].length - 1;
        System.out.print("Total income: $");
        if (rows * columns <= SMALL_HALL) {
            System.out.println(rows * columns * 10);
        } else {
            System.out.println((rows / 2) * columns * 10 + (rows - rows / 2) * columns * 8);
        }
    }

    private static void printCinema(String[][] cinema) {
        System.out.println("Cinema:");
        /*for (String[] strings : cinema) {
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }*/
        for (String[] rows : cinema) {
            for (String places : rows) {
                System.out.print(places + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}