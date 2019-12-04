package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlgoClass {

   /*
        Parts of the resizeImage method was used in the main method and was attempted to break out into this class.
        The point of this method is to square up the 2 images to avoid out of bound exceptions.
        In a future update of the program, the width and height are variables.
   */

   public static BufferedImage resizeImage(BufferedImage base){
       BufferedImage newImage = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
       SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
       String name = dateFormat.format(new Date());
        newImage = base.getSubimage(((int)(base.getWidth() * .5) - 150), ((int)(base.getHeight() * .5) - 150), 300 , 300);
        try{
            ImageIO.write(newImage, "jpg", new File(name));
        }catch(IOException e) {
            System.out.println(e);
        }
        return newImage;
    }

    //convert an integer to a 8 element array to store a "byte"
    public static int[] convertToBitArray(int input){
        int[] outputArray = new int[8];
        //For the 128 or 8th bit
        if(input >= 128) {
            outputArray[0] = 1;
            input = input - 128;
        }else outputArray[0] = 0;
        //for the 64th or 7th bit
        if(input >= 64){
            outputArray[1] = 1;
            input = input - 64;
        }else outputArray[1] = 0;
        //for the 32th bit
        if(input >= 32){
            outputArray[2] = 1;
            input = input - 32;
        }else outputArray[2] = 0;
        //for the 16th bit
        if(input >= 16){
            outputArray[3] = 1;
            input = input - 16;
        }else outputArray[3] = 0;
        //for the 8th bit
        if(input >= 8){
            outputArray[4] = 1;
            input = input - 8;
        }else outputArray[4] = 0;
        //for the 4th bit
        if(input >= 4){
            outputArray[5] = 1;
            input = input - 4;
        }else outputArray[5] = 0;
        //for the 2nd bit
        if(input >= 2){
            outputArray[6] = 1;
            input = input - 2;
        }else outputArray[6] = 0;
        //for the 1's bit
        if(input >= 1){
            outputArray[7] = 1;
            input = input - 1;
        }else outputArray[7] = 0;
        return outputArray;
    }
    //convert an array back to an integer.
    public static int convertToInt(int[] input){
        int output = 0;
        if(input[0] == 1) output += 128;
        if(input[1] == 1) output += 64;
        if(input[2] == 1) output += 32;
        if(input[3] == 1) output += 16;
        if(input[4] == 1) output += 8;
        if(input[5] == 1) output += 4;
        if(input[6] == 1) output += 2;
        if(input[7] == 1) output += 1;
        return output;
    }
    //breaks off the first 4 bits and returns 4 element array.
    public static int[] getMostSigBits(int[] input){
        int[] output = new int[4];

        for (int i = 0; i < 4; i ++) {
            output[i] = input[i];
        }
        return output;
    }

    //Never used. Alternative methode used instead. Kept for backup or case use.
    public static int[] getLeastSigBits(int[] input){
        int[] output = new int[4];
        for (int i = 4; i < input.length; i++) {
            output[i - 4] = input[i];
        }
        return output;
    }
    //takes the 2 -> 4 element arrays and combines them into 1 byte array.
    public static int[] hideArrayByte(int[] baseImage, int[] hideImage){
        int[] output = new int[8];
        for(int i = 0; i < 4; i++){
            output[i] = baseImage[i];
        }
        for(int j = 4; j < 8; j++){
            output[j] = hideImage[j - 4];
        }
        return output;
    }
    // shifts the int to the left by 4.
    public static int revealInt(int input){
        int output = 1;
        output = (input<<4) & 255;
        return output;
    }
    //builds on the process of comining to bits.
    public static int hideInt(int baseInt, int hideInt){
        //msb stands for "Most significant bit"
        int[] baseArryByte;
        int[] hideArrayByte;
        int[] msbBase;
        int[] msbHide;
        int[] encryptedBitArray;
        int output;

        baseArryByte = convertToBitArray(baseInt);
        hideArrayByte = convertToBitArray(hideInt);

        msbBase = getMostSigBits(baseArryByte);
        msbHide = getMostSigBits(hideArrayByte);

        encryptedBitArray = hideArrayByte(msbBase, msbHide);
        output = convertToInt(encryptedBitArray);
        return output;
    }

    //loops to travel from pixel to pixel-> ectracting the RGB and combining the ints.
    public static BufferedImage hideTheImage(BufferedImage baseImage, BufferedImage hideImage){
        BufferedImage encryptedImage = baseImage;
        for (int i = 0; i < baseImage.getWidth(); i++) {
            for(int j = 0; j < hideImage.getHeight(); j++){
                int baseRGBint = baseImage.getRGB(i, j);
                int hideRGBint = hideImage.getRGB(i, j);

                int baseRed = (baseRGBint>>16) & 255;
                int baseGreen = (baseRGBint>>8) & 255;
                int baseBlue = (baseRGBint) & 255;

                int hideRed = (hideRGBint>>16) & 255;
                int hideGreen = (hideRGBint>>8) & 255;
                int hideBlue = (hideRGBint) & 255;

                int encryptedRed = hideInt(baseRed, hideRed);
                int encryptedGreen = hideInt(baseGreen, hideGreen);
                int encryptedBlue = hideInt(baseBlue, hideBlue);

                int encryptedRGBint = (encryptedRed<<16) | (encryptedGreen<<8) | encryptedBlue;

                encryptedImage.setRGB(i, j, encryptedRGBint);
            }
        }
        return encryptedImage;
    }
    //extracts the hidden image and returning the image.
    public static BufferedImage decryptImage(BufferedImage EncryptedImage){
        BufferedImage hiddenImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < EncryptedImage.getWidth(); i++) {
            for (int j = 0; j < EncryptedImage.getHeight(); j++) {
                int eImageRGB = EncryptedImage.getRGB(i, j);
                int eImgRed = (eImageRGB>>16) & 255;
                int eImgGreen = (eImageRGB>>8) & 255;
                int eImgBlue = (eImageRGB) & 255;

                int dImgRed = revealInt(eImgRed);
                int dImgGreen = revealInt(eImgGreen);
                int dImgBlue = revealInt(eImgBlue);

                int deCryptedRGB = (dImgRed<<16) | (dImgGreen<<8) | (dImgBlue);
                hiddenImage.setRGB(i, j, deCryptedRGB);
            }
        }
        return hiddenImage;
    }

}


