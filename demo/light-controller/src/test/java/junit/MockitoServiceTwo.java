package junit;

import java.util.Random;

public class MockitoServiceTwo {

    public int getDemoStatus(){
        return new Random().nextInt();
    }

    public static void main(String[] args) {

    }
}

