/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_waehrung;

/**
 * @author martin
 *
 */
public class PdServiceAdressCurrency {
	
	
	private Long        lIdAdress = null;
	private Rec21_adresse 	recAdress = null;
	private Rec21_waehrung 	recSystemBaseCurrency = null;
	private Rec21_waehrung 	recAdressBaseCurrency = null;
	private RecList21  		recAdressAddonCurrencys = null;
	private RecList21   	recListForeignKontraktCurrencys = null;
	private RecList21   	recListForeignHeadedRechGutCurrencys = null;
	private RecList21   	recListForeignFreeRechGutCurrencys = null;
	
	/**
	 * 
	 */
	public PdServiceAdressCurrency(Long idAdress) throws myException{
		super();
		this.lIdAdress = 				idAdress;
	}

	
	/**
	 * 
	 * @return Rec21 with first found ForeignCurrency or null when there is none 
	 * @throws myException
	 */
	public Rec21_waehrung getFirstFoundForeignCurrency() throws myException {
		if (this.getRecAdressBaseCurrency().getId()!=this.getRecSystemBaseCurrency().getId()) {
			return this.getRecAdressBaseCurrency();
		} else {
			for (Rec21 r: this.getRecListAdressAddonCurrencys()) {
				if (r.getId()!=this.getRecSystemBaseCurrency().getId()) {
					return new Rec21_waehrung(r);
				}
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @return VEK<Rec21> with all ForeignCurrencys which are possible in the adress
	 * @throws myException
	 */
	public VEK<Rec21_waehrung> getAdressForeignCurrencys() throws myException {
		VEK<Rec21_waehrung> v = new VEK<Rec21_waehrung>();
		if (this.getRecAdressBaseCurrency().getId()!=this.getRecSystemBaseCurrency().getId()) {
			v._a(this.getRecAdressBaseCurrency());
		} 
		for (Rec21 r: this.getRecListAdressAddonCurrencys()) {
			if (r.getId()!=this.getRecSystemBaseCurrency().getId()) {
				v._a(new Rec21_waehrung(r));
			}
		}
		
		return v;
	}
	

	
	public boolean isBaseCurrencyAForeignCurrency() throws myException {
		return this.getRecAdressBaseCurrency().getId()==this.getRecSystemBaseCurrency().getId();
	}

	public boolean isAdressOwningForeighCurrency() throws myException {
		return this.getAdressForeignCurrencys().size()>0;
	}
	
	
	
	

	public Rec21_waehrung getRecSystemBaseCurrency() throws myException {
		if (recSystemBaseCurrency==null) {
			recSystemBaseCurrency = new Rec21_waehrung(new Rec21(_TAB.waehrung)._fill_id(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_lValue(0l)));
		}
		return recSystemBaseCurrency;
	}


	public long getLongSystemCurrency()  throws myException{
		return this.getRecSystemBaseCurrency().getId();
	}



	public Rec21_waehrung getRecAdressBaseCurrency() throws myException {
		if (this.recAdressBaseCurrency==null) {
			this.recAdressBaseCurrency=new Rec21_waehrung(this.getRecAdress().get_up_Rec21(WAEHRUNG.id_waehrung));
		}
		return recAdressBaseCurrency;
	}


	public long getLongAdressBaseCurrency()  throws myException{
		return this.getRecAdressBaseCurrency().getId();
	}



	public RecList21 getRecListAdressAddonCurrencys() throws myException {
		if (this.recAdressAddonCurrencys==null) {
			this.recAdressAddonCurrencys = new RecList21(_TAB.waehrung);
			RecList21 rlaw = this.getRecAdress().get_down_reclist21(ADRESSE_WAEHRUNG.id_adresse);
			for (Rec21 raw: rlaw) {
				this.recAdressAddonCurrencys._add(raw.get_up_Rec21(WAEHRUNG.id_waehrung));
			}
		}
		return recAdressAddonCurrencys;
	}



	
	public VEK<Rec21_waehrung> getAdressAddonCurrencysExceptBaseCurrency() throws myException {
		VEK<Rec21_waehrung>  v = new VEK<Rec21_waehrung>();

		for (Rec21 r: this.getRecListAdressAddonCurrencys()) {
			if (r.getId()!=this.getLongAdressBaseCurrency()) {
				v.add(new Rec21_waehrung(r));
			}
		}
		
		return v;
	}




	public Rec21_adresse getRecAdress() throws myException {
		if (this.recAdress==null) {
			this.recAdress = new Rec21_adresse()._fill_id(this.lIdAdress);
		}
		return recAdress;
	}


	public RecList21 getRecListForeignKontraktCurrencys() throws myException {
		if (this.recListForeignKontraktCurrencys==null) {
			SEL sel = new SEL(_TAB.waehrung.fullTableName()+".*").ADD_Distinct()
								.FROM(_TAB.vpos_kon)
								.INNERJOIN(_TAB.vkopf_kon, VPOS_KON.id_vkopf_kon, 		VKOPF_KON.id_vkopf_kon)
								.INNERJOIN(_TAB.waehrung,  VPOS_KON.id_waehrung_fremd, 	WAEHRUNG.id_waehrung)
					.WHERE(	new vglParam(VKOPF_KON.id_adresse, 			COMP.EQ))
					.AND(	new vglParam(VPOS_KON.id_waehrung_fremd, 	COMP.NOT_EQ))
					;
			SqlStringExtended sql1 = new SqlStringExtended(sel.s());
			sql1._addParameters(new VEK<ParamDataObject>()	._a(new Param_Long(this.getRecAdress().getLongDbValue(ADRESSE.id_adresse)))
															._a(new Param_Long(this.getRecSystemBaseCurrency().getLongDbValue(WAEHRUNG.id_waehrung))));
			
			this.recListForeignKontraktCurrencys = new RecList21(_TAB.waehrung)._fill(sql1);
		}
		return this.recListForeignKontraktCurrencys; 
	}





	public RecList21 getRecListForeignHeadedRechGutCurrencys() throws myException {
		if (this.recListForeignHeadedRechGutCurrencys==null) {
			SEL sel = new SEL(_TAB.waehrung.fullTableName()+".*").ADD_Distinct().FROM(_TAB.vpos_rg)
						.INNERJOIN(_TAB.vkopf_rg, VPOS_RG.id_vkopf_rg, VKOPF_RG.id_vkopf_rg)
						.INNERJOIN(_TAB.waehrung, VPOS_RG.id_waehrung_fremd, WAEHRUNG.id_waehrung)
					.WHERE(new vglParam(VKOPF_RG.id_adresse, COMP.EQ))
					.AND(new vglParam(VPOS_RG.id_waehrung_fremd, COMP.NOT_EQ))
					;
			SqlStringExtended sql = new SqlStringExtended(sel.s());
			sql._addParameters(new VEK<ParamDataObject>()	._a(new Param_Long(this.getRecAdress().getLongDbValue(ADRESSE.id_adresse)))
															._a(new Param_Long(this.getRecSystemBaseCurrency().getLongDbValue(WAEHRUNG.id_waehrung))));
			this.recListForeignHeadedRechGutCurrencys = new RecList21(_TAB.waehrung)._fill(sql);
		}
		
		return this.recListForeignHeadedRechGutCurrencys;
	}
	
	
	public RecList21 getRecListForeignFreeRechGutCurrencys() throws myException {
		if (this.recListForeignFreeRechGutCurrencys==null) {
			SEL sel = new SEL(_TAB.waehrung.fullTableName()+".*").ADD_Distinct().FROM(_TAB.vpos_rg)
						.INNERJOIN(_TAB.waehrung, VPOS_RG.id_waehrung_fremd, WAEHRUNG.id_waehrung)
					.WHERE(new vglParam(VKOPF_RG.id_adresse, COMP.EQ))
					.AND(new vglParam(VPOS_RG.id_waehrung_fremd, COMP.NOT_EQ))
					.AND(new VglNotNull(VPOS_RG.id_vkopf_rg))
					;
			SqlStringExtended sql = new SqlStringExtended(sel.s());
			sql._addParameters(new VEK<ParamDataObject>()	._a(new Param_Long(this.getRecAdress().getLongDbValue(ADRESSE.id_adresse)))
															._a(new Param_Long(this.getRecSystemBaseCurrency().getLongDbValue(WAEHRUNG.id_waehrung))));
			this.recListForeignFreeRechGutCurrencys = new RecList21(_TAB.waehrung)._fill(sql);
		}
		return this.recListForeignFreeRechGutCurrencys;
	}
	

	
}
