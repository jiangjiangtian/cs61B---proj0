public class Planet{
	public double xxPos;//Its current x position
	public double yyPos;//Its current y position
	public double xxVel;//Its current velocity in the x direction
	public double yyVel;//Its current velocity in the y direction
	public double mass;//Its mass
	public String imgFileName;//The name of the file that corresponds to the image that depicts the planet
	public static double graConstant = 6.67e-11;

	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double r = dx * dx + dy * dy;
		double sqrt = Math.sqrt(r);
		return sqrt;
	}

	public double calcForceExertedBy(Planet p){
		double dis = this.calcDistance(p);
		double Force = graConstant * this.mass * p.mass / dis / dis;
		return Force;
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double dis = this.calcDistance(p);
		double Force = this.calcForceExertedBy(p);
		double Forcex = Force * dx / dis;
		return Forcex;
	}

	public double calcForceExertedByY(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double dis = this.calcDistance(p);
		double Force = this.calcForceExertedBy(p);
		double Forcey = Force * dy / dis;
		return Forcey;
	}

	public double calcNetForceExertedByX(Planet[] p){
		double NetForceX = 0;
		for(Planet a : p){
			if(!this.equals(a)){
				NetForceX += this.calcForceExertedByX(a);
			}
		}
		return NetForceX;
	}

	public double calcNetForceExertedByY(Planet[] p){
		double NetForceY = 0;
		for(Planet a : p){
			if(!this.equals(a)){
				NetForceY += this.calcForceExertedByY(a);
			}
		}
		return NetForceY;
	}

	public void update(double dt, double ForceX, double ForceY){
		double aX = ForceX / this.mass;
		double aY = ForceY / this.mass;
		this.xxVel = this.xxVel + aX * dt;
		this.yyVel = this.yyVel + aY * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"E:/java/cs61B/cs61b1/proj0/images/" + this.imgFileName);
	}
}