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
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValueSettingsOnMaskAction;

/**
 * @author martin
 * @date 09.01.2020
 *
 */
public class B2_SearchAdresseV2Fremdware extends RB_HL_Search_V2_Adresse {

	String searchLetzte100FremdAdressen = 
			" SELECT ID_ADRESSE FROM ( "+
			"   SELECT "+BG_STATION.id_adresse.tnfn()+" FROM "+BG_ATOM.fullTabName() + 
		    "     INNER JOIN "+BG_STATION.fullTabName()+" ON ("+BG_ATOM.id_bg_station_ziel.tnfn()+"="+BG_STATION.id_bg_station.tnfn()+") " + 
		    "     INNER JOIN "+BG_VEKTOR.fullTabName()+" ON ("+BG_VEKTOR.id_bg_vektor.tnfn()+"="+BG_ATOM.id_bg_vektor.tnfn()+") " +
			"       WHERE " +BG_VEKTOR.en_transport_typ.tnfn()+ "  = '"+EnTransportTyp.FREMDWARENTRANSPORT.dbVal()+"'"+
			"       AND   " +BG_ATOM.id_mandant.tnfn()+ "  = "+bibALL.get_ID_MANDANT()+
			"       AND   " +BG_VEKTOR.en_vektor_quelle.tnfn()+ "  = "+EN_VEKTOR_QUELLE.NATIV.dbVal4SqlTerm()+
			"         ORDER BY "+BG_ATOM.id_bg_atom+" DESC "+
			") WHERE ROWNUM<100 ";
	

	
	
	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @throws myException
	 */
	public B2_SearchAdresseV2Fremdware() throws myException {
		super();
		this._setShapeForBewegung2();
		this._setFindOnlyMainAdresses(true);
		this._setAllowEmptySearchfield(true);
		
		this._registerAfterFieldEraseEvents(()-> {
			try {
				new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._add(e.get_ErrorMessage());
			}	
		});
		
		this._registerAfterValueChangeEvents(()-> {
			try {
				new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._add(e.get_ErrorMessage());
			}	
		});

		
		this._registerBeforeStartSearchEvent(()-> {
			if (isSearchFieldEmpty()) {
				this.getAndStatementCollectorOneTimeCondition().add(new TermSimple("("+ADRESSE.id_adresse.tnfn()+" IN ("+searchLetzte100FremdAdressen+"))"));
			}
		});

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
}
