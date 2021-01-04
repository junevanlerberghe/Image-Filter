package filters;

import imagelab.*;

public class horizontalMirrorColor implements ImageFilter{
	 
		// Attribute to store the modified image
		ImgProvider filteredImage;
	 
		public void filter(ImgProvider ip) {
	 
			// Grab the pixel information and put it into a 2D array
			short[][] blue = ip.getBlue();
			short[][] red = ip.getRed();
			short[][] green = ip.getGreen();
			short[][] alpha = ip.getAlpha();
	 
	 
			// Make variables for image height and width
			int height = blue.length;
			int width = blue[0].length;
	 
			// Create a new array to store the modified image
			short[][] newBlue = new short[height][width];
			short[][] newGreen = new short[height][width];
			short[][] newRed = new short[height][width];
			short[][] newAlpha = new short[height][width];
	 
			// Loop through the original image and store the modified
			// version in the newImage array
			for (int row = 0; row <  height; row++) {
				for (int col = 0; col <  width/2; col++) {
					short b = blue[row][col];
					short g = green[row][col];
					short r = red[row][col];
					short a = alpha[row][col];
					newBlue[row][width - 1 - col] = b;
					newGreen[row][width - 1 - col] = g;
					newRed[row][width - 1 - col] = r;
					if(col <  width/2) {
						newBlue[row][col] = blue[row][col];
						newGreen[row][col] = green[row][col];
						newRed[row][col] = red[row][col];
					}
					newAlpha[row][width - 1 - col] = a;
					newAlpha[row][col] = a;
				}
			}
			
			// Create a new ImgProvider and set the filtered image to our new image
			filteredImage = new ImgProvider();
			filteredImage.setColors(newRed, newGreen, newBlue, newAlpha);
	 
			// Show the new image in a new window with title "Flipped Horizontally"
			filteredImage.showPix("Horizontal Mirror (RGB)");
		}
	 
		public ImgProvider getImgProvider() {
			return filteredImage;
		}
	 
		// This is what users see in the Filter menu
		public String getMenuLabel() {
			return "Horizontal Mirror (RGB)";
		}
	 
	}
