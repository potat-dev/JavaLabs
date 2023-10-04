package labs.sem2.lab12.dop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.List;
import java.util.ArrayList;

// TODO: add @admin and @ban commands
// @admin <password> - gives admin rights to the user
// there can be only one admin at a time
// @ban <username> - bans the user from the chat
// banned users can't send messages to the chat
// only admins can ban users

public class Server {
    private static List<Connection> connections = new ArrayList<>();

    // banned users
    private static List<String> banned = new ArrayList<>();

    // current admin
    private static String admin = null;

    // admin password
    private static final String password = "xxXX1234";

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Server <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Connection connection = new Connection(clientSocket);
            connections.add(connection);
            connection.start();
        }
    }

    private static class Connection extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String name;

        public Connection(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        }

        public void sendToAll(String message) {
            for (Connection connection : connections) {
                connection.out.println(message);
            }
        }

        public void sendToUser(String username, String message) {
            for (Connection connection : connections) {
                if (connection.name.equals(username)) {
                    connection.out.println(message);
                }
            }
        }

        @Override
        public void run() {
            try {
                name = in.readLine();
                sendToAll(name + " joined the chat!");

                while (true) {
                    String message = in.readLine();

                    if (message.equals("@exit")) {
                        break;
                    }

                    else if (message.startsWith("@senduser ")) {
                        String username = message.substring(10);
                        String tempMessage = in.readLine();
                        sendToUser(username, name + " [private]: " + tempMessage);
                    }

                    else if (message.startsWith("@admin ")) {
                        if (admin != null) {
                            sendToUser(name, "Admin is already exist!");
                            continue;
                        }

                        String tempPassword = message.substring(7);
                        if (tempPassword.equals(password)) {
                            admin = name;
                            sendToAll(name + " is now admin!");
                        }

                        else {
                            sendToUser(name, "Wrong password!");
                        }
                    }

                    else if (message.startsWith("@ban ")) {
                        String username = message.substring(5);
                        if (admin == null) {
                            sendToUser(name, "You are not admin!");
                        } else if (admin.equals(name)) {
                            banned.add(username);
                            sendToAll(username + " was banned by " + name);
                        } else {
                            sendToUser(name, "You are not admin!");
                        }
                    }

                    else if (banned.contains(name)) {
                        sendToUser(name, "You are banned!");
                    }

                    else {
                        sendToAll(name + ": " + message);
                    }
                }

                sendToAll(name + " left the chat!");
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            finally {
                try {
                    clientSocket.close();
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    connections.remove(this);
                }
            }
        }
    }
}
