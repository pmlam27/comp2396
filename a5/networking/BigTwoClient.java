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
            CardGameMessage message;
            try {
                while ((message = (CardGameMessage) stream.readObject()) != null) {
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
                String[] playerNames = (String[]) message.getData();
                handlePlayerListMessage(message.getPlayerID(), playerNames);
                break;
            case CardGameMessage.JOIN:
                handleJoinMessage(message.getPlayerID(), (String) message.getData());
                break;
            case CardGameMessage.FULL:
                handleFullMessage();
                break;
            case CardGameMessage.QUIT:
                handleQuitMessage(message.getPlayerID(), (String) message.getData());
                break;
            case CardGameMessage.START:
                handleStartMessage((Deck) message.getData());
                break;
            case CardGameMessage.MSG:
                handleMsgMessage(message.getPlayerID(), (String) message.getData());
            default:
                gui.sendToGameLog("message received");
        }
    }

    /**
     *
     * @param localPlayerID the playerID of the local player
     * @param playerNames a reference to a regular array of strings specifying the names of the players
     */
    private void handlePlayerListMessage(int localPlayerID, String[] playerNames) {
        gui.sendToGameLog("Received PLAYER_LIST");
        gui.sendToGameLog("Player id is " + localPlayerID);
        this.playerID = localPlayerID;
        for(int i=0; i<playerNames.length; i++) {
            if(playerNames[i] == null) {
                gui.sendToGameLog("Player " + i + " is empty");
            } else {
                gui.sendToGameLog("adding " + playerNames[i]);
                game.updateNameOfPlayer(playerNames[i], i);
            }

        }
        sendJoinMessage();
    }

    private void handleJoinMessage(int joinPlayerID, String joinPlayerName) {
        gui.sendToGameLog("Received JOIN");
        gui.sendToGameLog("adding " + joinPlayerName);
        game.updateNameOfPlayer(joinPlayerName, joinPlayerID);
        if(joinPlayerID == playerID) {
            sendReadyMessage();
        }
    }

    private void handleFullMessage() {
        gui.sendToGameLog("Received FULL");
    }

    private void handleQuitMessage(int quitPlayerID, String playerInfo) {
        gui.sendToGameLog("Received QUIT");
    }

    private void handleStartMessage(Deck providedDeck) {
        gui.sendToGameLog("Received START");
    }

    private void handleMsgMessage(int senderPlayerID, String chatMessage) {
        gui.sendToGameLog("Received MSG");
    }

    private void sendJoinMessage() {
        gui.sendToGameLog("Sending JOIN");
        CardGameMessage message = new CardGameMessage(CardGameMessage.JOIN, -1, playerName);
        sendMessage(message);
    }

    private void sendReadyMessage() {
        gui.sendToGameLog("Sending READY");
        CardGameMessage message = new CardGameMessage(CardGameMessage.READY, -1, null);
        sendMessage(message);
    }

    private void sendMoveMessage(int[] cardIndices) {
        gui.sendToGameLog("Sending MOVE");
        CardGameMessage message = new CardGameMessage(CardGameMessage.MOVE, playerID, cardIndices);
        sendMessage(message);
    }

    private void sendMsgMessage(String chatMessage) {
        gui.sendToGameLog("Sending MSG");
        CardGameMessage message = new CardGameMessage(CardGameMessage.MSG, playerID, chatMessage);
        sendMessage(message);
    }

    /**
     * Sends the specified message to the server.
     *
     * @param message the specified message to be sent the server
     */
    @Override
    public void sendMessage(GameMessage message) {
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            gui.sendToGameLog("IO exception occurred during send message");
        }
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
