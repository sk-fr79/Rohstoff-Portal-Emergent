/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Color;
import panter.gmbh.BasicInterfaces.Service.PdServiceAdressCurrency;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_waehrung;

/**
 * @author martin
 *
 */
public class BSFP_LIST_WaehrungsAnzeigeWrapper extends MyE2_DB_PlaceHolder_NT {

	private int spaltenBreiteAnzeige = 35;
	private int zeilenHoeheAnzeige = 20;
	
	/**
	 * @throws myException
	 */
	public BSFP_LIST_WaehrungsAnzeigeWrapper() throws myException {
		super();
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,	SQLResultMAP oResultMAP) throws myException {
		this._clear();
		this._setSize(70);
		this._add_r(new OwnWaehrungsAnzeiger(oResultMAP));
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy	{
		try {
			return new BSFP_LIST_WaehrungsAnzeigeWrapper();
		} catch (myException e) {
			e.printStackTrace();
			return new RB_lab()._t("ERROR");
		}
	}

	
	/*
	 * grid enthaelt zwei anzeigen, zuerst hinweis, ob adresse eine fremdwaehrung besitzt, dann Hinweis, ob eine Fremdwaehrung in der position vorliegt
	 */
	private class OwnWaehrungsAnzeiger extends E2_Grid {
		private SQLResultMAP m_resultMap;
	
		/**
		 * @param resultMAP
		 */
		public OwnWaehrungsAnzeiger(SQLResultMAP resultMAP) {
			super();
			this.m_resultMap = resultMAP;
			this._clear();
			
			try {
				Long idFremdWaehrung = 	this.m_resultMap.getLongDBValue(VPOS_RG.id_waehrung_fremd);
				Long idAdresse = 		this.m_resultMap.getLongDBValue(VPOS_RG.id_adresse);
				Long idVKopfRg  = 		this.m_resultMap.getLongDBValue(VPOS_RG.id_vkopf_rg);
				
				if (idVKopfRg!=null && idAdresse==null) {
					//dann die adresse aus dem kopf
					Rec21 recVkopfRg = new Rec21(_TAB.vkopf_rg)._fill_id(idVKopfRg);
					idAdresse = recVkopfRg.getLongDbValue(VKOPF_RG.id_adresse);
				}
				
				
				PdServiceAdressCurrency service = 			new PdServiceAdressCurrency(idAdresse);
				Rec21_waehrung  		waehrungPos = 		new Rec21_waehrung()._fill_id(idFremdWaehrung);
				VEK<Rec21_waehrung> 	vForeignCurrencys = service.getAdressForeignCurrencys();
				Rec21_waehrung 			waehrungAdressBase = service.getRecAdressBaseCurrency();

				
				String					listeAllerZusatzwaehrungen =  service.getRecListAdressAddonCurrencys().getResultString((r)->{ return (S.NN(r.getUfs("",WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung),""))+" / ";}).toString();
				listeAllerZusatzwaehrungen=S.NN(listeAllerZusatzwaehrungen);
				if (listeAllerZusatzwaehrungen.endsWith(" / ")) {
					listeAllerZusatzwaehrungen = listeAllerZusatzwaehrungen.substring(0,listeAllerZusatzwaehrungen.lastIndexOf(" / "));
				}
				
				Color 					colorAdressWaehrung	=  (waehrungAdressBase.isForeignCurrency()||vForeignCurrencys.size()>0?new E2_ColorAlarm():new E2_ColorBase());
				Color 					colorBelegWaehrung	=  (waehrungPos.isBaseCurrency()?new E2_ColorBase(): new E2_ColorAlarm());
				MyE2_String   			tttBasisWaehrung = S.mt("Firmen-Basiswährung: ").ut(waehrungAdressBase.getSymbol());
				if (S.isFull(listeAllerZusatzwaehrungen)) {
					tttBasisWaehrung.t("\nDie Adresse besitzt Zusatz-Währung(en):  <").ut(listeAllerZusatzwaehrungen+">");
				}
				MyE2_String  			tttBelegWaehrung = S.ms("Belegwährung: ").ut(waehrungPos.getSymbol());
				
				this._bo_ddd()._setSize(spaltenBreiteAnzeige,spaltenBreiteAnzeige)._setRowHight(zeilenHoeheAnzeige)
												._a(new RB_lab(waehrungAdressBase.getSymbol())._f(new E2_FontPlain(-2))._ttt(tttBasisWaehrung)
													,new RB_gld()._center_mid()._col_back(colorAdressWaehrung))
												._a(new RB_lab(waehrungPos.getSymbol())._f(new E2_FontPlain(-2))._ttt(tttBelegWaehrung)
													,new RB_gld()._center_mid()._col_back(colorBelegWaehrung));
				
			} catch (myException e) {
				e.printStackTrace();
				this._clear()._a(new RB_lab("@ERR"), new RB_gld()._center_mid()._col_back_alarm());
			}
		}
	}
	
	
}
