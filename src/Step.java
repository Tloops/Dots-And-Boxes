import java.awt.*;

public class Step {

    public String getPctureName() {
        return pictureName;
    }

    public void setPctureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLength(double length){
        this.length = length;
    }

    private String pictureName;
    private double x1;
    private double y1;
    private double length;
    private String text;

    public Step(String pictureName, double x1, double y1,double length,  String text ){
        this.pictureName =pictureName;
        this.x1 = x1;
        this.y1 = y1;
        this.length = length;
        this.text = text;
    }


    public void printStep(){
        StdDraw.picture(5.46,1.55,pictureName,16,8);
        StdDraw.show();
        Font font = new Font("Ink Free", 2, 24);
        StdDraw.setFont(font);
        StdDraw.setPenColor(205,92,92);
        StdDraw.setPenRadius(0.01);
        StdDraw.rectangle(x1,y1,length,0.1);
        StdDraw.setPenColor(238,180,180);
        StdDraw.filledRectangle(x1,y1,length,0.1);
        StdDraw.setPenColor(205,92,92);
        StdDraw.text(x1,y1,text);
        StdDraw.setPenColor(106,90,205);
        StdDraw.filledRectangle(-2.15,-1.9,0.35,0.1);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(-2.15, -1.9, "Main Menu");
    }

    public static void printButton(double a, double b, double c, double d, String s1, String s2) {
        StdDraw.setPenColor(106,90,205);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(a + 0.15, b + 0.1, a + 0.85, b + 0.1);
        StdDraw.line(c + 0.15, d + 0.1, c + 0.85, d + 0.1);
        StdDraw.setPenRadius();
        Font fontButton = new Font("Ink Free", 1, 30);
        StdDraw.setFont(fontButton);
        StdDraw.textLeft(a + 0.2, b, s1);
        StdDraw.textLeft(c + 0.2, d, s2);
        for (double t = 0.0; t <= Math.PI; t += 0.02) {
            double y = -0.08 * Math.cos(t) + 0.04 * Math.cos(2 * t);
            double x = 0.08 * Math.sin(t) - 0.04 * Math.sin(2 * t);
            StdDraw.filledCircle(x + a, y + b, 0.01);
            StdDraw.filledCircle(-x + a, y + b, 0.01);
            StdDraw.filledCircle(x + c, y + d, 0.01);
            StdDraw.filledCircle(-x + c, y + d, 0.01);
            StdDraw.show();
            StdDraw.pause(1);
        }

        }

    public void printArrow(){
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(205,92,92);
        StdDraw.filledRectangle(x1,y1-0.2,0.01,0.1);
        StdDraw.line(x1,y1 - 0.3,x1-0.05,y1 - 0.25);
        StdDraw.line(x1,y1 - 0.3, x1 + 0.05, y1 - 0.25);
        StdDraw.show();
    }

}
