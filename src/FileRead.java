import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class FileRead {
private String url;
public FileRead(String url){
	this.url=url;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Object[] readFile(){	
	String temp="";
	try {
		BufferedReader reader=new BufferedReader(new FileReader(url));
		String line=reader.readLine();
		while(line!=null){
			temp+=line+'\n';
			line=reader.readLine();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "error in file");
	}
	return temp.split(""+'\n');
}
public static void main(String[] args) {
	FileRead t=new FileRead("C:\\Users\\user\\Desktop\\pp.txt");
	Comparable []b=(Comparable[]) t.readFile();
	HeapSort sor=new HeapSort();
	sor.sort(b, b.length);
	System.out.println("heap sort result:");
	for(int i=b.length-1;i>=0;i--)
		System.out.println(b[i]);
}
}