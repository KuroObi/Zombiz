/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhbw.Zombiz.output.display;

import com.dhbw.Zombiz.gameEngine.logic.BuildRoom;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Stazzer
 */
public class Credits{
        
        private javax.swing.JPanel panel_Credits;
        private javax.swing.JLabel label_creditsCopyright;
        private javax.swing.JLabel label_creditsHeadline;
        private javax.swing.JLabel label_creditsHeadline1;
        private javax.swing.JLabel label_creditsNames;
        private javax.swing.JLabel label_creditsSIMS;
        
         public Credits(final JFrame frame) {
             initComponents();
             for(int i=0; i<frame.getContentPane().getComponentCount(); i++){
                     frame.getContentPane().getComponent(i).setVisible(false);
             }
             frame.setLayout(null);
             frame.getContentPane().setBackground(Color.black);
             frame.getContentPane().add(panel_Credits);
             //frame.revalidate();
             frame.repaint();
             runCredits(frame);
         }
        
        private void initComponents() {
            panel_Credits = new javax.swing.JPanel();
            label_creditsHeadline = new javax.swing.JLabel();
            label_creditsHeadline1 = new javax.swing.JLabel();
            label_creditsNames = new javax.swing.JLabel();
            label_creditsSIMS = new javax.swing.JLabel();
            label_creditsCopyright = new javax.swing.JLabel();

            panel_Credits.setMinimumSize(new java.awt.Dimension(0, 1700));
            panel_Credits.setOpaque(false);
            panel_Credits.setLayout(null);
            panel_Credits.setBounds(130, 0, 730, 2000);

            label_creditsHeadline.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
            label_creditsHeadline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label_creditsHeadline.setText("Nightmare on Coblitzallee");
            //label_creditsHeadline.setText("<HTML><BODY style=\"color : #ffffff\"><DIV id=\"1\" style=\"text-align: center; color: blue; font-size: 16px;\">Nightmare on</DIV><BR\\><DIV id=\"2\">Coblitzallee</DIV></BODY></HTML>");
            panel_Credits.add(label_creditsHeadline);
            label_creditsHeadline.setBounds(10, 30, 540, 60);

            label_creditsHeadline1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label_creditsHeadline1.setText("A game made by Zombiez Inc.");
            panel_Credits.add(label_creditsHeadline1);
            label_creditsHeadline1.setBounds(160, 95, 230, 14);

            label_creditsNames.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label_creditsNames.setText(""
                    + "<HTML>"
                    + "<BODY style=\"color : #736F6E\">"
                    + "<DIV id=\"1\" style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Projektmanager</DIV><BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Kira Schomber</DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Lead Programmer</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Jan Brodhäcker</DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Story</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Kira Schomber<BR\\>Georg Rose<BR\\>Jannik Pachal</DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Rätsel</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Georg Rose<BR\\>Jannik Pachal</DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Charaktere/NPC's</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Kira Schomber<BR\\>Georg Rose <BR\\></DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Dialoge</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Kira Schomber<BR\\></DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Programmierung</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Kira Schomber<BR\\>Jan Brodhäcker<BR\\>Chirstoph Schabert<BR\\>Nadir Yuldashev<BR\\>Jannik Pachal</DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Grafiken</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Jan Brodhäcker<BR\\>Jannik Pachal<BR\\>Nadir Yuldashev</DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Sounds</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Georg Rose<BR\\>Christoph Schabert</DIV>"
                    + "<BR\\><BR\\><BR\\><BR\\>"
                    + "<DIV style=\"text-align: center; color: #FFFFFF; font-size: 16px; text-decoration: underline;\">Special Thanks to...</DIV>"
                    + "<BR\\>"
                    + "<DIV style=\"font-size: 11px; text-align: center;\">Christina Beisel - Die Schöpferin der Storyidee<BR\\>Sarah Brodhäcker - Deine Wohnung ist HAMMER !</DIV>"
                    + "</BODY>"
                    + "</HTML>");
            label_creditsNames.setMaximumSize(new java.awt.Dimension(2147483647, 1500));
            label_creditsNames.setMinimumSize(new java.awt.Dimension(158, 1500));
            label_creditsNames.setPreferredSize(new java.awt.Dimension(397, 990));
            panel_Credits.add(label_creditsNames);
            label_creditsNames.setBounds(0, 120, 530, 1500);

            label_creditsSIMS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            panel_Credits.add(label_creditsSIMS);
            label_creditsSIMS.setBounds(0, 1420, 530, 170);

            label_creditsCopyright.setText("Copyright by Zombiez Inc. 2013");
            panel_Credits.add(label_creditsCopyright);
            label_creditsCopyright.setBounds(140, 1770, 210, 14);
        }
        
        private void runCredits(final JFrame frame){                                                   
            panel_Credits.setVisible(true);
            panel_Credits.requestFocus();
            javax.swing.Timer timer;
            int delay = 50; //milliseconds
            ActionListener taskPerformer = new ActionListener() {

            int down = 0;

            public void actionPerformed(ActionEvent evt) {
                if (down == 1) {
                    panel_Credits.setLocation(panel_Credits.getX(), panel_Credits.getY() - 2);
                    if (panel_Credits.getY() == -1600 || panel_Credits.hasFocus() == false) {
                        down = 0;                        
                        ((javax.swing.Timer) evt.getSource()).stop();
                        frame.getContentPane().setBackground(new Color(238, 238, 238));
                        for(int i=0; i<frame.getContentPane().getComponentCount(); i++){
                            frame.getContentPane().getComponent(i).setVisible(true);
                        }
                    }
                } else {
                    //down = 0;
                    panel_Credits.setLocation(panel_Credits.getX(), panel_Credits.getY() + 490);
                    if (panel_Credits.getY() >= +490) {
                        down = 1;
                    }
                }
            }
        };
        timer = new javax.swing.Timer(delay, taskPerformer);
        timer.start();
    }                     
}
