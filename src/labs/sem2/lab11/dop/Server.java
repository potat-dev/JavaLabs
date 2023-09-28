package dop;
import java.io.*;
import java.net.*;

public class Server {
    private static InetAddress clientAddress;
    private static int clientPort;
    private static String chatMateName = "Client";

    private static String currentDir = System.getProperty("user.dir");

    public static void main(String args[]) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java Server <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        InetAddress tempAdr = InetAddress.getByName("localhost");
        DatagramSocket serverSocket = new DatagramSocket(port, tempAdr);
        byte[] receiveData = new byte[1024];

        System.out.println("Server is running on port: " + port);

        // Создаем поток для приема сообщений от клиента
        Thread receiveThread = new Thread(() -> {
            while (true) {
                try {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);

                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    if (receivedMessage.startsWith("<HELLO>")) {
                        clientPort = receivePacket.getPort();
                        clientAddress = receivePacket.getAddress();
                    }

                    else if (receivedMessage.startsWith("<NAME>")) {
                        chatMateName = receivedMessage.substring(5);
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
                        byte[] sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, clientAddress, clientPort);
                        serverSocket.send(sendPacket);
                    }

                    else if (receivedMessage.startsWith("<MSG>")) {
                        String userMessage = receivedMessage.substring(5);
                        if (chatMateName.isEmpty())
                            System.out.println("Received: " + userMessage);
                        else
                            System.out.println(chatMateName + ": " + userMessage);
                    }

                    else {
                        System.out.println("Received unsupported packet");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        receiveThread.start();

        // Создаем поток для отправки сообщений клиенту
        Thread sendThread = new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    String message = reader.readLine();

                    if (message.equals("@q")) {
                        serverSocket.close();
                        System.exit(0);
                        break;
                    }

                    else if (message.startsWith("@name ")) {
                        // Установка имени пользователя
                        String username = message.substring(6);
                        String tempMessage = "<NAME>" + username;
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, clientAddress, clientPort);
                        serverSocket.send(sendPacket);
                    }

                    else if (message.equals("@pwd")) {
                        String tempMessage = "<CMD>PWD";
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, clientAddress, clientPort);
                        serverSocket.send(sendPacket);
                    } else if (message.equals("@ls")) {
                        String tempMessage = "<CMD>LS";
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, clientAddress, clientPort);
                        serverSocket.send(sendPacket);
                    } else if (message.startsWith("@cd ")) {
                        String directory = message.substring(4);
                        String tempMessage = "<CMD>CD " + directory;
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, clientAddress, clientPort);
                        serverSocket.send(sendPacket);
                    }

                    else {
                        message = "<MSG>" + message;
                        byte[] sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, clientAddress, clientPort);
                        serverSocket.send(sendPacket);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sendThread.start();
    }
}