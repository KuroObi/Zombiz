package com.dhbw.Zombiz.output.display;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dhbw.Zombiz.gameEngine.logic.Runtime;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Menu {

    public  Menu(final JFrame frame) {




        BufferedImage backgroundImage = null;
        /*BufferedImage startGameImage = null; 
         BufferedImage loadGameImage = null;
         BufferedImage creditsGameImage = null;
         BufferedImage exitGameImage = null;
         */
        String path = "src/main/resources/Picture/Menue/";

        try {
            backgroundImage = ImageIO.read(new File(path + "backgroundv2.jpg"));
            /*startGameImage = ImageIO.read(new File(path+"btnNewGame.png"));
             loadGameImage = ImageIO.read(new File(path+"btnLoadGame.png"));	 
             creditsGameImage = ImageIO.read(new File(path+"btnCredits.png"));	
             exitGameImage = ImageIO.read(new File(path+"btnExit.png"));
             */

        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(new ImageIcon(backgroundImage));

        /*backgroundImage.getGraphics().drawImage(startGameImage, 30, 200, 180, 25, null);
         backgroundImage.getGraphics().drawImage(loadGameImage, 30, 250, 180, 25, null);
         backgroundImage.getGraphics().drawImage(creditsGameImage, 30, 300, 180, 25, null);
         backgroundImage.getGraphics().drawImage(exitGameImage, 30, 350, 180, 25, null);
         */

        JLabel startGamelabel = new JLabel();
        startGamelabel.setBounds(69, 370, 93, 60);
        JLabel startGamelabel2 = new JLabel();
        startGamelabel2.setBounds(129, 347, 50, 43);
        JLabel startGamelabel3 = new JLabel();
        startGamelabel3.setBounds(188, 307, 33, 34);
        JLabel startGamelabel4 = new JLabel();
        startGamelabel4.setBounds(203, 285, 27, 23);

        JLabel loadGamelabel = new JLabel();
        loadGamelabel.setBounds(179, 382, 74, 58);
        JLabel loadGamelabel2 = new JLabel();
        loadGamelabel2.setBounds(179, 382, 74, 58);
        JLabel loadGamelabel3 = new JLabel();
        loadGamelabel3.setBounds(179, 382, 74, 58);

        JLabel creditsGamelabel = new JLabel();
        creditsGamelabel.setBounds(276, 334, 43, 55);
        JLabel creditsGamelabel2 = new JLabel();
        creditsGamelabel2.setBounds(287, 301, 29, 34);

        JLabel exitGamelabel = new JLabel();
        exitGamelabel.setBounds(334, 374, 83, 79);
        JLabel exitGamelabel2 = new JLabel();
        exitGamelabel2.setBounds(327, 336, 54, 35);
        JLabel exitGamelabel3 = new JLabel();
        exitGamelabel3.setBounds(319, 310, 44, 27);
        JLabel exitGamelabel4 = new JLabel();
        exitGamelabel4.setBounds(316, 290, 33, 19);


        startGamelabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("you started a new game ");
                Runtime r = new Runtime(true, frame);
            }
        });
        startGamelabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("you started a new game ");
                Runtime r = new Runtime(true, frame);
            }
        });
        startGamelabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("you started a new game ");
                Runtime r = new Runtime(true, frame);
            }
        });
        loadGamelabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("Load ....");
            }
        });
        loadGamelabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("Load ....");
            }
        });
        loadGamelabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("Load ....");
            }
        });
        creditsGamelabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("Credits ...");
                Credits c = new Credits(frame);
            }
        });
        creditsGamelabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("Credits ...");
                Credits c = new Credits(frame);
            }
        });
        exitGamelabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                closeGame();
            }
        });
        exitGamelabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                closeGame();
            }
        });
        exitGamelabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                closeGame();
            }
        });
        exitGamelabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                closeGame();
            }
        });
        exitGamelabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                closeGame();
            }
        });




        frame.add(startGamelabel);
        frame.add(startGamelabel2);
        frame.add(startGamelabel3);
        frame.add(loadGamelabel);
        frame.add(loadGamelabel2);
        frame.add(loadGamelabel3);
        frame.add(creditsGamelabel);
        frame.add(creditsGamelabel2);
        frame.add(exitGamelabel);
        frame.add(exitGamelabel2);
        frame.add(exitGamelabel3);
        frame.add(exitGamelabel4);

        frame.add(label);
        frame.setVisible(true);
        frame.repaint();



    }

    public static void closeGame() {
        System.exit(0);
    }
}
