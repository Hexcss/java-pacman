import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        final int MAP_SIZE = 15;

        int move;
        int score = 0;

        String tiles[] = { "#", "*", "0" };
        String map[][] = new String[MAP_SIZE][MAP_SIZE];

        int x, y;
        int pacman[] = { Math.round(MAP_SIZE / 2), Math.round(MAP_SIZE / 2) };

        boolean run = true;

        // Storing the map
        for (x = 0; x < MAP_SIZE; x++) {
            for (y = 0; y < MAP_SIZE; y++) {
                System.out.print(map[x][y] = "");
            }
        }

        // Designing the map
        for (x = 0; x < MAP_SIZE; x++) {
            for (y = 0; y < MAP_SIZE; y++) {
                if (x == 0 || x == MAP_SIZE - 1) {
                    map[x][y] = tiles[0];
                } else if (y == 0 || y == 14) {
                    map[x][y] = tiles[0];
                } else if ((x == 5 || x == 9) && y > 4 && y < 10) {
                    map[x][y] = tiles[0];
                } else if ((x == 2 || x == MAP_SIZE - 3)
                        && ((y > 1 && y < 5) || (y > MAP_SIZE - 6 && y < MAP_SIZE - 2))) {
                    map[x][y] = tiles[0];
                } else if (x == MAP_SIZE - 2 && y == MAP_SIZE - 2) {
                    map[x][y] = tiles[2];
                } else {
                    map[x][y] = tiles[1];
                }
            }
        }

        while (run) {

            // Printing the game
            System.out.println("SCORE: " + score);

            map[pacman[0]][pacman[1]] = "@";
            for (x = 0; x < MAP_SIZE; x++) {
                for (y = 0; y < MAP_SIZE; y++) {
                    System.out.print(map[x][y] + " ");
                }
                System.out.println();
            }

            System.out.println("\nEnter a move: ");
            move = input.nextInt();

            if (move == 8) {
                pacman[0] -= 1;
                if (map[pacman[0]][pacman[1]] == tiles[0]) {
                    pacman[0] += 1;
                } else {
                    if (pacman[0] > 0) {
                        map[pacman[0] + 1][pacman[1]] = " ";
                    }
                }
            } else if (move == 2) {
                pacman[0] += 1;
                if (map[pacman[0]][pacman[1]] == tiles[0]) {
                    pacman[0] -= 1;
                } else {
                    if (pacman[0] > 0) {
                        map[pacman[0] - 1][pacman[1]] = " ";
                    }
                }
            } else if (move == 4) {
                pacman[1] -= 1;
                if (map[pacman[0]][pacman[1]] == tiles[0]) {
                    pacman[1] += 1;
                } else {
                    if (pacman[1] > 0) {
                        map[pacman[0]][pacman[1] + 1] = " ";
                    }
                }
            } else if (move == 6) {
                pacman[1] += 1;
                if (map[pacman[0]][pacman[1]] == tiles[0]) {
                    pacman[1] -= 1;
                } else {
                    if (pacman[1] > 0) {
                        map[pacman[0]][pacman[1] - 1] = " ";
                    }
                }
            }

            if (map[pacman[0]][pacman[1]] == tiles[1]) {
                score++;
            }

            if (map[pacman[0]][pacman[1]] == tiles[2]) {
                for (int i = 0; i < 20; i++) {
                    System.out.println();
                }
                System.out.println("\nFINISH!");
                System.out.println("\nFINAL SCORE: " + score);
                run = false;
            }
        }

        input.close();
    }
}
