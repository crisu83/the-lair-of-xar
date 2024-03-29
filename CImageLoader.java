package gridwhack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Core image loader class file.
 * @author Christoffer Niska <ChristofferNiska@gmail.com>
 */
public class CImageLoader
{
	private static CImageLoader instance = new CImageLoader();

	private static final String IMAGE_DIR = "images\\";

	private GraphicsConfiguration gc;

	/**
	 * Private constructor.
	 */
	private CImageLoader()
	{
		// Get the graphics configuration.
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gc = ge.getDefaultScreenDevice().getDefaultConfiguration();

	}

	/**
	 * @return the single instance of this class.
	 */
	public static CImageLoader getInstance()
	{
		return instance;
	}

	/**
	 * Returns the image with the specified filename from the images folder.
	 * @param filename the name of the image file.
	 * @return the image.
	 */
	public Image getImage(String filename)
	{
		try
		{
			// Create a managed image for hardware acceleration.
			BufferedImage image = ImageIO.read(getClass().getResource(IMAGE_DIR + filename));
			int transparency = image.getColorModel().getTransparency();
			BufferedImage copy = gc.createCompatibleImage(image.getWidth(), image.getHeight(), transparency);

			Graphics2D g = copy.createGraphics();

			g.drawImage(image, 0, 0, null);
			g.dispose();
			return copy;
		}
		catch( IOException e )
		{
			System.out.println("Error while loading image: " + filename);
			return null; // image could not be loaded
		}
	}
}
