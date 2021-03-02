import java.awt.*;

public class Title {
    public static void printBackground() {
        StdDraw.picture(0, 0, "titleBGP.jpg", 5, 4);
        Font fontTitle = new Font("Ink Free", 1, 85);
        StdDraw.setFont(fontTitle);
        StdDraw.text(0, -1.5, "Dots and Boxes");
    }

    public static void printButton(double a, double b, double c, double d,double e,double f, String s1, String s2, String s3) {
        StdDraw.setPenColor(106,90,205);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(a + 0.15, b + 0.1, a + 1.05, b + 0.1);
        StdDraw.line(c + 0.15, d + 0.1, c + 1.05, d + 0.1);
        StdDraw.line(e + 0.15, f + 0.1, e + 1.05, f + 0.1);
        StdDraw.setPenRadius();
        Font fontButton = new Font("Ink Free", 0, 30);
        StdDraw.setFont(fontButton);
        StdDraw.textLeft(a + 0.2, b, s1);
        StdDraw.textLeft(c + 0.2, d, s2);
        StdDraw.textLeft(e + 0.2, f, s3);
        for (double t = 0.0; t <= Math.PI; t += 0.02) {
            double y = -0.08 * Math.cos(t) + 0.04 * Math.cos(2 * t);
            double x = 0.08 * Math.sin(t) - 0.04 * Math.sin(2 * t);
            StdDraw.filledCircle(x + a, y + b, 0.01);
            StdDraw.filledCircle(-x + a, y + b, 0.01);
            StdDraw.filledCircle(x + c, y + d, 0.01);
            StdDraw.filledCircle(-x + c, y + d, 0.01);
            StdDraw.filledCircle(x + e, y + f, 0.01);
            StdDraw.filledCircle(-x + e, y + f, 0.01);
            StdDraw.show();
            StdDraw.pause(2);
        }
    }

    public static void printTeam() {
        StdDraw.clear();
        StdDraw.picture(0,1,"teamBGP.png", 5, 4);
        StdDraw.setFont();
        Font fontTitle = new Font("Ink Free", 1, 56);
        StdDraw.setFont(fontTitle);
        StdDraw.text(0,-1.5,"Our Team");
        Font font = new Font("Ink Free", 0, 27);
        StdDraw.setFont(font);
        StdDraw.setPenColor();
        StdDraw.textRight(-0.4,-1.1,"Group Members:     ");
        StdDraw.textRight(-0.4,-0.8,"Music Resources:     ");
        StdDraw.textLeft(-0.4,-1.1,"Nie Qiushi , Huang Runqing");
        StdDraw.picture(0.3, -0.8, "music1.png");
        StdDraw.picture(0.4, -0.5, "music2.png");
        StdDraw.picture(0.15, -0.2, "music3.png");
//        StdDraw.textLeft(0,-0.8,"空想森林");
//        StdDraw.textLeft(0,-0.5,"ヨルシカ - 夏陰、ピアノを弾く.mp3");
//        StdDraw.textLeft(0,-0.2,"Nanofingers - Henry.mp3");
        printButton(-0.3,1.5,-0.3,1.5,-0.3,1.5,"Return","Return","Return");
        StdDraw.show();
        while(true){
            if(Initial.isIn(-0.2,0.8,1.4,1.6)){
                if(StdDraw.isMousePressed()){
                    return;
                }
            }
        }
    }

    public static void printTitle(){
        printBackground();
        printButton(-1.1, -0.5, -1.1, 0, -1.1,0.5,"New Game", "Our Team", "Course");
    }

    public static int num(){
        while (true) {
            if (Initial.isIn(-1, 0.85, -0.6, -0.4)) {
                if(StdDraw.isMousePressed()) {
                    return 1;
                }
            } else if (Initial.isIn(-1, 0.85, -0.1, 0.1)) {
                if(StdDraw.isMousePressed()){
                    return 0;
                }
            }else if(Initial.isIn(-1,0.85,0.4,0.6)){
                if(StdDraw.isMousePressed()){
                    return -1;
                }
            }
        }

    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(800, 640);
        StdDraw.setXscale(-2.5, 2.5);
        StdDraw.setYscale(-2, 2);
        while(true){
            printTitle();
            int n = num();
            if(n == 1)
                break;
            if(n == 0)
                printTeam();
            if(n == -1)
                Course.printCourse();
        }
        Initial.newGame();
        if (Initial.mod == 3) {
//            Initial.inputName();
        }
    }
}


