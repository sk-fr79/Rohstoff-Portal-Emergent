package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class LAND__MASK_TAB extends MyE2_Column  implements IF_BaseComponent4Mask
{
	
	private Vector<IF_ADDING_Allowed>  vAddingAllowed = new Vector<IF_ADDING_Allowed>();
	
	public LAND__MASK_TAB(LAND__MASK_ComponentMAP oMap) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		
		// 5 Grids
		MyE2_Grid oGrid0 = new MyE2_Grid(4,0);
		MyE2_Grid oGrid1 = new MyE2_Grid(4,0);
		
		this.vAddingAllowed.add(oGrid0);
		this.vAddingAllowed.add(oGrid1);
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Land"), oGrid0);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("RC-Sorten"), oGrid1);
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
		oTabbedPaneMaske.set_bAutoHeight(true);

		//hier kommen die Felder	
		oFiller.add_Line(oGrid0, new MyE2_String("ID"), 1, _DB.LAND$ID_LAND+"|#  |",3);
		oFiller.add_Separator(oGrid0, E2_INSETS.I(2,2,2,2));
		oFiller.add_Line(oGrid0, new MyE2_String("Name des Lands"), 1, _DB.LAND$LAENDERNAME+"|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Ländercode"), 1, _DB.LAND$LAENDERCODE+"|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("ISO-Ländercode"), 1, LAND.iso_country_code.fn()+"|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Postcode"), 1, _DB.LAND$POST_CODE+"|#  |#Postcode vorhanden:|"+_DB.LAND$HAT_POSTCODE,3);
		oFiller.add_Line(oGrid0, new MyE2_String("UST-Präfix"), 1, _DB.LAND$UST_PRAEFIX+"|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Ländervorwahl"), 1, _DB.LAND$LAENDERVORWAHL+"|#  |",3);
		oFiller.add_Separator(oGrid0, E2_INSETS.I(2,2,2,2));
		oFiller.add_Line(oGrid0, new MyE2_String("Beschreibung"), 1, _DB.LAND$BESCHREIBUNG+"|#  |",3);
		oFiller.add_Separator(oGrid0, E2_INSETS.I(2,2,2,2));
		oFiller.add_Line(oGrid0, new MyE2_String("Intrastat/EU-Land"), 1, _DB.LAND$INTRASTAT_JN+"|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Gelangensbestätigung(Sonderfall)"), 1, LAND.sonderfall_gelangensbestaet.fn()+"|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Generelle Einfuhr-Notifizierung"), 1, _DB.LAND$GENERELLE_EINFUHR_NOTI+"|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Standard"), 1, _DB.LAND$ISTSTANDARD+"|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Reihenfolge der Anzeige"), 1, _DB.LAND$ANZEIGEREIHENFOLGE+"|#  |",3);

		oFiller.add_Line(oGrid1, LAND__CONST.LAND_MASK_COMP__RC_SORTEN_MATRIX+"|#  |",4);
		
		

	}
	
	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException {
		return this.vAddingAllowed;
	}
	

	
	
	
//	/**
//	 * actionAgents fuer die tab, die die Complexen tochter-felder aktivieren
//	 */
//	private class tabbActionAgent extends XX_ActionAgent
//	{
//		private E2_ComponentMAP 			oE2_MAP = null;
//		private XX_FULL_DAUGHTER			oDaughterToActivate = null;
//		
//		public tabbActionAgent(XX_FULL_DAUGHTER activate, E2_ComponentMAP oe2_map)
//		{
//			super();
//			this.oDaughterToActivate = activate;
//			this.oE2_MAP = oe2_map;
//		}
//
//
//		public void executeAgentCode(ExecINFO execInfo) throws myException
//		{
//			try
//			{
//				/* hier alle full-daughter-klassen deaktivieren
//				((XX_FULL_DAUGHTER) this.oE2_MAP.get(LAND__LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
//				((XX_FULL_DAUGHTER) this.oE2_MAP.get(LAND__LIST_BasicModuleContainer.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
//				*/
//
//				if (this.oDaughterToActivate!=null) this.oDaughterToActivate.set_bIsActive(true);
//			}
//			catch (myException ex)
//			{
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("tabbActionAgent:doAction:Error setting Complex-Objects!"));
//			}
//		}
//	}




	
}
