
package rohstoff.businesslogic.bewegung2.list.listSelector;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;


public class B2_SelectField extends MyE2_SelectField implements IF_FontandText<MyE2_SelectField> {

	public B2_SelectField() {
		super();
	}

	public B2_SelectField(Extent oWidth) {
		super(oWidth);
	}


	public B2_SelectField(String[] aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
	}


	public B2_SelectField(Vector<String> aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
	}


	public B2_SelectField(String[][] aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
	}


	public B2_SelectField(HashMap<String, String> aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
	}

	
	public B2_SelectField(String[] aDefArray, String cdefaultValue, boolean btranslate, Extent oWidth) throws myException {
		super(aDefArray, cdefaultValue, btranslate, oWidth);
	}

	
	public B2_SelectField(String[][] aDefArray, String cdefaultValue, boolean btranslate, Extent oWidth)
			throws myException {
		super(aDefArray, cdefaultValue, btranslate, oWidth);
	}

	
	public B2_SelectField(LinkedHashMap<String, String> hm_values, boolean bEmptyInFront, boolean bTranslate)
			throws myException {
		super(hm_values, bEmptyInFront, bTranslate);
	}


	public B2_SelectField(LinkedHashMap<String, MyE2_String> hm_values, boolean bEmptyInFront) throws myException {
		super(hm_values, bEmptyInFront);
	}


	public B2_SelectField(String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER, boolean bEmtyValueInFront,
			boolean bValuesFormated, boolean btranslate) throws myException {
		super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated, btranslate);
	}


	public B2_SelectField(String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER, boolean bEmtyValueInFront,
			boolean bValuesFormated, boolean btranslate, Extent Width) throws myException {
		super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated, btranslate,
				Width);
	}


	public B2_SelectField(String cSQL_Query_For_LIST, String cSQL_Query_4_Inaktiv, boolean bThirdColumnIS_STANDARD_MARKER,
			boolean bEmtyValueInFront, boolean bValuesFormated, boolean btranslate, Extent Width) throws myException {
		super(cSQL_Query_For_LIST, cSQL_Query_4_Inaktiv, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront,
				bValuesFormated, btranslate, Width);
	}

}
