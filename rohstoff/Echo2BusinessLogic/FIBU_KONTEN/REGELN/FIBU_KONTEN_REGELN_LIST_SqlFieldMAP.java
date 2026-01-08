package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_KONTEN_REGELN_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public FIBU_KONTEN_REGELN_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_FIBU_KONTENREGEL_NEU", "", false);
		

		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("ID_FILTER").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTO").set_cDefaultValueFormated("");
		this.get_("AKTIV").set_cDefaultValueFormated("Y");
		
		HashMap<String, String>  hmZusatzK = new HashMap<String, String>();
		hmZusatzK.put("FIBU_KONTO_GESAMT", "JT_FIBU_KONTO.KONTO||' - '||JT_FIBU_KONTO.BESCHREIBUNG");
		this.add_JOIN_Table("JT_FIBU_KONTO", "JT_FIBU_KONTO", SQLFieldMAP.LEFT_OUTER, "KONTO:BESCHREIBUNG", true, "JT_FIBU_KONTENREGEL_NEU.ID_FIBU_KONTO=JT_FIBU_KONTO.ID_FIBU_KONTO", "K_", hmZusatzK);
		
/*		HashMap<String, String>  hmZusatzT = new HashMap<String, String>();
		hmZusatzT.put("STEUERSATZ", "CASE WHEN JT_FIBU_KONTENREGEL.ID_TAX IS NOT NULL THEN JT_TAX.DROPDOWN_TEXT || ' (' || JD_LAND.LAENDERCODE || ', ' || JT_TAX.STEUERSATZ || '%)' ELSE  JT_TAX.DROPDOWN_TEXT END");
		this.add_JOIN_Table(_DB.TAX, _DB.TAX, SQLFieldMAP.LEFT_OUTER, "DROPDOWN_TEXT|STEUERSATZ", true, "JT_FIBU_KONTENREGEL.ID_TAX=JT_TAX.ID_TAX", "TAX_", hmZusatzT);
		this.add_JOIN_Table(_DB.LAND, _DB.LAND, SQLFieldMAP.LEFT_OUTER, "LAENDERKUERZEL", true, "JT_TAX.ID_LAND = JD_LAND.ID_LAND", "LAND_", null);
		
			
		this.add_SQLField(new SQLField("TO_CHAR(ID_FIBU_KONTENREGEL)", "QUELLEN_LAENDER", new MyE2_String("QUELLEN_LAENDER"), bibE2.get_CurrSession()), false);
		
	*/	
		this.initFields();
	}
	
}
