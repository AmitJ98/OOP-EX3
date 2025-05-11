package image_char_matching;
import utils.Tuple;

import java.util.*;

import static image_char_matching.CharConverter.convertToBoolArray;

public class SubImgCharMatcher {

	private static final int CHAR_ARRAY_SIZE = 256;
	private Map<Character, Tuple<Double, Double>> brightnessMap;
	private Set<Character> charSet;

	public SubImgCharMatcher(char[] charset){
		this.charSet = new HashSet<>();
		this.brightnessMap = new HashMap<>();

		for (char ch : charset){
			this.charSet.add(ch);

			this.brightnessMap.put(ch, new Tuple<>(calculateCharBrightness(ch), 0.0));
		}
		normalizedBrightnessMap();
	}

	public char getCharByImageBrightness(double brightness) {
		char bestChar = 0;
		double minDiff = Double.MAX_VALUE;

		for (char c : this.charSet) {
			double diff = Math.abs(brightness - brightnessMap.get(c).first);
			if (diff < minDiff || (diff == minDiff && c < bestChar)) {
				minDiff = diff;
				bestChar = c;
			}
		}
		return bestChar;
	}

	public void addChar(char c){
		if (!this.charSet.contains(c)) {
			this.charSet.add(c);
			this.brightnessMap.put(c, new Tuple<>(calculateCharBrightness(c), 0.0));
			normalizedBrightnessMap();
		}
	}

	public void removeChar(char c){
		this.charSet.remove(c);
		this.brightnessMap.remove(c);
		normalizedBrightnessMap();
	}

	private Double findMinBrightness() {
		double minValue = Double.MAX_VALUE;

		for (Map.Entry<Character, Tuple<Double,Double>> entry : this.brightnessMap.entrySet()) {
			if (entry.getValue().first < minValue) {
				minValue = entry.getValue().first;
			}
		}
		return minValue;
	}

	private Double findMaxBrightness() {
		double maxValue = Double.MIN_VALUE;
		for (Map.Entry<Character, Tuple<Double,Double>> entry : this.brightnessMap.entrySet()) {
			if (entry.getValue().first > maxValue) {
				maxValue = entry.getValue().first;
			}
		}
		return maxValue;
	}


	private double calculateCharBrightness(char c){
		boolean[][] charArray = convertToBoolArray(c);
		int truePixel = 0;
		for(int row = 0; row < charArray.length; row++){
			for(int col = 0; col < charArray[row].length; col++){
				if (charArray[row][col]){
					truePixel++;
				}
			}
		}
		double normalPixel = (double) truePixel / CHAR_ARRAY_SIZE;
		return normalPixel;
	}


	private void normalizedBrightnessMap() {
		double minVal = findMinBrightness();
		double maxVal = findMaxBrightness();

		for (char c : charSet) {
			Tuple<Double, Double> brightness = brightnessMap.get(c);
			brightness.second = (brightness.first - minVal) / (maxVal - minVal);
		}
	}

}
