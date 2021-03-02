import java.util.ArrayList;

public class StackOfStep {

	private int size = 0;
	private ArrayList<int[]> list = new ArrayList<>();
	
	public void add(int i, int j, int num) {
		int[] a = {i, j, num};
		list.add(a);
		size++;
	}
	
	public boolean isEmpty() {
		if(getSize() == 0)
			return true;
		return false;
	}
	
	public int[] peek() {
		return list.get(size - 1);
	}
	
	public int[] remove() {
		int[] a = list.get(size - 1);
		list.remove(size - 1);
		size--;
		return a;
	}
	
	public void clear() {
		list.clear();
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

}
