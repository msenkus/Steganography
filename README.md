# Steganography

A simple class to test hiding an image inside of another image.

## Build
To build, run:
```
$ mvn clean verify
```
There are issues with the unit test failing due to non matching results. To get around this, run:
```
$ mvn clean verify -DskipTests
```

## Usage
```
$ java -jar target/Steganography-0.0.1-SNAPSHOT.jar
Usage:
	steganography -e <path to cover image> <path to image to hide> <out path for resulting image>
	steganography -d <path to encrypted image> <out path for hidden image>

steganography: the practice of concealing a file, message, image, or video within another file, message, image, or video.
```

