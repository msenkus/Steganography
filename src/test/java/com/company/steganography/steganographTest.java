package com.company.steganography;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class steganographTest {

    private final String BASE_IMAGE_PATH = "src/test/resources/redImage";
    private final String HIDDEN_IMAGE_PATH = "src/test/resources/testImage";
    private final String ENCRYPTED_IMAGE_PATH = "target/test-classes/testEncryptedImage.jpg";

    @Test
    public void testEncryption() throws Exception {
        steganograph toEncrypted = new steganograph(BASE_IMAGE_PATH, HIDDEN_IMAGE_PATH);

        steganographyUtils.writeImageToFile(toEncrypted.getRESULT_IMAGE(), ENCRYPTED_IMAGE_PATH);

        steganograph fromEncrypted = new steganograph(ENCRYPTED_IMAGE_PATH);

        assertEquals(toEncrypted.getHIDDEN_IMAGE().getRaster(), fromEncrypted.getHIDDEN_IMAGE().getRaster());
        assertEquals(toEncrypted.getHIDDEN_IMAGE().getColorModel(), fromEncrypted.getHIDDEN_IMAGE().getColorModel());
        assertEquals(toEncrypted.getHIDDEN_IMAGE().getType(), fromEncrypted.getHIDDEN_IMAGE().getType());
        assertEquals(toEncrypted.getHIDDEN_IMAGE(), fromEncrypted.getHIDDEN_IMAGE());
    }
}
