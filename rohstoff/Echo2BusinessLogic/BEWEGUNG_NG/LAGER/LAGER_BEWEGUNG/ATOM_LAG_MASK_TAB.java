package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;

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
* TODO .... MUSS Umbenannt werden in ATOM_LAG_MASK
*/
public class ATOM_LAG_MASK_TAB extends MyE2_Column 
{

	
	public ATOM_LAG_MASK_TAB(ATOM_LAG_MASK_ComponentMAP oMap) throws myException
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
		oFiller.add_Line(oGrid0, new MyE2_String("ABGERECHNET:"), 1, "ABGERECHNET|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ABRECHENBAR:"), 1, "ABRECHENBAR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ABZUG_MENGE:"), 1, "ABZUG_MENGE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ARTBEZ1:"), 1, "ARTBEZ1|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ARTBEZ2:"), 1, "ARTBEZ2|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BEMERKUNG:"), 1, "BEMERKUNG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BEMERKUNG_SACHBEARBEITER:"), 1, "BEMERKUNG_SACHBEARBEITER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BESTELLNUMMER:"), 1, "BESTELLNUMMER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("BUCHUNGSNUMMER:"), 1, "BUCHUNGSNUMMER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("DEL_DATE:"), 1, "DEL_DATE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("DELETED:"), 1, "DELETED|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("DEL_GRUND:"), 1, "DEL_GRUND|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("DEL_KUERZEL:"), 1, "DEL_KUERZEL|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("E_PREIS:"), 1, "E_PREIS|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("E_PREIS_RESULT_NETTO_MGE:"), 1, "E_PREIS_RESULT_NETTO_MGE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("EU_STEUER_VERMERK:"), 1, "EU_STEUER_VERMERK|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_ARTIKEL:"), 1, "ID_ARTIKEL|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_ARTIKEL_BEZ:"), 1, "ID_ARTIKEL_BEZ|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_BEWEGUNG:"), 1, "ID_BEWEGUNG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_BEWEGUNG_ATOM:"), 1, "ID_BEWEGUNG_ATOM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_BEWEGUNG_VEKTOR:"), 1, "ID_BEWEGUNG_VEKTOR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_VPOS_KON:"), 1, "ID_VPOS_KON|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ID_VPOS_STD:"), 1, "ID_VPOS_STD|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("KONTRAKTZWANG:"), 1, "KONTRAKTZWANG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("KONTRAKTZWANG_AUS_AM:"), 1, "KONTRAKTZWANG_AUS_AM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("KONTRAKTZWANG_AUS_GRUND:"), 1, "KONTRAKTZWANG_AUS_GRUND|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("KONTRAKTZWANG_AUS_VON:"), 1, "KONTRAKTZWANG_AUS_VON|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("LEISTUNGSDATUM:"), 1, "LEISTUNGSDATUM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("LIEFERINFO_TPA:"), 1, "LIEFERINFO_TPA|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("MENGE:"), 1, "MENGE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("NATIONALER_ABFALL_CODE:"), 1, "NATIONALER_ABFALL_CODE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("NOTIFIKATION_NR:"), 1, "NOTIFIKATION_NR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ORDNUNGSNUMMER_CMR:"), 1, "ORDNUNGSNUMMER_CMR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("PLANMENGE:"), 1, "PLANMENGE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("POSNR:"), 1, "POSNR|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("POSTENNUMMER:"), 1, "POSTENNUMMER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("PREISERMITTLUNG:"), 1, "PREISERMITTLUNG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("PRUEFUNG_MENGE:"), 1, "PRUEFUNG_MENGE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("PRUEFUNG_MENGE_AM:"), 1, "PRUEFUNG_MENGE_AM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("PRUEFUNG_MENGE_VON:"), 1, "PRUEFUNG_MENGE_VON|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("SETZKASTEN_KOMPLETT:"), 1, "SETZKASTEN_KOMPLETT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("STEUERSATZ:"), 1, "STEUERSATZ|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("STORNIERT:"), 1, "STORNIERT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("STORNIERT_AM:"), 1, "STORNIERT_AM|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("STORNIERT_GRUND:"), 1, "STORNIERT_GRUND|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("STORNIERT_VON:"), 1, "STORNIERT_VON|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ZEITSTEMPEL:"), 1, "ZEITSTEMPEL|#  |",3);

		
		
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
				((XX_FULL_DAUGHTER) this.oE2_MAP.get(ATOM_LAG_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
				((XX_FULL_DAUGHTER) this.oE2_MAP.get(ATOM_LAG_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
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
