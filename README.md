# Steganography

This is a project in process. I created a steganography program previously in JS and wanted to practice the same techneque in JAVA. This is a much more difficult task as image date seems to be stored in multiple types. I have achieved the image storage but can slightly notice it more then I should. I think the pixels are bitshifted to the left causeing a more significant bit to be manipulated. 

Below describes how I programmed this in this past. 

Each pixel in an image contains a RGBA value. First seperate the R G and B values into their respective bytes. Then in the image you want to hide, bitshift to the right by 4 places. Then replace the last 4 bits of the cover image with these 4 bit. This will replace the least significant bits of the cover image with the most significant bits of the hide image. Reverse this to decrypt. 

Mist   Least 
sig     sig
____     ____
