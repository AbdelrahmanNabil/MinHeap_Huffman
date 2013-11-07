import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeCompress {
private boolean towrite=false;
private FilePro pro;
private BufferedOutputStream outStream = null;
private long bytecount;
private byte[]  forWrite;
private Node pointer;
private long oldSizeCounter=0;
private File f;
private final byte[]standard={-128,64,32,16,8,4,2,1};
public DeCompress(){
	forWrite=new byte[1024];
	bytecount=0;
	pro=null;
}
public void read(String url){
	byte [] buffer=new byte[1024];
	BufferedInputStream in;
	try {
		in=new BufferedInputStream(new FileInputStream(url));
		ObjectInputStream str=new ObjectInputStream(in);
		pro=(FilePro) str.readObject();
		pointer=pro.getHuffT();
		f= new File(url.replace(".nan",'.'+pro.getFileExt()));
		f.createNewFile();
		outStream = new BufferedOutputStream(new FileOutputStream(f.getPath()));
		while(in.read(buffer)!=-1){
			for(int i=0;i<1024;i++){
				tobin(buffer[i]);
			}
		}str.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>to binary and write<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
public void tobin(byte b){
	if(oldSizeCounter<pro.getFileSize())
	for(int i=0;i<8;i++){
		if((standard[i]&b)==0){
		pointer=pointer.getLeftC();
		}else{
		pointer=pointer.getRightC();
		}
		if(pointer.isLeaf()){
			towrite=true;
			oldSizeCounter+=1;
			forWrite[(int)(bytecount%1024)]=pointer.getElm();
			bytecount++;
			pointer=pro.getHuffT();
			if((bytecount!=0&&bytecount%1024==0)){
				try {
					towrite=false;
					outStream.write(forWrite);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>close<<<<<<<<<<<<<<<<<<
public void close(){
	try {
		if(towrite)
			outStream.write(forWrite,0,(int)(pro.getFileSize()%1024));
		outStream.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} 

public void rSet(){
	forWrite=new byte[1024];
	bytecount=0;
	pro=null;
	oldSizeCounter=0;
}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>decomp method<<<<<<<<<<<<<<<<<<<<<<<<
public void decomp(String url){
	read(url);
	close();
}
}
