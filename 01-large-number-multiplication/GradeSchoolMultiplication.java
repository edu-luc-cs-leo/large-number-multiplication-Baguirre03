import java.util.Arrays;

public class GradeSchoolMultiplication {
    public static void main(String[] args) {
        int[] x = {1,2,3,4};
        int[] y = {5,6,7,8};
        int base = 10;
        int[] product = multiply(x,y,base);
        System.out.println(Arrays.toString(product));
    }
    private static final int DEFAULT_BASE = 10;

    public static int[] multiply(final int[] x, final int[] y, final int base) {
        int indents = 0;
        int curYIndex = y.length - 1;
        int addZeros = 1;
        int multiples[] = new int[y.length];

        // Grab each digit of Y array and multiple in X array.
        while(indents <= y.length - 1) {
            // multiply each digit in bottom row by each digit in top row.
            int curY = y[curYIndex];
            int carry = 0;
            int rowResult = 0;
            int multiply = 1;
            for(int i = x.length - 1 ; i >= 0; i--) {
                int num = x[i] * curY;
                num += carry;
                carry = num / 10;
                num = num % 10;

                // increase needed trackers
                rowResult += num * multiply;
                multiply *= 10;
            }
            // add a zero depending on what digit we are multiplying the top row by
            rowResult *= addZeros;

            // push into the multiples that we will add together
            multiples[indents] = rowResult;

            // increase or decrease needed trackers
            indents++;
            // decrease y index - 1 to grab new number to multiply by x..
            curYIndex--;
            // this is for if we are the second round of digit multiplication, there is an indent for when added
            addZeros *= 10;
        }

        // now do digit addition on the numbers that we have.
        int res[] = addNumbers(multiples);
        return res;
    } // method multiply

    public static int[] addNumbers(final int[] arr) {
        int longest = arr[arr.length - 1]; // grab the longest Number
        int longestLength = (int)(Math.log10(longest)); // get the length of the longest number for our result array.
        int res[] = new int[longestLength + 1]; // create res
        int carry = 0; // carru variable

        // loop through backwards so the number is in correct direction
        // create a number which stores the addition of each row
        for(int i = longestLength; i >= 0; i--) {
            int curAddition = 0;
            // loop through array of multiples to add into our current addition
            for(int j = 0; j < arr.length; j++) {
                int add = arr[j] % 10;
                arr[j] = arr[j] / 10;
                curAddition += add;
            }
            // grab the last digit and add carry. push digit into result! 
            curAddition += carry;
            carry = curAddition / 10;
            curAddition = curAddition % 10;
            res[i] = curAddition;
        }

        return res;
    }

    public static int[] multiply(final int[] x, final int[] y) {
        return multiply(x, y, DEFAULT_BASE);
    } // method multiply
}