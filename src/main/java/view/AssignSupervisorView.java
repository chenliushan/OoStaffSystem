package view;

import model.HrStaff;
import model.Personnel;
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
        super("Assign a Supervisor");
        this.hrStaff = (HrStaff) personnel;
        this.newStaffId = newStaffId;
        this.thisFrame = this;
        initView();
        PersonnelProcess.printInfo();
    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("The new staff has been created successfully!" ));
        aPanel.add(topPanel, BorderLayout.NORTH);


        JPanel upPanel = new JPanel();
        JLabel label=new JLabel("The staff ID :" + newStaffId);
        label.setForeground(Color.red);
        upPanel.add(label);
        aPanel.add(upPanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(8, 3, 5, 10));

        for (int i = 0; i < 3; i++) {
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JPanel());
        centerPanel.add(new JLabel("Assign a supervisor :"));
        centerPanel.add(new JPanel());

        for (int i = 0; i < 3; i++) {
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JLabel("Supervisor Id", SwingConstants.RIGHT));
        final JTextField spidTextField = new JTextField("", 20);
        centerPanel.add(spidTextField);
        centerPanel.add(new JPanel());

        centerPanel.add(new JPanel());
        JButton handelBtn = new JButton("Submit");
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String spid = spidTextField.getText();
                if (spid.length() > 0 && CommonUtils.isInteger(spid)) {
                    Integer id = Integer.valueOf(spid);
                    if (hrStaff.assignSupervisor(newStaffId, id)) {
                        JOptionPane.showMessageDialog(AssignSupervisorView.this, "Success!");
                        thisFrame.dispose();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(AssignSupervisorView.this, "Please input correct supervisor ID!");
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
