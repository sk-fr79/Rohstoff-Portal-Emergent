package rohstoff.Echo2BusinessLogic.EAK;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR.CAL__BUTTON_StartCalendar;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.GLOBAL_REPORTS.REPORT__BUTTON_StartReports;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Button;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2__Button;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.BUTTON_MODULVERWALTUNG;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS.TODO__BUTTON_StartTodoList;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EAK.BRANCHE.EAKB_LIST_ModuleContainer;
import rohstoff.Echo2BusinessLogic.EAK.CODE.EAKC_LIST_ModuleContainer;
import rohstoff.Echo2BusinessLogic.EAK.GRUPPE.EAKG_LIST_ModuleContainer;

public class EAK_BasicModuleContainer extends E2_BasicModuleContainer
{
	/*
	 * modulecontainer fuer den tab mit den drei reitern:
	 * Branche / Gruppe / Code
	 */
	
	private EAKB_LIST_ModuleContainer oContainerBRANCHE = null;
	private EAKG_LIST_ModuleContainer oContainerGRUPPE = null;
	private EAKC_LIST_ModuleContainer oContainerCODE = null;
	
	
	private MyE2_TabbedPane oTabbed = new MyE2_TabbedPane(new Integer(600));

	
	/*
	 * sprachentabelle, wird allen modulen fuer den uebersetzungsteil zur verfuegung gestellt
	 */
	private String[][]   	cSPRACHEN = null;
	
	// layouts fuer die ausklapp-tabellen
	private MutableStyle	 StyleForUebersetzungsTables = null;
	
	private GridLayoutData   oGridLayoutData = new GridLayoutData();
	
	
	public EAK_BasicModuleContainer() throws myException
	{
		super();
		
		
		this.StyleForUebersetzungsTables = new MutableStyle();
		this.StyleForUebersetzungsTables.setProperty(Grid.PROPERTY_BORDER, new Border(0,new E2_ColorLLight(),Border.STYLE_SOLID));
		this.StyleForUebersetzungsTables.setProperty(Grid.PROPERTY_BACKGROUND, new E2_ColorLLight());
		
		this.oGridLayoutData.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		this.oGridLayoutData.setBackground(new E2_ColorLLight());
		this.oGridLayoutData.setInsets(E2_INSETS.I_1_1_1_1);
		
		
		this.cSPRACHEN = bibALL.add_EmptyPairInFrontOfArray(bibDB.EinzelAbfrageInArray("SELECT BEZEICHNUNG,ID_SPRACHE FROM JD_SPRACHE ORDER BY BEZEICHNUNG"),"-");
		
		this.oContainerBRANCHE = 	new EAKB_LIST_ModuleContainer(this);
		this.oContainerGRUPPE = 	new EAKG_LIST_ModuleContainer(this);
		this.oContainerCODE = 		new EAKC_LIST_ModuleContainer(this);
		
		this.add_AddonComponent(new BUTTON_MODULVERWALTUNG(this.oContainerBRANCHE));
		
		//2015-10-08: neues hilfemodul
		//this.add_AddonComponent(new HELP_BUTTON_FOR_MODULES(this.oContainerBRANCHE));
		//this.add_AddonComponent(new HAD_Button(this.oContainerBRANCHE));
		
		//2018-09-05: neues hilfemodul
		this.add_AddonComponent(new HELP2__Button(this.oContainerBRANCHE));

		
		this.add_AddonComponent(new BUTTON_MODULVERWALTUNG(this.oContainerGRUPPE));
		//2015-10-08: neues hilfemodul
		//this.add_AddonComponent(new HELP_BUTTON_FOR_MODULES(this.oContainerGRUPPE));
		//this.add_AddonComponent(new HAD_Button(this.oContainerGRUPPE));

		//2018-09-05: neues hilfemodul
		this.add_AddonComponent(new HELP2__Button(this.oContainerGRUPPE));
		
		
		this.add_AddonComponent(new BUTTON_MODULVERWALTUNG(this.oContainerCODE));
		//2015-10-08: neues hilfemodul
		//this.add_AddonComponent(new HELP_BUTTON_FOR_MODULES(this.oContainerCODE));
		//this.add_AddonComponent(new HAD_Button(this.oContainerCODE));

		//2018-09-05: neues hilfemodul
		this.add_AddonComponent(new HELP2__Button(this.oContainerCODE));
	
		
		this.add_AddonComponent(new CAL__BUTTON_StartCalendar(this.oContainerCODE));
		this.add_AddonComponent(new REPORT__BUTTON_StartReports(this.oContainerCODE));
		this.add_AddonComponent(new TODO__BUTTON_StartTodoList(this.oContainerCODE));


		
		this.oTabbed.add_Tabb(new MyE2_String("Branchen"),	this.oContainerBRANCHE);
		this.oTabbed.add_Tabb(new MyE2_String("Gruppen"),	this.oContainerGRUPPE);
		this.oTabbed.add_Tabb(new MyE2_String("Codes"),		this.oContainerCODE);
	
		
		
		
		this.add(this.oTabbed);
	}


	public EAKB_LIST_ModuleContainer get_oContainerBRANCHE()
	{
		return oContainerBRANCHE;
	}


	public EAKC_LIST_ModuleContainer get_oContainerCODE()
	{
		return oContainerCODE;
	}


	public EAKG_LIST_ModuleContainer get_oContainerGRUPPE()
	{
		return oContainerGRUPPE;
	}


	public MyE2_TabbedPane get_oTabbed()
	{
		return oTabbed;
	}


	public String[][] get_cSPRACHEN()
	{
		return cSPRACHEN;
	}


	public MutableStyle get_StyleForUebersetzungsTables()
	{
		return StyleForUebersetzungsTables;
	}


	public GridLayoutData get_oGridLayoutData()
	{
		return oGridLayoutData;
	}

}
