package panter.gmbh.indep;


//erzeugt ein paar eines typ
public class TRIPLE<U,V,W> {

	private U val1 = null;
	private V val2 = null;
	private W val3 = null;
	
	public TRIPLE(U p1, V p2, W p3) {
		super();
		this.val1=p1;
		this.val2=p2;
		this.val3=p3;
	}

	
	public TRIPLE() {
		super();
	}
	
	public U getVal1() {
		return val1;
	}

	public V getVal2() {
		return val2;
	}
	
	public W getVal3() {
		return val3;
	}
	

	public TRIPLE<U,V,W> _setVal1(U val1) {
		this.val1 = val1;
		return this;
	}

	public TRIPLE<U,V,W> _setVal2(V val2) {
		this.val2 = val2;
		return this;
	}

	public TRIPLE<U,V,W> _setVal3(W val3) {
		this.val3 = val3;
		return this;
	}
	

	
}
