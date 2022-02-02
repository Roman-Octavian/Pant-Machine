import java.util.Scanner;

public class UI {

    private final static String logo =
            "\n######     #    #     # #######    #     #    #     #####  #     # ### #     #    #    ####### ####### ######      #####    ###     ###     ###   \n" +
            "#     #   # #   ##    #    #       ##   ##   # #   #     # #     #  #  ##    #   # #      #    #     # #     #    #     #  #   #   #   #   #   #  \n" +
            "#     #  #   #  # #   #    #       # # # #  #   #  #       #     #  #  # #   #  #   #     #    #     # #     #          # #     # #     # #     # \n" +
            "######  #     # #  #  #    #       #  #  # #     # #       #######  #  #  #  # #     #    #    #     # ######      #####  #     # #     # #     # \n" +
            "#       ####### #   # #    #       #     # ####### #       #     #  #  #   # # #######    #    #     # #   #      #       #     # #     # #     # \n" +
            "#       #     # #    ##    #       #     # #     # #     # #     #  #  #    ## #     #    #    #     # #    #     #        #   #   #   #   #   #  \n" +
            "#       #     # #     #    #       #     # #     #  #####  #     # ### #     # #     #    #    ####### #     #    #######   ###     ###     ###   \n\n";

    public static void printLogo() {
        System.out.println(logo);
    }
    /**
     * Prints out the three options available in the main menu of the recycling machine
     */
    public static void printMenu() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t1. INSERT NEW CONTAINER" +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t2. CASH IN" +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t3. EXIT");
    }
    /**
     * Platform-independent function that clears the screen on the Java console
     * Originally written by Bartosz Biryło for the first semester final project (repurposed)
     * Works by printing a large amount of characters that are removed by a plugin
     */
    public static void clearScreen() {
        try {
            System.out.print("clear");
            if (!System.getProperty("java.class.path").contains("idea_rt.jar")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                if (System.getProperty("os.name").contains("Windows")) {
                    Runtime.getRuntime().exec("cls");
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            }
        } catch (final Exception e) {
            //  Handle any exceptions
        }
    }

    /**
     * Adds one second of delay for aesthetic purposes
     * @param ms amount of delay in milliseconds
     * @throws IllegalArgumentException if {@code ms} is negative
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Prints out a receipt once all the containers have been recycled
     * @param total totalMonetaryAmount goes here
     * @param pants the getPantStatus() function is used here to get the amount of each pant container recycled
     */
    public static void printReceipt(double total, int[] pants) {
        clearScreen();
        System.out.println(
                "\t\t\t\t\t\t\t\t\t\t\t\t<=============================================================>" +
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t<=============================================================>");
        sleep(750);
        System.out.println(
                "\t\t\t\t\t\t\t\t\t\t\t\t                                     __            __     \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t                                    /  |          /  |    \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t  ______   ______   _______  ______ $$/  ______  _$$ |_   \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t /      \\ /      \\ /       |/      \\/  |/      \\/ $$   |  \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t/$$$$$$  /$$$$$$  /$$$$$$$//$$$$$$  $$ /$$$$$$  $$$$$$/   \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t$$ |  $$/$$    $$ $$ |     $$    $$ $$ $$ |  $$ | $$ | __ \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t$$ |     $$$$$$$$/$$ \\_____$$$$$$$$/$$ $$ |__$$ | $$ |/  |\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t$$ |     $$       $$       $$       $$ $$    $$/  $$  $$/ \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t$$/       $$$$$$$/ $$$$$$$/ $$$$$$$/$$/$$$$$$$/    $$$$/  \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t                                       $$ |               \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t                                       $$ |               \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t                                       $$/                ");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tPANT A: " + pants[0]);
        sleep(750);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tPANT B: " + pants[1]);
        sleep(750);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tPANT C: " + pants[2]);
        sleep(750);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tTOTAL AMOUNT: " + total + " DKK");
        System.out.println("\n\n");
        System.out.println(
                "\t\t\t\t\t\t\t\t\t\t\t\t<=============================================================>" +
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t<=============================================================>");
        System.out.println("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\tInsert anything to go back");
        Scanner wait = new Scanner(System.in);
        wait.nextLine();
    }
}
