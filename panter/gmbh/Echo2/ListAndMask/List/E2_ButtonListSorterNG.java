package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.ENUM_ListSortStatus;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * klasse zur darstellung der kombination button und hoch/runter-pfeil bei den sortierueberschriften 
 * der liste
 */
public abstract class E2_ButtonListSorterNG extends E2_Grid implements IF_ListSorter
{
	private MyE2_Label 							labelSortStatus = new MyE2_Label(E2_ResourceIcon.get_RI("empty10small.png"));
	private ENUM_ListSortStatus					status = ENUM_ListSortStatus.NEUTRAL;

	private MyE2_String 						buttonText = S.ms("<sort>");
	private String 								sortTermUp = null;
	private String 								sortTermDown = null;
	
	private E2_Button 							sortButton = new E2_Button(); 
	
	public abstract E2_NavigationList           getNavigationList() throws myException;

	/**
	 */
	public E2_ButtonListSorterNG() {
		super();
		this._s(2);
		this._a(sortButton	._style(E2_Button.StyleTransparentTextButtonWithDDDBorder())
							._t(this.buttonText), new RB_gld()._ins(0,0,0,0))
			._a(this.labelSortStatus, new RB_gld()._left_mid()._ins(1,0,0,0));
		this.setStyle(new Style_Row_Normal(0, new Insets(0,0,2,0)));
		
		this.sortButton._aaa(new ownActionAgent());
	}

	
	@Override
	public void Reset() {
		this.status = ENUM_ListSortStatus.NEUTRAL;
		this.labelSortStatus.setIcon(this.status.getIcon());
	}


	
	private class ownActionAgent extends XX_ActionAgent	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
			
			E2_NavigationList oNaviList = getNavigationList();
			
			if (!oNaviList.get_bSortIsAllowed()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Im Moment ist das Sortieren verboten, vermutlich sind Sie im Edit-Modus o.ä."));
				return;
			}
			
			oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
			ENUM_ListSortStatus statusTemp = status.getStatusNext();			

			E2_ListSortHelper.reset_all_sorters(oNaviList);
			
			status = statusTemp;
			
			set_Unsorted();
			
			String sortTerm = null;
			switch (status) {
			case UP:
				sortTerm=sortTermUp;
				break;
				
			case DOWN:
				sortTerm=sortTermDown;
				break;
				
			case NEUTRAL:
				sortTerm=null;
				break;
			}

			oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(sortTerm);
			
			
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
			try	{
				if (oNaviList.get_bSortViaCompleteQuery())	{
					E2_ButtonListSorterNG.this.baue_sortierung_via_komplettem_query();
				} else {
					E2_ButtonListSorterNG.this.baue_sortierung_via_tempTable();
				}
			} catch (myException ex) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("MyE2_NavigationList:actionPerformed:Fehler beim Neuaufbau des Segments !"));
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(ex.ErrorMessage));
			}
	
			labelSortStatus.setIcon(status.getIcon());
			
		}
		
	}



	/**
	 * 2013-10-10: neue sortiereinsteller 
	 */
	@Override
	public void set_Unsorted() {
		this.labelSortStatus.setIcon(ENUM_ListSortStatus.NEUTRAL.getIcon());
	}
	
	@Override
	public void set_SortedUP() {
		this.labelSortStatus.setIcon(ENUM_ListSortStatus.UP.getIcon());
	}
	
	@Override
	public void set_SortedDown() {
		this.labelSortStatus.setIcon(ENUM_ListSortStatus.DOWN.getIcon());
	}
	//2013-10-10
	
	

	//2011-09-06: sortierung auch auf der basis des vorhandenen id-vectors moeglich machen
	private void baue_sortierung_via_komplettem_query() throws myException {
		
		Vector<String> vIDs = getNavigationList().build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components("",true);
		String cID_Active = getNavigationList().get_cID_Unformated_Of_LastActive_Row();
		getNavigationList().set_newContentVector(vIDs);
		getNavigationList().gotoSiteWithID_orFirstSite(cID_Active);
		if (buttonText != null) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(
					new MyE2_String("Sortierung wurde vorgenommen nach der Spalte: ",true,
									"<"+buttonText.CTrans()+">",false)));
		}
	}


	//2011-09-06: sortierung auch auf der basis des vorhandenen id-vectors moeglich machen
	private void baue_sortierung_via_tempTable() throws myException {
		
		Vector<String> vOldIds = new Vector<String>();
		vOldIds.addAll( getNavigationList().get_vectorSegmentation());

		this.do_exec("DELETE FROM TT_SORT_TABLE",false);
		
		for (int i=0;i<vOldIds.size();i++)
		{
			String cSQL = "INSERT INTO TT_SORT_TABLE (ID) VALUES("+vOldIds.get(i)+")";
			this.do_exec(cSQL,false);
		}
		
		String cWhere =  getNavigationList().get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE()+"."+ getNavigationList().get_oComponentMAP__REF().get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cFieldName();
		cWhere = cWhere + " IN (SELECT ID FROM TT_SORT_TABLE)";
		cWhere = "("+cWhere+")";
		
		Vector<String> vIDs =  getNavigationList().build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components(cWhere,true,false);
		String cID_Active =  getNavigationList().get_cID_Unformated_Of_LastActive_Row();
		getNavigationList().set_newContentVector(vIDs);
		getNavigationList().gotoSiteWithID_orFirstSite(cID_Active);
		
		this.do_exec("DELETE FROM TT_SORT_TABLE", true);      //loescht die temp-table-eintraege
		
		if (buttonText != null) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(
					new MyE2_String("Sortierung wurde vorgenommen nach der Spalte: ",true,
									"<"+buttonText.CTrans()+">",false)));
		}
	}

	
	private void do_exec(String cSQL, boolean bCommit) throws myException 	{
		boolean bOK1 = bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(cSQL, bCommit);
		if (!bOK1) {
			bibDB.Commit();  
			throw new myException(this, "Error executing statement in sort-operation ... "+cSQL);
		}
	}


	@Override
	public MyE2_Label getLabelSortStatus() {
		return labelSortStatus;
	}

	public MyE2_String getButtonText() {
		return buttonText;
	}

	public E2_ButtonListSorterNG _setButtonText(MyE2_String p_buttonText) {
		this.buttonText = p_buttonText;
		this.sortButton._t(p_buttonText);
		return this;
	}

	public String getSortTermUp() {
		return sortTermUp;
	}

	public E2_ButtonListSorterNG _setSortTermUp(String p_sortTermUp) {
		this.sortTermUp = p_sortTermUp;
		return this;
	}

	public String getSortTermDown() {
		return sortTermDown;
	}

	public E2_ButtonListSorterNG _setSortTermDown(String p_sortTermDown) {
		this.sortTermDown = p_sortTermDown;
		return this;
	}


	@Override
	public String get_cSORT_STATEMENT_UP() { return sortTermUp;}

	@Override
	public String get_cSORT_STATEMENT_DOWN() { return sortTermDown; }




}

