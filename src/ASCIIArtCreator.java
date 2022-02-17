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
 * @version 1.1
 */
public class ASCIIArtCreator {

    /**
     * Message for usage of the program via command line interface in case the user uses it wrongly or needs help.
     */
    private static final String USAGE = "Uasge : java ASCIIArtCreator <options> <pixels(optional)> <source image path>\n" +
            "Options:\n-i to disable image popup\n-s if you wish to render and save the text as image\n-a for set of characters 1\n-b for set of characters 2\n-c for set of " +
            "characters 3\nNote that use only one of the a,b or c option at a time otherwise the behaviour is undefined\n";

    public static void main(String[] args) {
        boolean popupImage = true;
        boolean saveAsImage = false;
        int mode = -1;
        if (args.length == 0) {
            System.out.println(USAGE);
            return;
        }
        while (args[0].startsWith("-")) {
            boolean isDone = false;
            if (args[0].contains("i")) {
                popupImage = false;
                isDone = true;
            }
            if (args[0].contains("c")) {
                mode = 1;
                isDone = true;
            }
            if (args[0].contains("b")) {
                mode = 0;
                isDone = true;
            }
            if (args[0].contains("a")) {
                mode = -1;
                isDone = true;
            }
            if (args[0].contains("s")) {
                saveAsImage = true;
                isDone = true;
            }
            if (isDone) {
                String[] argsCopy = new String[args.length];
                System.arraycopy(args, 0, argsCopy, 0, argsCopy.length);
                args = new String[args.length - 1];
                for (int i = 1; i < argsCopy.length; i++) {
                    args[i - 1] = argsCopy[i];
                }
                continue;
            }
            if (args[0].equals("-i")) {
                popupImage = false;
            } else if (args[0].equals("-a")) {
                mode = -1;
            } else if (args[0].equals("-b")) {
                mode = 0;
            } else if (args[0].equals("-c")) {
                mode = 1;
            } else {
                System.out.println(USAGE);
                return;
            }
            String[] argsCopy = new String[args.length];
            System.arraycopy(args, 0, argsCopy, 0, argsCopy.length);
            args = new String[args.length - 1];
            for (int i = 1; i < argsCopy.length; i++) {
                args[i - 1] = argsCopy[i];
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
                if (mode == -1) {
                    String text = convertor.getASCII_1(processedImage.getImage());
                    if (saveAsImage) {
                        new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
                    }
                    System.out.println(text);
                } else if (mode == 0) {
                    String text = convertor.getASCII_2(processedImage.getImage());
                    if (saveAsImage) {
                        new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
                    }
                    System.out.println(text);
                } else if (mode == 1) {
                    String text = convertor.getASCII_3(processedImage.getImage());
                    if (saveAsImage) {
                        new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
                    }
                    System.out.println(text);
                } else {
                    String text = convertor.getASCII_1(processedImage.getImage());
                    if (saveAsImage) {
                        new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
                    }
                    System.out.println(text);
                }
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
            if (mode == -1) {
                String text = convertor.getASCII_1(processedImage.getImage());
                if (saveAsImage) {
                    new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
                }
                System.out.println(text);
            } else if (mode == 0) {
                String text = convertor.getASCII_2(processedImage.getImage());
                if (saveAsImage) {
                    new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
                }
                System.out.println(text);
            } else if (mode == 1) {
                String text = convertor.getASCII_3(processedImage.getImage());
                if (saveAsImage) {
                    new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
                }
                System.out.println(text);
            } else {
                String text = convertor.getASCII_1(processedImage.getImage());
                if (saveAsImage) {
                    new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
                }
                System.out.println(text);
            }
            if (popupImage)
                new ImageShower(processedImage.getImage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Pixels should be an integer.");
        }
    }
}