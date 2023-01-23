package ibf.sdf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

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
        }
        dos.flush();
        return board;
    }

    public void startGame(String[] tttBoard, DataInputStream dis, DataOutputStream dos) throws IOException {
        String player = "X", computer = "O", winner = "";

        while (winner == "") {
            Integer input;
            input = Integer.parseInt(dis.readUTF());
            if ((input > 0) && (input < 10)) {
                if ((tttBoard[input - 1].equals(String.valueOf(input)))) {
                    tttBoard[input - 1] = player;
                    // LOOPING THROUGH BOARD FOR COMPUTER TO TAKE IT'S TURN
                    for (int i = 0; i < 9; i++) {
                        if (!tttBoard[i].equals(player)) {
                            if (tttBoard[i].equals(computer)) {
                                continue;
                            }
                            tttBoard[i] = computer;
                            break;
                        }
                    }

                }

            }
            winner = checkWinner(tttBoard);
            if (!winner.equals("")) {
                if (!winner.equalsIgnoreCase("DRAW") && !winner.equalsIgnoreCase("O")) {
                    printBoard(tttBoard, dos);
                    dos.writeUTF("\nYou've won! Goodbye!");
                } else if (!winner.equalsIgnoreCase("DRAW")
                        && !winner.equalsIgnoreCase("X")) {
                    printBoard(tttBoard, dos);
                    dos.writeUTF("Computer won! Goodbye!");

                } else {
                    printBoard(tttBoard, dos);
                    dos.writeUTF("It's a draw! Goodbye!");
                }
                dos.flush();
            }
            printBoard(tttBoard, dos);
            dos.writeUTF("----------");
            dos.flush();
        }
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
