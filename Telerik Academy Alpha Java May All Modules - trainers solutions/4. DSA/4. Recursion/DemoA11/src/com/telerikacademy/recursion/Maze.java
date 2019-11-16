package com.telerikacademy.recursion;

public class Maze {

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

        int startRow = 0;
        int startColumn = 0;
        int res = findAllPaths(mySuperCoolMaze, startRow, startColumn);
        System.out.println(res);
    }

    public static boolean hasPath(char[][] maze, int row, int col) {
        // if current cell is a wall or out of bounds?
        if (!isInRange(maze, row, col)) {
            return false;
        }

        if (maze[row][col] == WALL) {
            return false;
        }

        // See if it is the Exit
        if (maze[row][col] == EXIT) {
            return true;
        }

        // if it is not visited?
        if (maze[row][col] == VISITED) {
            return false;
        }

        // Mark current element visited
        maze[row][col] = VISITED;

        // Find a path to the left
        boolean hasPathToTheLeft = hasPath(maze, row, col + 1);
        // Find a path to the right
        boolean hasPathToTheRight =  hasPath(maze, row, col - 1);
        // Find a path up
        boolean hasPathToTheUp = hasPath(maze, row - 1, col);
        // Find a path down
        boolean hasPathToTheDown = hasPath(maze, row + 1, col);

        return hasPathToTheDown || hasPathToTheLeft || hasPathToTheRight || hasPathToTheUp;
    }

    public static boolean findPath(char[][] maze, int row, int col){
        // if current cell is a wall or out of bounds?
        if (!isInRange(maze, row, col)) {
            return false;
        }

        if (maze[row][col] == WALL) {
            return false;
        }

        // See if it is the Exit
        if (maze[row][col] == EXIT) {
            printMatrix(maze);
            return true;
        }

        // if it is not visited?
        if (maze[row][col] == VISITED) {
            return false;
        }

        // Mark current element visited
        maze[row][col] = VISITED;

        // Find a path to the left
        boolean hasPathToTheLeft = findPath(maze, row, col + 1);
        // Find a path to the right
        boolean hasPathToTheRight =  findPath(maze, row, col - 1);
        // Find a path up
        boolean hasPathToTheUp = findPath(maze, row - 1, col);
        // Find a path down
        boolean hasPathToTheDown = findPath(maze, row + 1, col);

        return hasPathToTheDown || hasPathToTheLeft || hasPathToTheRight || hasPathToTheUp;
    }

    public static int findAllPaths(char maze[][], int row, int col){
        int counter = 0;
        // if current cell is a wall or out of bounds?
        if (!isInRange(maze, row, col)) {
            return 0;
        }

        if (maze[row][col] == WALL) {
            return 0;
        }

        // See if it is the Exit
        if (maze[row][col] == EXIT) {
            printMatrix(maze);
            return 1;
        }

        // if it is not visited?
        if (maze[row][col] == VISITED) {
            return 0;
        }

        // Mark current element visited
        maze[row][col] = VISITED;

        // Find a path to the left
        counter += findAllPaths(maze, row, col + 1);
        // Find a path to the right
        counter += findAllPaths(maze, row, col - 1);
        // Find a path up
        counter += findAllPaths(maze, row - 1, col);
        // Find a path down
        counter += findAllPaths(maze, row + 1, col);

        // Mark current element as not visited, so you keep searching other paths
        maze[row][col] = EMPTY_PLACE;

        return counter;
    }

    // Check if the row is between 0 and maze width
    // Check if the column is between 0 and maze height
    private static boolean isInRange(char[][] maze, int row, int column) {
        return row < maze.length
                && row >= 0
                && column < maze[0].length
                && column >= 0;
    }

    private static void printMatrix(char[][] maze) {
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
