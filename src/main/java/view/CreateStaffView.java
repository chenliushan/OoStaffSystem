package view;

import Data.CommonConstant;
import model.HrStaff;
import model.Personnel;
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

    public CreateStaffView(Personnel personnel) throws HeadlessException {
        super("Create Staff");
        this.hrStaff = (HrStaff) personnel;
        thisFrame=this;
        initView();

    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Please fill the staff information:"));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(8, 3, 5, 10));

        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JLabel("Staff Name", SwingConstants.RIGHT));
        final JTextField nameTextField = new JTextField("", 20);
        centerPanel.add(nameTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JLabel("Password", SwingConstants.RIGHT));
        final JTextField passwordTextField = new JTextField("", 20);
        centerPanel.add(passwordTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JLabel("Salary", SwingConstants.RIGHT));
        final JTextField salaryTextField = new JTextField("", 20);
        centerPanel.add(salaryTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JPanel());
        JButton handelBtn = new JButton("Create");
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=nameTextField.getText();
                String pw=passwordTextField.getText();
                String sa=salaryTextField.getText();

                if ( name.length()<1 || pw.length()<1|| sa.length()<1) {
                    JOptionPane.showMessageDialog(CreateStaffView.this, "Please input all required information!");
                    return;
                }
                if (CommonUtils.isNumber(sa)) {
                    double salary = Double.valueOf(sa);
                    int newId=hrStaff.createStaff(name, pw, salary);
                    if(newId!=-1){
                        thisFrame.dispose();
//                        JOptionPane.showMessageDialog(CreateStaffView.this, "The staff:"+name+" has been created successfully!\n The staff ID :"+newId);
                        new AssignSupervisorView(hrStaff,newId);
                    }else{
                        JOptionPane.showMessageDialog(CreateStaffView.this, "Fail to create the new staff!");
                    }
                } else {
                    JOptionPane.showMessageDialog(CreateStaffView.this, "Please input correct salary!\n salary: "+sa);
                }
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
    }
}
