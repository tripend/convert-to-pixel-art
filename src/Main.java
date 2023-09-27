import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <inputFilePath> <outputFilePath> <pixelSize>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];
        int pixelSize = Integer.parseInt(args[2]);

        try {
            BufferedImage inputImage = ImageIO.read(new File(inputFilePath));
            BufferedImage pixelArtImage = ProcessImage.convertToPixelArt(inputImage, pixelSize);
            ProcessImage.saveJPEG(pixelArtImage, outputFilePath);
            System.out.println("Pixel art image saved: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
