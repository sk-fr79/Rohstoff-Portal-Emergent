package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT;

import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import com.sun.xml.internal.ws.addressing.policy.AddressingFeatureConfigurator;

import echopointng.Separator;
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
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_SelectField_Zeitraum;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_LIST_Selector_Fragment_User;

public class WF_HEAD_LIST_SelectionComponentVector_Version2 extends E2_SelectionComponentsVector
{

	// Dropdown-List für die User
	protected WF_HEAD_LIST_Selector_Fragment_User 	selUser = new WF_HEAD_LIST_Selector_Fragment_User();
	protected MyE2_CheckBox 			cbShowFinished 	= new MyE2_CheckBox("Abgeschlossene Laufzettel anzeigen",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
	protected MyE2_CheckBox 			cbShowDeleted 	= new MyE2_CheckBox("Gelöschte Laufzettel anzeigen",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
	
	protected E2_NavigationList 				oNaviList 	= null;

	
	
	
	public WF_HEAD_LIST_SelectionComponentVector_Version2( 	E2_NavigationList oNavigationList ,
															Component  oButtonSwitchToNew, 
															String cMODULE_KENNER) throws myException {
		super(oNavigationList);
		this.oNaviList = oNavigationList;
		

		selUser.get_SelectField().set_ActiveValue_OR_FirstValue(bibALL.get_ID_USER());
		this.add(selUser);
		
		
		this.add(new E2_ListSelectorStandard(cbShowFinished,"","  JT_LAUFZETTEL.ABGESCHLOSSEN_AM IS NULL "));
		this.add(new E2_ListSelectorStandard(cbShowDeleted,"","  NVL(JT_LAUFZETTEL.GELOESCHT,'N')='N' "));
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.add(oDB_BasedSelektor);

		CSelector4LaufzettelAufgaben oSelLaufzettelAufgaben = new CSelector4LaufzettelAufgaben();
		this.add(oSelLaufzettelAufgaben);
		
		
		//
		//  GUI
		//
		MyE2_Grid oGridMain = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		
		oGridMain.add(new MyE2_Label( new MyE2_String("Laufzettel mit Beteiligung von:"),MyE2_Label.STYLE_NORMAL_PLAIN()),1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		
		// Zeile 2
		
		MyE2_Grid oGridPrio = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridPrio.add(new MyE2_Label( new MyE2_String("Betrachtungszeitraum")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridPrio.add(oSelLaufzettelAufgaben.get_oSelZeitraum() ,1,1,E2_INSETS.I_5_0_0_0,Alignment.ALIGN_TOP);
		oGridPrio.add(new MyE2_Label( new MyE2_String("Priorität")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridPrio.add(oSelLaufzettelAufgaben.get_oSelectPrio() ,1,1,E2_INSETS.I_5_0_0_0,Alignment.ALIGN_TOP);
		oGridPrio.add( new MyE2_Label( new MyE2_String("Diverse")),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridPrio.add(oDB_BasedSelektor.get_oComponentForSelection(100),1,1,E2_INSETS.I_5_0_0_0,Alignment.ALIGN_TOP);
		
		oGridMain.add( selUser.get_oComponentForSelection(),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add( new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(oSelLaufzettelAufgaben.get_oComponentForSelection(),1,2,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(oGridPrio,1,2,E2_INSETS.I_40_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		
		
		// Zeile 3
		MyE2_Label lblHeadingLaufzettel = new MyE2_Label(new MyE2_String("Laufzettel"),MyE2_Label.STYLE_NORMAL_PLAIN());
		oGridMain.add(
				new E2_ComponentGroupVertical(E2_INSETS.I_0_0_0_0,0,lblHeadingLaufzettel,
																	cbShowFinished,
																	cbShowDeleted,
																	oSelLaufzettelAufgaben.get_cbShowOhneAufgaben()	) 
				,1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		// leere Spalte durch rowspan
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String(""))	,1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		oGridMain.add(new MyE2_Label( new MyE2_String("")),1,1,E2_INSETS.I_20_0_0_0,Alignment.ALIGN_TOP);
		
		oGridMain.setColumnWidth(0, new Extent(320));
		
		this.set_oSelComponent(oGridMain);
		
	}
	
	
	
	private class OptionSelectorAction extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_NavigationList oList = WF_HEAD_LIST_SelectionComponentVector_Version2.this.oNaviList;
			
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
	
	private class CSelector4LaufzettelAufgaben extends XX_ListSelektor {
		
		// Checkboxen für die Aufgben-Selektion
		private MyE2_CheckBox cbShowOwnBearbeiter 	 	= new MyE2_CheckBox("Bearbeiter", MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		private MyE2_CheckBox cbShowOwnBearbeiterNOT 	= new MyE2_CheckBox("nicht Bearbeiter", MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		private MyE2_CheckBox cbShowOwnBesitzer 		= new MyE2_CheckBox("Besitzer", MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		private MyE2_CheckBox cbShowOwnBesitzerNOT 		= new MyE2_CheckBox("nicht Besitzer", MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		
		private MyE2_CheckBox cbShowOffene				= new MyE2_CheckBox("offen",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		private MyE2_CheckBox cbShowAbgeschlossene		= new MyE2_CheckBox("abgeschlossen",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		
		private MyE2_CheckBox cbShowUngeloeschte		= new MyE2_CheckBox("ungelöscht",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		private MyE2_CheckBox cbShowGeloeschte			= new MyE2_CheckBox("gelöscht",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		
		private MyE2_CheckBox cbShowOhneAufgaben		= new MyE2_CheckBox("Laufzettel ohne Aufgaben anzeigen",MyE2_CheckBox.STYLE_NORMAL_NO_BORDER_NO_INSETS());
		
		
		

		// Zeitraum der Aufgaben
		private WF_HEAD_LIST_SelectField_Zeitraum  oSelZeitraum = null;
		


		// Prio der Aufgaben
		private MyE2_SelectField  oSelectPrio = null;

		public CSelector4LaufzettelAufgaben() throws myException {
		
			
			// Prio
			MyDropDownSettings oDD = new MyDropDownSettings(bibE2.cTO(),"JT_LAUFZETTEL_PRIO","PRIO","ID_LAUFZETTEL_PRIO",null,"ISDEFAULT",true, "PRIO_SORT",false);
			this.oSelectPrio = new MyE2_SelectField(oDD.getDD(), "", false);
			this.oSelectPrio.setToolTipText(new MyE2_String("Selektiert alle Laufzettel mit einem Eintrag dieser Priorität").CTrans());

			// Zeitraum
			String[][] arrZeitraum = new String[][]{
								{new MyE2_String("-").CTrans(),""},
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
								};
								
			this.oSelZeitraum = new WF_HEAD_LIST_SelectField_Zeitraum(arrZeitraum, null, false);
			this.oSelZeitraum.set_ActiveInhalt_or_FirstInhalt( new MyE2_String("2 Wochen").CTrans() );
			
		
			// default-settings
			cbShowOwnBearbeiter.setSelected(true);
			cbShowOwnBesitzer.setSelected(true);
			cbShowOffene.setSelected(true);
			cbShowUngeloeschte.setSelected(true);
			cbShowOhneAufgaben.setSelected(true);
			
			
			
		}
		
		@Override
		public String get_WhereBlock() throws myException {
			// ausgewählten User ermitteln
			WF_HEAD_LIST_SelectionComponentVector_Version2 oThis = WF_HEAD_LIST_SelectionComponentVector_Version2.this;
			String idUser = oThis.selUser.get_SelectField().get_ActualWert();

			String sWhereBearbeiter = "";
			String sWhereAbgeschlossen = "";
			String sWhereGeloescht = "";
			
			String sWhereZeitraum = "";
			String sWherePrio = "";

			String sWhereComplete = "";
			
			//
			// abgeschlossene und offene
			//
			String s1 = "";
			String s2 = "";
			String s3 = "";
			String s4 = "";
			
			s1 = " 1=2 ";
			s2 = " 1=2 ";
			if (cbShowAbgeschlossene.isSelected() ){
				// keine abgeschlossenen Einträge:
				s1 = " E.ABGESCHLOSSEN_AM IS NOT NULL ";
			} 
			
			if (cbShowOffene.isSelected()){
				// keine offenen Einträge
				s2 = " E. ABGESCHLOSSEN_AM IS NULL ";
			}
			sWhereAbgeschlossen = "(" + s1 + " OR " + s2 + ")";
			
			
			


			//
			// Bearbeiter und Besitzer
			//
			s1 = " 1=2 ";
			s2 = " 1=2 ";
			s3 = " 1=2 ";
			s4 = " 1=2 ";
			if (!bibALL.isEmpty(idUser)){
				if ( cbShowOwnBearbeiter.isSelected() ){
					s1 = " E.ID_USER_BEARBEITER = "+ idUser + " ";
				}
				if ( cbShowOwnBesitzer.isSelected() ){
					s2 = " E.ID_USER_BESITZER = "+ idUser + " ";
				}
				if (cbShowOwnBearbeiterNOT.isSelected()){
					s3 = " E.ID_USER_BEARBEITER != "+ idUser + " ";
				}
				if (cbShowOwnBesitzerNOT.isSelected()){
					s4 = " E.ID_USER_BESITZER != "+ idUser + " ";
				}
				sWhereBearbeiter = "(" + s1 + " OR " + s2 + " OR " + s3 + " OR " + s4 + " ) " ;
			} else {
				sWhereBearbeiter = " 1 = 1 ";
			}

			//
			// gelöschte und ungelöschten
			//
			s1 = " 1=2 ";
			s2 = " 1=2 ";
			if (cbShowUngeloeschte.isSelected() ){
				s1 = " NVL(E.GELOESCHT,'N') = 'N' ";
			} 
			
			if (cbShowGeloeschte.isSelected()){
				s2 = " NVL(E.GELOESCHT,'N') != 'N'  ";
			}
			sWhereGeloescht = "(" + s1 + " OR " + s2 + ")";

			
			//
			// Zeitraum
			//
			String cZeitraum = this.oSelZeitraum.get_ActualWert();
			if (S.isEmpty(cZeitraum)){
				// default AND 
				sWhereZeitraum = " 1=1 ";
			} else {
				sWhereZeitraum = " 	NVL( E." + RECORD_LAUFZETTEL_EINTRAG.FIELD__FAELLIG_AM +" ,SYSDATE + " + cZeitraum + ") <= (SYSDATE + " + cZeitraum + ")" ; 
			}

			
			// 
			// PRIO
			//
			String sPrio = this.oSelectPrio.get_ActualWert();
			
			if (S.isEmpty(sPrio))
			{
				sWherePrio = " 1=1 ";
			} else {
				sWherePrio = " E." + RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL_PRIO + " = " + sPrio ;
						
			}
			
			
			//
			// LZ ohne Aufgaben
			// 
			String sWhereOhneAufgaben = "";
			if (cbShowOhneAufgaben.isSelected()){
				sWhereOhneAufgaben =  " OR (SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG LE WHERE LE.ID_LAUFZETTEL=JT_LAUFZETTEL.ID_LAUFZETTEL)=0 ";
			}
			
			
			sWhereComplete = 
			" JT_LAUFZETTEL.ID_LAUFZETTEL IN ( "+
			" 		SELECT L.ID_LAUFZETTEL "+
			" 		FROM   JT_LAUFZETTEL L LEFT OUTER JOIN JT_LAUFZETTEL_EINTRAG E ON L.ID_LAUFZETTEL = E.ID_LAUFZETTEL "+
			" 		WHERE  (  "+ sWhereAbgeschlossen +  " AND " + sWhereBearbeiter +  " AND " + sWhereGeloescht +  " AND " + sWhereZeitraum  +  " AND " + sWherePrio +  " ) " +
					sWhereOhneAufgaben +
			") ";
			
			return sWhereComplete;

		}

		@Override
		public Component get_oComponentForSelection() throws myException {
			//
			//  GUI
			//
			MyE2_Grid oGridAufgaben = new MyE2_Grid(6, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			oGridAufgaben.add(new MyE2_Label(new MyE2_String("Selektiere Laufzettel die Aufgaben enthalten und bei denen folgende Bedingungen gelten: "),MyE2_Label.STYLE_NORMAL_PLAIN()),6,E2_INSETS.I_0_0_10_0);

			oGridAufgaben.add(new MyE2_Label(new MyString("")),6,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);
			
			oGridAufgaben.add(new MyE2_Label(new MyString("Der Mitarbeiter ist")),1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			oGridAufgaben.add(this.cbShowOwnBearbeiter,1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			oGridAufgaben.add(this.cbShowOwnBesitzer,1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			oGridAufgaben.add(this.cbShowOwnBearbeiterNOT,1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			oGridAufgaben.add(this.cbShowOwnBesitzerNOT,2,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);

			oGridAufgaben.add(new MyE2_Label(new MyString("")),6,1,E2_INSETS.I_0_10_0_0,Alignment.ALIGN_TOP);
			
			oGridAufgaben.add(new MyE2_Label(new MyString("Die Aufgaben sind")),1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			oGridAufgaben.add(this.cbShowOffene,1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			oGridAufgaben.add(this.cbShowAbgeschlossene,4,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			
			
			oGridAufgaben.add(new MyE2_Label(new MyString("Die Aufgaben sind")),1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			oGridAufgaben.add(this.cbShowUngeloeschte,1,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			oGridAufgaben.add(this.cbShowGeloeschte,4,1,E2_INSETS.I_0_0_10_0,Alignment.ALIGN_TOP);
			
			oGridAufgaben.add(new MyE2_Label(new MyString("")),6,1,E2_INSETS.I_0_10_0_0,Alignment.ALIGN_TOP);

			return oGridAufgaben;
			
		}

		@Override
		public Component get_oComponentWithoutText() throws myException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
			this.cbShowOwnBearbeiter.add_oActionAgent(oAgent);
			this.cbShowOwnBearbeiterNOT.add_oActionAgent(oAgent);
			this.cbShowOwnBesitzer.add_oActionAgent(oAgent);
			this.cbShowOwnBesitzerNOT.add_oActionAgent(oAgent);
			this.cbShowAbgeschlossene.add_oActionAgent(oAgent);
			this.cbShowOffene.add_oActionAgent(oAgent);
			this.cbShowGeloeschte.add_oActionAgent(oAgent);
			this.cbShowUngeloeschte.add_oActionAgent(oAgent);
			this.oSelectPrio.add_oActionAgent(oAgent);
			this.oSelZeitraum.add_oActionAgent(oAgent);
			this.cbShowOhneAufgaben.add_oActionAgent(oAgent);
		}

		@Override
		public void doInternalCheck() {
			// TODO Auto-generated method stub
			
		}

		
		public WF_HEAD_LIST_SelectField_Zeitraum get_oSelZeitraum() {
			return oSelZeitraum;
		}
		
		public MyE2_SelectField get_oSelectPrio() {
			return oSelectPrio;
		}

		
		/**
		 * gibt die Checkbox zurück, damit sie extern plaziert werden kann
		 * @return
		 */
		public MyE2_CheckBox get_cbShowOhneAufgaben() {
			return cbShowOhneAufgaben;
		}

		

		
	}
	

}
