package view;

import Data.CommonConstant;
import model.LeavingApplication;
import model.Personnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by liushanchen on 16/3/28.
 */
public class LaToBeHandleView extends JFrame {

    private Personnel personnel;
    private JFrame thisFrame;
    private JList jList;
    private DefaultListModel listModel;
    private java.util.List<LeavingApplication> las;

    public LaToBeHandleView(Personnel personnel) throws HeadlessException {
        super(CommonConstant.Messages.SHOW_ALL_LA);
        this.personnel = personnel;
        this.thisFrame = this;
        initView();

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
        las =personnel.getAllShouldBeHandle();
        for (LeavingApplication la : las) {
            listModel.addElement(la.toDisplayStr());
        }
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jList.setModel(listModel);
        jList.setSelectedIndex(0);

        JButton handelBtn = new JButton(CommonConstant.Messages.HANDEL_REQUEST);
        handelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = jList.getSelectedIndex();
                if(selected!=-1){
                    LeavingApplication la = las.get(selected);
                    thisFrame.dispose();
                    new LaHandleView(personnel,la);
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
