package com.knopp10000.ImageViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    private JPanel panel1;
    private JPanel PictureLabel;
    private static final String defImage = "https://cdn.pixabay.com/photo/2017/03/02/09/26/question-mark-2110767_960_720.jpg";
    private static final String largeDefImage = "http://s1.picswalls.com/wallpapers/2015/09/20/2015-wallpaper_111525594_269.jpg";

    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main desu = null;
        if (args.length < 1) // by default program will load AnyExample logo
        {
            //Default
            desu = new Main(largeDefImage);
            frame.setContentPane(desu.panel1);
        }else {
            desu = new Main(args[0]);
            frame.setContentPane(desu.panel1);
        }

        /*if (isFullscreen()){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else {
            frame.pack();
        }*/

        frame.pack();


        Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        frame.setIconImage(icon);

        frame.setVisible(true);


    }

    public Main(String source){
        // loading image


        BufferedImage screenImage = null;

        try {
            if (source.startsWith("http://") || source.startsWith("https://") ) // http:// URL was specified
            {
                screenImage = ImageIO.read(new URL(source));
                System.out.println("Source pic:" + source);
                addPicture(resize(screenImage));

            }else if (source.equals("")){
                System.out.println("Empty main call ");
            }else {
                screenImage = ImageIO.read(new File(source)); // otherwise - file
                addPicture(resize(screenImage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Image resize(BufferedImage source){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenSize.getHeight() < source.getHeight() || screenSize.getWidth() < source.getWidth()) {
            Image resizedImage = source.getScaledInstance(source.getWidth(), source.getHeight(), Image.SCALE_FAST);
            return resizedImage;
        }

        return (Image)source;
    }

    /*private static Boolean isFullscreen(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenSize.getHeight() < panel1.getHeight() || screenSize.getWidth() < panel1.getWidth()) {
            return true;
        } else {
            return false;
        }
    }*/

    private void addPicture(Image screenImage){
        try {
            JLabel picLabel = new JLabel(new ImageIcon(screenImage));
            PictureLabel.add(picLabel, BorderLayout.CENTER);
            PictureLabel.revalidate();

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
