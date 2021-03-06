/**
  Name: Adam vanWestrienen
  Glass Falling

 */
public class GlassFalling {

 // Do not change the parameters!
 public int glassFallingRecur(int floors, int sheets) {
    
  // base cases
    if (floors == 0 || floors == 1 || sheets == 1) {
      return floors;
    }

  int minDrops = -1;
  int minDropFloor = -1;
  for (int currentFloor = 1; currentFloor < floors; currentFloor++) {
    // glass shattered at floor f so we look only at floors below this floor
    int glassShattered = glassFallingRecur(currentFloor-1, sheets-1);
    // glass did not shatter so we look at floors above this floor.
    int glassGood = glassFallingRecur(floors-currentFloor, sheets);

    // get the highest value at each floor and pick the min of each
    int maxDropsAtFloor = Math.max(glassGood, glassShattered);
    if (minDrops == -1 || minDrops > maxDropsAtFloor) {
      minDrops = maxDropsAtFloor;
      minDropFloor = currentFloor;
    }
  }
  // add one to the result for the bottom floor
  return minDrops + 1;
}

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized() {
    // Fill in here and change the return
    return 0;
  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
 
    // initialize table to store values
    int table[][] = new int[sheets + 1][floors + 1];

    // with one sheet we have as many drops as there are floors
    for (int currentFloor = 0; currentFloor <= floors; currentFloor++) {
      table[1][currentFloor] = currentFloor;
    }

    // drops are fixed for floors 0 and 1
    for (int s = 1; s < sheets; s++) {
      table[s][0] = 0;
      table[s][1] = 1;
    }

    int glassGood;
    int glassShattered;
    // 1 sheet to total sheets available, start from floor 2 and fill in table using bottom up approach
    for (int s = 1; s <= sheets; s++) {
      for (int currentFloor = 2; currentFloor <= floors; currentFloor++) {
        int minDrops = -1;
        for (int i=1; i <= currentFloor; i++) {
          // glass shattered so move down and test
          glassShattered = table[s-1][i-1];
          // glass survived the drop so move up and test
          glassGood = table[s][currentFloor - i];
          int maxDropsAtFloor = 1 + Math.max(glassShattered,glassGood);
            minDrops = maxDropsAtFloor;
            table[s][currentFloor] = minDrops;
          }
        }
      }
    
      return table[sheets][floors];
    }

  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}
