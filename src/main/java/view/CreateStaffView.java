package view;

import Data.CommonConstant;
import Data.RuntimeData;
import model.HrStaff;
import model.Personnel;
import model.Staff;
import utils.CommonUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liushanchen on 16/3/28.
 */
public class CreateStaffView extends JFrame {

    HrStaff hrStaff;
    JFrame thisFrame;
    Personnel supervisor = null;

    public CreateStaffView(HrStaff personnel) throws HeadlessException {

        super(CommonConstant.Messages.CREATE_NEW_STAFF);
        this.hrStaff = personnel;
        thisFrame = this;
        initView();

    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(CommonConstant.Messages.STAFF_INFO));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(9, 3, 5, 10));

        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JLabel(Personnel.PersonnelStr.NAME, SwingConstants.RIGHT));
        final JTextField nameTextField = new JTextField("", 20);
        centerPanel.add(nameTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JLabel(Personnel.PersonnelStr.PASSWORD, SwingConstants.RIGHT));
        final JTextField passwordTextField = new JTextField("", 20);
        centerPanel.add(passwordTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JLabel(Personnel.PersonnelStr.SALARY, SwingConstants.RIGHT));
        final JTextField salaryTextField = new JTextField("", 20);
        centerPanel.add(salaryTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JLabel(Staff.StaffStr.SUPERVISOR + " " + Personnel.PersonnelStr.ID, SwingConstants.RIGHT));
        final JTextField spidTextField = new JTextField("", 20);
        centerPanel.add(spidTextField);
        JButton checkBtn = new JButton(CommonConstant.Messages.VIEW_STAFF_INFO);
        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String spid = spidTextField.getText();
                if (spid.length() > 0 && CommonUtils.isInteger(spid)) {
                    Integer id = Integer.valueOf(spid);
                    supervisor = hrStaff.viewStaffInfo(id);
                    JOptionPane.showMessageDialog(CreateStaffView.this, supervisor);

                } else {
                    JOptionPane.showMessageDialog(CreateStaffView.this, CommonConstant.Messages.STAFF_NOT_FOUND);
                }
            }
        });
        centerPanel.add(checkBtn);

        centerPanel.add(new JPanel());
        JButton handelBtn = new JButton(CommonConstant.Messages.CREATE_NEW_STAFF);
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String pw = passwordTextField.getText();
                String sa = salaryTextField.getText();

                if (name.length() < 1 || pw.length() < 1 || sa.length() < 1) {
                    JOptionPane.showMessageDialog(CreateStaffView.this, CommonConstant.Messages.ILLEGAL_INPUT);
                    return;
                }
                if (supervisor == null) {
                    JOptionPane.showMessageDialog(CreateStaffView.this, CommonConstant.Messages.ASSIGN_A_SUPERVISOR);
                    return;
                }
                if (!CommonUtils.isNumber(sa)) {
                    JOptionPane.showMessageDialog(CreateStaffView.this,
                            CommonConstant.Messages.ILLEGAL_INPUT + sa + CommonConstant.Messages.ILLEGAL_INPUT_SPECIFIC,
                            CommonConstant.Messages.ILLEGAL, JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double salary = Double.valueOf(sa);
                int newId = hrStaff.createStaff(supervisor, name, pw, salary);
                if (newId != -1) {
                    JOptionPane.showMessageDialog(CreateStaffView.this, CommonConstant.Messages.SUCCESS);
                } else {
                    JOptionPane.showMessageDialog(CreateStaffView.this, CommonConstant.Messages.OPERATION_FAILED);
                }
                thisFrame.dispose();
            }
        });
        centerPanel.add(handelBtn);
        centerPanel.add(new JPanel());


        for (int i = 0; i < 5; i++) {
            centerPanel.add(new JPanel());
        }
        JButton defaultBtn = new JButton(CommonConstant.Messages.CREATE_D_STAFF);
        defaultBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrStaff.createStaff(hrStaff, RuntimeData.StaffAInfo.NAME,
                        RuntimeData.StaffAInfo.PASSWORD,
                        RuntimeData.StaffAInfo.SALARY);
                hrStaff.createStaff(hrStaff, RuntimeData.StaffBInfo.NAME,
                        RuntimeData.StaffBInfo.PASSWORD,
                        RuntimeData.StaffBInfo.SALARY);
                JOptionPane.showMessageDialog(CreateStaffView.this, CommonConstant.Messages.SUCCESS);
                defaultBtn.setEnabled(false);
            }
        });
        centerPanel.add(defaultBtn);
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);
    }
}
