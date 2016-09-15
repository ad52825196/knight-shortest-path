import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

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
	private static final int MAX_SIZE_OF_BOARD = 200;
	private static int r;
	private static int c;
	private static int[][] board = new int[MAX_SIZE_OF_BOARD][MAX_SIZE_OF_BOARD];

	private static void getRowsAndColumns() throws IOException {
		String line;
		String[] list;
		line = reader.readLine();
		list = line.split("\\s");
		r = Integer.parseInt(list[0]);
		c = Integer.parseInt(list[1]);
	}

	public static void main(String[] args) {
		try {
			while (true) {
				getRowsAndColumns();
			}
		} catch (Exception e) {
			System.exit(0);
		}
	}

}
