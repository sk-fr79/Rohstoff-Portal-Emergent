package panter.gmbh.indep.myVectors;

import java.util.Collection;
import java.util.Vector;


public class VectorString extends Vector<String>
{

	public VectorString()
	{
		super();
	}

	public VectorString(Collection<? extends String> c)
	{
		super(c);
	}

	public VectorString(String s1)
	{
		super();
		if (s1!=null) this.add(s1);
	}

	
	public VectorString(String s1,String s2)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
	}

	public VectorString(String s1,String s2,String s3)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
	}
	public VectorString(String s1,String s2,String s3,String s4)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
	}
	public VectorString(String s1,String s2,String s3,String s4,String s5)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
		if (s5!=null) this.add(s5);
	}

	
	public VectorString(String s1,String s2,String s3,String s4,String s5,String s6)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
		if (s5!=null) this.add(s5);
		if (s6!=null) this.add(s6);
	}

	
	public VectorString(String s1,String s2,String s3,String s4,String s5,String s6,String s7)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
		if (s5!=null) this.add(s5);
		if (s6!=null) this.add(s6);
		if (s7!=null) this.add(s7);
	}


	public VectorString(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
		if (s5!=null) this.add(s5);
		if (s6!=null) this.add(s6);
		if (s7!=null) this.add(s7);
		if (s8!=null) this.add(s8);
	}
	

	
	public VectorString(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
		if (s5!=null) this.add(s5);
		if (s6!=null) this.add(s6);
		if (s7!=null) this.add(s7);
		if (s8!=null) this.add(s8);
		if (s9!=null) this.add(s9);
	}

	
	public VectorString(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9,String s10)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
		if (s5!=null) this.add(s5);
		if (s6!=null) this.add(s6);
		if (s7!=null) this.add(s7);
		if (s8!=null) this.add(s8);
		if (s9!=null) this.add(s9);
		if (s10!=null) this.add(s10);
	}

	public VectorString(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9,String s10,String s11)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
		if (s5!=null) this.add(s5);
		if (s6!=null) this.add(s6);
		if (s7!=null) this.add(s7);
		if (s8!=null) this.add(s8);
		if (s9!=null) this.add(s9);
		if (s10!=null) this.add(s10);
		if (s11!=null) this.add(s11);
	}

	public VectorString(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9,String s10,String s11,String s12)
	{
		super();
		if (s1!=null) this.add(s1);
		if (s2!=null) this.add(s2);
		if (s3!=null) this.add(s3);
		if (s4!=null) this.add(s4);
		if (s5!=null) this.add(s5);
		if (s6!=null) this.add(s6);
		if (s7!=null) this.add(s7);
		if (s8!=null) this.add(s8);
		if (s9!=null) this.add(s9);
		if (s10!=null) this.add(s10);
		if (s11!=null) this.add(s11);
		if (s12!=null) this.add(s12);
	}

	
	/**
	 * uebergibt alle strings des vectors verkettetet, trennungszeichen weder am Anfang noch am Ende
	 * @param cTrenner
	 * @return
	 */
	public String get_VerketteteWerte(String cTrenner) {
		String cRueck = "";
		
		for (String cWert: this) {
			cRueck = cRueck+cTrenner+cWert;
		}
		
		if (cRueck.endsWith(cTrenner)) {
			cRueck = cRueck.substring(0,cRueck.length()-1);
		}
		
		return cRueck;
	}
	
	
}
