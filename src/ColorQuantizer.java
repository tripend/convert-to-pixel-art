import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ColorQuantizer {

    // Median Cut algorithm
    public static List<int[]> medianCut(int[][] colors, int paletteSize) {
        if (colors.length <= paletteSize) return Arrays.asList(colors);
        return medianCutHelper(Arrays.asList(colors), paletteSize);
    }
    // Recursive helper method for performing the Median Cut
    private static List<int[]> medianCutHelper(List<int[]> colorList, int depth) {
        if (depth == 1 || colorList.size() <= 1) {
            return Arrays.asList(averageColors(colorList));
        }

        int maxRangeIndex = findMaxRangeIndex(colorList);
        colorList.sort(Comparator.comparingInt(a -> a[maxRangeIndex]));

        int medianIndex = colorList.size() / 2;
        List<int[]> lowerHalf = medianCutHelper(new ArrayList<>(colorList.subList(0, medianIndex)), depth / 2);
        List<int[]> upperHalf = medianCutHelper(new ArrayList<>(colorList.subList(medianIndex, colorList.size())), depth / 2);

        List<int[]> result = new ArrayList<>();
        result.addAll(lowerHalf);
        result.addAll(upperHalf);
        return result;
    }

    // Calculates the average color of a list of colors
    private static int[] averageColors(List<int[]> colors) {
        long totalRed = 0, totalGreen = 0, totalBlue = 0;
        for (int[] color : colors) {
            totalRed += color[0];
            totalGreen += color[1];
            totalBlue += color[2];
        }
        int count = colors.size();
        return new int[]{(int) (totalRed / count), (int) (totalGreen / count), (int) (totalBlue / count)};
    }

    // Finds the index with the maximum color range to determine where to split.
    private static int findMaxRangeIndex(List<int[]> colors) {
        int[] min = {255, 255, 255};
        int[] max = {0, 0, 0};
        for (int[] color : colors) {
            for (int i = 0; i < 3; i++) {
                if (color[i] < min[i]) min[i] = color[i];
                if (color[i] > max[i]) max[i] = color[i];
            }
        }
        int maxRange = 0;
        int maxRangeIndex = 0;
        for (int i = 0; i < 3; i++) {
            int range = max[i] - min[i];
            if (range > maxRange) {
                maxRange = range;
                maxRangeIndex = i;
            }
        }
        return maxRangeIndex;
    }

    // Extracts a color histogram from a BufferedImage
    public static int[][] getColorHistogram(BufferedImage image) {
        ArrayList<int[]> colorList = new ArrayList<>();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                colorList.add(new int[]{(pixel >> 16) & 0xFF, (pixel >> 8) & 0xFF, pixel & 0xFF});
            }
        }
        return colorList.toArray(new int[0][]);
    }
}
