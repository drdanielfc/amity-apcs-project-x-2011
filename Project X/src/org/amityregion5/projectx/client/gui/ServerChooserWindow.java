/**
 * Copyright (c) 2011 Amity AP CS A Students of 2010-2011.
 *
 * ex: set filetype=java expandtab tabstop=4 shiftwidth=4 :
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation.
 */
package org.amityregion5.projectx.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.amityregion5.projectx.client.communication.CommunicationHandler;
import org.amityregion5.projectx.client.communication.MulticastCommunicationHandler;
import org.amityregion5.projectx.common.communication.DatagramListener;
import org.amityregion5.projectx.common.communication.messages.IntroduceMessage;

/**
 * The window for choosing a server
 * 
 * @author Daniel Centore
 * @author Joe Stein
 */
public class ServerChooserWindow extends JFrame implements DatagramListener {

    private static final long serialVersionUID = 1L;
    private DefaultListModel dlm = new DefaultListModel();

    /**
     * Creates new form ServerChooserWindow
     */
    public ServerChooserWindow()
    {
        MulticastCommunicationHandler mch = new MulticastCommunicationHandler();
        mch.registerListener(this);
        mch.start();

        initComponents();
        dlm.addElement("Manual/Internet...");
        dlm.addElement("127.0.0.1");

        serverList.setModel(dlm);

        serverList.setSelectedIndex(0);

        joinBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                int selected = serverList.getSelectedIndex();
                String server;
                if (selected == 0) // manual/internet
                {
                    server = JOptionPane.showInputDialog(ServerChooserWindow.this, "Input server IP", "Server Input", JOptionPane.PLAIN_MESSAGE);
                    // TODO: verify the server here before continuing
                } else
                {
                    Object o = serverList.getModel().getElementAt(selected);
                    server = (String) o;
                }

                CommunicationHandler ch = new CommunicationHandler(server);

                ServerChooserWindow.this.setVisible(false);

                ch.send(new IntroduceMessage("USERNAME")); // TODO: add username handling

                new LobbyWindow(ch.getSocket());

            }

        });

        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("serial")
    private void initComponents()
    {

        joinBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Join a Server");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        joinBtn.setText("Join");
        joinBtn.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Join a Server");

        serverList.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        serverList.setModel(new javax.swing.AbstractListModel() {
            String[] strings =
            { "Manual/Internet..." };

            public int getSize()
            {
                return strings.length;
            }

            public Object getElementAt(int i)
            {
                return strings[i];
            }
        });
        serverList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        serverList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                serverListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(serverList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE).addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE).addComponent(joinBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(joinBtn).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * What to do when the selected value/index on the server list changes.
     * 
     * If the selected index is greater than -1 (i.e. there is a selected element), enable the "Join" button.
     * 
     * @param evt the ListSelectionEvent to handle
     */
    private void serverListValueChanged(javax.swing.event.ListSelectionEvent evt)// GEN-FIRST:event_serverListValueChanged
    {// GEN-HEADEREND:event_serverListValueChanged
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                joinBtn.setEnabled(serverList.getSelectedIndex() > -1);
            }
        });

    }// GEN-LAST:event_serverListValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                new ServerChooserWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton joinBtn;
    private javax.swing.JList serverList;

    // End of variables declaration//GEN-END:variables

    @Override
    public void handle(DatagramPacket packet)
    {
        String add = packet.getAddress().getHostAddress();

        if (!dlm.contains(add))
            dlm.addElement(add);
    }
}
