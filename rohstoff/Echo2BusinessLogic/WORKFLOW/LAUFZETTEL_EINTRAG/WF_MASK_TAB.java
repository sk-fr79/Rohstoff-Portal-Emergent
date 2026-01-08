package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.exceptions.myException;

/*
* maskenvariante mit TabbedPane 
*/
public class WF_MASK_TAB extends MyE2_Column 
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2843065263576982119L;




	public WF_MASK_TAB(WF_MASK_ComponentMAP oMap) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		oTabbedPaneMaske.set_bAutoHeight(true);

		
		// 5 Grids
		MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid1 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid2 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid3 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid4 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid5 = new MyE2_Grid(2,0);
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("text1"), oGrid0);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("text2"), oGrid1);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("text3"), oGrid2);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("text4"), oGrid3);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("text5"), oGrid4);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("text6"), oGrid5);
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
		
		//hier kommen die Felder	
		oFiller.add_Line(oGrid0, new MyE2_String("ABGESCHLOSSEN_AM:"), 1, "ABGESCHLOSSEN_AM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_USER_ABGESCHLOSSEN_VON:"), 1, "ID_USER_ABGESCHLOSSEN_VON|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ANGELEGT_AM:"), 1, "ANGELEGT_AM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("AUFGABE:"), 1, "AUFGABE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BERICHT:"), 1, "BERICHT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("FAELLIG_AM:"), 1, "FAELLIG_AM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("GEAENDERT_VON:"), 1, "GEAENDERT_VON|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_LAUFZETTEL:"), 1, "ID_LAUFZETTEL|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_LAUFZETTEL_EINTRAG:"), 1, "ID_LAUFZETTEL_EINTRAG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_LAUFZETTEL_PRIO:"), 1, "ID_LAUFZETTEL_PRIO|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_LAUFZETTEL_STATUS:"), 1, "ID_LAUFZETTEL_STATUS|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_MANDANT:"), 1, "ID_MANDANT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_USER_BEARBEITER:"), 1, "ID_USER_BEARBEITER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_USER_BESITZER:"), 1, "ID_USER_BESITZER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("LETZTE_AENDERUNG:"), 1, "LETZTE_AENDERUNG|#  |",3);

		
		
		/*
		 * beispiel fuer action-umschalter bei komplexten maskenelementen die actionagents zur aktivierung der komplexen toechter definieren
		 *
		oTabbedPaneMaske.add_ActionAgent_to_Tab(0,new tabbActionAgent(null,oMap));   // alles inaktiv
		oTabbedPaneMaske.add_ActionAgent_to_Tab(1,new tabbActionAgent(null,oMap));   // alles inaktiv
		oTabbedPaneMaske.add_ActionAgent_to_Tab(2,new tabbActionAgent(null,oMap));   // alles inaktiv
		oTabbedPaneMaske.add_ActionAgent_to_Tab(3,new tabbActionAgent(null,oMap));   // alles inaktiv
		oTabbedPaneMaske.add_ActionAgent_to_Tab(4,new tabbActionAgent((XX_FULL_DAUGHTER)oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS),oMap));   // alles inaktiv
		oTabbedPaneMaske.add_ActionAgent_to_Tab(5,new tabbActionAgent((XX_FULL_DAUGHTER)oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER),oMap));   // alles inaktiv
		*/

	}

	
	
	
	/**
	 * actionAgents fuer die tab, die die Complexen tochter-felder aktivieren
	 */
	@SuppressWarnings("unused")
	private class tabActionAgent extends XX_ActionAgent
	{
		private E2_ComponentMAP 			oE2_MAP = null;
		private XX_FULL_DAUGHTER			oDaughterToActivate = null;
		
		public tabActionAgent(XX_FULL_DAUGHTER activate, E2_ComponentMAP oe2_map)
		{
			super();
			this.oDaughterToActivate = activate;
			this.oE2_MAP = oe2_map;
		}


		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			try
			{
				/* hier alle full-daughter-klassen deaktivieren
				((XX_FULL_DAUGHTER) this.oE2_MAP.get(WF_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
				((XX_FULL_DAUGHTER) this.oE2_MAP.get(WF_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
				*/

				if (this.oDaughterToActivate!=null) this.oDaughterToActivate.set_bIsActive(true);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("tabbActionAgent:doAction:Error setting Complex-Objects!"));
			}
		}
	}
	

	
}
