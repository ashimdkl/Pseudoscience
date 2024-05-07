import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * No argument constructor--private to prevent instantiation.
     */
    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.println("please enter a number that is positive and real.");
        String userInput = in.nextLine(); // stores user input string var

        while (!(FormatChecker.canParseDouble(userInput)
                && Double.parseDouble(userInput) > 0)) {
            // above conditions checks for when the val is NOT a string or
            // negative number.

            out.println("please enter a number that is positive and real.");
            userInput = in.nextLine();
        }
        return Double.parseDouble(userInput);
        // program runs till a positive double val is entered then returns.
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.println("please enter a number that is positive and real.");
        String userInput = in.nextLine();
        // same thing as above, stores as string.
        while (!(FormatChecker.canParseDouble(userInput)
                && Double.parseDouble(userInput) > 0
                && Double.parseDouble(userInput) != 1.0)) {
            // checks same conditions as previous method but ensures
            // userInput isnt 1.0
            out.println(
                    "please enter a number that is positive and real and NOT "
                            + "equal to 1.0");
            userInput = in.nextLine();
        }
        return Double.parseDouble(userInput);
        // returns userInput that meets criteria.

    }

    /**
     * calculates the error in the method.
     *
     * @param mu
     * @param sum
     * @return the error found by the formula below.
     */
    private static double userErrorCalculator(double mu, double sum) {
        return (Math.abs(mu - sum) / mu);
    }

    /**
     * calculates the sum in the method.
     *
     * @param wPowValue
     * @param xPowValue
     * @param yPowValue
     * @param zPowValue
     * @return the total "sum" which is just the product of the 4 exponents.
     */
    private static double sumCalculator(double wPowValue, double xPowValue,
            double yPowValue, double zPowValue) {
        return (wPowValue * xPowValue * yPowValue * zPowValue);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        // these 4 variables are retrieved from our method above.

        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        double minimumError = Double.MAX_VALUE;

        out.println("Please enter the constant value for mu.");
        final double mu = getPositiveDouble(in, out);
        // prompt user to enter a val for mu and store it.

        double closestToMu = 0;
        double bestA = 0;
        double bestB = 0;
        double bestC = 0;
        double bestD = 0;
        // initialize variables for bestA,B,C,D at 0, so we can increment.

        double[] givenArrayList = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3,
                -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };
        // given array list above ^

        // all the incrementing and int'ing is done inside for-loop.
        for (int i = 0; i < givenArrayList.length; i++) {
            double wPowValue = Math.pow(w, givenArrayList[i]);
            // just gets all the powers of w^[i] in the array, and stores.

            for (int j = 0; j < givenArrayList.length; j++) {
                double xPowValue = Math.pow(x, givenArrayList[j]);
                // just gets all the powers of x^[i] in the array, and stores.

                for (int k = 0; k < givenArrayList.length; k++) {
                    double yPowValue = Math.pow(y, givenArrayList[k]);
                    // just gets all the powers of y^[i] in the array,
                    // and stores.

                    for (int l = 0; l < givenArrayList.length; l++) {
                        double zPowValue = Math.pow(z, givenArrayList[l]);
                        // just gets all the powers of z^[i] in the array,
                        // and stores.

                        double sum = sumCalculator(wPowValue, xPowValue,
                                yPowValue, zPowValue);

                        // multiples all of those vals here so we have product.

                        double userError = userErrorCalculator(mu, sum);
                        // formula to find the error % above ^

                        if (userError < minimumError) {
                            // if the error is less than the min val,replaces.

                            minimumError = userError;
                            closestToMu = sum;
                            // getting all the best vals and storing below.
                            bestA = givenArrayList[i];
                            bestB = givenArrayList[j];
                            bestC = givenArrayList[k];
                            bestD = givenArrayList[l];
                        }
                    }
                }
            }
        }

        out.println("The best value that is closest to Mu is " + closestToMu);
        out.println("The best value for exponent-a is " + bestA);
        out.println("The best value for exponent-b is " + bestB);
        out.println("The best value for exponent-c is " + bestC);
        out.println("The best value for exponent-d is " + bestD);

        out.print("The error is ");
        out.print(minimumError * 100, 2, false);
        out.print("%");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
