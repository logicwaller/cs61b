public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
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
        return Math.sqrt(((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos)) + ((this.yyPos - p.yyPos) * (this.yyPos - p.yyPos)));
    }

    public double calcForceExertedBy(Planet p){
        double G = 6.67e-11;
        return (G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcForceExertedByX(Planet p){
        return (this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p));
    }

    public double calcForceExertedByY(Planet p){
        return (this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p));
    }

    public double calcNetForceExertedByX(Planet[] ps){
        double res = 0;
        for (Planet p : ps){
           if(!this.equals(p)){
               res += this.calcForceExertedByX(p);
           }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] ps){
        double res = 0;
        for (Planet p : ps){
            if(!this.equals(p)){
                res += this.calcForceExertedByY(p);
            }
        }
        return res;
    }

    public void update(double t, double Fx, double Fy){
        double ax = Fx / this.mass;
        double ay = Fy / this.mass;
        this.xxVel += (ax * t);
        this.yyVel += (ay * t);
        this.xxPos += (this.xxVel * t);
        this.yyPos += (this.yyVel * t);
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }
}
