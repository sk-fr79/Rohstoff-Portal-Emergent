package panter.gmbh.indep.myVectors;

import java.util.Vector;

import panter.gmbh.indep.S;

/*
 * spezieller Vector, der jedes element nur einmal uebernimmt
 */
public class VectorSingleIgnoreEmptys extends Vector<String>
{
	
	public VectorSingleIgnoreEmptys()
	{
		super();
	}

	public VectorSingleIgnoreEmptys(String cErsterEintrag)
	{
		super();
		this.add(cErsterEintrag);
	}

	
	public boolean add(String o)
	{
		boolean bRueck = false;
		
		if (S.isEmpty(o))
			return bRueck;
		
		if (!this.contains(o))
			bRueck=super.add(o);
		
		return bRueck;
	}

	public boolean addAll(Vector<String> v)
	{
		boolean bRueck = true;
		
		for (int i=0;i<v.size();i++)
		{
			if (!this.contains(v.get(i)))
				this.add(v.get(i));
		}
		
		
		return bRueck;
	}
	
	
	
}
