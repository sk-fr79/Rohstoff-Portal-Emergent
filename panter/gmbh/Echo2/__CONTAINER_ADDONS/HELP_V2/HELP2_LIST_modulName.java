package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import java.util.HashMap;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW_ReplaceDB_Value;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class HELP2_LIST_modulName extends MyE2_DB_Label_INROW_ReplaceDB_Value {

	public HELP2_LIST_modulName(SQLField osqlField) throws myException {
		super(osqlField, true);
		
		HashMap<String, String> hmTextErsatz = new HashMap<String, String>();
		hmTextErsatz.put("", "");
		
		for (E2_MODULNAME_ENUM.MODUL m: E2_MODULNAME_ENUM.MODUL.values()) {
			hmTextErsatz.put(m.get_callKey(), m.get_userInfo().CTrans());
		}
		
		Color dark = new E2_ColorDark();
		HashMap<String, Color> hmBackGround = new HashMap<String, Color>();
		hmBackGround.put("",  null);
		for (E2_MODULNAME_ENUM.MODUL m: E2_MODULNAME_ENUM.MODUL.values()) {
			hmBackGround.put(m.get_callKey(), dark);
		}

		
		this.set_ReplaceOptions(hmTextErsatz, null, null, hmBackGround, hmBackGround);
		
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		try {
			HELP2_LIST_modulName cop = new HELP2_LIST_modulName(this.EXT_DB().get_oSQLField());
			cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
			return cop;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	
}
