package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.dataTools.bibDB_SYNTAX;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class RGOM___Attach_Missing_and_Collect_Relevant_Attachements_4_MAIL {
	private RECORD_VKOPF_RG 				RecRG = 						null;
	private ArrayList<RECORD_ARCHIVMEDIEN> 	vArchivmedienSingular4Attach = 	new ArrayList<RECORD_ARCHIVMEDIEN>();  
	private ArrayList<String> 				vDublettenCheck = 				new ArrayList<String>();
	private ArrayList<String> 				vFilesBereitsVorhanden = 		new ArrayList<String>();

	
	/**
	 * klasse haengt die fehlenden attachements an die VKOPF_RG-archive an
	 * und sammelt anschliessend die relevanten RECORD_ARCHIVMEDIEN-objecte
	 * @param recRG
	 * @param o_mv
	 * @throws myException
	 */
	public RGOM___Attach_Missing_and_Collect_Relevant_Attachements_4_MAIL(RECORD_VKOPF_RG recRG, MyE2_MessageVector o_mv) throws myException {
		super();
		this.RecRG = recRG;
		this.vDublettenCheck.clear();
		this.vFilesBereitsVorhanden.clear();
		
		
		//zuerst alle files, unabhaengig vom programmkenner, sammeln, um die "bereits-vorhanden-liste" zu definieren, damit diese nicht neu verhaengt werden
		RL_ARCHIVMEDIEN_rg rlArchimedienBereitsVorhanden = new RL_ARCHIVMEDIEN_rg(this.RecRG, null);
		for (RECORD_ARCHIVMEDIEN ra: rlArchimedienBereitsVorhanden) {
			this.vFilesBereitsVorhanden.add(new RECORD_ARCHIVMEDIEN_ext(ra).get__cCompletePathAndFileName());
		}
		
		
		//dann die vom fuhrenort / der fuhre einsammeln
		ArrayList<RECORD_ARCHIVMEDIEN> vArchivmedienExt = new ArrayList<RECORD_ARCHIVMEDIEN>();
		for (RECORD_VPOS_RG rp: this.RecRG.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg(bibDB_SYNTAX.NOT_DEL(_DB.VPOS_RG$DELETED), null, true)) {
			
			//zuerst die fuhren
			RECORD_VPOS_TPA_FUHRE_ORT  recFUO =   rp.get_UP_RECORD_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre_ort_zugeord();
			//esistiert ein fuhrenort, dann wird nur dieser nach passenden anlagen durchsucht
			if (recFUO!=null) {
				if (this.RecRG.get_VORGANG_TYP_cF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
					vArchivmedienExt.addAll(new RL_ARCHIVMEDIEN_fuo(recFUO, PROGRAMMKENNER.RECHNUNG_ANHANG).values());
				} else {
					vArchivmedienExt.addAll(new RL_ARCHIVMEDIEN_fuo(recFUO, PROGRAMMKENNER.GUTSCHRIFT_ANHANG).values());
				}
				vArchivmedienExt.addAll(new RL_ARCHIVMEDIEN_fuo(recFUO, PROGRAMMKENNER.RECH_GUT_ANHANG).values());
				
			} else {
				RECORD_VPOS_TPA_FUHRE  recFu = rp.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_zugeord();
				if (recFu!=null) {
					if (this.RecRG.get_VORGANG_TYP_cF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
						vArchivmedienExt.addAll(new RL_ARCHIVMEDIEN_fu(recFu, PROGRAMMKENNER.RECHNUNG_ANHANG).values());
					} else {
						vArchivmedienExt.addAll(new RL_ARCHIVMEDIEN_fu(recFu, PROGRAMMKENNER.GUTSCHRIFT_ANHANG).values());
					}
					vArchivmedienExt.addAll(new RL_ARCHIVMEDIEN_fu(recFu, PROGRAMMKENNER.RECH_GUT_ANHANG).values());
				}
			}
		}

		//jetzt die noch fehlenden externen anhaenge zur rechnung verknuepfen
		this.appendMissing_RG_Attachments(vArchivmedienExt,this.vFilesBereitsVorhanden, o_mv);
		
		if (o_mv.get_bIsOK()) {
			
			//jetzt alle einsammeln, die nun an der rechnung haengen
			ArrayList<RECORD_ARCHIVMEDIEN> vArchivmedienRG = new ArrayList<RECORD_ARCHIVMEDIEN>();
			
			//jetzt die direkt am RG-kopf angehaengten suchen
			if (this.RecRG.get_VORGANG_TYP_cF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
				vArchivmedienRG.addAll(new RL_ARCHIVMEDIEN_rg(this.RecRG, PROGRAMMKENNER.RECHNUNG_ANHANG).values());
			} else {
				vArchivmedienRG.addAll(new RL_ARCHIVMEDIEN_rg(this.RecRG, PROGRAMMKENNER.GUTSCHRIFT_ANHANG).values());
			}
			vArchivmedienRG.addAll(new RL_ARCHIVMEDIEN_rg(this.RecRG, PROGRAMMKENNER.RECH_GUT_ANHANG).values());
	
			
			//jetzt nach der reihenfolge des erfassens sortieren
			Collections.sort(vArchivmedienRG, new Comparator<RECORD_ARCHIVMEDIEN>() {
				@Override
				public int compare(RECORD_ARCHIVMEDIEN o1,RECORD_ARCHIVMEDIEN o2) {
					try {
						return o1.get_ID_ARCHIVMEDIEN_lValue(new Long(-1)).compareTo(o2.get_ID_ARCHIVMEDIEN_lValue(new Long(-1)));
					} catch (myException e) {
						return 0;
					}
				}
			});
			
			
			//jetzt saeubern: jede datei nur einmal anhaengen an die email-anhaengen, dazu die externen umhaengen an den RG-Datensatz
			this.vArchivmedienSingular4Attach.addAll(this.collectSingular(vArchivmedienRG,	o_mv));
		}
	}


	
	private void appendMissing_RG_Attachments(	ArrayList<RECORD_ARCHIVMEDIEN> 	vArchmedExtern,
												ArrayList<String> 				vfilesBereitsVorhanden,
												MyE2_MessageVector 				mv) throws myException {
		for (RECORD_ARCHIVMEDIEN ra: vArchmedExtern) {
			RECORD_ARCHIVMEDIEN_ext ra_ext = new RECORD_ARCHIVMEDIEN_ext(ra);
			if (!vfilesBereitsVorhanden.contains(ra_ext.get__cCompletePathAndFileName())) {
				RECORDNEW_ARCHIVMEDIEN recNew = ra_ext.get_Record_NEW_FilledWithActualValues(mv, false, true);
				mv.add_MESSAGE(recNew.set_NewValueForDatabase(_DB.ARCHIVMEDIEN$ID_TABLE, this.RecRG.get_ID_VKOPF_RG_cUF()));
				mv.add_MESSAGE(recNew.set_NewValueForDatabase(_DB.ARCHIVMEDIEN$TABLENAME, _DB.VKOPF_RG.substring(3)));
				if (mv.get_bHasAlarms()) {
					throw new myException("Error collection Archiv-Files ...");
				}
				recNew.do_WRITE_NEW_ARCHIVMEDIEN(mv);
			}
		}

	}
	
	
	
	
	
	/**
	 * list mit archivmedien aller zusammengesammelter 
	 * @return
	 * @throws myException 
	 */
	private ArrayList<RECORD_ARCHIVMEDIEN> collectSingular(ArrayList<RECORD_ARCHIVMEDIEN> vArchmedTocheck, MyE2_MessageVector mv) throws myException {
		//es werden alle anlagen auf dubletten geprueft
		ArrayList<RECORD_ARCHIVMEDIEN>  vRueck = 			new ArrayList<RECORD_ARCHIVMEDIEN>();
		
		// zuerst die originale, dann der rest
		for (RECORD_ARCHIVMEDIEN ra: vArchmedTocheck) {
			RECORD_ARCHIVMEDIEN_ext ra_ext = new RECORD_ARCHIVMEDIEN_ext(ra);
			if (ra_ext.is_IST_ORIGINAL_YES()) {
				if (!vDublettenCheck.contains(ra_ext.get__cCompletePathAndFileName())) {
					vRueck.add(ra_ext);
					vDublettenCheck.add(ra_ext.get__cCompletePathAndFileName());
					DEBUG.System_println("ich sammle orig ..."+ra_ext.get__cCompletePathAndFileName(), DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
				}
			}
		}
		
		// dann der rest
		for (RECORD_ARCHIVMEDIEN ra: vArchmedTocheck) {
			RECORD_ARCHIVMEDIEN_ext ra_ext = new RECORD_ARCHIVMEDIEN_ext(ra);
			if (ra_ext.is_IST_ORIGINAL_NO()) {
				if (!vDublettenCheck.contains(ra_ext.get__cCompletePathAndFileName())) {
					vRueck.add(ra_ext);
					vDublettenCheck.add(ra_ext.get__cCompletePathAndFileName());
					DEBUG.System_println("ich sammle zubehoer ..."+ra_ext.get__cCompletePathAndFileName(), DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
				}
			}
		}
		return vRueck;
	}
	
	
	
	private class RL_ARCHIVMEDIEN_rg extends RECLIST_ARCHIVMEDIEN_ext {
		public RL_ARCHIVMEDIEN_rg(RECORD_VKOPF_RG rg, PROGRAMMKENNER p_kenner) throws myException {
			super(_DB.VKOPF_RG, rg.get_ID_VKOPF_RG_cUF(), null , p_kenner);
		}
	}
	
	private class RL_ARCHIVMEDIEN_fu extends RECLIST_ARCHIVMEDIEN_ext {
		public RL_ARCHIVMEDIEN_fu(RECORD_VPOS_TPA_FUHRE rg, PROGRAMMKENNER p_kenner) throws myException {
			super(_DB.VPOS_TPA_FUHRE, rg.get_ID_VPOS_TPA_FUHRE_cUF(), null , p_kenner);
		}
	}
	
	private class RL_ARCHIVMEDIEN_fuo extends RECLIST_ARCHIVMEDIEN_ext {
		public RL_ARCHIVMEDIEN_fuo(RECORD_VPOS_TPA_FUHRE_ORT rg, PROGRAMMKENNER p_kenner) throws myException {
			super(_DB.VPOS_TPA_FUHRE_ORT, rg.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), null , p_kenner);
		}
	}

	public ArrayList<RECORD_ARCHIVMEDIEN> get_v_collected_ArchivMedien() {
		return vArchivmedienSingular4Attach;
	}
	
			
}
