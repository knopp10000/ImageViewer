package com.knopp10000.ImageViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    private JPanel panel1;
    private JPanel PictureLabel;
    private static final String defImage = "https://i.ytimg.com/vi/wcIQQkgPFBM/hqdefault.jpg";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main("").panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //frame.setBounds(0,0, 600, 600);

        Main desu = null;
        if (args.length < 1) // by default program will load AnyExample logo
        {
            //Default
            desu = new Main(defImage);
            frame.setContentPane(desu.panel1);
        }else {
            desu = new Main(args[0]);
            frame.setContentPane(desu.panel1);
        }
        frame.pack();
    }

    public Main(String source){
        // loading image
        BufferedImage screenImage = null;

        try {
            if (source.startsWith("http://") || source.startsWith("https://") ) // http:// URL was specified
            {
                screenImage = ImageIO.read(new URL(source));
                System.out.print(source);
                addPicture(screenImage);

            }else if (source.equals("")){
                System.out.print("Empty main call ");
            }else {
                screenImage = ImageIO.read(new File(source)); // otherwise - file
                addPicture(screenImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void addPicture(BufferedImage screenImage){
        try {
            JLabel picLabel = new JLabel(new ImageIcon(screenImage));
            PictureLabel.add(picLabel, BorderLayout.CENTER);
            PictureLabel.revalidate();

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
