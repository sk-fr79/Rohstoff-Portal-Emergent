/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VEKSingle;
import rohstoff.Echo2BusinessLogic.AH7.Reclist21_AH7P_GlobalProfiles;
import rohstoff.Echo2BusinessLogic.AH7.Reclist21_AH7P_SpecifiedProfiles;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Steuerdatei;

/**
 * @author martin
 * laeßt die voerhandenen profile an die uebergebenen id_ah7_steuerdatei uebergeben 
 */
public abstract class _PdServiceAH7Profiler {
	
	public enum COUNTERS {
		COUNTPROFILE
		,COUNTRELATIONEN
		,COUNTMATCHED
		,COUNTLOCKED
		,COUNTRESETED
	}
	
	public abstract Vector<Rec21_AH7_Steuerdatei> get_vIds__AH_7_steuerdatei_to_classify() throws myException;
	
	public HashMap<COUNTERS, Integer>  hmCounters = new HashMap<_PdServiceAH7Profiler.COUNTERS, Integer>();
	public HashMap<String, String>     hmIdsandProfiles = new HashMap<String, String>(); 				//merkt sich die IDs, die bewertet wurden und die automatisch erzeugten profile 
 	
	public boolean    					bSuppressActivation = false;  								//bei einzelbewertungen darf nur bis zum status vorbereitet gesetzt werden 
	
	
	
	/**
	 * 
	 * @param loopPopup
	 * @param mv
	 * @throws myException
	 * 
	 * wird nichts uebergeben, dann sucht sich das modul die profilierungs-vektoren selbst 
	 * @throws myException 
	 */

	public _PdServiceAH7Profiler(E2_ServerPushMessageContainer loopPopup, MyE2_MessageVector mv, boolean suppressActivation) throws myException {
		super();
		this.hmCounters.put(COUNTERS.COUNTPROFILE, 		new Integer(0));
		this.hmCounters.put(COUNTERS.COUNTRELATIONEN, 	new Integer(0));
		this.hmCounters.put(COUNTERS.COUNTLOCKED, 		new Integer(0));
		this.hmCounters.put(COUNTERS.COUNTMATCHED,	 	new Integer(0));
		this.hmCounters.put(COUNTERS.COUNTRESETED,	 	new Integer(0));
		
		
		this.bSuppressActivation = suppressActivation;
		
		this.runProfiling(loopPopup, 	new Reclist21_AH7P_SpecifiedProfiles().getVEK(), 
									new Reclist21_AH7P_GlobalProfiles().getVEK(),
									mv);
	}


	/**
	 * 
	 * @param loopPopup
	 * @param vSpecial_AH7_Profiles (definierte V7Profiles)
	 * @param vSonder_AH7_Profiles (globale profile)
	 * @param mv
	 * @throws myException
	 */
	public _PdServiceAH7Profiler(E2_ServerPushMessageContainer loopPopup, VEK<Rec21> vSpecial_AH7_Profiles,  VEK<Rec21> vSonder_AH7_Profiles, MyE2_MessageVector mv, boolean suppressActivation) throws myException {
		super();
		this.hmCounters.put(COUNTERS.COUNTPROFILE, 		new Integer(0));
		this.hmCounters.put(COUNTERS.COUNTRELATIONEN, 	new Integer(0));
		this.hmCounters.put(COUNTERS.COUNTLOCKED, 		new Integer(0));
		this.hmCounters.put(COUNTERS.COUNTMATCHED,	 	new Integer(0));
		this.hmCounters.put(COUNTERS.COUNTRESETED,	 	new Integer(0));

		
		this.bSuppressActivation = suppressActivation;
		
		this.runProfiling(loopPopup, 	vSpecial_AH7_Profiles, vSonder_AH7_Profiles, mv);
	}

	
	


	/**
	 * 
	 * @param loopPopup = 	E2_ServerPushMessageContainer can be null
	 * @param vSpecial_AH7_Profiles =    	Vector mit den Rec21 der speziellen Varianten
	 * @param vSonder_AH7_Profiles = 	Vector mit den Rec21 der basic-globalen-varianten
	 * @param mv
	 * @throws myException 
	 */
	private void runProfiling(E2_ServerPushMessageContainer loopPopup, VEK<Rec21> vSpecial_AH7_Profiles,  VEK<Rec21> vSonder_AH7_Profiles, MyE2_MessageVector mv) throws myException {
		
		Vector<Rec21_AH7_Steuerdatei>  	vIDs_AH7_Steuerdatei = this.get_vIds__AH_7_steuerdatei_to_classify();
		VEK<String> 		vIdsMatched = new VEK<>();
		VEKSingle<String>	vIdsLocked = new VEKSingle<>();
		VEK<String> 		vIdsResetted = new VEK<>();
		
		//zuerst die speziellen, dann die globalen sonderdefinitionen
		VEK<Rec21>  vGesamtProfile = new VEK<Rec21>()._a(vSpecial_AH7_Profiles)._a(vSonder_AH7_Profiles);
		
		this.hmIdsandProfiles.clear();
		
		
		//verhindert bei pruefungen ueber mehrere profile immer wieder aufs neue konsistenz-pruefung
		HashMap<Long, Boolean>  hmPruefHistorieKonsitenz = new HashMap<Long, Boolean>();
		
		
		for (Rec21 rec_AH7_Profil: vGesamtProfile) {

			int iCount = 0; 
			
			for (Rec21_AH7_Steuerdatei rec_ah7_steuerdatei_ToQualify: vIDs_AH7_Steuerdatei) {
				
				String id = rec_ah7_steuerdatei_ToQualify.getUfs(AH7_STEUERDATEI.id_ah7_steuerdatei);
				
				//hier die bewertung durchfuehren
				if (!mv.get_bIsOK()) {
					break;
				}
				
				//die konsitenzpruefung nur einmal laufen lassen fuer mehrere profile
				boolean bAH7Steuerdatei_is_consistent = false;
				if (hmPruefHistorieKonsitenz.get(rec_ah7_steuerdatei_ToQualify.getLongDbValue(AH7_STEUERDATEI.id_ah7_steuerdatei))!= null) {
					bAH7Steuerdatei_is_consistent = hmPruefHistorieKonsitenz.get(rec_ah7_steuerdatei_ToQualify.getLongDbValue(AH7_STEUERDATEI.id_ah7_steuerdatei));
				} else {
					bAH7Steuerdatei_is_consistent = new _PdServiceCheckAH7SteuerrelationIsConsistent().isAH7_steuersatzIsConsistent(rec_ah7_steuerdatei_ToQualify, mv);
					hmPruefHistorieKonsitenz.put(rec_ah7_steuerdatei_ToQualify.getLongDbValue(AH7_STEUERDATEI.id_ah7_steuerdatei), bAH7Steuerdatei_is_consistent);
					if (!bAH7Steuerdatei_is_consistent) {
						mv._add(new _PdServiceResetAH7Steuerdatensatz().resetSteuerDatei(rec_ah7_steuerdatei_ToQualify));
						vIdsResetted._a(id);
					}
				}


				
				if (bAH7Steuerdatei_is_consistent) {
					if (rec_ah7_steuerdatei_ToQualify.is_yes_db_val(AH7_STEUERDATEI.locked)) {
						vIdsLocked.add(id);
					} else {
						if (!vIdsMatched.contains(id)) {
							/// !!! hier wird gematched
							if (rec_ah7_steuerdatei_ToQualify.isAH7ProfileMatching(rec_AH7_Profil)) {
								mv.add_MESSAGE(rec_ah7_steuerdatei_ToQualify.writeProfilingResults(rec_AH7_Profil, this.bSuppressActivation));
								vIdsMatched._a(id);
								this.hmIdsandProfiles.put(id, rec_AH7_Profil.get_ufs_dbVal(AH7_PROFIL.bezeichnung));
							}
						}
					}
					
					if (loopPopup!=null) {
						if ((iCount)%200==0) {
							
							E2_Grid status = new E2_Grid()._setSize(300,500)._bo_dd();
							status	._a(new RB_lab()._tr("Profil, das angewendet wird:"), 					new RB_gld()._left_mid()._ins(2)._col_back_d())
									._a(new RB_lab()._t(rec_AH7_Profil.getUfs(AH7_PROFIL.bezeichnung)), 	new RB_gld()._center_mid()._ins(2)._col_back_d())
									._a(new RB_lab()._tr("Zähler"), 										new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+iCount), 										new RB_gld()._center_mid()._ins(2))
									._a(new RB_lab()._tr("Gesamtzahl Steuersätze"), 						new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+vIDs_AH7_Steuerdatei.size()), 					new RB_gld()._center_mid()._ins(2))
									._a(new RB_lab()._tr("Steuersätze gefunden und aktualisiert"), 			new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+vIdsMatched.size()),			 					new RB_gld()._center_mid()._ins(2))
									._a(new RB_lab()._tr("Steuersätze wurden übersprungen, weil gesperrt"), new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+vIdsLocked.size()),			 					new RB_gld()._center_mid()._ins(2))
									._a(new RB_lab()._tr("Steuersätze resettet weil inkonsistent"), 		new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+vIdsResetted.size()),			 				new RB_gld()._center_mid()._ins(2))
									
									;
							
							loopPopup.get_oGridBaseForMessages()._clear()._add(status, new RB_gld()._ins(5));
							loopPopup.get_oWindowPane().setWidth(new Extent(830));
							loopPopup.get_oWindowPane().setHeight(new Extent(300));
						}
					}
				}
				iCount++;
			}
			
		}
		
		this.hmCounters.put(COUNTERS.COUNTPROFILE, 		vGesamtProfile.size());
		this.hmCounters.put(COUNTERS.COUNTRELATIONEN,	vIDs_AH7_Steuerdatei.size());
		this.hmCounters.put(COUNTERS.COUNTLOCKED, 		vIdsLocked.size());
		this.hmCounters.put(COUNTERS.COUNTMATCHED, 		vIdsMatched.size());
		this.hmCounters.put(COUNTERS.COUNTRESETED, 		vIdsResetted.size());
		
		
		if (loopPopup!=null) {
			E2_Grid status = new E2_Grid()._setSize(300,500)._bo_dd();
			status	._a(new RB_lab()._tr("Abschlussmeldung"), 								new RB_gld()._left_mid()._ins(2)._col_back_d())
					._a(new RB_lab()._t(""), 												new RB_gld()._center_mid()._ins(2)._col_back_d())
					._a(new RB_lab()._tr("Gesamtzahl Steuersätze"), 						new RB_gld()._left_mid()._ins(2))
					._a(new RB_lab()._t(""+vIDs_AH7_Steuerdatei.size()), 					new RB_gld()._center_mid()._ins(2))
					._a(new RB_lab()._tr("Steuersätze gefunden und aktualisiert"), 			new RB_gld()._left_mid()._ins(2))
					._a(new RB_lab()._t(""+vIdsMatched.size()),			 					new RB_gld()._center_mid()._ins(2))
					._a(new RB_lab()._tr("Steuersätze wurden übersprungen, weil gesperrt"), new RB_gld()._left_mid()._ins(2))
					._a(new RB_lab()._t(""+vIdsLocked.size()),			 					new RB_gld()._center_mid()._ins(2))
					._a(new RB_lab()._tr("Steuersätze resettet weil inkonsistent"), 		new RB_gld()._left_mid()._ins(2))
					._a(new RB_lab()._t(""+vIdsLocked.size()),			 					new RB_gld()._center_mid()._ins(2))
					
					;
			
			loopPopup.get_oGridBaseForMessages()._clear()._add(status, new RB_gld()._ins(5));
			loopPopup.get_oWindowPane().setWidth(new Extent(830));
			loopPopup.get_oWindowPane().setHeight(new Extent(300));
			//loopPopup.showSimpleInfo(info);
		} else {
			MyE2_String info = S.ms("Automatisches Matching: ").ut(""+vGesamtProfile.size()).t(" Profil(e) auf ").ut(""+vIDs_AH7_Steuerdatei.size()).t(" Relationen angewendet: ")
					.ut(""+vIdsMatched.size()).t(" Relationen gefunden und aktualisiert, ").ut(""+vIdsLocked.size()).t(" Relationen wurden übersprungen, weil gesperrt !")
					.ut(""+vIdsResetted.size()).t(" Relationen wegen Inkosistenz resettet ");
			mv.add_MESSAGE(new MyE2_Info_Message(info));
		}
		
	}

}
