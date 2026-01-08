package rohstoff.utils.SQL_DAEMONS;

import java.util.Vector;

/*
 * spezieller Vector, der jedes element nur einmal uebernimmt
 */
public class VectorSingle_PRUEF_Interface extends Vector<IF_PRUEF_Interface>
{
	
	public boolean add(IF_PRUEF_Interface o)
	{
		boolean bRueck = false;
		
		if (!this.contains(o))
			bRueck=super.add(o);
		
		return bRueck;
	}

	public boolean addAll(Vector<IF_PRUEF_Interface> v)
	{
		boolean bRueck = true;
		
		for (int i=0;i<v.size();i++)
		{
			if (!this.contains(v.get(i)))
				this.add(v.get(i));
		}
		
		
		return bRueck;
	}
	
	
	public boolean has_SomethingToDo()
	{
		for (IF_PRUEF_Interface pruefer: this)
		{
			if (!pruefer.IsDone())
			{
				return true;
			}
		}
		return false;
	}
	
}
