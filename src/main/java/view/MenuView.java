package view;

import Data.CommonConstant;
import model.Director;
import model.HrStaff;
import model.Personnel;
import model.Staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liushanchen on 16/3/28.
 */
public class MenuView extends JFrame {

    Personnel personnel;

    public MenuView(Personnel personnel) throws HeadlessException {
        super(CommonConstant.Messages.MAIN_MENU);
        this.personnel = personnel;
        initView();
    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(CommonConstant.Messages.WELCOME + personnel.getName() + "."));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(9, 3, 5, 10));

        centerPanel.add(new JLabel(Personnel.PersonnelStr.ID + ": " + personnel.getId(), SwingConstants.RIGHT));
        centerPanel.add(new JPanel());
        centerPanel.add(new JLabel(personnel.getTitle(), SwingConstants.LEFT));


        if (!personnel.getTitle().equals(CommonConstant.DIRECTOR_TITLE)) {
            Staff staff=(Staff) personnel;
            centerPanel.add(new JPanel());
            centerPanel.add(new JLabel(Staff.StaffStr.SUPERVISOR + ": " + personnel.getSupervisorName(), SwingConstants.CENTER));
            centerPanel.add(new JPanel());

            centerPanel.add(new JPanel());
            JButton applyBtn = new JButton(CommonConstant.Messages.APPLY_FOR_A_LEAVE);
            applyBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    new CreateLeavingApplicationView(staff);
                }
            });
            centerPanel.add(applyBtn);
            centerPanel.add(new JPanel());

            centerPanel.add(new JPanel());
            JButton resultBtn = new JButton(CommonConstant.Messages.VIEW_MY_APPLICATION);
            resultBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new LaResultsView(staff);
                }
            });
            centerPanel.add(resultBtn);
            centerPanel.add(new JPanel());

        } else {
            Director director=(Director) personnel;
            centerPanel.add(new JPanel());
            centerPanel.add(new JPanel());
            centerPanel.add(new JPanel());

            centerPanel.add(new JPanel());
            JButton showAll = new JButton(CommonConstant.Messages.SHOW_ALL_STAFF);
            showAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new PrintAllStaffView(director);
                }
            });
            centerPanel.add(showAll);
            centerPanel.add(new JPanel());


            centerPanel.add(new JPanel());
            centerPanel.add(new JPanel());
            centerPanel.add(new JPanel());
            centerPanel.add(new JPanel());
            centerPanel.add(new JPanel());
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JPanel());
        JButton handelBtn = new JButton(CommonConstant.Messages.HANDEL_REQUEST);
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LaToBeHandleView(personnel);
            }
        });
        centerPanel.add(handelBtn);
        centerPanel.add(new JPanel());


        if (personnel.getTitle().equals(CommonConstant.HR_TITLE)) {
            HrStaff hrStaff=(HrStaff) personnel;

            centerPanel.add(new JPanel());
            JButton createBtn = new JButton(CommonConstant.Messages.CREATE_NEW_STAFF);
            createBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CreateStaffView(hrStaff);
                }
            });
            centerPanel.add(createBtn);
            centerPanel.add(new JPanel());

            centerPanel.add(new JPanel());
            JButton deleteBtn = new JButton(CommonConstant.Messages.DELETE_A_STAFF);
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new DeleteAStaffView(hrStaff);
                }
            });
            centerPanel.add(deleteBtn);
            centerPanel.add(new JPanel());
        }


        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);
    }
}
