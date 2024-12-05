import java.io.IOException;
import java.net.*;
import java.io.ObjectOutputStream;

public class BigTwoClient implements NetworkGame {

    private BigTwo game;
    private BigTwoGUI gui;
    private Socket sock;
    private ObjectOutputStream oos;
    private int playerID;
    private String playerName;
    private String serverIP;
    private int serverPort;
    private BigTwoConnectGUI connectGUI;

    public BigTwoClient(BigTwo game, BigTwoGUI gui) {
        connectGUI = new BigTwoConnectGUI();
    }

    public class ServerHandler implements Runnable {
        @Override
        public void run() {
            //
        }
    }

    public void promptUserToConnect() {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Makes a network connection to the server.
     */
    @Override
    public void connect() {
        try {
            sock = new Socket("127.0.0.1", 5000);
        } catch (UnknownHostException e) {
            connectGUI.sendToLog("Error: host is unknown");
        } catch (IOException e) {
            connectGUI.sendToLog("Error: exception occurred during IO");
        }

    }

    /**
     * Parses the specified message received from the server.
     *
     * @param message the specified message received from the server
     */
    @Override
    public void parseMessage(GameMessage message) {

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
