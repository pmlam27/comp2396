import jogamp.common.util.locks.SingletonInstanceServerSocket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Objects;

public class BigTwoClient implements NetworkGame {

    private BigTwo game;
    private BigTwoGUI gui;
    private Socket sock;
    private ObjectOutputStream oos;
    private int playerID;
    private String playerName;
    private String serverIP = "127.0.0.1";
    private int serverPort = 2396;

    public BigTwoClient(BigTwo game, BigTwoGUI gui) {
        this.game = game;
        this.gui = gui;
        this.playerName = JOptionPane.showInputDialog("Please enter your player name:");
        connect();
    }

    public class ConnectButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            connect();
        }
    }

    public class ServerHandler implements Runnable {
        private ObjectInputStream stream;
        public ServerHandler(ObjectInputStream stream) {
            this.stream = stream;
        }
        @Override
        public void run() {
            GameMessage message;
            try {
                while ((message = (GameMessage) stream.readObject()) != null) {
                    parseMessage(message);
                }
            } catch (IOException e){
                gui.sendToGameLog("Error: IO exception occurred during read line");
            } catch (ClassNotFoundException e) {
                gui.sendToGameLog("Error: Class not found exception occurred during read line");
            }
        }
    }

    /**
     * Makes a network connection to the server.
     */
    @Override
    public void connect() {
        try {
            sock = new Socket(serverIP, serverPort);

            oos = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream stream = new ObjectInputStream(sock.getInputStream());

            gui.sendToGameLog("Success: client have connected to server at " + serverIP + " (port " + serverPort + ")");

            ServerHandler handler = new ServerHandler(stream);
            Thread receiveThread = new Thread(handler);
            receiveThread.start();

        } catch (UnknownHostException e) {
            gui.sendToGameLog("Error: host is unknown");
        } catch (IOException e) {
            gui.sendToGameLog("Error: exception occurred during IO");
        }

    }

    /**
     * Parses the specified message received from the server.
     *
     * @param message the specified message received from the server
     */
    @Override
    public void parseMessage(GameMessage message) {
        int messageType = message.getType();

        switch(messageType) {
            case CardGameMessage.PLAYER_LIST:
                handlePlayerListMessage(message);
                break;
            case CardGameMessage.JOIN:
                gui.sendToGameLog("Received JOIN");
                break;
            case CardGameMessage.FULL:
                gui.sendToGameLog("Received FULL");
                break;
            case CardGameMessage.QUIT:
                gui.sendToGameLog("Received QUIT");
                break;
            case CardGameMessage.READY:
                gui.sendToGameLog("Received READY");
                break;
            case CardGameMessage.START:
                gui.sendToGameLog("Received START");
                break;
            case CardGameMessage.MOVE:
                gui.sendToGameLog("Received MOVE");
                break;
            case CardGameMessage.MSG:
                gui.sendToGameLog("Received MSG");
                break;
        }
    }

    private void handlePlayerListMessage(GameMessage message) {
        gui.sendToGameLog("Received PLAYER_LIST");
        gui.sendToGameLog("Player id is " + message.getPlayerID());
        playerID = message.getPlayerID();
    }

    private void handleJoinMessage(GameMessage message) {

    }

    private void handleFullMessage(GameMessage message) {

    }

    /**
     * Sends the specified message to the server.
     *
     * @param message the specified message to be sent the server
     */
    @Override
    public void sendMessage(GameMessage message) {

    }

    /**
     * Returns the playerID (index) of the local player.
     *
     * @return the playerID (index) of the local player
     */
    @Override
    public int getPlayerID() {
        return 0;
    }

    /**
     * Sets the playerID (index) of the local player.
     *
     * @param playerID the playerID (index) of the local player.
     */
    @Override
    public void setPlayerID(int playerID) {

    }

    /**
     * Returns the name of the local player.
     *
     * @return the name of the local player
     */
    @Override
    public String getPlayerName() {
        return "";
    }

    /**
     * Sets the name of the local player.
     *
     * @param playerName the name of the local player
     */
    @Override
    public void setPlayerName(String playerName) {

    }

    /**
     * Returns the IP address of the server.
     *
     * @return the IP address of the server
     */
    @Override
    public String getServerIP() {
        return "";
    }

    /**
     * Sets the IP address of the server.
     *
     * @param serverIP the IP address of the server
     */
    @Override
    public void setServerIP(String serverIP) {

    }

    /**
     * Returns the TCP port of the server.
     *
     * @return the TCP port of the server
     */
    @Override
    public int getServerPort() {
        return 0;
    }

    /**
     * Sets the TCP port of the server
     *
     * @param serverPort the TCP port of the server
     */
    @Override
    public void setServerPort(int serverPort) {

    }
}
