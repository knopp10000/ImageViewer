package com.knopp10000.ImageViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel picturePanel;
    JLabel picLabel;
    private static final String defImage = "https://cdn.pixabay.com/photo/2017/03/02/09/26/question-mark-2110767_960_720.jpg";
    private static final String largeDefImage = "http://s1.picswalls.com/wallpapers/2015/09/20/2015-wallpaper_111525594_269.jpg";

    public static void main(String[] args) {
        if (args.length == 0){ // by default program will load An Example logo
            new Main().run(largeDefImage);
        }else if(args.length == 1){
            new Main().run(args[0]);
        }else{
            throw new Error("Too many arguments. Only use 1.");
        }
     }

    public void run(String source){
        frame = new JFrame("Epic ImageViewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIcons(frame);

        /*if (isFullscreen()){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else {
            frame.pack();
        }*/


        picturePanel = new JPanel();
        frame.add(picturePanel, BorderLayout.CENTER);

        BufferedImage placeholder;
        try {
            placeholder = ImageIO.read(new File("/images/placeholder.png"));
            picLabel = new JLabel(new ImageIcon(placeholder));
        } catch (IOException e) {            e.printStackTrace();
        }
        picturePanel.add(picLabel, BorderLayout.CENTER);




        BufferedImage image;
        try {
            if (source.startsWith("http://") || source.startsWith("https://") ) // http:// URL was specified
            {
                image = ImageIO.read(new URL(source));
                System.out.println("Image source: " + source);
                addPicture(resize(image));

            }else if(source.isEmpty()){
                throw new Exception("Empty Main call");
            }else {
                image = ImageIO.read(new File(source)); // otherwise - file
                addPicture(resize(image));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.pack();
        frame.setVisible(true);

    }

    private void setIcons(JFrame frame) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/images/ImageViewerIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BufferedImage> icons = new LinkedList<>(Arrays.asList(img));
        frame.setIconImages(icons);
    }

    private Image resize(BufferedImage source){
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        if(screenSize.getHeight() < source.getHeight() || screenSize.getWidth() < source.getWidth()) {
//            return source.getScaledInstance((int)screenSize.getWidth(), (int)screenSize.getHeight(), Image.SCALE_SMOOTH);
//        }
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

    private void addPicture(Image image){
        try {
            picLabel = new JLabel(new ImageIcon(image));
            picturePanel.repaint();

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
