/**
 * rohstoff.businesslogic.bewegung2.lager
 * @author sebastien
 * @date 07.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.lager;

import java.math.BigDecimal;

import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class B2_LAG_LIST_Cmp_mengeAnzeige extends E2_UniversalListComponent {

	private MENGE_TYP mengeTyp;
	
	public B2_LAG_LIST_Cmp_mengeAnzeige(MENGE_TYP oMgeTyp) {
		super();
		this.mengeTyp = oMgeTyp;
	}
	
	@Override
	public String key() throws myException {
		return this.mengeTyp.name().toUpperCase();
	}


	@Override
	public String userText() throws myException {
		return this.mengeTyp.name().toLowerCase();
	}


	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}


	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		String transportTyp = resultMap.get_UnFormatedValue(BG_VEKTOR.en_transport_typ.fn());
		String pos_in_mask 	= resultMap.get_UnFormatedValue(BG_ATOM.pos_in_mask.fn());

		BigDecimal bdMge 	= resultMap.get_bdActualValue(this.mengeTyp.getDbField().fn(), false);

		if(bdMge != null) {
			this._a(""+bdMge.toBigInteger().longValueExact());
		}else {
			this._a("");
		}
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_LAG_LIST_Cmp_mengeAnzeige ret = new B2_LAG_LIST_Cmp_mengeAnzeige(this.mengeTyp);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	public enum MENGE_TYP{
		MENGE_BRUTTO(BG_ATOM.menge),
		MENGE_NETTO(BG_ATOM.menge_netto),
		MENGE_ABZUG(BG_ATOM.menge_abzug),
		;
		
		private IF_Field field;

		MENGE_TYP(IF_Field pField){
			this.field = pField;
			
		}
		
		public IF_Field getDbField() {
			return field;
		}
	}
	
}
