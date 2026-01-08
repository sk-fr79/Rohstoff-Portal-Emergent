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
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

@Deprecated
public class WF_HEAD_LIST_Selector extends E2_ListSelectorContainer
{
	
	public enum enumDisplayOptions { };
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	//2011-12-02: variable zu klassenvariablen hochgestuft
	WF_CheckBoxOption oCB_OwnOnly 	= new WF_CheckBoxOption("Nur Laufzettel mit eigenen, offenen Aufgaben (-)",EnumDisplayOptions.SHOW_OWN_ONLY);
	WF_CheckBoxOption oCB_Finished 	= new WF_CheckBoxOption("Laufzettel mit abgeschlossenen Aufgaben (+)", EnumDisplayOptions.SHOW_FINISHED);
	WF_CheckBoxOption oCB_Deleted 	= new WF_CheckBoxOption("Laufzettel mit gelöschten Aufgaben (+)",EnumDisplayOptions.SHOW_DELETED);

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8367474497544331036L;
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	private E2_NavigationList oNaviList = null;
	//private MyE2_SelectField 			   	oSelZeitraum = null;
	private String[][] 		 				arrZeitraum = null;
	
	public WF_HEAD_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oNaviList = oNavigationList;
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		MyE2_CheckBox oCB_DeletedList = new MyE2_CheckBox(new MyE2_String("Gelöschte Laufzettel zeigen (+)"));
		MyE2_CheckBox oCB_FinishedList = new MyE2_CheckBox(new MyE2_String("Abgeschlossene Laufzettel zeigen (+)"));
		MyE2_CheckBox oCB_OwnOnlyList = new MyE2_CheckBox(new MyE2_String("Nur Laufzettel mit eigener Beteiligung (-)"));
		oCB_OwnOnlyList.setSelected(true);
		
		
		
		//testCheckBox oCB_Deleted = new testCheckBox(new MyE2_String("Zeige gelöschte Einträge"));
		oCB_Deleted.add_oActionAgent(new OptionSelectorAction());
		oCB_Deleted.setToolTipText(new MyString("Es werden zusätzlich gelöschte Aufgaben berücksichtigt.").CTrans());
		
		oCB_Finished.add_oActionAgent(new OptionSelectorAction());
		oCB_Finished.setToolTipText(new MyString("Es werden zusätzlich abgeschlossene Aufgaben berücksichtigt.").CTrans());
		
		oCB_OwnOnly.add_oActionAgent(new OptionSelectorAction());
		oCB_OwnOnly.setToolTipText(new MyString("Es werden nur Laufzettel mit offenen Aufgaben berücksichtig.").CTrans());
		
		oCB_OwnOnly.setSelected(true);
		
		// dummy-Selektoren, damit der Selektor ausgeführt wird
		oSelVector.add(new E2_ListSelectorStandard(oCB_Deleted,"1=1","") );
		oSelVector.add(new E2_ListSelectorStandard(oCB_Finished,"1=1","") );
		oSelVector.add(new E2_ListSelectorStandard(oCB_OwnOnly,"1=1","") );
		
		
		//2011-11-30: aenderung: Es sollen nur Laufzettel mit offenen Aufgaben innerhalb des zeitraums gefunden werden
//		oSelVector.add(new E2_ListSelectorStandard(this.oSelZeitraum, " JT_LAUFZETTEL.ID_LAUFZETTEL in (" +
//																	  " SELECT ID_LAUFZETTEL FROM  "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE " +
//																	  " NVL(FAELLIG_AM,SYSDATE) <= SYSDATE + #WERT# )", null, null));
		
		ownSelektorBetrachtungszeitraum oSelBetrachtungsZeitraum = new ownSelektorBetrachtungszeitraum();
		oSelVector.add(oSelBetrachtungsZeitraum);	

		//2011-11-30:  ---ende
		
		
		//2012-01-16: selektor fuer prioritaeten
		ownListSelectorPrioritaet  oSelPrio = new ownListSelectorPrioritaet();
		
		
		
		// Zeitraum in die Zukunft
//		arrZeitraum = new String[][]{
//				{new MyE2_String("1 Tag").CTrans(),"1"},
//				{new MyE2_String("2 Tage").CTrans(),"2"},
//				{new MyE2_String("3 Tage").CTrans(),"3"},
//				{new MyE2_String("4 Tage").CTrans(),"4"},
//				{new MyE2_String("5 Tage").CTrans(),"5"},
//				{new MyE2_String("6 Tage").CTrans(),"6"},
//				{new MyE2_String("7 Tage").CTrans(),"7"},
//				{new MyE2_String("2 Wochen").CTrans(),"14"},
//				{new MyE2_String("3 Wochen").CTrans(),"21"},
//				{new MyE2_String("4 Wochen").CTrans(),"28"},
//				{new MyE2_String("5 Wochen").CTrans(),"35"},
//				{new MyE2_String("10 Wochen").CTrans(),"70"},
//				{new MyE2_String("20 Wochen").CTrans(),"140"},
//				{new MyE2_String("30 Wochen").CTrans(),"210"},
//				{new MyE2_String("1 Jahr").CTrans(),"365"},
//				{new MyE2_String("unbegrenz").CTrans(),"999999"},
//				};
//		this.oSelZeitraum = new MyE2_SelectField(arrZeitraum, null, false);
//		this.oSelZeitraum.set_ActiveInhalt_or_FirstInhalt("2 Wochen");
		
		MyE2_Row oRow = new MyE2_Row();
		
		MyE2_Column oColHead = new MyE2_Column();
		
		MyE2_Column oColTrenner = new MyE2_Column();
		oColTrenner.setInsets(new Insets(new Extent(10)));
		
		MyE2_Column oColTrenner2 = new MyE2_Column();
		oColTrenner2.setInsets(new Insets(new Extent(10)));
	
		MyE2_Column oColTrenner3 = new MyE2_Column();
		oColTrenner3.setInsets(new Insets(new Extent(10)));

		
		MyE2_Column oColEntries = new MyE2_Column();
		
		RowLayoutData oLayoutRow = new RowLayoutData();
		oLayoutRow.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		
		ColumnLayoutData oLayoutCol = new ColumnLayoutData();
		oLayoutCol.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		
		oRow.setLayoutData(oLayoutRow);
		oColHead.setLayoutData(oLayoutRow);
		oColEntries.setLayoutData(oLayoutRow);
	
		oColHead.add(new MyE2_Label( new MyE2_String("Laufzettel")));
		oColHead.add(oCB_OwnOnlyList);
		oColHead.add(oCB_FinishedList);
		oColHead.add(oCB_DeletedList);		
		
		oColEntries.add(new MyE2_Label( new MyE2_String("Aufgaben")));
		oColEntries.add(oCB_OwnOnly);
		oColEntries.add(oCB_Finished);		
		oColEntries.add(oCB_Deleted);

		
		//2012-01-16 Prioritaet
		MyE2_Column oColPrio = new MyE2_Column();
		oColPrio.setLayoutData(oLayoutRow);
		oColPrio.add(new MyE2_Label( new MyE2_String("Priorität")));
		oColPrio.add(oSelPrio.get_oComponentForSelection());
		///////////
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		//oGrid.add(oDB_BasedSelektor.get_oComponentForSelection(100),1,1,E2_INSETS.I_0_0_0_0,Alignment.ALIGN_TOP);
		
		
		MyE2_Column oColZeitraum = new MyE2_Column();
		oColZeitraum.setLayoutData(oLayoutRow);
		oColZeitraum.add(new MyE2_Label( new MyE2_String("Betrachtungszeitraum")));
		//2011-11-30
		//oColZeitraum.add(oSelZeitraum);
		oColZeitraum.add(new E2_ComponentGroupHorizontal(0,  oSelBetrachtungsZeitraum.get_oComponentForSelection(), this.oSelVector.get_oButtonReload(),E2_INSETS.I_0_0_2_0));
		oColZeitraum.add(new Separator(),E2_INSETS.I_0_0_2_0);
		oColZeitraum.add(new MyE2_Label( new MyE2_String("Diverse:")),E2_INSETS.I_0_0_2_0);
		oColZeitraum.add(oDB_BasedSelektor.get_oComponentForSelection(100),E2_INSETS.I_0_0_2_0);
		
		
		
		oRow.add(oColHead);
		oRow.add(oColTrenner);
		oRow.add(oColEntries);
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
		
		
		//2012-01-16: selektor fuer aufgaben-prioritaeten
		oSelVector.add(oSelPrio);
		
		
		
	
		
		
		/*
		 * Beispiele
		 *
		String cID_USER = bibALL.get_ID_USER(bibE2.get_CurrSession());
		MyE2_CheckBox oCBNurEigene = new MyE2_CheckBox("Nur mit eigener Beteiligung");
		oSelVector.add_(new E2_ListSelectorStandard(oCBNurEigene,"(JT_LAUFZETTEL.ID_USER="+cID_USER+" OR JT_LAUFZETTEL.ID_WF_HEAD IN (SELECT ID_WF_HEAD FROM "+bibE2.cTO()+".JT_LAUFZETTEL_TEILNEHMER WHERE ID_USER="+cID_USER+"))",""));

		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_LAUFZETTEL_WICHTIGKEIT","BESCHREIBUNG","ID_WF_HEAD_WICHTIGKEIT","ISTSTANDARD",true);

		MyE2_SelectField	oSelectWichtigkeit = new MyE2_SelectField(oDDWichtigkeit.getDD(),null,false);
		oSelVector.add_(new E2_ListSelectorStandard(oSelectWichtigkeit,"JT_LAUFZETTEL.ID_WF_HEAD_WICHTIGKEIT=#WERT#"));

		
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(new MyE2_Label("Wichtigkeit:"), new Insets(4,2,20,2));
		oGridInnen.add(oSelectWichtigkeit, new Insets(4,2,15,2));
		oGridInnen.add(oCBNurEigene, new Insets(4,2,15,2));
		*/
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	

	/**
	 * Action Agent-Klasse für die Behandlung der Auswahloptionen
	 * @author manfred
	 *
	 */
	
	private class OptionSelectorAction extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_NavigationList oList = WF_HEAD_LIST_Selector.this.oNaviList;
			
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
	
	
	
	
//	//2011-11-31: eigener Selektor fuer den Beobachtungszeitraum
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
			
			
			//2011-12-02: selektion der zeitraeume auf die faelligkeitsdaten der aufgaben
			//            verfeinern, sodass die angezeigten laufzettel die faelligkeiten aller
			//            in der unterselektion angezeigten aufgaben beruecksichtigt
			String cZusatzWhere = "";
			
			if (!WF_HEAD_LIST_Selector.this.oCB_Deleted.isSelected())
			{
				cZusatzWhere = cZusatzWhere +" AND NVL(JT_LAUFZETTEL_EINTRAG.GELOESCHT,'N')='N' ";
			}
			if (!WF_HEAD_LIST_Selector.this.oCB_Finished.isSelected())
			{
				cZusatzWhere = cZusatzWhere +" AND JT_LAUFZETTEL_EINTRAG.ABGESCHLOSSEN_AM IS NULL ";
			}
			if (WF_HEAD_LIST_Selector.this.oCB_OwnOnly.isSelected())
			{
				cZusatzWhere = cZusatzWhere +" AND  ( " +
						" JT_LAUFZETTEL_EINTRAG.ID_USER_BESITZER="+bibALL.get_ID_USER()+" OR "+
						" JT_LAUFZETTEL_EINTRAG.ID_USER_BEARBEITER="+bibALL.get_ID_USER()+" OR "+
						" JT_LAUFZETTEL_EINTRAG.ID_USER_ABGESCHLOSSEN_VON="+bibALL.get_ID_USER()+
						")"
				;
			}
			
			String cWhereBlockRueck = " JT_LAUFZETTEL.ID_LAUFZETTEL in (" +
									  " SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL FROM  "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE " +
									  " NVL(JT_LAUFZETTEL_EINTRAG.FAELLIG_AM,SYSDATE) <= (SYSDATE +"+cWert+") "+cZusatzWhere 
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
			
			if (!WF_HEAD_LIST_Selector.this.oCB_Deleted.isSelected())
			{
				cZusatzWhere = cZusatzWhere +" AND NVL(JT_LAUFZETTEL_EINTRAG.GELOESCHT,'N')='N' ";
			}
			if (!WF_HEAD_LIST_Selector.this.oCB_Finished.isSelected())
			{
				cZusatzWhere = cZusatzWhere +" AND JT_LAUFZETTEL_EINTRAG.ABGESCHLOSSEN_AM IS NULL ";
			}
			if (WF_HEAD_LIST_Selector.this.oCB_OwnOnly.isSelected())
			{
				cZusatzWhere = cZusatzWhere +" AND  ( " +
						" JT_LAUFZETTEL_EINTRAG.ID_USER_BESITZER="+bibALL.get_ID_USER()+" OR "+
						" JT_LAUFZETTEL_EINTRAG.ID_USER_BEARBEITER="+bibALL.get_ID_USER()+" OR "+
						" JT_LAUFZETTEL_EINTRAG.ID_USER_ABGESCHLOSSEN_VON="+bibALL.get_ID_USER()+
						")"
				;
			}
			
			String cWhereBlockRueck = " JT_LAUFZETTEL.ID_LAUFZETTEL in (" +
									  " SELECT JT_LAUFZETTEL_EINTRAG.ID_LAUFZETTEL FROM  "+bibE2.cTO()+".JT_LAUFZETTEL_EINTRAG WHERE " +
									  " NVL(JT_LAUFZETTEL_EINTRAG." + RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL_PRIO+",0) = "+cWert+" "+cZusatzWhere 
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
