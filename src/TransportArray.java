

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TransportArray extends Remote{
	public int[][] getArray() throws RemoteException;
	public boolean getMoved() throws RemoteException;
}
