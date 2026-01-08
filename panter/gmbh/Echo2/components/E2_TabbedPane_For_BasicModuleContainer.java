package panter.gmbh.Echo2.components;

import java.util.Vector;

import echopointng.tabbedpane.DefaultTabModel;



/**
 * @deprecated    eigene methoden wurden direkt in die klasse E2_TabbedPaneForFirstContainer uebernommen
 * @author martin
 *
 */
public class E2_TabbedPane_For_BasicModuleContainer extends MyE2_TabbedPane
{
	
	/*
	 * vector mit referenzen auf alle Objekte vom typ E2_TabbedPaneTabWithCloseButton,
	 * wird ein modul mit gleichem E2_BasicModuleContainer nochmal gestartet, wird der tab ledigich aktiviert
	 */
	private Vector<E2_Tabb_Sheet_For_BasicModuleContainer> 	vTabbs = new Vector<E2_Tabb_Sheet_For_BasicModuleContainer>();
	
	
	
	
	public E2_TabbedPane_For_BasicModuleContainer()
	{
		super(null);
	}


	public void add_Tabb(E2_Tabb_Sheet_For_BasicModuleContainer oTabSheet)
	{

		DefaultTabModel oTabMod = (DefaultTabModel)this.getModel();
		
		int iFoundAt = -1;
		for (int i=0;i<this.vTabbs.size();i++)
		{
			String KennerOld = this.vTabbs.get(i).get_cKompletterAufrufString();
			String KennerNew = oTabSheet.get_cKompletterAufrufString();
			if (KennerOld !=null && KennerNew !=null && KennerNew.equals(KennerOld))
			{
				iFoundAt = i;
				break;
			}
		}
		
		if (iFoundAt > -1)
		{
			this.setSelectedIndex(iFoundAt);
		}
		else
		{
			oTabMod.addTab(oTabSheet.get_oRowLabelButton(),oTabSheet.get_oModuleContainer());
			this.vTabbs.add(oTabSheet);
			this.setSelectedIndex(this.vTabbs.size()-1);
			
			//oTabSheet.set_iNumberInTabbedPane(this.vTabbs.size()-1);
		}
		
		this.setVisible(true);
	}

	
	

	public Vector<E2_Tabb_Sheet_For_BasicModuleContainer> get_vTabbs()
	{
		return vTabbs;
	}
	
	
	
	
	
}
