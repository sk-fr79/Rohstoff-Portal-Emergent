package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import java.util.HashMap;

import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW_ReplaceDB_Value;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class HELP2_LIST_user extends MyE2_DB_Label_INROW_ReplaceDB_Value {

	public HELP2_LIST_user(SQLField osqlField) throws myException {
		super(osqlField, true);
		
		HashMap<String, String> hmTextErsatz = new HashMap<String, String>();
		
		@SuppressWarnings("unchecked")
		HashMap<Long, Rec21>  hmUser = (HashMap<Long, Rec21>)ENUM_MANDANT_SESSION_STORE.HASHMAP_ALL_PROGRAMM_USERS.getValueFromSession();
		
		hmTextErsatz.put("", "");
		for (Long l: hmUser.keySet()) {
			Rec21 r = hmUser.get(l);
			String name = r.get_ufs_kette(" ", true, USER.vorname, USER.name1);
			if (S.isEmpty(name)) {
				name = "ID: "+r.getFs(USER.id_user);
			}
			hmTextErsatz.put(r.get_fs_dbVal(USER.id_user),name);
			
		}
		
		this.set_ReplaceOptions(hmTextErsatz, null, null, null, null);
		
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		try {
			HELP2_LIST_user cop = new HELP2_LIST_user(this.EXT_DB().get_oSQLField());
			cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
			return cop;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	
}
