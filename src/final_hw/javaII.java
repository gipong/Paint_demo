package final_hw;
//package painter.me2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class javaII extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color currentcolor, precolor;	//color panel-menu-design
	private int tool = 1;			//tool panel-menu-design
	private Container c = getContentPane(); //Ask panel
	private Point start, goal;		//Geometry axis
	Graphics2D g;			//2D-graphics painting

	private ColorBar_ cbr;			//ColorBar-Panel-menu var.
	private ToolBar_ tbr;			//ToolBar-Panel-menu var.
//	private static javaII p;

/*	abstract class Bar_	{		
	}
*/
	abstract class Bar_ {
	    protected Panel bar;
	    Bar_() {
		bar = new Panel();
 	    }
     }
//-------------------------------------------------------
/*
	class ColorBar_ {//Class ColorBar-design from Bar
	}
*/
	class ColorButton extends JButton  {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Color buttonColor;

		ColorButton(Color col)	{
			buttonColor = col;
			this.setBackground(col);
		}

	   	public Color getColor()	{
			return buttonColor;
		}
	}

	class ColorBar_ extends Bar_ {
		public ColorButton[] cb;
		public final int buttonnum = 3;

		ColorBar_() {
			super();

			cb = new ColorButton[buttonnum];
			cb[0] = new ColorButton(Color.black);//¶Â¦â«ö¶s
			cb[1] = new ColorButton(Color.red); //¬õ¦â«ö¶s
			cb[2] = new ColorButton(Color.yellow);	//¶À¦â«ö¶s
			bar.setLayout(new GridLayout(1,3));
			for (int i = 0; i < buttonnum; i++)
				bar.add(cb[i]);
	        }
      }

class ToolButton extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type;

	ToolButton(int ty, String filename)
	{
		type = ty;
		this.setIcon(new ImageIcon(getClass().getResource(filename)));
	}

	public int gettype()
	{
		return type;
	}
}

class ToolBar_ extends Bar_
{
	public ToolButton[] tb;
	public final int buttonnum = 3;

	ToolBar_()
	{
		super();

		tb = new ToolButton[buttonnum];
		tb[0] = new ToolButton(0, "a");	//·s­¶­±
		tb[1] = new ToolButton(1, "b");	//¹]µ§
		tb[2] = new ToolButton(2, "c");	//À¿¤l
		bar.setLayout(new GridLayout(3,1));
		for (int i = 0; i < buttonnum; i++)
			bar.add(tb[i]);
	}
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public javaII() {//Constructor of javaII 
	    super("Painter by Java_Chen ");

        start = new Point(-1, -1);		//Each paint starts
	    goal = new Point(-1, -1);		//Each paint stops
	    currentcolor = Color.BLACK;		//CurrentColor
	    precolor = Color.BLACK;		//Precolor

	    cbr = new ColorBar_();
	    tbr = new ToolBar_();

	    c.setLayout(new BorderLayout());
	    this.setBackground(Color.YELLOW);

	    c.add( cbr.bar, BorderLayout.SOUTH);
	    c.add( tbr.bar, BorderLayout.EAST);

	    for(int j=0; j<cbr.buttonnum;j++) 
		cbr.cb[j].addActionListener(this);
	    for(int i=0; i<tbr.buttonnum;i++)
		tbr.tb[i].addActionListener(this);
	    this.addMouseListener( new MouseAdapter() {
		public void mousePressed(MouseEvent mep) {
			switch(tool) {
				case 1:
				  start.x=-1; start.y=-1;goal.x=-1;goal.y=-1;
				  break;
			//	case 3: case 6: case 7: case 8:
				case 2:
				  goal=mep.getPoint(); 
			   	  break;
				default: break;
		  	}
		}
/*
		public void mouseReleased(MouseEvent mer) {
			switch(tool) { 
				case 3: case 6: case 7: case 8:
				case 9:
				  goal=mer.getPoint(); repaint();
				  break;
				default: break;
			}
		}
*/
           }                                   
        );

//Setup the contour of Java-windows-------------------------------------
	    this.setAlwaysOnTop(true);
	    this.setResizable(true);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setSize(1024, 768);
	    this.setVisible(true);
	}

	public void paint(Graphics g) {		//Method of Painting workshop 
//		System.out.println("Painting workshop is to be developed");
		g.setColor(currentcolor);
//		int width = Math.abs(goal.x - start.x);
//		int height = Math.abs(goal.y - start.y);

		int tempx = start.x;
		int tempy = start.y;
		if(goal.x < tempx)
			tempx = goal.x;
		if(goal.y < tempy)
			tempy = goal.y;
		switch(tool) {
			case 0:
				c.setBackground(Color.BLACK);
				c.setBackground(Color.WHITE);
				tool=tbr.tb[1].gettype();
				break;
			case 1:	case 2:	case 3:	case 4:
			case 5:
				c.setBackground(currentcolor); break;
			case 6: case 7: case 8: case 9:
			default: break;
		}
	}
//--------------------------------------------------------------------------- 
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i< cbr.buttonnum; i++) 
			if( e.getSource() == cbr.cb[i] && tool != 2) {
				currentcolor=cbr.cb[i].getColor();
				precolor= currentcolor;
			}

		if( e.getSource() == tbr.tb[0] ) {
			tool = tbr.tb[0].gettype();
			repaint();
		}
		else {
			for(int i=1; i<tbr.buttonnum;i++) 
				if(e.getSource() == tbr.tb[i]) {
					tool=tbr.tb[i].gettype();
					currentcolor=precolor;
					if(e.getSource()==tbr.tb[2]) {
						precolor=currentcolor;
						currentcolor=Color.white;
					}
				}
		}

		
	}//ActionListener Interface Implement
//****************************************************************************
	public static void main(String[] args){	//All get started from here	
		javaII p = new javaII();
	}
/*
	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}
*/
}



