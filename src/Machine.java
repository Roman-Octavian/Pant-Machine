import java.util.ArrayList;
import java.util.Scanner;

public class Machine {

    private double totalMonetaryAmount;

    /**
     * Stores our Container objects inside
     * Allows us to see the current status of the recycling process at any time
     */
    private ArrayList<Container> containerList = new ArrayList<>();

    //Getters and setters
    public void setTotalMonetaryAmount(double totalAmount) {
        this.totalMonetaryAmount = totalAmount;
    }

    public ArrayList<Container> getContainerList() {
        return containerList;
    }

    /**
     * The three following functions add a new container object to the containerList
     * Must determine container type first
     */
    public void createPantAContainer() {
        PantA a = new PantA(0,1.00);
        getContainerList().add(a);
    }
    public void createPantBContainer() {
        PantB b = new PantB(1,1.50);
        getContainerList().add(b);
    }
    public void createPantCContainer() {
        PantC c = new PantC(2,3.00);
        getContainerList().add(c);
    }

    /**
     * Finds out what type of container (Pant A, B or C) we are inserting with user input according to the project requirements
     * Once the container type has been determined, it also adds the new container to containerList with one of the three functions above
     */
    public void determineContainerType() {
        Scanner sc = new Scanner(System.in);

        System.out.println("INSERT CONTAINER CAPACITY IN LITERS:");
        double tempCap;
        while (true) {
            try {
                tempCap = Double.parseDouble(sc.nextLine());
                break;
            }
            catch (Exception e) {
                System.out.println("Invalid input");
            }
        }

        System.out.println("IS IT A PLASTIC BOTTLE?\n1. NO\n2. YES");
        int tempException;
        boolean isPlastic = false;
        while (true) {
            try {
                tempException = Integer.parseInt(sc.nextLine());
                if (tempException == 2) {
                    isPlastic = true;
                }
                break;
        }
            catch (Exception e) {
                System.out.println("Invalid input");
            }
        }

        if (!isPlastic) {
            if (tempCap > 0.00 && tempCap <= 1.00) {
                createPantAContainer();
            }
            else if (tempCap > 1.00 && tempCap <= 20.00) {
                createPantCContainer();
            }
            else {
                System.out.println("Invalid container size.");
                UI.sleep(1500);
            }
        }
        else {
            if (tempCap > 0.00 && tempCap < 0.50) {
                createPantAContainer();
            }
            else if (tempCap == 0.50) {
                createPantBContainer();
            }
            else if (tempCap >= 1.00 && tempCap <= 20.00) {
                createPantCContainer();
            }
            else {
                System.out.println("Invalid container size.");
                UI.sleep(1500);
            }
        }
    }

    /**
     * Keeps track of how many of each container is being stored in containerList
     * Allows us to update the counter everytime a new container is added
     */
    public int[] getPantStatus() {
        int a = 0;
        int b = 0;
        int c = 0;
        int[] result = new int[3];
        for (Container i: getContainerList()) {
            if (i.getType() == 0) {
                a++;
            }
            else if (i.getType() == 1) {
                b++;
            }
            else if (i.getType() == 2) {
                c++;
            }
        }
        result[0] = a;
        result[1] = b;
        result[2] = c;
        return result;
    }

    /**
     * Returns the amount of funds obtained by recycling
     */
    public double getFinalAmount() {
        totalMonetaryAmount = 0.00;
        for (Container i: getContainerList()) {
            totalMonetaryAmount += i.getValue();
        }
        return totalMonetaryAmount;
    }

    /**
     * Prints out a counter with how many of each type of containers has been inserted in the machine
     * Also states the current amount of funds to be obtained by the user
     */
    public void printPantStatus() {
        System.out.println(
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Pant A --> " + getPantStatus()[0] +
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t================================" +
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Pant B --> " + getPantStatus()[1] +
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t================================" +
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Pant C --> " + getPantStatus()[2]);
        System.out.println(
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t++++++++++++++++++++++++++++++++" +
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tAMOUNT TO BE PAID OUT: " + getFinalAmount() + " DKK" +
                        "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t++++++++++++++++++++++++++++++++\n");
    }

    /**
     * Resets the machine after a receipt has been printed
     */
    public void resetMachine() {

        setTotalMonetaryAmount(0.00);
        containerList.clear();
    }

    /**
     * Uses all the functions above and in other classes to run the machine
     */
    boolean running = true;
    public void runMachine() {
        while (running) {
            Scanner sc = new Scanner(System.in);
                UI.printLogo();
                printPantStatus();
                setTotalMonetaryAmount(0.00);
                UI.printMenu();

            switch (sc.nextLine()) {
                case "1":
                    determineContainerType();
                    UI.clearScreen();
                    break;
                case "2":
                    if (getFinalAmount() > 0) {
                        UI.printReceipt(getFinalAmount(), getPantStatus());
                        resetMachine();
                    }
                    else {
                        System.out.println("Recycle something before you cash in!\n\nInsert anything to go back.");
                        Scanner wait = new Scanner(System.in);
                        wait.nextLine();
                    }
                    UI.clearScreen();
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
                    UI.clearScreen();
                    break;
            }
        }
    }
}
