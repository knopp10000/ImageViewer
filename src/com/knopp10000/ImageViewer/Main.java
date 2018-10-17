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
    private JButton getPictureButton;
    private JPanel PictureLabel;
    private static final String defImage = "https://i.ytimg.com/vi/wcIQQkgPFBM/hqdefault.jpg";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main("").panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(0,0, 600, 600);

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
    }

    public Main(String source){
        // loading image

        getPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BufferedImage screenImage = null;
                
                try {
                    if (source.startsWith("http://") || source.startsWith("https://") ) // http:// URL was specified
                    {
                        screenImage = ImageIO.read(new URL(source));
                        System.out.print(source);

                    }else if (source == ""){
                        System.out.print("Erroooororororororor ");
                    }else {
                        screenImage = ImageIO.read(new File(source)); // otherwise - file
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addPicture(screenImage);
            }
        });
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
