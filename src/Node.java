import java.io.Serializable;
import java.util.Hashtable;

public class Node implements Comparable ,Serializable{
	private int freq;
	private byte elm;
	private Node rightC;
	private Node leftC;
	// >>>hash table of code
	public static Hashtable<Byte, String> codeT = new Hashtable<Byte, String>();

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>constructor<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public Node(int f, byte e) {
		freq = f;
		elm = e;
		rightC = null;
		leftC = null;
	}

	public Node(int f, byte e, Node lC, Node rC) {
		freq = f;
		elm = e;
		rightC = rC;
		leftC = lC;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>setter and getter<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public Node getRightC() {
		return rightC;
	}

	public void setRightC(Node rightC) {
		this.rightC = rightC;
	}

	public Node getLeftC() {
		return leftC;
	}

	public void setLeftC(Node leftC) {
		this.leftC = leftC;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public byte getElm() {
		return elm;
	}

	public void setElm(byte elm) {
		this.elm = elm;
	}

	public static Hashtable<Byte, String> getCodeT() {
		return codeT;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>is leaf method<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public boolean isLeaf() {
		return (leftC == null && rightC == null);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Building hash table <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public void code(Node n, String res) {
		if (n == null)
			return;
		if (n.isLeaf()) {
			codeT.put(n.elm, res);
			System.out.println((char)(n.elm) + ":" + res);
		}
		code(n.leftC, res + 0);
		code(n.rightC, res + 1);

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>comparing method<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		int temp = ((Node) arg0).freq;
		if (this.freq > temp) {
			return 1;
		}
		if (this.freq < temp) {
			return -1;
		}
		if (this.freq == temp) {
			return 0;
		}
		return 0;
	}

}
