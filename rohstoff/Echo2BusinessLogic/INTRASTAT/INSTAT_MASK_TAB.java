package rohstoff.Echo2BusinessLogic.INTRASTAT;

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
* TODO .... MUSS Umbenannt werden in INSTAT_MASK
*/
public class INSTAT_MASK_TAB extends MyE2_Column 
{

	
	public INSTAT_MASK_TAB(INSTAT_MASK_ComponentMAP oMap) throws myException
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
		oFiller.add_Line(oGrid0, new MyE2_String("ID_INTRASTAT_MELDUNG:"), 1, "ID_INTRASTAT_MELDUNG|#  |",3);

		oFiller.add_Line(oGrid0, new MyE2_String("MELDEART:"), 1, "MELDEART|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ANMELDEFORM:"), 1, "ANMELDEFORM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("STEUERNR:"), 1, "STEUERNR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BUNDESLAND_FA:"), 1, "BUNDESLAND_FA|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BEZUGSJAHR:"), 1, "BEZUGSJAHR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BEZUGSMONAT:"), 1, "BEZUGSMONAT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ANMELDEMONAT:"), 1, "ANMELDEMONAT|#  |",3);

		oFiller.add_Line(oGrid0, new MyE2_String("ID Fuhre:"), 1, "ID_VPOS_TPA_FUHRE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID FuhreOrt:"), 1, "ID_VPOS_TPA_FUHRE_ORT|#  |",3);

		oFiller.add_Line(oGrid0, new MyE2_String("PAGINIERNUMMER:"), 1, "PAGINIERNUMMER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BESTIMM_LAND:"), 1, "BESTIMM_LAND|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BESTIMM_REGION:"), 1, "BESTIMM_REGION|#  |",3);

		oFiller.add_Line(oGrid0, new MyE2_String("WARENNR:"), 1, "WARENNR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("EIGENMASSE:"), 1, "EIGENMASSE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("MASSEINHEIT:"), 1, "MASSEINHEIT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("EXPORTIERT_AM:"), 1, "EXPORTIERT_AM|#  |",3);

		oFiller.add_Line(oGrid0, new MyE2_String("RECHBETRAG:"), 1, "RECHBETRAG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("STATISTISCHER_WERT:"), 1, "STATISTISCHER_WERT|#  |",3);

		oFiller.add_Line(oGrid0, new MyE2_String("GESCHAEFTSART:"), 1, "GESCHAEFTSART|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("LAND_URSPRUNG:"), 1, "LAND_URSPRUNG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("UNTERSCHEIDUNGSNR:"), 1, "UNTERSCHEIDUNGSNR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("VERKEHRSZWEIG:"), 1, "VERKEHRSZWEIG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("WAEHRUNGSKENNZIFFER:"), 1, "WAEHRUNGSKENNZIFFER|#  |",3);

		
		
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
				((XX_FULL_DAUGHTER) this.oE2_MAP.get(INSTAT_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
				((XX_FULL_DAUGHTER) this.oE2_MAP.get(INSTAT_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
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
