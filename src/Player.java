
public abstract class Player {
	
	private static int count = 1;
	private int score;
	private int num;
	private String name;
	private boolean turn;
	
	Player(){
		setNum(count);
		count++;
		if(count > 2)
			count = count % 2;
	}
	
	public abstract void move(int[][] a, Player enemy, StackOfStep stack);

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

}
