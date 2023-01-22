package ibf.sdf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class App {
    public static void main(String[] args) throws IOException {

        int port = Integer.parseInt(args[0]);
        try (ServerSocket server = new ServerSocket(port)) {
            ExecutorService executorService = Executors.newFixedThreadPool(1);

            while (true) {
                Socket socket = server.accept();
                System.out.printf("Connection received from port %d.\n", port);
                ClientHandler cH = new ClientHandler(socket);
                executorService.submit(cH);
            }
        }

    }
}
