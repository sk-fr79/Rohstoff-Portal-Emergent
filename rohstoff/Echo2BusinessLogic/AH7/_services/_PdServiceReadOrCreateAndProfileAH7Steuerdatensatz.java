/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceAH7Profiler.COUNTERS;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Steuerdatei;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * klasse, um ein profil, was noch nicht vorhanden ist, zu erzeugen und gleich zu bewerten
 */
public class _PdServiceReadOrCreateAndProfileAH7Steuerdatensatz {

	private String 		id_ah7_steuerdatei_found_or_created = null;
	private boolean  	relationNew = false;
	
//	private String idAdresseGeoStart = null;
//	private String idAdresseGeoZiel = null;
	
	public HashMap<COUNTERS, Integer>  hmCounters = new HashMap<_PdServiceAH7Profiler.COUNTERS, Integer>();
	public HashMap<String, String>     hmIdsAndProfiles = new HashMap<String, String>(); 				//merkt sich die IDs, die bewertet wurden und die automatisch erzeugten profile 

	
	/**
	 * @param p_idAdresseGeoStart
	 * @param p_idAdresseGeoZiel
	 * @throws myException 
	 */
	public _PdServiceReadOrCreateAndProfileAH7Steuerdatensatz(String p_idAdresseGeoStart, String p_idAdresseGeoZiel, MyE2_MessageVector mv) throws myException {
		super();

		this.hmCounters.clear();
		this.hmIdsAndProfiles.clear();
		
		if (S.isAllFull(p_idAdresseGeoStart,p_idAdresseGeoZiel)) {
			Rec21_adresse a_s = new Rec21_adresse()._fill_id(p_idAdresseGeoStart);
			Rec21_adresse a_z = new Rec21_adresse()._fill_id(p_idAdresseGeoZiel);
			
			SEL sel = new SEL("*").FROM(_TAB.ah7_steuerdatei)	
					.WHERE(new vgl(AH7_STEUERDATEI.id_adresse_geo_start,p_idAdresseGeoStart))
					.AND(new vgl(AH7_STEUERDATEI.id_adresse_geo_ziel,p_idAdresseGeoZiel))
					;

			RecList21 rl_AH7_steuerdatei = new RecList21(_TAB.ah7_steuerdatei)._fill(sel.s());
			
			if (rl_AH7_steuerdatei.size()==0) {
				Rec21 rec_ah7_steuerdatei =this._createAH7_steuerdatei_record(mv, a_s, a_z).getRec21LastRead();
				if (rec_ah7_steuerdatei==null) {
					throw new myException(this,"Error: no Record present!");
				}
				this.id_ah7_steuerdatei_found_or_created = rec_ah7_steuerdatei.get_key_value();
				this.relationNew = true;
				OwnServiceAH7Profiler pf = new OwnServiceAH7Profiler(mv);
				this.hmCounters.putAll(pf.hmCounters);
				this.hmIdsAndProfiles.putAll(pf.hmIdsandProfiles);
			} else if (rl_AH7_steuerdatei.size()==1) {
				Rec21 rec = new Rec21_AH7_Steuerdatei(a_s,a_z)._SAVE(true, mv)._rebuildRecord().getRec21LastRead();
				if (rec==null) {
					throw new myException(this,"Error: no Record present!");
				}
				this.id_ah7_steuerdatei_found_or_created = rec.get_key_value();
				OwnServiceAH7Profiler pf = new OwnServiceAH7Profiler(mv);
				this.hmCounters.putAll(pf.hmCounters);
				this.hmIdsAndProfiles.putAll(pf.hmIdsandProfiles);
			} else {
				throw new myException(this,"Error: only one Record allowed !");
			}
			
		}		
		
	}
	
	
	private Rec21 _createAH7_steuerdatei_record(MyE2_MessageVector mv, Rec21_adresse  a_s,	Rec21_adresse  a_z) throws myException {
		return new Rec21(_TAB.ah7_steuerdatei)
		._nv(AH7_STEUERDATEI.id_adresse_geo_start, 	a_s.get_ufs_dbVal(ADRESSE.id_adresse), mv)
		._nv(AH7_STEUERDATEI.id_adresse_geo_ziel,  	a_z.get_ufs_dbVal(ADRESSE.id_adresse), mv)
		._nv(AH7_STEUERDATEI.id_adresse_jur_start, 	a_s.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse), mv)
		._nv(AH7_STEUERDATEI.id_adresse_jur_ziel, 	a_z.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse), mv)
		._nv(AH7_STEUERDATEI.drucke_blatt2, "N", mv)
		._nv(AH7_STEUERDATEI.drucke_blatt3, "N", mv)
		._nv(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.UNDEF.db_val(), mv)
		._SAVE(true, mv);
	}
	
	
	/**
	 * profiling one relation
	 * @author martin
	 *
	 */
	private class OwnServiceAH7Profiler extends _PdServiceAH7Profiler {

		/**
		 * @param mv
		 * @throws myException
		 */
		public OwnServiceAH7Profiler(MyE2_MessageVector mv) throws myException {
			super(null, mv, true);
		}

		@Override
		public Vector<Rec21_AH7_Steuerdatei> get_vIds__AH_7_steuerdatei_to_classify() throws myException {
			Vector<Rec21_AH7_Steuerdatei> v = new Vector<>();
			Rec21_AH7_Steuerdatei rs = new Rec21_AH7_Steuerdatei()._fill_id(_PdServiceReadOrCreateAndProfileAH7Steuerdatensatz.this.id_ah7_steuerdatei_found_or_created);
			v.add(rs);
			return v;
		}
	}

	
	
	public String get_id_ah7_steuerdatei_found_or_created() {
		return id_ah7_steuerdatei_found_or_created;
	}


	/**
	 * wurde eine relation angelegt, dann wird dieser wert true, bei neubewertung false
	 * @return
	 */
	public boolean isRelationNew() {
		return relationNew;
	}
	
	
}
