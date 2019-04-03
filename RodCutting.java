/**
 * Name: Adam vanWestrienen
 * Rod cutting problem described in Chapter 15 of textbook
 * 
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
    
    // base case
    if (rodLength == 0) {
      return 0;
    }
    // set max to lowest int value
    int max = Integer.MIN_VALUE;

    // loop through lengths updating max by calling rodCutting recursively
    for (int i = 0; i < rodLength; i++) {
      max = Math.max(max, lengthPrices[i] + rodCuttingRecur(rodLength-i-1, lengthPrices));
    }
    return max;
  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
    
    // setup an array to store values and initialize to 0
    int array[] = new int[rodLength + 1];
    array[0] = 0;

    // loop through the lengths starting at 1 
    for (int i = 1; i <= rodLength; i++) {
      int max = Integer.MIN_VALUE;
      // loop through prices taking the maximum current and previous prices. update and store in array 
      for (int j = 0; j < i; j++) {
        max = Math.max(max, lengthPrices[j] + array[i-j-1]);
      }
      array[i] = max;
    }
    return array[rodLength];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
