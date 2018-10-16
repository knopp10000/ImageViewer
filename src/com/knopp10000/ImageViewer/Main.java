package com.knopp10000.ImageViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private JPanel panel1;
    private JButton getPictureButton;
    private JPanel PictureLabel;
    BufferedImage screenImage;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main("").panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0,0, 600, 600);

        if (args.length < 1) // by default program will load AnyExample logo
            new Main("https://i.ytimg.com/vi/wcIQQkgPFBM/hqdefault.jpg");
        else
            new Main(args[0]);
    }

    public Main(String source){

        // loading image
        try {
            if (source.startsWith("http://") || source.startsWith("https://") ) // http:// URL was specified
            {
                screenImage = ImageIO.read(new URL("https://i.ytimg.com/vi/wcIQQkgPFBM/hqdefault.jpg"));
                System.out.print(source);
            }else if (source == ""){
                System.out.print("Hdasdasd");
            }else {
                screenImage = ImageIO.read(new File(source)); // otherwise - file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        getPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                addPicture();
            }
        });
    }



    public void addPicture(){
        System.out.print("sdsda");

        try {
            //BufferedImage myPicture = screenImage;
            JLabel picLabel = new JLabel(new ImageIcon(screenImage));
            PictureLabel.add(picLabel, BorderLayout.CENTER);
            PictureLabel.revalidate();

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
