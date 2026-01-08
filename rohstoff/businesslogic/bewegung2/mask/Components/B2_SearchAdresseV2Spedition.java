/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 09.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Adresse;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_TPA;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 09.01.2020
 *
 */
public class B2_SearchAdresseV2Spedition extends RB_HL_Search_V2_Adresse {

	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @throws myException
	 */
	public B2_SearchAdresseV2Spedition() throws myException {
		super();
		this._setShapeForBewegung2();
		
		this._setFindSpeditionenWhenSearchEmpty();
	}

	
	
	public RB_SearchFieldV2 _setShapeForBewegung2() throws myException {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		
		getTextFieldSearchInput()._w(80);
		this._clear()
			._a(getTextFieldSearchInput(), gl)
			._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
			._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
			._a(getEditAdresse(), gl._c()._ins(0, 2, 2, 0))
			._a(this.getGridForResults(), gl._c()._ins(0,0,0,0))
			._setSize(80,20,20,20,140);

		return this;
	}
	
	
	@Override
	public void renderResultOnMask(E2_Grid gridForResults, Long id) throws myException {
		gridForResults._clear()._setSize(140)._bo_dd();
		if (id!=null) {
			Rec21_adresse  recAd = new Rec21_adresse()._fill_id(id);
			String  nameOrt = recAd.getName1Ort();
			PAIR<String,String>  resultLang = recAd.getVornameName1Name2OrtInfoZuFirma();
			String toolTips = S.NN(resultLang.getVal1(),"-")+"\n"+S.NN(resultLang.getVal2(),"-");
			gridForResults	._a(new RB_lab(nameOrt)._i()._fsa(-2)._ttt(toolTips), new RB_gld()._left_top()._ins(2,0,2,0));
			;
		}
	}
	
	
	
	/**
	 * sorgt dafuer, dass bei leerem suchfeld die eigenen adressen angezeigt werden 
	 * @author martin
	 * @date 26.09.2019
	 *
	 *
	 * @return
	 */
	public B2_SearchAdresseV2Spedition _setFindSpeditionenWhenSearchEmpty() {

		try {
			this._setAllowEmptySearchfield(true);
			this._setSearchIconAutomatikSearch();
			this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Wird der Suchbutton mit leerer Eingabe gedückt, dann werden nur Adressen gefunden, die bisher in Transportaufträgen vorkamen !"));
			
			SEL sel = new SEL(VKOPF_TPA.id_adresse).FROM(_TAB.vkopf_tpa).ADD_Distinct();
			
			this.getAndStatementCollectorForBasic().clear();
			//gesucht werden: hauptadressen
			this.getAndStatementCollectorForBasic().and(new vgl(ADRESSE.adresstyp, ""+myCONST.ADRESSTYP_FIRMENINFO));
			//nur aktive
			this.getAndStatementCollectorForBasic().and(new vgl(ADRESSE.aktiv, "Y"));

			this.getAndStatementCollectorForBasic().and(new TermSimple(ADRESSE.id_adresse.tnfn()+" IN ("+sel.s()+")"));
			
		} catch (myException e) {
			e.printStackTrace();
			
			bibMSG.MV()._addAlarm(e.getOriginalMessage()+"<628e0a06-32f4-11ea-978f-2e728ce88125>");
		}
		
		return this;
	}
}
