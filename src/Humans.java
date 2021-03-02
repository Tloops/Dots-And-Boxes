import java.awt.Font;

public class Humans extends Player{

	public Humans(String name){
		setName(name);
	}
	
	public static boolean hasMoved = false;
	public static boolean hasUndone = false;
	public static boolean hasExit = false;
	
	public void move(int[][] a, Player e, StackOfStep stack) {
		StdDraw.pause(2);
		while(true) {
			hasUndone = false;
			if(StdDraw.mouseX() < (a[0].length + 1) / 2 + 0.45 
					&& StdDraw.mouseX() > (a[0].length + 1) / 2 - 0.45 
					&& StdDraw.mouseY() < 0.75 
					&& StdDraw.mouseY() > 0.25) {
				if(StdDraw.isMousePressed()) {
					if(stack.getSize() != 0) {
						if(e instanceof Humans) {
							int[] step = stack.remove();
							hasUndone = true;
							a[step[0]][step[1]] = 0;
							if(step[2] == e.getNum()) {
								this.setTurn(false);
								e.setTurn(true);
							}
							Game.buffer();
							break;
						}
						else if(e instanceof Machine) {
							if(stack.getSize() <= 1 && Initial.goFirst == 2) {
								
							}
							else {
								int x;
								do {
									int[] step = stack.remove();
									hasUndone = true;
									a[step[0]][step[1]] = 0;
									x = step[2];
								}while(x == e.getNum()&&stack.getSize() > 0);
								this.setTurn(true);
								e.setTurn(false);
								Game.buffer();
								break;
							}
						}
					}
				}
			}
			
			if(this.getNum() < e.getNum())
				Game.printGame(a, this, e);
			else
				Game.printGame(a, e, this);
			
			hasExit = false;
			if(StdDraw.mouseX() < (a[0].length + 1) / 2 + 0.75 
					&& StdDraw.mouseX() > (a[0].length + 1) / 2 - 0.75 
					&& StdDraw.mouseY() < 1.75 
					&& StdDraw.mouseY() > 1.25) {
				if(StdDraw.isMousePressed()) {
					Game.buffer();
					printComfirmation(a);
					while(true) {
						//(((a[0].length+1)/2-1)/2-0.8, ((a.length + 1)/2-1.5)/2+0.6, 0.45, 0.25)
						if(StdDraw.mouseX() < ((a[0].length+1)/2-1)/2-0.8 + 0.45 
								&& StdDraw.mouseX() > ((a[0].length+1)/2-1)/2-0.8 - 0.45 
								&& StdDraw.mouseY() < ((a.length + 1)/2-1.5)/2+0.6 + 0.25 
								&& StdDraw.mouseY() > ((a.length + 1)/2-1.5)/2+0.6 - 0.25) {
							if(StdDraw.isMousePressed()) {
								Game.buffer();
								break;
							}
						}
						if(StdDraw.mouseX() < ((a[0].length+1)/2-1)/2+0.8 + 0.45 
								&& StdDraw.mouseX() > ((a[0].length+1)/2-1)/2+0.8 - 0.45 
								&& StdDraw.mouseY() < ((a.length + 1)/2-1.5)/2+0.6 + 0.25 
								&& StdDraw.mouseY() > ((a.length + 1)/2-1.5)/2+0.6 - 0.25) {
							if(StdDraw.isMousePressed()) {
								hasExit = true;
								Game.buffer();
								return;
							}
						}
					}
				}
			}
			
			for(int i = 0; i < a.length; i++) {
				int j = i % 2 == 0 ? 1 : 0;
				for(;j < a[i].length; j += 2) {
					if(a[i][j] == 3 || a[i][j] == 4)
						a[i][j] = 0;
				}
			}
			
			hasMoved = false;
			for(int i = 1; i < a.length; i += 2) {
				double centerY = i * 0.5;
				if(StdDraw.mouseY() < centerY + 0.45 && StdDraw.mouseY() > centerY - 0.45) {
					for(int j = 0; j < a[0].length; j += 2) {
						double centerX = j * 0.5;
						if(StdDraw.mouseX() < centerX + 0.06 && StdDraw.mouseX() > centerX - 0.06) {
							if(a[i][j] == 0) {
								a[i][j] = getNum() + 2;
								if(StdDraw.isMousePressed()) {
									hasMoved = true;
									a[i][j] = getNum();
									stack.add(i, j, getNum());
								}
							}
						}
					}
					if(hasMoved)
						break;
				}
			}
			if(hasMoved)
				break;
			for(int j = 1; j < a[0].length; j += 2) {
				double centerX = j * 0.5;
				if(StdDraw.mouseX() < centerX + 0.45 && StdDraw.mouseX() > centerX - 0.45) {
					for(int i = 0; i < a.length; i += 2) {
						double centerY = i * 0.5;
						if(StdDraw.mouseY() < centerY + 0.06 && StdDraw.mouseY() > centerY - 0.06) {
							if(a[i][j] == 0) {
								a[i][j] = getNum() + 2;
								if(StdDraw.isMousePressed()) {
									hasMoved = true;
									a[i][j] = getNum();
									stack.add(i, j, getNum());
								}
							}
						}
					}
					if(hasMoved)
						break;
				}
			}
			if(hasMoved)
				break;
		}
	}
	
	private static void printComfirmation(int[][] a) {
		StdDraw.setPenColor(255, 250, 205);
		StdDraw.filledRectangle(((a[0].length+1)/2-1)/2, ((a.length + 1)/2-1.5)/2, 2.2, 1.25);
		StdDraw.setPenColor(0, 0, 139);
		StdDraw.setPenRadius(0.015);
		StdDraw.rectangle(((a[0].length+1)/2-1)/2, ((a.length + 1)/2-1.5)/2, 2.2, 1.25);
		StdDraw.setPenColor();
		StdDraw.setFont(new Font("Verdana", 2, 22));
		StdDraw.text(((a[0].length+1)/2-1)/2, ((a.length + 1)/2-1.5)/2-0.7, "Are you sure you want to leave?");
		StdDraw.setFont(new Font("Verdana", 0, 18));
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(((a[0].length+1)/2-1)/2, ((a.length + 1)/2-1.5)/2-0.3, "(Your game will not be saved)");
		
		StdDraw.setFont(new Font("Verdana", 2, 28));
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.setPenRadius(0.025);
		StdDraw.rectangle(((a[0].length+1)/2-1)/2-0.8, ((a.length + 1)/2-1.5)/2+0.6, 0.45, 0.25);
		StdDraw.filledRectangle(((a[0].length+1)/2-1)/2-0.8, ((a.length + 1)/2-1.5)/2+0.6, 0.4, 0.2);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(((a[0].length+1)/2-1)/2-0.8, ((a.length + 1)/2-1.5)/2+0.63, "No");
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.rectangle(((a[0].length+1)/2-1)/2+0.8, ((a.length + 1)/2-1.5)/2+0.6, 0.45, 0.25);
		StdDraw.filledRectangle(((a[0].length+1)/2-1)/2+0.8, ((a.length + 1)/2-1.5)/2+0.6, 0.4, 0.2);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(((a[0].length+1)/2-1)/2+0.8, ((a.length + 1)/2-1.5)/2+0.63, "Yes");
		StdDraw.show();
	}
	
	public static void main(String[] args) {
//		Game.tutorial(5, 4, Machine.MEDIUM);
		Game.storyMode(4, 5, Machine.MEDIUM, false, "Travis");
//		Game.pvpMode(5, 5, "P1", "P2");
		System.out.println("sdsaf");
	}

}
