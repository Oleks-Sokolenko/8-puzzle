/**
 * Look for explanations in README file
 */

import trees.*;

import java.util.*;


public class GameTree extends AbstractTree<Board>{

	private Node<Board> root;
	private int size;


	// creates a game tree with the given depth
	public GameTree(Board initial, int depth){
		addRoot(initial);
		createChildren(root, depth);
	}

	/**
	 * Returns the root of the tree
	 *
	 * @return root of the tree
	 */
	@Override
	public Position<Board> root(){
		return root;
	}

	/**
	 * Returns the parent of the position p
	 *
	 * @param p A valid Position within the tree
	 * @return parent of p
	 * @throws IllegalArgumentException if p is not a valid position within the tree
	 */
	@Override
	public Position<Board> parent(Position<Board> p) throws IllegalArgumentException{
		Node<Board> node = validate(p);
		return node.getParent();
	}

	/**
	 * Returns the list of children for the specified position p
	 *
	 * @param p A valid Position within the tree
	 * @return list of children
	 * @throws IllegalArgumentException if p is not a valid position within the tree
	 */
	@Override
	public Iterable<Position<Board>> children(Position<Board> p) throws IllegalArgumentException{
		Iterable<Position<Board>> children = new ArrayList<>(4); //max size of children for each board is 4
		Node<Board> node = validate(p);
		if(node.getChildren() != null)
			children = node.getChildren();

		return children;

	}

	/**
	 * Create and return the roo
	 *
	 * @param board board to be added to the root position
	 * @return position of root
	 */
	public Position<Board> addRoot(Board board){
		if(!isEmpty()){
			throw new IllegalStateException("Game tree isn't empty!!!");
		}
		root = createNode(board, null, null);
		size = 1;
		return root;
	}

	/**
	 * Adds children to a node, and returns the list of them
	 *
	 * @param p A valid position within the tree
	 * @return list of children
	 */
	public Iterable<Position<Board>> addChildren(Position<Board> p){
		List<Position<Board>> children = new ArrayList<>();
		Node<Board> parent = validate(p);
		List<Board> neighbors = (List<Board>)parent.getElement()
		                                           .neighbors();

		neighbors.stream()
		         .map(neighbor -> createNode(neighbor, parent, null))
		         .forEach(children::add);

		parent.setChildren(children);

		size += neighbors.size();

		return children;
	}

	/**
	 * Creates children of the node
	 *
	 * @param p     a valid position within tree
	 * @param depth the number of levels to create
	 */
	private void createChildren(Position<Board> p, int depth){
		if(depth <= 1)
			return;
		Iterable<Position<Board>> children = addChildren(p);
		for(Position child : children){
			createChildren(child, depth - 1);
		}

	}


	/**
	 * Returns a node after validating position p on being an instance of Node class
	 *
	 * @param p a valid position within the tree
	 * @return newly created node
	 */
	private Node<Board> validate(Position<Board> p){
		if(!(p instanceof Node))
			throw new IllegalArgumentException("p is not a valid position for this tree");

		Node<Board> node = (Node<Board>)p;
		if(node.getParent() == node)
			throw new IllegalArgumentException("p is not longer in the tree");

		return node;
	}


	/**
	 * Create and return a node
	 *
	 * @param board    a board that will be added to a node
	 * @param parent   a parent of the node
	 * @param children children of the node
	 * @return node within the tree with specified board, parent and children
	 */
	private Node<Board> createNode(Board board, Node<Board> parent, Iterable<Position<Board>> children){
		return new Node(board, parent, children);
	}

	/**
	 * Returns the size of the Game tree
	 *
	 * @return
	 */
	public int size(){
		return size;
	}

	/**
	 * Depth-first-search
	 * @return string of preordered tree layout
	 */
	public String DFS(){
		List<String> layout = new ArrayList<>();
		if(!isEmpty()){
			treePreorder(root, layout);
		}

		return layout.stream().reduce("", String::concat);
	}

	private void treePreorder(Position<Board> p, List<String> layout){
		layout.add(p.getElement().toString() + "\n\n");
		for(Position<Board> child : children(p)){
			treePreorder(child, layout);
		}
	}

	/**
	 * Breadth-first-search
	 * @return string of layout done in breadth first order
	 */
	public String BFS(){
		List<String> layout = new ArrayList<>();
		LinkedList<Position<Board>> dequeue = new LinkedList<>();
		dequeue.addLast(root);
		while(!dequeue.isEmpty()){
			Position<Board> current = dequeue.pollFirst();
			layout.add(current.getElement().toString() + "\n\n");
			for(Position<Board> child : children(current)){
				dequeue.addLast(child);
			}
		}
		return layout.stream().reduce("", String::concat);
	}


	/**
	 * Nested Node class
	 *
	 * @param <E> type of the node
	 */
	protected static class Node<E> implements Position<E>{
		private E element;
		private Node<E> parent;
		private Iterable<Position<E>> children;

		public Node(E element, Node<E> parent, Iterable<Position<E>> children){
			this.element = element;
			this.parent = parent;
			this.children = children;
		}

		@Override
		public E getElement(){
			return element;
		}

		public void setElement(E element){
			this.element = element;
		}

		public Node<E> getParent(){
			return parent;
		}

		public void setParent(Node<E> parent){
			this.parent = parent;
		}

		public Iterable<Position<E>> getChildren(){
			return children;
		}

		public void setChildren(Iterable<Position<E>> children){
			this.children = children;
		}

	}

}
