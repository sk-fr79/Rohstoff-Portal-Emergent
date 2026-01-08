package panter.gmbh.indep.myVectors;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.indep.S;

/*
 * spezieller Vector, der jedes element nur einmal uebernimmt
 */
public class VectorSingle extends Vector<String>
{
	
	public VectorSingle()
	{
		super();
	}

	public VectorSingle(String cErsterEintrag)
	{
		super();
		this.add(cErsterEintrag);
	}

	
	public boolean add(String o)
	{
		boolean bRueck = false;
		
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
	
	
	public boolean addAll(Collection<? extends String> v)
	{
		boolean bRueck = true;
		
		for (String cWert: v)
		{
			if (!this.contains(cWert))
				this.add(cWert);
		}
		
		return bRueck;
	}

	
	public boolean addAllOnlyNotEmpty(Collection<? extends String> v)
	{
		boolean bRueck = true;
		
		for (String cWert: v)
		{
			if (!this.contains(cWert))
			{
				if (S.isFull(cWert))
				{
					this.add(cWert);
				}
			}
		}
		return bRueck;
	}

	
	
}
