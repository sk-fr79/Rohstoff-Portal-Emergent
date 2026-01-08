/**
 * rohstoff.Echo2BusinessLogic.ARTIKELSTAMM
 * @author martin
 * @date 30.10.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;

/**
 * @author martin
 * @date 30.10.2019
 *
 */
public class AS_LIST_ComponentShowUndefinedDanger extends E2_UniversalListComponent {

	/**
	 * @author martin
	 * @date 30.10.2019
	 *
	 */
	public AS_LIST_ComponentShowUndefinedDanger() {

	}

	@Override
	public String key() throws myException {
		return this.getClass().getName()+"-07b79a82-732c-4c75-906a-6e7d5793bdf1";
	}

	@Override
	public String userText() throws myException {
		return "Gefahr-Status";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		
		this._clear()._setSize(30,30,30)._bord_dgrey();
		try {
			this.setHeight(new Extent(35));
			
			E2_ComponentMAP_V2 map = ((E2_ComponentMAP_V2)this.EXT().get_oComponentMAP());
			
			Rec21_artikel artikel = new Rec21_artikel(map.getRec21());
			
			boolean gefahrWgSchalter = artikel.is_yes_db_val(ARTIKEL.gefahrgut);
			boolean gefahrWgBaselCode = artikel.isGefaehrlichNachBaselCode();
			boolean gefahrWgOECDCode = artikel.isGefaehrlichNachOECDCode();
			boolean gefahrWgEk_AVV = artikel.isGefaehrlichNachAVVEingang();
			boolean gefahrWgVk_AVV = artikel.isGefaehrlichNachAVVExMandant();
			boolean gefahrWgKundenSpezSorte = artikel.hasEinstungungGefaehrlichNachKundenArtikelbez();
			
			
			this.__ad(createLab("AS"," nach Schalter im Artikelstamm",			gefahrWgSchalter));
			this.__ad(createLab("B"," nach Basel-Code",							gefahrWgBaselCode));
			this.__ad(createLab("O"," nach OECD-Code",							gefahrWgOECDCode));
			this.__ad(createLab("AVV"," nach Kleinanlieferer-AVV",				gefahrWgEk_AVV));
			this.__ad(createLab("AVV"," nach AVV-Ex-Mandant",					gefahrWgVk_AVV));
			this.__ad(createLab("KSA"," kundenspezifischem Artikel",			gefahrWgKundenSpezSorte));
		} catch (Exception e) {
			e.printStackTrace();
			this._setSize(100);
			this._a(new RB_lab("@@ERR@@"));
		}
		
		
	}

	private void __ad(PAIR<RB_lab,RB_gld> vals) {
		this._a(vals.getVal1(),vals.getVal2());
	}
	
	private PAIR<RB_lab,RB_gld> createLab(String txt, String tooltips, boolean gefaehrlich) {
		return new PAIR<RB_lab,RB_gld>()._setVal1(new RB_lab(txt)._ttt(S.ms((gefaehrlich?"Gefährlich":"Ungefährlich")+tooltips)))
				._setVal2(gefaehrlich?new RB_gld()._ins(1)._center_mid()._col_back_alarm(): new RB_gld()._ins(1)._center_mid()._col_back_green());
	}
	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new AS_LIST_ComponentShowUndefinedDanger();
	}

	
}
