/*              07.10.2023          */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Robot {
    private int direction = 0, moves = 0, target = -1;
    private int[] coordinates = {0, 0};
    private List<int[]> uniqueCoords = new ArrayList<>();

    public Robot(String command) {
        for (int i = 0; i < command.length(); i++) {
            boolean outp = interpret(command.charAt(i));
            if (outp)
                break;
        }
        System.out.println(target);
    }

    public boolean interpret(char c) {
        switch (c) {
            case 'L', 'R':
                turn(c);
                break;
            case 'S':
                move();
                if (target > -1)
                    return true;
                break;
        }
        return false;

    }

    public void move(){
        switch (direction) {
            case 0:
                coordinates[1]++;
                break;
            case 1:
                coordinates[0]++;
                break;
            case 2:
                coordinates[1]--;
                break;
            case 3:
                coordinates[0]--;
                break;
        }
        moves++;
        if (uniqueCoords.stream().anyMatch(arr -> Arrays.equals(arr, coordinates))) {
            target = moves;
        } else {
            uniqueCoords.add(coordinates.clone());
        }

    }
    private void turn(char c){
        if (c == 'L')
            direction--;
        else if (c=='R')
            direction++;
     if (direction > 3)
        direction = 0;
     else if (direction < 0)
        direction = 3;


    }



    public static void robotMovement(String robotCommand) {
        int outp = -1;
        int moves = 0;
        int[] coordinates = new int[2];
        int direction = 0;
        List<int[]> uniqueCoords = new ArrayList<int[]>();
        uniqueCoords.add(coordinates.clone());
        for (int i = 0; i < robotCommand.length(); i++) {
            switch (robotCommand.charAt(i)) {
                case 'L':
                    direction--;
                    break;
                case 'R':
                    direction++;
                    break;
                case 'S':
                    switch (direction) {
                        case 0:
                            coordinates[1]++;
                            break;
                        case 1:
                            coordinates[0]++;
                            break;
                        case 2:
                            coordinates[1]--;
                            break;
                        case 3:
                            coordinates[0]--;
                            break;
                    }
                    moves++;
                    if (uniqueCoords.stream().anyMatch(arr -> Arrays.equals(arr, coordinates))) {
                        outp = moves;
                        break;
                    } else {
                        uniqueCoords.add(coordinates.clone());
                    }

            }
            if (outp != -1){
                break;
            }
            if (direction > 3) {
                direction = 0;
            } else if (direction < 0) {
                direction = 3;
            }
        }System.out.println(outp);
        //return outp;
    }
}
