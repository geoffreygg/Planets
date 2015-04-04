public class Planet {
	double x;
	double y;
	double xVelocity;
	double yVelocity;
	double mass;
	String img;
	double g = 6.67e-11;
	double xNetForce;
	double yNetForce;
	double xAccel;
	double yAccel;

	public Planet(double xPos, double yPos, double xVel, double yVel, double mas, String image){
		x = xPos;
		y = yPos;
		xVelocity = xVel;
		yVelocity = yVel;
		mass = mas;
		img = image;
	}

	public double calcDistance(Planet planetX){
		double dx = x - planetX.x;
		double dy = y - planetX.y;
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double calcPairwiseForce(Planet planetX){
		double r = calcDistance(planetX);
		return g * mass * planetX.mass / (r * r);
	}

	public double calcPairwiseForceX(Planet planetX){
		double dx = planetX.x - x;
		return calcPairwiseForce(planetX) * dx / calcDistance(planetX);
	}

	public double calcPairwiseForceY(Planet planetX){
		double dy = planetX.y - y;
		return calcPairwiseForce(planetX) * dy / calcDistance(planetX);
	}

	public void setNetForce(Planet[] planets){
		int i = 0;
		xNetForce = 0;
		yNetForce = 0;
		while (i < planets.length) {
			if (this != planets[i]){
				xNetForce += calcPairwiseForceX(planets[i]);
				yNetForce += calcPairwiseForceY(planets[i]);
			}
			i += 1;
		}
	}

	public void draw(){
		StdDraw.picture(x,y,"images/" + img);
	}

	public void update(double dt){
		xAccel = xNetForce / mass;
		yAccel = yNetForce / mass;
		xVelocity += xAccel * dt;
		yVelocity += yAccel * dt;
		x += dt * xVelocity;
		y += dt * yVelocity;
	}
}












