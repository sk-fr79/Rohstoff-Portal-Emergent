package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * klasse zur darstellung der kombination button und hoch/runter-pfeil bei den sortierueberschriften 
 * der liste
 */
public class E2_ButtonListSorter_old extends MyE2_Row
{
	private E2_ButtonListSorter_old.ownButton 		oButtonSort = null;
	private MyE2_Label 							labelSortUp = new MyE2_Label(E2_ResourceIcon.get_RI("sortup.png"));
	private MyE2_Label 							labelSortDown = new MyE2_Label(E2_ResourceIcon.get_RI("sortdown.png"));
	private E2_NavigationList 					oNavigationList = null;
	

	private String 								cSORT_STATEMENT_UP = null;
	private String 								cSORT_STATEMENT_DOWN = null;
	
	private boolean								bStatusDown = true;
	
	private MyString 							oLabelforButton = null;
	



	/**
	 * 
	 * @param clabelForButton
	 * @param csort_STATEMENT_UP
	 * @param csort_STATEMENT_DOWN
	 * @param list
	 * @param lineWrap
	 * @param vADDON_4_ListTitel  //2014-08-29
	 */
	public E2_ButtonListSorter_old(	MyString clabelForButton,
								String csort_STATEMENT_UP, 
								String csort_STATEMENT_DOWN, 
								E2_NavigationList list,
								boolean lineWrap,
								Vector<E2IF__BelongsToNavigationList> vADDON_4_ListTitel)
	{
		super();
		this.cSORT_STATEMENT_UP = csort_STATEMENT_UP;
		this.cSORT_STATEMENT_DOWN = csort_STATEMENT_DOWN;
		this.oButtonSort = new E2_ButtonListSorter_old.ownButton(clabelForButton,lineWrap);
		this.oNavigationList = list;

		this.oLabelforButton = clabelForButton;
		
		this.add(this.oButtonSort);
		this.setStyle(new Style_Row_Normal(0, new Insets(0,0,2,0)));
		
		//2014-08-29: weitere komponenten in den listentitel einhaengen
		if (vADDON_4_ListTitel != null && vADDON_4_ListTitel.size()>0) {
			for (E2IF__BelongsToNavigationList oCompADDON: vADDON_4_ListTitel) {
				if (oCompADDON instanceof Component) {
					this.add((Component)oCompADDON);
				}
			}
		}
		
		this.oButtonSort.add_oActionAgent(new ownActionAgent());
		
	}

	
	public void Reset()
	{
		this.bStatusDown = true;
		this.set_Unsorted();
	}

	// eigener klassenname fuer die recursionssuche
	public static class ownButton extends MyE2_Button
	{
		public ownButton(MyString cText) 
		{
			super(cText);
		}
		
		public ownButton(MyString cText, boolean lineWrap) 
		{
			super(cText);
			this.setLineWrap(lineWrap);
		}
	}
	
	
	

	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			/*
			 * falls die komponente einen messageagent enthalt diesen an den button weitergeben
			 */
			
			E2_NavigationList oNaviList = E2_ButtonListSorter_old.this.oNavigationList;
			
			/*
			 * falls das sortieren unterbunden wurde, dann hier eine meldung und raus
			 */
			if (!oNaviList.get_bSortIsAllowed())
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Im Moment ist das Sortieren verboten, vermutlich sind Sie im Edit-Modus o.ä."));
				return;
			}
			
			oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
			boolean bNewStatus = !E2_ButtonListSorter_old.this.bStatusDown;
			

			//2012-01-31: ersetzen durch statische methode
			E2_ListSortHelper.reset_all_sorters(oNaviList);
			
			
//			E2_ButtonListSorter.this.removeAll();
			E2_ButtonListSorter_old.this.set_Unsorted();
			E2_ButtonListSorter_old.this.bStatusDown = bNewStatus;
			if (bNewStatus)
			{
				oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(E2_ButtonListSorter_old.this.cSORT_STATEMENT_DOWN);
//				E2_ButtonListSorter.this.add(E2_ButtonListSorter.this.oButtonSort);
//				E2_ButtonListSorter.this.add(E2_ButtonListSorter.this.labelSortDown);
				E2_ButtonListSorter_old.this.set_SortedDown();
			}
			else
			{
				oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(E2_ButtonListSorter_old.this.cSORT_STATEMENT_UP);
//				E2_ButtonListSorter.this.add(E2_ButtonListSorter.this.oButtonSort);
//				E2_ButtonListSorter.this.add(E2_ButtonListSorter.this.labelSortUp);
				E2_ButtonListSorter_old.this.set_SortedUP();
			}
			
			
			/*
			 * 2013-10-10: wenn die navilist so eingestellt ist, dann den order-segment-vector abspeichern
			 */
			if (oNaviList.get_bSaveSortStatus()) {
				Vector<String> vSortSegments = oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_vOrderFields();
				new E2_Usersetting_ListSortStatus(oNaviList.get_AUTOMATIC_GENERATED_KENNUNG()).STORE_Vector(vSortSegments);
			}
			
			
			/*
			 * dann den id-vector neu aufbauen und auf seite 1 springen oder auf die Seite des letzten aktive elements
			 */
			try
			{

				if (oNaviList.get_bSortViaCompleteQuery())
				{
					E2_ButtonListSorter_old.this.baue_sortierung_via_komplettem_query();
				}
				else
				{
					E2_ButtonListSorter_old.this.baue_sortierung_via_tempTable();
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("MyE2_NavigationList:actionPerformed:Fehler beim Neuaufbau des Segments !"));
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(ex.ErrorMessage));
			}
	
		}
		
	}

	public E2_ButtonListSorter_old.ownButton get_oButtonSort()
	{
		return oButtonSort;
	}
	

	/**
	 * 2013-10-10: neue sortiereinsteller 
	 */
	public void set_Unsorted() {
		this.removeAll();
		this.add(this.oButtonSort);
	}
	
	public void set_SortedUP() {
		this.removeAll();
		this.add(this.oButtonSort);
		this.add(this.labelSortUp);
	}
	
	public void set_SortedDown() {
		this.removeAll();
		this.add(this.oButtonSort);
		this.add(this.labelSortDown);
	}
	//2013-10-10
	
	

	//2011-09-06: sortierung auch auf der basis des vorhandenen id-vectors moeglich machen
	private void baue_sortierung_via_komplettem_query() throws myException
	{
		E2_NavigationList oNaviList = this.oNavigationList;
		Vector<String> vIDs = oNaviList.build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components("",true);
		String cID_Active = oNaviList.get_cID_Unformated_Of_LastActive_Row();
		oNaviList.set_newContentVector(vIDs);
		oNaviList.gotoSiteWithID_orFirstSite(cID_Active);
		if (E2_ButtonListSorter_old.this.oLabelforButton != null)
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message(
					new MyE2_String("Sortierung wurde vorgenommen nach der Spalte: ",true,
									"<"+E2_ButtonListSorter_old.this.oLabelforButton.CTrans()+">",false)));
		}
	}


	//2011-09-06: sortierung auch auf der basis des vorhandenen id-vectors moeglich machen
	private void baue_sortierung_via_tempTable() throws myException
	{
		E2_NavigationList oNaviList = this.oNavigationList;
		
		Vector<String> vOldIds = new Vector<String>();
		vOldIds.addAll(oNaviList.get_vectorSegmentation());

		this.do_exec("DELETE FROM TT_SORT_TABLE",false);
		
		for (int i=0;i<vOldIds.size();i++)
		{
			String cSQL = "INSERT INTO TT_SORT_TABLE (ID) VALUES("+vOldIds.get(i)+")";
			this.do_exec(cSQL,false);
		}
		
		String cWhere = oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE()+"."+oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cFieldName();
		cWhere = cWhere + " IN (SELECT ID FROM TT_SORT_TABLE)";
		cWhere = "("+cWhere+")";
		
		Vector<String> vIDs = oNaviList.build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components(cWhere,true,false);
		String cID_Active = oNaviList.get_cID_Unformated_Of_LastActive_Row();
		oNaviList.set_newContentVector(vIDs);
		oNaviList.gotoSiteWithID_orFirstSite(cID_Active);
		
		String cAnzahlsortierteZeilen = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM TT_SORT_TABLE");
		
		this.do_exec("DELETE FROM TT_SORT_TABLE", true);      //loescht die temp-table-eintraege
		
		if (E2_ButtonListSorter_old.this.oLabelforButton != null)
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message(
					new MyE2_String("Sortierung wurde vorgenommen nach der Spalte: ",true,
									"<"+E2_ButtonListSorter_old.this.oLabelforButton.CTrans()+">  // "+cAnzahlsortierteZeilen+" Zeilen ...",false)));
		}
	}

	
	private void do_exec(String cSQL, boolean bCommit) throws myException
	{
		boolean bOK1 = bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL, bCommit);
		if (!bOK1) 
		{
			bibDB.Commit();  
			throw new myException(this, "Error executing statement in sort-operation ... "+cSQL);
		}

	}

	
	public String get_cSORT_STATEMENT_UP() {
		return this.cSORT_STATEMENT_UP;
	}


	public String get_cSORT_STATEMENT_DOWN() {
		return this.cSORT_STATEMENT_DOWN;
	}


	


}

