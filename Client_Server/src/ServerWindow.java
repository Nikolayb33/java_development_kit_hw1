import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    JButton btnStart, btnExit;
    SettingWindow settingWindow;
    JTextArea log;
    JPanel logF;

    boolean isServerWorking;

    ServerWindow(){
        isServerWorking = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        setTitle("Start server");
        setResizable(false);
        btnStart = new JButton("Start");
        btnExit = new JButton("Exit");

        JPanel logJP = new JPanel();


        log = new JTextArea("Server started " + isServerWorking + "\n");
        log.setBounds(10,30, 200,200);
        setVisible(true);
        logJP.add(log);
        add(logJP);



        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                System.out.println("Server stopped " + isServerWorking + "\n");
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                log = new JTextArea("Server started " + isServerWorking + "\n");
                log.setBounds(10,30, 200,200);
                setVisible(true);
                logJP.add(log);
                add(logJP);


//                System.out.println("Server started " + isServerWorking + "\n");
//                settingWindow.setVisible(true);
//                log.append("Server started " + isServerWorking + "\n");
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2));

        panBottom.add(btnStart);
        panBottom.add(btnExit);

        add(panBottom, BorderLayout.SOUTH);
//        add(map);
        setVisible(true);

    }

}
