/**
 * rohstoff.businesslogic.bewegung2.lager
 * @author sebastien
 * @date 06.12.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.lager;

import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;


public class B2_LAG_LIST_Cmp_Sorte extends E2_UniversalListComponent {

	private static String key = "04ba8572-3aff-44d4-ba2c-4b4d47d06850";

	public B2_LAG_LIST_Cmp_Sorte()throws myException{
		super();
		
	}
	
	@Override
	public String key() throws myException {
		return key;
	}

	@Override
	public String userText() throws myException {
		return "Sorte";
	}


	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
		this._s(1)._w100()._bo_no() ;
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		String id_artikel = resultMap.get_UnFormatedValue("SORTE_INFO", "");
		if(S.isFull(id_artikel)) {
			Rec21_artikel recArtikel = new Rec21_artikel()._fill_id(id_artikel);
			this._a(recArtikel.__get_anr1_artbez1(false), new RB_gld()._left_top());
		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_LAG_LIST_Cmp_Sorte ret = new B2_LAG_LIST_Cmp_Sorte();
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

}
