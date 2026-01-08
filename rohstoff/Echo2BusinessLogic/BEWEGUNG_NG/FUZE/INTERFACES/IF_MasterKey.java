package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES;

import java.util.ArrayList;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.Mapper.FieldMapperOrSetter;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.UmbuchungInfoMap;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_MASK_TYPE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;

public interface IF_MasterKey extends IF_KeyChain {
	public KEY_VEKT 								k_vektor();
	public IF_MasterKey  							set_mask_status(MASK_STATUS newStatus);
	public ENUM_MASK_TYPE     						getMaskType();
	public ArrayList<FieldMapperOrSetter> 			getFieldMappersBeforeSaveMask();
	public ArrayList<FieldMapperOrSetter> 			getFieldMappersAfterSaveDataObject();
	public ArrayList<FieldMapperOrSetter> 			getFieldMappersBeforeSaveDataObject();
	public ArrayList<UmbuchungInfoMap>				getUmbuchungen();
	
	public KEY_ATOM 								getKeyAtomStart();
	public KEY_ATOM 								getKeyAtomZiel();
	
	public default  ENUM_VEKTOR_TYP getVektorType() {
		return this.getMaskType().getVectorType();
	}
	
}
