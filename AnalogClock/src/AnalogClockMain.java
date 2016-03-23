import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.acl.LastOwnerException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.omg.CORBA.INTERNAL;

//This is a simple project of Analog clock
//Developed by Md. Kamrul Hasan Golap
//Department of Computer Science and Engineering (CSE)
//Khulna University of Engineering & Technology (KUET)
//Khulna-9203, Bangladesh
//Date 23-Mar-2016

public class AnalogClockMain extends JPanel implements Runnable{

	int lastxs=0, lastys=0, lastxm=0, lastym=0, lastxh=0, lastyh=0;
	
	public AnalogClockMain() {
		// TODO Auto-generated constructor stub
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Thread thread = new Thread(this);
		thread.start();
		
		analogClockFrame = new JDialog();
		analogClockFrame.setTitle("Analog Clock");
		analogClockFrame.add(this);
		analogClockFrame.setOpacity(1.0f);
		analogClockFrame.setSize(260, 310);
		analogClockFrame.setLocation(1090, 330);
		//analogClockFrame.setLocationRelativeTo(null);
		//analogClockFrame.setDefaultCloseOperation(analogClockFrame.DISPOSE_ON_CLOSE);
		analogClockFrame.setUndecorated(true);
		analogClockFrame.setVisible(true);
	}
	
	public void circle(int x0, int y0, int r, Graphics g)
	{
		int x, y;
		float d;
		
		x = 0;
		y = r;
		d = 5/4 - r;
		plotpoints(x0, y0, x, y, g);
		
		while(y>x)
		{
			if(d<0)
			{
				d = d + 2 * x + 3;
				x++;
			}
			else
			{
				d = d + 2 * (x - y) + 5;
				x++;
				y--;
			}
			plotpoints(x0, y0, x, y, g);
		}
	}
	
	private void plotpoints(int x0, int y0, int x, int y, Graphics g) {
		g.drawLine(x0+x, y0+y, x0+x, y0+y);
		g.drawLine(x0+y, y0+x, x0+y, y0+x);
		g.drawLine(x0+y, y0-x, x0+y, y0-x);
		g.drawLine(x0+x, y0-y, x0+x, y0-y);
		
		g.drawLine(x0-x, y0-y, x0-x, y0-y);
		g.drawLine(x0-y, y0-x, x0-y, y0-x);
		g.drawLine(x0-y, y0+x, x0-y, y0+x);
		g.drawLine(x0-x, y0+y, x0-x, y0+y);
	}

	//protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		int xh, yh, xm, ym, xs, ys, s, m, h, xcenter, ycenter;
		String today;
		
		Date dat = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss aa yyyy");
		cal.setTime(dat);
		
		s = (int) cal.get(Calendar.SECOND);
		m = (int) cal.get(Calendar.MINUTE);
		h = (int) cal.get(Calendar.HOUR_OF_DAY);
		
		today = df.format(dat);

		xcenter = 130;
		ycenter = 110;
		
		xs = (int) (Math.cos(s * 3.14f/30 - 3.14f/2) * 90 + xcenter);
		ys = (int) (Math.sin(s * 3.14f/30 - 3.14f/2) * 90 + ycenter);
		xm = (int) (Math.cos(m * 3.14f/30 - 3.14f/2) * 80 + xcenter);
		ym = (int) (Math.sin(m * 3.14f/30 - 3.14f/2) * 80 + ycenter);
		xh = (int) (Math.cos((h*30 + m/2) * 3.14f/180 - 3.14f/2) * 60 + xcenter);
		yh = (int) (Math.sin((h*30 + m/2) * 3.14f/180 - 3.14f/2) * 60 + ycenter);
		
		//g.setFont( new Font("Times New Roman", Font.PLAIN, 18));
		
		g.setFont( new Font("MahouaEMJ", Font.PLAIN, 18));
		//Color fgcol = new Color(Integer.parseInt(getp))
		
		
		
		g.setColor(Color.red);
		
		circle(xcenter, ycenter, 100, g);
		
		//g.drawString("Engr. Md. Kamrul Hasan Golap", 50, 250);
		int xtext=5, ytext=250;
		g.setFont( new Font("SolaimanLipi", Font.BOLD, 25));
		g.drawString("মোঃ কামরুল হাসান গোলাপ", xtext, ytext);
		g.setColor(Color.BLUE);
		g.setFont( new Font("SolaimanLipi", Font.PLAIN, 15));
		g.drawString("কম্পিউটার প্রকৌশলী", xtext+140, ytext+20);
		g.drawString("খুলনা প্রকৌশল ও প্রযুক্তি বিশ্ববিদ্যালয়।", xtext+45, ytext+40);
		
		
		g.setColor(Color.WHITE);
		for (int i = 0; i < 60; i++) {
			if(i%5 == 0) g.setColor(Color.BLACK);
			else g.setColor(Color.WHITE); 
			int lx = (int) (Math.cos(i * 3.14f/30 - 3.14f/2) * 90 + xcenter);
			int ly = (int) (Math.sin(i * 3.14f/30 - 3.14f/2) * 90 + ycenter);
			int x = (int) (Math.cos(i * 3.14f/30 - 3.14f/2) * 100 + xcenter);
			int y = (int) (Math.sin(i * 3.14f/30 - 3.14f/2) * 100 + ycenter);
			g.drawLine(lx, ly, x, y);
			
		}
		
		g.setColor(Color.GREEN);
		for (int i = 0; i < 60; i++) {
			int cenX=xcenter, cenY=100;
			int x = (int) (Math.cos(i * 3.14f/30 - 3.14f/2) * 45 + cenX);
			int y = (int) (Math.sin(i * 3.14f/30 - 3.14f/2) * 10 + cenY);
			g.drawLine(cenX, cenX, x, y);
			
//			cenX = 200;
//			cenY = 100;
//			int x1 = (int) (Math.cos(i * 3.14f/30 - 3.14f/2) * 45 + cenX);
//			int y1 = (int) (Math.sin(i * 3.14f/30 - 3.14f/2) * 10 + cenY);
//			g.drawLine(cenX, cenX, x1, y1);
//			
//			int x2 = (int) (Math.cos(i * 3.14f/30 - 3.14f/2) * 10 + xcenter);
//			int y2 = (int) (Math.sin(i * 3.14f/30 - 3.14f/2) * 45 + ycenter);
//			g.drawLine(ycenter, ycenter, x2, y2);
//			g.drawLine(ycenter+100, ycenter, x1, y1);
		}
		
		g.setFont( new Font("MahouaEMJ", Font.PLAIN, 20));
		//g.setFont( new Font("Siyam Rupali", Font.PLAIN, 20));
		//g.setFont( new Font("Kalpurush", Font.PLAIN, 20));
		g.setColor(Color.red);
		g.drawString("9", xcenter-90, ycenter+13);
		g.drawString("3", xcenter+60, ycenter+13);
		g.drawString("12", xcenter-25, ycenter-74);
		g.drawString("6", xcenter-15, ycenter+90);
		
		g.setColor(Color.RED);
		
		if(xs != lastxs || ys != lastys)
		{
			//g.drawLine(xcenter, ycenter, lastxs, lastys);
			//g.drawString(l, arg1, arg2);
		}
		if(xm != lastxm || ym != lastym)
		{
			//g.drawLine(xcenter, ycenter-1, lastxm, lastym);
			//g.drawLine(xcenter-1, ycenter, lastxm, lastym);
			//g.drawString(l, arg1, arg2);
		}
		if(xh != lastxh || yh != lastyh)
		{
			//g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
			//g.drawLine(xcenter-1, ycenter, lastxh, lastyh);
			//g.drawString(l, arg1, arg2);
		}
		
		g.setColor(Color.RED);
		//g.drawString(today, 5, 125);
		g.drawLine(xcenter, ycenter, xs, ys);
		
		
		g.setColor(Color.BLUE);
		g.drawLine(xcenter, ycenter, xm, ym);
		g.drawLine(xcenter-1, ycenter-1, xm, ym);
		g.drawLine(xcenter+1, ycenter+1, xm, ym);
		g.drawLine(xcenter, ycenter, xm, ym);
		g.drawLine(xcenter, ycenter, xm, ym);
		
//		g.drawLine(xcenter, ycenter, xm, ym);
//		g.drawLine(xcenter, ycenter-1, xm, ym);
//		g.drawLine(xcenter+1, ycenter, xm, ym);
//		g.drawLine(xcenter, ycenter-2, xm, ym);
//		g.drawLine(xcenter+2, ycenter, xm, ym);
//		g.drawLine(xcenter, ycenter-3, xm, ym);
//		g.drawLine(xcenter+3, ycenter, xm, ym);
		
		g.setColor(Color.BLACK);
		g.fillOval(xcenter-5, ycenter-5, 10, 10);
		
		g.setColor(Color.BLACK);
		g.drawLine(xcenter, ycenter, xh, yh);
		g.drawLine(xcenter-1, ycenter-1, xh, yh);
		g.drawLine(xcenter+1, ycenter+1, xh, yh);
		g.drawLine(xcenter-2, ycenter-2, xh-1, yh-1);
		g.drawLine(xcenter+2, ycenter+2, xh+1, yh+1);
		g.drawLine(xcenter, ycenter, xh, yh);
//		g.drawLine(xcenter, ycenter, xh, yh);
//		g.drawLine(xcenter, ycenter-1, xh, yh);
//		g.drawLine(xcenter+1, ycenter, xh, yh);
//		g.drawLine(xcenter+2, ycenter, xh, yh);
//		g.drawLine(xcenter, ycenter-2, xh, yh);
//		g.drawLine(xcenter+3, ycenter, xh, yh);
//		g.drawLine(xcenter, ycenter-3, xh, yh);
//		g.drawLine(xcenter+4, ycenter, xh, yh);
//		g.drawLine(xcenter, ycenter-4, xh, yh);
		
//		g.drawLine(xcenter-5, ycenter, xh, yh);
//		g.drawLine(xcenter-6, ycenter, xh, yh);
//		g.drawLine(xcenter-7, ycenter, xh, yh);
//		g.drawLine(xcenter-8, ycenter, xh, yh);
//		g.drawLine(xcenter-9, ycenter, xh, yh);
		
		lastxs = xs; lastys = ys;
		lastxm = xh; lastyh = yh;
		lastxh = xh; lastyh = yh;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AnalogClockMain();
	}
	
	JDialog analogClockFrame;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			repaint();
		}
	}

}
