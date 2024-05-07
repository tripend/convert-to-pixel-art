import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;

public class ProcessImage {
    // Converts an image into pixel art using specified pixel and palette sizes
    public static BufferedImage convertToPixelArt(BufferedImage inputImage, int pixelSize, int paletteSize) throws IOException {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage pixelArtImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Generate histogram and reduce palette
        int[][] histogram = ColorQuantizer.getColorHistogram(inputImage);
        List<int[]> reducedPalette = ColorQuantizer.medianCut(histogram, paletteSize);

        // Convert each block of the image to the nearest color from the reduced palette
        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                int avgColor = getAverageColor(inputImage, x, y, pixelSize);
                int paletteColor = findClosestPaletteColor(avgColor, reducedPalette);
                fillRectangle(pixelArtImage, x, y, pixelSize, pixelSize, paletteColor);
            }
        }

        return pixelArtImage;
    }

    // Calculates the average color of a specified block of pixels
    private static int getAverageColor(BufferedImage image, int x, int y, int size) {
        long totalRed = 0, totalGreen = 0, totalBlue = 0;
        int pixelCount = 0;

        // Sum up all RGB values in the block
        for (int offsetY = 0; offsetY < size && y + offsetY < image.getHeight(); offsetY++) {
            for (int offsetX = 0; offsetX < size && x + offsetX < image.getWidth(); offsetX++) {
                int pixelColor = image.getRGB(x + offsetX, y + offsetY);
                totalRed += (pixelColor >> 16) & 0xFF;
                totalGreen += (pixelColor >> 8) & 0xFF;
                totalBlue += pixelColor & 0xFF;
                pixelCount++;
            }
        }

        if (pixelCount == 0) return 0; // Prevent division by zero
        int avgRed = (int) (totalRed / pixelCount);
        int avgGreen = (int) (totalGreen / pixelCount);
        int avgBlue = (int) (totalBlue / pixelCount);

        return (avgRed << 16) | (avgGreen << 8) | avgBlue;
    }

    // Finds the palette color closest to a given color
    private static int findClosestPaletteColor(int color, List<int[]> palette) {
        int bestDistance = Integer.MAX_VALUE;
        int closestColor = color;

        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        // Determine the closest color from the palette by calculating Euclidean distance
        for (int[] pc : palette) {
            int pr = pc[0];
            int pg = pc[1];
            int pb = pc[2];
            int distance = (pr - r) * (pr - r) + (pg - g) * (pg - g) + (pb - b) * (pb - b);
            if (distance < bestDistance) {
                bestDistance = distance;
                closestColor = (pr << 16) | (pg << 8) | pb;
            }
        }

        return closestColor;
    }

    // Fills a rectangle on the BufferedImage with the specified color
    public static void fillRectangle(BufferedImage image, int x, int y, int width, int height, int color) {
        for (int offsetY = 0; offsetY < height && y + offsetY < image.getHeight(); offsetY++) {
            for (int offsetX = 0; offsetX < width && x + offsetX < image.getWidth(); offsetX++) {
                image.setRGB(x + offsetX, y + offsetY, color);
            }
        }
    }

    // Saves the processed image to a file in JPEG format
    public static void saveJPEG(BufferedImage image, String outputPath) throws IOException {
        File outputFile = new File(outputPath);
        ImageIO.write(image, "jpg", outputFile);
    }
}
