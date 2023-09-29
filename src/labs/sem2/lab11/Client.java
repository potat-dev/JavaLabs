package labs.sem2.lab11;

import java.io.*;
import java.net.*;

// Better optimized version is in /dop

public class Client {
    private static boolean connected = false;
    private static String chatMateName = "Server";

    public static void main(String args[]) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java Client <server_address> <server_port>");
            return;
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddressInet = InetAddress.getByName(serverAddress);

        System.out.println("Client connected to server host: " + serverAddress + ", port: " + serverPort);

        // Создаем поток для отправки сообщений серверу
        Thread sendThread = new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    if (!connected) {
                        String tempMessage = "<HELLO>";
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                        connected = true;
                    }

                    String message = reader.readLine();

                    if (message.equals("@q")) {
                        clientSocket.close();
                        System.exit(0);
                        break;
                    }

                    else if (message.startsWith("@name ")) {
                        // Установка имени пользователя
                        String username = message.substring(6);
                        String tempMessage = "<NAME>" + username;
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                    }

                    else if (message.equals("@pwd")) {
                        String tempMessage = "<CMD>PWD";
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                    }

                    else if (message.equals("@ls")) {
                        String tempMessage = "<CMD>LS";
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                    }

                    else if (message.startsWith("@cd ")) {
                        String directory = message.substring(4);
                        String tempMessage = "<CMD>CD " + directory;
                        byte[] sendData = tempMessage.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                    }

                    else {
                        message = "<MSG>" + message;
                        byte[] sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sendThread.start();

        // Создаем поток для приема сообщений от сервера
        Thread receiveThread = new Thread(() -> {
            byte[] receiveData = new byte[1024];

            while (true) {
                try {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);

                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                    if (receivedMessage.startsWith("<NAME>")) {
                        chatMateName = receivedMessage.substring(5);
                    }

                    else if (receivedMessage.equals("<PWD>")) {
                        String pwd = System.getProperty("user.dir");
                        System.out.println(chatMateName + " requested your PWD: " + pwd);
                        String message = "<>" + pwd;
                        byte[] sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                    }

                    else if (receivedMessage.equals("@ls")) {
                        System.out.println(chatMateName + " requested your ls");

                        File currentDir = new File(System.getProperty("user.dir"));
                        File[] files = currentDir.listFiles();

                        StringBuilder sb = new StringBuilder();

                        if (files != null) {
                            for (File file : files) {
                                sb.append(file.getName()).append("\n");
                            }
                        }

                        String result = sb.toString();

                        String message = "<LS>" + result;
                        byte[] sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                    }

                    else if (receivedMessage.startsWith("@cd ")) {
                        String pwd = System.getProperty("user.dir");
                        String message = "<PWD>" + pwd;
                        byte[] sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, serverAddressInet, serverPort);
                        clientSocket.send(sendPacket);
                    }

                    else if (receivedMessage.startsWith("<CMD>")) {
                        String commandOutput = receivedMessage.substring(5);
                        System.out.println("Command output: " + commandOutput);
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
    }
}