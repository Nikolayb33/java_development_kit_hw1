import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame{
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    // Add pannel and button on window
    JButton btnStart, btnExit; //
    SettingWindow settingWindow; // window
    JTextArea log; // text area
    JPanel logF; // pannel
    Map map;


    boolean isServerWorking; // добавляем константу состояния сервера


    ServerWindow(){
        isServerWorking = false; // изначальное состояние сервера
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // default close
        setSize(WIDTH, HEIGHT); // set size window
        setLocationRelativeTo(null); // window to center

        // инициализация
        setTitle("Start server"); // титул окошка
        setResizable(false); // запрет на изменения размера
        btnStart = new JButton("Start"); // инициализация кнопки
        btnExit = new JButton("Exit"); // инициализация кнопки
        settingWindow = new SettingWindow(this); // setting window
//        logF = new JPanel(); //
        map = new Map();
        log = new JTextArea("Server started " + isServerWorking + "\n");
        log.setBounds(10,30, 150,150);
        add(log);
        setVisible(true); // Visible window
//        logF.add(log);
//        add(logF);



        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                 рабочая наполовину
                isServerWorking = true;
                log = new JTextArea("Server started " + isServerWorking + "\n");
                add(log);


//через окно настроек
//                settingWindow.setVisible(true);
//                log.append("Server started " + isServerWorking + "\n");
            }
        });





        // размещение панели снизу
        JPanel panBottom = new JPanel(new GridLayout(1, 2));

        panBottom.add(btnStart);
        panBottom.add(btnExit);

        add(panBottom, BorderLayout.SOUTH);
//        add(map);


    }

}
