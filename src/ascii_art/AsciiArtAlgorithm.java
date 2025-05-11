package ascii_art;

import image.Image;
import image.PaddedImage;
import image_char_matching.SubImgCharMatcher;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsciiArtAlgorithm {

	private final Image image;
	private final int resolution;
	private final SubImgCharMatcher charMatcher;
	private final Map<Color[][], Double> brightnessCache;

	public AsciiArtAlgorithm(Image image, int resolution, char[] charSet) {
		this.image = image;
		this.resolution = resolution;
		this.charMatcher = new SubImgCharMatcher(charSet);
		this.brightnessCache = new HashMap<>();
	}


	/**
	 * Runs the ASCII art algorithm and returns a 2D character array
	 * representing the image in ASCII.
	 */
	public char[][] run() {
		PaddedImage paddedImage = new PaddedImage(this.image);
		List<Color[][]> blocks = paddedImage.splitImageToBlocks(resolution);

		int charsInRow = this.resolution;
		int charsInCol = blocks.size() / charsInRow;
		char[][] result = new char[charsInCol][charsInRow];

		for (int i = 0; i < charsInCol; i++) {
			for (int j = 0; j < charsInRow; j++) {
				Color[][] block = blocks.get(i * charsInRow + j);
				Double brightness = PaddedImage.calculateImageBrightness(block);
				char c = this.charMatcher.getCharByImageBrightness(brightness);
				result[i][j] = c;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		try {
			// Load the image file
			Image boardImage = new Image("examples/board.jpeg");

			// Define the character set
			char[] charset = { 'm', 'o' };

			// Set resolution (2 characters per row)
			int resolution = 2;

			// Create and run the ASCII art algorithm
			AsciiArtAlgorithm algorithm = new AsciiArtAlgorithm(boardImage, resolution, charset);
			char[][] result = algorithm.run();

			// Print the result as matrix format
			System.out.println("[");
			for (int i = 0; i < result.length; i++) {
				System.out.print("  [");
				for (int j = 0; j < result[i].length; j++) {
					System.out.print(result[i][j]);
					if (j < result[i].length - 1) System.out.print(", ");
				}
				System.out.print("]");
				if (i < result.length - 1) System.out.println(",");
			}
			System.out.println("\n]");
		} catch (Exception e) {
			System.out.println("Error running ASCII art: " + e.getMessage());
		}
	}
}
