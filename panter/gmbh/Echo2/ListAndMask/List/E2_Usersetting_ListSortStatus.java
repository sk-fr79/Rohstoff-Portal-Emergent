package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.UserSettings.E2_Usersetting_Vector_of_Strings;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;

/**
 * speichert den sort-vector aus einer sql-field-map
 * @author martin
 *
 */
public class E2_Usersetting_ListSortStatus extends E2_Usersetting_Vector_of_Strings {
	
	public E2_Usersetting_ListSortStatus(String keyOfSqlFieldMap)  {
		super(ENUM_USER_SAVEKEY.SESSION_HASH_USER_SAVE_SORT_LIST.name(),";",keyOfSqlFieldMap);
	}

	
	
}
