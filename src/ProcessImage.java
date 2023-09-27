import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProcessImage {
    public static BufferedImage convertToPixelArt(BufferedImage inputImage, int pixelSize) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage pixelArtImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        return pixelArtImage;
    }


    public static void saveJPEG(BufferedImage image, String outputPath) throws IOException {
        File outputFile = new File(outputPath);
        ImageIO.write(image, "jpg", outputFile);
    }
}
