import java.awt.*;

public class Initial {
    public static int mod = 0;
    public static int size = 0;
    public static int goFirst = 0;
    public static int difficulty = 0;
    public static String playerName1;
    public static String playerName2;
    
    public static void print(){
        StdDraw.picture(0, 0, "background.png", 8, 8);
        StdDraw.setPenColor(255, 120, 120);
        Font fontTitle = new Font("Ink Free", 1, 54);
        StdDraw.setFont(fontTitle);
        StdDraw.text(0, -3.5, "Welcome to Dots and Boxes");
        Font font = new Font("Ink Free", 0, 26);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(-3.8, -2.5, "Please choose the scale");
        StdDraw.textLeft(-3.5, -2, "3 * 3");
        StdDraw.textLeft(-1.9, -2, "4 * 4");
        StdDraw.textLeft(-0.3, -2, "5 * 5");
        StdDraw.circle(-3.7, -2, 0.05);
        StdDraw.circle(-2.1, -2, 0.05);
        StdDraw.circle(-0.5, -2, 0.05);
        StdDraw.textLeft(-3.8, -1.5, "Please choose the type");
        StdDraw.textLeft(-3.5, -1, "machine VS machine");
        StdDraw.textLeft(-0.85, -1, "human VS human");
        StdDraw.textLeft(1.5, -1, "human VS machine");
        StdDraw.circle(-3.7, -1, 0.05);
        StdDraw.circle(-1.05, -1, 0.05);
        StdDraw.circle(1.3, -1, 0.05);
        StdDraw.setFont(fontTitle);
        StdDraw.text(0, 1.5, "Next->");
        StdDraw.setPenColor(255, 140, 140);
        StdDraw.setPenRadius(0.01);
        double[] x = {-1, 1, 1, -1};
        double[] y = {1.2, 1.2, 1.8, 1.8};
        StdDraw.polygon(x, y);
        StdDraw.setFont(font);
        StdDraw.show();
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public static boolean isIn(double x1, double x2, double y1, double y2){
        if ((x1 < StdDraw.mouseX()) && (StdDraw.mouseX() < x2) ) {
            if ((y1 < StdDraw.mouseY()) && (StdDraw.mouseY() < y2))
                return true;
            else return false;
        }
        else return false;
    }

    
    public static void printChoose1( int size) {
        if (size == 1) {
            StdDraw.filledCircle(-3.7, -2, 0.05);
        } else if (size == 2) {
            StdDraw.filledCircle(-2.1, -2, 0.05);
        } else if (size == 3) {
            StdDraw.filledCircle(-0.5, -2, 0.05);
        }
    }

    public static void printChoose2(int mod,int goFirst, int difficulty){
        if(mod == 1){
            StdDraw.filledCircle(-3.7, -1, 0.05);
           Initial. printQuestion4();
           Initial.printChoose4(difficulty);

        }
        else if(mod == 2){
            StdDraw.filledCircle(-1.05, -1, 0.05);
        }
        else if(mod == 3){
            StdDraw.filledCircle(1.3, -1, 0.05);
            Initial.printQuestion3();
            Initial.printChoose3(goFirst);
            printQuestion4();
            printChoose4(difficulty);
        }
    }

    public static void printChoose3(int goFirst){
        if(goFirst == 1){
            StdDraw.filledCircle(-3.7, 0, 0.05);
        }
        else if(goFirst == 2){
            StdDraw.filledCircle(-2.1, 0, 0.05);
        }
    }

    public static void printChoose4(int difficulty){
        if(difficulty == 1){
            StdDraw.filledCircle(-3.7, 1, 0.05);
        } else if (difficulty == 2) {
            StdDraw.filledCircle(-2.1, 1, 0.05);
        } else if (difficulty == 3) {
            StdDraw.filledCircle(-0.5, 1, 0.05);
        }
    }

    public static void printQuestion3(){
        StdDraw.textLeft(-3.8,-0.5,"choose whether you want to go first");
        StdDraw.textLeft(-3.5,0,"yes");
        StdDraw.textLeft(-1.9,0,"no");
        StdDraw.circle(-3.7,0,0.05);
        StdDraw.circle(-2.1,0,0.05);
    }

    public static void printQuestion4(){
        StdDraw.textLeft(-3.8,0.5,"Please adjust the difficulty");
        StdDraw.circle(-3.7, 1, 0.05);
        StdDraw.circle(-2.1, 1, 0.05);
        StdDraw.circle(-0.5, 1, 0.05);
        StdDraw.textLeft(-3.5, 1, "easy");
        StdDraw.textLeft(-1.9, 1, "medium");
        StdDraw.textLeft(-0.3, 1, "hard");
    }

    public static void inputName(int num, String greet){
        StdDraw.setCanvasSize(800,500);
        StdDraw.setXscale(-2, 2);
        StdDraw.setYscale(-1.25, 1.25);
        StringBuilder str = new StringBuilder();

        while(true){
            StdDraw.clear();
            StdDraw.picture(0, 0, "nameBGP.jpg", 4, 2.5);
            StdDraw.setFont(new Font("Ink Free", 1, 40));
            StdDraw.setPenColor(255, 127, 80);//Coral
            StdDraw.textLeft(-1.9, -0.6, "Please input " + greet + " name: ");
            StdDraw.setPenRadius(0.005);
            StdDraw.rectangle(0,0, 1.05, 0.2);
            StdDraw.setPenColor();
            StdDraw.setFont(new Font("Ink Free", 0, 40));
            char c = '\u0000';
            String s = new String(str);
            if(StdDraw.hasNextKeyTyped())
                c = StdDraw.nextKeyTyped();
            if(c != '\u0000' && c != '\b' && c != '\n') {
                if (str.length() < 8) {
                    str.append(c);
                    s = new String(str);
                }
            }
            else if(c == '\b'){
                if(str.length() > 0)
                    str.deleteCharAt(str.length() - 1);
                StdDraw.clear();
                s = new String(str);
            }
            else if(c == '\n') {
            	if(str.length() != 0)
            		break;
            }
            if(str.length() == 8) {
                StdDraw.setFont(new Font("Ink Free", 0, 35));
                StdDraw.text(0, 0.35, "Your name should be no more than 8 characters!");
            }
            StdDraw.setFont(new Font("Ink Free", 0, 40));
            StdDraw.textLeft(-1, 0, s);
            StdDraw.setPenColor(255, 127, 80);
            StdDraw.rectangle(1, 0.8, 0.52, 0.13);
            StdDraw.setPenColor();
            StdDraw.setFont(new Font("Ink Free", 1, 50));
            StdDraw.text(1, 0.8, "Next->");
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.setFont(new Font("Ink Free", 0, 40));
            if(str.length() == 0){
                StdDraw.text(0,0 ,"Please enter your name");
            }
            StdDraw.show();
            if(0.48 < StdDraw.mouseX() && StdDraw.mouseX() < 1.52 && 0.67 < StdDraw.mouseY() && StdDraw.mouseY() <0.93){
                if(StdDraw.isMousePressed()){
                    if(str.length() != 0){
                        break;
                    }
                }
            }
        }
        if(num == 1)
        	playerName1 = new String(str);
        else
        	playerName2 = new String(str);
    }

    public static void newGame(){
    	mod = 0;
        size = 0;
        goFirst = 0;
        difficulty = 0;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(-4,4);
        StdDraw.setYscale(-4,4);
        Initial.print();
        StdDraw.pause(200);
        while(true) {
            if(Initial.isIn(-3.8,-2.9,-2.15,-1.85)){
                    if (StdDraw.isMousePressed()) {
                        Initial.print();
                        Initial.printChoose2(mod,goFirst,difficulty);
                        StdDraw.filledCircle(-3.7, -2, 0.05);
                        StdDraw.show();
                        size = 1;
                    }
            }
            else if(Initial.isIn(-2.2,-1.3,-2.15,-1.85)){
                if(StdDraw.isMousePressed()) {
                    Initial.print();
                    Initial.printChoose2(mod,goFirst,difficulty);
                    StdDraw.filledCircle(-2.1, -2, 0.05);
                    StdDraw.show();
                    size = 2;
                }
            }
            else  if(Initial.isIn(-0.6, 0.3,-2.15 , -1.85)) {
                if (StdDraw.isMousePressed()) {
                    Initial.print();
                    Initial.printChoose2(mod,goFirst,difficulty);
                    StdDraw.filledCircle(-0.5, -2, 0.05);
                    StdDraw.show();
                    size = 3;
                }
            }
            else if (Initial.isIn(-3.8 , -1.3,-1.15 , -0.85)) {
                if (StdDraw.isMousePressed()) {
                    mod = 1;
                    goFirst = 0;
                    Initial.print();
                    Initial.printChoose1(size);
                    Initial.printChoose2(mod,goFirst,difficulty);
                    StdDraw.show();
                }
            }
            else if (Initial.isIn(-1.15, 1,-1.15 , -0.85)) {
                if (StdDraw.isMousePressed()) {
                    Initial.print();
                    Initial.printChoose1(size);
                    StdDraw.filledCircle(-1.05, -1, 0.05);
                    StdDraw.show();
                    mod = 2;
                    goFirst = 0;
                    difficulty = 0;
                }
            }
            else if (Initial.isIn(1.2 ,3.6,-1.15,-0.85)) {
                if (StdDraw.isMousePressed()) {
                    mod = 3;
                    Initial.print();
                    Initial.printChoose1(size);
                    Initial.printChoose2(mod,goFirst,difficulty);
                    Initial.printQuestion3();
                    Initial.printQuestion4();
                    StdDraw.show();
                }
            }
            else if(Initial.isIn(-3.8, -3.1,-0.1 , 0.1)) {
            	if(mod == 3){
	                if (StdDraw.isMousePressed()) {
	                    Initial.print();
	                    Initial.printChoose1(size);
	                    StdDraw.filledCircle(1.3, -1, 0.05);
	                    Initial.printQuestion3();
	                    StdDraw.filledCircle(-3.7, 0, 0.05);
	                    Initial.printQuestion4();
	                    Initial.printChoose4(difficulty);
	                    StdDraw.show();
	                    goFirst = 1;
	                }
                }
            }
            else if(Initial.isIn(-2.2, -1.7,-0.1 , 0.1)){
            	if(mod == 3) {
	                if(StdDraw.isMousePressed()) {
	                    Initial.print();
	                    Initial.printChoose1(size);
	                    StdDraw.filledCircle(1.3, -1, 0.05);
	                    Initial.printQuestion3();
	                    StdDraw.filledCircle(-2.1, 0, 0.05);
	                    printQuestion4();
	                    printChoose4(difficulty);
	                    StdDraw.show();
	                    goFirst = 2;
	                }
            	}
            }
            else if(Initial.isIn(-3.8,-2.9,0.85,1.15)){
            	if(mod == 1 || mod == 3) {
	                if(StdDraw.isMousePressed()){
	                    Initial.print();
	                    Initial.printChoose1(size);
	                    Initial.printChoose2(mod,goFirst,difficulty);
	                    StdDraw.filledCircle(-3.7, 1, 0.05);
	                    StdDraw.show();
	                    difficulty = 1;
	                }
            	}
            }
            else if(Initial.isIn(-2.2,-1.3,0.85,1.15)){
            	if(mod == 1 || mod == 3) {
	                if(StdDraw.isMousePressed()) {
	                    Initial.print();
	                    Initial.printChoose1(size);
	                    Initial.printChoose2(mod,goFirst,difficulty);
	                    StdDraw.filledCircle(-2.1, 1, 0.05);
	                    StdDraw.show();
	                    difficulty = 2;
	                }
            	}
            }
            else if(Initial.isIn(-0.6, 0.3, 0.85, 1.15)) {
            	if(mod == 1 || mod == 3) {
	                if(StdDraw.isMousePressed()) {
	                    Initial.print();
	                    Initial.printChoose1(size);
	                    Initial.printChoose2(mod,goFirst,difficulty);
	                    StdDraw.filledCircle(-0.5, 1, 0.05);
	                    StdDraw.show();
	                    difficulty = 3;
	                }
            	}
            }
            else if(Initial.isIn(-1, 1, 1.2, 1.8)) {
                if(StdDraw.isMousePressed()) {
                    if (size == 0 || mod == 0 || (mod == 3 && (goFirst == 0 || difficulty == 0))|| (mod ==1 && difficulty == 0)) {
                        StdDraw.text(0, 2, "Make sure you answer all the questions above");
                        StdDraw.show();
                    } else {
                        StdDraw.clear();
                        StdDraw.show();
                        break;
                    }
                }
            }
            StdDraw.pause(20);
        }
    }
}



