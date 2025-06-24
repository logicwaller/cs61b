public class NBody {
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        /*读取大小和星星*/
        double radius = readRadius(filename);
        Planet[] ps = readPlanets(filename);

        /*绘制背景图*/
        StdDraw.setScale(-1 * radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /*绘制星星*/
        for (Planet p : ps){
            p.draw();
        }

        /*动画*/
        StdDraw.enableDoubleBuffering();
        int total = ps.length;
        for(double i = 0; i < T; i += dt){
            double[] xForces = new double[total];
            double[] yForces = new double[total];
            for(int j = 0; j < total; j++){
                xForces[j] = ps[j].calcNetForceExertedByX(ps);
                yForces[j] = ps[j].calcNetForceExertedByY(ps);
            }
            for(int j = 0; j < total; j++){
                ps[j].update(dt, xForces[j], yForces[j]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : ps){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                    ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
        }
    }

    public static double readRadius(String path){
        In in = new In(path);
        int tem = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int n = in.readInt();
        in.readDouble();
        double px, py, vx, vy, m;
        String img;
        Planet[] ps = new Planet[n];
        for(int i = 0; i < n; i++){
            px = in.readDouble();
            py = in.readDouble();
            vx = in.readDouble();
            vy = in.readDouble();
            m = in.readDouble();
            img = in.readString();
            Planet p = new Planet(px, py, vx, vy, m, img);
            ps[i] = p;
        }
        return ps;
    }

}
