package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class __WF_HEAD_LIST_Selector__unused extends E2_ListSelectorContainer
{
	
	public enum enumDisplayOptions { };
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
//	//2011-12-02: variable zu klassenvariablen hochgestuft
//	WF_CheckBoxOption oCB_Deleted = new WF_CheckBoxOption("Gelöschte Aufgaben zeigen (+)",EnumDisplayOptions.SHOW_DELETED);
//	WF_CheckBoxOption oCB_Finished = new WF_CheckBoxOption("Abgeschlossene Aufgaben zeigen (+)", EnumDisplayOptions.SHOW_FINISHED);
//	WF_CheckBoxOption oCB_OwnOnly = new WF_CheckBoxOption("Nur Aufgaben mit eigener Beteiligung (-)",EnumDisplayOptions.SHOW_OWN_ONLY);

	
	/**
	 * 
	 */
	private static final long 				serialVersionUID 			= 8367474497544331036L;
	private E2_SelectionComponentsVector 	oSelVector 					= null;
	
	private MyE2_CheckBox 					oCB_ActiveTasksOnlyList 	= null;
	private MyE2_CheckBox 					oCB_OwnOnlyList 			= null;
	private E2_NavigationList 				oNaviList 					= null;
	private String[][] 		 				arrZeitraum 				= null;
	
	
	public __WF_HEAD_LIST_Selector__unused(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oNaviList = oNavigationList;
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		oCB_OwnOnlyList = new MyE2_CheckBox(new MyE2_String("Nur Laufzettel mit eigener Beteiligung (-)"));
		oCB_ActiveTasksOnlyList = new MyE2_CheckBox(new MyE2_String("Nur wenn aktive Aufgaben vorhanden sind(-)"));
		
		MyE2_CheckBox oCB_DeletedList = new MyE2_CheckBox(new MyE2_String("Gelöschte Laufzettel zeigen (+)"));
		MyE2_CheckBox oCB_FinishedList = new MyE2_CheckBox(new MyE2_String("Abgeschlossene Laufzettel zeigen (+)"));
		oCB_OwnOnlyList.setSelected(true);

		oCB_OwnOnlyList.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				if (oCB_OwnOnlyList.isSelected()){
					oCB_ActiveTasksOnlyList.set_bEnabled_For_Edit(true);
				} else {
					oCB_ActiveTasksOnlyList.set_bEnabled_For_Edit(false);
					oCB_ActiveTasksOnlyList.setSelected(false);
				}
				
			}
		});
		
		
		
		ownSelektorBetrachtungszeitraum oSelBetrachtungsZeitraum = new ownSelektorBetrachtungszeitraum();
		oSelVector.add(oSelBetrachtungsZeitraum);	
		//2011-11-30:  ---ende
		
		
		//2012-01-16: selektor fuer prioritaeten
		ownListSelectorPrioritaet  oSelPrio = new ownListSelectorPrioritaet();
		

		
		MyE2_Row oRow = new MyE2_Row();
		
		MyE2_Column oColHead = new MyE2_Column();
		
		MyE2_Column oColTrenner = new MyE2_Column();
		oColTrenner.setInsets(new Insets(new Extent(10)));
		
		MyE2_Column oColTrenner2 = new MyE2_Column();
		oColTrenner2.setInsets(new Insets(new Extent(10)));
	
		MyE2_Column oColTrenner3 = new MyE2_Column();
		oColTrenner3.setInsets(new Insets(new Extent(10)));


		RowLayoutData oLayoutRow = new RowLayoutData();
		oLayoutRow.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		
		ColumnLayoutData oLayoutCol = new ColumnLayoutData();
		oLayoutCol.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		
		oRow.setLayoutData(oLayoutRow);
		oColHead.setLayoutData(oLayoutRow);
	
		oColHead.add(new MyE2_Label( new MyE2_String("Laufzettel")));
		oColHead.add(oCB_OwnOnlyList);
		oColHead.add(oCB_ActiveTasksOnlyList,E2_INSETS.I_20_0_0_0);
		oColHead.add(oCB_FinishedList);
		oColHead.add(oCB_DeletedList);		
		
		
		//2012-01-16 Prioritaet
		MyE2_Column oColPrio = new MyE2_Column();
		oColPrio.setLayoutData(oLayoutRow);
		oColPrio.add(new MyE2_Label( new MyE2_String("Priorität")));
		oColPrio.add(oSelPrio.get_oComponentForSelection());
		///////////
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		
		
		MyE2_Column oColZeitraum = new MyE2_Column();
		oColZeitraum.setLayoutData(oLayoutRow);
		oColZeitraum.add(new MyE2_Label( new MyE2_String("Betrachtungszeitraum")));
		//2011-11-30
		oColZeitraum.add(new E2_ComponentGroupHorizontal(0,  oSelBetrachtungsZeitraum.get_oComponentForSelection(), this.oSelVector.get_oButtonReload(),E2_INSETS.I_0_0_2_0));
		oColZeitraum.add(new Separator(),E2_INSETS.I_0_0_2_0);
		oColZeitraum.add(new MyE2_Label( new MyE2_String("Diverse:")),E2_INSETS.I_0_0_2_0);
		oColZeitraum.add(oDB_BasedSelektor.get_oComponentForSelection(100),E2_INSETS.I_0_0_2_0);
				
		
		oRow.add(oColHead);
		oRow.add(oColTrenner);
		oRow.add(oColTrenner2);
		oRow.add(oColPrio);
		oRow.add(oColTrenner3);
		oRow.add(oColZeitraum);
		
		this.add(oRow);

		
		
		oSelVector.add(new E2_ListSelectorStandard(oCB_DeletedList,"","  NVL(JT_LAUFZETTEL.GELOESCHT,'N')='N'"));
		oSelVector.add(new E2_ListSelectorStandard(oCB_FinishedList,""," JT_LAUFZETTEL.ABGESCHLOSSEN_AM IS NULL ") );
		
		//TODO: hier muss man noch die Auswahl machen...
		String cID_USER = bibALL.get_ID_USER();
		String sWhere = "(JT_LAUFZETTEL.ID_LAUFZETTEL in ( " + 
							"SELECT DISTINCT ID_LAUFZETTEL " +
							"from "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE " +
							" (" +
							" JT_LAUFZETTEL_EINTRAG.ID_USER_BESITZER = " + cID_USER + 
							" OR JT_LAUFZETTEL_EINTRAG.ID_USER_BEARBEITER = " + cID_USER +
							" )" +
//							"AND ID_USER_ABGESCHLOSSEN_VON IS NULL " +
							//" OR ID_USER_ABGESCHLOSSEN_VON = " + cID_USER +
							")" 
							+ " OR (JT_LAUFZETTEL.ID_USER_BESITZER = " + cID_USER + " OR JT_LAUFZETTEL.ID_USER_SUPERVISOR = " + cID_USER + ") )" ;
		
		oSelVector.add(new E2_ListSelectorStandard(oCB_OwnOnlyList,sWhere,"") );
	
		
		// dummy-Selektor, damit die Zusatz-Where-Funktion aufgerufen wird...
		oSelVector.add(new E2_ListSelectorStandard(oCB_ActiveTasksOnlyList,"1=1","") );
		
		//2012-01-16: selektor fuer aufgaben-prioritaeten
		oSelVector.add(oSelPrio);
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}



	
	private String getSqlWhereAufgaben() throws myException{
			String cZusatzWhere = "";
			
			if (this.oCB_ActiveTasksOnlyList.isSelected())
			{
				cZusatzWhere = cZusatzWhere +" AND NVL(JT_LAUFZETTEL_EINTRAG.GELOESCHT,'N')='N' ";
				cZusatzWhere = cZusatzWhere +" AND JT_LAUFZETTEL_EINTRAG.ABGESCHLOSSEN_AM IS NULL ";
				cZusatzWhere = cZusatzWhere +" AND  ( " +
						" JT_LAUFZETTEL_EINTRAG.ID_USER_BESITZER="+bibALL.get_ID_USER()+" OR "+
						" JT_LAUFZETTEL_EINTRAG.ID_USER_BEARBEITER="+bibALL.get_ID_USER()+" OR "+
						" JT_LAUFZETTEL_EINTRAG.ID_USER_ABGESCHLOSSEN_VON="+bibALL.get_ID_USER()+
						")";
			}
			
			return cZusatzWhere;
	}

	
//	private String getSqlWhereAufgaben__() throws myException{
//	
//		String sSqlWhere = "";
//		// recursive nach dem E2_BasicModuleContainer suchen
//		E2_RecursiveSearchParent_BasicModuleContainer oSearch = new E2_RecursiveSearchParent_BasicModuleContainer( this.oNaviList );
//		
//		E2_BasicModuleContainer oContainerList = oSearch.get_First_FoundContainer();
//		
//		E2_RecursiveSearch_Component oSearchComps = new E2_RecursiveSearch_Component(
//				oContainerList, bibALL.get_Vector(WF_HEAD_LIST_Selector_for_entries.class.getName()), null);
//		
//		Vector<Component> vResult = oSearchComps.get_vAllComponents();
//		
//		
//		for (Component cb : vResult)
//		{
//			WF_HEAD_LIST_Selector_for_entries oPanel = (WF_HEAD_LIST_Selector_for_entries) cb;
//			
//			sSqlWhere += oPanel.get_WhereBlock();
//			
//		}
//		return sSqlWhere;
//	}
//	
	
	
	
	//2011-11-31: eigener Selektor fuer den Beobachtungszeitraum
	private class ownSelektorBetrachtungszeitraum extends XX_ListSelektor
	{

		private MyE2_SelectField  oSelZeitraum = null;
		
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
					{new MyE2_String("unbegrenz").CTrans(),"999999"},
					};
			this.oSelZeitraum = new MyE2_SelectField(arrZeitraum, null, false);
			this.oSelZeitraum.set_ActiveInhalt_or_FirstInhalt("2 Wochen");
			this.oSelZeitraum.setToolTipText(new MyE2_String("Zeitraum zur Auswahl zukünftiger Aufgaben.").CTrans());
		}

		@Override
		public String get_WhereBlock() throws myException
		{
			String cWert = this.oSelZeitraum.get_ActualWert();
			
			
			//2011-12-02: selektion der zeitraeume auf die faelligkeitsdaten der aufgaben
			//            verfeinern, sodass die angezeigten laufzettel die faelligkeiten aller
			//            in der unterselektion angezeigten aufgaben beruecksichtigt
			String cZusatzWhere = "";
			cZusatzWhere = getSqlWhereAufgaben();

			String cWhereBlockRueck = " JT_LAUFZETTEL.ID_LAUFZETTEL in (" +
									  " SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL FROM  "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE " +
									  " NVL(JT_LAUFZETTEL_EINTRAG.FAELLIG_AM,SYSDATE) <= (SYSDATE +"+cWert+") "
									  +cZusatzWhere 
									  +")";
			
			//dafuer sorgen, dass leere laufzettel ohne aufgabe auf jeden fall angezeigt werden
			cWhereBlockRueck = "("+cWhereBlockRueck+" OR (SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG LE WHERE LE.ID_LAUFZETTEL=JT_LAUFZETTEL.ID_LAUFZETTEL)=0 )";
			
			
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
			
			
			//2011-12-02: selektion der zeitraeume auf die faelligkeitsdaten der aufgaben
			//            verfeinern, sodass die angezeigten laufzettel die faelligkeiten aller
			//            in der unterselektion angezeigten aufgaben beruecksichtigt
			String cZusatzWhere = "";
			cZusatzWhere = getSqlWhereAufgaben();
			
			
			String cWhereBlockRueck = " JT_LAUFZETTEL.ID_LAUFZETTEL in (" +
									  " SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL FROM  "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE " +
									  " NVL(JT_LAUFZETTEL_EINTRAG." + RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL_PRIO+",0) = "+cWert+" "
									  + cZusatzWhere 
									  +")";
			
			//dafuer sorgen, dass leere laufzettel ohne aufgabe auf jeden fall angezeigt werden
			cWhereBlockRueck = "("+cWhereBlockRueck+" OR (SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG LE WHERE LE.ID_LAUFZETTEL=JT_LAUFZETTEL.ID_LAUFZETTEL)=0 )";
			
			
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
