/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 27.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 27.06.2019
 *
 */
public class Rec21_KreditVersicherungKopf extends Rec21 {

	/**
	 * @author martin
	 * @date 27.06.2019
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public Rec21_KreditVersicherungKopf() throws myException {
		super(_TAB.kreditvers_kopf);
	}

	public Rec21_KreditVersicherungKopf(Rec21 baseRec) throws myException {
		super(baseRec);
	}

	
	

	/**
	 * 
	 * @author martin
	 * @date 27.06.2019
	 * Erzeugt einen Fehler, wenn die eine position (ausser der letzten) ein offenes ablaufdatum hat!
	 * wenn alle bis auf die letzte position ein ablaufdatum enthalten, damit die ablaufpruefung der fakturierungsfrist
	 * geprueft werden kann (wobei bei der letzten das ablaufdatum immer als das aktuelle datum definiert wird
	 * !!Wichtig! die fakturierungsfrist wird hier nicht abgefragt, da die pruefung auf beim Speichern der Maske erfolgen kann, und
	 *            dabei erst die fakturierungsfrist geschrieben wird
	 *
	 * @param allowLastAblaufdatumEmpty
	 *         folgende parameter, dann wenn eine noch ungespeicherte position von oben mitgeprueft werden soll
	 * @param idkreditversicherungsposZusatz
	 * @param gueltigAbZusatz
	 * @param gueltigBisZusatz
	 * @return
	 */
	public MyE2_MessageVector checkKonsistenzOfPosition(boolean allowLastAblaufdatumEmpty, Long idkreditversicherungsposZusatz, Date gueltigAbZusatz, Date gueltigBisZusatz) {
		MyE2_MessageVector  mv = new MyE2_MessageVector();

		VEK<PosTupel> v_pos = new VEK<>(); 
		
		try {
			RecList21 rl = this.get_down_reclist21(KREDITVERS_POS.id_kreditvers_kopf, null, KREDITVERS_POS.gueltig_ab.fn());
			
			MyE2_String fehlermeldung =  S.ms("Achtung! Verlängerte Fakturierungsfrist: Fehler bei Konsistenzpruefung: \n")
								.ut("  ID: "+this.getLongDbValue(KREDITVERS_KOPF.id_kreditvers_kopf)+"\n")
								.t("Nur die nach Beginn-Datum letzte Position innerhalb einer aktiven Kreditversicherung\n")
								.t("und gar keine Position innerhalb einer inaktiven Krediversicherung\n")
								.t("darf ein offenes Ablaufdatum haben !");
			
			if (rl.size()>0) {
				for (Rec21 r_pos: rl) {
					//wird von oben ein tuperl uebergeben, dann ist der entweder null (neueingabe position) oder ungleich null (position, die gerade veraendert wird)
					if (idkreditversicherungsposZusatz==null || idkreditversicherungsposZusatz.longValue()!=r_pos.getId()) {
						v_pos._a(new PosTupel(r_pos));
					}
				}
				if (idkreditversicherungsposZusatz!=null || gueltigAbZusatz!=null || gueltigBisZusatz !=null) {
					v_pos._a(new PosTupel(idkreditversicherungsposZusatz, gueltigAbZusatz, gueltigBisZusatz ));
				}

				v_pos.sort(new Comparator<PosTupel>() {

					@Override
					public int compare(PosTupel o1, PosTupel o2) {
						Date d1 = O.NN(o1.gueltigAb, new GregorianCalendar(2099,11, 31).getTime());
						Date d2 = O.NN(o2.gueltigAb, new GregorianCalendar(2099,11, 31).getTime());
						
						if  (d1.before(d2)) {
							return -1;
						} else if (d1.after(d2)) {
							return 1;
						} else {
							return 0;
						}
					}
				});
				
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
				for (int i=0; i<v_pos.size(); i++) {
					DEBUG._print("Datum "+i+": "+df.format(v_pos.get(i).gueltigAb));
					
					
					if (i!=(v_pos.size()-1) ||!allowLastAblaufdatumEmpty) {
						if (v_pos.get(i).gueltigBis==null) {
							mv._addAlarm(fehlermeldung);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			mv._addAlarm(S.ms("Fehler bei Konsitenzpruefung: ").ut("Code<522bf4e0-98bf-11e9-a2a3-2a2ae2dbcce4>\n").ut(ex.getLocalizedMessage()));
		}
		
		return mv;
	}
	
	
	
	private class PosTupel {
		private Long idKreditVersicherungsPos = null;
		private Date gueltigAb =null;
		private Date gueltigBis =null;
		
		public PosTupel(Rec21 kreditversicherungspos) throws myException {
			super();
			this.idKreditVersicherungsPos = kreditversicherungspos.getIdLong();
			this.gueltigAb = (Date)kreditversicherungspos.getRawVal(KREDITVERS_POS.gueltig_ab);
			this.gueltigBis = (Date)kreditversicherungspos.getRawVal(KREDITVERS_POS.gueltig_bis);
		}
		public PosTupel(Long idkreditversicherungspos, Date ab, Date bis) throws myException {
			super();
			this.idKreditVersicherungsPos=idkreditversicherungspos;
			this.gueltigAb = ab;
			this.gueltigBis = bis;
		}
		
	}
	
	
}
