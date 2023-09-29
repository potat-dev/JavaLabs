package dop;

import java.io.*;
import java.net.*;

public class Client {
    private static boolean connected = false;
    private static String chatMateName = "Server";

    private static String currentDir = System.getProperty("user.dir");

    public static void main(String args[]) throws Exception {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java Client <server_address> <server_port>");
            System.out.println("or: java Client <port> (when running locally)");
            return;
        }

        if (args.length == 1) {
            args = new String[] { "localhost", args[0] };
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddressInet = InetAddress.getByName(serverAddress);

        System.out.println("Client connected to server host: " + serverAddress + ", port: " + serverPort);

        // Создаем поток для отправки сообщений серверу
        Thread sendThread = new SendThread(clientSocket, serverAddressInet, serverPort);
        sendThread.start();

        // Создаем поток для получения сообщений от сервера
        Thread receiveThread = new ReceiveThread(clientSocket, serverAddressInet, serverPort);
        receiveThread.start();
    }

    public static void sendPacket(DatagramSocket sock, InetAddress addr, int port, String msg) throws IOException {
        byte[] sendData = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, addr, port);
        sock.send(sendPacket);
    }

    private static class SendThread extends Thread {
        private DatagramSocket clientSocket;
        private InetAddress serverAddressInet;
        private int serverPort;

        public SendThread(DatagramSocket clientSocket, InetAddress serverAddressInet, int serverPort) {
            this.clientSocket = clientSocket;
            this.serverAddressInet = serverAddressInet;
            this.serverPort = serverPort;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    if (!connected) {
                        sendPacket(clientSocket, serverAddressInet, serverPort, "<HELLO>");
                        connected = true;
                    }

                    String message = reader.readLine();

                    if (message.equals("@q")) {
                        clientSocket.close();
                        System.exit(0);
                        break;
                    }

                    else if (message.startsWith("@name ")) {
                        String username = message.substring(6);
                        String answer = "<NAME>" + username;
                        sendPacket(clientSocket, serverAddressInet, serverPort, answer);
                    }

                    else if (message.equals("@pwd")) {
                        String answer = "<CMD>PWD";
                        sendPacket(clientSocket, serverAddressInet, serverPort, answer);
                    }

                    else if (message.equals("@ls")) {
                        String answer = "<CMD>LS";
                        sendPacket(clientSocket, serverAddressInet, serverPort, answer);
                    }

                    else if (message.startsWith("@cd ")) {
                        String directory = message.substring(4);
                        String answer = "<CMD>CD " + directory;
                        sendPacket(clientSocket, serverAddressInet, serverPort, answer);
                    }

                    else {
                        String answer = "<MSG>" + message;
                        sendPacket(clientSocket, serverAddressInet, serverPort, answer);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clientSocket.close();
            }
        }
    }

    private static class ReceiveThread extends Thread {
        private DatagramSocket clientSocket;
        private InetAddress serverAddressInet;
        private int serverPort;

        public ReceiveThread(DatagramSocket clientSocket, InetAddress serverAddressInet, int serverPort) {
            this.clientSocket = clientSocket;
            this.serverAddressInet = serverAddressInet;
            this.serverPort = serverPort;
        }

        @Override
        public void run() {
            byte[] receiveData = new byte[1024];

            while (true) {
                try {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                    if (receivedMessage.startsWith("<NAME>")) {
                        chatMateName = receivedMessage.substring(5);
                    }

                    else if (receivedMessage.startsWith("<CMD>")) {
                        String commandOutput = receivedMessage.substring(5);
                        System.out.println("Command output: " + commandOutput);
                    }

                    else if (receivedMessage.startsWith("<CMD>")) {
                        String command = receivedMessage.substring(5);
                        System.out.println("Command output: " + command);

                        String result = "";
                        if (command.equals("LS")) {
                            File dir = new File(currentDir);
                            File[] files = dir.listFiles();

                            StringBuilder sb = new StringBuilder();

                            if (files != null) {
                                for (File file : files) {
                                    sb.append(file.getName()).append("\n");
                                }
                            }

                            result = sb.toString();
                        }

                        else if (command.equals("PWD")) {
                            result = currentDir;
                        }

                        else if (command.startsWith("CD ")) {
                            String directory = command.substring(3);
                            currentDir = directory;
                            result = directory;
                        }

                        String message = "<MSG>" + result;
                        sendPacket(clientSocket, serverAddressInet, serverPort, message);
                    }

                    else if (receivedMessage.startsWith("<MSG>")) {
                        String userMessage = receivedMessage.substring(5);
                        System.out.println(chatMateName + ": " + userMessage);
                    }

                    else {
                        System.out.println("Unknown message: " + receivedMessage);
                    }
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
