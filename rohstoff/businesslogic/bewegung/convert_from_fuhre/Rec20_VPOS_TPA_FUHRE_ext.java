package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBResultSet_Prepared;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec20Prep;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;


public class Rec20_VPOS_TPA_FUHRE_ext  extends Rec21{

	public Rec20_VPOS_TPA_FUHRE_ext() throws myException{
		super (_TAB.vpos_tpa_fuhre);
	}
	
	public Rec20_VPOS_TPA_FUHRE_ext(Rec21 baseRec) throws myException{
		super(baseRec);
	}
	
	
	
	
}
