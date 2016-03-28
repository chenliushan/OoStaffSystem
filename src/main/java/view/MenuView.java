package view;

import model.Personnel;
import process.LoginProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liushanchen on 16/3/28.
 */
public class MenuView extends JFrame{

    Personnel personnel;
    public MenuView(Personnel personnel) throws HeadlessException {
        super("Main Menu");
        this.setSize(500, 300);
        this.setLocation(100, 100);
        this.personnel=personnel;
        initView();
    }
    private void initView() {
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Welcome "+personnel.getName()+"."));
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
