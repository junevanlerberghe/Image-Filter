package filters;
import imagelab.*;

public class Darken implements ImageFilter {

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

		// Loop through the original image and store the modified
		// version in the newImage array
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				// flips the image horizontally by reversing the column direction
				short val = im[row][col];
				
				val = (short) (val/2);
				
				newImage[row][col] = val;
			}
		}

		// Create a new ImgProvider and set the filtered image to our new image
		filteredImage = new ImgProvider();
		filteredImage.setBWImage(newImage);

		// Show the new image in a new window with title "Flipped Horizontally"
		filteredImage.showPix("darken");
	}

	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	// This is what users see in the Filter menu
	public String getMenuLabel() {
		return "darken";
	}

}