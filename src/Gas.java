import java.util.InputMismatchException;
import java.util.Scanner;


public class Gas {
	private Particle[] particles;
	
	public Gas(Particle[] particles){
		this.particles = particles;
	}
	
	public Particle[] getParticles(){
		return particles;
	}
	public void setParticles(Particle[] particles){
		this.particles = particles;
	}
	
	
	public double msd(){
		
		int t = Particle.askTSteps();
		double runningtotal = 0;
		
		for(int i = 0; i < getParticles().length; i++){
			getParticles()[i] = new Particle(0, 0);
			getParticles()[i].diffuse(t);
			runningtotal += getParticles()[i].displacement()*getParticles()[i].displacement();
		}
		
		return runningtotal/(double)getParticles().length;
	}
	
	
	public void printMsd(){
		String str = String.format("For %d particles, the mean square distance is: %.2f", getParticles().length, msd());
		System.out.println(str);
	}
	
	
	
	
	public static Gas askGas(){
		Scanner keyboard = new Scanner(System.in);
		int n = 0;
		
		while(true){
			try{
				System.out.println("Enter the number of particles in the gas.");
				n = keyboard.nextInt();
				if(n >= 20) break;
				System.out.println("Value must be at least 20.");
			}catch(InputMismatchException e){
				System.out.println("Please enter an integer.");
				keyboard.nextLine();
			}
		}
		
		Particle[] pars = new Particle[20];
		
		return new Gas(pars);
	}
}
