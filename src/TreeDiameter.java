import java.util.*;
class Tree {
	int data;
	ArrayList<Tree> children;
	public Tree (int data) {
		this.data = data;
		children = new ArrayList<Tree>();
	}
	void addChild (int data) {
		Tree temp = new Tree(data);
		children.add(temp);
	}
	void print () {
		
	}
}
public class TreeDiameter {
	Tree tree;
	int dia;
	public TreeDiameter(Tree tree)
	{
		this.tree = tree;
		dia = 0;
	}
	int getDiameter () {
		int max1 = 0, max2 = 0;
		
		if (tree.children.size() == 0) return 0;
		for (Tree t: tree.children)
		{
			int d = getDepth(t);
			if (d > max1) {
				max2 = max1;
				max1 = d;
			}
			else if (d > max2)
				max2 = d;
		}
		dia = (max1+max2+2 > dia)?(max1+max2+2):dia;
		
		return dia;
	}
	int getDepth (Tree t) {
		
		int max1 = 0, max2 = 0;
		if (t.children.size() == 0) return 0;
		for (Tree t1: t.children)
		{
			int d = getDepth(t1);
			if (d > max1) {
				max2 = max1;
				max1 = d;
			}
			else if (d > max2)
				max2 = d;
		}
		dia = ((max1+max2+2) > dia)?(max1+max2+2):dia;
		
		return max1 + 1;
	}
	
	public static void main (String[] args) {
		Tree inp = new Tree(0);
		inp.addChild(1);
		inp.addChild(2);
		Tree one = inp.children.get(0);
		one.addChild(3);
		one.addChild(4);
		Tree two = inp.children.get(1);
		two.addChild(5);
		Tree three = one.children.get(0);
		three.addChild(6);
		three.addChild(7);
		Tree four = one.children.get(1);
		four.addChild(8);
		Tree seven = three.children.get(1);
		seven.addChild(9);
		Tree eight = four.children.get(0);
		eight.addChild(10);
		eight.addChild(11);
		Tree nine = seven.children.get(0);
		nine.addChild(12);
		nine.addChild(13);
		Tree eleven = eight.children.get(1);
		eleven.addChild(14);
		inp.print();
		System.out.println(new TreeDiameter(inp).getDiameter());
	}

}
