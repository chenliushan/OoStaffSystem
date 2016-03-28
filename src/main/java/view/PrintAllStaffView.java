package view;

import model.Director;
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
public class PrintAllStaffView extends JFrame {

    private Director director;
    private JFrame thisFrame;

    public PrintAllStaffView(Personnel personnel) throws HeadlessException {
        super("Personnel");
        this.director = (Director) personnel;
        this.thisFrame = this;
        initView();

    }

    private void initView() {
        this.setSize(800, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Here is all the staff information in the system:" ));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JTextArea(director.showAllStaff()));
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);
    }
}
