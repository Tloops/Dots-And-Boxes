
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Transmitter2 extends Player implements TransportArray{

	public static int port = 2;
	private static volatile int[][] chessboard;
	private static volatile boolean meMoved;
	private static volatile boolean heMoved;
	
	public Transmitter2() {
		setName("2");
	}
	
	@Override
	public int[][] getArray(){
		synchronized (chessboard) {
			return chessboard;
		}
	}
	
	@Override
	public boolean getMoved(){
		synchronized(chessboard) {
			return meMoved;
		}
	}
	
	public static void sending() throws Exception{
		Transmitter2 t = new Transmitter2();
		TransportArray stub = (TransportArray) UnicastRemoteObject.exportObject(t, 12);

		Registry registry = LocateRegistry.createRegistry(port);
		registry.bind("DAB Game", stub);
	}

	public static void receiving() throws Exception{
		Registry registry = LocateRegistry.getRegistry(null, Transmitter1.port);
		TransportArray stub = (TransportArray)registry.lookup("DAB Game");
		
		int[][] a = stub.getArray();
		heMoved = stub.getMoved();
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < a[i].length; j++)
				chessboard[i][j] = a[i][j];
	}
	
	public static void main(String[] args) {
		Player p1 = new Transmitter1();
		Player p2 = new Transmitter2();
		
		chessboard = new int[11][11];
		Game.printGame(chessboard, p1, p2);
		
		//Transmitter2 initialize
		try {
			sending();
			System.err.println("P2 ready");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		p1.setTurn(true);
		p2.setTurn(false);
		
		while(!Game.isFull(chessboard)) {
			while(true) {
				try {
					receiving();
					receiving();
					receiving();
					receiving();
					receiving();
					break;
				}catch(Exception ex) {}
			}
			if(p2.isTurn()) {
				do {
					if(Game.isFull(chessboard))
						break;
					p2.move(chessboard, p1, null);
					while(true) {
						try {
							receiving();
							break;
						}catch(Exception ex) {}
					}
					//Game.correctMistake
					if(!Game.hasScored(chessboard, p2, p1)) {
						Game.printGame(chessboard, p1, p2);
						p1.setTurn(true);
						p2.setTurn(false);
						meMoved = true;
						break;
					}
					else {
						Game.printGame(chessboard, p1, p2);
					}
				}while(true);
			}
			else {
				if(heMoved) {
					meMoved = false;
					p1.setTurn(false);
					p2.setTurn(true);
				}
				Game.printGame(chessboard, p1, p2);
			}
		}
		System.out.println("Game End!");
	}

	public static boolean hasMoved = false;
	
	@Override
	public void move(int[][] a, Player e, StackOfStep stack) {
		synchronized(chessboard) {
		while(true) {
			if(this.getNum() < e.getNum())
				Game.printGame(a, this, e);
			else
				Game.printGame(a, e, this);
			
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
	}
}
