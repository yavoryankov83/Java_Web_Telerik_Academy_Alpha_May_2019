package com.telerikacademy.recursion;

public class LectureMaze {
    public static final char WALL = '\u25A0';
    public static final char EMPTY_PLACE = '\u25CB';
    public static final char VISITED = '\u25CD';
    public static final char EXIT = '\u25A1';

    public static void main(String[] args) {
        char[][] mySuperCoolMaze = {
                {EMPTY_PLACE, WALL, EMPTY_PLACE, EMPTY_PLACE, WALL, EXIT},
                {EMPTY_PLACE, WALL, WALL, EMPTY_PLACE, WALL, EMPTY_PLACE},
                {EMPTY_PLACE, EMPTY_PLACE, EMPTY_PLACE, WALL, WALL, EMPTY_PLACE},
                {EMPTY_PLACE, EMPTY_PLACE, EMPTY_PLACE, EMPTY_PLACE, EMPTY_PLACE, EMPTY_PLACE},
                {EMPTY_PLACE, WALL, WALL, EMPTY_PLACE, WALL, EMPTY_PLACE},
        };

        printMaze(mySuperCoolMaze, 0, 0);
        System.out.println(findAllPaths(mySuperCoolMaze));

    }

    public static boolean hasPath(char[][] maze) {
        return hasPathHelp(0, 0, maze);
    }

    public static void findPath(char[][] maze) {
        findPathHelp(0, 0, maze);
    }

    public static int findAllPaths(char[][] maze) {
        return findAllPathsHelp(0, 0, maze);
    }

    public static int findAllPathsHelp(int row, int column, char[][] maze) {
        int count = 0;
        if (!isInRange(row, column, maze)) {
            return count;
        }

        if (maze[row][column] == WALL) {
            return count;
        }

        if (maze[row][column] == EXIT) {
            printMaze(maze, 0, 0);
            count ++;
            return count;
        }

        if (maze[row][column] == VISITED) {
            return count;
        }

        maze[row][column] = VISITED;

            count += findAllPathsHelp(row - 1, column, maze);
            count += findAllPathsHelp(row + 1, column, maze);
            count += findAllPathsHelp(row, column - 1, maze);
            count += findAllPathsHelp(row, column + 1, maze);

        maze[row][column] = EMPTY_PLACE;

        return count;
    }

    public static void findPathHelp(int row, int column, char[][] maze) {
        if (!isInRange(row, column, maze)) {
            return;
        }

        if (maze[row][column] == WALL) {
            return;
        }

        if (maze[row][column] == EXIT) {
            printMaze(maze, 0, 0);
            return;
        }

        if (maze[row][column] == VISITED) {
            return;
        }

        maze[row][column] = VISITED;

        findPathHelp(row - 1, column, maze);
        findPathHelp(row + 1, column, maze);
        findPathHelp(row, column - 1, maze);
        findPathHelp(row, column + 1, maze);
    }

    public static boolean hasPathHelp(int row, int column, char[][] maze) {
        if (!isInRange(row, column, maze)) {
            return false;
        }

        if (maze[row][column] == WALL) {
            return false;
        }

        if (maze[row][column] == EXIT) {
            return true;
        }

        if (maze[row][column] == VISITED) {
            return false;
        }

        maze[row][column] = VISITED;

        boolean hasPathToTheTop = hasPathHelp(row - 1, column, maze);
        boolean hasPathToTheDown = hasPathHelp(row + 1, column, maze);
        boolean hasPathToTheLeft = hasPathHelp(row, column - 1, maze);
        boolean hasPathToTheRight = hasPathHelp(row, column + 1, maze);

        return hasPathToTheTop ||
                hasPathToTheDown ||
                hasPathToTheLeft ||
                hasPathToTheRight;
    }

    private static boolean isInRange(int row, int column, char[][] maze) {
        return row >= 0 && column >= 0
                && row < maze.length
                && column < maze[0].length;
    }

    private static void printMaze(char[][] maze, int rows, int columns) {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print("|" + cell);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println();
    }
}
