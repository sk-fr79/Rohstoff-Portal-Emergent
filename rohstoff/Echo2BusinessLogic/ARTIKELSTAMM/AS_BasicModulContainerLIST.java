package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingListeBleibtAusgeklappt;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class AS_BasicModulContainerLIST extends Project_BasicModuleContainer
{
	private AS_BasicModuleContainerMASK		oModuleContainerMASK = null;
	private E2_NavigationList				oNaviList = null;
	
	
	public AS_BasicModulContainerLIST() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ARTIKELSTAMM_LISTE);
		
		this.oModuleContainerMASK = new AS_BasicModuleContainerMASK();

		this.oNaviList = new E2_NavigationList();
		
		this.oNaviList.INIT_WITH_ComponentMAP(new AS_LIST_ComponentMAP(this),null, this.get_MODUL_IDENTIFIER());
		
		AS_ListSelector  oListSelektor = new AS_ListSelector(this,this.oNaviList);
		
		this.add(oListSelektor,E2_INSETS.I_10_10_0_0);
		this.add(new AS_LIST_BedienPanel(this,this.oModuleContainerMASK,this.oNaviList),E2_INSETS.I_10_10_0_0);
		this.add(this.oNaviList,E2_INSETS.I_10_10_0_0);
		
		
		//2011-01-12: Sequenz zum pruefen, ob ein ausklappzustand gespeichert ist
		boolean bPermanentAusKlapp = false;
		Object  Ausklappt = new E2_UserSettingListeBleibtAusgeklappt().get_Settings(this.get_MODUL_IDENTIFIER());
		if (Ausklappt != null && Ausklappt instanceof String)
		{
			bPermanentAusKlapp = ((String)Ausklappt).equals("Y");
		}
		this.oNaviList.get_oComponentMAP__REF().set_bExtendSublistsAutomatic(bPermanentAusKlapp);

		
		oListSelektor.get_oSelVector().doActionPassiv();
	}


	
	//NEU_09
	public E2_NavigationList get_oNaviList() 
	{
		return oNaviList;
	}


}
