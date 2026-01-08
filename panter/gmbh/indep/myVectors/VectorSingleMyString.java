package panter.gmbh.indep.myVectors;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;

/*
 * spezieller Vector, der jedes element nur einmal uebernimmt
 */
public class VectorSingleMyString extends Vector<MyString>
{
	
	public VectorSingleMyString()
	{
		super();
	}

	public VectorSingleMyString(MyString cErsterEintrag)
	{
		super();
		super.add(cErsterEintrag);
	}

	
	public boolean add(MyString o)
	{
		boolean bRueck = false;
		
		if (!this.bContains(o))
			bRueck=super.add(o);
		
		return bRueck;
	}

	public boolean addAll(Vector<MyString> v)
	{
		boolean bRueck = true;
		
		for (int i=0;i<v.size();i++)
		{
			if (!this.bContains(v.get(i)))
				this.add(v.get(i));
		}
		
		return bRueck;
	}
	
	
	public boolean addAll(Collection<? extends MyString> v)
	{
		boolean bRueck = true;
		
		for (MyString cWert: v)
		{
			if (!this.bContains(cWert))
				this.add(cWert);
		}
		
		return bRueck;
	}

	
	public boolean addAllOnlyNotEmpty(Collection<? extends MyString> v)
	{
		boolean bRueck = true;
		
		for (MyString cWert: v)
		{
			if (!this.bContains(cWert))
			{
				if (S.isFull(cWert))
				{
					this.add(cWert);
				}
			}
		}
		return bRueck;
	}

	
	private boolean bContains(MyString oTestString) {
		for (int i=0;i<this.size();i++) {
			if (S.NN(oTestString.COrig()).equals(S.NN(this.get(i).COrig()))) {
				return true;
			}
		}
		return false;
	}
	
}
