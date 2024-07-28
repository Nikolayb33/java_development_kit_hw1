import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private ServerWindow serverWindow;
    private boolean connected;
    private String name;
    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;


//    JTextArea log = new JTextArea();
//
//    JPanel panelTop = new JPanel(new GridLayout(2, 3));
//    JTextField tfIPAdress = new JTextField("127.0.0.1");
//    JTextField tfPort = new JTextField("8189");
//    JTextField tfLogin = new JTextField("ivan_igorevich");
//    JPasswordField tfPassword = new JPasswordField("123456");
//    JButton btnLogin = new JButton("Login");
//
//
//    JPanel panelBottom = new JPanel(new BorderLayout());
//    JTextField tfMessage = new JTextField();
//    JButton btnSend = new JButton("Send");


    ClientGUI(ServerWindow serverWindow){
        this.serverWindow = serverWindow;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(serverWindow.getX() - 500, serverWindow.getY());
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");

        createPanel();

        setVisible(true);

//        panelTop.add(tfIPAdress);
//        panelTop.add(tfPort);
//        panelTop.add(tfLogin);
//        panelTop.add(tfPassword);
//        panelTop.add(btnLogin);
//        add(panelTop, BorderLayout.NORTH);
//
//        panelBottom.add(tfMessage, BorderLayout.CENTER);
//        panelBottom.add(btnSend, BorderLayout.EAST);
//        add(panelBottom, BorderLayout.SOUTH);
//
//        log.setEditable(false);
//        JScrollPane scrollLog = new JScrollPane(log);
//        add(scrollLog);
//
//        setVisible(true);



//        btnStart = new JButton("Start new chat");


//        btnStart.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//            }
//        });
    }

    public void answer(String text){
        appendLog(text);
    }
    private void connectToServer() {
        if (serverWindow.connectUser(this)){
            appendLog("Вы успешно подключились!\n");
            headerPanel.setVisible(false);
            connected = true;
            name = tfLogin.getText();
            String log = serverWindow.getLog();
            if (log != null){
                appendLog(log);
            }
        } else {
            appendLog("Подключение не удалось");
        }
    }

    public void disconnectFromServer() {
        if (connected) {
            headerPanel.setVisible(true);
            connected = false;
            serverWindow.disconnectUser(this);
            appendLog("Вы были отключены от сервера!");
        }
    }

    public void message(){
        if (connected){
            String text = tfMessage.getText();
            if (!text.equals("")){
                serverWindow.message(name + ": " + text);
                tfMessage.setText("");
            }
        } else {
            appendLog("Нет подключения к серверу");
        }

    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    message();
                }
            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectFromServer();
        }
        super.processWindowEvent(e);
    }



}
