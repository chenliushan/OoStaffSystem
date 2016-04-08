package view;

import Data.CommonConstant;
import com.sun.net.httpserver.Authenticator;
import model.LeavingApplication;
import model.Personnel;
import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liushanchen on 16/4/8.
 */
public class LaHandleView extends JFrame {

    private Personnel personnel;
    private JFrame thisFrame;
    private LeavingApplication la;

    public LaHandleView(Personnel personnel, LeavingApplication la) {
        super(CommonConstant.Messages.HANDEL_LEAVING_APPLICATIONS);
        thisFrame=this;
        this.personnel = personnel;
        this.la = la;
        initView();
    }

    private void initView() {
        this.setSize(500, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(CommonConstant.Messages.LEAVING_APPLICATIONS_DETAIL));
        aPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JTextArea(la.toString()));
        aPanel.add(centerPanel, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new GridLayout(3, 2, 5, 10));
        JButton showAll = new JButton(CommonConstant.Messages.ENDORSE);
        showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personnel.endorse(la);
                JOptionPane.showMessageDialog(LaHandleView.this, CommonConstant.Messages.SUCCESS);
                thisFrame.dispose();
            }
        });
        bottomPanel.add(showAll);

        JButton showAllLa = new JButton(CommonConstant.Messages.DECLINE);
        showAllLa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personnel.decline(la);
                JOptionPane.showMessageDialog(LaHandleView.this, CommonConstant.Messages.SUCCESS);
                thisFrame.dispose();
            }
        });
        bottomPanel.add(showAllLa);

        for (int i = 0; i < 4; i++) {
            bottomPanel.add(new JPanel());
        }
        aPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.add(aPanel);
        this.setVisible(true);
    }
}
