



public class TestGameTree{
	public static void main(String[] args){
		//		[0 1 3; 4 2 5; 7 8 6]
		int[][] gameBoard = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
		Board board = new Board(gameBoard);
		GameTree tree = new GameTree(board, 3);
		System.out.println("DFS");
		String dfs = tree.DFS();
		System.out.println(dfs);
		System.out.println("===============");
		System.out.println("BFS");
		String bfs = tree.BFS();
		System.out.println(bfs);
		System.out.println("===============");

	}
}
