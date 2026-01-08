package panter.gmbh.indep.myVectors;

import java.util.Collections;
import java.util.Vector;

public class VectorComparer
{
	
	private VectorSingle vTableBeide = 		new VectorSingle();
	private VectorSingle vTableFehltIn1 = 	new VectorSingle();
	private VectorSingle vTableFehltIn2 = 	new VectorSingle();

	public VectorComparer(Vector<String> V1, Vector<String> V2, boolean Sort)
	{
		super();
		
		
		for (int i=0;i<V1.size();i++)
		{
			String cWert = V1.get(i);
			if (!vTableBeide.contains(cWert) && V2.contains(cWert))
			{
				vTableBeide.add(cWert);
			}
		}

		for (int i=0;i<V2.size();i++)
		{
			String cWert = V2.get(i);
			if (!vTableBeide.contains(cWert) && V1.contains(cWert))
			{
				vTableBeide.add(cWert);
			}
		}

		for (int i=0;i<V2.size();i++)
		{
			String cWert = V2.get(i);
			if (!V1.contains(cWert))
			{
				vTableFehltIn1.add(cWert);
			}
		}
		
		for (int i=0;i<V1.size();i++)
		{
			String cWert = V1.get(i);
			if (!V2.contains(cWert))
			{
				vTableFehltIn2.add(cWert);
			}
		}

		if (Sort)
		{
			Collections.sort(vTableBeide);
			Collections.sort(vTableFehltIn1);
			Collections.sort(vTableFehltIn2);
		}

	}

	public VectorSingle get_vBeide()
	{
		return vTableBeide;
	}

	public VectorSingle get_vFehltIn1()
	{
		return vTableFehltIn1;
	}

	public VectorSingle get_vFehltIn2()
	{
		return vTableFehltIn2;
	}

}
