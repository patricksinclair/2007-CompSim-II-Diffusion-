import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Particle {
	private double x, y, x0, y0;
	private ArrayList<Double> disList = new ArrayList<Double>();

	public Particle(double x, double y){
		this.x = x;
		this.y = y;
		this.x0 = x;
		this.y0 = y;
	}

	public double getX(){
		return x;
	}
	public void setX(double x){
		this.x = x;
	}
	public double getY(){
		return y;
	}
	public void setY(double y){
		this.y = y;
	}
	public double getX0(){
		return x0;
	}
	public double getY0(){
		return y0;
	}
	public ArrayList<Double> disList(){
		return disList;
	}


	public void diffuse(int tSteps){

		for(int i = 0; i < tSteps; i++){
			double r = Math.random();
			if(r < 0.25) setX(getX() + 1);
			else if(r >= 0.25 && r < 0.5) setX(getX() - 1);
			else if(r >= 0.5 && r < 0.75) setY(getY() + 1);
			else if(r > 0.75) setY(getY() - 1);
			disList().add(displacement());
		}		
	}


	public double displacement(){

		return Math.sqrt((getX() - getX0())*(getX() - getX0()) + (getY() - getY0())*(getY() - getY0()));

	}


	public void saveDisplacements(ArrayList<Double> dList)throws IOException{

		String filename = askFilename();
		int interval = disList().size()/10;
		BufferedWriter out = new BufferedWriter(new FileWriter(new File(filename)));

		for(int i = 0; i < disList().size(); i++){
			int t = i+1;
			if(t%interval == 0){
				String str = String.format("At %d timesteps, the displacement is: %.2f\n", t, disList().get(i));
				out.write(str);
			}
		}
		out.close();
		System.out.println("The results have been saved to: "+filename);
	}


	public void printDisplacement(){

		int t = askTSteps();
		diffuse(t);
		double s = displacement();	
		String str = String.format("The distance travelled after %d timesteps is: %.2f",t, s);
		System.out.println(str);
	}


	public static int askTSteps(){

		Scanner keyboard = new Scanner(System.in);
		int tSteps = 0;

		while(true){
			try{
				System.out.println("Enter the number of timesteps: ");
				tSteps = keyboard.nextInt();
				if(tSteps >= 50) break;
				System.out.println("Value must be at least 50.");
			}catch(InputMismatchException e){
				System.out.println("Please enter an integer.");
				keyboard.nextLine();
			}
		}
		return tSteps;
	}


	public static String askFilename(){

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the name of a file to save the results to: ");
		String filename = keyboard.next();

		return filename.trim()+".txt";
	}






}
