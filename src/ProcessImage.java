import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProcessImage {
    public static BufferedImage convertToPixelArt(BufferedImage inputImage, int pixelSize) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage pixelArtImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                int avgColor = getAverageColor(inputImage, x, y, pixelSize);
                fillRectangle(pixelArtImage, x, y, pixelSize, pixelSize, avgColor);
            }
        }

        return pixelArtImage;
    }

    public static int getAverageColor(BufferedImage image, int x, int y, int size) {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int pixelCount = 0;

        for (int offsetY = 0; offsetY < size && y + offsetY < image.getHeight(); offsetY++) {
            for (int offsetX = 0; offsetX < size && x + offsetX < image.getWidth(); offsetX++) {
                int pixelColor = image.getRGB(x + offsetX, y + offsetY);
                totalRed += (pixelColor >> 16) & 0xFF;
                totalGreen += (pixelColor >> 8) & 0xFF;
                totalBlue += pixelColor & 0xFF;
                pixelCount++;
            }
        }

        int avgRed = totalRed / pixelCount;
        int avgGreen = totalGreen / pixelCount;
        int avgBlue = totalBlue / pixelCount;

        return (avgRed << 16) | (avgGreen << 8) | avgBlue;
    }

    public static void fillRectangle(BufferedImage image, int x, int y, int width, int height, int color) {
        for (int offsetY = 0; offsetY < height && y + offsetY < image.getHeight(); offsetY++) {
            for (int offsetX = 0; offsetX < width && x + offsetX < image.getWidth(); offsetX++) {
                image.setRGB(x + offsetX, y + offsetY, color);
            }
        }
    }

    public static void saveJPEG(BufferedImage image, String outputPath) throws IOException {
        File outputFile = new File(outputPath);
        ImageIO.write(image, "jpg", outputFile);
    }
}
