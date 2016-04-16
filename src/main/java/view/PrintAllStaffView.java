package view;

import Data.CommonConstant;
import model.Director;
import model.Personnel;

import javax.swing.*;
import java.awt.*;


/**
 * Created by liushanchen on 16/3/28.
 */
public class PrintAllStaffView extends JFrame {

    private Director director;
    private JFrame thisFrame;

    public PrintAllStaffView(Director personnel) throws HeadlessException {
        super(CommonConstant.Messages.SHOW_ALL_STAFF);
        this.director = personnel;
        this.thisFrame = this;
        initView();

    }

    private void initView() {
        this.setSize(800, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(CommonConstant.Messages.ALL_STAFF_IS));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JTextArea(director.showAllStaff()));
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);
    }
}
