import java.io.IOException;


public class DiffusionMain {
	public static void main(String args[])throws IOException{
		
		ParFrame pf = new ParFrame();
		
		Particle par = new Particle(0.0, 0.0);
		par.printDisplacement();
		par.saveDisplacements(par.disList());
		
		Gas g = Gas.askGas();
		g.printMsd();
	}
}
