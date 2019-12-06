package com.company;

import com.company.steganography.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if(!(args[0] == "-e" && args.length == 4) || !(args[0] == "-d" && args.length == 3))
            printHelp();

        if(args[0] == "-e") {
            steganograph myNefariousExercise = new steganograph(args[1], args[2]);
            steganographyUtils.writeImageToFile(myNefariousExercise.getRESULT_IMAGE(), args[3]);
        }

        if(args[0] == "-d") {
            steganograph myNefariousExercise = new steganograph(args[1]);
            steganographyUtils.writeImageToFile(myNefariousExercise.getHIDDEN_IMAGE(), args[2]);
        }
    }

    private static void printHelp() {
        System.out.println("steganography: the practice of concealing a file, message, image, or video within another file, message, image, or video.\n");
        System.out.println("Usage: \n");
        System.out.println("steganography -e <path to cover image> <path to image to hide> <out path for resulting image> \n");
        System.out.println("steganography -d <path to encrypted image> <out path for hidden image> \n");
    }
}