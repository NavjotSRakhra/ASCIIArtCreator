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
 * {@link ASCIIArtCreator#popupImage}
 * {@link ASCIIArtCreator#saveAsImage}
 * {@link ASCIIArtCreator#mode}
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
            "characters 3\n-d for set of characters 4\nNote that use only one of the a,b or c option at a time otherwise the behaviour is undefined\n";
    private static boolean popupImage = true;
    private static boolean saveAsImage = false;
    private static int mode = -1;

    public static void main(String[] args) {
        parseArguments(args);
    }

    /**
     * Pareses the options(Strings that start with '-'), sets the state of global variables and returns a new array without those options
     *
     * @param args Command line arguments.
     * @return Returns a new array without options.
     */
    private static String[] parseOptionsAndSetStates(String[] args) {
        while (args[0].startsWith("-")) {
            boolean isDone = false;
            if (args[0].contains("i")) {
                popupImage = false;
                isDone = true;
            }
            if (args[0].contains("d")) {
                mode = 2;
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
            } else if (args[0].equals("-d")) {
                mode = 1;
            } else {
                printUsage();
                System.exit(0);
            }
            String[] argsCopy = new String[args.length];
            System.arraycopy(args, 0, argsCopy, 0, argsCopy.length);
            args = new String[args.length - 1];
            for (int i = 1; i < argsCopy.length; i++) {
                args[i - 1] = argsCopy[i];
            }
        }
        return args;
    }

    private static void printUsage() {
        System.out.println(USAGE);
    }

    private static void printAndPerformActionsAfterScalingWithDefaultPixelCount(String filePath) {
        try {
            ImageProcessing processedImage = new ImageProcessing(filePath);
            ImageToAsciiArtConvertor convertor = new ImageToAsciiArtConvertor(processedImage.getSide());
            String text = convertor.getAsciiArt(processedImage.getImage(), mode + 1);
            if (saveAsImage)
                new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
            System.out.println(text);
            if (popupImage)
                new ImageShower(processedImage.getImage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printAndPerformActionsAfterScaling(String[] args) {
        try {
            int sides = Integer.parseInt(args[0]);
            ImageProcessing processedImage = new ImageProcessing(args[1], sides);
            ImageToAsciiArtConvertor convertor = new ImageToAsciiArtConvertor(processedImage.getSide());
            String text = convertor.getAsciiArt(processedImage.getImage(), mode + 1);
            if (saveAsImage)
                new RenderAndSaveTextAsImage(processedImage.getSide()).save(text);
            System.out.println(text);
            if (popupImage)
                new ImageShower(processedImage.getImage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Pixels should be an integer.");
        }
    }

    private static void parseArguments(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        args = parseOptionsAndSetStates(args);

        if (args.length == 0 || args.length > 2) {
            printUsage();
            return;
        }
        if (args.length == 1) {
            printAndPerformActionsAfterScalingWithDefaultPixelCount(args[0]);
            return;
        }

        // args length = 2;
        printAndPerformActionsAfterScaling(args);

    }
}