package view;

import Data.CommonConstant;
import model.Personnel;
import process.PersonnelProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liushanchen on 16/3/28.
 */
public class LoginView extends Frame {

    public LoginView() throws HeadlessException {
        super(CommonConstant.Messages.PROGRAM_NAME);
        initView();
    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(CommonConstant.Messages.LOGIN));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(8, 3, 5, 10));
        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }
        centerPanel.add(new JLabel(Personnel.PersonnelStr.ID, SwingConstants.RIGHT));
        final JTextField usernameTextField = new JTextField("", 20);
        centerPanel.add(usernameTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JLabel(Personnel.PersonnelStr.PASSWORD, SwingConstants.RIGHT));
        final JTextField passwordTextField = new JTextField("", 20);
        centerPanel.add(passwordTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JPanel());
        JButton button = new JButton(CommonConstant.Messages.LOGIN);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean personnel = PersonnelProcess.getInstance().check(usernameTextField.getText(), passwordTextField.getText());
                if (!personnel) {
                    JOptionPane.showMessageDialog(LoginView.this, CommonConstant.Messages.LOGIN_ERROR);
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
//        setupFocus(centerPanel);
    }

    private void setupFocus(JPanel centerPanel) {
        centerPanel.setFocusTraversalPolicyProvider(true);
        centerPanel.setFocusTraversalPolicy(new ContainerOrderFocusTraversalPolicy() {
            @Override
            protected boolean accept(Component aComponent) {
                // Otherwise labels would get focused
                return !(aComponent instanceof JLabel) && super.accept(aComponent);
            }
        });
        // Otherwise the form itself would get focused
        centerPanel.setFocusable(false);
    }

}
