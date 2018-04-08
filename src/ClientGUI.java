import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientGUI extends JFrame {
    private JTextField userText;
    private JTextArea chatWindow;
    private ClientLogic clientLogic;
    private String userName;

    public ClientGUI(String userName){
        super(userName);
        this.userName = userName;
        userText = new JTextField();
        userText.setEditable(true);
        userText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    clientLogic.sendMessage(userText.getText());
                    userText.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        add(userText, BorderLayout.SOUTH);
        chatWindow = new JTextArea();
        chatWindow.setEditable(false);
        add(new JScrollPane(chatWindow));
        setSize(500,250);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        clientLogic = new ClientLogic(this,"localhost");
        run();
    }
    public void setChatWindow(String msg){
        chatWindow.append(msg);
    }
    private void run() {
        clientLogic.startRunning();
    }
    public String getName(){
        return userName;
    }
}
