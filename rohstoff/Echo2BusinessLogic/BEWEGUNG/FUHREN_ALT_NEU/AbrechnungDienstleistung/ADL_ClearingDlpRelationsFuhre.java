/**
 * @author martin
 * @date 16.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung;

import java.util.Date;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.DLP_JOIN_WARENBEWG;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecWatch;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Date;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.Or;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.DienstleistungsProfile.DL_ENUM_ANWENDEN_AUF;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_DlpJoinWarenbewegung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhre;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_DlpJoinWarenbewegung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_DlpProfile;

/**
 * @author martin
 * @date 16.09.2019
 *
 */
public class ADL_ClearingDlpRelationsFuhre {
	
	private String   						errorMessage = 							null;
	private RecList21_DlpProfile  			recListDlpProfileMatching  = 			null;
	private RecList21_DlpJoinWarenbewegung 	recListDlpJoin_ExistingWarenbeweg = 	null;

	

	// enthaelt alle ids der dienstleistungspositionen, die
	// lauf profilierung noetig sind, aber noch nicht
	// vorhanden
	private VEK<Long> dlpProfilesIdsMissing = 								new VEK<Long>();

	// enthaelt alle vorhandenen, die laut profil nicht
	// noetig sind (zwei kategorieen)
	private VEK<Long> dlpProfilesIdsFalseExisting = 						new VEK<Long>(); 
	private VEK<Long> dlpProfilesIdsFalseExistingWithUndeletedFuhre = 		new VEK<Long>();
	private VEK<Long> dlpProfilesIdsFalseExistingWithoutUndeletedFuhre = 	new VEK<Long>();
	
	// enthaelt alle vorhandenen, die laut profil auch
	// noetig sind (3 kategorien)
	private VEK<Long> dlpProfilesIdsCorrectExisting = 								new VEK<Long>(); 
	private VEK<Long> dlpProfilesIdsCorrectExistingActiveWithUndeletedFuhre = 		new VEK<Long>(); 
	private VEK<Long> dlpProfilesIdsCorrectExistingActiveWithDeletedFuhre = 		new VEK<Long>();
	private VEK<Long> dlpProfilesIdsCorrectExistingActiveWithoutFuhre = 			new VEK<Long>();
	private VEK<Long> dlpProfilesIdsCorrectExistingInactive = 						new VEK<Long>();    //ausgeschaltete
	


	public VEK<Long> getIdsCorrectExistingDlpProfilesInactive() {
		return dlpProfilesIdsCorrectExistingInactive;
	}




	private Rec21_VposTpaFuhre  fuhre = null;
	
	public Rec21_VposTpaFuhre getFuhre() {
		return fuhre;
	}




	private RecWatch            fuhreWatch = null;
	
	public RecWatch getFuhreWatch() {
		return fuhreWatch;
	}


	/**
	 * @author martin
	 * @throws myException 
	 * @date 16.09.2019
	 *
	 */
	public ADL_ClearingDlpRelationsFuhre(Rec21_VposTpaFuhre p_fuhre) throws myException {
		fuhre = p_fuhre;
		
		fuhreWatch = new RecWatch(_TAB.vpos_tpa_fuhre, fuhre.getId());
		
	}

	
	public ADL_ClearingDlpRelationsFuhre _init() throws myException {

		//zuerst pruefen, ob die fuhre die noetigen angaben besitzt
		Date start1 =fuhre.getDateDbValue(VPOS_TPA_FUHRE.datum_abholung);
		Date start2 =fuhre.getDateDbValue(VPOS_TPA_FUHRE.datum_aufladen);
		Date vergleichsDatum = start2;
		if (vergleichsDatum == null) {vergleichsDatum = start1;}
		
		//die evtl. vorhandenen dienstleistungszuordnungen auslesen (kann auch bei fuhren der fall sein, die nicht in die voraussetzungen passen, weil sie frueher mal gepasst haben)
		this.recListDlpJoin_ExistingWarenbeweg = 	this.readExistingJoinRecords(fuhre.getIdLong());
		
		dlpProfilesIdsMissing.clear(); 
		dlpProfilesIdsFalseExisting.clear(); 
		dlpProfilesIdsFalseExistingWithUndeletedFuhre.clear();
		dlpProfilesIdsFalseExistingWithoutUndeletedFuhre.clear();
		dlpProfilesIdsCorrectExisting.clear(); 

		dlpProfilesIdsCorrectExisting.clear();
		dlpProfilesIdsCorrectExistingActiveWithUndeletedFuhre.clear();
		dlpProfilesIdsCorrectExistingActiveWithDeletedFuhre.clear();
		dlpProfilesIdsCorrectExistingActiveWithoutFuhre.clear();
		dlpProfilesIdsCorrectExistingInactive.clear();
		

		
		
		//mindestvoraussetzungen der fuhre
		if (	vergleichsDatum != null 
				&& fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_lager_start)!=null 
				&& fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_lager_ziel)!=null 
				&& fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_artikel)!=null
				&& fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_artikel_bez_ek)!=null
				&& fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_artikel_bez_vk)!=null
				)
		{
		

			//und die laut profil noetigen
			
			VEK<ParamDataObject>   			vParams = new VEK<>();
			SEL s = new SEL(_TAB.dlp_profil).FROM(_TAB.dlp_profil)
							.WHERE(	new vgl_YN(	 DLP_PROFIL.aktiv, true))
							.AND(	new vglParam(DLP_PROFIL.gilt_ab_datum, COMP.LE))
							.AND(   new vglParam(DLP_PROFIL.id_adresse_start))
							.AND(   new vglParam(DLP_PROFIL.id_adresse_ziel))
							.AND(   new vglParam(DLP_PROFIL.id_artikel)) 
							.AND(   new Or(new vglParam(DLP_PROFIL.id_artikel_bez_quelle)).OR(new VglNull(DLP_PROFIL.id_artikel_bez_quelle))) 
							.AND(   new Or(new vglParam(DLP_PROFIL.id_artikel_bez_ziel))  .OR(new VglNull(DLP_PROFIL.id_artikel_bez_ziel  )))
							;
			
			vParams	._a(new Param_Date(vergleichsDatum))
					._a(new Param_Long(fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_lager_start)))
					._a(new Param_Long(fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_lager_ziel)))
					._a(new Param_Long(fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_artikel)))
					._a(new Param_Long(fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_artikel_bez_ek)))
					._a(new Param_Long(fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_artikel_bez_vk)))
					;
			
			
			
			//ab hier variiert es von fuhre zu fuhre
			if (fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)!=null) {
				s.AND(   new Or(new vglParam(DLP_PROFIL.id_adresse_fremdware))  .OR(new VglNull(DLP_PROFIL.id_adresse_fremdware  )));
				vParams._a(new Param_Long(fuhre.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)));
			} else {
				s.AND(new VglNull(DLP_PROFIL.id_adresse_fremdware  ));
			}
			
			if (fuhre.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung)) {
				s.AND(new Or(new vglParam(DLP_PROFIL.anwenden_auf_typ)).OR(new vglParam(DLP_PROFIL.anwenden_auf_typ)));
				vParams._a(new Param_String("1",DL_ENUM_ANWENDEN_AUF.OHNE_ABRECH.db_val()));
				vParams._a(new Param_String("2",DL_ENUM_ANWENDEN_AUF.ALLE.db_val()));
			} else {
				s.AND(new Or(new vglParam(DLP_PROFIL.anwenden_auf_typ)).OR(new vglParam(DLP_PROFIL.anwenden_auf_typ)));
				vParams._a(new Param_String("1",DL_ENUM_ANWENDEN_AUF.MIT_ABRRECH.db_val()));
				vParams._a(new Param_String("2",DL_ENUM_ANWENDEN_AUF.ALLE.db_val()));
			}
			
			this.recListDlpProfileMatching = new RecList21_DlpProfile()._fill(new SqlStringExtended(s.s())._addParameters(vParams));
			this.recListDlpProfileMatching._addRecWatch(fuhreWatch);
			
			dlpProfilesIdsMissing._a(			recListDlpProfileMatching.getIdsDlpProfil()).removeAll(recListDlpJoin_ExistingWarenbeweg.getIdsDlpProfil());
			dlpProfilesIdsFalseExisting._a(		recListDlpJoin_ExistingWarenbeweg.getIdsDlpProfil()).removeAll(recListDlpProfileMatching.getIdsDlpProfil());
			dlpProfilesIdsCorrectExisting._a(	recListDlpProfileMatching.getIdsDlpProfil()).removeAll(dlpProfilesIdsMissing);
			
			//jetzt nachsehen, welche profile der falschen und korrekten bereits eine fuhre besitzen
			for (Long idProfil: dlpProfilesIdsFalseExisting) {
				Rec21 recDlFuhre = this.recListDlpJoin_ExistingWarenbeweg.getRec21JoinWarenbeweg(idProfil).get_up_Rec21(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl,
																														VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, true);
				if (recDlFuhre == null || recDlFuhre.is_yes_db_val(VPOS_TPA_FUHRE.deleted)) {
					dlpProfilesIdsFalseExistingWithoutUndeletedFuhre._a(idProfil);
				} else {
					dlpProfilesIdsFalseExistingWithUndeletedFuhre._a(idProfil);
				}
			}

			for (Long idProfil: dlpProfilesIdsCorrectExisting) {
				if (this.recListDlpJoin_ExistingWarenbeweg.isActive(idProfil)) {
					Rec21 recDlFuhre = this.recListDlpJoin_ExistingWarenbeweg.getRec21JoinWarenbeweg(idProfil).get_up_Rec21(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl,
							VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, true);
					if (recDlFuhre==null) {
						dlpProfilesIdsCorrectExistingActiveWithoutFuhre._a(idProfil);
					} else if (recDlFuhre.is_yes_db_val(VPOS_TPA_FUHRE.deleted)) {
						dlpProfilesIdsCorrectExistingActiveWithDeletedFuhre._a(idProfil);
					} else {
						dlpProfilesIdsCorrectExistingActiveWithUndeletedFuhre._a(idProfil);
					}
				} else {
					dlpProfilesIdsCorrectExistingInactive._a(idProfil);
				}
			}
			
		} else {
			//falls die fuhre noch nicht ausreichend spezifiziert ist, sind alle vorhandenen erstmal falsch
			dlpProfilesIdsFalseExisting._a(recListDlpJoin_ExistingWarenbeweg.getIdsDlpProfil());
			
			//jetzt nachsehen, welche profile der falschen und korrekten bereits eine fuhre besitzen
			for (Long idProfil: dlpProfilesIdsFalseExisting) {
				Rec21 recDlFuhre = this.recListDlpJoin_ExistingWarenbeweg.getRec21JoinWarenbeweg(idProfil).get_up_Rec21(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl,
																														VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, true);
				if (recDlFuhre == null || recDlFuhre.is_yes_db_val(VPOS_TPA_FUHRE.deleted)) {
					dlpProfilesIdsFalseExistingWithoutUndeletedFuhre._a(idProfil);
				} else {
					dlpProfilesIdsFalseExistingWithUndeletedFuhre._a(idProfil);
				}
			}

			
		}

		return this;
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public ADL_ClearingDlpRelationsFuhre _setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public RecList21_DlpProfile getRecListDlpProfile() {
		return recListDlpProfileMatching;
	}


	public RecList21_DlpJoinWarenbewegung getRecListDlpJoinWarenbeweg() {
		return recListDlpJoin_ExistingWarenbeweg;
	}



	public VEK<Long> getDlpProfilesIdsMissing() {
		return dlpProfilesIdsMissing;
	}

	public VEK<Long> getDlpProfilesIdsFalseExisting() {
		return dlpProfilesIdsFalseExisting;
	}

	public VEK<Long> getDlpProfilesIdsCorrectExisting() {
		return dlpProfilesIdsCorrectExisting;
	}
	

	public VEK<Long> getDlpProfilesIdsFalseExistingWithUndeletedFuhre() {
		return dlpProfilesIdsFalseExistingWithUndeletedFuhre;
	}

	public VEK<Long> getDlpProfilesIdsFalseExistingWithoutUndeletedFuhre() {
		return dlpProfilesIdsFalseExistingWithoutUndeletedFuhre;
	}
	
	public VEK<Long> getDlpProfilesIdsCorrectExistingActiveWithUndeletedFuhre() {
		return dlpProfilesIdsCorrectExistingActiveWithUndeletedFuhre;
	}
	
	public VEK<Long> getDlpProfilesIdsCorrectExistingActiveWithoutFuhre() {
		return dlpProfilesIdsCorrectExistingActiveWithoutFuhre;
	}

	public VEK<Long> getDlpProfilesIdsCorrectExistingActiveWithDeletedFuhre() {
		return dlpProfilesIdsCorrectExistingActiveWithDeletedFuhre;
	}

	public boolean isRelevant() {
		//nur fuhren, die noch nicht abgeschlossen sind, werden beruecksichtigt
		boolean ret = false;
		if (dlpProfilesIdsCorrectExisting.size()>0 || dlpProfilesIdsFalseExisting.size()>0 || dlpProfilesIdsMissing.size()>0) {
			ret = true;
		}
		return ret;
	}
	
	
	
	/**
	 * loescht alle falsch zugeordneten profile, die noch keine relationierte Fuhre besitzen (id_vpos_tpa_fuhre_dl = null),
	 * ebenso werden alle profile geloescht, die eine geloeschte fuhre enthalten 
	 */
	public ADL_ClearingDlpRelationsFuhre _cleanUp() throws myException {
		MyE2_MessageVector mv = bibMSG.MV();

		VEK<Rec20>  recsToDelete = new VEK<>();
		
		if (dlpProfilesIdsFalseExistingWithoutUndeletedFuhre.size()>0 || dlpProfilesIdsCorrectExistingActiveWithDeletedFuhre.size()>0) {

			VEK<Long> idToDelete = new VEK<Long>()._a(dlpProfilesIdsFalseExistingWithoutUndeletedFuhre)._a(dlpProfilesIdsCorrectExistingActiveWithDeletedFuhre);
			
			for (Long id: idToDelete) {
				Rec21_DlpJoinWarenbewegung rec = this.recListDlpJoin_ExistingWarenbeweg.getRec21JoinWarenbeweg(id);
				
				if (rec==null) {
					throw new myException("Error: undefined state of found false relations !<95b7982a-e105-11e9-81b4-2a2ae2dbcce4>");
				} else {
					rec._addRecWatch(this.fuhreWatch);
					recsToDelete._a(rec);
				}
			}
			
			if (bibDB.deleteRecords(recsToDelete, true, mv)) {
				mv._addWarn(S.ms("Es wurden leere, ungültige Relationen / Relationen mit gelöschter Fuhre entfernt: ").ut(""+recsToDelete.size()));
			}

		}
		return this;
	}
	

	
	
	/**
	 * implementierung fuer die fuhre
	 * 
	 * es werden die relationen erzeugt, die fehlen, die falschen werden geloescht (falls keine fuhren angelegt sind)
	 * 
	 * @author martin
	 * @date 27.09.2019
	 *
	 * @param fuhre
	 * @return
	 */
	public ADL_ClearingDlpRelationsFuhre _buildMissingRelations()  throws myException {
		MyE2_MessageVector mv = bibMSG.MV();

		try {
			VEK<Rec20> recordsToSave = new VEK<>();
			
			//falls es noch gar kein bestehendes profil gibt, dann ein dlp_join_ext anlegen
			if (dlpProfilesIdsMissing.size()>0) {

				for (Long id: dlpProfilesIdsMissing) {
					Rec21_DlpJoinWarenbewegung  recWB = new Rec21_DlpJoinWarenbewegung();
					recWB.getRecWatches().add(this.fuhreWatch);
					recWB._setNewVal(DLP_JOIN_WARENBEWG.id_dlp_profil, id, mv);
					recWB._setNewVal(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre, fuhre.getIdLong(), mv);
					recWB._setNewVal(DLP_JOIN_WARENBEWG.aktiv, "Y", mv);
					if (mv.hasAlarms()) {
						break;
					}
					recordsToSave._a(recWB);
					recWB._addRecWatch(this.fuhreWatch);
				}
					
				if (mv.isOK()) {
					if (bibDB.saveRecords(recordsToSave, true, mv)) {
						mv._addInfo(S.ms("Die Dienstleistungsfuhren wurden vorbereitet..."));
					}
				}
			}
			
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._add(e.get_ErrorMessage());
		}
		
		return this;
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 16.09.2019
	 *
	 * @param idFuhre
	 * @return reclist21 mit den join-Tabellen-Werten zu den dienstleistungsfuhren
	 * @throws myException
	 */
	protected  RecList21_DlpJoinWarenbewegung  readExistingJoinRecords(Long idFuhre) throws myException {
		SEL sel = new SEL(_TAB.dlp_join_warenbewg)
								.FROM(_TAB.dlp_join_warenbewg)
								.LEFTOUTER(	new TableTerm(_TAB.vpos_tpa_fuhre.n(), "FDL"), 
											new FieldTerm(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl), 
											new FieldTerm("FDL",VPOS_TPA_FUHRE.id_vpos_tpa_fuhre))
								.WHERE(new vglParam(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre));

		
		
		return (RecList21_DlpJoinWarenbewegung)new RecList21_DlpJoinWarenbewegung()._fill(
				new SqlStringExtended(sel.s())._addParameter(new Param_Long("", idFuhre)))._addRecWatch(this.fuhreWatch);
	}
	

	

	

	
	public ADL_STATUS_PROFIL getStatusProfil(Long idProfil) {
		ADL_STATUS_PROFIL ret = ADL_STATUS_PROFIL.FEHLERSTATUS;
		
		if (this.dlpProfilesIdsCorrectExisting.contains(idProfil)) {
			if (this.dlpProfilesIdsCorrectExistingInactive.contains(idProfil)) {
				ret = ADL_STATUS_PROFIL.PROFIL_KORREKT_DEAKTIVIERT;
			} else {
				ret = ADL_STATUS_PROFIL.PROFIL_KORREKT;
			}
		} else if (this.dlpProfilesIdsFalseExisting.contains(idProfil)) {
			if (this.dlpProfilesIdsFalseExistingWithUndeletedFuhre.contains(idProfil)) {
				ret = ADL_STATUS_PROFIL.PROFIL_FALSCH_MIT_FUHRE;
			} else {
				ret = ADL_STATUS_PROFIL.PROFIL_FALSCH_OHNE_FUHRE;
			}
		} 
		
		return ret;
		
	}
	
	

	
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 19.11.2019
	 *
	 * @return s pair: in val 1 ist die hauptfuhre, in val2 ein VEK<> mit den zuehoerigen DL-fuhren, oder leerer VEK
	 * @throws myException
	 */
	public PAIR<Rec21, VEK<Rec21>>  getAllFuhrenOfThisBlock(boolean addDeleted) throws myException {
		
		VEK<Rec21> sammlerDlFuhren = new VEK<>();
		
		for (Rec21 dlpJoin: this.recListDlpJoin_ExistingWarenbeweg) {
			sammlerDlFuhren._addValidated(f-> {
				if (f!=null) {
					if (addDeleted) {
						return true;
					} else {
						try {
							return f.is_no_db_val(VPOS_TPA_FUHRE.deleted);
						} catch (myException e) {
							e.printStackTrace();
						}
					}
				}
				return false;
			},dlpJoin.get_up_Rec21(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, true));
		}
			
		return  new PAIR<Rec21, VEK<Rec21>>()._setVal1(fuhre)._setVal2(sammlerDlFuhren);
	}
	
	
	
	
	public VEK<Long> getAllRelatedFuhrenIds(boolean addDeleted) throws myException {
		PAIR<Rec21, VEK<Rec21>> p = this.getAllFuhrenOfThisBlock(addDeleted);
		
		if (p!=null) {
			VEK<Long> ret = new VEK<>();
			ret._a(p.getVal1().getIdLong());
			
			for (Rec21 fuhrenDl: p.getVal2()) {
				ret._a(fuhrenDl.getIdLong());
			}
			
			return ret;
		}
		
		return null;
	}
	
	
}
 