import java.io.IOException;

/**
 * Parses the command line arguments. First it checks for options. If there are any options it stores the options and
 * removes them re-creates the array without the options. Then it checks if the number of CLI are valid, if not it prints
 * usage message. If however, it is valid it passes them accordingly to {@link ImageProcessing} constructors.
 * <p>
 * <br>
 * Static fields
 * <p>
 * {@link ASCIIArtCreator#USAGE}
 *
 * @author Navjot Singh Rakhra
 * @version 1.0
 */
public class ASCIIArtCreator {

    /**
     * Message for usage of the program via command line interface in case the user uses it wrongly or needs help.
     */
    private static final String USAGE = "Uasge : java ASCIIArtCreator <options> <pixels(optional)> <source image path>\nOptions:\n-i to disable image popup";

    public static void main(String[] args) {
        boolean popupImage = true;
        if (args.length == 0) {
            System.out.println(USAGE);
            return;
        }
        if (args[0].startsWith("-")) {
            if (args[0].equals("-i")) {
                popupImage = false;
                String[] argsCopy = new String[args.length];
                System.arraycopy(args, 0, argsCopy, 0, argsCopy.length);
                args = new String[args.length - 1];
                for (int i = 1; i < argsCopy.length; i++) {
                    args[i - 1] = argsCopy[i];
                }
            } else {
                System.out.println(USAGE);
                return;
            }
        }

        if (args.length == 0 || args.length > 2) {
            System.out.println(USAGE);
            return;
        }
        if (args.length == 1) {
            try {
                ImageProcessing processedImage = new ImageProcessing(args[0]);
                ImageToAsciiArtConvertor convertor = new ImageToAsciiArtConvertor(processedImage.getSide());
                System.out.println(convertor.getASCII(processedImage.getImage()));
                if (popupImage)
                    new ImageShower(processedImage.getImage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return;
        }

        assert args.length == 2;

        try {
            int sides = Integer.parseInt(args[0]);
            ImageProcessing processedImage = new ImageProcessing(args[1], sides);
            ImageToAsciiArtConvertor convertor = new ImageToAsciiArtConvertor(processedImage.getSide());
            System.out.println(convertor.getASCII(processedImage.getImage()));
            if (popupImage)
                new ImageShower(processedImage.getImage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Pixels should be an integer.");
        }
    }
}