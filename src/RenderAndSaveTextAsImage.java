import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Renders the ASCII text in an Image and saves it with a random file name at the current directory.
 *
 * @author Navjot Singh Rakhra
 * @version 1.0
 */
public class RenderAndSaveTextAsImage {
    private static final String ALPHABETS = "ABCDEFGHIJLKMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String HYPHEN = "-";
    private int side;
    private int height, width;
    private String fileName;

    public RenderAndSaveTextAsImage(int side) {
        this.side = side;
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        Font font = new Font("Courier New", Font.PLAIN, 15);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        width = metrics.stringWidth("0") * side;
        height = metrics.getHeight() * side;
        g2d.dispose();

        StringBuilder fn = new StringBuilder();

        Random rand = new Random();

        fn.append(ALPHABETS.charAt(rand.nextInt(ALPHABETS.length())));

        for (int i = 2; i <= 15; i++) {
            int alphOrNum = rand.nextInt(2);
            if (alphOrNum == 0) {
                fn.append(ALPHABETS.charAt(rand.nextInt(ALPHABETS.length())));
            } else {
                fn.append(NUMBERS.charAt(rand.nextInt(NUMBERS.length())));
            }
            if (i % 4 == 0) {
                fn.append(HYPHEN);
            }
        }
        fn.append(ALPHABETS.charAt(rand.nextInt(ALPHABETS.length())));
        fn.append(".png");
        fileName = fn.toString();
    }

    public void save(String text) throws IOException {
        String textArray[] = text.split("\n");
        BufferedImage image;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        int h = height / side;

        //The below block sets the properties of image to be rendered
        Font font = new Font("Courier New", Font.PLAIN, 15);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);

        for (int i = 0; i < textArray.length; i++) {
            g2d.drawString(textArray[i], 0, i * h);
        }
        g2d.dispose();

        ImageIO.write(image, "png", new File(fileName));
        image = ImageIO.read(new File(fileName));
        int maxDim = Math.max(height, width);
        Image im = image.getScaledInstance(maxDim, maxDim, BufferedImage.SCALE_SMOOTH);
        image = new BufferedImage(maxDim, maxDim, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.drawImage(im, 0, 0, null);
        g.dispose();
        ImageIO.write(image, "png", new File(fileName));
    }
}