package panter.gmbh.basics4project;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.components.E2_Tabb_Sheet_For_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import echopointng.tabbedpane.DefaultTabModel;



/*
 * in diesem tabbedpane werden die  verschiedenen E2_BasicModuleContainer angeordnet,
 * je nach zugefuegtem modulnamen
 */
public class E2_TabbedPaneForFirstContainer extends 	MyE2_TabbedPane {
	private E2_ContentPane 			oContentPaneThisBelongsTo = null;
	
	/*
	 * vector mit referenzen auf alle Objekte vom typ E2_TabbedPaneTabWithCloseButton,
	 * wird ein modul mit gleichem E2_BasicModuleContainer nochmal gestartet, wird der tab ledigich aktiviert
	 */
	private Vector<E2_Tabb_Sheet_For_BasicModuleContainer> 	vTabbs = new Vector<E2_Tabb_Sheet_For_BasicModuleContainer>();

	
	/*
	 * Ersatzobjekt zur anzeige, wenn das eigentliche object versteckt ist
	 */
	private Component    			ComponentToShowIfContainerIsHidden = null;

	
	//die hashmap der offenen module hier vorhalten und zur verfuegung stellen
	
	/**
	 * @param Base_ContentPaneThisBelongsTo   	- zu diesem ContentPane gehoert das modul 
	 * @param Base_ContentPaneOtherSide				- falls nicht null, kann das modul mit diesem contentpane nach rechts verschoben / neu gestartet werden
	 * @param oTabbedPaneotherSide
	 */
	public E2_TabbedPaneForFirstContainer(	E2_ContentPane 			Base_ContentPaneThisBelongsTo) 	{
		super(null);
		this.oContentPaneThisBelongsTo = Base_ContentPaneThisBelongsTo;
	}
	
	
	
	public void setVisible(boolean bIsVisible) 	{
		super.setVisible(bIsVisible);
		
		if (this.ComponentToShowIfContainerIsHidden != null) 		{
			this.ComponentToShowIfContainerIsHidden.setVisible(!bIsVisible);
		}
	}
	
	
	
	
	public Component get_oComponentToShowIfContainerIsHidden() 
	{
		return ComponentToShowIfContainerIsHidden;
	}
	
	
	public void set_oComponentToShowIfContainerIsHidden(Component componentToShowIfContainerIsHidden) 
	{
		ComponentToShowIfContainerIsHidden = componentToShowIfContainerIsHidden;
		this.ComponentToShowIfContainerIsHidden.setVisible(!this.isVisible());
	}
	
	

	
	
	
	public void add_TabModule(String Kompletter_AufrufString_modul_plus_beschriftung, boolean aktivAmStart) throws myException
	{
		CallStringAnalyser analyse = new CallStringAnalyser(Kompletter_AufrufString_modul_plus_beschriftung);

		//hier schauen, ob es ein solches modul schon gibt
		Vector<E2_Tabb_Sheet_For_BasicModuleContainer>  vTabs = this.get_vTabbs();
		
		//zuerst nachsehen, ob das gestartete modul bereits geladen ist, dann wird es hoechtens aktiviert
		for (int i=0;i<vTabs.size();i++) {
			if (vTabs.get(i).get_cKompletterAufrufString().equals(Kompletter_AufrufString_modul_plus_beschriftung)) {
				if (!vTabs.get(i).get_bActive()) {
					vTabs.get(i).build_ModuleContainer();    //aktivieren und bauen, falls noch nicht gemacht
					this.reload_own_Tabs(new Integer(i));
				} else {
					this.setSelectedIndex(i);
				}
				return;
			}
		}

		this.add_Tabb(new E2_Tabb_Sheet_For_BasicModuleContainer(analyse, this, aktivAmStart));
	}
	
	
	
	private void add_Tabb(E2_Tabb_Sheet_For_BasicModuleContainer oTabSheet)	{

		DefaultTabModel oTabMod = (DefaultTabModel)this.getModel();
		
		int iFoundAt = -1;
		for (int i=0;i<this.vTabbs.size();i++)	{
			String KennerOld = this.vTabbs.get(i).get_cKompletterAufrufString();
			String KennerNew = oTabSheet.get_cKompletterAufrufString();
			if (KennerOld !=null && KennerNew !=null && KennerNew.equals(KennerOld)) {
				iFoundAt = i;
				break;
			}
		}
		
		if (iFoundAt > -1) 	{
			this.setSelectedIndex(iFoundAt);
		} else {
			oTabMod.addTab(oTabSheet.get_oRowLabelButton(),oTabSheet.get_oModuleContainer());
			this.vTabbs.add(oTabSheet);
			this.setSelectedIndex(this.vTabbs.size()-1);
			
		}
		
		this.setVisible(true);
	}


	/**
	 * 
	 * @param iTab
	 * @param b_setSelectedIndex (wenn true, wird der tab auch angesprungen)
	 * @throws myException
	 */
	public void build_ModuleContainer(int iTab, boolean b_setSelectedIndex) throws myException {
		if (this.vTabbs.size()>iTab) {
			if (!this.vTabbs.get(iTab).get_bActive()) {
				this.vTabbs.get(iTab).build_ModuleContainer();
				this.reload_own_Tabs(null);
			}
			
			if (b_setSelectedIndex) {
				this.setSelectedIndex(iTab);
			}
		}
		
	}
	
	

	/**
	 * wenn ein passives tab in aktive, d.h. mit aufgebautem modulContainer gewandelt wird, dann muss der tab-pane neu gebaut werden
	 */
	public void reload_own_Tabs(Integer iNewActivTab) {
		//alle tabs rausschmeisen und wieder einbauen
		
		int iAktiv = (iNewActivTab==null?this.getSelectedIndex():iNewActivTab.intValue());
		
		DefaultTabModel oTabModel = (DefaultTabModel)this.getModel();
				
		//alle raus
		for (int l=0;l<this.vTabbs.size();l++) {
			oTabModel.removeTabAt(0);
		}
		//wieder alle rein
		for (int l=0;l<vTabbs.size();l++) {
			oTabModel.addTab(vTabbs.get(l).get_oRowLabelButton(),vTabbs.get(l).get_oModuleContainer());
		}

		this.setSelectedIndex(iAktiv);
		
	}
	

	
	
	
	
	
	public E2_ContentPane get_oContentPaneLeft()
	{
		return oContentPaneThisBelongsTo;
	}

	
	
	
	
	public ContainerInfo  get_ContainerInfo(String cMODUL_NAME) throws myException
	{
		ContainerInfo  oContainerRueck = null;
		
		for (int i=0;i<this.get_vTabbs().size();i++)
		{
			if (this.vTabbs.get(i).get_CallAnalyser()!=null) {
				if (S.isFull(this.vTabbs.get(i).get_CallAnalyser().get_callString())) {
					if (this.vTabbs.get(i).get_CallAnalyser().get_callString().equals(cMODUL_NAME)) {
						oContainerRueck = new ContainerInfo(i,this.get_vTabbs().get(i));
					}
				}
			}
		}
		return oContainerRueck;
	}
	
	
	
	/**
	 * 
	 * @return s aktiv E2_ModuleContainerInfo
	 */
	public ContainerInfo  get_ContainerInfo_Activ()
	{
		ContainerInfo  oContainerRueck = null;
		
		int iIndex= this.getSelectedIndex();
		
		if (iIndex>=0)   // es ist etwas selektiert
		{
			oContainerRueck = new ContainerInfo(iIndex,this.get_vTabbs().get(iIndex));
		}
		return oContainerRueck;
	}
	

	public String get_MODULKENNER_OF_ACTIVE_MODULE()
	{
		ContainerInfo  oContainerRueck = this.get_ContainerInfo_Activ();
		if (oContainerRueck!=null)
		{
			if (oContainerRueck.oTabSheet.get_CallAnalyser()!=null && 
				oContainerRueck.oTabSheet.get_CallAnalyser().get_CallType()!=null &&  
				oContainerRueck.oTabSheet.get_CallAnalyser().get_CallType()==CallStringAnalyser.ModulTypes.echo2_starter)
			{
				return oContainerRueck.oTabSheet.get_CallAnalyser().get_callString();
			}
		}
		return null;
	}
	
	
	
	public class ContainerInfo
	{
		public  	int 										iTabPosOfContainer = -1;
		public 		E2_Tabb_Sheet_For_BasicModuleContainer  	oTabSheet = null;
		public ContainerInfo(int i_TabPosOfContainer, E2_Tabb_Sheet_For_BasicModuleContainer o_tabSheet) 
		{
			super();
			this.iTabPosOfContainer = i_TabPosOfContainer;
			this.oTabSheet = o_tabSheet;
		}
		
	}
	
	
	
	

	
	

	public Vector<E2_Tabb_Sheet_For_BasicModuleContainer> get_vTabbs()	{
		return vTabbs;
	}


}
