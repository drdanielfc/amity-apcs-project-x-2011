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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import org.amityregion5.projectx.client.Game;
import org.amityregion5.projectx.client.communication.CommunicationHandler;
import org.amityregion5.projectx.client.communication.MulticastCommunicationHandler;
import org.amityregion5.projectx.common.communication.MessageListener;
import org.amityregion5.projectx.common.communication.User;
import org.amityregion5.projectx.common.communication.messages.ActivePlayersMessage;
import org.amityregion5.projectx.common.communication.messages.AnnounceMessage;
import org.amityregion5.projectx.common.communication.messages.ChatMessage;
import org.amityregion5.projectx.common.communication.messages.DisconnectRequestMessage;
import org.amityregion5.projectx.common.communication.messages.GoodbyeMessage;
import org.amityregion5.projectx.common.communication.messages.IntroduceMessage;
import org.amityregion5.projectx.common.communication.messages.Message;
import org.amityregion5.projectx.common.communication.messages.NotifyMessage;
import org.amityregion5.projectx.common.communication.messages.ReadyMessage;
import org.amityregion5.projectx.common.communication.messages.StatusUpdateMessage;
import org.amityregion5.projectx.common.maps.TestingMap;

/**
 * The game lobby
 * 
 * @author Daniel Centore
 * @author Joe Stein
 * @author Michael Wenke
 */
public class LobbyWindow extends JFrame implements MessageListener
{

    private static final long serialVersionUID = 555L;
    private static LobbyWindow instance; // current instance
    private DefaultListModel playerListModel; // the model we change for players
    private CommunicationHandler ch; // the communication with the server
    private boolean ready; // are we ready to play?
    private String lastFrom; // last person to recieve from
    private Game game; // game used by LobbyWindow
    private String username; // our username
    private String[] specialUsernames = new String[]
    {"The5678Nerd", "cowguru2000", "Senhor Herp", "Danielle", "JoeShmo101", "mdubs", "Mr. B"};

    /**
     * Creates a new LobbyWindow.
     * 
     * @param sock the socket that was connected to the server after choosing
     * @param players the players that were already in this lobby. Can be empty or null.
     */
    public LobbyWindow(CommunicationHandler ch, List<User> players, String username)
    {
        super("Project X Lobby");

        this.username = username;
        instance = this;

        lastFrom = "";

        ready = false;
        // adds a window listener so we can tell the server when we leave
        this.addWindowListener(new CustomWindowAdapter());

        this.ch = ch;
        ch.registerListener(this);
        playerListModel = new DefaultListModel();
        initComponents();
        playerList.setCellRenderer(new CustomCellRenderer());
        playerListModel.addElement(new User(username));
        if (players != null)
        {
            for (User player : players)
            {
                playerListModel.addElement(player);
            }
        } else
        {
            ch.send(new ActivePlayersMessage(null));
        }
        playerList.setModel(playerListModel);
        this.setVisible(true);

        ch.send(new NotifyMessage(NotifyMessage.Type.LOBBY_READY));
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatLogArea = new javax.swing.JTextArea();
        chatField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        playerList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sendBtn = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        BackToServerChooserButton = new javax.swing.JButton();
        readyButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatLogArea.setColumns(20);
        chatLogArea.setEditable(false);
        chatLogArea.setLineWrap(true);
        chatLogArea.setRows(5);
        jScrollPane1.setViewportView(chatLogArea);

        chatField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                chatFieldKeyPressed(evt);
            }
        });

        jScrollPane2.setViewportView(playerList);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18));
        jLabel1.setText("Chat");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18));
        jLabel2.setText("Players");

        sendBtn.setText("Send");
        sendBtn.setEnabled(false);
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sendBtnActionPerformed(evt);
            }
        });

        statusLabel.setText("Waiting for players...");
        statusLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));

        BackToServerChooserButton.setText("Exit");
        BackToServerChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BackToServerChooserButtonActionPerformed(evt);
            }
        });

        readyButton.setText("Ready");
        readyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                readyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE).addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(layout.createSequentialGroup().addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE).addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE).addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(layout.createSequentialGroup().addComponent(readyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(BackToServerChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jLabel2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(readyButton).addComponent(BackToServerChooserButton)).addContainerGap()));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void readyButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_readyButtonActionPerformed
    {// GEN-HEADEREND:event_readyButtonActionPerformed

        if (ready)
        {
            readyButton.setText("Ready");
            ch.send(new ReadyMessage(false));
        } else
        {
            readyButton.setText("Not Ready");
            ch.send(new ReadyMessage(true));
        }

        ready = !ready;
    }

    private void BackToServerChooserButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        ch.kill(); // close old connection
        ch.removeListener(this);
        ServerChooserWindow chooser = new ServerChooserWindow();
        MulticastCommunicationHandler mch = new MulticastCommunicationHandler();
        mch.registerListener(chooser);
        mch.start();
        this.dispose();
    }

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_sendBtnActionPerformed
    {// GEN-HEADEREND:event_sendBtnActionPerformed
        sendChat();

    }// GEN-LAST:event_sendBtnActionPerformed

    private void chatFieldKeyPressed(java.awt.event.KeyEvent evt)// GEN-FIRST:event_chatFieldKeyPressed
    {// GEN-HEADEREND:event_chatFieldKeyPressed
        int keycode = evt.getKeyCode();
        if (keycode == KeyEvent.VK_ENTER)
        {
            if (chatField.getText().length() > 0)
            {
                sendChat();
            }
        } else if (keycode == KeyEvent.VK_BACK_SPACE && chatField.getText().length() <= 1)
        {
            if (sendBtn.isEnabled())
            {
                SwingUtilities.invokeLater(new Runnable()
                {

                    public void run()
                    {
                        sendBtn.setEnabled(false);
                    }
                });
            }
        } else if (!evt.isShiftDown() && !evt.isActionKey())
        {
            if (!sendBtn.isEnabled())
            {
                SwingUtilities.invokeLater(new Runnable()
                {

                    public void run()
                    {
                        sendBtn.setEnabled(true);
                    }
                });
            }
        }
    }// GEN-LAST:event_chatFieldKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToServerChooserButton;
    private javax.swing.JTextField chatField;
    private javax.swing.JTextArea chatLogArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList playerList;
    private javax.swing.JButton readyButton;
    private javax.swing.JButton sendBtn;
    private javax.swing.JLabel statusLabel;

    // End of variables declaration//GEN-END:variables
    @Override
    public void handle(final Message m)
    {
        if (m instanceof ChatMessage)
        {
            ChatMessage cm = (ChatMessage) m;

            final String toShow;
            if (lastFrom.equals(cm.getFrom()))
            {
                toShow = cm.getText() + "\n";
            } else
            {
                toShow = "--" + cm.getFrom() + "--\n" + cm.getText() + "\n";
                lastFrom = cm.getFrom();
            }
            // final String toShow = cm.getFrom() + ": " + cm.getText();
            SwingUtilities.invokeLater(new Runnable()
            {

                public void run()
                {
                    chatLogArea.append(toShow);
                    // automatically scroll down
                    chatLogArea.setCaretPosition(chatLogArea.getDocument().getLength());
                }
            });

        } else if (m instanceof IntroduceMessage)
        {
            SwingUtilities.invokeLater(new Runnable()
            {

                public void run()
                {
                    playerListModel.addElement(new User(((IntroduceMessage) m).getText()));
                }
            });
        } else if (m instanceof ActivePlayersMessage)
        {
            SwingUtilities.invokeLater(new Runnable()
            {

                public void run()
                {
                    List<User> usernames = ((ActivePlayersMessage) m).getPlayers();

                    for (User q : usernames)
                    {
                        playerListModel.addElement(q);
                    }
                }
            });
        } else if (m instanceof GoodbyeMessage)
        {
            SwingUtilities.invokeLater(new Runnable()
            {

                public void run()
                {
                    playerListModel.removeElement(new User(((GoodbyeMessage) m).getText()));
                }
            });
        } else if (m instanceof StatusUpdateMessage)
        {
            final StatusUpdateMessage sum = (StatusUpdateMessage) m;
            if (sum.getType() == StatusUpdateMessage.Type.STARTING)
            {
                Game g = new Game(ch, new TestingMap(), username);
                g.initWindow();
                ch.removeListener(this);
                this.dispose();
            }
            SwingUtilities.invokeLater(new Runnable()
            {

                public void run()
                {
                    statusLabel.setText(sum.getText());
                }
            });
        } else if (m instanceof AnnounceMessage)
        {
            final AnnounceMessage am = (AnnounceMessage) m;
            SwingUtilities.invokeLater(new Runnable()
            {

                public void run()
                {
                    chatLogArea.append("[SERVER] " + am.getText() + "\n");
                    // automatically scroll down
                    chatLogArea.setCaretPosition(chatLogArea.getDocument().getLength());
                }
            });
        } else if (m instanceof DisconnectRequestMessage)
        {
            JOptionPane.showMessageDialog(this,
                    ((DisconnectRequestMessage) m).getReason(), "Disconnected",
                    JOptionPane.WARNING_MESSAGE);
            SwingUtilities.invokeLater(new Runnable()
            {

                public void run()
                {
                    playerListModel.removeAllElements();
                    playerList.setEnabled(false);
                    chatField.setEnabled(false);
                    sendBtn.setEnabled(false);
                    statusLabel.setText("Disconnected.");
                    chatLogArea.setEnabled(false);
                }
            });
        } else if (m instanceof ReadyMessage)
        {
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    ReadyMessage rm = (ReadyMessage) m;
                    User u = (User) playerListModel.get(playerListModel.indexOf(new User(rm.getUsername())));
                    u.setReady(rm.isAffirmative());
                    LobbyWindow.this.repaint();
                }
            });
        }
    }

    /**
     * What to do if the server dies
     */
    public void tellSocketClosed()
    {
        JOptionPane.showMessageDialog(this, "Server disconnected unexpectedly", "Connection closed", JOptionPane.WARNING_MESSAGE);
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                playerListModel.removeAllElements();
                readyButton.setEnabled(false);
                playerList.setEnabled(false);
                chatField.setEnabled(false);
                sendBtn.setEnabled(false);
                statusLabel.setText("Disconnected.");
                chatLogArea.setEnabled(false);
            }
        });
    }

    /**
     * Sends the chat that is currently in the box
     */
    private void sendChat()
    {
        ChatMessage chm = new ChatMessage(chatField.getText(), ChatMessage.Type.PUBLIC, username);
        ch.send(chm);
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                chatField.setText("");
                sendBtn.setEnabled(false);
            }
        });
    }

    /**
     * Custom window adapter for notifying the server on leaving.
     */
    private class CustomWindowAdapter extends WindowAdapter
    {

        @Override
        public void windowClosing(WindowEvent e)
        {
            ch.send(new GoodbyeMessage(username));
        }
    }

    private class CustomCellRenderer implements ListCellRenderer
    {

        public Component getListCellRendererComponent(final JList list, final Object value, int index, final boolean isSelected, boolean cellHasFocus)
        {
            return new JPanel()
            {
                private static final long serialVersionUID = 670L;

                @Override
                public void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    User user = (User) value;
                    String username = user.toString();
                    Font f = g.getFont();
                    if (user.isReady())
                    {
                        f = f.deriveFont(Font.BOLD);
                    }
                    FontMetrics fm = g.getFontMetrics(f);
                    g.setColor(isSelected ? list.getSelectionBackground() : list.getBackground());
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(isSelected ? list.getSelectionForeground() : list.getForeground());
                    for (String names: specialUsernames)
                    {
                        if(username.equals(names))
                        {
                            g.setColor(Color.RED);
                            username += " (Admin)";
                        }
                    }
                    g.setFont(f);
                    g.drawString(username, 0, fm.getAscent());
                }

                @Override
                public Dimension getPreferredSize()
                {
                    Graphics g = getGraphics();
                    FontMetrics fm = g.getFontMetrics(g.getFont());
                    return new Dimension(fm.stringWidth(value.toString()), fm.getHeight());
                }
            };
        }
    }

    /**
     * @return Gets the most recent instance of LobbyWindow
     */
    public static LobbyWindow getInstance()
    {
        return instance;
    }

    /**
     * @return The current game (if any)
     */
    public Game getGame()
    {
        return game;
    }
}
