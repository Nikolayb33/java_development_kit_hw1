import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame{
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    JButton btnStart;
    JButton btnExit;

    SettingWindow(ServerWindow serverWindow){
        btnStart = new JButton("Start new chat");

        setLocationRelativeTo(serverWindow);
        setSize(WIDTH, HEIGHT);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }


}
