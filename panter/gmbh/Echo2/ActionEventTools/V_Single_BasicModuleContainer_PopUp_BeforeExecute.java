package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

public class V_Single_BasicModuleContainer_PopUp_BeforeExecute extends Vector<E2_BasicModuleContainer_PopUp_BeforeExecute>
{
	public V_Single_BasicModuleContainer_PopUp_BeforeExecute()
	{
		super();
	}

	public V_Single_BasicModuleContainer_PopUp_BeforeExecute(E2_BasicModuleContainer_PopUp_BeforeExecute oErsterEintrag)
	{
		super();
		this.add(oErsterEintrag);
	}

	
	public boolean add(E2_BasicModuleContainer_PopUp_BeforeExecute oErsterEintrag)
	{
		boolean bRueck = false;
		
		if (oErsterEintrag != null)
		{
			if (!this.contains(oErsterEintrag))
			{
				bRueck=super.add(oErsterEintrag);
			}
		}
		return bRueck;
	}

	public boolean addAll(Vector<E2_BasicModuleContainer_PopUp_BeforeExecute> v)
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
