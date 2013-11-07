public class MinHeap {
	private Comparable[] heap;
	private int size;
	private int n;

	//>>>>>>>>>>>>>>>>>>>>>constructor<<<<<<<<<<<<<<<<<<<<<<
	public MinHeap() {
		size = 100;
		n = 0;
		heap = new Comparable[size];
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>setter and getter<<<<<<<<<<<<<<<<<<<<<<<
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public MinHeap(int s) {
		size = s;
		n = 0;
		heap = new Comparable[size];
	}
	public MinHeap(Comparable [] h,int n){
		heap=h;
		this.n=n;
		size=h.length;
		buildHeap();
	}
	public int getSize() {
		return size;
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>is leaf method<<<<<<<<<<<<<<<<<<<<<
	public boolean isLeaf(int pos) {
		return pos >= n / 2 && pos < n;
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>return left child position<<<<<<<<<<<<<<<
	public int leftchild(int pos) { 
		assert pos < n / 2 : "Position has no left child";
		return 2 * pos + 1;
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>return right child position<<<<<<<<<<<<<<<
	public int rightchild(int pos) { 
		assert pos < (n - 1) / 2 : "Position has no right child";
		return 2 * pos + 2;
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>return parent position<<<<<<<<<<<<<<<
	public int parent(int pos) {
		assert pos > 0 : "Position has no parent";
		return (pos - 1) / 2;
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sift down<<<<<<<<<<<<<<<<<<<<
	public void siftDown(int pos) {
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		while (!isLeaf(pos)) {
			int temp = leftchild(pos);
			if (temp < n - 1 && heap[temp].compareTo(heap[temp + 1]) > 0) {
				temp++;
			}
			if (heap[pos].compareTo(heap[temp]) >= 0) {
				Comparable o = heap[pos];
				heap[pos] = heap[temp];
				heap[temp] = o;
				pos = temp;
			} else {
				return;
			}
		}
		
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Building heap<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public void buildHeap() {
		for (int i = (n / 2) - 1; i >= 0; i--) {
			siftDown(i);
		}
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>remove minimum<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 
	public Comparable removeMin(){
		assert n > 0 : "Removing from empty heap";
		Comparable temp=heap[0];
		n--;
		heap[0]=heap[n];
		heap[n]=temp;
		if(n!=0)
		siftDown(0);
		return temp;
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.insert new element <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public void insert(Comparable in){
		assert n<size:"heap is full";
		heap[n]=in;
		int pos=n;
		n++;
		while((pos!=0)&&heap[pos].compareTo(heap[parent(pos)])<0){
			heap[pos]=heap[parent(pos)];
			heap[parent(pos)]=in;
			pos=parent(pos);
		}
	}
	public void remove(Comparable in){
		assert(n>0):"empty heap";
		int pos =0;
	while(pos<n){
		if(heap[pos].compareTo(in)==0){
			n--;
			heap[pos]=heap[n];
			if(n!=0){
			siftDown(pos);
			while((pos!=0)&&heap[pos].compareTo(heap[parent(pos)])<0){
				heap[pos]=heap[parent(pos)];
				heap[parent(pos)]=in;
				pos=parent(pos);
			}
			}
			break;
		}
		pos++;
	}
	}
}
