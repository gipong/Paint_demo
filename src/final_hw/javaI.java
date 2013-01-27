package final_hw;

import java.awt.*;
import java.awt.event.*;
//	import java.awt.geom.Line2D;
import javax.swing.*;
//	import java.io.*;

public class javaI extends JFrame implements ActionListener,MouseMotionListener,MouseListener{

	int px,py,px2,py2,status,tno=3;	// default use triangle so tno = 3
	int rpx,rpy,rpx2,rpy2;
	int tx[] = new int[20];
	int ty[] = new int[20];
	int rtx[] = new int[20];
	int rty[] = new int[20];
	JButton cp[] = new JButton[3];	// set button 
	JColorChooser ch;
	Color nco;
	Graphics2D g2;
	
	public static void main(String[] args) {
		javaI frame = new javaI();

	}
	public javaI(){	// javaI constructor 
		super("painter");
		Container c=getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		c.setBackground(Color.white); 
		JToolBar tb1 = new JToolBar(); // my toolbar
		tb1.setFloatable(false);
		String str1[] = { "triangle","square","choose color" };	// menu text
		// for loops build button
		for(int i=0;i<3;i++){
			cp[i]=new JButton(str1[i]);
			cp[i].addActionListener(this);
			tb1.add(cp[i]);
			}
		c.add(tb1,BorderLayout.NORTH);	// let toolbar North
		ch=new JColorChooser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		addMouseMotionListener(this);
		addMouseListener(this);
		status=0;
		nco=new Color(0,255,255);
		setSize(800,600);	// set panel size
		setVisible(true);	// display panel 
	}
	//move mouse 
	public void mouseMoved(MouseEvent e){
		px=e.getX();
		py=e.getY();	// get mouse location
		status=0;
	}
	
/* 
 * test stroke size and position
 * 
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D) g;
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(1.0f));
		g2.drawLine(50,50,150,50); 
		g2.setStroke(new BasicStroke(2.0f));
		g2.drawLine(50,60,150,60); 
		g2.setStroke(new BasicStroke(3.0f));
		g2.drawLine(50,70,150,70); 
		g2.setStroke(new BasicStroke(4.0f));
		g2.drawLine(50,80,150,80); 
		g2.setStroke(new BasicStroke(5.0f));
		g2.drawLine(50,90,150,90); 
		g2.setStroke(new BasicStroke(6.0f));
		g2.drawLine(50,100,150,100);
	}
*/

	public void mouseDragged(MouseEvent e){
		Graphics g=getGraphics();
		g2=(Graphics2D) g; 
		g2.setStroke(new BasicStroke(3.0f)); 
		g2.setColor(nco); 
		g2.setXORMode(Color.black);	// use XOR mode
		//check new picture
	/*	
	 	if(status==1){
			g2.drawOval(rpx,rpy,rpx2,rpy2);
		}else{
			px=e.getX();
			py=e.getY();
			status=1;
		}
		px2=Math.abs(e.getX()-px); //count length
		py2=Math.abs(e.getY()-py);	//count width
		g2.drawOval(px,py,px2,py2); //draw 
		rpx=px;rpy=py;rpx2=px2;rpy2=py2; //save position
	*/
		if(status==1){
			Polygon pg1=new Polygon(rtx,rty,tno);
			g2.drawPolygon(pg1);
		}else{
			px=e.getX();
			py=e.getY();
			status=1; 
		}
		double s=(e.getX()-px)*(e.getX()-px)+(e.getY()-py)*(e.getY()-py);
		double r=Math.sqrt(s);
		int w=360/tno;
		//count edge_point 
		for(int i=0;i<tno;i++){
			tx[i]=px+(int)(r*Math.sin((180+i*w)*Math.PI/180));
			ty[i]=py+(int)(r*Math.cos((180+i*w)*Math.PI/180));
			rtx[i]=tx[i];
			rty[i]=ty[i];
		}
		Polygon pg2=new Polygon(tx,ty,tno);
		g2.drawPolygon(pg2);
	}

	public void mouseReleased(MouseEvent e){
		Graphics g=getGraphics();
		g2=(Graphics2D) g;
		g2.setColor(nco);	// change stroke color what user choose
		g2.setStroke(new BasicStroke(3.0f)); // set stroke
	//oval	g2.drawOval(rpx,rpy,rpx2,rpy2); // draw oval
		Polygon pg1 = new Polygon(rtx,rty,tno);
		g2.drawPolygon(pg1);
		}
	public void mousePressed(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}

	public void actionPerformed(ActionEvent e) {
	
		for(int i=0;i<2;i++){
			if(e.getSource() == cp[i]){ tno = i+3; }
		}
		if(e.getSource() == cp[2]){
			nco=JColorChooser.showDialog(javaI.this,"choose color",nco);	// call dialog to choose color
		}
		
	}

	
}