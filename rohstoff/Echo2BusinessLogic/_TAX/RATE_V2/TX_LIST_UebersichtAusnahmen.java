/**
 * rohstoff.Echo2BusinessLogic._TAX.RATE_V2
 * @author martin
 * @date 27.11.2020
 * 
 */
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 27.11.2020
 *
 */
public class TX_LIST_UebersichtAusnahmen extends E2_UniversalListComponent {

	private Rec22 tax = null;

	public static String KeyName = "TX_LIST_UebersichtAusnahmen - 2518e0fa-308d-11eb-adc1-0242ac120002";
	
	@Override
	public String key() throws myException {
		return TX_LIST_UebersichtAusnahmen.KeyName;
	}

	@Override
	public String userText() throws myException {
		return "Steuer-Ausnahmen";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(50,10,50,40);
		
		
		try {
			this.tax = new Rec22(_TAB.tax)._fill_id(resultMap.getLongId());

			RecList22 rl = tax.get_down_reclist22(TAX_AENDERUNGEN.id_tax);
			
			if (rl.size()>0) {
				for (Rec22 ta: rl) {
					this._a(new RB_lab(ta.get_fs_dbVal(TAX_AENDERUNGEN.gueltig_von, ""))._fsa(-2), 	new RB_gld()._ins(2, 2, 2, 2))
						._a(new RB_lab("-")._fsa(-2), 	new RB_gld()._ins(2, 2, 2, 2)._center_mid())
						._a(new RB_lab(ta.get_fs_dbVal(TAX_AENDERUNGEN.gueltig_bis, ""))._fsa(-2), 	new RB_gld()._ins(2, 2, 2, 2))
						._a(new RB_lab(ta.get_fs_dbVal(TAX_AENDERUNGEN.steuersatz, "")+" %")._line_wrap(false)._fsa(-2), 	new RB_gld()._ins(2, 2, 2, 2)._right_top())
						;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new TX_LIST_UebersichtAusnahmen();
	}
	
}
