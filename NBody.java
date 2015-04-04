public class NBody {

    public static void main(String[] args) {
	    double T = Double.parseDouble(args[0]);
	    double dt = Double.parseDouble(args[1]);
	    String filename = args[2];
	    In in = new In(filename);
	    int num_planets = in.readInt();
	    Double scale = in.readDouble();
	    int i = 0;
	    Planet[] planets = new Planet[num_planets];
	    while (i < num_planets){
	    	planets[i] = getPlanet(in);
	    	i += 1;
	    }
	    StdDraw.setScale(-scale,scale);
	    StdDraw.picture(0,0,"images/starfield.jpg");
	    i = 0;
	    while (i < num_planets){
	    	planets[i].draw();
	    	i += 1;
	    }
	    double time = 0;
	    while (time <= T){
	    	i = 0;
	    	while (i < num_planets){
	    		planets[i].setNetForce(planets);
	    		i += 1;
	    	}
	    	StdDraw.picture(0,0,"images/starfield.jpg");
	    	i = 0;
	    	while (i < num_planets){
	    		planets[i].update(dt);
	    		planets[i].draw();
	    		i += 1;
	    	}
	    	StdDraw.show(10);
	    	time += dt;
	    }
	    StdOut.printf("%d\n", num_planets);
		StdOut.printf("%.2e\n", scale);
		for (Planet p : planets) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            p.x, p.y, p.xVelocity, p.yVelocity, p.mass, p.img);
		}
    }

    public static Planet getPlanet(In in){
    	Double x1 = in.readDouble();
    	Double y1 = in.readDouble();
    	Double xVel1 = in.readDouble();
    	Double yVel1 = in.readDouble();
    	Double mass1 = in.readDouble();
    	String string1 = in.readString();
    	Planet planetX = new Planet(x1,y1,xVel1,yVel1,mass1,string1);
    	return planetX;
    }
}