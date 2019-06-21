import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest{
	private Board board;

	@BeforeEach
	void init(){
		board = new Board(new int[][]{{8, 0, 7}, {9, 2, 5}, {6, 3, 4}});
	}

	@AfterEach
	void destruct(){
		board = null;
	}

	@Test
	void isNotNull(){
		assertNotNull(board);
	}

	@Test
	@Ignore
	void checkToString(){

		assertEquals("8 0 7\n9 2 5\n6 3 4", board.toString());
	}

	@Test
	void tileAt(){

		assertEquals(8, board.tileAt(0, 0));
		assertEquals(0, board.tileAt(0, 1));
		assertEquals(7, board.tileAt(0, 2));
		assertEquals(9, board.tileAt(1, 0));
		assertEquals(2, board.tileAt(1, 1));
		assertEquals(5, board.tileAt(1, 2));
		assertEquals(6, board.tileAt(2, 0));
		assertEquals(3, board.tileAt(2, 1));
		assertEquals(4, board.tileAt(2, 2));
	}

	@Test
	void size(){
		assertEquals(3, board.size());
		assertNotEquals(4, board.size());
	}

	@Test

	void isGoal(){
		assertFalse(board.isGoal());
	}

	@Test

	void neighbors(){

		Iterable<Board> list = Arrays.asList(
				new Board(new int[][]{{8,2,7},{9,0,5},{6,3,4}}),
				new Board(new int[][]{{0,8,7},{9,2,5},{6,3,4}}),
				new Board(new int[][]{{8,7,0},{9,2,5},{6,3,4}})
				);

		assertEquals(list, board.neighbors());
	}
}