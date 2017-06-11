import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


class ParPanel extends JPanel{

	Particle par = new Particle(0,0);
	private int tSteps;
	private int X, Y;
	Timer timer;

	public ParPanel(){
		setBackground(Color.CYAN);

		timer = new Timer(400, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diffuse();
			}
		});
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		X = getWidth();
		Y = getHeight();
		int w = 10;

		int xLoc = (int)getXloc()*5;
		int yLoc = (int)getYloc()*5;

		g2d.translate(X/2, Y/2);
		g.setColor(Color.RED);
		g.fillRect(xLoc, yLoc, w, w);
	}

	public double getXloc(){
		return par.getX();
	}
	public void setXloc(double xloc){
		par.setX(xloc);
	}
	public double getYloc(){
		return par.getY();
	}
	public void setYloc(double yloc){
		par.setY(yloc);
	}
	public int getTSteps(){
		return tSteps;
	}
	public void setTSteps(int tSteps){
		this.tSteps = tSteps;
	}

	public void diffuse(){


			int x0 = (int)getXloc();
			int y0 = (int)getYloc();
			double r = Math.random();
			
			if(r < 0.25) setXloc(getXloc() + 1);
			else if(r >= 0.25 && r < 0.5) setXloc(getXloc() - 1);
			else if(r >= 0.5 && r < 0.75) setYloc(getYloc() + 1);
			else if(r > 0.75) setYloc(getYloc() - 1);
			
			int x1 = (int)getXloc();
			int y1 = (int)getYloc();
			
			repaint();
			
		
	}


	public void reset(){
		par = new Particle(0, 0);
	}

}




public class ParFrame extends JFrame{

	private int X = 600, Y = 600;
	ParPanel ppan;
	JPanel control;
	JLabel tSteps;
	JTextField intext;
	JButton start, reset;


	public ParFrame(){

		tSteps = new JLabel("Enter the number of timesteps: ");
		intext = new JTextField(10);
		start = new JButton("Start");
		reset = new JButton("Reset");

		ppan = new ParPanel();
		control = new JPanel();

		control.add(tSteps);
		control.add(intext);
		control.add(start);
		control.add(reset);

		intext.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				intext.setText("");
			}
		});

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});


		setLayout(new BorderLayout());
		getContentPane().add(ppan, BorderLayout.CENTER);
		getContentPane().add(control, BorderLayout.SOUTH);

		setBackground(Color.LIGHT_GRAY);
		setSize(X, Y);
		setLocation(200, 0);
		setTitle("Diffusion");
		setVisible(true);

		renumerate();
		start();
		reset();
	}


	public void renumerate(){
		intext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int t = Integer.parseInt(intext.getText());
				ppan.setTSteps(t);
				repaint();
			}
		});

	}

	public void start(){
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i = 0; i < ppan.getTSteps(); i++){
				ppan.timer.start();
				repaint();
				//if(i == ppan.getTSteps() - 1) ppan.timer.stop();
				}
				//ppan.timer.stop();
			}
		});
	}

	public void reset(){
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ppan.reset();
				ppan.timer.stop();
				repaint();
			}
		});
	}
}
