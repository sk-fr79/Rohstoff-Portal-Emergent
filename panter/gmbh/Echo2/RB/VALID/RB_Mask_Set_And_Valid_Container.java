package panter.gmbh.Echo2.RB.VALID;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.IF.IF_RB_Collector;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



/**
 * existiert in jeder RB_Mask, um beliebige Einstellungen oder Aktionen beim Laden, Klicken oder vor dem speichern (validierungslauf) auszufuehren
 * Container-klasse
 * @author martin
 *
 */
public class RB_Mask_Set_And_Valid_Container  implements E2_IF_Copy, IF_RB_Collector<RB_Mask_Set_And_Valid> {
	
	private LinkedHashMap<RB_KF, Vector<RB_Mask_Set_And_Valid>> hm_ValidatorVector = new LinkedHashMap<RB_KF, Vector<RB_Mask_Set_And_Valid>>();
	
	
	
	/**
	 * fuegt dem 
	 * @param fieldKey (validierer ist primaer zu einer beliebigen komponente gebunden)
	 * @param oSettingAndValid
	 */
	@Override
	public RB_Mask_Set_And_Valid registerComponent(RB_K fieldKey, RB_Mask_Set_And_Valid oSettingAndValid) throws myException {
		if (fieldKey instanceof RB_KF) {
			Vector<RB_Mask_Set_And_Valid> vValid = this.hm_ValidatorVector.get(fieldKey);
			
			if (vValid==null) {
				vValid = new Vector<RB_Mask_Set_And_Valid>();
				this.hm_ValidatorVector.put((RB_KF)fieldKey, vValid);
			}
			
			vValid.add(oSettingAndValid);
			oSettingAndValid.rb_set_belongs_to(this);

			return oSettingAndValid;
			
		} else {
			throw new myException(this,"RB_Mask_Set_And_Valid:Keytype MUST be RB_KF");
		}
	}

	
	/**
	 * 
	 * @param mask (RB_K - keywert)
	 * @return
	 */
	public  Vector<RB_Mask_Set_And_Valid> get(RB_KF mask) {
		return this.hm_ValidatorVector.get(mask);
	}
	



	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		RB_Mask_Set_And_Valid_Container oCopy = new RB_Mask_Set_And_Valid_Container();
		
		for (RB_KF cKEY: this.hm_ValidatorVector.keySet()) {
			Vector<RB_Mask_Set_And_Valid> vValid = this.hm_ValidatorVector.get(cKEY);
			for (RB_Mask_Set_And_Valid valid: vValid) 	{
				try {
					oCopy.registerComponent(cKEY, valid);
				} catch (myException e) {
					e.printStackTrace();
					throw new myExceptionCopy(e.ErrorMessage);
				}
			}
		}
		return oCopy;
	}
	
	
	public Set<RB_KF> keySet() {
		return this.hm_ValidatorVector.keySet();
	}


	
}
