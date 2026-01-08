/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_PopupWithButtonCascading.MenueEntry;
import panter.gmbh.Echo2.components.DB.E2_DB_SelFieldAsCascadingPopup;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_tax;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_taxGroup;

/**
 * @author martin
 *
 */
public class FUO_MASK_SelectTaxViaPopup extends E2_DB_SelFieldAsCascadingPopup {

	//checked
	/**
	 * @param osqlField
	 * @param iTextFieldLen
	 * @throws myException
	 */
	public FUO_MASK_SelectTaxViaPopup(SQLField osqlField) throws myException {
		super(osqlField, 65, 600);
		
		//2020-11-13: tooltips erweitern
		this._registerTooltipGenerator((id)-> {
			String tooltip = "";
			MyLong l = new MyLong(id);
			if (l.isOK()) {
				try {
					Rec21_tax tax = new Rec21_tax()._fill_id(l.get_oLong());
					String steuerNeu = tax.getFs(TAX.steuersatz_neu, "");
					
					VEK<String> steuerAusnahmen = tax.getFormatedTaxesAusnahmen();
					String t_ausnahmen = steuerAusnahmen.size()>0?("/"+steuerAusnahmen.concatenante("/")):"";

					
					steuerNeu = (S.isFull(steuerNeu)?" / Wechsel: "+steuerNeu:"");
					RecList21 ausnahmen = tax.get_down_reclist21(TAX_AENDERUNGEN.id_tax);
					String ausnahmenText = ausnahmen.size()>0?" / **AUSNAHMEN**":"";
					
					tooltip = tax.getFs(TAX.dropdown_text)+", Basis-Steuersatz: "+tax.get_fs_dbVal(TAX.steuersatz)+"% "+steuerNeu+ausnahmenText+t_ausnahmen+", (ID:"+tax.get_fs_dbVal(TAX.id_tax)+")";
				} catch (myException e) {
					e.printStackTrace();
				}
			}
			
			return tooltip;
		});

	}

	@Override
	public void executeClick(MenueEntry menue) throws myException {
		
	}

	//checked
	@Override
	public VEK<MenueEntry> generateMenueStructur() throws myException {
		
		VEK<MenueEntry> vRet = new VEK<MenueEntry>();
		
		RecList21 rlSteuerGruppen = new RecList21(_TAB.tax_group)._fillWithAll(new VEK<IF_Field>()._a(TAX_GROUP.kurztext));
		
		//jetzt die gruppen-submenues bauen
		for (Rec21 rg: rlSteuerGruppen) {
			Rec21_taxGroup rgg = new Rec21_taxGroup(rg);
			if (rgg.getSubMenueOfTaxes().size()>0) {
				vRet._a(new MenueEntry(rg.getFs(TAX_GROUP.kurztext), rgg.getSubMenueOfTaxes()));
			}
		}

		//jetzt die nicht gruppierten
		RecList21 rlSteuernOhneGruppe = new RecList21(_TAB.tax)._fill(new And(new vgl_YN(TAX.aktiv, true)).and(new VglNull(TAX.id_tax_group)).s(), TAX.dropdown_text.fn());
		//leereintrag schreiben
		vRet._a(new MenueEntry("<Steuersatz entfernen für Neuauswahl>", "").setFont(new E2_FontItalic()));
		for (Rec21 rs: rlSteuernOhneGruppe) {
			String ausnahmen = rs.get_down_reclist21(TAX_AENDERUNGEN.id_tax).size()>0?" **AUSNAHMEN** ":"";

			VEK<String> steuerAusnahmen = new Rec21_tax(rs).getFormatedTaxesAusnahmen();
			String t_ausnahmen = steuerAusnahmen.size()>0?("/"+steuerAusnahmen.concatenante("/")):"";
			
			if (rs.getRawVal(TAX.steuersatz_neu)==null) {
				vRet._a(new MenueEntry("("+rs.getFs(TAX.steuersatz)+t_ausnahmen+") "+rs.getFs(TAX.dropdown_text)+ausnahmen, rs.getFs(TAX.id_tax)));
			} else {
				vRet._a(new MenueEntry("("+rs.getFs(TAX.steuersatz)+"/"+rs.getFs(TAX.steuersatz_neu)+t_ausnahmen+") "+rs.getFs(TAX.dropdown_text)+ausnahmen, rs.getFs(TAX.id_tax)));
			}

		}

		return vRet;
	}


	/**
	 * fuer den action-validator muessen die geklickten endpoint-buttons die gleiche signatur tragen wie die komponente selbst, damit die FU_Set_And_Valid_Steuervermerk() funktioniert
	 * @return
	 * @throws myException 
	 */
	public FUO_MASK_SelectTaxViaPopup _setOwnHashkeyToAllButtonEndpointIds() throws myException {
		for (E2_Button b: this.getPopupCascade().getRealMenueButtons()) {
			b.EXT().set_C_HASHKEY(this.EXT().get_C_HASHKEY());
		}
		return this;
	}
	
	
}
