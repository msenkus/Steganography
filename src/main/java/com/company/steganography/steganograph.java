package com.company.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class steganograph {

    private BufferedImage BASE_IMAGE;
    private BufferedImage HIDDEN_IMAGE;
    private BufferedImage RESULT_IMAGE;

    public BufferedImage getBASE_IMAGE() {
        return this.BASE_IMAGE;
    }

    public BufferedImage getHIDDEN_IMAGE() {
        return this.HIDDEN_IMAGE;
    }

    public BufferedImage getRESULT_IMAGE() {
        return this.RESULT_IMAGE;
    }

    public steganograph scaled() {
        return new steganograph(
                steganographyUtils.resizeImage(this.getBASE_IMAGE()),
                steganographyUtils.resizeImage(this.getHIDDEN_IMAGE()),
                steganographyUtils.resizeImage(this.getRESULT_IMAGE())
        );
    }

    private void unHideImage() {
        this.HIDDEN_IMAGE = steganographyUtils.unHideImage(RESULT_IMAGE);
    }

    public steganograph(BufferedImage encryptedImage) {
        this.RESULT_IMAGE = encryptedImage;
        this.BASE_IMAGE = encryptedImage;
        unHideImage();
    }

    public steganograph(String encryptedImagePath) {
        try {
            this.RESULT_IMAGE = ImageIO.read(new File(encryptedImagePath));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        unHideImage();
    }

    private void hideImage() {
        this.RESULT_IMAGE = steganographyUtils.hideImage(BASE_IMAGE, HIDDEN_IMAGE);
    }

    public steganograph(BufferedImage coverImage, BufferedImage hiddenImage) {
        this.BASE_IMAGE = coverImage;
        this.HIDDEN_IMAGE = hiddenImage;
        hideImage();
    }

    public steganograph(String coverImagePath, String hiddenImagePath) {
        try {
            this.BASE_IMAGE = ImageIO.read(new File(coverImagePath));
            this.HIDDEN_IMAGE = ImageIO.read(new File(hiddenImagePath));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        hideImage();
    }

    private steganograph(BufferedImage baseImage, BufferedImage hiddenImage, BufferedImage encryptedImage) {
        this.BASE_IMAGE = baseImage;
        this.HIDDEN_IMAGE = hiddenImage;
        this.RESULT_IMAGE = encryptedImage;
    }

}
