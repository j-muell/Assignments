import java.io.FileNotFoundException;
import java.io.IOException;

public class FindPath {
    Map pyramidMap;

    public FindPath(String fileName) {
        try {
            pyramidMap = new Map(fileName);

        } catch (InvalidMapCharacterException e) {
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public DLStack<Chamber> path() {

        DLStack<Chamber> pathStack = new DLStack<>();
        int numTreasures = getMap().getNumTreasures();
        Chamber startChamber = getMap().getEntrance();
        int treasureCounter = 0;

        pathStack.push(startChamber);
        startChamber.markPushed();

        while (pathStack != null) {
            // DLStack<Chamber> temp = pathStack;

            Chamber curr = pathStack.peek();
            if (curr.isTreasure() && treasureCounter == numTreasures) break;

            Chamber bestMove = bestChamber(curr);
            if (bestMove != null) {
                pathStack.push(bestMove);
                bestMove.markPushed();
                if (bestMove.isTreasure()) treasureCounter++;
            } else {
                Chamber poppy = pathStack.pop();
                poppy.markPopped();
            }

        }

        return pathStack;

    }

    public Map getMap() {
        return pyramidMap;
    }

    public boolean isDim(Chamber currentChamber) {
        if (currentChamber != null && currentChamber.isSealed() == false && currentChamber.isLighted() == false) { // checks for the proper conditions
            for (int i = 0; i <= 5; i++) { // for 0-5 check the neighbour champber at that position and see if it is lighted. if it is, reutrn true for dim.
                if (currentChamber.getNeighbour(i).isLighted()) return true;
            }
        } 

        return false; // if even  one of these does not pass, return false.
        
    }

    public Chamber bestChamber(Chamber currentChamber) {
        // need unmarked and has treasure, or move to lighted or dim chamber. if none, return null
        int numOfNeighbours = 5;

        
        for (int i = 0; i <= numOfNeighbours; i++) { // for loop to test all neighbouring chambers

            if (currentChamber.getNeighbour(i) == null) continue;

            if (currentChamber.getNeighbour(i).isMarked() == false && (currentChamber.getNeighbour(i).isTreasure())) { // testing for treasure
                return currentChamber.getNeighbour(i);
            } 

            if (currentChamber.getNeighbour(i).isMarked() == false && (currentChamber.getNeighbour(i).isLighted())) { // testing for lighted
                return currentChamber.getNeighbour(i);
            }

            boolean dimTest = isDim(currentChamber.getNeighbour(i));
            if (currentChamber.getNeighbour(i).isMarked() == false && (dimTest == true)) { // testing for a dim chamber
                return currentChamber.getNeighbour(i);
            }

            continue; // if hit nothing, move to next chamber
        }

        return null; // if there is no chamber with the specifications needed, return null.

    }

}
