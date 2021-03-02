import java.util.ArrayList;

public class Course {
    public static void printCourse(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(800, 640);
        StdDraw.setXscale(-2.5, 2.5);
        StdDraw.setYscale(-2, 2);
        ArrayList<Step> stepList = new ArrayList<>();
        stepList.add( new Step("p1.png", -1.4, 0.4,1.05, "Names and scores of two players."));
        stepList.add( new Step("p1.png",0,1.5,1.12,"Here print who will play next."));
        stepList.add( new Step("p2.png",-0.7,-0.45,1.3,"click between the dots to set a line"));
        stepList.add( new Step("p3.png",0,1.4,1.2,"Then is your opponent's turn."));
        stepList.add( new Step("p4.png",-0.37,0.1,1.2,"The red arrows mark the last step."));
        stepList.add( new Step("p5.png",0,0.7,2,"The one seal the box can win a score and go another step."));
        stepList.add(new Step("p5.png",0.5,0.15,1.9,"If you regret, click Undo to revoke and continue to play"));
        stepList.add( new Step("p6.png",-0.1,-1.1,1.6,"Then...(follow the rules) you will reach a dilemma."));
        stepList.add( new Step("p6.png",-0.1,-1.1,1.7,"Don't worry! We prepare some tips given by examples"));
        stepList.add( new Step("p7-1.png",-0.75,0.7,1.3,"tip1:if necessary, give the smallest block."));
        stepList.add( new Step("p7-2.png",0,1.3,1.9,"If the boxes are eaten, he have to give you some boxes."));
        stepList.add( new Step("p8.png",0,0.25,1.3,"Here comes tip2: Don't be too greedy."));
        stepList.add( new Step("p9.png",0.3,1.35,1,"If you give him two boxes..."));
        stepList.add( new Step ("p10.png",0,0.25,1,"You will win more!"));
        stepList.add(new Step("p11-1.png", -0.5, 0.75, 1.8, "If your opponent is even smarter, use tip2 here."));
        stepList.add(new Step("p11-2.png", 0, 1.35, 1.1, "You will lose according to tip2."));
        stepList.add(new Step("p12.png", -0.65, 0.55, 1.8, "But!! We have tip3:If you give him the boxes this way..."));
        stepList.add(new Step("p13.png", 0, 1.4, 1.1, "He will have no chance,and..."));
        stepList.add(new Step("p10.png", 0, 0.25, 1, "You will win for sure!!!"));
        int i = 0;
        while (i < stepList.size()) {
            stepList.get(i).printStep();
            if(i ==0 || i == 2 || i == 4 || i == 9 || i == 10|| i == 12 || i == 16 || i ==14 ){
                stepList.get(i).printArrow();
            }
            if(i == 8){
                StdDraw.picture(-0.1,0.18,"zb.jpg",2.4,1.92);
                StdDraw.show();
            }
            Step.printButton(1.62,1.5,-2.32,1.5,"Next","Back");
            while (true) {
                if (Initial.isIn(1.72, 2.62, 1.4, 1.6)) {
                    if (StdDraw.isMousePressed()) {
                        i++;
                        break;
                    }
                }
                else if (Initial.isIn(-2.22, -1.32, 1.4, 1.6)) {
                    if (StdDraw.isMousePressed()) {
                        if(i == 0){
                            return;
                        }
                        else {i--;
                              break;
                        }
                    }
                }
                else if(Initial.isIn(-2.5,-1.65,-2,-1.8)){
                    if(StdDraw.isMousePressed()){
                        return;
                    }
                }
            }
        }


    }
}

