public class HeapSort {
//>>>>>>>>>>>>>>>>>>>>>>>sorting method<<<<<<<<<<<<<<<<<<<<<
public void sort(Comparable []array,int n){
	MinHeap heap=new MinHeap(array,n);
	for(int i=0;i<n;i++){
		heap.removeMin();
	}
}
public static void main(String[] args) {
	Comparable []array={10,20,30,22,39,31,32,25,100,50,60};
	MinHeap min=new MinHeap(array,array.length);
	min.remove(39);
	for(int i=0;i<array.length;i++){
		System.out.println(array[i]);
	}
}
}
