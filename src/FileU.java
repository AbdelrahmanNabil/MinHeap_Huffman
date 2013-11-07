import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileU {

	private String fileName;
	private FilePro fP;

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>constructor<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public FileU(String fN) {
		fileName = fN;
		File file = new File(fileName);
		fP = new FilePro(file.length(), fN.replace('.', ',').split(",")[1],
				null);
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.bulding huffman tree<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public void buildHuffman(int[] array) {
		MinHeap min = new MinHeap(256);
		for (int i = 0; i < 256; i++) {
			if (array[i] != 0) {
				min.insert(new Node(array[i], (byte) (i - 128)));
			}
		}
		Node temp;
		Node temp1;
		while (min.getN() > 1) {
			temp = (Node) min.removeMin();
			temp1 = (Node) min.removeMin();
			min.insert(new Node(temp.getFreq() + temp1.getFreq(), (byte) 0,
					temp, temp1));
		}
		fP.setHuffT((Node) min.removeMin());
	}

	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>read from file for making huffman tree<<<<<<<<<<<<<<<<<
	public void readFromFile() {

		BufferedInputStream bufferedInput = null;
		byte[] buffer = new byte[1024];
		int[] charFreq = new int[256];
		long fileCounter = fP.getFileSize();
		int i = 0;
		try {

			bufferedInput = new BufferedInputStream(new FileInputStream(
					fileName));
			int bytesRead = 0;
			while ((bytesRead = bufferedInput.read(buffer)) != -1) {
		
				i = 0;
				while (i < 1024 && fileCounter > 0) {
					charFreq[buffer[i] + 128] += 1;
					i++;
					fileCounter--;
				}
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bufferedInput != null)
					bufferedInput.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		buildHuffman(charFreq);
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>compress method<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	private void compress(String url) {
		readFromFile();
		BufferedInputStream bufferedInput = null;
		byte[] buffer = new byte[1024];
		long fileCounter = fP.getFileSize();
		int i = 0;
		File f = new File(url);
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BitOutStream stream = new BitOutStream(url);
		fP.getHuffT().code(fP.getHuffT(), "");
		stream.writeObject(fP);
		try {

			// Construct the BufferedInputStream object
			bufferedInput = new BufferedInputStream(new FileInputStream(
					fileName));
			int bytesRead = 0;

			// Keep reading from the file while there is any content
			// when the end of the stream has been reached, -1 is returned
			while ((bytesRead = bufferedInput.read(buffer)) != -1) {
				// Process the chunk of bytes read
				// in this case we just construct a String and print it out
				i = 0;
				while (i < 1024 && fileCounter > 0) {
					stream.write(Node.codeT.get((byte) buffer[i]));
					i++;
					fileCounter--;
				}
			}
			stream.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// Close the BufferedInputStream
			try {
				if (bufferedInput != null)
					bufferedInput.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>comprison method <<<<<<<<<<<<<<<<<<<<<<<<<<
	public void compress (){
		File temp=new File(fileName.replace("."+fP.getFileExt(),".nan"));
		try {
			temp.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		compress(temp.getPath());
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>reset the compressed file<<<<<<<<<<<<<<<<<<<
	public void rSetFile(String fN){
		fileName = fN;
		File file = new File(fileName);
		fP = new FilePro(file.length(), fN.replace('.', ',').split(",")[1],
				null);
	}

}
