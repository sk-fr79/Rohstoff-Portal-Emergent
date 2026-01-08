package panter.gmbh.Echo2.RB.VALID;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.IF.IF_RB_Part;
import panter.gmbh.indep.exceptions.myException;


/*
 * klassen werden in die componentmap uebergeben und werden am ende der methode fill_ComponentMapFromDB() ausgefuehrt 
 */
public abstract class RB_Mask_Set_And_Valid  implements IF_RB_Part<RB_Mask_Set_And_Valid_Container>  {
	
	
	private RB_Mask_Set_And_Valid_Container    rb_Mask_Set_And_Valid_Container_This_BelongsTo = null; 
	
	
	//schluessel unter dem das objekt bei der uebergeordneten collection registriert ist
	private RB_K  										my_key_in_collection = null;
	
	
	public RB_K  getMyKeyInCollection() throws myException {
		return this.my_key_in_collection;
	}
	
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		this.my_key_in_collection = key;
		return key;
	}


	
	/**
	 * 
	 * @param rbMASK
	 * @param ActionType
	 * @param oExecInfo
	 * @return
	 * @throws myException
	 */
	public abstract MyE2_MessageVector make_Interactive_Set_and_Valid(	RB_ComponentMap rbMASK, 
																		VALID_TYPE ActionType, 
																		ExecINFO oExecInfo) throws myException;
		


	public void rb_set_belongs_to(RB_Mask_Set_And_Valid_Container rbMask_Set_And_Valid_Container_This_BelongsTo) throws myException {
		this.rb_Mask_Set_And_Valid_Container_This_BelongsTo = rbMask_Set_And_Valid_Container_This_BelongsTo;
	}
	public RB_Mask_Set_And_Valid_Container rb_get_belongs_to() throws myException{
		return rb_Mask_Set_And_Valid_Container_This_BelongsTo;
	}

	
}
