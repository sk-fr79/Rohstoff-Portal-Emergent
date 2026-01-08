/**
 * rohstoff.businesslogic.bewegung2.lager
 * @author sebastien
 * @date 05.12.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.lager;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST.B2_LAG_NAMES;

/**
 * @author sebastien
 * @date 05.12.2019
 *
 */
public class B2_LAG_LIST_Cmp_LagerKunde extends E2_UniversalListComponent {

	private B2_LAG_NAMES m_field = null;

	public B2_LAG_LIST_Cmp_LagerKunde(B2_LAG_NAMES ziellagerAdresse)throws myException{
		super();
		this.m_field = ziellagerAdresse;
		this._s(1)._w100()._bo_no();

	}

	@Override
	public String key() throws myException {
		return this.m_field.db_val();
	}

	@Override
	public String userText() throws myException {
		return this.m_field.user_text();
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		if(resultMap.get_UnFormatedValue(this.key()) != null && S.isFull(resultMap.get_UnFormatedValue(this.key()))) {
			Rec21_adresse recAdresse = new Rec21_adresse()._fill_id(resultMap.get_UnFormatedValue(this.key()));

			RB_lab adresse_label = new RB_lab(recAdresse.__get_name1_ort());

			if(recAdresse.is_sonderlager()) {
				adresse_label._col_fore_dgrey();
//				adresse_label._t(recAdresse.getUfs(ADRESSE.sonderlager, "-"));
			}
			this._a(adresse_label, new RB_gld()._left_top());
			if(recAdresse.__is_liefer_adresse() && !recAdresse.is_sonderlager()) {
				this._a(new RB_lab()._t("Hauptadresse: " + recAdresse._getMainAdresse().__get_name1_ort())._i()._fsa(-2));
			}
		}else {
			this._a("-",new RB_gld()._left_top());
		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_LAG_LIST_Cmp_LagerKunde ret = new B2_LAG_LIST_Cmp_LagerKunde(this.m_field);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

}
