import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ServerWindow extends JFrame{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    public static final  String LOG_PATH = "src/log.txt";

    ArrayList<ClientGUI> clientGUIList; // инициализация списка клиентов


    JButton btnStart, btnStop; //

    JTextArea log; // text area

    boolean isServerWorking; // изначальное состояние сервера


    ServerWindow(){
        clientGUIList = new ArrayList<>(); // инициализация списка клиентов на сервере

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // default close
        setSize(WIDTH, HEIGHT); // set size window
        setLocationRelativeTo(null); // window to center

        // инициализация
        setTitle("Chat server"); // титул окошка
        setResizable(false); // запрет на изменения размера
//

        createPanel();
        setVisible(true); // Visible window

//
       }

    public boolean connectUser(ClientGUI clientGUI){
        if (!isServerWorking){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }

    public String getLog(){return readLog();}

    public void disconnectUser(ClientGUI clientGUI){
        clientGUIList.remove(clientGUI);
        if (clientGUI != null){
            clientGUI.disconnectFromServer();
        }
    }

    private String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking){
                    appendLog("Сервер уже был запущен");
                } else {
                    isServerWorking = true;
                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking){
                    appendLog("Сервер уже был остановлен");
                } else {
                    isServerWorking = false;
                    while (!clientGUIList.isEmpty()){
                        disconnectUser(clientGUIList.get(clientGUIList.size()-1));
                    }
                    appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    private void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.answer(text);
        }
    }

     private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void message(String text){
        if (!isServerWorking){
            return;
        }
        text += "";
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    }


