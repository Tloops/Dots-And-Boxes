
public class Main {

	public static void main(String[] args) throws Exception {
		StdDraw.enableDoubleBuffering();
		try {
			MediaTest media = new MediaTest(new String[] {
					"おさむらいさん - 空想森林.mp3",
					"ヨルシカ - 夏陰、ピアノを弾く.mp3",
					"Nanofingers - Henry.mp3"
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true) {
	        StdDraw.setCanvasSize(800, 640);
	        StdDraw.setXscale(-2.5, 2.5);
	        StdDraw.setYscale(-2, 2);
	        while(true){
	            Title.printTitle();
	            int n = Title.num();
	            if(n == 1)
	                break;
	            if(n == 0)
	                Title.printTeam();
	            if(n == -1) {
	            	StdDraw.loading();
	                Course.printCourse();
	            }
	        }
	        StdDraw.loading();
	        Initial.newGame();
	        StdDraw.loading();
	        if(Initial.mod == Game.PVP_MODE) {
	        	Initial.inputName(1, "player1's");
	        	StdDraw.loading();
	        	Initial.inputName(2, "player2's");
	        	StdDraw.loading();
	        }
	        if (Initial.mod == Game.STORY_MODE) {
	            Initial.inputName(1, "your");
	            StdDraw.loading();
	        }
	        
	        if(Initial.mod == Game.TUTORIAL) {
	        	if(Initial.size == 1)
	        		Game.tutorial(3, 3, Initial.difficulty);
	        	else if(Initial.size == 2)
	        		Game.tutorial(4, 4, Initial.difficulty);
	        	else if(Initial.size == 3)
	        		Game.tutorial(5, 5, Initial.difficulty);
	        	else
	        		throw new Exception("invalid size");
	        }
	        else if(Initial.mod == Game.PVP_MODE) {
	        	if(Initial.size == 1)
	        		Game.pvpMode(3, 3, Initial.playerName1, Initial.playerName2);
	        	else if(Initial.size == 2)
	        		Game.pvpMode(4, 4, Initial.playerName1, Initial.playerName2);
	        	else if(Initial.size == 3)
	        		Game.pvpMode(5, 5, Initial.playerName1, Initial.playerName2);
	        	else
	        		throw new Exception("invalid size");
	        }
	        else if(Initial.mod == Game.STORY_MODE) {
	        	if(Initial.size == 1)
	        		Game.storyMode(3, 3, Initial.difficulty, Initial.goFirst == 1 ? true : false, Initial.playerName1);
	        	else if(Initial.size == 2)
	        		Game.storyMode(4, 4, Initial.difficulty, Initial.goFirst == 1 ? true : false, Initial.playerName1);
	        	else if(Initial.size == 3)
	        		Game.storyMode(5, 5, Initial.difficulty, Initial.goFirst == 1 ? true : false, Initial.playerName1);
	        	else
	        		throw new Exception("invalid size");
	        }
	        else
	        	throw new Exception("invalid mode");
		}
        
	}
	
}
