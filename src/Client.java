public class Client {
    public static void main(String[] args) {
        Login login = new Login();
        Thread thread = new Thread(login);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String name = login.getName();
        ClientGUI clientGui = new ClientGUI(name);
    }
}
