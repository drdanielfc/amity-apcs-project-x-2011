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
package org.amityregion5.projectx.server.gui;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import org.amityregion5.projectx.server.controllers.AggregateServerController;
import org.amityregion5.projectx.server.Server;

/**
 *
 * @author Joe Stein
 */
public class ServerNameWindow extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    /** Creates new form ServerNameWindow */
    public ServerNameWindow() {
        initComponents();
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        serverNameField = new javax.swing.JTextField();
        okBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Please enter a name for your server:");

        serverNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                serverNameFieldKeyPressed(evt);
            }
        });

        okBtn.setText("OK");
        okBtn.setEnabled(false);
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(serverNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okBtnActionPerformed
    {//GEN-HEADEREND:event_okBtnActionPerformed
        startServer();
    }//GEN-LAST:event_okBtnActionPerformed

    private void startServer()
    {
        Server s = new Server(serverNameField.getText());
        AggregateServerController asc = new AggregateServerController(s);
        s.setController(asc);
        
        this.dispose();
    }

    private void serverNameFieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_serverNameFieldKeyPressed
    {//GEN-HEADEREND:event_serverNameFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (serverNameField.getText().length() < 1)
            {
                evt.consume();
            } else
            {
                startServer();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE &&
                serverNameField.getText().length() <= 1)
        {
            SwingUtilities.invokeLater(new Runnable(){
                public void run()
                {
                    okBtn.setEnabled(false);
                }
            });
        } else
        {
            SwingUtilities.invokeLater(new Runnable(){
                public void run()
                {
                    okBtn.setEnabled(true);
                }
            });
        }
    }//GEN-LAST:event_serverNameFieldKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okBtn;
    private javax.swing.JTextField serverNameField;
    // End of variables declaration//GEN-END:variables

}
