package view;

import model.Personnel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import process.LoginProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liushanchen on 16/3/28.
 */
public class LoginView extends Frame {
    private static Logger logger = LogManager.getLogger(Personnel.class.getName());

    private LoginProcess loginProcess;

    public LoginView() throws HeadlessException {
        super("Staff System");
        this.setSize(500, 300);
        this.setLocation(100, 100);
        initView();
    }

    private void initView() {
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Login"));
        aPanel.add(topPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(8, 3, 5, 10));
        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }
        centerPanel.add(new JLabel("StaffId", SwingConstants.RIGHT));
        final JTextField usernameTextField = new JTextField("", 20);
        centerPanel.add(usernameTextField);
        centerPanel.add(new JPanel());
        centerPanel.add(new JLabel("Password", SwingConstants.RIGHT));
        final JTextField passwordTextField = new JTextField("", 20);
        centerPanel.add(passwordTextField);
        centerPanel.add(new JPanel());
        centerPanel.add(new JPanel());
        JButton button = new JButton("Login");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginProcess = new LoginProcess();
                if (loginProcess.check(usernameTextField.getText(), passwordTextField.getText())) {
                    JOptionPane.showMessageDialog(LoginView.this, "Hello " + usernameTextField.getText());
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Wrong username or password!");
                }
            }
        });
        centerPanel.add(button);
        centerPanel.add(new JPanel());
        for (int i = 0; i < 9; i++) {
            centerPanel.add(new JPanel());
        }
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);
    }

}
