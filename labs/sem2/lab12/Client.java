package labs.sem2.lab12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java Client <host> <port>");
            System.out.println("or: java Client <port> (when running locally)");
            return;
        }

        if (args.length == 1) {
            args = new String[] { "localhost", args[0] };
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        Socket socket = new Socket(host, port);
        System.out.println("Connected to server on port " + port);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Enter your name:");
        String name = console.readLine();
        socketOut.println(name);

        Thread receiver = new ReceiveThread(socketIn);
        receiver.start();

        Thread sender = new SendThread(socketOut, console);
        sender.start();
    }

    private static class ReceiveThread extends Thread {
        private BufferedReader socketIn;

        public ReceiveThread(BufferedReader socketIn) {
            this.socketIn = socketIn;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = socketIn.readLine();
                    if (message == null) {
                        System.out.println("Server closed connection");
                        socketIn.close();
                        System.exit(0);
                    } else {
                        System.out.println(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class SendThread extends Thread {
        private PrintWriter socketOut;
        private BufferedReader console;

        public SendThread(PrintWriter socketOut, BufferedReader console) {
            this.socketOut = socketOut;
            this.console = console;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = console.readLine();
                    socketOut.println(message);
                    if (message.equals("@exit")) {
                        socketOut.close();
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
