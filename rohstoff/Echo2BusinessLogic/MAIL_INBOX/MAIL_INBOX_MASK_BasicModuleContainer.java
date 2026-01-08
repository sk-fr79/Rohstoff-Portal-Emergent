package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.tabbedpane.DefaultTabModel;


public class MAIL_INBOX_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{

	/*
	 * das tabbedPane fuer die maske
	 */
	private ownTabbedPane oTabbedPaneMask = new ownTabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain(0)), null);
	
	/*
	 * die Component-Map
	 */
	private MAIL_INBOX_MASK_ComponentMAP oMAIL_INBOX_MASK_ComponentMAP = null;

	
	public MAIL_INBOX_MASK_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_MAIL_INBOX_MASKE);

		this.set_bVisible_Row_For_Messages(true);
		
		oMAIL_INBOX_MASK_ComponentMAP = new MAIL_INBOX_MASK_ComponentMAP(this);
		
		this.INIT(oMAIL_INBOX_MASK_ComponentMAP,
				new MAIL_INBOX_MASK(oMAIL_INBOX_MASK_ComponentMAP,oTabbedPaneMask),
				new Extent(900), new Extent(650));
		
//		this.INIT(oMAIL_INBOX_MASK_ComponentMAP, new MAIL_INBOX_MASK(oMAIL_INBOX_MASK_ComponentMAP), new Extent(900), new Extent(650));
		
		
	}
	
	

	public MAIL_INBOX_MASK_ComponentMAP get_oComponentMAP()
	{
		return oMAIL_INBOX_MASK_ComponentMAP;
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
	
	
	
	
	
}
