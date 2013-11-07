import java.io.Serializable;

public class FilePro implements Serializable {
		private long fSize;
		private String fExt;
		private Node huffT;
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>constructor<<<<<<<<<<<<<<<<<<<<<<<<<<<,
		public FilePro(long fS,String fE,Node ht){
			fSize=fS;
			fExt=fE;
			huffT=ht;
		}
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>setter and getter<<<<<<<<<<<<<<<<
	   public long getFileSize() {
			return fSize;
		}
		public void setFileSize(long fileSize) {
			this.fSize = fileSize;
		}
		public String getFileExt() {
			return fExt;
		}
		public void setFileExt(String fileExt) {
			this.fExt = fileExt;
		}
		public Node getHuffT() {
			return huffT;
		}
		public void setHuffT(Node huffT) {
			this.huffT = huffT;
		}
}
