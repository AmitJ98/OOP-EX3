package image;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaddedImage {
	private static final Color WHITE = Color.WHITE;
	private final Color[][] paddedImage;
	private final int width;
	private final int height;


	public PaddedImage(String filename) throws IOException {
		Image originalImage = new Image(filename);
		// todo catch the exception
		int originalWidth = originalImage.getWidth(), originalHeight = originalImage.getHeight();

		this.height = (int) Math.pow(2, Math.ceil(Math.log(originalHeight) / Math.log(2)));
		this.width = (int) Math.pow(2, Math.ceil(Math.log(originalWidth) / Math.log(2)));
		this.paddedImage = createReadyImage(originalWidth, originalHeight, originalImage);
	}

	public PaddedImage(Image image) {
		this.height = (int) Math.pow(2, Math.ceil(Math.log(image.getHeight()) / Math.log(2)));
		this.width = (int) Math.pow(2, Math.ceil(Math.log(image.getWidth()) / Math.log(2)));
		this.paddedImage = createReadyImage(image.getWidth(), image.getHeight(), image);

	}


	private Color[][] createReadyImage(int origWidth, int origHeight, Image originalImage) {
		int heightMargin = (this.height - origHeight) / 2;
		int widthMargin = (this.width - origWidth) / 2;


		Color[][] readyImage = new Color[this.height][this.width];
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				if (row < heightMargin || row >= (this.height - heightMargin)
						|| col < widthMargin || col >= (this.width - widthMargin)) {
					readyImage[row][col] = WHITE;
				} else {
					readyImage[row][col] = originalImage.getPixel(col - widthMargin, row - heightMargin);
				}
			}
		}

		return readyImage;
	}

	public Color[][] getPaddedImage() {
		return this.paddedImage;
	}


	public List<Color[][]> splitImageToBlocks(int resolution) {
		List<Color[][]> blocks = new ArrayList<>();

		int blockSize = (int) Math.ceil((double) width / resolution);
		int blocksInCol = (int) Math.ceil((double) height / blockSize);
		int blocksInRow = (int) Math.ceil((double) width / blockSize);

		for (int blockY = 0; blockY < blocksInCol; blockY++) {
			for (int blockX = 0; blockX < blocksInRow; blockX++) {
				Color[][] block = new Color[blockSize][blockSize];
				for (int dy = 0; dy < blockSize; dy++) {
					for (int dx = 0; dx < blockSize; dx++) {
						int pixelY = blockY * blockSize + dy;
						int pixelX = blockX * blockSize + dx;
						block[dy][dx] = paddedImage[pixelY][pixelX];
					}
				}
				blocks.add(block);
			}
		}

		return blocks;
	}

	private static Double makePixelGrey(Color color) {
		return color.getRed() * 0.2126 + color.getGreen() * 0.7152
				+ color.getBlue() * 0.0722 ;
	}

	public static double calculateImageBrightness(Color[][] image) {
		double brightnessSum = 0;

		for (int row = 0; row < image.length; row++) {
			for (int col = 0; col < image[row].length; col++) {
				brightnessSum += makePixelGrey(image[row][col]);
			}
		}
		int totalPixels = image.length * image[0].length;
		double avgGray = brightnessSum / totalPixels;

		return avgGray / 255.0;
	}
}
