package view;

import Data.CommonConstant;
import model.LeavingApplication;
import model.Personnel;
import model.Staff;
import utils.CommonUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by liushanchen on 16/4/7.
 */
public class CreateLeavingApplicationView extends JFrame {
    Staff staff;
    JFrame thisFrame;

    public CreateLeavingApplicationView(Personnel personnel) throws HeadlessException {
        super(CommonConstant.Messages.APPLY_FOR_A_LEAVE);
        this.staff = (Staff) personnel;
        thisFrame = this;
        initView();

    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(CommonConstant.Messages.LEAVE_INFO));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(8, 3, 5, 10));

        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JLabel(LeavingApplication.LAStr.STARTDATE, SwingConstants.RIGHT));
        final JTextField nameTextField = new JTextField("", 20);
        centerPanel.add(nameTextField);
        JButton todayBtn = new JButton(CommonConstant.Messages.TODAY);
        centerPanel.add(todayBtn);

        centerPanel.add(new JLabel(LeavingApplication.LAStr.ENDDATE, SwingConstants.RIGHT));
        final JTextField passwordTextField = new JTextField("", 20);
        centerPanel.add(passwordTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JLabel(LeavingApplication.LAStr.NOTE, SwingConstants.RIGHT));
        final JTextField salaryTextField = new JTextField("", 20);
        centerPanel.add(salaryTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JPanel());
        JButton handelBtn = new JButton(CommonConstant.Messages.SUBMIT);
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String starD = nameTextField.getText();
                String endD = passwordTextField.getText();
                String note = salaryTextField.getText();

                if (staff.applyALeave(starD, endD, note)) {

                    JOptionPane.showMessageDialog(CreateLeavingApplicationView.this, CommonConstant.Messages.SUCCESS);

                } else {
                    JOptionPane.showMessageDialog(CreateLeavingApplicationView.this, CommonConstant.Messages.OPERATION_FAILED);

                }

                thisFrame.dispose();

            }
        });
        centerPanel.add(handelBtn);
        centerPanel.add(new JPanel());


        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);

        todayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                nameTextField.setText(CommonUtils.dispDate(d));
                passwordTextField.setText(CommonUtils.dispDate(d));
            }
        });
    }
}
