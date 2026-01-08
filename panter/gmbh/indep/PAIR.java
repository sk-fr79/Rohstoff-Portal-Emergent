package panter.gmbh.indep;


//erzeugt ein paar eines typ
public class PAIR<U,V> {

	private U val1 = null;
	private V val2 = null;
	
	public PAIR(U p1, V p2) {
		super();
		this.val1=p1;
		this.val2=p2;
	}
	
	public PAIR() {
		super();
	}
	

	public U getVal1() {
		return val1;
	}

	public V getVal2() {
		return val2;
	}

	public void setVal1(U val1) {
		this.val1 = val1;
	}

	public void setVal2(V val2) {
		this.val2 = val2;
	}
	
	public PAIR<U,V> _setVal1(U val1) {
		this.val1 = val1;
		return this;
	}

	public PAIR<U,V> _setVal2(V val2) {
		this.val2 = val2;
		return this;
	}
	
	
}
