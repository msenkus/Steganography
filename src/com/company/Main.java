package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BufferedImage baseImg = null;
        BufferedImage scaledBaseImg = null;
        BufferedImage hideImg = null;
        //BufferedImage scaledHideImg;
        BufferedImage encryptedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        BufferedImage decrptedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        AlgoClass process = new AlgoClass();

        try {
           scaledBaseImg = ImageIO.read(new File("test.jpg"));
           baseImg = ImageIO.read(new File("redImage"));
           hideImg = ImageIO.read(new File("testImage"));
        }catch(IOException e) {
            System.out.println(e);
        }

        //The function used to call the resize function. Best practice is to rename the file once you resize it.
        //scaledBaseImg = process.resizeImage(scaledBaseImg);

        encryptedImage = process.hideTheImage(baseImg, hideImg);
        decrptedImage = process.decryptImage(encryptedImage);

        try{
            //ImageIO.write(scaledBaseImg, "jpg", new File("scaledRedImg"));
            //ImageIO.write(scaledHideImg, "jpg", new File("ScaledTestImage"));
            ImageIO.write(encryptedImage, "jpg", new File("encryptedImage"));
            ImageIO.write(decrptedImage, "jpg", new File("decryptedImage"));
        }catch(IOException e) {
            System.out.println("failed");
        }
    }
}
/*
        for (int i = 0; i < scaledBaseImg.getWidth(); i++) {
            for(int j = 0; j < scaledHideImg.getHeight(); j++){
                int baseRGBint = baseImg.getRGB(i, j);
                int hideRGBint = hideImg.getRGB(i, j);

                int baseRed = (baseRGBint>>16) & 255;
                int baseGreen = (baseRGBint>>8) & 255;
                int baseBlue = (baseRGBint) & 255;

                int hideRed = (hideRGBint>>16) & 255;
                int hideGreen = (hideRGBint>>8) & 255;
                int hideBlue = (hideRGBint) & 255;

                int encryptedRed = process.hideInt(baseRed, hideRed);
                int encryptedGreen = process.hideInt(baseGreen, hideGreen);
                int encryptedBlue = process.hideInt(baseBlue, hideBlue);

                int encryptedRGBint = (encryptedRed<<16) | (encryptedGreen<<8) | encryptedBlue;

                encryptedImage.setRGB(i, j, encryptedRGBint);
            }
        }
 */