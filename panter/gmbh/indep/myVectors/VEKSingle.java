package panter.gmbh.indep.myVectors;

import java.util.Collection;
import java.util.Vector;


public class VEKSingle<E> extends Vector<E> {

	public VEKSingle() {
		super();
	}

	public VEKSingle(Collection<? extends E> c) {
		super();
		this.addAll(c);
	}

	public VEKSingle(E[] array) {
		super();
		for (E o: array) {
			this.add(o);
		}
	}
	
	
	public boolean add(E o) {
		boolean bRueck = false;
		
		if (!this.contains(o)) {
			bRueck=super.add(o);
		}
		
		return bRueck;
	}

	public boolean addAll(Collection<? extends E> v) 	{
		boolean bRueck = true;
		
		for (E o: v) {
			if (!this.contains(o)) {
				this.add(o);
			}
		}
		
		return bRueck;
	}

	
	public VEKSingle<E>  _a(E o) {
		this.add(o);
		return this;
	}
	
	public VEKSingle<E>  _a(E... o) {
		for (E oo: o) {
			this.add(oo);
		}
		return this;
	}
	
	
	public VEKSingle<E> _aa(Collection<? extends E> v) 	{
	
		for (E o: v) {
			if (!this.contains(o)) {
				this.add(o);
			}
		}
		
		return this;
	}

}
