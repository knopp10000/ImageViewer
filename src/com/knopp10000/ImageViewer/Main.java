package com.knopp10000.ImageViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private JPanel panel1;
    private JPanel PictureLabel;
    private static final String defImage = "https://cdn.pixabay.com/photo/2017/03/02/09/26/question-mark-2110767_960_720.jpg";
    private static final String largeDefImage = "http://s1.picswalls.com/wallpapers/2015/09/20/2015-wallpaper_111525594_269.jpg";

    public static void main(String[] args) {
        if (args.length < 1) // by default program will load AnyExample logo
        {
            new Main().run(largeDefImage);
        }else {
            new Main().run(args[0]);
        }
     }

    public void run(String source){
        JFrame frame = new JFrame("Epic ImageViewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*if (isFullscreen()){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else {
            frame.pack();
        }*/

        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/images/ImageViewerIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BufferedImage> icons = new LinkedList<>(Arrays.asList(img));
        frame.setIconImages(icons);

        frame.setVisible(true);
        // loading image
        BufferedImage screenImage = null;
        try {
            if (source.startsWith("http://") || source.startsWith("https://") ) // http:// URL was specified
            {
                screenImage = ImageIO.read(new URL(source));
                System.out.println("Source pic:" + source);
                addPicture(resize(screenImage));

            }else if (source.isEmpty()){
                throw new Exception("Empty Main call");
            }else {
                screenImage = ImageIO.read(new File(source)); // otherwise - file
                addPicture(resize(screenImage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.pack();


    }

    private Image resize(BufferedImage source){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenSize.getHeight() < source.getHeight() || screenSize.getWidth() < source.getWidth()) {
            Image resizedImage = source.getScaledInstance((int)screenSize.getWidth(), (int)screenSize.getHeight(), Image.SCALE_SMOOTH);
            return resizedImage;
        }
        return source;
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
