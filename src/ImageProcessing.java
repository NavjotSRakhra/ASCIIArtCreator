import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {
    private int side = 48;
    private BufferedImage image;

    private ImageProcessing() {
    }

    public ImageProcessing(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedImage img = ImageIO.read(file);
        Image imm = img.getScaledInstance(side, side, Image.SCALE_DEFAULT);
        image = new BufferedImage(side, side, BufferedImage.TYPE_BYTE_GRAY);
        image.getGraphics().drawImage(imm, 0, 0, null);
    }

    public ImageProcessing(String filePath, int side) throws IOException {
        this.side = side;
        File file = new File(filePath);
        BufferedImage img = ImageIO.read(file);
        Image imm = img.getScaledInstance(side, side, Image.SCALE_DEFAULT);
        image = new BufferedImage(side, side, BufferedImage.TYPE_BYTE_GRAY);
        image.getGraphics().drawImage(imm, 0, 0, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getSide() {
        return side;
    }
}