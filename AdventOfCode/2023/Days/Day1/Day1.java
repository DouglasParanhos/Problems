package Days.Day1;

import Days.Day;

public final class Day1 extends Day {

    public Day1() {
        super();
        getInput("day1.txt");
    }


    public String exec(){

        return this.inputLines.get(0);
    }
}
