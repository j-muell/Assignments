package Assign1;


public class Dice {
    int value;

    public Dice(){
        value = -1;
    }

    public Dice(int num){
        value = num;
    }

    public void roll(){
        int num = RandomNumber.getRandomNumber(1, 6);
        value = num;
    }   

    public int getValue(){
        return value;
    }

}
