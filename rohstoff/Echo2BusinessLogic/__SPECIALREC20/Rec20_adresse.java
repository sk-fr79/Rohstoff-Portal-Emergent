package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.MITARBEITER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;

public class Rec20_adresse extends Rec20 {


	public Rec20_adresse() throws myException {
		super(_TAB.adresse);
	}


	public Rec20_adresse(Rec20 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab() != _TAB.adresse) {
			throw new myException(this,"Only Record from type ADRESSE are allowed !");
		}
	}


	//fill-methodes
	@Override
	public Rec20_adresse _fill(Rec20 baseRec) throws myException {
		super._fill(baseRec);
		return this;
	}
	
	@Override
	public Rec20_adresse _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	@Override
	public Rec20_adresse _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}
	
	@Override
	public Rec20_adresse _fill_sql(String sql) throws myException {
		super._fill_sql(sql);
		return this;
	}
	
	@Override
	public Rec20_adresse _fill(MyRECORD_IF_RECORDS rec) throws myException {
		super._fill(rec);
		return this;
	}
	
	public boolean __is_main_adresse() throws myException {
		return this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_FIRMENINFO);
	}

	public boolean __is_liefer_adresse() throws myException {
		return this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_LIEFERADRESSE);
	}

	public Rec20_adresse __get_main_adresse() throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"__get_main_adresse() is only possible on filled rec20-object");
		}

		if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_FIRMENINFO)) {
			return this;
		} else if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_LIEFERADRESSE)) {
			Rec20 		recAdresseBasis = this.get_down_reclist20(LIEFERADRESSE.id_adresse_liefer,null,null).get(0).get_up_rec20(LIEFERADRESSE.id_adresse_basis,ADRESSE.id_adresse,true);
			return new Rec20_adresse(recAdresseBasis);
		} else {
			throw new myException(this,"__get_main_adresse() is only possible on types FIRMENINFO/LIEFERADRESSE");
		}
	}

	
	public Rec20_adresse _getMainAdresse() throws myException {
		if (this.is_newRecordSet()) {
			throw new myException(this,"_getMainAdresse() is only possible on filled rec20-object");
		}

		if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_FIRMENINFO)) {
			return this;
		} else if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_LIEFERADRESSE)) {
			Rec20 		recAdresseBasis = this.get_down_reclist20(LIEFERADRESSE.id_adresse_liefer,null,null).get(0).get_up_Rec20(LIEFERADRESSE.id_adresse_basis,ADRESSE.id_adresse,true);
			return new Rec20_adresse(recAdresseBasis);
		}  else if (this.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_MITARBEITER)) {
			Rec20 		recAdresseBasis = this.get_down_reclist20(MITARBEITER.id_adresse_mitarbeiter,null,null).get(0).get_up_Rec20(MITARBEITER.id_adresse_basis,ADRESSE.id_adresse,true);
			return new Rec20_adresse(recAdresseBasis);
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
			Rec20_adresse rl = this.__get_main_adresse();
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


	public boolean isPrivat() throws myException {
		return this.getFirmeninfo().is_yes_db_val(FIRMENINFO.privat);
	}
	
	public Rec20 getFirmeninfo() throws myException {
		return this.__get_main_adresse().get_down_reclist20(FIRMENINFO.id_adresse, null, null).get(0);
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
			RecList20 rl = this.get_down_reclist20(LIEFERADRESSE.id_adresse_liefer, null, null);
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
		
		RecList20 rl_artbez_kundenspez = this.__get_main_adresse().get_down_reclist20(ARTIKELBEZ_LIEF.id_adresse, null, null);
		
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
	
	
}
