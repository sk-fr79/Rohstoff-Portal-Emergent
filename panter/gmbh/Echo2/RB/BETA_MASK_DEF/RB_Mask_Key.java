package panter.gmbh.Echo2.RB.BETA_MASK_DEF;

import panter.gmbh.indep.exceptions.myException;

public class RB_Mask_Key implements Comparable<RB_Mask_Key> {

	private final int x;
	private final int y;

	public RB_Mask_Key(int i_column, int i_line) {
		this.x = i_column;
		this.y = i_line;
	}

	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		if (!(o instanceof RB_Mask_Key)) {
			return false;
		}
		
		RB_Mask_Key key = (RB_Mask_Key) o;
		if(x == key.x && y == key.y) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
	
	public int get_row() throws myException{
		return this.y;
	}
	
	public int get_column() throws myException{
		return this.x;
	}

	
	@Override
	public int compareTo(RB_Mask_Key mk) {
		if (this.y < mk.y) {
			return -1;
		}
		
		if (this.y > mk.y) {
			return 1;
		}
		
		if (this.x < mk.x) {
			return -1;
		}
		
		if (this.x > mk.x) {
			return 1;
		}
		return 0;
	}
}
