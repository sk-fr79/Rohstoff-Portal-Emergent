package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

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
* TODO .... MUSS Umbenannt werden in WK_MASK
*/
public class WK_MASK_TAB extends MyE2_Column 
{

	
	public WK_MASK_TAB(WK_MASK_ComponentMAP oMap) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		
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
		oFiller.add_Line(oGrid0, new MyE2_String("BEFUND:"), 1, "BEFUND|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_ADRESSE_LAGER:"), 1, "ID_ADRESSE_LAGER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_ADRESSE_LIEFERANT:"), 1, "ID_ADRESSE_LIEFERANT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_ARTIKEL_SORTE:"), 1, "ID_ARTIKEL_SORTE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_VPOS_TPA_FUHRE:"), 1, "ID_VPOS_TPA_FUHRE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_VPOS_TPA_FUHRE_ORT:"), 1, "ID_VPOS_TPA_FUHRE_ORT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_WIEGEKARTE:"), 1, "ID_WIEGEKARTE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("IST_LIEFERANT:"), 1, "IST_LIEFERANT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("KENNZEICHEN:"), 1, "KENNZEICHEN|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("LFD_NR:"), 1, "LFD_NR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("NETTOGEWICHT:"), 1, "NETTOGEWICHT|#  |",3);

		
		
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
	private class tabbActionAgent extends XX_ActionAgent
	{
		private E2_ComponentMAP 			oE2_MAP = null;
		private XX_FULL_DAUGHTER			oDaughterToActivate = null;
		
		public tabbActionAgent(XX_FULL_DAUGHTER activate, E2_ComponentMAP oe2_map)
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
				((XX_FULL_DAUGHTER) this.oE2_MAP.get(WK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
				((XX_FULL_DAUGHTER) this.oE2_MAP.get(WK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
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
