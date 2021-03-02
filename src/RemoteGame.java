
public class RemoteGame {

	public static Player p1 = new Transmitter1();
	public static Player p2 = new Transmitter2();
	
	public static void main(String[] args){

		Transmitter1.chessboard = new int[11][11];
		Game.printGame(Transmitter1.chessboard, p1, p2);
		
		//Transmitter1 initialize
		try {
			Transmitter1.sending();
			System.err.println("P1 ready");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		p1.setTurn(true);
		Transmitter1.meMoved = false;
		p2.setTurn(false);
		
		while(true) {
			try {
				Transmitter1.receiving();
				break;
			}catch(Exception ex) {}
		}
		
		while(!Game.isFull(Transmitter1.chessboard)) {
			while(true) {
				try {
					Transmitter1.receiving();
					Transmitter1.receiving();
					Transmitter1.receiving();
					Transmitter1.receiving();
					Transmitter1.receiving();
					break;
				}catch(Exception ex) {}
			}
			System.out.println(Transmitter1.heMoved);
			if(p1.isTurn()) {
				do {
					if(Game.isFull(Transmitter1.chessboard))
						break;
					p1.move(Transmitter1.chessboard, p2, null);
					//Game.correctMistake
					while(true) {
						try {
							Transmitter1.receiving();
							break;
						}catch(Exception ex) {}
					}
					if(!Game.hasScored(Transmitter1.chessboard, p1, p2)) {
						Game.printGame(Transmitter1.chessboard, p1, p2);
						p1.setTurn(false);
						p2.setTurn(true);
						Transmitter1.meMoved = true;
						break;
					}
					else {
						Game.printGame(Transmitter1.chessboard, p1, p2);
					}
				}while(true);
			}
			else {//synchronized
				if(Transmitter1.heMoved) {
					Transmitter1.meMoved = false;
					p1.setTurn(true);
					p2.setTurn(false);
				}
				Game.printGame(Transmitter1.chessboard, p1, p2);
			}
		}
		System.out.println("Game End!");
	}

}
