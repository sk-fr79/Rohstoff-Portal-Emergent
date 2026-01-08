package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7__ENUM_TYPEFUHRE;
import rohstoff.Echo2BusinessLogic.AH7.RELATION.AH7__hmTranslateProfileToRealAdressIds;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceCheckAH7SteuerrelationIsConsistent;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceCheckAH7ProfileIsFittingToAH7Steuerrelation;

public class Rec21_AH7_Steuerdatei extends Rec21 {

	
	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_AH7_Steuerdatei() throws myException {
		super(_TAB.ah7_steuerdatei);
	}

	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec21_AH7_Steuerdatei(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	
	/**
	 * creates Rec21_AH7_Steuerdatei and fills with result, when not existing unique returns an empty Rec21_AH7_Steuerdatei
	 * @param geoStart
	 * @param geoZiel
	 * @throws myException
	 */
	public Rec21_AH7_Steuerdatei(Rec21_adresse geoStart, Rec21_adresse geoZiel) throws myException {
		this();
		
		SEL sel = new SEL("*").FROM(_TAB.ah7_steuerdatei)	.WHERE(new vgl(AH7_STEUERDATEI.id_adresse_geo_start,geoStart.get_ufs_dbVal(ADRESSE.id_adresse)))
				.AND(new vgl(AH7_STEUERDATEI.id_adresse_geo_ziel,geoZiel.get_ufs_dbVal(ADRESSE.id_adresse)))
				;

		RecList21 reclistAH7 = new RecList21(_TAB.ah7_steuerdatei)._fill(sel.s());

		if (reclistAH7.size()==1) {
			this._fill(reclistAH7.get(0));
		}
		
		
	}
	
	
	/**
	 * creates Rec21_AH7_Steuerdatei and fills with result, when not existing unique returns an empty Rec21_AH7_Steuerdatei
	 * 
	 * @param id_adresse_geoStart_uf
	 * @param id_adresse_geoZiel_uf
	 * @throws myException
	 */
	public Rec21_AH7_Steuerdatei(String id_adresse_geoStart_uf, String id_adresse_geoZiel_uf) throws myException {
		this();
		
		SEL sel = new SEL("*").FROM(_TAB.ah7_steuerdatei)	.WHERE(new vgl(AH7_STEUERDATEI.id_adresse_geo_start,id_adresse_geoStart_uf))
				.AND(new vgl(AH7_STEUERDATEI.id_adresse_geo_ziel,id_adresse_geoZiel_uf))
				;

		RecList21 reclistAH7 = new RecList21(_TAB.ah7_steuerdatei)._fill(sel.s());

		if (reclistAH7.size()==1) {
			this._fill(reclistAH7.get(0));
		}
	}
	

	
	
	@Override
	public Rec21_AH7_Steuerdatei _fill_id(String id) throws myException {
		super._fill_id(id);
		return this;
	}

	@Override
	public Rec21_AH7_Steuerdatei _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	
	
	public AH7__ENUM_TYPEFUHRE getType() throws myException {
		if (this.get_myLong_dbVal(AH7_STEUERDATEI.id_adresse_jur_start).get_lValue()==new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_lValue() && 
			this.get_myLong_dbVal(AH7_STEUERDATEI.id_adresse_jur_ziel).get_lValue()==new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_lValue()) {
			return AH7__ENUM_TYPEFUHRE.LL;
		} 
		
		if (this.get_myLong_dbVal(AH7_STEUERDATEI.id_adresse_jur_start).get_lValue()==new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_lValue() && 
				this.get_myLong_dbVal(AH7_STEUERDATEI.id_adresse_jur_ziel).get_lValue()!=new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_lValue()) {
				return AH7__ENUM_TYPEFUHRE.WA;
		} 

		if (this.get_myLong_dbVal(AH7_STEUERDATEI.id_adresse_jur_start).get_lValue()!=new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_lValue() && 
				this.get_myLong_dbVal(AH7_STEUERDATEI.id_adresse_jur_ziel).get_lValue()==new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_lValue()) {
				return AH7__ENUM_TYPEFUHRE.WE;
		} 
		
		if (this.get_myLong_dbVal(AH7_STEUERDATEI.id_adresse_jur_start).get_lValue()!=new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_lValue() && 
				this.get_myLong_dbVal(AH7_STEUERDATEI.id_adresse_jur_ziel).get_lValue()!=new MyLong(bibALL.get_ID_ADRESS_MANDANT()).get_lValue()) {
				return AH7__ENUM_TYPEFUHRE.ST;
		} 
		throw new myException();  //kann nicht sein
	}
	
	
	/**
	 * prueft die aktuelle relation gegen das zu matchende profil
	 * @param recAH7_Profil
	 * @return
	 * @throws myException 
	 */
	public boolean  isAH7ProfileMatching(Rec21 recAH7_Profil) throws myException {
		return new _PdServiceCheckAH7ProfileIsFittingToAH7Steuerrelation().isFitting(	recAH7_Profil, 
																				this.get_raw_resultValue_Long(AH7_STEUERDATEI.id_adresse_geo_start),
																				this.get_raw_resultValue_Long(AH7_STEUERDATEI.id_adresse_geo_ziel));
	}
	
	


	public MyE2_MessageVector  writeProfilingResults(Rec21 recProfile, boolean suppressActivation) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		HashMap<IF_Field, String> hmValuesForAH7Steuerdatei = new AH7__hmTranslateProfileToRealAdressIds(this.get_ufs_dbVal(AH7_STEUERDATEI.id_adresse_geo_start), this.get_ufs_dbVal(AH7_STEUERDATEI.id_adresse_geo_ziel), recProfile, mv);
		
		//falls das profil den status "automatisch aktivieren" besitzt, aber das unterdrueckt wird, dann auf inaktiv schalten
		if (hmValuesForAH7Steuerdatei.get(AH7_STEUERDATEI.status_relation).equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val()) && suppressActivation) {
			hmValuesForAH7Steuerdatei.put(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.INACTIVE.db_val());
		}
		
		for (IF_Field f: hmValuesForAH7Steuerdatei.keySet()) {
			this._nv(f, hmValuesForAH7Steuerdatei.get(f), mv);
		}
		this._SAVE(true, mv);
		return mv;
	}

	
	
	private boolean isBlock1Full() throws myException {
		return (S.isAllFull(
				this.getUfs(AH7_STEUERDATEI.id_1_abfallerzeuger)
				,this.getUfs(AH7_STEUERDATEI.id_1_import_empfaenger)
				,this.getUfs(AH7_STEUERDATEI.id_1_verbr_veranlasser)
				,this.getUfs(AH7_STEUERDATEI.id_1_verwertungsanlage)
						));
	}
	
	private boolean isBlock2Correct() throws myException {
		if (this.is_yes_db_val(AH7_STEUERDATEI.drucke_blatt2)) {
			return S.isAllFull(
							this.getUfs(AH7_STEUERDATEI.id_2_abfallerzeuger)
							,this.getUfs(AH7_STEUERDATEI.id_2_import_empfaenger)
							,this.getUfs(AH7_STEUERDATEI.id_2_verbr_veranlasser)
							,this.getUfs(AH7_STEUERDATEI.id_2_verwertungsanlage)
							);
		} else {
			return S.isAllEmpty(
					this.getUfs(AH7_STEUERDATEI.id_2_abfallerzeuger)
					,this.getUfs(AH7_STEUERDATEI.id_2_import_empfaenger)
					,this.getUfs(AH7_STEUERDATEI.id_2_verbr_veranlasser)
					,this.getUfs(AH7_STEUERDATEI.id_2_verwertungsanlage)
					);
			
		}
		
	}

	
	private boolean isBlock3Correct() throws myException {
		if (this.is_yes_db_val(AH7_STEUERDATEI.drucke_blatt3)) {
			return S.isAllFull(
							this.getUfs(AH7_STEUERDATEI.id_3_abfallerzeuger)
							,this.getUfs(AH7_STEUERDATEI.id_3_import_empfaenger)
							,this.getUfs(AH7_STEUERDATEI.id_3_verbr_veranlasser)
							,this.getUfs(AH7_STEUERDATEI.id_3_verwertungsanlage)
							);
		} else {
			return S.isAllEmpty(
					this.getUfs(AH7_STEUERDATEI.id_3_abfallerzeuger)
					,this.getUfs(AH7_STEUERDATEI.id_3_import_empfaenger)
					,this.getUfs(AH7_STEUERDATEI.id_3_verbr_veranlasser)
					,this.getUfs(AH7_STEUERDATEI.id_3_verwertungsanlage)
					);
			
		}
		
	}


	/**
	 * 
	 * @return true, when AH7_steuerdatei - record ist existing and valid and active: ready to use
	 */
	public boolean isPresentAndReadyToUse(MyE2_MessageVector mv_call) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		boolean ret = false;

		if (this.is_ExistingRecord() && this.isBlock1Full()&&this.isBlock2Correct()&&this.isBlock3Correct()) {
			_PdServiceCheckAH7SteuerrelationIsConsistent consistCheck = new _PdServiceCheckAH7SteuerrelationIsConsistent();
			ret = consistCheck.isAH7_steuersatzIsConsistent(this, mv);
			if (mv.get_bHasAlarms()) {
				ret = false;
			}
			
			if (ret) {
				ret = (this.getUfs(AH7_STEUERDATEI.status_relation).equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val()));
			}
			
		}
		mv_call.add_MESSAGE(mv);
		
		return ret;
	}
	
	
}
