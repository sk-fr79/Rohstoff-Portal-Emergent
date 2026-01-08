package panter.gmbh.Echo2.ListAndMask;

import java.util.Collection;
import java.util.Vector;

import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class V_ComponentMAP_SubqueryAGENT extends Vector<XX_ComponentMAP_SubqueryAGENT> 
{

	public V_ComponentMAP_SubqueryAGENT() 
	{
		super();
	}

	public V_ComponentMAP_SubqueryAGENT(Collection<? extends XX_ComponentMAP_SubqueryAGENT> arg0) 
	{
		super(arg0);
	}

	public V_ComponentMAP_SubqueryAGENT(int arg0, int arg1) 
	{
		super(arg0, arg1);
	}

	public V_ComponentMAP_SubqueryAGENT(int arg0) 
	{
		super(arg0);
	}

	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) 	throws myException
	{
		for (int i=0;i<this.size();i++)
		{
			this.get(i).PrepareComponents_For_NEW(oMAP);
		}
	}
	
	
	public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) 	throws myException
	{
		for (int i=0;i<this.size();i++)
		{
			this.get(i).fillComponents(oMAP,oUsedResultMAP);
		}
	}

	
}
