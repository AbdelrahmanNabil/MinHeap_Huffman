import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test {
JFrame frame;
JButton compress;
JButton decompress;
JButton rb;
JButton rst;
JTextField adress;
JTextField adress1;
JTextField ratio;
JTextField time;
JFileChooser f;
JPanel panel;
FileU comp;
DeCompress dec;
Co t1=new Co();
De t2=new De();
Timer timer ;
public Test(){
	timer=new Timer(1000,new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			time.setText((Long.parseLong(time.getText())+1)+"");
		}
	});
	frame=new JFrame("Winnan");
	compress=new JButton("Compress");
	decompress=new JButton("Decompress");
	adress=new JTextField();
	adress1=new JTextField();
	ratio=new JTextField();
	time=new JTextField();
	f=new JFileChooser();
	panel=new JPanel();
	comp=new FileU("int.txt");
	dec=new DeCompress();
	rb=new JButton("..");
	rst=new JButton("..");
	setPosition();
	addlist();
	
}
public void setPosition(){
	frame.setLayout(null);
	panel.setLayout(null);
	frame.add(panel);
	panel.setBounds(0, 0, 500, 300);
	frame.setBounds(400, 300, 500, 300);
	compress.setBounds(10, 15, 107, 40);
	decompress.setBounds(10, 70, 107, 40);
	adress.setBounds(120, 15, 340, 40);
	adress1.setBounds(120, 70, 340, 40);
	rb.setBounds(463, 15, 10, 40);
	rst.setBounds(463, 70, 10, 40);
	ratio.setBounds(140,130,100,40);
	time.setBounds(250,130,100,40);
	panel.add(ratio);
	panel.add(time);
	adress.setEnabled(false);
	adress1.setEnabled(false);
	panel.add(rb);
	panel.add(rst);
	panel.add(adress);
	panel.add(adress1);
	panel.add(decompress);
	panel.add(compress);
	panel.setVisible(true);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(3);
}
public void addlist(){
	compress.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		t1.start();
		}
	});
	decompress.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			t2.start();
		}
	});
	
	rb.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		if(f.showOpenDialog(null)==f.APPROVE_OPTION){
			comp.rSetFile(f.getSelectedFile().getPath());
			adress.setText(f.getSelectedFile().getPath());
		}
		}
	});
rst.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		if(f.showOpenDialog(null)==f.APPROVE_OPTION){
			adress1.setText(f.getSelectedFile().getPath());
			dec.rSet();
		}
		}
	});
}
public static void main(String[] args) {
	try{
	new Test();
	}catch(Exception e){
		
	}
}
class Co extends Thread{
	public void run(){
		time.setText("0");
		timer.start();
		comp.compress();
		timer.stop();
		File oF=new File(adress.getText());
		String temp=(oF.getPath().replace('.', ',')).split(",")[1];
		File nF=new File(oF.getPath().replace("."+temp, ".nan"));
		ratio.setText(((((double)oF.length()-(double)nF.length())/(oF.length()))*100)+"");
		this.stop();
	}
}
class De extends Thread{
	public void run(){
		time.setText("0");
		timer.start();
		dec.decomp(adress1.getText());
		timer.stop();
		ratio.setText("");
		this.stop();
	}
}

}