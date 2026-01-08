/**
 * panter.gmbh.Echo2.components.DB
 * @author martin
 * @date 17.12.2018
 * 
 */
package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.HMAP;

/**
 * @author martin
 * @date 17.12.2018
 * allgemeine klasse um einen JD_USER - eintrag in der liste anzuzeigen.
 * uebergeben wird ein String mit der ID, zurueck kommt ein String aus mehreren feldinhalten
 */
public class E2_DBLabelTranslateDbvalToReadableVal extends E2_DBActiveLabelForLists {

	private HMAP<String, String>  	   mapDbvalToReadable = new HMAP<String, String>();


	public E2_DBLabelTranslateDbvalToReadableVal(SQLField osqlField) throws myException {
		super(osqlField);
	}

	@Override
	public void set_cActualMaskValue(String dbVal) throws myException {
		
		String dbValLocal = S.NN(dbVal);
		
		String ersatz = mapDbvalToReadable.get(dbValLocal);
		
		if (ersatz==null) {
			ersatz = dbVal;
		}
		this.setText(ersatz);
	}

	public E2_DBLabelTranslateDbvalToReadableVal _setTranslateMap( HMAP<String, String> mapDbValToReadable) {
		this.mapDbvalToReadable._putAll(mapDbValToReadable);
		return this;
	}


	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		try {
			E2_DBLabelTranslateDbvalToReadableVal cop = new E2_DBLabelTranslateDbvalToReadableVal(this.EXT_DB().get_oSQLField())
												._setTranslateMap(this.mapDbvalToReadable);
			cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
			return cop;
			
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
