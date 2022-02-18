import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * <h1>Image processing class</h1>
 * This class processes the image according to various parameters.It scales the image to the default value of side which
 * is 48 pixels. then it converts the image into grayscale {@link BufferedImage}. Otherwise if side is given while
 * instantiating an object the image will be scaled according to that.
 *
 * @author Navjot Singh Rakhra
 * @version 1.0
 */
public class ImageProcessing {
    private final int side;
    private final BufferedImage image;

    /**
     * The constructor sets side to default value i.e. 48 pixels, after that it reads the {@linkplain BufferedImage} and
     * scales it to 48 pixels and converts it into grayscale format.
     *
     * @param filePath This parameter accepts a {@linkplain  String} that is the path to an image formats supported by
     *                 {@link ImageIO#read(URL)}.
     * @throws IOException Exceptions that might occur while reading the image.
     * @see ImageProcessing#ImageProcessing(String, int)
     * @see BufferedImage
     * @see IOException
     */
    public ImageProcessing(String filePath) throws IOException {
        side = 48;
        File file = new File(filePath);
        BufferedImage img = ImageIO.read(file);
        Image imm = img.getScaledInstance(side, side, Image.SCALE_DEFAULT);
        image = new BufferedImage(side, side, BufferedImage.TYPE_BYTE_GRAY);
        image.getGraphics().drawImage(imm, 0, 0, null);
    }

    /**
     * The constructor sets side to value passed in {@code int side}, after that it reads the {@linkplain BufferedImage} and
     * scales it to {@code side} pixels and converts it into grayscale format.
     *
     * @param filePath This parameter accepts a {@linkplain  String} that is the path to an Image supported by
     *                 {@link ImageIO#read(URL)}.
     * @param side     This parameter sets the value of the global variable {@code private int side}. It represents the
     *                 number of pixels the image will be scaled to.
     * @throws IOException Exceptions that might occur while reading the image.
     * @see ImageProcessing#ImageProcessing(String)
     * @see BufferedImage
     * @see IOException
     */
    public ImageProcessing(String filePath, int side) throws IOException {
        this.side = side;
        File file = new File(filePath);
        BufferedImage img = ImageIO.read(file);
        Image imm = img.getScaledInstance(side, side, Image.SCALE_DEFAULT);
        image = new BufferedImage(side, side, BufferedImage.TYPE_BYTE_GRAY);
        image.getGraphics().drawImage(imm, 0, 0, null);
    }

    /**
     * @return Returns processed {@link BufferedImage} linked with {@link ImageProcessing} object.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @return Returns the number of pixels associated with the {@link BufferedImage} of the object.
     */
    public int getSide() {
        return side;
    }
}