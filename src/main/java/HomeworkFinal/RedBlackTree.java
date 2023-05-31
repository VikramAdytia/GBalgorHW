package HomeworkFinal;

public class RedBlackTree<V extends Comparable<V>> {

	enum NodeColor {RED, BLACK, NONE}

	public class Node {
		private V value;
		private NodeColor color;
		private Node parent;
		private Node left;
		private Node right;
		
		public Node() {
			value = null;
			color = NodeColor.NONE;
			parent = null;
			left = null;
			right = null;
		}

		public Node(V value, NodeColor color) {
			value = value;
			color = color;
			parent = nil;
			left = nil;
			right = nil;
		}

		public boolean isFree() {
			return value == null || value == nil;
		}

		public boolean isParentFree() {
			return parent == null || parent == nil;
		}
		
		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			value = value;
		}

		public Node getParent() {
			return parent;
		}
		
		public void setParent(Node node) {
			parent = node;
		}
		
		public Node getLeft() {
			return left;
		}
		
		public void setLeft(Node node) {
			left = node;
			if(left != null && left != nil) left.parent = this;
		}
		
		public Node getRight() {
			return right;
		}

		public void setRight(Node node) {
			right = node;
			if(right != null && right != nil) right.parent = this;
		}
		
		public boolean isBlack() {
			return color == NodeColor.BLACK;
		}
		
		public void makeBlack() {
			color = NodeColor.BLACK;
		}

		public boolean isRed() {
			return color == NodeColor.RED;
		}

		public void makeRed() {
			color = NodeColor.RED;
		}
		public Node getGrandfather() {
			if(parent != null && parent != nil)
				return parent.parent;
			return null;
		}
		
	}
	private Node root;
	private Node nil;
	public RedBlackTree() {
		root = new Node();
		nil = new Node();
		nil.color = NodeColor.BLACK;
		root.parent = nil;
		root.right = nil;
		root.left = nil;
	}
	private static <T extends Comparable<T>> void leftRotate(RedBlackTree<T> tree, RedBlackTree<T>.Node node) {
		RedBlackTree<T>.Node nodeParent = node.getParent();
		RedBlackTree<T>.Node nodeRight = node.getRight();
		if(nodeParent != tree.nil) {
			if(nodeParent.getLeft() == node)
				nodeParent.setLeft(nodeRight);
			else
				nodeParent.setRight(nodeRight);
		}
		else {
			tree.root = nodeRight;
			tree.root.setParent(tree.nil);
		}
		node.setRight(nodeRight.getLeft());
		nodeRight.setLeft(node);
	}
	private static <T extends Comparable<T>> void rightRotate(RedBlackTree<T> tree, RedBlackTree<T>.Node node) {
		RedBlackTree<T>.Node nodeParent = node.getParent();
		RedBlackTree<T>.Node nodeLeft = node.getLeft();
		if(nodeParent != tree.nil) {
			if(nodeParent.getLeft() == node)
				nodeParent.setLeft(nodeLeft);
			else
				nodeParent.setRight(nodeLeft);
		}
		else {
			tree.root = nodeLeft;
			tree.root.setParent(tree.nil);
		}
		node.setLeft(nodeLeft.getRight());
		nodeLeft.setRight(node);
	}
	public void add(V o) {
		Node node = root, temp = nil;
		Node newNode = new Node((V)o, NodeColor.RED);
		while(node != null && node != nil && !node.isFree()) {
			temp = node;
			if(newNode.getValue().compareTo(node.getValue()) < 0)
				node = node.getLeft();
			else
				node = node.getRight();
		}
		newNode.setParent(temp);
		if(temp == nil)
			root.setValue(newNode.getValue());
		else {
			if(newNode.getValue().compareTo(temp.getValue()) < 0)
				temp.setLeft(newNode);
			else
				temp.setRight(newNode);
		}
		newNode.setLeft(nil);
		newNode.setRight(nil);
		fixInsert(newNode);
	}
	private void fixInsert(Node node) {
		Node temp;
		while(!node.isParentFree() && node.getParent().isRed()) {
			if(node.getParent() == node.getGrandfather().getLeft()) {
				temp = node.getGrandfather().getRight();
				if(temp.isRed()) {
					temp.makeBlack();
					node.getParent().makeBlack();
					node.getGrandfather().makeRed();
					node = node.getGrandfather();
				}
				else {
					if(node == node.getParent().getRight()) {
						node = node.getParent();
						leftRotate(this, node);
					}
					node.getParent().makeBlack();
					node.getGrandfather().makeRed();
					rightRotate(this, node.getGrandfather());
				}
			}
			else {
				temp = node.getGrandfather().getLeft();
				if(temp.isRed()) {
					temp.makeBlack();
					node.getParent().makeBlack();
					node.getGrandfather().makeRed();
					node = node.getGrandfather();
				}
				else {
					if(node == node.getParent().getLeft()) {
						node = node.getParent();
						 rightRotate(this, node);
					}
					node.getParent().makeBlack();
					node.getGrandfather().makeRed();
					leftRotate(this, node.getGrandfather());
				}
			}
		}
		root.makeBlack();
	}
}
