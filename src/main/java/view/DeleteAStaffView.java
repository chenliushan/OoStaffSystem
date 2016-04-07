package view;

import Data.CommonConstant;
import exception.IllegalOperationException;
import model.HrStaff;
import model.Personnel;
import model.Staff;
import utils.CommonUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liushanchen on 16/4/7.
 */
public class DeleteAStaffView extends JFrame {
    private HrStaff hrStaff;
    private JFrame thisFrame;

    public DeleteAStaffView(Personnel personnel) throws HeadlessException {
        super(CommonConstant.Messages.DELETE_A_STAFF);
        this.hrStaff = (HrStaff) personnel;
        this.thisFrame = this;
        initView();
    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(10, 3, 5, 10));

        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JPanel());
        centerPanel.add(new JLabel(CommonConstant.Messages.DELETE_A_STAFF + CommonConstant.Messages.DELETE_BY_ID));
        centerPanel.add(new JPanel());

        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JLabel(Personnel.PersonnelStr.ID + ":", SwingConstants.RIGHT));
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
                    Personnel searchP = hrStaff.viewStaffInfo(id);
                    if (searchP != null) {
                        int selection = JOptionPane.showConfirmDialog(DeleteAStaffView.this,
                                CommonConstant.Messages.DELETE_CONFIRMATION + searchP.toString(),
                                CommonConstant.Messages.CONFIRMATION, JOptionPane.YES_NO_OPTION);
                        if (selection == JOptionPane.YES_OPTION) {
                            if (hrStaff.deleteStaff(searchP)) {
                                JOptionPane.showMessageDialog(DeleteAStaffView.this, CommonConstant.Messages.SUCCESS);
                            } else {
                                JOptionPane.showMessageDialog(DeleteAStaffView.this, CommonConstant.Messages.OPERATION_FAILED);
                            }
                            thisFrame.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(DeleteAStaffView.this, CommonConstant.Messages.STAFF_NOT_FOUND);
                    }
                    return;
                }
                JOptionPane.showMessageDialog(DeleteAStaffView.this, CommonConstant.Messages.ILLEGAL_INPUT);
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
