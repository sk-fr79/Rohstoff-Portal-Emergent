/**
 * panter.gmbh.Echo2.RB.HIGHLEVEL
 * @author martin
 * @date 03.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelFieldV2S;
import panter.gmbh.Echo2.RB.COMP.SelV2.RB_SelectCascading;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_taxGroup;

/**
 * @author martin
 * @date 03.05.2019
 *
 */
public class RB_HL_SelectTaxWithGroups extends RB_SelFieldV2S {

	public RB_HL_SelectTaxWithGroups() throws myException {
		super();
		
		RecList21 rlSteuerGruppen = new RecList21(_TAB.tax_group)._fillWithAll(new VEK<IF_Field>()._a(TAX_GROUP.kurztext));

		HMAP<String, String> 				valuesVisible = new HMAP<>();
		HMAP<String, String> 				valuesInactive = new HMAP<>();
		HMAP<String, HMAP<String, String>>  valuesSubMenues =new HMAP<>();
		
		//jetzt die gruppen-submenues bauen
		for (Rec21 tg: rlSteuerGruppen) {
			if (tg.is_yes_db_val(TAX_GROUP.aktiv)) {
				Rec21_taxGroup tg2 = new Rec21_taxGroup(tg);
				HMAP<String, String> subMenue = new HMAP<>();
				
				RecList21 subTax = tg2.get_down_reclist21(TAX.id_tax_group);
				
				for (Rec21 tax: subTax) {
					if (tax.is_yes_db_val(TAX.aktiv)) {
						subMenue.put(tax.getFs(TAX.id_tax),tax.getUfs(TAX.steuersatz)+" %,  "+tax.getFs(TAX.dropdown_text));
					}
				}
				if (subMenue.size()>0) {
					valuesSubMenues.put(tg2.getFs(TAX_GROUP.kurztext), subMenue);
				}
			} else {
				//steuern aus inaktive steuergruppen werden automatisch den inaktiven steuern zugeschlagen 
				Rec21_taxGroup tg2 = new Rec21_taxGroup(tg);
				RecList21 subTax = tg2.get_down_reclist21(TAX.id_tax_group);

				for (Rec21 tax: subTax) {
					valuesInactive.put(tax.getFs(TAX.id_tax),tax.getUfs(TAX.steuersatz)+" %,  "+tax.getFs(TAX.dropdown_text));
				}
			}
		}

		//jetzt die nicht gruppierten
		RecList21 rlSteuernOhneGruppe = new RecList21(_TAB.tax)._fill(new And(new vgl_YN(TAX.aktiv, true)).and(new VglNull(TAX.id_tax_group)).s(), TAX.dropdown_text.fn());
		//dann alle nicht-aktiven in die shadows
		RecList21 rlSteuernInaktiv = new RecList21(_TAB.tax)._fill(new And(new vgl_YN(TAX.aktiv, false)).s(),TAX.dropdown_text.fn());
		
		//einen leereintrag einfuegen
		valuesVisible.put("", "-");
		for (Rec21 tax: rlSteuernOhneGruppe) {
			valuesVisible.put(tax.getFs(TAX.id_tax),tax.getUfs(TAX.steuersatz)+" %,  "+tax.getFs(TAX.dropdown_text));

		}

		for (Rec21 tax: rlSteuernInaktiv) {
			valuesInactive.put(tax.getFs(TAX.id_tax),tax.getUfs(TAX.steuersatz)+" %,  "+tax.getFs(TAX.dropdown_text));
		}
		
		this._setWidthTextField(250)._setWidthOfDropDownBlock(300)._render()._populate(valuesVisible, valuesInactive, valuesSubMenues);
		this._setAutomaticButtonWidth();
	}

	
	public RB_HL_SelectTaxWithGroups(HMAP<String, String> valuesVisible, HMAP<String, String> valuesInactive, HMAP<String, HMAP<String, String>> valuesSubMenues ) throws myException {
		super();
		this._setWidthTextField(250)._setWidthOfDropDownBlock(300)._populate(valuesVisible, valuesInactive, valuesSubMenues);
		this._setAutomaticButtonWidth();
		this._render();
	}
	
	
	
	public RB_HL_SelectTaxWithGroups _setHidden(VEK<String> hiddenValues) {
		this.getSelectCascadingWithString()._setHidden(hiddenValues);
		
		return this;
	}
	
	
	
	@Override
	public void setShapeOfActionButtonOutside(RB_SelectCascading<String>.MenueButton button, String key) {
		
		if (key.equals("")) {
			button.setToolTipText("Steuersatzfeld leeren ...");
		} else {

			if (new MyLong(key).isOK()) {
				try {
					Rec21 r = new Rec21(_TAB.tax)._fill_id(new MyLong(key).getLong());
					button.setToolTipText(r.getUfs(TAX.info_text_user,TAX.dropdown_text)+": "+r.getFs(TAX.steuersatz)+"% (ID: "+r.getFs(TAX.id_tax)+")");
					
				} catch (myException e) {
					e.printStackTrace();
					button.setToolTipText("Error: <4923c276-6fdc-11e9-a923-1681be663d3e>");
				}
				
			}
		}
		
		button.setInsets(new Insets(2, 2, 2, 2));
		button.setBorder(new Border(1, new E2_ColorDark(), Border.STYLE_SOLID));
		button.setTextAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
		button.setLayoutData(new RB_gld()._ins(2));
	}
	
	public void setHighLightStatusOfActualValueButtonOutside(RB_SelectCascading<String>.MenueButton button) {
		//button.setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));
		button.setBackground(new E2_ColorLight());
	}
	public void resetHighLightStatusOfActualValueButtonOutside(RB_SelectCascading<String>.MenueButton button) {
		//button.setBorder(new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID));
		button.setBackground(new E2_ColorDark());

	}


	
	
}
