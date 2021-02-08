package com.knopp10000.ImageViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    JFrame frame;
    private JPanel picturePanel;
    int buffer = 700;
    private static final String defImage = "https://cdn.pixabay.com/photo/2017/03/02/09/26/question-mark-2110767_960_720.jpg";
    private static final String largeDefImage = "http://s1.picswalls.com/wallpapers/2015/09/20/2015-wallpaper_111525594_269.jpg";
    private static final String localImage = "src/images/test.png";
    private static final String localImage2 = "src/com/knopp10000/ImageViewer/test2.jpg";

    public static void main(String[] args) {
        if (args.length == 0) { // by default program will load An Example logo
            System.out.println("!!!");
            new Main().run(localImage);
        } else if (args.length == 1) {
            new Main().run(args[0]);
        } else {
            throw new Error("Too many arguments. Only use 1.");
        }
    }

    public void run(String source){
        System.out.println(source);
        frame = new JFrame("Epic ImageViewer: " + source);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIcons(frame);

//        picturePanel = new JPanel();
//        frame.add(picturePanel, BorderLayout.CENTER);

        //frame.setContentPane();
//        Main desu;

//        if (args.length < 1) // by default program will load AnyExample logo
//        {
//            //Default
//            desu = new Main(localImage);
//            frame.setTitle(localImage);
//            frame.setContentPane(desu.panel1);
//        }else {
//            desu = new Main(args[0]);
//            frame.setContentPane(desu.panel1);
//        }
//        frame.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        if(screenSize.getHeight() <= frame.getHeight() || screenSize.getWidth() <= frame.getWidth()) {
//            System.out.println("here");
//            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.pack();
        } else {
            frame.pack();
        }

        //Kill tha coffé icon
//        Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
//        frame.setIconImage(icon);


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
                // otherwise - file
                screenImage = ImageIO.read(new File(source));
                addPicture(resize(screenImage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.pack();
        frame.setVisible(true);

    }

    private void setIcons(JFrame frame) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/images/ImageViewerIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BufferedImage> icons = new LinkedList<>(Collections.singletonList(img));
        frame.setIconImages(icons);
    }

    private Image resize(BufferedImage source){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double sHeight = screenSize.getHeight();
        double sWidth = screenSize.getWidth();

        if(sHeight < source.getHeight() && sWidth < source.getWidth()) {
            double hdiff = source.getHeight() - sHeight;
            double wdiff = source.getWidth() - sWidth;
            int nHeight = (int)(sHeight - hdiff);
            int nWidth = (int)(sWidth - wdiff);

            if (hdiff > wdiff){
                System.out.println("taller");
                return source.getScaledInstance(nWidth, nHeight - buffer, Image.SCALE_SMOOTH);
            }else{
                System.out.println("wider (or same)");
                return source.getScaledInstance(nWidth - buffer, nHeight, Image.SCALE_SMOOTH);
            }
        }else if (sHeight < source.getHeight()){
            System.out.println("tall");
            double diff = source.getHeight() - sHeight;
            return source.getScaledInstance((int)(source.getWidth() - diff), (int)sHeight, Image.SCALE_SMOOTH);
        }else if(sWidth < source.getWidth()) {
            System.out.println("fat");
            double diff = source.getHeight() - sHeight;
            return source.getScaledInstance((int)sWidth, (int)(source.getHeight() - diff), Image.SCALE_SMOOTH);
        }

        return source;
    }

//    private Boolean isFullscreen(){
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        return screenSize.getHeight() < panel1.getHeight() || screenSize.getWidth() < panel1.getWidth();
//    }

    private void addPicture(Image screenImage){
        try {
            JLabel picLabel = new JLabel(new ImageIcon(screenImage));
            frame.add(picLabel, BorderLayout.CENTER);
            frame.revalidate();

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
