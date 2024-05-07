# Convert-to-Pixel-Art

Convert high-resolution images into stylized pixel art using a user-defined pixel size and color palette. This application simplifies images into blocks of solid colors derived from a reduced color palette, giving them a retro, pixel art look.

## Requirements

- Java Development Kit (JDK) 8 or later
- Images in formats supported by Java ImageIO (e.g., JPEG, PNG)

## Usage

Compile the Java files first:

```bash
javac Main.java ProcessImage.java ColorQuantizer.java
```

To run the program, use the following command from src:
```bash
java Main <inputFilePath> <outputDirPath> <pixelSize> <paletteSize>
```


**Parameters:**
- `<inputFilePath>`: Path to the input image file.
- `<outputDirPath>`: Path to the directory where the output image will be saved.
- `<pixelSize>`: Size of each pixel block (e.g., 10 for 10x10 pixel blocks).
- `<paletteSize>`: Number of colors in the reduced palette.

## Examples
### Example 1: Reducing Detail with a Large Pixel Size

**Command:**
```bash
 java Main ../imgs/waves.png ../output/ 20 16
```

**Before:**
- High-resolution image of a wave.
![image](https://github.com/tripend/convert-to-pixel-art/assets/77321126/5c3b45a6-b46b-4c78-8f47-51845526f8ab)


**After:**
- Pixelated version with blocks of colors simplifying the complex scenery.
![image](https://github.com/tripend/convert-to-pixel-art/assets/77321126/7334fe21-144d-404a-a9bf-ec82de11bbaf)


### Example 2: Limiting Colors
**Command:**
```bash
java Main ../imgs/kingfisher.jpg ../output/ 100 5
```
**Before:**
- Detailed photo of a kingfisher.
![image](https://github.com/tripend/convert-to-pixel-art/assets/77321126/8dec416e-b3df-4350-bcca-3fdf8dfac633)


**After:**
- Reduced to 5 colors, giving it a stark, stylized appearance.
![image](https://github.com/tripend/convert-to-pixel-art/assets/77321126/4e3ad712-5c23-4641-85bf-5076358b2fb1)





## Detailed Command Description

- **`<inputFilePath>`**: The full path to the image file you want to convert.
- **`<outputDirPath>`**: The directory where the pixel art version of the image will be saved.
- **`<pixelSize>`**: Determines how many pixels each 'pixel' in the pixel art will comprise. Larger numbers result in a more blocky appearance.
- **`<paletteSize>`**: Controls how many colors the final image will include. Reducing the number of colors helps achieve the classic pixel art aesthetic.

## Note
Ensure that the output directory exists as the program does not create directories.
