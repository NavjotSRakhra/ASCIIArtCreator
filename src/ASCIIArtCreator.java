import java.io.IOException;

public class ASCIIArtCreator {
    public static void main(String[] args) {
        final String USAGE = "Uasge : java ASCIIArtCreator <options> <pixels(optional)> <source image path>\nOptions:\n-i to disable image popup";
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