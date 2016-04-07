package view;

import Data.CommonConstant;
import model.HrStaff;
import model.Personnel;
import model.Staff;
import process.PersonnelProcess;
import utils.CommonUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liushanchen on 16/3/28.
 */
public class AssignSupervisorView extends JFrame {

    private HrStaff hrStaff;
    private int newStaffId;
    private JFrame thisFrame;

    public AssignSupervisorView(Personnel personnel, int newStaffId) throws HeadlessException {
        super(CommonConstant.Messages.ASSIGN_A_SUPERVISOR);
        this.hrStaff = (HrStaff) personnel;
        this.newStaffId = newStaffId;
        this.thisFrame = this;
        initView();
    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel(CommonConstant.Messages.CREATE_NEW_STAFF+" "+CommonConstant.Messages.SUCCESS));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(9, 3, 5, 10));

        for (int i = 0; i < 3; i++) {
            centerPanel.add(new JPanel());
        }
        centerPanel.add(new JPanel());
        JLabel label=new JLabel(Personnel.PersonnelStr.ID +": "+ newStaffId);
        label.setForeground(Color.red);
        centerPanel.add(label);
        centerPanel.add(new JPanel());

        centerPanel.add(new JPanel());
        centerPanel.add(new JLabel(CommonConstant.Messages.ASSIGN_A_SUPERVISOR));
        centerPanel.add(new JPanel());

        for (int i = 0; i < 3; i++) {
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JLabel(Staff.StaffStr.SUPERVISOR+" "+ Personnel.PersonnelStr.ID , SwingConstants.RIGHT));
        final JTextField spidTextField = new JTextField("", 20);
        centerPanel.add(spidTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JPanel());
        JButton handelBtn = new JButton(CommonConstant.Messages.SUBMIT);
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String spid = spidTextField.getText();
                if (spid.length() > 0 && CommonUtils.isInteger(spid)) {
                    Integer id = Integer.valueOf(spid);
                    if (hrStaff.assignSupervisor(newStaffId, id)) {
                        JOptionPane.showMessageDialog(AssignSupervisorView.this,CommonConstant.Messages.SUCCESS);
                        thisFrame.dispose();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(AssignSupervisorView.this, CommonConstant.Messages.STAFF_NOT_FOUND);
            }
        });
        centerPanel.add(handelBtn);
        centerPanel.add(new JPanel());


        for (int i = 0; i < 9; i++) {
            centerPanel.add(new JPanel());
        }
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);
    }
}
