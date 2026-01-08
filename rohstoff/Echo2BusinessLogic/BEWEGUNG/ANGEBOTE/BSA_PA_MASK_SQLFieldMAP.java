package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


// sqlfieldmap fuer die angebundene componentmap JT_VPOS_STD_ANGEBOT
public class BSA_PA_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSA_PA_MASK_SQLFieldMAP(SQLFieldMAP oSQLFieldMap_VPOS_STD) throws myException 
	{
		super("JT_VPOS_STD_ANGEBOT", ":ID_VPOS_STD:", false);
		
		// das connection-field
		this.add_SQLField(new SQLFieldJoinOutside("JT_VPOS_STD_ANGEBOT","ID_VPOS_STD","ID_VPOS_STD",new MyE2_String("ID-Position"),false,bibE2.get_CurrSession(),oSQLFieldMap_VPOS_STD.get_("ID_VPOS_STD")), false);

		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.initFields();
	}

}
