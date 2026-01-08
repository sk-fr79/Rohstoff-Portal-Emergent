/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 29.05.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 29.05.2020
 *
 */
public class B2_Lib {

	/**
	 * @author martin
	 * @date 29.05.2020
	 *
	 */
	public B2_Lib() {
	}

	
//	/**
//	 * erzeugt key fuer die indifikation der BeschriftungsTexte
//	 * @author martin
//	 * @date 29.05.2020
//	 *
//	 * @param maskKey
//	 * @param field
//	 * @return
//	 * @throws Exception
//	 */
//	public static RB_KF genTextKey(RB_KM maskKey, IF_Field field) throws myException {
//		RB_KF fieldKey = new RB_KF();
//		String prefix = null;
//		
//		if (maskKey.equals(RecV.key)) {
//			prefix = "V";
//		} else if (maskKey.equals(RecA1.key)) {
//			prefix = "A1";
//		}  else if (maskKey.equals(RecA2.key)) {
//			prefix = "A2";
//		}  else if (maskKey.equals(RecS1.key)) {
//			prefix = "S1";
//		}  else if (maskKey.equals(RecS2.key)) {
//			prefix = "S2";
//		} else if (maskKey.equals(RecS3.key)) {
//			prefix = "S3";
//		} else {
//			throw new myException("maskkey not in allowed range: V,A1,A2,S1,S2,S3");
//		}
//		
//		fieldKey._setHASHKEY("TEXT@"+prefix+"@"+field.fieldName().toUpperCase())
//				._setREALNAME("TEXT@"+prefix+"@"+field.fieldName().toUpperCase())
//				;
//		
//		
//		
//		
//		return fieldKey;
//	}
//
//	public static RB_KF genTextKey(RB_KM maskKey, String fieldKeyName) throws myException {
//		RB_KF fieldKey = new RB_KF();
//		String prefix = null;
//		
//		if (maskKey.equals(RecV.key)) {
//			prefix = "V";
//		} else if (maskKey.equals(RecA1.key)) {
//			prefix = "A1";
//		}  else if (maskKey.equals(RecA2.key)) {
//			prefix = "A2";
//		}  else if (maskKey.equals(RecS1.key)) {
//			prefix = "S1";
//		}  else if (maskKey.equals(RecS2.key)) {
//			prefix = "S2";
//		} else if (maskKey.equals(RecS3.key)) {
//			prefix = "S3";
//		} else {
//			throw new myException("maskkey not in allowed range: V,A1,A2,S1,S2,S3");
//		}
//		
//		fieldKey._setHASHKEY("TEXT@"+prefix+"@"+fieldKeyName.toUpperCase())
//				._setREALNAME("TEXT@"+prefix+"@"+fieldKeyName.toUpperCase())
//				;
//		
//		
//		
//		
//		return fieldKey;
//	}

	
	
}
