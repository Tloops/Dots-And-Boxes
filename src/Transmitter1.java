
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.*;

public class Transmitter1 extends Player implements TransportArray {

	public static int port = 1;
	public static volatile int[][] chessboard;
	public static volatile boolean meMoved;
	public static volatile boolean heMoved;
	
	public Transmitter1() {
		setName("1");
	}
	
	@Override
	public int[][] getArray(){
		synchronized(chessboard) {
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
		Transmitter1 t = new Transmitter1();
		TransportArray stub = (TransportArray) UnicastRemoteObject.exportObject(t, 11);

		Registry registry = LocateRegistry.createRegistry(port);
		registry.bind("DAB Game", stub);
	}

	public static void receiving() throws Exception{
		Registry registry = LocateRegistry.getRegistry(null, Transmitter2.port);//host
		TransportArray stub = (TransportArray)registry.lookup("DAB Game");
		
		int[][] a = stub.getArray();
		heMoved = stub.getMoved();
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < a[i].length; j++)
				chessboard[i][j] = a[i][j];
	}
	
//	public static void main(String[] args) {
//		try {
//			sending();
//		} catch (Exception e) {}
//		while(true) {
//			try {
//				receiving();
//				System.out.println("get response:" + chessboard[1][1]);
//				break;
//			} catch (Exception e) {}
//		}
//	}

	public static boolean hasMoved = false;
	
	@Override
	public void move(int[][] a, Player e, StackOfStep stack) {
		synchronized (chessboard) {
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
