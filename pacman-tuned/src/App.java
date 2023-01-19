import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class App {

    static final int MAP_SIZE = 15;
    static final int PAC_MAN_SPEED = 1;
    static final int GHOSTS_SPEED = 1;

    public static final String YELLOW = "\033[1;93m";
    public static final String BLUE = "\033[1;34m";
    public static final String RED = "\u001b[31;1m";
    public static final String WHITE = "\u001b[37;1m";
    public static final String GREEN = "\033[0;32m";
    public static final String PURPLE= "\033[1;35m";

    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) throws Exception {

        boolean run = true;
        String tiles[] = { BLUE + "#" + RESET,  WHITE + "*" + RESET, GREEN + "0" + RESET, " " };
        String map[][] = new String[MAP_SIZE][MAP_SIZE];

        runGame(run, map, tiles);
    }

    static void runGame(boolean run, String[][] map, String[] tiles) {

        int score = 0;
        int pacMan[] = {Math.round(MAP_SIZE / 2), Math.round(MAP_SIZE / 2)};
        int ghosts[][] = { {1, 1}, {1, MAP_SIZE - 2}, {MAP_SIZE - 2, 1} };

        createMap(map, tiles);

        while (run) {

            printScore(score);
            printPacMan(map, pacMan);
            printGhosts(map, ghosts);
            printMap(map);

            handlePacManMovement(map, tiles, pacMan);
            handleGhostsMovements(map, tiles, ghosts);

            score = handleScore(map, tiles, pacMan, score);
            run = handleShouldGameRun(map, tiles, ghosts, pacMan);
        }

        handleGameOver(score);
    }

    static void createMap(String[][] map, String[] tiles) {
        int x, y;

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
                } else if (x > 5 && x < 9 && (y == 2 || y == 3 || y == 11 || y == 12)) {
                    map[x][y] = tiles[0];
                } else {
                    map[x][y] = tiles[1];
                }
            }
        }
    }

    static void printScore(int score) {
        System.out.println(RED + "\nSCORE: " + score + RESET);
    }

    static void printPacMan(String[][] map, int pacMan[]) {
        map[pacMan[0]][pacMan[1]] = YELLOW + "@" + RESET;
    }

    static void printGhosts(String[][] map, int[][] ghosts) {
        for (int[] ghost :ghosts) {
            map[ghost[0]][ghost[1]] = PURPLE + "O" + RESET;
        }
    }

    static void printMap(String[][] map) {
        int x, y;

        for (x = 0; x < MAP_SIZE; x++) {
            for (y = 0; y < MAP_SIZE; y++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }

    static void handlePacManMovement(String[][] map, String[] tiles, int[] pacMan) {

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter a move: ");
        int move = input.nextInt();

        if (move == 8) {
            pacMan[0] -= PAC_MAN_SPEED;
            if (map[pacMan[0]][pacMan[1]] == tiles[0]) {
                pacMan[0] += PAC_MAN_SPEED;
            } else {
                if (pacMan[0] > 0) {
                    map[pacMan[0] + 1][pacMan[1]] = tiles[3];
                }
            }
        } else if (move == 2) {
            pacMan[0] += PAC_MAN_SPEED;
            if (map[pacMan[0]][pacMan[1]] == tiles[0]) {
                pacMan[0] -= PAC_MAN_SPEED;
            } else {
                if (pacMan[0] > 0) {
                    map[pacMan[0] - 1][pacMan[1]] = tiles[3];
                }
            }
        } else if (move == 4) {
            pacMan[1] -= PAC_MAN_SPEED;
            if (map[pacMan[0]][pacMan[1]] == tiles[0]) {
                pacMan[1] += PAC_MAN_SPEED;
            } else {
                if (pacMan[1] > 0) {
                    map[pacMan[0]][pacMan[1] + 1] = tiles[3];
                }
            }
        } else if (move == 6) {
            pacMan[1] += PAC_MAN_SPEED;
            if (map[pacMan[0]][pacMan[1]] == tiles[0]) {
                pacMan[1] -= PAC_MAN_SPEED;
            } else {
                if (pacMan[1] > 0) {
                    map[pacMan[0]][pacMan[1] - 1] = tiles[3];
                }
            }
        }
    }

    static void handleGhostsMovements(String[][] map, String[] tiles, int[][] ghosts) {
        for (int[] ghost : ghosts) {
            int move = randomNumber(1, 4);

            if (move == 1) {
                ghost[0] -= GHOSTS_SPEED;
                if (map[ghost[0]][ghost[1]] == tiles[0]) {
                    ghost[0] += GHOSTS_SPEED;
                } else {
                    if (ghost[0] > 0) {
                        map[ghost[0] + 1][ghost[1]] = tiles[1];
                    }
                }
            } else if (move == 2) {
                ghost[0] += GHOSTS_SPEED;
                if (map[ghost[0]][ghost[1]] == tiles[0]) {
                    ghost[0] -= GHOSTS_SPEED;
                } else {
                    if (ghost[0] > 0) {
                        map[ghost[0] - 1][ghost[1]] = tiles[1];
                    }
                }
            } else if (move == 3) {
                ghost[1] -= GHOSTS_SPEED;
                if (map[ghost[0]][ghost[1]] == tiles[0]) {
                    ghost[1] += GHOSTS_SPEED;
                } else {
                    if (ghost[1] > 0) {
                        map[ghost[0]][ghost[1] + 1] = tiles[1];
                    }
                }
            } else if (move == 4) {
                ghost[1] += GHOSTS_SPEED;
                if (map[ghost[0]][ghost[1]] == tiles[0]) {
                    ghost[1] -= GHOSTS_SPEED;
                } else {
                    if (ghost[1] > 0) {
                        map[ghost[0]][ghost[1] - 1] = tiles[1];
                    }
                }
            }
        }
    }

    static int handleScore(String[][] map, String[] tiles, int[] pacMan, int score) {
        if (map[pacMan[0]][pacMan[1]] == tiles[1]) {
            return score += 1;
        }

        return score;
    }   

    static boolean handleShouldGameRun(String[][] map, String[] tiles, int[][] ghosts, int[] pacMan) {
        if (map[pacMan[0]][pacMan[1]] == tiles[2]) {
            return false;
        } 

        for (int[] ghost : ghosts) {
            if (Arrays.equals(ghost, pacMan)) {
                return false;
            }
        }

        return true;
    }

    static void handleGameOver(int score) {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }

        System.out.println(WHITE + "\nFINISH!" + RESET);
        System.out.println(RED + "\nFINAL SCORE: " + score + RESET);
    }

    static int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
