package ibf.sdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // INPUT STREAMS
            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            // OUTPUT STREAMS
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            // TIC TAC TOE GAME
            T3 t3 = new T3();
            String[] tttBoard = t3.generateBoard();
            String[] printedBoard = t3.printBoard(tttBoard, dos);
            t3.startGame(printedBoard, dis, dos);

            // CLOSING ALL STREAMS
            dis.close();
            bis.close();
            is.close();
            dos.close();
            bos.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
