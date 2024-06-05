import java.net.Socket;

/**
 * This is the class of client.
 */
public class Client {

    /**
     * Connecting the client to the server
     * 
     * @param args
     */
    public static void main(String[] args) {
        try{
            Socket socket_c = new Socket("127.0.0.1", 5000);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
