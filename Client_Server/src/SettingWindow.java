import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    JTextArea log = new JTextArea();

    JPanel panelTop = new JPanel(new GridLayout(2, 3));
    JTextField tfIPAdress = new JTextField("127.0.0.1");
    JTextField tfPort = new JTextField("8189");
    JTextField tfLogin = new JTextField("ivan_igorevich");
    JPasswordField tfPassword = new JPasswordField("123456");
    JButton btnLogin = new JButton("Login");


    JPanel panelBottom = new JPanel(new BorderLayout());
    JTextField tfMessage = new JTextField();
    JButton btnSend = new JButton("Send");


    SettingWindow(ServerWindow serverWindow){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);



//        btnStart = new JButton("Start new chat");


//        btnStart.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//            }
//        });
    }


}
