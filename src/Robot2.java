/*              07.10.2023          */

// Решение задачи "Робот К-79" с помощью матрицы
// https://acmp.ru/index.asp?main=task&id_task=235

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Robot2 {

    public static void main(String[] args){
        Robot2 r2 =new Robot2("LSSLLSSS");
    }

    private int direction = 0, moves = 0, target = -1;
    private boolean[][] coordinateMap = new boolean[101][101];
    private int[] robotCoordinate = {50,50};

    public Robot2(String command) {
        coordinateMap[50][50] = true;
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
                robotCoordinate[1]++;
                break;
            case 1:
                robotCoordinate[0]++;
                break;
            case 2:
                robotCoordinate[1]--;
                break;
            case 3:
                robotCoordinate[0]--;
                break;
        }

        if (coordinateMap[robotCoordinate[0]][robotCoordinate[1]]){
            target = moves;
        } else{
            moves++;
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