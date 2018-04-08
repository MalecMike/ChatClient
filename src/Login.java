import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login extends JFrame implements Runnable {
    private JTextField userName;
    private JLabel information;
    private String name;
    private boolean ready;

    public Login(){
        ready = true;
        name = "";
        userName = new JTextField();
        userName.setEditable(true);
        userName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    setName(userName.getText());
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        add(userName, BorderLayout.CENTER);
        information = new JLabel("USERNAME");
        add(information,BorderLayout.NORTH);
        setSize(200,100);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    @Override
    public void run() {
        while(ready){
            haveName();
        }
    }
    public synchronized void haveName(){
        if(name.equals("")){
            ready = true;
        }
        else ready = false;
    }
}
