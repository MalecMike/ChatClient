import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientLogic {
    private ClientGUI clientGui;
    private String serverIp;

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    public ClientLogic(ClientGUI clientGui,String serverIp){
        this.clientGui = clientGui;
        this.serverIp = serverIp;
    }
    public void startRunning(){
        try {
            connectToServer();
            setupStreams();
            gettingMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }
    private void connectToServer() throws IOException {
        showMessage("Attempting connection... ");
        socket = new Socket(InetAddress.getByName(serverIp),3333);
        showMessage("Connected to: " + socket.getInetAddress().getHostName());
    }
    private void setupStreams() throws IOException{
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }
    private void gettingMessages() throws IOException{
        String message = "";
        do{
            try {
                message = (String) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(message.contains(clientGui.getName())){

            }else showMessage(message);
        }while(!message.equals("SERVER: bye"));
    }
    private void closeConnection(){
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showMyMessage(String message){
        clientGui.setChatWindow("Me: " + message + "\n");
    }
    private void showMessage(String message){
        clientGui.setChatWindow(message + "\n");
    }
    public void sendMessage(String message){
        try {
            objectOutputStream.writeObject(clientGui.getName()+ ": " + message);
            objectOutputStream.flush();
            showMyMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
