/**
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading.MenueEntry;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class Rec21_taxGroup extends Rec21 {

	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_taxGroup() throws myException {
		super(_TAB.tax_group);
	}

	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_taxGroup(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab()!=_TAB.tax_group) {
			throw new myException("baseRec must be rec of table TAX_GROUP !");
		}
	}

	
	//checked
	public VEK<MenueEntry>  getSubMenueOfTaxes() throws myException {
		VEK<MenueEntry> v_ret = new VEK<MenueEntry>();
		
		RecList21 rlTaxes = this.get_down_reclist21(TAX.id_tax_group,new vgl_YN(TAX.aktiv, true).s(), TAX.dropdown_text.fn());
		
		for (Rec21 rs: rlTaxes) {
			String ausnahmen = rs.get_down_reclist21(TAX_AENDERUNGEN.id_tax).size()>0?" **AUSNAHMEN** ":"";
			
			VEK<String> steuerAusnahmen = new Rec21_tax(rs).getFormatedTaxesAusnahmen();
			String t_ausnahmen = steuerAusnahmen.size()>0?("/"+steuerAusnahmen.concatenante("/")):"";
			
			if (rs.getRawVal(TAX.steuersatz_neu)==null) {
				v_ret._a(new MenueEntry("("+rs.getFs(TAX.steuersatz)+t_ausnahmen+") "+rs.getFs(TAX.dropdown_text)+ausnahmen, rs.getFs(TAX.id_tax)));
			} else {
				v_ret._a(new MenueEntry("("+rs.getFs(TAX.steuersatz)+"/"+rs.getFs(TAX.steuersatz_neu)+t_ausnahmen+") "+rs.getFs(TAX.dropdown_text)+ausnahmen, rs.getFs(TAX.id_tax)));
			}
		}
		return v_ret;
	}
	
	
	
	
}
