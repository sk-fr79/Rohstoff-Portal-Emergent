package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.KOMMUNIKATION;
import panter.gmbh.basics4project.DB_ENUMS.KOMMUNIKATIONS_TYP;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_POS;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.MITARBEITER;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.SANKTION.SANKTION_Hashwert_SHA256_generator;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_BigDecimal;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Date;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;

public class Rec21_adresse extends Rec21 {


	private Rec21  recLand = null;
	
	
	public Rec21_adresse() throws myException {
		super(_TAB.adresse);
	}


	public Rec21_adresse(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.adresse) {
			throw new myException(this,"Only Record from type ADRESSE are allowed !");
		}
	}


	//fill-methodes
	@Override
	public Rec21_adresse _fill(Rec21 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}

	@Override
	public Rec21_adresse _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_adresse _fill_sql(SqlStringExtended sqlExt) throws myException {
		super._fill_sql(sqlExt);
		return this;
	}


	@Override
	public Rec21_adresse _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_adresse _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}

	@Override
	public Rec21_adresse _fill(MyRECORD_IF_RECORDS rec) throws myException {
		super._fill(rec);
		return this;
	}

	public boolean __is_main_adresse() throws myException {
		return this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_FIRMENINFO);
	}

	public boolean __is_liefer_adresse() throws myException {
		return this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_LIEFERADRESSE);
	}

	/**
	 * @deprecated  use {@link #_getMainAdresse()} instead like this: 
	 */
	@Deprecated 
	public Rec21_adresse __get_main_adresse() throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"__get_main_adresse() is only possible on filled rec20-object");
		}

		if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_FIRMENINFO)) {
			return this;
		} else if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_LIEFERADRESSE)) {
			Rec21 		recAdresseBasis = this.get_down_reclist21(LIEFERADRESSE.id_adresse_liefer,null,null).get(0).get_up_Rec20(LIEFERADRESSE.id_adresse_basis,ADRESSE.id_adresse,true);
			return new Rec21_adresse(recAdresseBasis);
		} else {
			throw new myException(this,"__get_main_adresse() is only possible on types FIRMENINFO/LIEFERADRESSE");
		}
	}


	public Rec21_adresse _getMainAdresse() throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"_getMainAdresse() is only possible on filled rec20-object");
		}

		if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_FIRMENINFO)) {
			return this;
		} else if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_LIEFERADRESSE)) {
			Rec21 		recAdresseBasis = this.get_down_reclist21(LIEFERADRESSE.id_adresse_liefer,null,null).get(0).get_up_Rec20(LIEFERADRESSE.id_adresse_basis,ADRESSE.id_adresse,true);
			return new Rec21_adresse(recAdresseBasis);
		}  else if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_MITARBEITER)) {
			Rec21 		recAdresseBasis = this.get_down_reclist21(MITARBEITER.id_adresse_mitarbeiter,null,null).get(0).get_up_Rec20(MITARBEITER.id_adresse_basis,ADRESSE.id_adresse,true);
			return new Rec21_adresse(recAdresseBasis);
		}  else {
			throw new myException(this,"__get_main_adresse() is only possible on types FIRMENINFO/LIEFERADRESSE");
		}
	}



	public String __get_name1_ort() throws myException {
		String c = "";
		c=this.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.ort);
		return c;
	}


	public String __get_name1_ort_info_zu_firma() throws myException {
		String c = "";
		c=this.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.ort);
		if (this.__is_main_adresse()) {
			c+=" (Hauptadresse)";
		} else if (this.__is_liefer_adresse()) {
			Rec21_adresse rl = this.__get_main_adresse();
			c+=" (Lieferadresse von: "+rl.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.ort)+")";
		} else {
			throw new myException(this,"__get_name1_ort_info_zu_firma() is only possible on fonly possible on types FIRMENINFO/LIEFERADRESSE");
		}

		return c;
	}

	public String get__FullNameAndAdress_Typ2() throws myException{
		return this.get_ufs_kette(" ", true, ADRESSE.vorname, ADRESSE.name1, ADRESSE.name2, ADRESSE.name3, ADRESSE.ort);
	}

	public String get__FullNameAndAdress_name_anschrift() throws myException{
		return this.get_ufs_kette(" ", true,  ADRESSE.name1, ADRESSE.name2, ADRESSE.name3, ADRESSE.plz, ADRESSE.ort);
	}

	public String get__FullNameAndAdress_name_anschrift(String sep) throws myException{
		return this.get_ufs_kette(sep, true,  ADRESSE.name1, ADRESSE.name2, ADRESSE.name3, ADRESSE.plz, ADRESSE.ort);
	}

	public String get__Name1Name2StrassePlzOrt(String sep) throws myException{
		String name = this.get_ufs_kette(" ", true,  ADRESSE.name1, ADRESSE.name2);
		String strasse = this.get_ufs_kette(" ", true,  ADRESSE.strasse, ADRESSE.hausnummer);
		String plz_ort = this.get_ufs_kette(" ", true,  ADRESSE.plz, ADRESSE.ort);
		VEK<String>  v = new VEK<>();
		v._addValidated(s-> {return S.isFull(s);}, name,strasse,plz_ort);
		
		return  S.Concatenate(v, sep, "");
	}

	public String get__strasseHausnummer(String sep) throws myException{
		return this.get_ufs_kette(sep, true,  ADRESSE.strasse, ADRESSE.hausnummer);
	}

	
	
	public String get__FullNameAndAdress_flexible(String sep) throws myException{
		String c_ret = "";
		if (S.isFull(this.get_ufs_dbVal(ADRESSE.vorname))) {
			c_ret = this.get_ufs_kette(sep, true,  ADRESSE.vorname, ADRESSE.name1, ADRESSE.name3, ADRESSE.plz, ADRESSE.ort);
		} else {
			c_ret = this.get_ufs_kette(sep, true,  ADRESSE.name1, ADRESSE.name2, ADRESSE.name3, ADRESSE.plz, ADRESSE.ort);
		}

		if (this.getFirmeninfo().is_yes_db_val(FIRMENINFO.privat)) {
			c_ret = c_ret+" (privat) ";
		}
		return c_ret;
	}


	public String get__FullNameAndAdress_flexibleWithId(String sep) throws myException{
		String c_ret = "";
		if (S.isFull(this.get_ufs_dbVal(ADRESSE.vorname))) {
			c_ret = this.get_ufs_kette(sep, true,  ADRESSE.vorname, ADRESSE.name1, ADRESSE.name3, ADRESSE.plz, ADRESSE.ort);
		} else {
			c_ret = this.get_ufs_kette(sep, true,  ADRESSE.name1, ADRESSE.name2, ADRESSE.name3, ADRESSE.plz, ADRESSE.ort);
		}

		if (this.getFirmeninfo().is_yes_db_val(FIRMENINFO.privat)) {
			c_ret = c_ret+" (privat) ";
		}

		c_ret = c_ret+" (ID: "+this.get_fs_dbVal(ADRESSE.id_adresse)+")";
		return c_ret;
	}



	public String get__Name_flexible(String sep) throws myException{
		String c_ret = "";
		if (S.isFull(this.get_ufs_dbVal(ADRESSE.vorname))) {
			c_ret = this.get_ufs_kette(sep, true,  ADRESSE.vorname, ADRESSE.name1, ADRESSE.name2);
		} else {
			c_ret = this.get_ufs_kette(sep, true,  ADRESSE.name1, ADRESSE.name2, ADRESSE.name3);
		}
		return c_ret;
	}

	public String get__PLZ_Anschrift(String sep) throws myException{
		String c_ret = "";
		c_ret = this.get_ufs_kette(sep, true,  ADRESSE.plz, ADRESSE.ort);
		return c_ret;
	}

	
	
	public String getVornameName1OrtInfoZuFirma() throws myException {
		String c = "";
		c=this.get_ufs_kette(" ", ADRESSE.vorname, ADRESSE.name1,ADRESSE.ort);
		if (this.__is_main_adresse()) {
			c+=" (Hauptadresse)";
		} else if (this.__is_liefer_adresse()) {
			Rec21_adresse rl = this._getMainAdresse();
			c+=" (Lieferadresse von: "+rl.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.ort)+")";
		} else {
			throw new myException(this,"__get_name1_ort_info_zu_firma() is only possible on fonly possible on types FIRMENINFO/LIEFERADRESSE");
		}

		return c;
	}
	

	public PAIR<String, String> getVornameName1Name2OrtInfoZuFirma() throws myException {
		String c1 = "";
		String c2 = "";
		c1=this.get_ufs_kette(" ", ADRESSE.vorname, ADRESSE.name1, ADRESSE.name2,ADRESSE.ort);
		if (this.__is_main_adresse()) {
			c2=" (Hauptadresse)";
		} else if (this.__is_liefer_adresse()) {
			Rec21_adresse rl = this._getMainAdresse();
			c2=" (Lieferadresse von: "+rl.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.ort)+")";
		} else {
			throw new myException(this,"__get_name1_ort_info_zu_firma() is only possible on fonly possible on types FIRMENINFO/LIEFERADRESSE");
		}

		return new PAIR<String, String>()._setVal1(c1)._setVal2(c2);
	}
	
	public PAIR<String, String> getName1OrtInfoZuFirma() throws myException {
		String c1 = "";
		String c2 = "";
		c1=this.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.ort);
		if (this.__is_main_adresse()) {
			c2=" (HA)";
		} else if (this.__is_liefer_adresse()) {
			Rec21_adresse rl = this._getMainAdresse();
			c2=" (LA von: "+rl.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.ort)+")";
		} else {
			throw new myException(this,"__get_name1_ort_info_zu_firma() is only possible on fonly possible on types FIRMENINFO/LIEFERADRESSE");
		}

		return new PAIR<String, String>()._setVal1(c1)._setVal2(c2);
	}
	
	
	public String getName1Ort() throws myException {
		String c1 = "";
		c1=this.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.ort);

		return c1;
	}
	
	
	
	public boolean isPrivat() throws myException {
		return this.getFirmeninfo().is_yes_db_val(FIRMENINFO.privat);
	}

	public Rec21 getFirmeninfo() throws myException {
		return this._getMainAdresse().get_down_reclist21(FIRMENINFO.id_adresse, null, null).get(0);
	}

	public String get__FullNameAndAdress_mit_id() throws myException{
		String cRueck = this.get_ufs_kette(" ", true, ADRESSE.vorname, ADRESSE.name1, ADRESSE.name2, ADRESSE.name3, ADRESSE.ort);
		cRueck += "("+this.get_fs_dbVal(ADRESSE.id_adresse) + ")";
		return cRueck;
	}


	public String get_StandardTelefonNumber() throws myException{

		String cRueck = "";

		String cQuery = "select WERT_LAENDERVORWAHL,WERT_VORWAHL,WERT_RUFNUMMER,jt_kommunikation.IST_STANDARD from "+
				bibE2.cTO()+".jt_kommunikation,"+bibE2.cTO()+".jt_kommunikations_typ "+
				" where jt_kommunikation.id_kommunikations_typ=jt_kommunikations_typ.id_kommunikations_typ and "+
				"jt_kommunikations_typ.basistyp='TELEFON' and jt_kommunikation.id_adresse="+this.get_ufs_dbVal(ADRESSE.id_adresse);

		String[][] cErgebnis= bibDB.EinzelAbfrageInArray(cQuery,"");

		if (cErgebnis != null && cErgebnis.length>0)
		{
			cRueck = cErgebnis[0][0]+" "+cErgebnis[0][1]+" "+cErgebnis[0][2];
			for (int i=0;i<cErgebnis.length;i++)
			{
				if (cErgebnis[i][3].equals("Y"))
				{
					/*
					 * standard gefunden
					 */
					cRueck = cErgebnis[i][0]+" "+cErgebnis[i][1]+" "+cErgebnis[i][2];
				}
			}

		}

		return cRueck.trim();
	}



	public String get_StandardFaxNumber() throws myException{
		String cRueck = "";

		String cQuery = "select WERT_LAENDERVORWAHL,WERT_VORWAHL,WERT_RUFNUMMER,jt_kommunikation.IST_STANDARD from "+
				bibE2.cTO()+".jt_kommunikation,"+bibE2.cTO()+".jt_kommunikations_typ "+
				" where jt_kommunikation.id_kommunikations_typ=jt_kommunikations_typ.id_kommunikations_typ and "+
				"jt_kommunikations_typ.basistyp='TELEFAX' and jt_kommunikation.id_adresse="+this.get_ufs_dbVal(ADRESSE.id_adresse);

		String[][] cErgebnis= bibDB.EinzelAbfrageInArray(cQuery,"");

		if (cErgebnis != null && cErgebnis.length>0)
		{
			cRueck = cErgebnis[0][0]+" "+cErgebnis[0][1]+" "+cErgebnis[0][2];
			for (int i=0;i<cErgebnis.length;i++)
			{
				if (cErgebnis[i][3].equals("Y"))
				{
					/*
					 * standard gefunden
					 */
					cRueck = cErgebnis[i][0]+" "+cErgebnis[i][1]+" "+cErgebnis[i][2];
				}
			}

		}

		return cRueck.trim();
	}




	/**
	 * gibt einen info-Text zum Lager/adresse
	 * @return
	 * @throws myException 
	 */
	public String get_infoText() throws myException {
		String c_rueck = "";

		if (S.isFull(this.get_ufs_dbVal(ADRESSE.sonderlager))) {
			ENUM_SONDERLAGER lager = ENUM_SONDERLAGER.find_SonderlagerFromDBValue(this.get_ufs_dbVal(ADRESSE.sonderlager));
			if (lager!=null) {
				c_rueck = lager.user_text_lang();
			} else {
				c_rueck = "<undefined Sonderlager>";
			}
		} else if (this.__is_liefer_adresse()) {
			c_rueck = this.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2, ADRESSE.ort)+" (*)";
		} else {
			c_rueck = this.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2, ADRESSE.ort);
		}

		return c_rueck;
	}


	public boolean is_sonderlager() throws myException {
		return S.isFull(this.get_ufs_dbVal(ADRESSE.sonderlager));
	}


	/**
	 * 
	 * @return s oeffnungszeiten from adress or lieferadresse
	 * @throws myException
	 */
	public String getOeffnungszeiten() throws myException {
		if (this.__is_main_adresse()) {
			return this.getFirmeninfo().get_fs_dbVal(FIRMENINFO.oeffnungszeiten);
		} else {
			RecList21 rl = this.get_down_reclist21(LIEFERADRESSE.id_adresse_liefer, null, null);
			if (rl.size()!=1) {
				throw new myException(this, "id_adresse can only be once in reclist");
			} else {
				return rl.get(0).get_fs_dbVal(LIEFERADRESSE.oeffnungszeiten);
			}
		}
	}



	/**
	 * 
	 * @param artikel bezeichnung rec20
	 * @return true, when 
	 * @throws myException
	 */
	public boolean isAllowedArtBez(Rec20_artikel_bez ab) throws myException {
		boolean b = false;

		RecList21 rl_artbez_kundenspez = this.__get_main_adresse().get_down_reclist21(ARTIKELBEZ_LIEF.id_adresse, null, null);

		VEK<String> v = rl_artbez_kundenspez.getVEKString(((r)-> {return r.get_ufs_dbVal(ARTIKELBEZ_LIEF.id_artikel_bez);}));

		if (v.contains(ab.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez))) {
			b=true;
		}
		return b;
	}


	/**
	 * 
	 * @return s when adress or correlating main-adress is mandant
	 * @throws myException
	 */
	public boolean isAdressOfMandant() throws myException {
		return  this._getMainAdresse().getUfs(ADRESSE.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT());
	}



	public Rec21 getRec21Lager() throws myException {
		Rec21 rlager = null;

		if (this.__is_liefer_adresse()) {
			rlager = this.get_down_reclist21(LIEFERADRESSE.id_adresse_liefer, null, null).get(0);
		}

		return rlager;
	}


	public Rec21_adresse getRec21LagerDrittBesitzer() throws myException {
		if (this.__is_liefer_adresse()) {
			Rec21 rlager = this.get_down_reclist21(LIEFERADRESSE.id_adresse_liefer, null, null).get(0);
			if (rlager!=null) {   //duerfte eigentlich nie null sein
				Rec21 rDrittBesitz = rlager.get_up_Rec20(LIEFERADRESSE.id_adresse_besitz_lager,ADRESSE.id_adresse,true);
				if (rDrittBesitz!=null) {
					return new Rec21_adresse(rDrittBesitz);
				}
			}
		}
		return null;
	}


	public String get_String_4_Hashwert() throws myException {
		String rueck = this.get_ufs_kette("", true,  
				ADRESSE.name1, 
				ADRESSE.name2, 
				ADRESSE.name3, 
				ADRESSE.vorname, 
				ADRESSE.strasse, 
				ADRESSE.hausnummer, 
				ADRESSE.ort, 
				ADRESSE.ausweis_nummer);
		
		if(S.isFull(this.getUfs(ADRESSE.id_land))) {		
			Rec21 rec_land = new Rec21(_TAB.land)._fill_id(this.getUfs(ADRESSE.id_land));
			
			if(! (rec_land == null) && rec_land.is_ExistingRecord()) {
				rueck= rueck + (rec_land.get_ufs_dbVal(LAND.iso_country_code,""));
			}
		}
		return rueck;
	}


	/**
	 * Ermitteln aller Employee-Adressen, incl. der Hauptadresse
	 * @author manfred
	 * @date 26.11.2018
	 *
	 * @param includeAktiv
	 * @param includeInaktiv
	 * @return
	 * @throws myException
	 */
	public RecList21 getMainEmployeeAdresses(boolean includeAktiv, boolean includeInaktiv) throws myException{

		RecList21 vRueck = new RecList21(_TAB.adresse);

		vRueck._add(this._getMainAdresse());

		long hauptadresse = vRueck.get(0).get_PRIMARY_KEY_VALUE();

		if(hauptadresse>0) {
			SEL mitarbeiter_selection = 
					new SEL(MITARBEITER.fullTabName()+".*")
					.FROM(_TAB.adresse)
					.INNERJOIN(_TAB.mitarbeiter, ADRESSE.id_adresse, MITARBEITER.id_adresse_basis)
					.WHERE(new vgl(ADRESSE.id_adresse, "?" )) 
					.ORDERUP(MITARBEITER.id_mitarbeiter);
			
			SqlStringExtended sqlExt = new SqlStringExtended(mitarbeiter_selection.s());
			sqlExt.getValuesList().add(new Param_Long(hauptadresse));
			
			
			RecList21 reclist_mit = new RecList21(_TAB.mitarbeiter)._fill(sqlExt);
			for(Rec21 rec_mit:reclist_mit) {
				vRueck._add(rec_mit.get_up_Rec20(MITARBEITER.id_adresse_mitarbeiter,ADRESSE.id_adresse, false));
			}
		}

		return vRueck;
	}
	
	

	/**
	 * Ermitteln aller Lager incl. der Hauptadresse
	 * @author manfred
	 * @date 26.11.2018
	 *
	 * @return
	 * @throws myException
	 */
	public RecList21 getMainStoreAdresses() throws myException{

		RecList21 vRueck = new RecList21(_TAB.adresse);

		vRueck._add(this._getMainAdresse());

		long hauptadresse = vRueck.get(0).get_PRIMARY_KEY_VALUE();

		if(hauptadresse>0) {

			SEL lieferadresse_selection = 
					new SEL(LIEFERADRESSE.fullTabName()+".*")
					.FROM(_TAB.adresse)
					.INNERJOIN(_TAB.lieferadresse, ADRESSE.id_adresse, LIEFERADRESSE.id_adresse_basis)
					.WHERE( new TermLMR(ADRESSE.id_adresse.fn(), COMP.EQ.ausdruck(), "?") )
					.AND(LIEFERADRESSE.id_adresse_liefer,COMP.GE.ausdruck(), "1000")   // Manfred: wegen Sonderlager sinnvoll
					;


			SqlStringExtended sqlExt = new SqlStringExtended(lieferadresse_selection.s());
			sqlExt.getValuesList().add(new Param_Long(hauptadresse));
			
			RecList21 reclist_lief = new RecList21(_TAB.lieferadresse)._fill(sqlExt);
			for(Rec21 rec_lief:reclist_lief) {
				vRueck._add(rec_lief.get_up_Rec20(LIEFERADRESSE.id_adresse_liefer,ADRESSE.id_adresse, false));
			}
		}

		return vRueck;
	}

	
	
	//2018-10-10: fehlerkorrektur fuer Sebastien
	public RecList21 getMainEmployeeAndStoreAdresses() throws myException{

		RecList21 vRueck = new RecList21(_TAB.adresse);

		vRueck._add(this._getMainAdresse());

		long hauptadresse = vRueck.get(0).get_PRIMARY_KEY_VALUE();

		if(hauptadresse>0) {
			SEL mitarbeiter_selection = 
					new SEL(MITARBEITER.fullTabName()+".*")
					.FROM(_TAB.adresse)
					.INNERJOIN(_TAB.mitarbeiter, ADRESSE.id_adresse, MITARBEITER.id_adresse_basis)
					.WHERE(new vgl(ADRESSE.id_adresse, ""+hauptadresse))
					.ORDERUP(MITARBEITER.id_mitarbeiter);

			RecList21 reclist_mit = new RecList21(_TAB.mitarbeiter)._fill(mitarbeiter_selection.s());
			for(Rec21 rec_mit:reclist_mit) {
				vRueck._add(rec_mit.get_up_Rec20(MITARBEITER.id_adresse_mitarbeiter,ADRESSE.id_adresse, false));
			}

			SEL lieferadresse_selection = 
					new SEL(LIEFERADRESSE.fullTabName()+".*")
					.FROM(_TAB.adresse)
					.INNERJOIN(_TAB.lieferadresse, ADRESSE.id_adresse, LIEFERADRESSE.id_adresse_basis)
					.WHERE(new vgl(ADRESSE.id_adresse, ""+hauptadresse))
					.AND(LIEFERADRESSE.id_adresse_liefer,COMP.GE.ausdruck(), "1000")   // Manfred: wegen Sonderlager sinnvoll
					.ORDERUP(LIEFERADRESSE.id_lieferadresse);

			RecList21 reclist_lief = new RecList21(_TAB.lieferadresse)._fill(lieferadresse_selection.s());
			for(Rec21 rec_lief:reclist_lief) {
				vRueck._add(rec_lief.get_up_Rec20(LIEFERADRESSE.id_adresse_liefer,ADRESSE.id_adresse, false));
			}
		}

		return vRueck;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 13.01.2020
	 *
	 * @param onlyActiveLager
	 * @return Recist21 with mainAdresse (everytime and lager with considering onlyActiveLager)
	 * @throws myException
	 */
	public RecList21 getMainAndLagerAdresses(boolean onlyActiveLager) throws myException{
		RecList21 vRueck = new RecList21(_TAB.adresse);

		Rec21_adresse mainAdress = this._getMainAdresse();
		
		vRueck._add(mainAdress);

		Long hauptadresse = mainAdress.getIdLong();

		if(hauptadresse!=null) {
			SEL lieferadresse_selection = 
					new SEL(_TAB.lieferadresse)
					.FROM(_TAB.adresse)
					.INNERJOIN(_TAB.lieferadresse, ADRESSE.id_adresse, LIEFERADRESSE.id_adresse_basis)
					.WHERE(new vgl(ADRESSE.id_adresse, ""+hauptadresse))
					.AND(LIEFERADRESSE.id_adresse_liefer,COMP.GE.ausdruck(), "1000")   // Manfred: wegen Sonderlager sinnvoll
					.ORDERUP("JT_ADRESSE.ORT, JT_ADRESSE.NAME1, JT_ADRESSE.NAME2");

			RecList21 reclist_lief = new RecList21(_TAB.lieferadresse)._fill(lieferadresse_selection.s());
			for(Rec21 rec_lief:reclist_lief) {
				if ( (!onlyActiveLager) || rec_lief.get_up_Rec20(LIEFERADRESSE.id_adresse_liefer,ADRESSE.id_adresse, false).is_yes_db_val(ADRESSE.aktiv)) {
					vRueck._add(rec_lief.get_up_Rec20(LIEFERADRESSE.id_adresse_liefer,ADRESSE.id_adresse, false));
				}
			}
		}
		return vRueck;
	}

	/**
	 * generate a SHA-256 based on all information (name1, name2, name3, vorname, strasse, hausnummer, ort, ausweisnummer, iso_country_code)
	 * of all adresses ( main adress + coworker adress + depot adress)
	 * @author sebastien
	 * @return
	 * @throws myException
	 */
	public String generate_sha256_hashwert() throws myException{
		//step 1: get all adresses
		RecList21 adresse_list = this.getMainEmployeeAndStoreAdresses();

		//step 2: return
		StringBuffer rueck = new StringBuffer("");

		//step 3: iterate
		for(Rec21 adrRec : adresse_list) {
			rueck.append(new Rec21_adresse(adrRec).get_String_4_Hashwert());
		}

		//step 4: generate the SHA-256 hash
		String sha256_rueck = new SANKTION_Hashwert_SHA256_generator().generate_sha256_hashcode(rueck.toString());

		return sha256_rueck;
	}
	
	/**
	 * 
	 * @return name1 name2 - strasse hnr - laendercode-plz ort<br>
	 * example: <i>August Leber Rohstoffehandels GmbH - Am Güterbahnhof 22 - D-77652 Offenburg</i>
	 * @throws myException
	 */
	public String get_name_adresse_with_landcode() throws myException{
		String name = this.get_ufs_kette(" " , ADRESSE.name1, ADRESSE.name2);
		String anschrift = this.get_ufs_kette(" ", ADRESSE.strasse, ADRESSE.hausnummer);
		String plz_ort = this.get_ufs_kette(" ", ADRESSE.plz, ADRESSE.ort);
		String land_code = this.get_up_Rec21(ADRESSE.id_land, LAND.id_land, false).getUfs(LAND.laendercode);
		
		return name +" - " + anschrift + " - " + land_code +"-"+plz_ort;  
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 25.09.2019
	 *
	 * @return  Rec21 of LAND or null on empty id_land or error
	 */
	public Rec21 getRecLand() {
		if (this.recLand==null) {
			try {
				this.recLand = this.get_up_Rec21(LAND.id_land);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		return this.recLand;
	}
	
	
	public boolean isKunde() throws myException {
		return this.hasAttrib(ADRESSKLASSE_DEF.ist_kunde);
	}
	

	public boolean isLieferant() throws myException {
		return this.hasAttrib(ADRESSKLASSE_DEF.ist_lieferant);
	}

	
	private boolean hasAttrib(IF_Field f) throws myException {
		
		SEL s = new SEL("COUNT(*)").FROM(_TAB.adressklasse).INNERJOIN(_TAB.adressklasse_def, ADRESSKLASSE_DEF.id_adressklasse_def, ADRESSKLASSE.id_adressklasse_def)
				.WHERE(new vglParam(f))
				.AND(new vglParam(ADRESSKLASSE.id_adresse));
		SqlStringExtended  sql = new SqlStringExtended(s.s())._addParameters(new VEK<ParamDataObject>()
												._a(new Param_String("Y","Y"))
												._a(new Param_BigDecimal((BigDecimal)this.getRawVal(ADRESSE.id_adresse))));
		
		String[][] result = bibDB.EinzelAbfrageInArray(sql);
		if (result!=null && result.length==1) {
			if (result[0].length==1) {
				MyLong l = new MyLong(result[0][0]);
				if (l.isOK()) {
					return (l.get_iValue()>0);
				}
			}
		}
		
		throw new myException(this,"Error in Query !");
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 14.01.2019
	 *
	 * @param artbezTyp
	 * @return s RecList21 with artikelbez_lief-Rec21 from type artbezTyp (EK or VK)
	 */
	public RecList21 getAllowedArtikelBezLief(String artbezTyp ) {
		
		RecList21 abl = null;;
		try {
			And and = new And(new vgl(ARTIKELBEZ_LIEF.artbez_typ, artbezTyp));
			abl = this.get_down_reclist21(ARTIKELBEZ_LIEF.id_adresse,and.s(),null);
		} catch (myException e) {
			e.printStackTrace();
		}
		return abl;
		
	}
	

	/**
	 * gibt die aktuell aktiven Kreditversicherungsverträge zurück
	 * @author manfred
	 * @date 22.05.2019
	 *
	 * @return Reclist21 (KREDITVERSICHERUNG_KOPF)
	 * @throws myException 
	 */ 
	public RecList21 getKreditversicherungen_Aktiv() throws myException{
		
		RecList21 vRueck = new RecList21(_TAB.kreditvers_kopf);

		SEL sel_kv_kopf = 
				new SEL(KREDITVERS_KOPF.fullTabName()+".*")
				.FROM(_TAB.kreditvers_kopf)
				.INNERJOIN(_TAB.kreditvers_adresse, KREDITVERS_ADRESSE.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
				.WHERE(new TermLMR(KREDITVERS_ADRESSE.id_adresse.fn(),COMP.EQ.s(), "?" ))
					.AND(new vgl_YN("", KREDITVERS_KOPF.aktiv, true)) 
				.ORDERUP(KREDITVERS_KOPF.id_kreditvers_kopf);
		
		SqlStringExtended sqlExt = new SqlStringExtended(sel_kv_kopf.s());
		sqlExt.getValuesList().add(new Param_BigDecimal( (BigDecimal)this.getRawVal(ADRESSE.id_adresse) ) );
				
		vRueck._fill(sqlExt);

		return vRueck;

	}
	
	
	
	/**
	 * gibt alle Kreditversicherungsverträge zurück
	 * @author martin
	 * @date 2020-02-14
	 *
	 * @return Reclist21 (KREDITVERSICHERUNG_KOPF)
	 * @throws myException 
	 */ 
	public RecList21 getKreditversicherungenAktivUndInaktiv() throws myException{
		
		RecList21 vRueck = new RecList21(_TAB.kreditvers_kopf);

		SEL sel_kv_kopf = 
				new SEL(KREDITVERS_KOPF.fullTabName()+".*")
				.FROM(_TAB.kreditvers_kopf)
				.INNERJOIN(_TAB.kreditvers_adresse, KREDITVERS_ADRESSE.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
				.WHERE(new TermLMR(KREDITVERS_ADRESSE.id_adresse.fn(),COMP.EQ.s(), "?" ))
				.ORDERUP(KREDITVERS_KOPF.id_kreditvers_kopf);
		
		SqlStringExtended sqlExt = new SqlStringExtended(sel_kv_kopf.s());
		sqlExt.getValuesList().add(new Param_BigDecimal( (BigDecimal)this.getRawVal(ADRESSE.id_adresse) ) );
				
		vRueck._fill(sqlExt);

		return vRueck;

	}
	
	
	
	/**
	 * gibt die Tage der Fakturierungsfristverlängerung der KV zurück, die für einen Vertrag .
	 * Falls es mehrere Kreditverträge und Positionen gibt, wird der kleinste Wert zurückgegeben.
	 * Keine Fakturierungsfrist bedeutet 0 Tage
	 * Es werden auch inaktive Verträge berücksichtigt, da die Suche auch in die Vergangenheit geht.
	 * 
	 * @author manfred
	 * @date 22.05.2019
	 *
	 * @return int
	 * @throws myException 
	 */ 
	public int getMinimum_Fakturierungsfrist(Date Stichtag) throws myException{
		int frist = 0;

		SEL sel_kv_Frist = 
				new SEL("nvl(min(nvl(" +  KREDITVERS_KOPF.fakturierungsfrist + ",0)),0) ")
				.FROM(_TAB.kreditvers_kopf)
				.INNERJOIN(_TAB.kreditvers_adresse, KREDITVERS_ADRESSE.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
				.INNERJOIN(_TAB.kreditvers_pos, KREDITVERS_POS.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
				.WHERE(	new TermLMR(KREDITVERS_ADRESSE.id_adresse.fn(),COMP.EQ.s(), "?" ) ) 
					.AND( new TermLMR("trunc("+KREDITVERS_POS.gueltig_ab.tnfn() + ",'DD')",COMP.LE.s(),"trunc(?,'DD')" )   )
					.AND( new TermLMR("trunc(nvl("+KREDITVERS_POS.gueltig_bis.tnfn() + ",sysdate),'DD')",COMP.GE.s(),"trunc(?,'DD')" )   )
				.ORDER("1");
				;
		
		SqlStringExtended sqlExt = new SqlStringExtended(sel_kv_Frist.s());
		sqlExt.getValuesList().add(new Param_BigDecimal( (BigDecimal)this.getRawVal(ADRESSE.id_adresse) ) );
		sqlExt.getValuesList().add(new Param_Date(Stichtag));
		sqlExt.getValuesList().add(new Param_Date(Stichtag));

		
		String [][] values = this.oDB.EinzelAbfrageInArray(sqlExt);
		if (values.length > 0){
			String sDays = values[0][0];
			frist = Integer.parseInt(sDays);
			
		}
		return frist;
	}

	
	/**
	 * gibt die Liste der KV-Positionen der Firma zurück, die zum Stichtag vorhanden sind.
	 * @author manfred
	 * @date 28.05.2019
	 *
	 * @param Stichtag
	 * @return
	 * @throws myException
	 */
	public RecList21 getKreditVersicherungsPositionen(Date Stichtag) throws myException{
		RecList21 rlRet = null;

		SEL sel_kv_Frist = 
				new SEL(_TAB.kreditvers_pos)
				.FROM(_TAB.kreditvers_pos)
				.INNERJOIN(_TAB.kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf, KREDITVERS_POS.id_kreditvers_kopf)
				.INNERJOIN(_TAB.kreditvers_adresse, KREDITVERS_ADRESSE.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
				.WHERE(	new TermLMR(KREDITVERS_ADRESSE.id_adresse.fn(),COMP.EQ.s(), "?" ) ) 
					.AND( new TermLMR("trunc("+KREDITVERS_POS.gueltig_ab.tnfn() + ",'DD')",COMP.LE.s(),"trunc(?,'DD')" )   )
					.AND( new TermLMR("trunc(nvl("+KREDITVERS_POS.gueltig_bis.tnfn() + ",sysdate),'DD')",COMP.GE.s(),"trunc(?,'DD')" )   )
				.ORDER("1");
				;
		
		SqlStringExtended sqlExt = new SqlStringExtended(sel_kv_Frist.s());
		sqlExt.getValuesList().add(new Param_BigDecimal( (BigDecimal)this.getRawVal(ADRESSE.id_adresse) ) );
		sqlExt.getValuesList().add(new Param_Date(Stichtag));
		sqlExt.getValuesList().add(new Param_Date(Stichtag));


		rlRet = new RecList21(_TAB.kreditvers_pos)._fill(sqlExt);
		
		return rlRet;
	}
	
	
    
    public boolean is_lieferant() throws myException {
    	RecList21  rlAdressKlasseDef = new RecList21(_TAB.adressklasse_def)._fillWithAll();
    	
    	for (Rec21 def: rlAdressKlasseDef) {
    		if (def.is_yes_db_val(ADRESSKLASSE_DEF.ist_lieferant)) {
    			return this.belongsTo(def.getIdLong());
    		}
    	}
    	return false;
    }
    
    public boolean is_abnehmer() throws myException {
    	RecList21  rlAdressKlasseDef = new RecList21(_TAB.adressklasse_def)._fillWithAll();
    	
    	for (Rec21 def: rlAdressKlasseDef) {
    		if (def.is_yes_db_val(ADRESSKLASSE_DEF.ist_kunde)) {
    			return this.belongsTo(def.getIdLong());
    		}
    	}
    	return false;
    }

	
    public boolean belongsTo(Long idAdresseKlasseDef) throws myException {
    	if (idAdresseKlasseDef!=null) {
			RecList21 rl = this.get_down_reclist21(ADRESSKLASSE.id_adresse);
			for (Rec21 adressklasse: rl) {
				if (adressklasse.getLongDbValue(ADRESSKLASSE.id_adressklasse_def).longValue()==idAdresseKlasseDef.longValue()) {
					return true;
				}
			}
    	}
    	return false;
    }
    
	
	
	
    public RecList21 getKundenSpezifischeArtikelBez() throws myException {
    	SEL  sel = new SEL(_TAB.artikelbez_lief).FROM(_TAB.artikelbez_lief).WHERE(new vglParam(ARTIKELBEZ_LIEF.id_adresse));
    	
    	RecList21 rl = new RecList21(_TAB.artikelbez_lief)._fill(new SqlStringExtended(sel.s())._addParameter(new Param_Long(this.getId())));
    	
    	return rl;
    	
    }

    
    public RecList21 getPreise(Date date, boolean ekAngebot) throws myException {
    	SEL sel = new SEL(_TAB.vpos_std).FROM(_TAB.vpos_std)
    					.INNERJOIN(_TAB.vkopf_std, VKOPF_STD.id_vkopf_std, VPOS_STD.id_vkopf_std)
    					.INNERJOIN(_TAB.vpos_std_angebot, VPOS_STD_ANGEBOT.id_vpos_std, VPOS_STD.id_vpos_std)
    		.WHERE(new vglParam(VKOPF_STD.id_adresse))
    		.AND(new vgl_YN(VPOS_STD_ANGEBOT.deleted,false))
    		.AND(new vgl_YN(VPOS_STD.deleted,false))
    		.AND(new vglParam(VPOS_STD_ANGEBOT.gueltig_von,COMP.LE))
    		.AND(new vglParam(VPOS_STD_ANGEBOT.gueltig_bis,COMP.GE))
    		.AND(new vglParam(VKOPF_STD.vorgang_typ))
    		;
    			
    		
    	RecList21 rl = new RecList21(_TAB.vpos_std)._fill(new SqlStringExtended(sel.s())._addParameters(
    			new VEK<ParamDataObject>()
    			 ._a(new Param_Long(this.getIdLong()))
    			 ._a(new Param_Date(date))
    			 ._a(new Param_Date(date))
    			 ._a(new Param_String("", ekAngebot? myCONST.VORGANGSART_ABNAHMEANGEBOT: myCONST.VORGANGSART_ANGEBOT ))
    			 ));
    	
    	
    	return rl;
    			
    }
    

    /**
     * 
     * @author martin
     * @date 02.03.2020
     *
     * @return Rec21 waehrung or null on error
     */
    public Rec21 getWaehrung() {
    	Rec21 waehrung = null;
		try {
			if (!this.__is_main_adresse()) {
				waehrung = this._getMainAdresse().get_up_Rec21(WAEHRUNG.id_waehrung);			
			} else {
				waehrung = this.get_up_Rec21(WAEHRUNG.id_waehrung);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return waehrung;
    }
    
    
    
    
    
	public Rec21_Kommunikation getStandardTelefonNummerLagerOrMain() throws myException{
		
		RecList21  rlKommunikation = this.get_down_reclist21(KOMMUNIKATION.id_adresse);
		RecList21  rlTelefon = rlKommunikation.get_filtered_list((rec)-> {
			Rec21 rectyp = ((Rec21)rec).get_up_Rec21(KOMMUNIKATIONS_TYP.id_kommunikations_typ);
			return (rectyp!=null && rectyp.getUfs(KOMMUNIKATIONS_TYP.basistyp).toUpperCase().trim().equals("TELEFON"));
		});
		RecList21 rlTelefonStd = rlTelefon.get_filtered_list((r)-> {
			return r.is_yes_db_val(KOMMUNIKATION.ist_standard);
		});
		
		Rec21_Kommunikation telefon = null;
		if (rlTelefonStd.size()>0) {
			telefon = new Rec21_Kommunikation(rlTelefonStd.get(0));
		} else {
			if (rlTelefon.size()>0) {
				telefon =  new Rec21_Kommunikation(rlTelefon.get(0));
			}
		}
		if (telefon == null && this.__is_liefer_adresse())  {
			telefon = this._getMainAdresse().getStandardTelefonNummerLagerOrMain();
		}
		
		return telefon;
	}



	public Rec21_Kommunikation getStandardTelefaxNummerLagerOrMain() throws myException{
		
		RecList21  rlKommunikation = this.get_down_reclist21(KOMMUNIKATION.id_adresse);
		RecList21  rlTelefax = rlKommunikation.get_filtered_list((rec)-> {
			Rec21 rectyp = ((Rec21)rec).get_up_Rec21(KOMMUNIKATIONS_TYP.id_kommunikations_typ);
			return (rectyp!=null && rectyp.getUfs(KOMMUNIKATIONS_TYP.basistyp).toUpperCase().trim().equals("TELEFAX"));
		});
		RecList21 rlTelefonStd = rlTelefax.get_filtered_list((r)-> {
			return r.is_yes_db_val(KOMMUNIKATION.ist_standard);
		});
		
		Rec21_Kommunikation telefax = null;
		if (rlTelefonStd.size()>0) {
			telefax = new Rec21_Kommunikation(rlTelefonStd.get(0));
		} else {
			if (rlTelefax.size()>0) {
				telefax = new Rec21_Kommunikation(rlTelefax.get(0));
			}
		}
		if (telefax == null && this.__is_liefer_adresse())  {
			telefax = this._getMainAdresse().getStandardTelefaxNummerLagerOrMain();
		}
		
		return telefax;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 09.03.2020
	 *
	 * @return s ustlkz/ust-id-pair of mainadresse, null if empty
	 */
    public Pair<String> getUstLkzUstIdBasis() {
    	Pair<String> ret = null;
    	
    	try {
			Rec21 recFirmenInfo = this._getMainAdresse().get_down_reclist21(FIRMENINFO.id_adresse, null, null).get(0);
			
			ret = new Pair<String>()._setVal1(recFirmenInfo.getFs(FIRMENINFO.umsatzsteuerlkz, ""))._setVal2(recFirmenInfo.getFs(FIRMENINFO.umsatzsteuerid, ""));
			if (S.isAllEmpty(ret.getVal1(),ret.getVal2())) {
				ret = null;
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
    	

    	
    	
    	return ret;
    }
    
    
    /**
     * liefert das LKZ  die UST-ID und das der Adresse.
     * Pair(<LKZ>,<UST-ID>)
     */
    public Pair<String> getUstLkzUstId(){
    	Pair<String> ret = new Pair<String>();
    	Pair<String> ust = new Pair<String>();

    	HashMap<Long, Pair<String>> hmUST_LKZ_ID = new HashMap<Long, Pair<String>>();
    	
    	try {
    		if (__is_main_adresse()) {
    			Rec21 recFirmenInfo = this.get_down_reclist21(FIRMENINFO.id_adresse, null, null).get(0) ;
    			ust._setVal1(recFirmenInfo.get_ufs_dbVal(FIRMENINFO.umsatzsteuerlkz, ""));
    			ust._setVal2(recFirmenInfo.get_ufs_dbVal(FIRMENINFO.umsatzsteuerid,""));
    			hmUST_LKZ_ID.put(get_raw_resultValue_Long(ADRESSE.id_land), ust);
    			
    		} else {
    			Rec21 recHauptFirma = _getMainAdresse();
    			Rec21 recFirmenInfoHaupt = recHauptFirma.get_down_reclist21(FIRMENINFO.id_adresse, null, null).get(0) ;
    			
    			ust = new Pair<String>(recFirmenInfoHaupt.get_ufs_dbVal(FIRMENINFO.umsatzsteuerlkz, ""),recFirmenInfoHaupt.get_ufs_dbVal(FIRMENINFO.umsatzsteuerid,""));
    			hmUST_LKZ_ID.put(recHauptFirma.get_raw_resultValue_Long(ADRESSE.id_land), ust);
    			
    			// eine Hashmap aller kombinationen von Länder und UST-IDs der Firma
    			RecList21 rlUST = recHauptFirma.get_down_reclist21(ADRESSE_UST_ID.id_adresse, "", "");
    			for (Rec21 recUst : rlUST) {
    				ust = new Pair<String>(recUst.get_ufs_dbVal(ADRESSE_UST_ID.umsatzsteuerlkz, ""), recUst.get_ufs_dbVal(ADRESSE_UST_ID.umsatzsteuerid, ""));
    				hmUST_LKZ_ID.put(recUst.get_raw_resultValue_Long(ADRESSE_UST_ID.id_land),ust);
    			}
    		}
    		
    		ret = hmUST_LKZ_ID.get(get_raw_resultValue_Long(ADRESSE.id_land));
    		
    		
		} catch (myException e) {
			e.printStackTrace();
		}
    	

    	
    	
    	return ret;
    }
       
   
    
    public boolean isGutschriftenSperren() throws myException {
    	return this.is_yes_db_val(ADRESSE.gutschriften_sperren);
    }
    
    
    public boolean isRechnungenSperren() throws myException {
    	return this.is_yes_db_val(ADRESSE.rechnungen_sperren);
    }
    
    
}
 