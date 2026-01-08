package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_ComponentGroupVertical;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_SelectField_Zeitraum;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_Selector_Fragment_User;

public class WF_HEAD_LIST_SelectionComponentVector_Version2_alt extends E2_SelectionComponentsVector
{

	

	// Dropdown-List für die User
	protected MyE2_CheckBox 			cbShowLZ_Abgeschlossen 	= new MyE2_CheckBox("Laufzettel ist abgeschlossen",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
	protected MyE2_CheckBox 			cbShowLZ_Geloescht 		= new MyE2_CheckBox("Laufzettel ist gelöscht",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
	protected MyE2_CheckBox				cbShowLZ_OhneAufgaben 	= new MyE2_CheckBox("Laufzettel hat keine Aufgaben",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
	
	protected ownSelektorNurAktiveDesBenutzers 		selOnlyOwnActive = new ownSelektorNurAktiveDesBenutzers();
	protected WF_HEAD_LIST_Selector_Fragment_User 	selUser = new WF_HEAD_LIST_Selector_Fragment_User();
	
	protected E2_NavigationList 				oNaviList 	= null;
	protected String[][] 		 				arrZeitraum = null;
	
	
	public WF_HEAD_LIST_SelectionComponentVector_Version2_alt( 	E2_NavigationList oNavigationList ,
															Component  oButtonSwitchToNew, 
															String cMODULE_KENNER) throws myException {
		super(oNavigationList);
		this.oNaviList = oNavigationList;
		
		// voreinstellungen
		selOnlyOwnActive.get_CheckboxBearbeiter().setSelected(true);

		selUser.get_SelectField().set_ActiveValue_OR_FirstValue(bibALL.get_ID_USER());
		this.add(selUser);
		
		
		this.add(new E2_ListSelectorStandard(cbShowLZ_Abgeschlossen,"","  JT_LAUFZETTEL.ABGESCHLOSSEN_AM IS NULL "));
		this.add(new E2_ListSelectorStandard(cbShowLZ_Geloescht,"","  NVL(JT_LAUFZETTEL.GELOESCHT,'N')='N' "));

		this.add(selOnlyOwnActive);
		
		ownSelektorBetrachtungszeitraum oSelBetrachtungsZeitraum = new ownSelektorBetrachtungszeitraum();
		this.add(oSelBetrachtungsZeitraum);	

		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.add(oDB_BasedSelektor);

		//2012-01-16: selektor fuer prioritaeten
		ownListSelectorPrioritaet  oSelPrio = new ownListSelectorPrioritaet();
		this.add(oSelPrio);

		
		
		//
		//  GUI
		//
		
		MyE2_Grid oGridMain = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		// Zeile 1
		oGridMain.add(new MyE2_Label( new MyE2_String("Laufzettel mit Beteiligung von:"),MyE2_Label.STYLE_NORMAL_PLAIN()),2,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("Priorität")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("Betrachtungszeitraum")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		//oGridMain.add(new MyE2_Label( new MyE2_String("Selektor umschalten")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		
		// Zeile 2
		oGridMain.add( selUser.get_oComponentForSelection(),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add( new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add( oSelPrio.get_oComponentForSelection(),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add( new E2_ComponentGroupHorizontal(0,  oSelBetrachtungsZeitraum.get_oComponentForSelection(), this.get_oButtonReload(),E2_INSETS.I_0_0_0_0)	,1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		// oGridMain.add( oButtonSwitchToNew,1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);

		
		// Zeile 3
		MyE2_Label lblHeadingLaufzettel = new MyE2_Label(new MyE2_String("Berücksichtigte Laufzettel"),MyE2_Label.STYLE_NORMAL_PLAIN());
		oGridMain.add(new E2_ComponentGroupVertical(E2_INSETS.I_0_0_0_0,0,lblHeadingLaufzettel,cbShowLZ_OhneAufgaben,cbShowLZ_Abgeschlossen,cbShowLZ_Geloescht) ,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(selOnlyOwnActive.get_oComponentForSelection(),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new E2_ComponentGroupVertical(E2_INSETS.I_0_0_0_0,0, new MyE2_Label( new MyE2_String("Diverse:")),oDB_BasedSelektor.get_oComponentForSelection(100))
						,1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		
		oGridMain.setColumnWidth(0, new Extent(320));
		
		this.set_oSelComponent(oGridMain);
		
	}
	
	
	
	private class OptionSelectorAction extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_NavigationList oList = WF_HEAD_LIST_SelectionComponentVector_Version2_alt.this.oNaviList;
			
			Vector<E2_ComponentMAP>  vMaps = oList.get_vComponentMAPS();
			
			for (E2_ComponentMAP oMap: vMaps)
			{
				if (oMap.get_List_EXPANDER_4_ComponentMAP().get_bIsOpen())
				{
					oMap.get_List_EXPANDER_4_ComponentMAP().refreshDaughterComponent();
				}
			}
			
		}
		
		
		
	}
	
	

	
	
	/**
	 * Selektor-checkbox für die aktiven Einträge des gewählten Benutzers
	 * @author manfred
	 *
	 */
	private class ownSelektorNurAktiveDesBenutzers extends XX_ListSelektor{
		private MyE2_CheckBox cbShowOwnBearbeiter 	= new MyE2_CheckBox("Ist Aufgaben-Bearbeiter ", MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		private MyE2_CheckBox cbShowOwnBesitzer 	= new MyE2_CheckBox("ODER Ist Aufgaben-Besitzer ", MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		private MyE2_CheckBox cbShowAbgeschlossene	= new MyE2_CheckBox("Abgeschlossene Aufgaben berücksichtigen",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());


		@Override
		public String get_WhereBlock() throws myException {
			// ausgewählten User ermitteln
			WF_HEAD_LIST_SelectionComponentVector_Version2_alt oThis = WF_HEAD_LIST_SelectionComponentVector_Version2_alt.this;
			String idUser = oThis.selUser.get_SelectField().get_ActualWert();

			String sWhereBearbeiter = "";
			String sWhereBesitzer = "";
			String sWhereAbgeschlossen = "";
			
			
			if (cbShowOwnBearbeiter.isSelected() || cbShowOwnBesitzer.isSelected()){
				cbShowAbgeschlossene.set_bEnabled_For_Edit(true);
			} else {
				cbShowAbgeschlossene.set_bEnabled_For_Edit(false);
				cbShowAbgeschlossene.setSelected(false);
			}
			
			
			if (!cbShowAbgeschlossene.isSelected() ){
				sWhereAbgeschlossen = " AND E. ABGESCHLOSSEN_AM is null";
			}
			
			if ( cbShowOwnBearbeiter.isSelected() ){
				sWhereBearbeiter = " E.ID_USER_BEARBEITER = "+ idUser + " ";
			}
			if ( cbShowOwnBesitzer.isSelected() ){
				sWhereBesitzer = " E.ID_USER_BESITZER = "+ idUser + " ";
			}
			
			String sWhere = "";
			
			if (( cbShowOwnBearbeiter.isSelected() && cbShowOwnBesitzer.isSelected())){
				sWhere +=  sWhereBearbeiter +  " OR " + sWhereBesitzer ;
			} else {
				sWhere +=  sWhereBearbeiter +  sWhereBesitzer ;
			}
			
			
			String sSelectOwnActiveOnly = "";
			if (!bibALL.isEmpty(idUser) &&  sWhere.length() > 0 ){
				sSelectOwnActiveOnly =
								" JT_LAUFZETTEL.ID_LAUFZETTEL IN ( "+
								" 		SELECT L.ID_LAUFZETTEL "+
								" 		FROM   JT_LAUFZETTEL L LEFT OUTER JOIN JT_LAUFZETTEL_EINTRAG E ON L.ID_LAUFZETTEL = E.ID_LAUFZETTEL "+
								" 		WHERE  (  "+ sWhere +  " ) " +
								sWhereAbgeschlossen +
								"       AND ( NVL(E.GELOESCHT,'N') = 'N' )"+
								") ";
			}
			return sSelectOwnActiveOnly;
		}

		
		@Override
		public Component get_oComponentForSelection() throws myException {
			MyE2_Grid oGridRueckOuter = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			MyE2_Grid oGridRueck = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGridRueck.add(new MyE2_Label(new MyE2_String("Laufzettel-Aufgaben des ausgewählten Mitarbeiters"),MyE2_Label.STYLE_NORMAL_PLAIN()),1,E2_INSETS.I_0_0_0_0);
			oGridRueck.add(this.cbShowOwnBearbeiter);
			oGridRueck.add(this.cbShowOwnBesitzer);
			oGridRueck.add(this.cbShowAbgeschlossene);
			oGridRueckOuter.add(oGridRueck, E2_INSETS.I_0_0_0_0);
			return oGridRueckOuter;
		}

		@Override
		public Component get_oComponentWithoutText() throws myException {
			return get_oComponentForSelection();
		}

		@Override
		public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
			this.cbShowOwnBearbeiter.add_oActionAgent(oAgent);
			this.cbShowOwnBesitzer.add_oActionAgent(oAgent);
			this.cbShowAbgeschlossene.add_oActionAgent(oAgent);
		}

		@Override
		public void doInternalCheck() {
			
		}
		
		
		/**
		 * Referenz auf die Checkbox
		 * @return
		 */
		public MyE2_CheckBox get_CheckboxBearbeiter(){
			return this.cbShowOwnBearbeiter;
		}

		/**
		 * Referenz auf die Checkbox
		 * @return
		 */
		public MyE2_CheckBox get_CheckboxBesitzer(){
			return this.cbShowOwnBesitzer;
		}

		
	}
	

	

	/**
	 * eigener Selektor fuer den Beobachtungszeitraum 
	 * @author manfred
	 * @date 2011-11-31
	 *
	 */
	private class ownSelektorBetrachtungszeitraum extends XX_ListSelektor
	{

		private WF_HEAD_LIST_SelectField_Zeitraum  oSelZeitraum = null;
		
		public ownSelektorBetrachtungszeitraum() throws myException
		{
			super();
			// Zeitraum in die Zukunft
			arrZeitraum = new String[][]{
					{new MyE2_String("1 Tag").CTrans(),"1"},
					{new MyE2_String("2 Tage").CTrans(),"2"},
					{new MyE2_String("3 Tage").CTrans(),"3"},
					{new MyE2_String("4 Tage").CTrans(),"4"},
					{new MyE2_String("5 Tage").CTrans(),"5"},
					{new MyE2_String("6 Tage").CTrans(),"6"},
					{new MyE2_String("7 Tage").CTrans(),"7"},
					{new MyE2_String("2 Wochen").CTrans(),"14"},
					{new MyE2_String("3 Wochen").CTrans(),"21"},
					{new MyE2_String("4 Wochen").CTrans(),"28"},
					{new MyE2_String("5 Wochen").CTrans(),"35"},
					{new MyE2_String("10 Wochen").CTrans(),"70"},
					{new MyE2_String("20 Wochen").CTrans(),"140"},
					{new MyE2_String("30 Wochen").CTrans(),"210"},
					{new MyE2_String("1 Jahr").CTrans(),"365"},
					{new MyE2_String("unbegrenzt").CTrans(),"999999"},
					};
			this.oSelZeitraum = new WF_HEAD_LIST_SelectField_Zeitraum(arrZeitraum, null, false);
			this.oSelZeitraum.set_ActiveInhalt_or_FirstInhalt( new MyE2_String("2 Wochen").CTrans() );
		}

		@Override
		public String get_WhereBlock() throws myException
		{
			String cWert = this.oSelZeitraum.get_ActualWert();
			
			String cWhereBlockRueck = " JT_LAUFZETTEL.ID_LAUFZETTEL in (" + 
									  " 	SELECT L.ID_LAUFZETTEL FROM  "+bibE2.cTO()+".JT_LAUFZETTEL L " +
									  "		LEFT OUTER JOIN  "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG E ON " +
									  " 	L.ID_LAUFZETTEL = E.ID_LAUFZETTEL " +
									  " 	AND E.ABGESCHLOSSEN_AM IS NULL" + 
									  " 	AND NVL(E.GELOESCHT,'N') = 'N' " +
									  " 	WHERE NVL(E.FAELLIG_AM,SYSDATE + " + cWert + ") <= (SYSDATE + " + cWert + ")" + 
									  " )";
			
			return cWhereBlockRueck;
		}

		
		@Override
		public Component get_oComponentForSelection()
		{
			MyE2_Grid oGridRueck = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			oGridRueck.add(this.oSelZeitraum);
			return oGridRueck;
		}

		@Override
		public Component get_oComponentWithoutText()
		{
			return this.get_oComponentForSelection();
		}

		@Override
		public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
		{
			this.oSelZeitraum.add_oActionAgent(oAgent);
		}

		@Override
		public void doInternalCheck()
		{
			
		}
		
	}
	
	
	//2012-01-16: neuer selektor fuer die laufzettel: selektion nach enthalten sein einer aufgabe mit einer bestimmten prioritaet
	private class ownListSelectorPrioritaet extends XX_ListSelektor
	{
		private MyE2_SelectField  oSelectPrio = null;

		public ownListSelectorPrioritaet() throws myException
		{
			super();
			
			MyDropDownSettings oDD = new MyDropDownSettings(bibE2.cTO(),"JT_LAUFZETTEL_PRIO","PRIO","ID_LAUFZETTEL_PRIO",null,"ISDEFAULT",true, "PRIO_SORT",false);
			
			this.oSelectPrio = new MyE2_SelectField(oDD.getDD(), "", false);
			
			this.oSelectPrio.setToolTipText(new MyE2_String("Selektiert alle Laufzettel mit einem Eintrag dieser Priorität").CTrans());
			
		}

		@Override
		public String get_WhereBlock() throws myException
		{
			String cWert = this.oSelectPrio.get_ActualWert();
			
			if (S.isEmpty(cWert.trim()))
			{
				return "";
			}
			
			
			String cWhereBlockRueck = " JT_LAUFZETTEL.ID_LAUFZETTEL in (" +
									  " SELECT L.ID_LAUFZETTEL FROM  JT_LAUFZETTEL L LEFT OUTER JOIN " + 
									  bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG E ON L.ID_LAUFZETTEL = E.ID_LAUFZETTEL WHERE " +
									  " E." + RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL_PRIO + " = " + cWert + " "+ 
									  ")";
			return cWhereBlockRueck;
		}

		@Override
		public Component get_oComponentForSelection()
		{
			MyE2_Grid oGridRueck = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			oGridRueck.add(this.oSelectPrio);
			return oGridRueck;
		}

		@Override
		public Component get_oComponentWithoutText()
		{
			return this.get_oComponentForSelection();
		}

		@Override
		public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
		{
			this.oSelectPrio.add_oActionAgent(oAgent);
		}

		@Override
		public void doInternalCheck()
		{
			
		}
		
	}

	
	

}
