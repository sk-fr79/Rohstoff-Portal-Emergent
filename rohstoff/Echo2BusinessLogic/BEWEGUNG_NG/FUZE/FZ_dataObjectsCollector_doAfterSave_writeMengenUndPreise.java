package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE;

import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;


public class FZ_dataObjectsCollector_doAfterSave_writeMengenUndPreise implements FZ_dataObjectsCollector_doAfterSave {

	@Override
	public void executeInOpenTransactionAfterSave(FZ_dataObjectsCollector do_collector, MyE2_MessageVector mv) throws myException {
		
		for (RB_Dataobject dob: do_collector) {
		
			RB_Dataobject_V2 dob2 = (RB_Dataobject_V2)dob;

			//jetzt die bewegung_vektor(en) suchen
			Rec20 r2 = dob2.get_rec20();
			
			if (r2.get_tab()==_TAB.bewegung_atom) {
				
				Rec20 recAtom = null;
				if (r2.is_newRecordSet()) {
					recAtom = r2.get_rec_after_save_new();
				} else {
					recAtom = r2;
				}

				//werte, die feststehen vor der abzugsliste
				BigDecimal bd_menge = 				recAtom.getRawResultValueAsBigDecimal(BEWEGUNG_ATOM.menge);
				BigDecimal bd_mengeVerteilung = 	recAtom.getRawResultValueAsBigDecimal(BEWEGUNG_ATOM.menge_verteilung, BigDecimal.ZERO);
				BigDecimal bd_epreis = 				recAtom.getRawResultValueAsBigDecimal(BEWEGUNG_ATOM.e_preis);
				String     id_artikel = 			recAtom.get_ufs_dbVal(BEWEGUNG_ATOM.id_artikel);
				
				BigDecimal bd_nettoMenge = null;
				BigDecimal bd_endPreis = null;
				BigDecimal bd_ePreisResultAufNettoMenge = null;
				BigDecimal bd_ePreisResultAufButtoMenge = null;
				
				//voraussetzung fuer die kalkualtion ist eine Menge>0 und ein Preis <> null
				if (bd_menge !=null && bibNUM.IS_GREATER_0(bd_menge) && bd_epreis!=null && S.isFull(id_artikel)) {
					
					//reale bruttomenge (verteilung ist weg)
					BigDecimal bd_mengeBrutto = bd_menge.subtract(bd_mengeVerteilung);
					BigDecimal bd_mengedivisor = recAtom.get_up_Rec20(ARTIKEL.id_artikel).getRawResultValueAsBigDecimal(ARTIKEL.mengendivisor, BigDecimal.ZERO);
					
					if (bibNUM.IS_EQUAL_0(bd_mengedivisor)) {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die benutzte Sorte hat keinen korrekten Mengendivisor!!")));

					} else {
						
						ownAbzugKalkulator 								abzugCalc = 			new ownAbzugKalkulator(recAtom, bd_mengeBrutto, bd_epreis);
						BigDecimal 										bdResultEpreis = 		abzugCalc.getBd_resultierenderEpreisAufNettoMenge();
						BigDecimal 										bdAbzugMengenRelevant =	abzugCalc.getBd_AbzugAufLagermenge();
						
						BigDecimal   bdMgeBrtInAbrechEinheit = 			MyBigDecimal.divide(bd_mengeBrutto, bd_mengedivisor, 6);
						bd_endPreis = 									MyBigDecimal.multiplicate(bdMgeBrtInAbrechEinheit, bd_epreis, 2);
						
						
						
						
						
						
						
						
						
						recAtom._SaveAndClean(false, mv);
					}
				}
			}
		}
	}
	
	

	
	
	/**
	 * muss noch ausprogrammiert werden
	 * @author martin
	 *
	 */
	
	private class ownAbzugKalkulator {
		
		private Rec20 atom = null;
		
		private BigDecimal bd_resultierenderEpreisAufNettoMenge = null;
		private BigDecimal bd_AbzugAufLagermenge = null;
		private BigDecimal bd_GPREIS_ABZ_MGE = null;
		private BigDecimal GPREIS_ABZ_AUF_NETTOMGE = null;
		private BigDecimal GPREIS_ABZ_VORAUSZAHLUNG = null;
		
		
		/**
		 * @param p_atom
		 */
		public ownAbzugKalkulator(Rec20 p_atom, BigDecimal bdStartMenge, BigDecimal bdStartPreis) {
			super();
			this.atom = p_atom;
			this.bd_resultierenderEpreisAufNettoMenge=bdStartPreis;
			this.bd_AbzugAufLagermenge = BigDecimal.ZERO;
		}
		
		public BigDecimal getBd_resultierenderEpreisAufNettoMenge() {
			return bd_resultierenderEpreisAufNettoMenge;
		}
		public BigDecimal getBd_AbzugAufLagermenge() {
			return bd_AbzugAufLagermenge;
		}

		public Rec20 getAtom() {
			return atom;
		}

		public BigDecimal getBd_GPREIS_ABZ_MGE() {
			return bd_GPREIS_ABZ_MGE;
		}

		public BigDecimal getgPREIS_ABZ_AUF_NETTOMGE() {
			return GPREIS_ABZ_AUF_NETTOMGE;
		}

		public BigDecimal getgPREIS_ABZ_VORAUSZAHLUNG() {
			return GPREIS_ABZ_VORAUSZAHLUNG;
		}
		
	}
	
}
