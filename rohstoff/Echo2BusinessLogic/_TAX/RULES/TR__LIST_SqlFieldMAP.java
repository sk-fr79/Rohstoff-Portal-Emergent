package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class TR__LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public TR__LIST_SqlFieldMAP(String cWhereBlock) throws myException 
	{
		super("JT_HANDELSDEF", "", false);
		
		this.add_JOIN_Table("JD_LAND", "LQJ", SQLFieldMAP.LEFT_OUTER, "LAENDERCODE", true, "JT_HANDELSDEF.ID_LAND_QUELLE_JUR=LQJ.ID_LAND", 	"LQJ_", null);
		this.add_JOIN_Table("JD_LAND", "LZJ", SQLFieldMAP.LEFT_OUTER, "LAENDERCODE", true, "JT_HANDELSDEF.ID_LAND_ZIEL_JUR=LZJ.ID_LAND", 	"LZJ_", null);

		this.add_JOIN_Table("JD_LAND", "LQG", SQLFieldMAP.LEFT_OUTER, "LAENDERCODE", true, "JT_HANDELSDEF.ID_LAND_QUELLE_GEO=LQG.ID_LAND", 	"LQG_", null);
		this.add_JOIN_Table("JD_LAND", "LZG", SQLFieldMAP.LEFT_OUTER, "LAENDERCODE", true, "JT_HANDELSDEF.ID_LAND_ZIEL_GEO=LZG.ID_LAND", 	"LZG_", null);
		
		
		this.add_JOIN_Table("JT_TAX", "TQ", SQLFieldMAP.LEFT_OUTER, "DROPDOWN_TEXT", true, "JT_HANDELSDEF.ID_TAX_QUELLE=TQ.ID_TAX", 	"TQ_", null);
		this.add_JOIN_Table("JT_TAX", "TZ", SQLFieldMAP.LEFT_OUTER, "DROPDOWN_TEXT", true, "JT_HANDELSDEF.ID_TAX_ZIEL=TZ.ID_TAX",    	"TZ_", null);
		
		if (S.isFull(cWhereBlock))
		{
			this.add_BEDINGUNG_STATIC(cWhereBlock);
		}
		this.get_(_DB.HANDELSDEF$ID_TAX_QUELLE).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(_DB.HANDELSDEF$ID_TAX_ZIEL).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
		
	}
	
}
