import java.util.ArrayList;

public class Machine extends Player {

	private final int DIFFICULTY;
	public static final int EASY = 1;
	public static final int MEDIUM = 2;
	public static final int HARD = 3;
	
	public Machine(int difficulty) {
		setName("AlphaDot" + getNum());
		if(difficulty >= 1 && difficulty <= 3)
			DIFFICULTY = difficulty;
		else
			throw new IllegalArgumentException("Undefined difficulty!");
	}
	
	private final int TIME_BETWEEN_MOVES = 500;
	private final int TIME_SLOW = 500;
	
	public void move(int[][] a, Player enemy, StackOfStep stack) {
//		System.out.println(getNum());
		boolean isMove = false;
		//medium 91 hard 216
		if(getDIFFICULTY() == EASY) {
			//TODO EASY: greedy -> random step
			//greedy
			for(int i = 1; i < a.length; i += 2) {
				for(int j = 1 ; j < a[i].length; j += 2) {
					if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] != 0
							&& a[i][j-1] != 0 && a[i][j+1] == 0) {
						a[i][j+1] = getNum();
						stack.add(i, j+1, getNum());
						isMove = true;
						break;
					}
					else if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] != 0
							&& a[i][j-1] == 0 && a[i][j+1] != 0) {
						a[i][j-1] = getNum();
						stack.add(i, j-1, getNum());
						isMove = true;
						break;
					}
					else if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] == 0
							&& a[i][j-1] != 0 && a[i][j+1] != 0) {
						a[i-1][j] = getNum();
						stack.add(i-1, j, getNum());
						isMove = true;
						break;
					}
					else if(a[i][j] == 0 && a[i+1][j] == 0 && a[i-1][j] != 0
							&& a[i][j-1] != 0 && a[i][j+1] != 0) {
						a[i+1][j] = getNum();
						stack.add(i+1, j, getNum());
						isMove = true;
						break;
					}
				}
				if(isMove)	break;
			}
			if(isMove)	{
				StdDraw.pause(TIME_BETWEEN_MOVES);
				return;
			}
			//random step
			int sum = 0;
			for(int i = 0; i < a.length; i++) {
				int j = i % 2 == 0 ? 1 : 0;
				for(;j < a[i].length; j += 2)
					if(a[i][j] == 0)
						sum++;
			}
			double random = Math.random();
			int count = 1;
			for(int i = 0; i < a.length; i++) {
				int j = i % 2 == 0 ? 1 : 0;
				for(;j < a[i].length; j += 2) {
					if(a[i][j] == 0 && random < (double)count / sum) {
						a[i][j] = getNum();
						stack.add(i, j, getNum());
						isMove = true;
						break;
					}
					if(a[i][j] == 0)
						count++;
				}
				if(isMove)	break;
				if(this.getNum() < enemy.getNum())
					Game.printGame(a, this, enemy);
				else
					Game.printGame(a, enemy, this);
			}
		}
		else if(getDIFFICULTY() == MEDIUM) {
			//TODO Medium: greedy -> choosing -> random
			//greedy
			for(int i = 1; i < a.length; i += 2) {
				for(int j = 1 ; j < a[i].length; j += 2) {
					if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] != 0
							&& a[i][j-1] != 0 && a[i][j+1] == 0) {
						a[i][j+1] = getNum();
						stack.add(i, j+1, getNum());
						isMove = true;
						break;
					}
					else if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] != 0
							&& a[i][j-1] == 0 && a[i][j+1] != 0) {
						a[i][j-1] = getNum();
						stack.add(i, j-1, getNum());
						isMove = true;
						break;
					}
					else if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] == 0
							&& a[i][j-1] != 0 && a[i][j+1] != 0) {
						a[i-1][j] = getNum();
						stack.add(i-1, j, getNum());
						isMove = true;
						break;
					}
					else if(a[i][j] == 0 && a[i+1][j] == 0 && a[i-1][j] != 0
							&& a[i][j-1] != 0 && a[i][j+1] != 0) {
						a[i+1][j] = getNum();
						stack.add(i+1, j, getNum());
						isMove = true;
						break;
					}
				}
				if(isMove)	break;
			}
			if(isMove)	{
				StdDraw.pause(TIME_BETWEEN_MOVES);
				return;
			}
			
			//choosing special step
			int[][] b = new int[a.length][a[0].length];
			for(int i = 0; i < a.length; i++)
				for(int j = 0; j < a[i].length; j++)
					b[i][j] = a[i][j];
			
			for(int i = 1; i < b.length; i += 2) {
				for(int j = 1; j < b[i].length; j += 2) {
					if(b[i][j] == 0) {
						int count = 0;
						if(b[i-1][j] != 0 && b[i-1][j] != -1)	count++;
						if(b[i+1][j] != 0 && b[i+1][j] != -1)	count++;
						if(b[i][j-1] != 0 && b[i][j-1] != -1)	count++;
						if(b[i][j+1] != 0 && b[i][j+1] != -1)	count++;
						if(count == 2)	{
							if(b[i-1][j] == 0)	b[i-1][j] = -1;
							if(b[i+1][j] == 0)	b[i+1][j] = -1;
							if(b[i][j-1] == 0)	b[i][j-1] = -1;
							if(b[i][j+1] == 0)	b[i][j+1] = -1;
						}
					}
				}
			}
			int sum1 = 0;
			for(int i = 0; i < b.length; i++) {
				int j = i % 2 == 0 ? 1 : 0;
				for(; j < b[i].length; j += 2)
					if(b[i][j] == 0)
						sum1++;
			}
			double random1 = Math.random();
			int count1 = 1;
			for(int i = 0; i < a.length; i++) {
				int j = i % 2 == 0 ? 1 : 0;
				for(;j < a[i].length; j += 2) {
					if(b[i][j] == 0 && random1 < (double)count1 / sum1) {
						a[i][j] = getNum();
						stack.add(i, j, getNum());
						isMove = true;
						break;
					}
					if(b[i][j] == 0)
						count1++;
				}
				if(isMove)	break;
				if(this.getNum() < enemy.getNum())
					Game.printGame(a, this, enemy);
				else
					Game.printGame(a, enemy, this);
			}
			if(isMove)	{
				StdDraw.pause(TIME_BETWEEN_MOVES);
				return;
			}
			
			//random step
			int sum2 = 0;
			for(int i = 0; i < a.length; i++) {
				int j = i % 2 == 0 ? 1 : 0;
				for(; j < a[i].length; j += 2)
					if(a[i][j] == 0)
						sum2++;
			}
			double random2 = Math.random();
			int count2 = 1;
			for(int i = 0; i < a.length; i++) {
				int j = i % 2 == 0 ? 1 : 0;
				for(;j < a[i].length; j += 2) {
					if(a[i][j] == 0 && random2 < (double)count2 / sum2) {
						a[i][j] = getNum();
						stack.add(i, j, getNum());
						isMove = true;
						break;
					}
					if(a[i][j] == 0)
						count2++;
				}
				if(isMove)	break;
				if(this.getNum() < enemy.getNum())
					Game.printGame(a, this, enemy);
				else
					Game.printGame(a, enemy, this);
			}
		}
		
		else if(getDIFFICULTY() == HARD) {
			//TODO Hard
			ArrayList<int[]> list = new ArrayList<>();
			//copy and produce Array b 
			int[][] b = new int[a.length][a[0].length];
			for(int i = 0; i < a.length; i++)
				for(int j = 0; j < a[i].length; j++)
					b[i][j] = a[i][j];
			for(int i = 1; i < b.length; i += 2) {
				for(int j = 1; j < b[i].length; j += 2) {
					if(b[i][j] == 0) {
						int count = 0;
						if(b[i-1][j] != 0 && b[i-1][j] != -1)	count++;
						if(b[i+1][j] != 0 && b[i+1][j] != -1)	count++;
						if(b[i][j-1] != 0 && b[i][j-1] != -1)	count++;
						if(b[i][j+1] != 0 && b[i][j+1] != -1)	count++;
						if(count == 2)	{
							if(b[i-1][j] == 0)	b[i-1][j] = -1;
							if(b[i+1][j] == 0)	b[i+1][j] = -1;
							if(b[i][j-1] == 0)	b[i][j-1] = -1;
							if(b[i][j+1] == 0)	b[i][j+1] = -1;
						}//-1 means that side is not good to step on
					}
				}
			}
			//decide End-Game?
			isEndGame = true;
			for(int i = 0; i < b.length; i++) {
				int j = i % 2 == 0 ? 1 : 0;
				for(; j < b[i].length; j += 2) {
					if(b[i][j] == 0) {
						isEndGame = false;
						break;
					}
				}
				if(!isEndGame)	break;
			}
			
			
			if(!isEndGame) {
				//greedy without shuangjiao(Not end-game situation, just get it!)
				for(int i = 1; i < a.length; i += 2) {
					for(int j = 1 ; j < a[i].length; j += 2) {
						if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] != 0
								&& a[i][j-1] != 0 && a[i][j+1] == 0) {
							a[i][j+1] = getNum();
							stack.add(i, j+1, getNum());
							isMove = true;
							break;
						}
						else if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] != 0
								&& a[i][j-1] == 0 && a[i][j+1] != 0) {
							a[i][j-1] = getNum();
							stack.add(i, j-1, getNum());
							isMove = true;
							break;
						}
						else if(a[i][j] == 0 && a[i+1][j] != 0 && a[i-1][j] == 0
								&& a[i][j-1] != 0 && a[i][j+1] != 0) {
							a[i-1][j] = getNum();
							stack.add(i-1, j, getNum());
							isMove = true;
							break;
						}
						else if(a[i][j] == 0 && a[i+1][j] == 0 && a[i-1][j] != 0
								&& a[i][j-1] != 0 && a[i][j+1] != 0) {
							a[i+1][j] = getNum();
							stack.add(i+1, j, getNum());
							isMove = true;
							break;
						}
					}
					if(isMove)	break;
				}
				if(isMove)	{
					StdDraw.pause(TIME_BETWEEN_MOVES);
					return;
				}
				
				//choosing special step --> after end-game no need
				for(int i = 1; i < b.length; i += 2) {
					for(int j = 1; j < b[i].length; j += 2) {
						if(b[i][j] == 0) {
							int count = 0;
							if(b[i-1][j] != 0 && b[i-1][j] != -1)	count++;
							if(b[i+1][j] != 0 && b[i+1][j] != -1)	count++;
							if(b[i][j-1] != 0 && b[i][j-1] != -1)	count++;
							if(b[i][j+1] != 0 && b[i][j+1] != -1)	count++;
							if(count == 2)	{
								if(b[i-1][j] == 0)	b[i-1][j] = -1;
								if(b[i+1][j] == 0)	b[i+1][j] = -1;
								if(b[i][j-1] == 0)	b[i][j-1] = -1;
								if(b[i][j+1] == 0)	b[i][j+1] = -1;
							}
						}
					}
				}
				int sum1 = 0;
				for(int i = 0; i < b.length; i++) {
					int j = i % 2 == 0 ? 1 : 0;
					for(; j < b[i].length; j += 2)
						if(b[i][j] == 0)
							sum1++;
				}
				double random1 = Math.random();
				int count1 = 1;
				for(int i = 0; i < a.length; i++) {
					int j = i % 2 == 0 ? 1 : 0;
					for(;j < a[i].length; j += 2) {
						if(b[i][j] == 0 && random1 < (double)count1 / sum1) {
							a[i][j] = getNum();
							stack.add(i, j, getNum());
							isMove = true;
							break;
						}
						if(b[i][j] == 0)
							count1++;
					}
					if(isMove)	break;
					if(this.getNum() < enemy.getNum())
						Game.printGame(a, this, enemy);
					else
						Game.printGame(a, enemy, this);
				}
				if(isMove)	{
					StdDraw.pause(TIME_BETWEEN_MOVES);
					return;
				}
				
			}
			
			if(isEndGame) {
				//decide who first after end-game, and create Array c
//				System.out.println("End-Game!!!!");
//				StdDraw.pause(1000);
				boolean meFirst = true;
				for(int i = 1; i < a.length; i += 2) {
					for(int j = 1; j < a[i].length; j += 2) {
						if(a[i][j] == 0) {
							int count = 0;
							if(a[i-1][j] == 0)	count--;
							if(a[i+1][j] == 0)	count--;
							if(a[i][j-1] == 0)	count--;
							if(a[i][j+1] == 0)	count--;
							if(count == -1) {
								meFirst = false;
								break;
							}
						}
					}
				}
				
				if(meFirst) {
					//search the smallest block
					//search -2
					for(int i = 1; i < b.length; i += 2) {
						for(int j = 1; j < b[i].length; j += 2) {
							if(b[i][j] == 0) {
								int count = 0;
								if(b[i-1][j] == -1)	count++;
								if(b[i+1][j] == -1)	count++;
								if(b[i][j-1] == -1)	count++;
								if(b[i][j+1] == -1)	count++;
								if(count >= 3) {
									b[i][j] = -2;
								}
							}
						}
					}
					for(int i = 1; i < b.length; i += 2) {
						for(int j = 1; j < b[i].length; j += 2) {
							if(b[i][j] == -2) {
								int count = -1;
								if(b[i-1][j] == -1) {
									count++;
									dfs(b, i-2, j);
									if(sum == 0) {
										dfs(b, i-2, j, -1, -1);
										for(int k = list.size()-1; k >= list.size()-1-count; k--) {
											if(list.size() > 0 && k >= 0 && list.get(k)[2] == sum) {
												list.remove(k);
												sum = 0;
												break;
											}
										}
									}
									else {
										int[] step = {i-1, j, sum};
										list.add(step);
										sum = 0;
									}
								}
								if(b[i+1][j] == -1) {
									count++;
									dfs(b, i+2, j);
									if(sum == 0) {
										dfs(b, i+2, j, -1, -1);
										for(int k = list.size()-1; k >= list.size()-1-count; k--) {
											if(list.size() > 0 && k >= 0 && list.get(k)[2] == sum) {
												list.remove(k);
												sum = 0;
												break;
											}
										}
									}
									else {
										int[] step = {i+1, j, sum};
										list.add(step);
										sum = 0;
									}
								}
								if(b[i][j-1] == -1) {
									count++;
									dfs(b, i, j-2);
									if(sum == 0) {
										dfs(b, i, j-2, -1, -1);
										for(int k = list.size()-1; k >= list.size()-1-count; k--) {
											if(list.size() > 0 && k >= 0 && list.get(k)[2] == sum) {
												list.remove(k);
												sum = 0;
												break;
											}
										}
									}
									else {
										int[] step = {i, j-1, sum};
										list.add(step);
										sum = 0;
									}
								}
								if(b[i][j+1] == -1) {
									count++;
									dfs(b, i, j+2);
									if(sum == 0) {
										dfs(b, i, j+2, -1, -1);
										for(int k = list.size()-1; k >= list.size()-1-count; k--) {
											if(list.size() > 0 && k >= 0 && list.get(k)[2] == sum) {
												list.remove(k);
												sum = 0;
												break;
											}
										}
									}
									else {
										int[] step = {i, j+1, sum};
										list.add(step);
										sum = 0;
									}
								}
							}
						}
					}
					//search 0
					for(int i = 1; i < b.length; i += 2) {
						for(int j = 1; j < b[i].length; j += 2) {
							if(b[i][j] == 0) {
								dfs(b, i, j);
								if(b[i-1][j] == -1) {
									int[] step = {i-1, j, sum};
									list.add(step);
								}
								else if(b[i+1][j] == -1) {
									int[] step = {i+1, j, sum};
									list.add(step);
								}
								else if(b[i][j-1] == -1) {
									int[] step = {i, j-1, sum};
									list.add(step);
								}
								else if(b[i][j+1] == -1) {
									int[] step = {i, j+1, sum};
									list.add(step);
								}
								sum = 0;
							}
						}
					}
					//find maxSum and its position
					if(list.size() >= 1) {
						int minSum = Integer.MAX_VALUE;
						int position = 0;
						for(int i = 0; i < list.size(); i++) {
							if(list.get(i)[2] < minSum) {
								minSum = list.get(i)[2];
								position = i;
							}
						}
						stack.add(list.get(position)[0], list.get(position)[1], getNum());
						a[list.get(position)[0]][list.get(position)[1]] = getNum();
						StdDraw.pause(TIME_SLOW);
						return;
					}
					else {
						
					}
					
//					for(int[] e1: list) {
//						for(int e2: e1)
//							System.out.print(e2 + " ");
//						System.out.println();
//					}
//					System.out.println();
//					return;
				}
				else {
					//greedy + shuangjiao
					ArrayList<int[]> block = new ArrayList<>();
					int[][] c = new int[a.length][a[0].length];
					for(int i = 0; i < a.length; i++)
						for(int j = 0; j < a[i].length; j++)
							c[i][j] = a[i][j];
					for(int i = 1; i < a.length; i+=2) {
						for(int j = 1; j < a[i].length; j+=2) {
							if(c[i][j] == 0) {
								int count = 0;
								if(c[i-1][j] == 0)	count++;
								if(c[i+1][j] == 0)	count++;
								if(c[i][j-1] == 0)	count++;
								if(c[i][j+1] == 0)	count++;
								if(count == 1) {
									int[] ce = {i, j};
									block.add(ce);
								}
							}
						}
					}
					
					for(int[] e : block) {
						dfs2(c, e[0], e[1]);
						int x = e[0];
						int y = e[1];
						if(c[x-1][y] == 0) {
							int[] step = {x-1, y, sum};
							list.add(step);
							sum = 0;
						}
						else if(c[x+1][y] == 0) {
							int[] step = {x+1, y, sum};
							list.add(step);
							sum = 0;
						}
						else if(c[x][y-1] == 0) {
							int[] step = {x, y-1, sum};
							list.add(step);
							sum = 0;
						}
						else if(c[x][y+1] == 0) {
							int[] step = {x, y+1, sum};
							list.add(step);
							sum = 0;
						}
						//sum == 2
					}
					int minSum = Integer.MAX_VALUE;
					int position = 0;
					for(int i = 0; i < list.size(); i++) {
						if(list.get(i)[2] < minSum) {
							minSum = list.get(i)[2];
							position = i;
						}
					}
					if(block.size() >= 2) {
						stack.add(list.get(position)[0], list.get(position)[1], getNum());
						a[list.get(position)[0]][list.get(position)[1]] = getNum();
						StdDraw.pause(TIME_SLOW);
						return;
					}
					else if(list.get(position)[2] == 1 || list.get(position)[2] >= 3) {
						stack.add(list.get(position)[0], list.get(position)[1], getNum());
						a[list.get(position)[0]][list.get(position)[1]] = getNum();
						StdDraw.pause(TIME_SLOW);
						return;
					}
					else if(list.get(position)[2] == 2) {
						int empty = 0;
						for(int i = 1; i < a.length; i+=2)
							for(int j = 1; j < a[i].length; j+=2)
								if(a[i][j] == 0)
									empty++;
						if(empty == 2) {
							stack.add(list.get(position)[0], list.get(position)[1], getNum());
							a[list.get(position)[0]][list.get(position)[1]] = getNum();
							StdDraw.pause(TIME_SLOW);
							return;
						}
						else {
							int[][] d = new int[a.length][a[0].length];
							for(int i = 0; i < a.length; i++)
								for(int j = 0; j < a[i].length; j++)
									d[i][j] = a[i][j];
							boolean hasFound = false;
							for(int i = 1; i < a.length; i+=2) {
								for(int j = 1; j < a[i].length; j+=2) {
									if(d[i][j] == 0) {
										int count = 0;
										if(d[i-1][j] == 0)	count++;
										if(d[i+1][j] == 0)	count++;
										if(d[i][j-1] == 0)	count++;
										if(d[i][j+1] == 0)	count++;
										if(count == 1) {
											if(d[i-1][j] == 0) {
												hasFound = true;
												d[i-1][j] = 1;
												break;
											}
											if(d[i+1][j] == 0) {
												hasFound = true;
												d[i+1][j] = 1;
												break;
											}
											if(d[i][j-1] == 0) {
												hasFound = true;
												d[i][j-1] = 1;
												break;
											}
											if(d[i][j+1] == 0) {
												hasFound = true;
												d[i][j+1] = 1;
												break;
											}
										}
									}
								}
								if(hasFound) {
									break;
								}
							}
							boolean hasMoved = false;
							for(int i = 1; i < a.length; i+=2) {
								for(int j = 1; j < a[i].length; j+=2) {
									if(d[i][j] == 0) {
										int count = 0;
										if(d[i-1][j] == 0)	count++;
										if(d[i+1][j] == 0)	count++;
										if(d[i][j-1] == 0)	count++;
										if(d[i][j+1] == 0)	count++;
										if(count == 1) {
											if(d[i-1][j] == 0) {
												hasMoved = true;
												stack.add(i-1, j, getNum());
												a[i-1][j] = getNum();
												break;
											}
											if(d[i+1][j] == 0) {
												hasMoved = true;
												stack.add(i+1, j, getNum());
												a[i+1][j] = getNum();
												break;
											}
											if(d[i][j-1] == 0) {
												hasMoved = true;
												stack.add(i, j-1, getNum());
												a[i][j-1] = getNum();
												break;
											}
											if(d[i][j+1] == 0) {
												hasMoved = true;
												stack.add(i, j+1, getNum());
												a[i][j+1] = getNum();
												break;
											}
										}
									}
								}
								if(hasMoved)
									break;
							}
							StdDraw.pause(TIME_SLOW);
							return;
						}
					}
				}
			}
		}
		StdDraw.pause(TIME_BETWEEN_MOVES);
	}
	
	private static int sum;
	public static boolean isEndGame;
	
	private static void dfs(int[][] b, int x, int y) {
		dfs(b, x, y, 0, -1);
	}
	
	private static void dfs2(int[][] c, int x, int y) {
		dfs(c, x, y, 0, 0);
	}
	
	private static void dfs(int[][] b, int x, int y, int num, int edge) {
		if(b[x][y] != num)
			return;
		sum++;
		b[x][y] = num - 1;
		if(x - 2 > 0 			&& b[x-1][y] == edge && b[x-2][y] == num)	dfs(b, x-2, y, num, edge);
		if(x + 2 < b.length 	&& b[x+1][y] == edge && b[x+2][y] == num)	dfs(b, x+2, y, num, edge);
		if(y - 2 > 0 			&& b[x][y-1] == edge && b[x][y-2] == num)	dfs(b, x, y-2, num, edge);
		if(y + 2 < b[0].length 	&& b[x][y+1] == edge && b[x][y+2] == num)	dfs(b, x, y+2, num, edge);
	}
	
	public static void main(String[] args) throws Exception{ 
		while(true) {
			try {
				Game.tutorial(5, 7, HARD);
				StdDraw.pause(2000);
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Error!");
				break;
			}
		}
//		Game.storyMode(5, 5, MEDIUM, true, "Travis");
//		Game.storyMode(5, 5, HARD, true, "YSY");
//		Game.pvpMode(6, 11, "Travis", "dsdd");
	}

	public int getDIFFICULTY() {
		return DIFFICULTY;
	}
	
}
