package view;

import Data.CommonConstant;
import model.Personnel;

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
        super("Main Menu");
        this.personnel = personnel;
        initView();
    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Welcome " + personnel.getName() + "."));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(8, 3, 5, 10));

        centerPanel.add(new JPanel());
        centerPanel.add(new JLabel(personnel.getTitle(), SwingConstants.RIGHT));
        centerPanel.add(new JPanel());


        if (!personnel.getTitle().equals(CommonConstant.DIRECTOR_TITLE)) {
            centerPanel.add(new JPanel());
            JButton applyBtn = new JButton("Apply for a leave");
            applyBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            centerPanel.add(applyBtn);
            centerPanel.add(new JPanel());


        } else {
            centerPanel.add(new JPanel());
            JButton showAll = new JButton("Show all staff");
            showAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new PrintAllStaffView(personnel);
                }
            });
            centerPanel.add(showAll);
            centerPanel.add(new JPanel());
        }

        centerPanel.add(new JPanel());
        JButton handelBtn = new JButton("Handel request");
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        centerPanel.add(handelBtn);
        centerPanel.add(new JPanel());

        if (personnel.getTitle().equals(CommonConstant.HR_TITLE)) {
            centerPanel.add(new JPanel());
            JButton createBtn = new JButton("Create new staff");
            createBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CreateStaffView(personnel);
                }
            });
            centerPanel.add(createBtn);
            centerPanel.add(new JPanel());

            centerPanel.add(new JPanel());
            JButton deleteBtn = new JButton("Delete a staff");
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            centerPanel.add(deleteBtn);
            centerPanel.add(new JPanel());
        }


        for (int i = 0; i < 9; i++) {
            centerPanel.add(new JPanel());
        }
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);
    }
}
