package soft.virtual;

import java.awt.GraphicsEnvironment;

public class AvailableFontLister {

    public static void main(String args[]) {
        System.out.println("****************************");
        System.out.println("**Listing Fonts Using Method From GraphicsEnvironment Class**");
        listUsingGraphicsEnvironment();

    }

    /**
     * Prints a list of all available fonts from the local graphics environment.
     *
     * The output list varies from manchine to machine
     */
    public static void listUsingGraphicsEnvironment() {
        GraphicsEnvironment ge = null;

        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] fontNames = ge.getAvailableFontFamilyNames();

        for (int i = 0; i<fontNames.length;i++) {
            System.out.println(fontNames[i]);
        }
    }

}
