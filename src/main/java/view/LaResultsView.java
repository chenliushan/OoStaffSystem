package view;

import Data.CommonConstant;
import model.LeavingApplication;
import model.Personnel;
import model.Staff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by liushanchen on 16/3/28.
 */
public class LaResultsView extends JFrame {

    private Staff personnel;
    private JFrame thisFrame;
    private JList jList;
    private DefaultListModel listModel;
    private java.util.List<LeavingApplication> las;

    public LaResultsView(Personnel personnel) throws HeadlessException {
        super(CommonConstant.Messages.SHOW_ALL_LA);
        if (personnel instanceof Staff) {
            this.personnel = (Staff) personnel;
            this.thisFrame = this;
            initView();
        }
    }

    private void initView() {
        this.setSize(800, 300);
        this.setLocation(100, 100);
        JPanel aPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(CommonConstant.Messages.ALL_LA_IS));
        aPanel.add(topPanel, BorderLayout.NORTH);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 500, 200);

        jList = new JList();
        listModel = new DefaultListModel();
        las = personnel.getMyApplicationResults();
        for (LeavingApplication la : las) {
            listModel.addElement(la.toDisplayStr());
        }
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jList.setModel(listModel);
        jList.setSelectedIndex(0);

        JButton handelBtn = new JButton(CommonConstant.Messages.DETAIL);
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = jList.getSelectedIndex();
                if (selected != -1) {
                    LeavingApplication la = las.get(selected);
                    JOptionPane.showMessageDialog(LaResultsView.this, la.toString());
                }
            }
        });

        scrollPane.setViewportView(jList);

        aPanel.add(scrollPane, BorderLayout.CENTER);
        aPanel.add(handelBtn, BorderLayout.SOUTH);

        this.add(aPanel);
        this.setVisible(true);
    }
}
