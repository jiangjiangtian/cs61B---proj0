public class NBody {
    public static double readRadius(String name){
        In in = new In("E:/java/cs61B/cs61b1/proj0/" + name);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets(String name){
        In in = new In("E://java//cs61B//cs61b1//proj0/" + name);
        int N = in.readInt();
        in.readDouble();
        Planet[] p = new Planet[N];
        int i = 0;
        while(i<p.length){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String n = in.readString();
            p[i] = new Planet(xp,yp, xv, yv, m,n);
            i++;
        }
        return p;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] p = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius,radius);
        StdDraw.picture(0.0,0.0,"E://java//cs61B//cs61b1//proj0//images//starfield.jpg");

        for(Planet p1 : p){
            p1.draw();
        }

        StdDraw.enableDoubleBuffering();

        double dtt = 0;
        while(dtt <= T){
            double[] xForces = new double[p.length];
            double[] yForces = new double[p.length];
            int i = 0;
            for(Planet p1 : p){
                xForces[i] = p1.calcNetForceExertedByX(p);
                yForces[i] = p1.calcNetForceExertedByY(p);
                i++;
            }
            for(i = 0 ; i < p.length;i++){
                p[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0.0,0.0,"E://java//cs61B//cs61b1//proj0//images//starfield.jpg");

            for(Planet p1 : p){
                p1.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            dtt += dt;
        }

        StdOut.println(p.length);
        StdOut.println(radius);
        for(Planet p1 : p){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p1.xxPos,p1.yyPos,p1.xxVel,p1.yyVel,p1.mass,p1.imgFileName);

        }
    }
}