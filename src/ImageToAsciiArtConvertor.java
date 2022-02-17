import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Converts grayscale {@link BufferedImage} to ASCII art.
 *
 * @author Navjot Singh Rakhra
 * @version 1.0
 */
public class ImageToAsciiArtConvertor {

    private final static String ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY = " ░▄▀█";
    private final static String ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY_1 = "_.,-=+:;cba!?1023456789$W#@Ñ";
    private final static String ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY_2 = "█▀▄░ ";
    private final static int LENGTH = ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY.length();
    private final static int LENGTH_1 = ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY_1.length();
    private final static int LENGTH_2 = ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY_2.length();

    private int side;

    /**
     * Initializes the side global variable.
     *
     * @param side Number of pixels in the grayscale {@link BufferedImage}
     */
    ImageToAsciiArtConvertor(int side) {
        this.side = side;
    }

    /**
     * Maps the average of red, blue and green value of grayscale {@link BufferedImage} to the ASCII art static string
     * in the order of increasing/decreasing pixel density.
     *
     * @param img {@link BufferedImage} whose height = width = side that was initialized for this object.
     * @return Returns the ASCII art string.
     */
    public String getASCII(BufferedImage img) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                Color color = new Color(img.getRGB(j, i));
                int rgb = color.getRed() + color.getGreen() + color.getBlue();
                rgb /= 3;
                double rgb2 = (double) rgb / 255;
                int index = (int) Math.round(rgb2 * (LENGTH - 1));
                output.append(ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY.charAt(index));
            }
            output.append("\n");
        }
        return output.toString();
    }

}