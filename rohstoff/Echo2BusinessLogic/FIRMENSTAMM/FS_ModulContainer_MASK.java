package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.tabbedpane.DefaultTabModel;

public class FS_ModulContainer_MASK extends Project_BasicModuleContainer_MASK
{

	
	/*
	 * das tabbedPane fuer die maske
	 */
	//private ownTabbedPane oTabbedPaneMask = new ownTabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain(-2)), null);
	//2014-01-16: umstellung auf linewrap
	private ownTabbedPane oTabbedPaneMask = new ownTabbedPane(new MyE2_TabbedPane.MyE2_TabModelLineWrap(new E2_FontPlain(-2),2*bibALL.get_FONT_SIZE()+4), null);


	private FS_MASK_ComponentMAP oComponentMAP = null;
	



	public FS_ModulContainer_MASK() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK);
		
		this.getOBUTTON_MODULVERWALTUNG().add_cZusatzMODULKENNER(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK_MATSPEZ);
		
		//DEBUG.System_println(""+(bibALL.get_FONT_SIZE()+4), null);
		
		this.oComponentMAP = new FS_MASK_ComponentMAP(this);
		
		this.INIT(oComponentMAP,new FS_Mask(oComponentMAP,this.oTabbedPaneMask),
											new Extent(FS_CONST.MASK_WIDTH),
											new Extent(FS_CONST.MASK_HEIGHT));
		
		this.add_SubTableComponentMAP(oComponentMAP.get_E2_ComponentMAP_Firmeninfo());
	}

	public FS_MASK_ComponentMAP get_oComponentMAP()
	{
		return oComponentMAP;
	}




	public MyE2_TabbedPane get_oTabbedPaneMask()
	{
		return oTabbedPaneMask;
	}

	
	/*
	 * hier wird die klasse formal abgeleitet, da nur dann die tab-reihenfolge gespeichert werden kann
	 */
	private class ownTabbedPane extends MyE2_TabbedPane
	{

		public ownTabbedPane(DefaultTabModel tabModel, Integer ForcedHeight)
		{
			super(tabModel, ForcedHeight);
		}

		public ownTabbedPane(Integer ForcedHeight)
		{
			super(ForcedHeight);
		}
		
	}
	
	
	
	/**
	 * 
	 * @param cTabTextOrig (selektiert die Seite mit der übergebenen Beschriftung)
	 * @throws myException 
	 */
	public void set_TabAsActive(String cTabTextOrig) throws myException {
		
		int iTab = this.oTabbedPaneMask.get_SortVector().indexOf(cTabTextOrig);
		if (iTab>=0) {
			if (this.oTabbedPaneMask.get_hmAgentsAndButtons().get(cTabTextOrig)!=null) {
				this.oTabbedPaneMask.get_hmAgentsAndButtons().get(cTabTextOrig).oActionAgent.executeAgentCode(null);
			}
			
			this.oTabbedPaneMask.setSelectedIndex(iTab);
		}
	}
	
	
	
	
}
