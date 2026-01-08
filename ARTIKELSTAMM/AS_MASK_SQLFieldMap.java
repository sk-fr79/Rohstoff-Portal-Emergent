package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class AS_MASK_SQLFieldMap extends Project_SQLFieldMAP
{
	public AS_MASK_SQLFieldMap() throws myException
	{
		super("JT_ARTIKEL","",false);
	
		this.get_("ANR1").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		//NEU_09
		this.get_("AKTIV").set_cDefaultValueFormated("Y");

		
		//2011-01-12: zolltarifnummer und rc-vermerk einblenden in artikel
		this.add_JOIN_Table("JT_ZOLLTARIFNUMMER", "JT_ZOLLTARIFNUMMER", SQLFieldMAP.LEFT_OUTER, 
				":NUMMER:REVERSE_CHARGE:", true,
				"JT_ARTIKEL.ID_ZOLLTARIFNUMMER=JT_ZOLLTARIFNUMMER.ID_ZOLLTARIFNUMMER", "ZT_", null);
		
		this.initFields();
	}

}
