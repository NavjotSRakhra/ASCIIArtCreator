import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageToAsciiArtConvertor {
    private final String ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY = " ░▄▀█";
    private final String ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY_1 = "_.,-=+:;cba!?1023456789$W#@Ñ";
    private final String ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY_2 = "█▀▄░ ";
    private final int LENGTH = ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY.length();
    private final int LENGTH_1 = ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY_1.length();
    private final int LENGTH_2 = ASCII_CHARACTERS_SORTED_ACCORDING_TO_DENSITY_2.length();

    private int side;

    ImageToAsciiArtConvertor(int side) {
        this.side = side;
    }

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