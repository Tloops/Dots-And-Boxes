import java.awt.*;

public class Game {

	public static final int TUTORIAL = 1, PVP_MODE = 2, STORY_MODE = 3;
	public static StackOfStep stack = new StackOfStep();
	
	private Game() {}
	
	private static void battle(int mode, int m, int n, int difficulty, 
			boolean isFirst, String name1, String name2) {
		stack.clear();
		isBackgroundPrinted = false;
		StdDraw.enableDoubleBuffering();
		Player p1,p2;
		if(mode == TUTORIAL) {
			p1 = new Machine(difficulty);
			p2 = new Machine(difficulty);
		}
		else if(mode == STORY_MODE) {
			if(isFirst) {
				p1 = new Humans(name1);
				p2 = new Machine(difficulty);
			}
			else {
				p1 = new Machine(difficulty);
				p2 = new Humans(name1);
			}
		}
		else {
			p1 = new Humans(name1);
			p2 = new Humans(name2);
		}
		
		int[][] DAB = new int[2*m-1][2*n-1];
		printGame(DAB, p1, p2);
		p1.setTurn(true);
		p2.setTurn(false);
		Humans.hasExit = false;
		while(!isFull(DAB)) {
			if(p1.isTurn()) {
				do {
					if(isFull(DAB))
						break;
					p1.move(DAB, p2, stack);
					correctMistake(DAB, p1, p2);
//					printGame(DAB, p1, p2);
					if(Humans.hasUndone)	break;
					if(Humans.hasExit)		return;
					if(!hasScored(DAB, p1, p2)) {
						printGame(DAB, p1, p2);
						p1.setTurn(false);
						p2.setTurn(true);
						break;
					}
					else {
						printGame(DAB, p1, p2);
					}
				}while(true);//scored to do again
			}
			else {
				do {
					if(isFull(DAB))
						break;
					p2.move(DAB, p1, stack);
					correctMistake(DAB, p2, p1);
//					printGame(DAB, p1, p2);
					if(Humans.hasUndone)	break;
					if(Humans.hasExit)		return;
					if(!hasScored(DAB, p2, p1)) {
						printGame(DAB, p1, p2);
						p1.setTurn(true);
						p2.setTurn(false);
						break;
					}
					else {
						printGame(DAB, p1, p2);
					}
				}while(true);
			}
		}
		printFinal(DAB, p1, p2, m, n);
		battleNum++;
		if(mode == STORY_MODE) {
			StdDraw.save("final" + battleNum + ".png");
			printBQB(DAB, p1, p2, m, n);
		}
		click(DAB);
		StdDraw.loading();
	}
	
	private static int battleNum = 0;
	
	public static void tutorial(int m, int n, int difficulty) {
		battle(TUTORIAL, m, n, difficulty, false, null, null);
	}
	
	public static void storyMode(int m, int n, int difficulty, boolean isFirst, String name) {
		battle(STORY_MODE, m, n, difficulty, isFirst, name, null);
	}
	
	public static void pvpMode(int m, int n, String name1, String name2) {
		battle(PVP_MODE, m, n, 0, false, name1, name2);
	}
	
	public static boolean isFull(int[][] a) {//private
		boolean isFull = true;
		for(int i = 1; i < a.length; i+=2) {
			for(int j = 1; j < a[i].length; j+=2) {
				if(a[i][j] == 0) {
					isFull = false;
					break;
				}
			}
			if(!isFull)	break;
		}
		return isFull;
	}
	
	public static boolean hasScored(int[][] a, Player p, Player e) {//private
		boolean hasScored = false;
		for(int i = 1; i < a.length; i += 2) {
			for(int j = 1; j < a[i].length; j += 2) {
				if(a[i][j] == 0 && a[i-1][j] != 0 && a[i+1][j] != 0 
						&& a[i][j-1] != 0 && a[i][j+1] != 0) {
					hasScored = true;
					p.setScore(p.getScore() + 1);
					a[i][j] = p.getNum();
				}
			}
		}
		return hasScored;
	}
	
	public static void correctMistake(int[][] a, Player p, Player e) {//private
		for(int i = 1; i < a.length; i += 2) {
			for(int j = 1; j < a[i].length; j += 2) {
				if(a[i][j] != 0 && (a[i-1][j] == 0 || a[i+1][j] == 0 
						|| a[i][j-1] == 0 || a[i][j+1] == 0)) {
					if(a[i][j] == p.getNum()) {
						p.setScore(p.getScore() - 1);
						a[i][j] = 0;
					}
					else {
						e.setScore(e.getScore() - 1);
						a[i][j] = 0;
					}
				}
			}
		}
	}
	
	private static void printFinal(int[][] a, Player p1, Player p2, int m, int n) {//private
		printGame(a, p1, p2);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(new Font("Verdana",3,30));
		if(p1.getScore() > p2.getScore())	
			StdDraw.text((n - 0.5) / 2, m, p1.getName() + " wins!    ");
		else if(p1.getScore() < p2.getScore())	
			StdDraw.text((n - 0.5) / 2, m, p2.getName() + " wins!    ");
		else
			StdDraw.text((n - 0.5) / 2, m, "Game over, no winner!");
		StdDraw.show();
	}

	private static void printBQB(int[][] a, Player p1, Player p2, int m, int n) {
		for(double t = 0,x = t / 50 - 4; x < (n-1)/2.0; t++) {
			StdDraw.clear();
			StdDraw.picture((n+3.0)/2-2, (m+2.5)/2-2, "final" + battleNum + ".png");
			x = t / 50 - 4;
			if(p1 instanceof Humans && p1.getScore() < p2.getScore()
					|| p2 instanceof Humans && p1.getScore() > p2.getScore())
				StdDraw.picture(x, (m-1) / 2.0, "cxk.gif");
			else
				StdDraw.picture(x, (m-1) / 2.0, "keyi.gif", 3, 3);
			StdDraw.pause(2);
			StdDraw.show();
		}
	}
	
	private static void click(int[][] a) {
		StdDraw.pause(1500);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle((a.length + 1) / 2, (a[0].length + 1) / 2, 20, 0.5);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(new Font("Ink Free", 1, 30));
		StdDraw.text((StdDraw.xmin + StdDraw.xmax)/2, (a[0].length + 1) / 2, "click ANYWHERE to main menu");
		StdDraw.show();
		while(true) {
			if(StdDraw.isMousePressed()) {
				buffer();
				return;
			}
		}
	}
	
	public static void buffer() {
		while(true)
			if(!StdDraw.isMousePressed())
				break;
	}
	
	public static void main(String[] args) {
		storyMode(3, 3, Machine.MEDIUM, true, "wew");
	}
	
	private static boolean isBackgroundPrinted = false;
    
    public static void printGame(int[][] a, Player p1, Player p2) {
    	if(!isBackgroundPrinted) {
    		setBackground((a.length + 1) / 2, (a[0].length + 1) / 2);
    		isBackgroundPrinted = true;
    	}
    	StdDraw.clear();
    	setBGPicture((a.length + 1) / 2, (a[0].length + 1) / 2);
    	printTitle((a[0].length + 1) / 2);
    	printNames(p1, p2);
    	printSquare(a, p1, p2);
    	printLines(a);
    	printDots((a.length + 1) / 2, (a[0].length + 1) / 2);
//    	if(p1 instanceof Humans || p2 instanceof Humans) {
//    		if(Machine.isEndGame) {
//    			StdDraw.picture(((a.length + 1) / 2 - 0.5)/2, (a[0].length + 1) / 2, "saying.jpg");
//    		}
//    	}
    	if(!isFull(a)) {
    		if(p1 instanceof Humans || p2 instanceof Humans) {
    			printUndoButton((a[0].length + 1) / 2, 0.5);
    			printMainMenuButton((a[0].length + 1) / 2, 1.5);
    		}
    		printTurn(p1, p2, (a.length + 1) / 2, (a[0].length + 1) / 2);
    	}
    	StdDraw.show();
    }
    
    private static void setBackground(int row, int col) {
    	StdDraw.setCanvasSize((col + 2) * 100, row * 100 + 150);//size size 
    	StdDraw.setXscale(-2, col + 1);
    	StdDraw.setYscale(-2, row + 0.5);
    	StdDraw.enableDoubleBuffering();
    }
    
    private static void setBGPicture(int row, int col) {
    	StdDraw.picture(row - 2, 3, "backfi.png", 16 * 1.15, 9 * 1.15);
    }
    
    private static void printTitle(int col) {
    	StdDraw.setFont(new Font("Ink Free",1,50 + col * 2));//title font
    	StdDraw.setPenColor(0, 255, 127);//title color, spring green
    	StdDraw.text((StdDraw.xmin + StdDraw.xmax)/2, -1.25, "~Dots and Boxes~");
    }
    
    private static void printNames(Player m1, Player m2) {
    	StdDraw.setFont(new Font("Verdana",2,15));
    	StdDraw.setPenColor(StdDraw.MAGENTA);
    	StdDraw.setPenRadius(0.003);
    	StdDraw.rectangle(-1, 0.75, 0.8, 0.6);
    	StdDraw.setPenColor(StdDraw.BLACK);
    	StdDraw.text(-1, 0.5, m1.getName() + ":" + m1.getScore());
    	StdDraw.text(-1, 1, m2.getName() + ":" + m2.getScore()); 
    	if(m1.isTurn())	{
    		StdDraw.setPenColor(0, 0, 255);
    		StdDraw.filledCircle(-1.9, 0.5, 0.07);
    	}
    	else {
    		StdDraw.setPenColor(255, 215, 0);
    		StdDraw.filledCircle(-1.9, 1, 0.07);
    	}
    }
    
    private static void printDots(int row, int col) {
    	StdDraw.setPenColor(StdDraw.BOOK_RED);//dot color
    	for(int i = 0; i < col; i++)
    		for(int j = 0; j < row; j++)
    			StdDraw.filledCircle(i, j, 0.07);
    }
    
    private static void printLines(int[][] a) {
    	int[] step;
		for(int i = 0; i < a.length; i++) {
			int j = i % 2 == 0 ? 1 : 0;
			for(; j < a[0].length; j += 2) {
				if(a[i][j] == 1) {
					StdDraw.setPenColor(0, 0, 255);//Blue
					if(i % 2 == 0)
						StdDraw.filledRectangle(j/2.0, i/2.0, 0.5, 0.035);
					else
						StdDraw.filledRectangle(j/2.0, i/2.0, 0.035, 0.5);
				}
				else if(a[i][j] == 2) {
					StdDraw.setPenColor(255, 215, 0);//Gold
					if(i % 2 == 0)
						StdDraw.filledRectangle(j/2.0, i/2.0, 0.5, 0.035);
					else
						StdDraw.filledRectangle(j/2.0, i/2.0, 0.035, 0.5);
				}
				else if(a[i][j] == 3) {
					StdDraw.setPenColor(new Color(0, 0, 255, 100));
					if(i % 2 == 0)
						StdDraw.filledRectangle(j/2.0, i/2.0, 0.5, 0.035);
					else
						StdDraw.filledRectangle(j/2.0, i/2.0, 0.035, 0.5);
				}
				else if(a[i][j] == 4) {
					StdDraw.setPenColor(new Color(255, 215, 0, 120));
					if(i % 2 == 0)
						StdDraw.filledRectangle(j/2.0, i/2.0, 0.5, 0.035);
					else
						StdDraw.filledRectangle(j/2.0, i/2.0, 0.035, 0.5);
				}
			}
		}
		if(stack.getSize() != 0) {
    		step = stack.peek();
    		for(int i = 0; i < a.length; i++) {
    			int j = i % 2 == 0 ? 1 : 0;
    			for(; j < a[0].length; j += 2) {
    				if(i == step[0] && j == step[1]) {
    					StdDraw.setPenColor(255, 0, 0);
    					StdDraw.setFont(new Font("Verdana",3,20));
    					if(i % 2 == 0)
    						StdDraw.text(j/2.0, i/2.0, "-><-", 90);
    					else
    						StdDraw.text(j/2.0, i/2.0 + 0.03, "-><-");
    				}	
    			}
			}
		}
    }

    private static void printSquare(int[][] a, Player p1, Player p2) {
    	for(int i = 1; i < a.length; i += 2) {
    		for(int j = 1; j < a[0].length; j += 2) {
    			if(a[i][j] == 1) {
    				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
    				StdDraw.filledSquare(j/2.0, i/2.0, 0.47);
    				StdDraw.setPenColor();
    				if(p1 instanceof Machine)
    					StdDraw.text(j/2.0, i/2.0, "1");
    				else
    					StdDraw.text(j/2.0, i/2.0, p1.getName().substring(0, 1) + "1");
    			}
    			else if(a[i][j] == 2) {
    				StdDraw.setPenColor(255, 106, 106);//Indian Red
    				StdDraw.filledSquare(j/2.0, i/2.0, 0.47);
    				StdDraw.setPenColor();
    				if(p2 instanceof Machine)
    					StdDraw.text(j/2.0, i/2.0, "2");
    				else
    					StdDraw.text(j/2.0, i/2.0, p2.getName().substring(0, 1) + "2");
    			}
    		}
    	}
    }
    
    private static void printTurn(Player p1, Player p2, int m, int n) {
    	if(p1.isTurn()) {
    		StdDraw.setPenColor(StdDraw.BLACK);
    		StdDraw.setFont(new Font("Verdana",3,25));
    		if(p1 instanceof Humans)
    			StdDraw.text((n - 0.5) / 2, m-0.5, p1.getName() + "'s Turn    ");
    		else
    			StdDraw.text((n - 0.5) / 2, m-0.5, "Thinking...  ");
    	}
    	else {
    		StdDraw.setPenColor(StdDraw.BLACK);
    		StdDraw.setFont(new Font("Verdana",3,25));
    		if(p2 instanceof Humans)
    			StdDraw.text((n - 0.5) / 2, m-0.5, p2.getName() + "'s Turn    ");
    		else
    			StdDraw.text((n - 0.5) / 2, m-0.5, "Thinking...  ");
    	}
    }
    
    private static void printUndoButton(double x, double y) {
    	StdDraw.setPenColor(125, 38, 205);//Black
    	StdDraw.filledRectangle(x, y, 0.45, 0.25);
    	StdDraw.setFont(new Font("Verdana", 0, 24));
    	StdDraw.setPenColor(StdDraw.WHITE);
    	StdDraw.text(x, y + 0.03, "Undo");
    }
    
    private static void printMainMenuButton(double x, double y) {
    	StdDraw.setPenColor(125, 38, 205);//Black
    	StdDraw.filledRectangle(x, y, 0.75, 0.25);
    	StdDraw.setFont(new Font("Verdana", 0, 24));
    	StdDraw.setPenColor(StdDraw.WHITE);
    	StdDraw.text(x, y + 0.03, "Main Menu");
    }
    
}
