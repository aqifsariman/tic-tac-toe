package ibf.sdf;

public final class App {
    private App() {
    }

    // CREATE MULTI THREAD GAME

    public static void main(String[] args) {
        String[] tttBoard = new String[9];
        String player = "X";

        for (int i = 0; i < 9; i++) {
            tttBoard[i] = String.valueOf(i + 1);
        }

        T3 t3 = new T3();
        t3.printBoard(tttBoard);
    }
}
