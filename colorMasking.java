package filters;

import javax.swing.JOptionPane;

import imagelab.*;

public class colorMasking implements ImageFilter{
	 
		// Attribute to store the modified image
		ImgProvider filteredImage;
	 
		public void filter(ImgProvider ip) {
	 
			// Grab the pixel information and put it into a 2D array
			short[][] blue = ip.getBlue();
			short[][] red = ip.getRed();
			short[][] green = ip.getGreen();
	 
	 
			// Make variables for image height and width
			int height = blue.length;
			int width = blue[0].length;
	 
			// Create a new array to store the modified image
			short[][] newImage = new short[height][width];
			
			String response1 = JOptionPane.showInputDialog("Enter a blue amount (0-255)");
			String response2 = JOptionPane.showInputDialog("Enter a red amount (0-255)");
			String response3 = JOptionPane.showInputDialog("Enter a green amount (0-255)");
			
			int blueNum = Integer.parseInt(response1);
			int redNum = Integer.parseInt(response2);
			int greenNum = Integer.parseInt(response3);
	 
			// Loop through the original image and store the modified
			// version in the newImage array
			for (int row = 0; row <  height; row++) {
				for (int col = 0; col <  width; col++) {
					if (distance(red[row][col], redNum, green[row][col], greenNum, blue[row][col], blueNum) < 100) {
						newImage[row][col] = 255;
					} else {
						newImage[row][col] = 0;
					}
				}
			}
			
			// Create a new ImgProvider and set the filtered image to our new image
			filteredImage = new ImgProvider();
			filteredImage.setBWImage(newImage);
	 
			// Show the new image in a new window with title "Flipped Horizontally"
			filteredImage.showPix("color masking");
		}
		
		public double distance(int r1, int r2, int g1, int g2, int b1, int b2) {
			int red = Math.abs(r1-r2)*Math.abs(r1-r2);
			int blue = Math.abs(b2 - b1)*Math.abs(b2 - b1);
			int green = Math.abs(g1 - g2)*Math.abs(g1 - g2);
			return Math.sqrt((red + blue + green));
		}
	 
		public ImgProvider getImgProvider() {
			return filteredImage;
		}
	 
		// This is what users see in the Filter menu
		public String getMenuLabel() {
			return "color masking";
		}
	 
	}
