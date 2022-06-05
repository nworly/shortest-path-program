package io.deeplay;

import java.util.PriorityQueue;
import java.util.Queue;


public class Algorithm {

    private static final int[] X_AXIS = {-1, 0, 1, 0};
    private static final int[] Y_AXIS = {0, 1, 0, -1};

    private static int height = 4;
    private static int width = 4;

    private static class Cell implements Comparable<Cell> {
        int x;
        int y;
        int distance;

        Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Cell o) {
            return Long.compare(this.distance, o.distance);
        }
    }

    private static boolean isAvailable(int x, int y) {
        return (x >= 0 && x < height && y >= 0 && y < width);
    }

    public static int getShortestPath(int[][] field, int x, int y) {
        int[][] distance = new int[x][y];

        int initialState = Integer.MAX_VALUE;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                distance[i][j] = initialState;
            }
        }

        distance[0][0] = 0;

        Queue<Cell> queue = new PriorityQueue<>(x * y);
        Cell sourceCell = new Cell(0, 0, distance[0][0]);

        queue.add(sourceCell);

        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();

            for (int i = 0; i < X_AXIS.length; i++) {
                int row = currentCell.x + X_AXIS[i];
                int col = currentCell.y + Y_AXIS[i];

                if (isAvailable(row, col)) {

                    if (distance[currentCell.x][currentCell.y] + field[row][col] < distance[row][col]) {

                        if (distance[row][col] != initialState) {
                            Cell adjacentCell = new Cell(row, col, distance[row][col]);
                            queue.remove(adjacentCell);
                        }

                        distance[row][col] = distance[currentCell.x][currentCell.y] + field[row][col];

                        queue.add(new Cell(row, col, distance[row][col]));
                    }
                }
            }
        }
        return distance[height - 1][width - 1];
    }

    public static void setHeight(int height) {
        if (height < 1) return;
        Algorithm.height = height;
    }

    public static void setWidth(int width) {
        if (width < 1) return;
        Algorithm.width = width;
    }
}
