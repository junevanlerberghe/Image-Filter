package filters;
import javax.swing.JOptionPane;

import imagelab.*;

public class Polychrome implements ImageFilter {

	// Attribute to store the modified image
	ImgProvider filteredImage;

	public void filter (ImgProvider ip) {

		// Grab the pixel information and put it into a 2D array
		short[][] im = ip.getBWImage();

		// Make variables for image height and width
		int height = im.length;
		int width  = im[0].length;

		// Create a new array to store the modified image
		short[][] newImage = new short[height][width];
		
		String response = JOptionPane.showInputDialog("Enter a number between 2 and 255");
		int numOfColors = Integer.parseInt(response);
		short[] colorNum = new short[numOfColors];
		short splitLength = 0;
		short oldSplit = 0;
		
		// Loop through the original image and store the modified
		// version in the newImage array
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				for (int i = 1; i <= colorNum.length; i++) {
					splitLength = (short) (( 255 / numOfColors)*i);
					if (im[row][col] > oldSplit && im[row][col] < splitLength) {
						newImage[row][col] = splitLength;
					}
					oldSplit = splitLength;
				}
				
			}
		}

		// Create a new ImgProvider and set the filtered image to our new image
		filteredImage = new ImgProvider();
		filteredImage.setBWImage(newImage);

		// Show the new image in a new window with title "Flipped Horizontally"
		filteredImage.showPix("Polychrome");
	}

	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	// This is what users see in the Filter menu
	public String getMenuLabel() {
		return "Polychrome";
	}

}