/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 08.02.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;

import panter.gmbh.BasicInterfaces.Service.PdServiceBewertungAbrechnungsStatus;
import panter.gmbh.BasicInterfaces.Service.PdServiceBewertungAbrechnungsStatus.ENUM_STATUS_UEBERGANG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.EN_VEKTOR_STATUS;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 08.02.2019
 *
 */
public class Rec21_bgVector extends Rec21 {


	
	
	public Rec21_bgVector() throws myException {
		super(_TAB.bg_vektor);
	}

	public Rec21_bgVector(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	
	
	public HMAP<RB_KM, Rec21> getCompleteStackOfRecords() {
       try {
    	   return getCompleteStackOfRecs();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 
	 * @author martin
	 * @date 03.03.2020
	 *
	 * @return s all rec21 or emtpy hashMap
	 * @throws Exception
	 */
	public  HMAP<RB_KM, Rec21> getCompleteStackOfRecs() throws Exception {
		HMAP<RB_KM, Rec21> hm = new HMAP<>();

		RecList21  rl_atome =	this.get_down_reclist21(BG_ATOM.id_bg_vektor);
		
		if (rl_atome.size()==2) {
			Rec21 ra0 = rl_atome.get(0);
			Rec21 ra1 = rl_atome.get(1);
			
			if (ra0.getUfs(BG_ATOM.pos_in_mask).equals(EnTabKeyInMask.A1.dbVal()) && ra1.getUfs(BG_ATOM.pos_in_mask).equals(EnTabKeyInMask.A2.dbVal())) {
				hm._put(RecA1.key, ra0)._put(RecA2.key, ra1);
			} else if (ra0.getUfs(BG_ATOM.pos_in_mask).equals(EnTabKeyInMask.A2.dbVal()) && ra1.getUfs(BG_ATOM.pos_in_mask).equals(EnTabKeyInMask.A1.dbVal())) {
				hm._put(RecA2.key, ra0)._put(RecA1.key, ra1);
			} else {
				throw new myException("Must a pair of EnStationPos.A1 and EnStationPos.A2 !!");
			}
			
			hm.put(RecS1.key, hm.get(RecA1.key).get_up_Rec21(BG_ATOM.id_bg_station_quelle, BG_STATION.id_bg_station, true));
			hm.put(RecS2.key, hm.get(RecA1.key).get_up_Rec21(BG_ATOM.id_bg_station_ziel,   BG_STATION.id_bg_station, true));
			hm.put(RecS3.key, hm.get(RecA2.key).get_up_Rec21(BG_ATOM.id_bg_station_ziel,   BG_STATION.id_bg_station, true));

			hm.put(RecV.key, this);
		} else {
			hm.clear();
			throw new myException("Must be 2 Rec21 of type ATOM !!<11d0485c-5d30-11ea-bc55-0242ac130003>");
		}

		return hm;
	}
	
	
	public MyE2_MessageVector checkStornable() {
		HMAP<RB_KM, Rec21> mapOfRecs = getCompleteStackOfRecords();
		MyE2_MessageVector mv = bibMSG.newMV();
		try {
			
			if (this.getBigDecimalDbValue(BG_VEKTOR.planmenge)!=null && this.getBigDecimalDbValue(BG_VEKTOR.planmenge).compareTo(BigDecimal.ZERO)>0 ) {
				mv._addAlarm(S.ms("Mindestens eine der zu stronierenden Warenbewegungen besitzt bereits eine Planmenge >0 !"));
			}
			
			
			if (mapOfRecs.get(RecA1.key).getBigDecimalDbValue(BG_ATOM.menge)!=null) {
				mv._addAlarm(S.ms("Mindestens eine der zu stronierenden Warenbewegungen hat in der Quell-Position ist bereits eine Menge erfaßt !"));
			}
			if (mapOfRecs.get(RecA2.key).getBigDecimalDbValue(BG_ATOM.menge)!=null) {
				mv._addAlarm(S.ms("Mindestens eine der zu stronierenden Warenbewegungen hat in der Ziel-Position ist bereits eine Menge erfaßt !"));
			}
			if (mapOfRecs.get(RecA1.key).getBigDecimalDbValue(BG_ATOM.e_preis_basiswaehrung)!=null || mapOfRecs.get(RecA1.key).getBigDecimalDbValue(BG_ATOM.e_preis_fremdwaehrung)!=null) {
				mv._addAlarm(S.ms("Mindestens eine der zu stronierenden Warenbewegungen hat in der Quell-Position ist bereits einen Preis erfaßt !"));
			}
			if (mapOfRecs.get(RecA2.key).getBigDecimalDbValue(BG_ATOM.e_preis_basiswaehrung)!=null || mapOfRecs.get(RecA2.key).getBigDecimalDbValue(BG_ATOM.e_preis_fremdwaehrung)!=null) {
				mv._addAlarm(S.ms("Mindestens eine der zu stronierenden Warenbewegungen hat in der Quell-Position ist bereits einen Preis erfaßt !"));
			}
			
		} catch (myException e) {
			e.printStackTrace();
			mv._addAlarm(S.ms("Unbekannter Fehler: eaa162d8-2f91-11e9-b210-d663bd873d93"));
		}
		
		return mv;
	}
	
	public MyE2_MessageVector checkDeletable() {
		
		if (S.isEmpty(this.getUfs("", BG_VEKTOR.id_bg_storno_info))) {
			return bibMSG.newMV()._addAlarm(S.ms("Warenbewegungen müssen vor dem Löschen storniert werden !"));
		}
		return bibMSG.getNewMV();
	}

	
	public boolean isDeleted() throws myException {
		return S.isFull(this.getUfs(BG_VEKTOR.id_bg_del_info));
	}
	
	
	public boolean isStorniert() throws myException {
		return S.isFull(this.getUfs(BG_VEKTOR.id_bg_storno_info));
	}
	

	public EnTransportTyp getTransportTyp() {
		EnTransportTyp typ = null;
		
		try {
			String s_typ = this.getUfs(BG_VEKTOR.en_transport_typ);
			if (S.isFull(s_typ)) {
				typ = EnTransportTyp.WA.getEnum(s_typ);
			}
			
			
		} catch (myException e) {
			e.printStackTrace();
		}
		return typ;
	}
	
	
	public boolean isNative() throws myException {
		if (this.is_ExistingRecord()) {
			return this.getUfs(BG_VEKTOR.en_vektor_quelle,"").equals(EN_VEKTOR_QUELLE.NATIV.dbVal());
		} else {
			throw new myException("only allowd on existing BG_VEKTOR - records !");
		}
	}
	
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 16.01.2020
	 *
	 * @return
	 */
	public MyE2_MessageVector unStorno() {
		return this.unStornoUndelete(true);
	}

	/**
	 * 
	 * @author martin
	 * @date 16.01.2020
	 *
	 * @return
	 */
	public MyE2_MessageVector unDelete() {
		return this.unStornoUndelete(false);
	}

	
	/**
	 * 
	 * @author martin
	 * @date 16.01.2020
	 *
	 * @param stornoTrue_DeleteFalse
	 * @return
	 */
	private MyE2_MessageVector unStornoUndelete(boolean stornoTrue_DeleteFalse) {
		MyE2_MessageVector mv = bibMSG.getNewMV();

		try {
			VEK<Rec21> toUpdate = new VEK<Rec21>();
			VEK<Rec21> toDelete = new VEK<Rec21>();
			
			HMAP<RB_KM, Rec21> records = this.getCompleteStackOfRecords();
			
			Rec21 recStornoOrDelete = null;
			String fieldName = null;
			
			String msg1 = null;
			String msg2 = null;
			
			if (stornoTrue_DeleteFalse) {
				recStornoOrDelete =  this.get_up_Rec21(BG_STORNO_INFO.id_bg_storno_info);
				fieldName = "ID_BG_STORNO_INFO";
				msg1 = "Stornoeintrag";
				msg2 = "storniert";
			} else {
				recStornoOrDelete =  this.get_up_Rec21(BG_DEL_INFO.id_bg_del_info);
				fieldName = "ID_BG_DEL_INFO";
				msg1 = "Löscheintrags";
				msg2 = "gelöscht";
			}
			
			
			
			if (recStornoOrDelete!=null && recStornoOrDelete.is_ExistingRecord()) {
			
				records.get(RecV.key)._setNewVal(fieldName, null, mv);
				records.get(RecA1.key)._setNewVal(fieldName, null, mv);
				records.get(RecA2.key)._setNewVal(fieldName, null, mv);
				records.get(RecS1.key)._setNewVal(fieldName, null, mv);
				records.get(RecS2.key)._setNewVal(fieldName, null, mv);
				records.get(RecS3.key)._setNewVal(fieldName, null, mv);
				
				toUpdate._a(records.get(RecV.key));
				toUpdate._a(records.get(RecA1.key));
				toUpdate._a(records.get(RecA2.key));
				toUpdate._a(records.get(RecS1.key));
				toUpdate._a(records.get(RecS2.key));
				toUpdate._a(records.get(RecS3.key));
				
				toDelete._a(recStornoOrDelete);

				if (mv.isOK()) {
					boolean mustCommit = false;
					mv._add(bibDB.execMultiRecSave(toUpdate, false));
					if (mv.isOK()) {
						if (bibDB.deleteRecords(toDelete, false, mv)) {
							mustCommit = true;
						} else {
							mv._addAlarm(S.ms("Fehler beim Löschen des "+msg1));
						}
					}
					
					if (mustCommit) {
						if (bibDB.Commit()) {
							mv._addInfo(S.ms("").ut("Vektor "+this.getId()+" wiederhergestellt !"));
						} else {
							bibDB.Rollback();
							mv._addAlarm(S.ms("Fehler beim Wiederherstellen"));
						}
					} else {
						bibDB.Rollback();
					}
				}
			} else {
				mv._addAlarm(S.ms("Der Vektor "+this.getIdLong().toString()+" ist nicht "+msg2));
			}
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"<5631929c-3848-11ea-a137-2e728ce88125>");
			e.printStackTrace();
		}
		return mv;
	}
	
	
	
	
	public enum EnVerteilStatus  {
		VERTEILUNG_ERLAUBT(true, "Warenbewegung kann aufgeteilt werden")
		,HAT_VERTEILUNG_NACHFOLGER(true, "Warenbewegung kann aufgeteilt werden (es wurden bereits Verteilungen vorgenommen)")
		,IST_VERTEILUNG_NACHFOLGER(false, "Die Warenbewegung ist Teil einer Aufteilung")
		,TYP_KANN_NICHT_VERTEILT_WERDEN(false, "Dieser Typ kann nicht verteilt werden")
		,STORNIERT(false, "Die Warebewegung wurde storniert.")
		,ABGESCHLOSSEN(false, "Die Warenbewegung wurde bereits abgeschlossen")
		,FEHLER(false, "Fehler: Verteilstatus konnte nicht festgestellt werden")
		;
		
		private boolean verteilungOK = false;
		private String  meldung = null; 
		private EnVerteilStatus(boolean p_verteilungOK, String p_meldung) {
			verteilungOK = p_verteilungOK;
			meldung = p_meldung;
		}
		public boolean isVerteilungOK() {
			return verteilungOK;
		}
		public String getMeldung() {
			return meldung;
		}
	}
	
	
	
	public EnVerteilStatus getVerteilStatus() {
		EnVerteilStatus verteilStatus = null;
		
	    try {
	    	EnTransportTyp typDiesesVektors = EnTransportTyp.WE.getEnum(this.getFs(BG_VEKTOR.en_transport_typ));
	    	
	    	if (typDiesesVektors!=EnTransportTyp.WE) {
	    		verteilStatus = EnVerteilStatus.TYP_KANN_NICHT_VERTEILT_WERDEN;
	    	} else {
	    		
	    		if (this.getLongDbValue(BG_VEKTOR.id_bg_storno_info)!=null) {
	    			verteilStatus = EnVerteilStatus.STORNIERT;
	    		} else {
		    		
		    		
			    	verteilStatus = EnVerteilStatus.VERTEILUNG_ERLAUBT;
			    	
					EN_VEKTOR_STATUS vektorStatus = EN_VEKTOR_STATUS.FINAL.getEnum(this.getUfs(BG_VEKTOR.en_vektor_status));
					
					if (vektorStatus==EN_VEKTOR_STATUS.FINAL) {
						verteilStatus = EnVerteilStatus.ABGESCHLOSSEN;
					} else {
						Long idVorgaenger = this.getLongDbValue(BG_VEKTOR.id_bg_vektor_base);
						
						if (idVorgaenger!=null) {
							verteilStatus = EnVerteilStatus.IST_VERTEILUNG_NACHFOLGER;
						} else {
							if (this.getVerteilRecords().size()>0) {
								verteilStatus = EnVerteilStatus.HAT_VERTEILUNG_NACHFOLGER;
							}
						}
					}
	    		}
	    	}
			
		} catch (Exception e) {
			e.printStackTrace();
			verteilStatus = EnVerteilStatus.FEHLER;
		}
		
		
		return verteilStatus;
		
	}
	
	
	public RecList21 getVerteilRecords() {
		try {
			SEL selSucheVerteilNachfolger =  new SEL(_TAB.bg_vektor).FROM(_TAB.bg_vektor)
					.WHERE(new vglParam(BG_VEKTOR.id_bg_vektor_base))
					.AND(new VglNull(BG_VEKTOR.id_bg_storno_info));

			RecList21 rlNachFolger = new RecList21(_TAB.bg_vektor)
								._fill(selSucheVerteilNachfolger.getSqlExt(new VEK<ParamDataObject>()._a(new Param_Long(this.getIdLong()))));

			return rlNachFolger;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 03.03.2020
	 *
	 * @return s status_uebergang left and right, on error exception, on undefinded besitzt Pair(ENUM_STATUS_UEBERGANG.UNDEFINDED, ENUM_STATUS_UEBERGANG.UNDEFINDED)
	 * @throws Exception
	 */
	public Pair<ENUM_STATUS_UEBERGANG> getStatusAbrechnungLeftRight() throws Exception {
		Pair<ENUM_STATUS_UEBERGANG> status = new Pair<ENUM_STATUS_UEBERGANG>(ENUM_STATUS_UEBERGANG.UNDEFINDED, ENUM_STATUS_UEBERGANG.UNDEFINDED);

		HMAP<RB_KM, Rec21> stack = getCompleteStackOfRecords();

		if (stack!=null) {
			if (		this.getUfs(BG_VEKTOR.en_vektor_quelle,"").equals(EN_VEKTOR_QUELLE.FUHRE.dbVal())
					|| 	this.getUfs(BG_VEKTOR.en_vektor_quelle,"").equals(EN_VEKTOR_QUELLE.LAGER.dbVal())	) {
				status._setVal1(ENUM_STATUS_UEBERGANG.ALTE_FUHRE)._setVal2(ENUM_STATUS_UEBERGANG.ALTE_FUHRE);
			} else {
			
				Rec21_adresse   besitzLeft = 	new Rec21_BgStation(stack.get(RecS1.key)).getAdresseBesitz();
				Rec21_adresse   besitzMid = 	new Rec21_BgStation(stack.get(RecS2.key)).getAdresseBesitz();
				Rec21_adresse   besitzRight = 	new Rec21_BgStation(stack.get(RecS3.key)).getAdresseBesitz();
				
				status	._setVal1(new PdServiceBewertungAbrechnungsStatus().getStatusLeft(besitzLeft, besitzMid, besitzRight))
						._setVal2(new PdServiceBewertungAbrechnungsStatus().getStatusRight(besitzLeft, besitzMid, besitzRight));
			}
		}
		
		
		return status;
	}
	
	
	
	public Rec21_bgVector _fill_id(long id) throws myException {
		super._fill_id(id);
		return this;
	}

	
}
