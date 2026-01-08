package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class WF_LIST_ComponentMap extends E2_ComponentMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 825223656172906994L;

	public WF_LIST_ComponentMap(String ID_LAUFZETTEL, String ID_USER_BEARBEITER, E2_NavigationList oNaviList,WF_MASK_BasicModuleContainer oWF_MASK_Container) throws myException
	{
		super(new WF_LIST_SqlFieldMAP(ID_LAUFZETTEL,ID_USER_BEARBEITER));
		
		boolean bStandAlone = (bibALL.isEmpty(ID_LAUFZETTEL) && bibALL.isEmpty(ID_USER_BEARBEITER));

		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		E2_DB_BUTTON_OPEN_MASK_FromID oButtonOpenMask = 
			new E2_DB_BUTTON_OPEN_MASK_FromID(
							oFM.get_("ID_LAUFZETTEL_EINTRAG_B"),
							oWF_MASK_Container,
							new MyE2_String("Maskenansicht Laufzettel"),
							E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,
							"BEARBEITE_WF_EDIT",
							"BEARBEITE_WF_VIEW");
			
		oButtonOpenMask.setIcon(E2_ResourceIcon.get_RI("edit.png"));
		
		
		MyE2_ButtonInLIST oButtonDel = new MyE2_ButtonInLIST(E2_ResourceIcon.get_RI("delete.png"));
		oButtonDel.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_Button oWnButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oWnButton.EXT().get_C_MERKMAL()));
			}
			
		});

		MyE2_DB_MultiComponentColumn oColButtons = new MyE2_DB_MultiComponentColumn();
		oColButtons.add_Component(oButtonOpenMask, new MyE2_String("-"), null);
		oColButtons.add_Component(oButtonDel, new MyE2_String("-"), WF_CONST.HASH_DEL_BUTTON_SUBLIST);
		
		
		this.add_Component(WF_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		if (bStandAlone)
		{
			this.add_Component(WF_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
		}
		else
		{
			this.add_Component(WF_CONST.HASH_MULTI_COL_BUTTONS,	oColButtons,new MyE2_String("?"));
		}
		
		
		// Multicolumn-Objekte
		MyE2_DB_MultiComponentColumn oColBearbeiter = new MyE2_DB_MultiComponentColumn();
		oColBearbeiter.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER_BEARBEITER")), new MyE2_String("Bearbeiter"), null);
		oColBearbeiter.add_Component(new MyE2_DB_Label(oFM.get_("FAELLIG_AM")), new MyE2_String("Fällig am"), null);
		this.add_Component(WF_CONST.HASH_MEHRZEILER_BEARBEITER, oColBearbeiter, new MyE2_String("Bearbeiter / Fällig am"));

		MyE2_DB_MultiComponentColumn oColPrio = new MyE2_DB_MultiComponentColumn();
		oColPrio.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAUFZETTEL_PRIO")), new MyE2_String("Priorität"), null);
		oColPrio.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAUFZETTEL_STATUS")), new MyE2_String("Status"), null);
		this.add_Component(WF_CONST.HASH_MEHRZEILER_PRIO, oColPrio, new MyE2_String("Priorität / Status"));

		//Einzelfelder...
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("AUFGABE"),300,3), new MyE2_String("Aufgabe"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BERICHT"),300,3), new MyE2_String("Aufgabe"));

		MyE2_DB_MultiComponentColumn oColBesitzer = new MyE2_DB_MultiComponentColumn();
		oColBesitzer.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER_BESITZER")), new MyE2_String("Besitzer"), null);
		oColBesitzer.add_Component(new MyE2_DB_Label(oFM.get_("ANGELEGT_AM")), new MyE2_String("Angelegt am"), null);
		this.add_Component(WF_CONST.HASH_MEHRZEILER_BESITZER, oColBesitzer, new MyE2_String("Besitzer / Angelegt am"));

		MyE2_DB_MultiComponentColumn oColAbschluss = new MyE2_DB_MultiComponentColumn();
		oColAbschluss.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER_ABGESCHLOSSEN_VON")), new MyE2_String("Abgeschlossen von"), null);
		oColAbschluss.add_Component(new MyE2_DB_Label(oFM.get_("ABGESCHLOSSEN_AM")), new MyE2_String("am"), null);
		this.add_Component(WF_CONST.HASH_MEHRZEILER_ABSCHLUSS, oColAbschluss, new MyE2_String("Abgeschlossen von  / am"));

		MyE2_DB_MultiComponentColumn oColIDs = new MyE2_DB_MultiComponentColumn();
		oColIDs.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAUFZETTEL")), new MyE2_String("Laufzettel ID"), null);
		oColIDs.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAUFZETTEL_EINTRAG")), new MyE2_String("Aufgabe ID"), null);
		this.add_Component(WF_CONST.HASH_MEHRZEILER_IDS, oColIDs, new MyE2_String("LaufzettelID / AufgabeDI"));

		
		//hier kommen die Felder	
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER_BEARBEITER")), new MyE2_String("Bearbeiter"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("FAELLIG_AM")), new MyE2_String("Fällig am"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAUFZETTEL")), new MyE2_String("Laufzettel ID"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAUFZETTEL_EINTRAG")), new MyE2_String("Aufgabe ID"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAUFZETTEL_PRIO")), new MyE2_String("Priorität"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_LAUFZETTEL_STATUS")), new MyE2_String("Status"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER_BESITZER")), new MyE2_String("Besitzer"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ANGELEGT_AM")), new MyE2_String("Angelegt am"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ABGESCHLOSSEN_AM")), new MyE2_String("Abgeschlossen am"));
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER_ABGESCHLOSSEN_VON")), new MyE2_String("Abgeschlossen von"));

		// Möglichkeit eine Spalte aus der Liste per Default auszublenden:
		//this.get__Comp("ID_USER_BEARBEITER").EXT().set_bIsVisibleInList(false);
		this.get_hmRealComponents().get("ID_LAUFZETTEL").EXT().set_bIsVisibleInList(false);
		this.get_hmRealComponents().get("ID_LAUFZETTEL_EINTRAG").EXT().set_bIsVisibleInList(false);
		
		GridLayoutData layout = new GridLayoutData();
		layout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		this.get_hmRealComponents().get("ID_USER_BEARBEITER").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("ID_USER_BESITZER").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("ID_USER_ABGESCHLOSSEN_VON").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("ID_LAUFZETTEL_PRIO").EXT().set_oLayout_ListElement(layout);
		this.get_hmRealComponents().get("ID_LAUFZETTEL_STATUS").EXT().set_oLayout_ListElement(layout);
		
		if (!bStandAlone)
		{
			this.get__Comp(WF_CONST.HASH_MULTI_COL_BUTTONS).EXT().set_oCompTitleInList(
					new WF_LIST_BT_NEW(oNaviList,oWF_MASK_Container));
		}
		
		
		
		this.set_oSubQueryAgent(new WF_LIST_FORMATING_Agent());
	}

}
