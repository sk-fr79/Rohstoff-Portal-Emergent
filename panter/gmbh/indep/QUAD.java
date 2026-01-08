package panter.gmbh.indep;


//erzeugt ein paar eines typ
public class QUAD<U,V,W,X> {

	private U val1 = null;
	private V val2 = null;
	private W val3 = null;
	private X val4 = null;
	
	public QUAD(U p1, V p2, W p3, X p4) {
		super();
		this.val1=p1;
		this.val2=p2;
		this.val3=p3;
		this.val4=p4;
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
	
	public X getVal4() {
		return val4;
	}
		

	public QUAD<U,V,W,X> _setVal1(U val1) {
		this.val1 = val1;
		return this;
	}

	public QUAD<U,V,W,X> _setVal2(V val2) {
		this.val2 = val2;
		return this;
	}

	public QUAD<U,V,W,X> _setVal3(W val3) {
		this.val3 = val3;
		return this;
	}
	
	public QUAD<U,V,W,X> _setVal4(X val4) {
		this.val4 = val4;
		return this;
	}
	
	
}
