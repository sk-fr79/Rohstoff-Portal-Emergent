package panter.gmbh.indep;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class myHashMapString extends HashMap<String, String>
{

	public myHashMapString()
	{
		super();
	}

	public myHashMapString(int initialCapacity, float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}

	public myHashMapString(int initialCapacity)
	{
		super(initialCapacity);
	}

	public myHashMapString(Map<? extends String, ? extends String> m)
	{
		super(m);
	}

	
	/**
	 * 
	 * @param cInFront
	 * @param bValueInFront (dann wird an position 0 nicht der key, sondern der wert eingetragen)
	 * @return
	 */
	public String[][] get_Array(String[][] cInFront, boolean bValueInFront)
	{
		int iSize = this.size();
		
		if (cInFront!=null && cInFront.length>0 && cInFront[0].length==2)
		{
			iSize += cInFront.length;
		}
		
		
		
		String[][] cRueck = new String[iSize][2];
		
		Iterator<String>  itKeys = this.keySet().iterator();
		
		int i=0;
		
		if (cInFront!=null && cInFront.length>0 && cInFront[0].length==2)
		{
			for (int j=0;j<cInFront.length;j++)
			{
				if (bValueInFront)
				{
					cRueck[i][1]=cInFront[j][0];
					cRueck[i][0]=cInFront[j][1];
				}
				else
				{
					cRueck[i][0]=cInFront[j][0];
					cRueck[i][1]=cInFront[j][1];
				}
				i++;
			}
		}
		
		while (itKeys.hasNext())
		{
			String cKey = itKeys.next();
			if (bValueInFront)
			{
				cRueck[i][1]=cKey;
				cRueck[i][0]=this.get(cKey);
			}
			else
			{
				cRueck[i][0]=cKey;
				cRueck[i][1]=this.get(cKey);
			}

			
			i++;
		}
		
        return cRueck;
		
	}
	
}
