import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        // Check if the correct number of arguments are passed
        if (args.length < 4) {
            System.out.println("Usage: java Main <inputFilePath> <outputDirPath> <pixelSize> <paletteSize>");
            return;
        }

        // Parse command line arguments
        String inputFilePath = args[0];
        String outputDirPath = args[1];
        int pixelSize = Integer.parseInt(args[2]);
        int paletteSize = Integer.parseInt(args[3]); // Parse the palette size argument

        // Prepare files for input and output
        File inputFile = new File(inputFilePath);
        String outputFileName = inputFile.getName();
        File outputDir = new File(outputDirPath);
        File outputFile = new File(outputDir, outputFileName);

        try {
            // Read the input image
            BufferedImage inputImage = ImageIO.read(inputFile);

            // Convert the image to pixel art, passing the new paletteSize parameter
            BufferedImage pixelArtImage = ProcessImage.convertToPixelArt(inputImage, pixelSize, paletteSize);

            // Save the converted image as JPEG
            ProcessImage.saveJPEG(pixelArtImage, outputFile.getAbsolutePath());
            System.out.println("Pixel art image saved: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            // Handle exceptions related to file I/O
            e.printStackTrace();
        }
    }
}
