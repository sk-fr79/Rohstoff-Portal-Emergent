package panter.gmbh.indep.myVectors;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;

public class VectorE2String extends Vector<MyE2_String> {

	public VectorE2String() {
		super();
	}

	public VectorE2String(String s) {
		super();
		this.add(new MyE2_String(s));
	}

	
	public VectorE2String(Collection<? extends MyE2_String> c) {
		super(c);
	}

	public VectorE2String t(String s) {
		this.add(new MyE2_String(s));
		return this;
	}
	
	public VectorE2String ut(String s) {
		this.add(new MyE2_String(s,false));
		return this;
	}

	public VectorE2String a(MyE2_String s) {
		this.add(s);
		return this;
	}

	
}
