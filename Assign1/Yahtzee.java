package Assign1;

import java.util.Arrays;

public class Yahtzee {
    Dice[] dice;

    // MAIN METHODS / PUBLIC METHODS

    public Yahtzee(){
        dice = new Dice[5];
        for (int i = 0; i < 5; i++){
            dice[i] = new Dice();
            dice[i].roll();
        }
    }

    public Yahtzee(Dice[] diceArray){
        this.dice = new Dice[5];
        this.dice = diceArray;
    
    }

    public int[] getValueCount(){ // Gets the value of each number from the dice that is rolled by accessing the dice object in the array, using getValue method.
        int ones=0, twos=0, threes=0, fours=0, fives=0, sixes=0;
        for (int i=0; i <= 4; i++) {
            if (dice[i].getValue() == 1){
                ones++;
            } else if (dice[i].getValue() == 2){
                twos++;
            } else if (dice[i].getValue() == 3){
                threes++;
            } else if (dice[i].getValue() == 4){
                fours++;
            } else if (dice[i].getValue() == 5){
                fives++;
            } else if (dice[i].getValue() == 6){
                sixes++;
            }
        }

        int finalArray[] = {ones, twos, threes, fours, fives, sixes};
        return finalArray;

    }

    public int[] getScoreOptions(){ // This is going to determine all of the possible scoring opportunities from the dice that were rolled.
        int[] scoreArray = new int[13];
        // First section is going to handle scrictly the number scoring (upper section of a yahtzee card)
        int[] numberCounts = getValueCount();

        // Updating the scoreArray list with the values

        scoreArray[0] = numberCounts[0]; // 1's. This section will use the correct part of the array and set the score values depending on the numberCounts that were found.
        scoreArray[1] = 2 * numberCounts[1]; // 2's
        scoreArray[2] = 3 * numberCounts[2]; // 3's
        scoreArray[3] = 4 * numberCounts[3]; // 4's
        scoreArray[4] = 5 * numberCounts[4]; // 5's
        scoreArray[5] = 6 * numberCounts[5]; // 6's

        int[] complexScoreArray = complexScoring(); //This runs the complex scoring method which will be assigning a new array with the results.

        // Lower half of the scoreArray

        scoreArray[6] = complexScoreArray[0]; // 3 of a kind
        scoreArray[7] = complexScoreArray[1]; // 4 of a kind
        scoreArray[8] = complexScoreArray[2]; // full house
        scoreArray[9] = complexScoreArray[3]; // small straight
        scoreArray[10] = complexScoreArray[4]; // large straight
        scoreArray[11] = complexScoreArray[5]; // yahtzee
        scoreArray[12] = complexScoreArray[6]; // chance


        // System.out.println(showArray(scoreArray));

        return scoreArray;
    }

    public int[] score(){
        int[] maxAndIndex = new int[2];
        maxAndIndex[0] = 0; // placement for the maximum score
        int maxIndex = 0;

        int[] scoreOptions = getScoreOptions();

        for (int i = 0; i < scoreOptions.length; i++){
            if (scoreOptions[i] > maxAndIndex[0]){ //if the number we are on is larger than our current max, set our max to that number
                maxAndIndex[0] = scoreOptions[i];
                maxIndex = i; // set our maxIndex value to whatever i we were at
            }
        }
    
        maxAndIndex[1] = maxIndex;

        return maxAndIndex;
    }

    public boolean equals(Yahtzee otherObj){
        int[] thisDiceNumbers = this.diceNumbers(this); // Send in this current object for diceNumbers
        int[] otherDiceNumbers = otherObj.diceNumbers(otherObj); // Send in otherObj as the object for diceNumbers

        Arrays.sort(thisDiceNumbers); // Here i sort both arrays. If they have the same numbers, the sorted array will alwaays be the exact same.
        Arrays.sort(otherDiceNumbers);

        if (Arrays.equals(thisDiceNumbers, otherDiceNumbers)){ // using the util of Arrays, we can simply test the equality by sending both arrays in.
            return true;
        }

        return false;
    }

    public String toString(){
        int[] diceNumbers = diceNumbers(this);
        String diceOutput = Arrays.toString(diceNumbers).replace("[", "").replace("]", ""); // Use Arrays import to make the toString method and replace the brackets with none.

        return ("Dice: {" + diceOutput + "}");
    }

    // HELPER METHODS / PRIVATE METHODS


    private int[] complexScoring(){ // Creating an array of 7 to deal with all of the more complex methods and assign it all appropropriately.
        int[] complexArray = new int[7];
        complexArray[0] = threeOfAKind();
        complexArray[1] = fourOfAKind();
        complexArray[2] = fullHouse();
        complexArray[3] = smallStraight();
        complexArray[4] = largeStraight();
        complexArray[5] = yahtzee();
        complexArray[6] = chance();
        

        return complexArray;
    }

    private int threeOfAKind(){ // Testing for three same numbers from the dice
        int[] numberCounts = getValueCount();
        int total_points = 0;
        int[] ogDiceNums = diceNumbers(this);

        for (int i=0; i <= 5; i++){
            if (numberCounts[i] == 3 || numberCounts[i] == 4 || numberCounts[i] == 5){
                for (int x=0; x <= 4; x++){
                    total_points = total_points + ogDiceNums[x];
                }
                return total_points;
            } 
            
        }

        return total_points;
    }

    private int fourOfAKind(){ // Testing for four same numbers from the dice
        int[] numberCounts = getValueCount();
        int total_points = 0;
        int[] ogDiceNums = diceNumbers(this);

        for (int i=0; i <= 4; i++){
            if (numberCounts[i] == 4 || numberCounts[i] == 5){
                for (int x=0; x < ogDiceNums.length; x++){
                    total_points = total_points + ogDiceNums[x];
                }
                return total_points;
            } 
            
        }

        return total_points;
    }

    private int yahtzee(){ // Five same numbers from dice
        int[] numberCounts = getValueCount();
        int total_points = 50; //Yahtzee has set point return of 50.

        for (int i=0; i <= 4; i++){
            if (numberCounts[i] == 5){
                return total_points;
            } 
            
        }

        return 0; // no points if there is not 5 of the same number.
    }

    private int fullHouse() {
        int[] numberCounts = getValueCount();
        int total_points = 25; // Full house has fixed value.

        for (int i=0; i <= 4; i++) { //run through first time, check for 3 
            if (numberCounts[i] == 3){
                for (int x=0; x <= 4; x++) { // run through second time, check for 2
                    if (numberCounts[x] == 2){
                        return total_points; // if there is 3 and 2, then return the point value of 25
                    }
                }
            }
        }

        return 0; // If the branches above don't get accessed, then send 0 as we don't have a full house.
    }

    private int smallStraight() { //NOTE Small straight and large straight will use practically the same code.
        int counter = 0;
        int total_points = 0;
        boolean found = false;
        int[] diceNumbers = diceNumbers(this);
        Arrays.sort(diceNumbers);

        for (int i = 0; i < diceNumbers.length - 1; i++) {
            if (diceNumbers[i + 1] == diceNumbers[i] + 1){ // if the nicenumber position + 1 (next index) is same as the current value + 1, add to counter
                counter++;
            } else if (diceNumbers[i + 1] == diceNumbers[i]) { //if the next position is the same, we have duplicate number, so continue.
                continue;
            } else { 
                counter = 0; //in the event that none of this is true, our counter is 0.
            }

            if (counter == 3) { // counter reaching 3 means we have found a small straight!
                found = true;
                break;
            }
            
        }

        if (found) {
            total_points = 30;
        } else {
            total_points = 0;
        }

        return total_points;

    }

    private int largeStraight() {
        // USING EXACT SAME ALGORITHM. The only changes have a comment noting what it is.
        int counter = 0;
        int total_points = 0;
        boolean found = false;
        int[] diceNumbers = diceNumbers(this);
        Arrays.sort(diceNumbers);

        for (int i = 0; i < diceNumbers.length - 1; i++) {
            if (diceNumbers[i + 1] == diceNumbers[i] + 1){ 
                counter++;
            } else if (diceNumbers[i + 1] == diceNumbers[i]) { 
                continue;
            } else { 
                counter = 0;
            }

            if (counter == 4) { // counter changed to 4, meaning we would need a large straight!
                found = true;
                break;
            }
            
        }

        if (found) {
            total_points = 40; // points change to 40.
        } else {
            total_points = 0;
        }

        return total_points;

    }

    private int chance() {
        int total_points = 0;
        int[] ogDiceNumbers = diceNumbers(this);

        for (int i=0; i <= 4; i++) {
            total_points = total_points + ogDiceNumbers[i];
        }

        return total_points;
    }


    private int[] diceNumbers(Yahtzee yahtObj){ // This is getting the original dice numbers  from the rolls in order to add up numbers for the scoring.
        int[] originalDiceNumbers = new int[5];
        for (int i = 0; i <= 4; i++){
            originalDiceNumbers[i] = yahtObj.dice[i].getValue();
        }

        return originalDiceNumbers;
    }

}
