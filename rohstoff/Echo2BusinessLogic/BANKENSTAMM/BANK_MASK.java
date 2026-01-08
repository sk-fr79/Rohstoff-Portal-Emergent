package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.exceptions.myException;

public class BANK_MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BANK_MASK(BANK_MASK_ComponentMAP oMap) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,oMap.get_oBANK_MASK_ZusatzMap(),null);
		E2_MaskFiller  oFillerAdresse = new E2_MaskFiller(oMap,null,null);
		E2_MaskFiller  oFillerBank = new E2_MaskFiller(oMap.get_oBANK_MASK_ZusatzMap(),null,null);

		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		oTabbedPaneMaske.set_bAutoHeight(true);

		// 5 Grids
		MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid1 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid2 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid3 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid4 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid5 = new MyE2_Grid(2,0);
		
		
		this.vMaskGrids.add(oGrid0);
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
		this.vMaskGrids.add(oGrid3);
		this.vMaskGrids.add(oGrid4);
		this.vMaskGrids.add(oGrid5);
		
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Bank"), 			oGrid0,new tabbActionAgent(null,oMap));
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Zusätze"), 		oGrid1,new tabbActionAgent(null,oMap));
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Kontaktinfos"), 	oGrid2,new tabbActionAgent(null,oMap));
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Mailadressen"), 	oGrid3,new tabbActionAgent(null,oMap));
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Infos"), 		oGrid4,new tabbActionAgent((XX_FULL_DAUGHTER)oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS),oMap));
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Mitarbeiter"), 	oGrid5,new tabbActionAgent((XX_FULL_DAUGHTER)oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER),oMap));
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
		
			
		//hier kommen die Felder
		
		oFiller.add_Line(oGrid0, new MyE2_String("ID"), 1, "ID_ADRESSE|#  |#ID-Bank|#  |ID_BANKENSTAMM",1);
		oFiller.add_Line(oGrid0, new MyE2_String("BLZ"), 1, "BANKLEITZAHL<W300W>|#  |",1);
		oFiller.add_Line(oGrid0, new MyE2_String("Name 1"), 1, "NAME1<W300W>|#  |",1);
		oFiller.add_Line(oGrid0, new MyE2_String("Name 2"), 1, "NAME2<W300W>|#  |",1);
		oFiller.add_Line(oGrid0, new MyE2_String("Name 3"), 1, "NAME3<W300W>|#  |",1);
		oFiller.add_Line(oGrid0, new MyE2_String("Strasse - Hausnummer"), 1, "STRASSE<W300W>|#|HAUSNUMMER<W50W>",1);
		oFiller.add_Line(oGrid0, new MyE2_String("Land-PLZ-Ort"), 1, "ID_LAND|#|PLZ<W100W>|#|ORT",1);
		oFiller.add_Line(oGrid0, new MyE2_String("Aktiv"), 1, "AKTIV|#  |",1);
		oFiller.add_Line(oGrid0, new MyE2_String("BIC/Swift-Adresse"), 1, "SWIFTCODE<W300W>|#  |",1);
//		oFiller.add_Line(oGrid0, new MyE2_String("IBAN-Basiscode"), 1, "IBAN_NR|#  |",1);
		
		oFillerAdresse.add_Line(oGrid1, new MyE2_String("Info Adresse"), 1, "BEMERKUNGEN|#  |",1);
		oFillerBank.add_Line(oGrid1, new MyE2_String("Info Adresse"), 1, "BEMERKUNGEN|#  |",1);
		oFiller.add_Line(oGrid1, new MyE2_String("Sprache"), 1, "ID_SPRACHE|#  |",1);
		oFiller.add_Line(oGrid1, new MyE2_String("Währung"), 1, "ID_WAEHRUNG|#  |",1);
		
		oGrid2.add(oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_DAUGHTER_TELEFON), E2_INSETS.I_2_2_2_2);
		
		oGrid3.add(oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_DAUGHTER_EMAIL), E2_INSETS.I_2_2_2_2);
		
		oGrid4.add(oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS), E2_INSETS.I_2_2_2_2);
		
		oGrid5.add(oMap.get_Comp(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER), E2_INSETS.I_2_2_2_2);

		
		
			
	}

	
	
	
	/**
	 * actionAgents fuer die tab, die die Complexen tochter-felder aktivieren
	 */
	private class tabbActionAgent extends XX_ActionAgent
	{
		private E2_ComponentMAP 			oE2_MAP_ADRESSE = null;
		private XX_FULL_DAUGHTER			oDaughterToActivate = null;
		
		public tabbActionAgent(XX_FULL_DAUGHTER activate, E2_ComponentMAP oe2_map_adresse)
		{
			super();
			this.oDaughterToActivate = activate;
			this.oE2_MAP_ADRESSE = oe2_map_adresse;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{

				((XX_FULL_DAUGHTER) this.oE2_MAP_ADRESSE.get(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
				((XX_FULL_DAUGHTER) this.oE2_MAP_ADRESSE.get(BANK_LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
				
				if (this.oDaughterToActivate!=null) this.oDaughterToActivate.set_bIsActive(true);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}
		
	}




	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	

	
}
