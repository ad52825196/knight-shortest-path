import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Knight shortest path problem: It is known that the knight travels two cells
 * in one direction then one cell in the other. Find the minimum number of
 * knight moves to travel from opposite corners on a board. Also, find the total
 * number of different shortest paths.
 * 
 * @author Zhen Chen
 *
 */

public class knight {
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static int r;
	private static int c;
	private static int[][] distance;
	private static BigInteger[][] path;

	private static class Coordinate {
		private int x;
		private int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public final int getX() {
			return x;
		}

		public final int getY() {
			return y;
		}
	}

	private static void getRowsAndColumns() throws IOException {
		String line;
		String[] list;
		line = reader.readLine();
		list = line.split("\\s");
		r = Integer.parseInt(list[0]);
		c = Integer.parseInt(list[1]);
	}

	private static boolean destination(final Coordinate coordinate) {
		return (coordinate.getX() == r - 1 && coordinate.getY() == c - 1);
	}

	/**
	 * Find all valid neighbors of the given position according to knight moving
	 * rules. The neighbors should be within the boundaries of the board.
	 * 
	 * @param position
	 * @return an array containing at most 8 valid neighbor coordinates
	 *         reachable from the given position
	 */
	private static Coordinate[] getNeighbour(final Coordinate position) {
		Coordinate[] neighbours = new Coordinate[8];
		int k = 0;
		int x = position.getX();
		int y = position.getY();

		for (int i = -2; i <= 2; i++) {
			if (i == 0)
				continue;
			for (int j = -2; j <= 2; j++) {
				if (j == 0)
					continue;
				if (i + j == 0 || i - j == 0)
					continue;
				if (x + i < 0 || x + i >= r || y + j < 0 || y + j >= c)
					continue;
				neighbours[k++] = new Coordinate(x + i, y + j);
			}
		}
		return neighbours;
	}

	private static boolean compute() {
		distance = new int[r][c];
		path = new BigInteger[r][c];
		Queue<Coordinate> queue = new LinkedList<Coordinate>();
		Coordinate[] neighbours;
		Coordinate position;
		int px, py;
		int nx, ny;

		queue.add(new Coordinate(0, 0));
		distance[0][0] = 0;
		path[0][0] = new BigInteger("1");

		while (!queue.isEmpty()) {
			position = queue.poll();
			if (destination(position)) {
				return true;
			}
			px = position.getX();
			py = position.getY();
			neighbours = getNeighbour(position);
			for (Coordinate neighbour : neighbours) {
				if (neighbour == null)
					break;
				nx = neighbour.getX();
				ny = neighbour.getY();
				if (nx + ny == 0)
					continue;
				if (distance[nx][ny] == 0) {
					distance[nx][ny] = distance[px][py] + 1;
					path[nx][ny] = path[px][py];
					// this neighbor is a new node to explore
					queue.add(neighbour);
				} else if (distance[nx][ny] == distance[px][py] + 1) {
					path[nx][ny] = path[nx][ny].add(path[px][py]);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		try {
			while (true) {
				getRowsAndColumns();
				if (compute()) {
					System.out.printf("%d %s%n", distance[r - 1][c - 1], path[r - 1][c - 1].toString());
				} else {
					break;
				}
			}
		} catch (Exception e) {
			System.exit(0);
		}
	}

}
