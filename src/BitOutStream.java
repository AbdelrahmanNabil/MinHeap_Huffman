import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;


public class BitOutStream {
private BufferedOutputStream outStream;
private int byteCoun;
private byte bitCount;
private byte[] buffer;
private byte mask;
private ObjectOutputStream objectStream; 

//>>>>>>>>>>>>>>>>>>>>>>>constructor<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
public BitOutStream(String url){
	mask=0;
	byteCoun=0;
	bitCount=0;
	buffer=new byte[1024];
	try {
		outStream=new BufferedOutputStream(new FileOutputStream(url));
		objectStream=new ObjectOutputStream(outStream);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void writeObject(FilePro fp){
try {
	objectStream.writeObject(fp);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}	
}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>bitWritter<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
public void write(byte a){
	mask=(byte) (mask|a);
	bitCount++;
	if(bitCount==8){
		buffer[byteCoun]=mask;
		mask=0;
		byteCoun++;
		bitCount=0;
		if(byteCoun==1024){
			try {
				outStream.write(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byteCoun=0;
		}
	}else{
		mask=(byte) (mask<<1);
	}
}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>StringBitWriter<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
public void write(String code){
	for(int i=0;i<code.length();i++){
		write(Byte.parseByte(code.charAt(i)+""));
	}
}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>stream closer<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
public void close(){
	if(bitCount!=0){
	for(int i=0;i<7-bitCount;i++){
		mask=(byte) (mask<<1);
	}
	buffer[byteCoun]=mask;
	byteCoun++;
	}
	if(byteCoun!=0){
		try {
			outStream.write(buffer, 0, byteCoun);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	try {
		outStream.close();
		objectStream.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
