package ibf.sdf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class T3 {

    public String[] generateBoard() {
        String[] board = new String[9];

        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }

        return board;
    }

    public String[] printBoard(String[] board, DataOutputStream dos) throws IOException {
        for (int i = 0; i < 9; i += 3) {
            int temp = i + 1;
            int temp2 = i + 2;
            dos.writeUTF(board[i] + " | " + board[temp] + " | " + board[temp2]);
            if (i < 6) {
                dos.writeUTF("---------");
            }
            dos.flush();
        }
        return board;
    }

    public void startGame(String[] tttBoard, DataInputStream dis, DataOutputStream dos) throws IOException {
        String player = "X";
        String winner = "";

        Scanner scanner = new Scanner(System.in);
        while (winner == "") {
            Integer input;

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

                printBoard(tttBoard, dos);
                winner = checkWinner(tttBoard);
                if (!winner.equals("")) {
                    if (!winner.equalsIgnoreCase("DRAW")) {
                        System.out.printf("Player %s has won.", winner);
                    } else
                        System.out.println("It's a draw.");
                }
            } else {
                System.out.println("Invalid input. Key in numbers 1 - 9 only.");
            }

        }
        scanner.close();
    }

    public String checkWinner(String[] board) {
        String line = "";
        String winner = "";
        int i = 0;
        while (i < 8) {
            switch (i) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;

                case 1:
                    line = board[3] + board[4] + board[5];
                    break;

                case 2:
                    line = board[6] + board[7] + board[8];
                    break;

                case 3:
                    line = board[0] + board[3] + board[6];
                    break;

                case 4:
                    line = board[1] + board[4] + board[7];
                    break;

                case 5:
                    line = board[2] + board[5] + board[8];
                    break;

                case 6:
                    line = board[0] + board[4] + board[8];
                    break;

                case 7:
                    line = board[2] + board[4] + board[6];
                    break;

            }

            if (line.equals("XXX")) {
                winner = "X";
                i = 9;
            } else if (line.equalsIgnoreCase("OOO")) {
                winner = "O";
                i = 9;
            } else {
                for (int a = 0; a < 9; a++) {
                    if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                        break;
                    } else if (a == 8) {
                        winner = "DRAW";
                    }
                }
            }

            i++;
        }
        return winner;
    }

}
