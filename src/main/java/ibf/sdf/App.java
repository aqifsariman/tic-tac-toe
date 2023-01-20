package ibf.sdf;

import java.util.Scanner;

public final class App {
    private App() {
    }

    // CREATE MULTI THREAD GAME

    public static void main(String[] args) {
        String winner = "";
        String[] tttBoard = new String[9];
        String player = "X";

        for (int i = 0; i < 9; i++) {
            tttBoard[i] = String.valueOf(i + 1);
        }

        T3 t3 = new T3();
        t3.printBoard(tttBoard);
        Scanner scanner = new Scanner(System.in);
        while (winner == "") {
            Integer input;
            System.out.println("TIC TAC TOE GAME");
            System.out.printf("%s player's turn\n", player);
            System.out.print("> ");
            input = scanner.nextInt();
            if ((input > 0) && (input < 10)) {
                if ((tttBoard[input - 1].equals(String.valueOf(input)))) {
                    tttBoard[input - 1] = player;

                    if (player.equalsIgnoreCase("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }
                } else {
                    System.out.println("Position taken up. Please wake up your idea.");
                }
                t3.printBoard(tttBoard);
                winner = t3.checkWinner(tttBoard);
                System.out.printf("%s player has won!", winner);
            } else {
                System.out.println("Invalid input. Key in numbers 1 - 9 only.");
            }

        }
        scanner.close();
    }
}
