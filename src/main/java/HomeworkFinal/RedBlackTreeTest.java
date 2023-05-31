package HomeworkFinal;

import java.util.Random;

public class RedBlackTreeTest {
	private static Random rand = new Random();
	public static void main(String[] args) {

		RedBlackTree<Integer> rbt;
		int testAmount;

		int num;

		for(int i = 0; i < 5; i++) {
			try {
				System.out.println("\n\nStart test #" + i);
				rbt = new RedBlackTree<Integer>();
				
				testAmount = rand.nextInt(10 - 1) + 1;
				
				System.out.println(" Insertion [" + testAmount + "]:");
				for(int j = 0; j < testAmount; j++) {
					num = rand.nextInt(15);
					System.out.println("  Insert " + num + " into tree");
					rbt.add(num);
				}
			}
			catch(Exception e) {
				System.out.println("Got error: " + e.getMessage());
			}
		}
		System.out.println("\nRBT testing is over!\n\n");

	}
}
