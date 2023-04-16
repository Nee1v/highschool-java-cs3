
public class GuitarHero 
{
	public static void main(String[] args) {

        // Create two guitar strings, for concert A and C
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; 
		GuitarString[] g = new GuitarString[37];
        
        for(int i = 0; i < 37; i++)
        {
        	double note = 440 * Math.pow(2, ((i+1)-24)/12.0);
        	GuitarString string = new GuitarString(note);
        	g[i] = string;
        }


        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                
                int n = keyboard.indexOf(key);
                
                try
                {
                	g[n].pluck();
                }
                catch(Exception e)
                {
                	
                }
            }

            // compute the superposition of all the samples
            double s = 0;
            for(int i = 0; i < g.length; i++)
            {
            	s = s + g[i].sample();
            }
            double sample = s;

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for(int i = 0; i < g.length; i++)
            {
            	g[i].tic();
            }
        }
    }
}
