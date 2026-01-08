/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author sebastien
 * @date 06.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author sebastien
 * @date 06.12.2018
 *
 */
public class Rec21_artikel extends Rec21 {


	public Rec21_artikel() throws myException {
		super(_TAB.artikel);
	}
	
	public Rec21_artikel(Rec21 baseRec) throws myException{
		super(baseRec);
		if (baseRec.get_tab() != _TAB.artikel) {
			throw new myException(this,"Only Record from type ADRESSE are allowed !");
		}
	}
	
	public String get_id_eak_code() throws myException{
		return this.get_ufs_dbVal(ARTIKEL.id_eak_code, "");
	}

	/**
	 * @return einheitkurz
	 * @throws myException
	 */
	public String get_einheit_k() throws myException{
		return this.get_up_Rec21(EINHEIT.id_einheit).get_fs_dbVal(EINHEIT.einheitkurz,"");
	}
	
	
	public Rec21 getEinheit() {
		try {
			return this.get_up_Rec21(EINHEIT.id_einheit);
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * ermittelt den "default"-Artikelbez (meist mit ANR2==00)
	 * @author manfred
	 * @date 11.11.2019
	 *
	 * @return
	 */
	public Rec21_artikel_bez get_artikel_bez_default() {
		Rec21_artikel_bez rec_artbez = null;
		try {
			RecList21 rlArtbez = get_down_reclist21(ARTIKEL_BEZ.id_artikel,"", ARTIKEL_BEZ.artbez2.fn());
			if (rlArtbez != null && rlArtbez.size() > 0) {
				rec_artbez = new Rec21_artikel_bez(rlArtbez.get(0));
			}
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rec_artbez;
	}
	
	/**
	 * 
	 * @return einheitlang
	 * @throws myException
	 */
	public String get_einheit_l() throws myException{
		return this.get_up_Rec21(EINHEIT.id_einheit).get_fs_dbVal(EINHEIT.einheitlang,"");
	}
	
	public String __get_anr1_artbez1(boolean idInKlammern) throws myException {
		String c = "";
		c=this.get_ufs_kette(" - ", ARTIKEL.anr1,ARTIKEL.artbez1);
		if (idInKlammern) {
			c = c +" ("+this.get_fs_dbVal(ARTIKEL.id_artikel)+")";
		}
		return c;
	}

	
	
	public Rec21_artikel _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	
	public Rec21_artikel _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	
	
	public Rec21_artikel _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}
	
	
	public boolean isGefaehrlichNachBaselCode() throws myException {
		return S.NN(this.getUfs(ARTIKEL.basel_code)).toUpperCase().startsWith("A");
	}
	
	public boolean isGefaehrlichNachOECDCode() throws myException {
		return S.NN(this.getUfs(ARTIKEL.eucode)).toUpperCase().startsWith("A");
	}
	
	
	public boolean isGefaehrlichNachAVVEingang() throws myException {
		Rec21 recAVV = this.get_up_Rec21(ARTIKEL.id_eak_code, EAK_CODE.id_eak_code, true);
		if (recAVV!=null) {
			return recAVV.is_yes_db_val(EAK_CODE.gefaehrlich);
		}
		
		return false;
	}
	
	
	public boolean isGefaehrlichNachAVVExMandant() throws myException {
		Rec21 recAVV = this.get_up_Rec21(ARTIKEL.id_eak_code_ex_mandant, EAK_CODE.id_eak_code, true);
		if (recAVV!=null) {
			return recAVV.is_yes_db_val(EAK_CODE.gefaehrlich);
		}
		
		return false;
	}
	
	
	public boolean hasEinstungungGefaehrlichNachKundenArtikelbez() throws myException {
		SEL sel = new SEL("COUNT(*) ").FROM(_TAB.eak_code,    "AVV")
				     .INNERJOIN(new TableTerm(_TAB.artikelbez_lief,"ABL"),new FieldTerm("AVV", EAK_CODE.id_eak_code),       new FieldTerm("ABL",ARTIKELBEZ_LIEF.id_eak_code))
				     .INNERJOIN(new TableTerm(_TAB.artikel_bez,    "AB"), new FieldTerm("ABL", ARTIKEL_BEZ.id_artikel_bez), new FieldTerm("AB",ARTIKEL_BEZ.id_artikel_bez))
				     .INNERJOIN(new TableTerm(_TAB.artikel,        "A"),  new FieldTerm("A",   ARTIKEL.id_artikel),         new FieldTerm("AB",ARTIKEL_BEZ.id_artikel))
				     .WHERE(new vglParam("A",ARTIKEL.id_artikel))
				     .AND(new TermSimple(new Nvl(new FieldTerm("AVV", EAK_CODE.gefaehrlich),"'N'").s()+"=?"));
     				;
		

		SqlStringExtended s_ex = new SqlStringExtended(sel.s())._addParameters(new VEK<ParamDataObject>()
																	._a(new Param_Long(this.getIdLong()))
																	._a(new Param_String("","Y"))
																	);
		
		VEK<Object[]> ob = bibDB.getResultLines(s_ex, true);
		
		if (ob==null || ob.size()==0) {
			throw new myException("Error quering "+sel.s()+": Code <dac6fcae-c8cc-40a7-8d1a-1036aa77f87f>");
		} else {
		   BigDecimal bdCount = (BigDecimal)ob.get(0)[0];
		   return bdCount.intValue()>0;
		}
	}
	
	
	
	public Rec21 getZollTarifNummer() {
		Rec21 ret  = null;
		
		try {
			ret = this.get_up_Rec21(ARTIKEL.id_zolltarifnummer, ZOLLTARIFNUMMER.id_zolltarifnummer, true);
		} catch (myException e) {
			e.printStackTrace();
			ret = null;
		}
		return ret;		
	}

	
	public boolean isReverseChargeInZolltarifnummer() throws Exception {
		boolean ret = false;
		Rec21 zolltarifnummer = this.getZollTarifNummer();
		if (zolltarifnummer!=null && zolltarifnummer.is_ExistingRecord()) {
			if (zolltarifnummer.is_yes_db_val(ZOLLTARIFNUMMER.reverse_charge)) {
				ret = true;
			}
		}
		return ret;
	}
	
	
}
