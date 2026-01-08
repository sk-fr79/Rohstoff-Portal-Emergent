package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_BEW_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6025321304238096121L;
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	public static final String NAME_OF_FUHRE_INFO_IN_LIST =     "NAME_OF_FUHRE_INFO_IN_LIST";

	
	// Objekt für die ermittlung der möglichen Mengen für einen bestimmten Preis
	ATOM_LAG_Mengenermittlung_ext 				oMengenErmittlung = null;
	//private Vector<String>  vAuswahlHaufen = new Vector<String>();

	private ATOM_LAG_BEW_LIST_Selector  oLAG_BEW_LIST_Selector = null;

	/**
	 * Standard-Konstruktor für Liste mit Kostenbehafteten Preisen
	 * @throws myException
	 */
	public ATOM_LAG_BEW_LIST_BasicModuleContainer() throws myException{
		this(true);
	}
	
	
	public ATOM_LAG_BEW_LIST_BasicModuleContainer(boolean bPreiseMitKosten) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWERTUNG_MIT_KOSTEN_LISTE);
		
		this.set_bVisible_Row_For_Messages(true);

		ATOM_LAG_BEW_LIST_ComponentMap  ComponentMAP = new ATOM_LAG_BEW_LIST_ComponentMap(bPreiseMitKosten);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		
		oMengenErmittlung = new ATOM_LAG_Mengenermittlung_ext(bPreiseMitKosten);
		
		this.oLAG_BEW_LIST_Selector = new ATOM_LAG_BEW_LIST_Selector(oNaviList,oMengenErmittlung, this.get_MODUL_IDENTIFIER(),bPreiseMitKosten);    //vAuswahlHaufen wird bei jeder selektion geleert

		oNaviList.INIT_WITH_ComponentMAP(ComponentMAP,E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		// Das Bedienpanel
		ATOM_LAG_BEW_LIST_BedienPanel oPanel = new ATOM_LAG_BEW_LIST_BedienPanel(oNaviList,this.oLAG_BEW_LIST_Selector,new ATOM_LAG_BEW_MASK_BasicModuleContainer());
		this.add(oPanel);
		
		// Objekte für die Mengenermittlungen 
		// das Objekt für die Mengenermittlung und das Panel werden hier gegenseitig referenziert...
		// könnte zu Problemen führen, beim Freigeben von Speicher...
		ATOM_LAG_BEW_LIST_Panel_MengenErmittlung oPanelME = new ATOM_LAG_BEW_LIST_Panel_MengenErmittlung(oNaviList,this.oLAG_BEW_LIST_Selector,oMengenErmittlung );
		oMengenErmittlung.addObserver(oPanelME);
		int t = oMengenErmittlung.countObservers();
		this.add(oPanelME);  

		// das Grid
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		ComponentMAP.set_oSubQueryAgent(new ATOM_LAG_BEW_LIST_FORMATING_Agent(oMengenErmittlung.getAuswahlListe()));
		
		this.oLAG_BEW_LIST_Selector.get_oSelVector().doActionPassiv();
	}


	/**
	 * @return the oLAG_BEW_LIST_Selector
	 */
	public ATOM_LAG_BEW_LIST_Selector get_oLAG_BEW_LIST_Selector()
	{
		return oLAG_BEW_LIST_Selector;
	}
		
	
	
	
}
