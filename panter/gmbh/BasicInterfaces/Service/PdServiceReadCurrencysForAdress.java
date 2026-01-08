/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.LinkedHashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class PdServiceReadCurrencysForAdress {
	
	public VEK<Rec21>  getVEKWaehrungen(Long idAdress) throws myException {
		VEK<Rec21> ret = new VEK<Rec21>();
		RecList21 rlZustzwaehrungen = new RecList21(_TAB.adresse_waehrung)._fill(
				new SEL("*").FROM(_TAB.adresse_waehrung).WHERE(new vgl(ADRESSE_WAEHRUNG.id_adresse,idAdress.toString())).s());
	
		Rec21 ra = new Rec21(_TAB.adresse)._fill_id(idAdress);
		
		ret.add(ra.get_up_Rec21(WAEHRUNG.id_waehrung));
		
		//die zusatzwaehrunge dahinter sortiert
		VEK<Rec21> wz = new VEK<Rec21>();
		for (Rec21 r: rlZustzwaehrungen) {
			wz._a(r.get_up_Rec21(WAEHRUNG.id_waehrung));
		}
		wz.sort((w1,w2)->{return w1.getUfs(WAEHRUNG.kurzbezeichnung, "", "").compareTo(w2.getUfs(WAEHRUNG.kurzbezeichnung, "", ""));});
		wz.forEach((r)->{ret.add(r);});

		//ret enthaelt jetzt an erster stelle die hauptwaehrung und die zusaetzwaehrungen sortiert dahinter
		
//		for (Rec21 r: ret) {
//			DEBUG._print(r.getUfs(WAEHRUNG.id_waehrung)+" -> "+r.getUfs(WAEHRUNG.kurzbezeichnung));
//		}
		
		return ret;
	}
	

	/**
	 * alle waehrungen
	 * @return
	 * @throws myException
	 */
	public VEK<Rec21>  getVEKWaehrungenAll() throws myException {
		VEK<Rec21> ret = new VEK<Rec21>();
		RecList21 rlWaehrungen = new RecList21(_TAB.adresse_waehrung)._fillWithAll();
	
		//die zusatzwaehrunge dahinter sortiert
		VEK<Rec21> wz = new VEK<Rec21>();
		for (Rec21 r: rlWaehrungen) {
			wz._a(r.get_up_Rec21(WAEHRUNG.id_waehrung));
		}
		wz.sort((w1,w2)->{return w1.getUfs(WAEHRUNG.kurzbezeichnung, "", "").compareTo(w2.getUfs(WAEHRUNG.kurzbezeichnung, "", ""));});
		wz.forEach((r)->{ret.add(r);});

		return ret;
	}

	
	public Rec21 getWaehrungMandantBasis() throws myException {
		Rec21 rMand = new Rec21(_TAB.mandant)._fill_id(bibALL.get_ID_MANDANT());
		
		return rMand.get_up_Rec21(WAEHRUNG.id_waehrung);
	}
	
	
	
	/**
	 * erzeugt linkedHashmap zum fuellen eines dropdowns
	 * @param idAdress
	 * @return
	 * @throws myException
	 */
	public LinkedHashMap<String, String> getLHmWaehrungen(Long idAdress) throws myException {
		LinkedHashMap<String, String> ret = new LinkedHashMap<String, String>();
		
		VEK<Rec21>  vRecW = getVEKWaehrungen(idAdress);
		
		vRecW.forEach((r)-> { ret.put(r.getFs(WAEHRUNG.id_waehrung, "-1", "-1"), r.getFs(WAEHRUNG.kurzbezeichnung, "<?>", "<?>")+" ("+r.getFs(WAEHRUNG.waehrungssymbol, "<?>", "<?>")+")"); });
		
		if (ret.containsKey("-1"))  {
			throw new myException("System-Error: Unknown Currency !!");
		}
		
//		for (String k: ret.keySet()) {
//			DEBUG._print(k+" -> "+ret.get(k));
//		}
	
		return ret;
	}
	
	
	/**
	 * erzeugt linkedHashmap zum fuellen eines dropdowns (komplementaere, nicht in adresse benutzte)
	 * @param idAdress
	 * @return
	 * @throws myException
	 */
	public LinkedHashMap<String, String> getLHmWaehrungenNotInKunde(Long idAdress) throws myException {
		LinkedHashMap<String, String> active = this.getLHmWaehrungen(idAdress);  //kundenwaehrungen
		
		RecList21 rlWaehrung = new RecList21(_TAB.waehrung)._fillWithAll();
		
		
		LinkedHashMap<String, String> ret = new LinkedHashMap<String, String>();
		
		for (Rec21 w: rlWaehrung) {
			if (!active.containsKey(w.getFs(WAEHRUNG.id_waehrung))) {
				 ret.put(w.getFs(WAEHRUNG.id_waehrung, "-1", "-1"), w.getFs(WAEHRUNG.kurzbezeichnung, "<?>", "<?>")+" ("+w.getFs(WAEHRUNG.waehrungssymbol, "<?>", "<?>")+")");
			}
		}
	
		return ret;
	}
	

	
	/**
	 * erzeugt dataToView zum fuellen eines dropdowns
	 * @param idAdress
	 * @return
	 * @throws myException
	 */
	public dataToView getDataToViewWaehrungen(Long idAdress, boolean emptyInFront) throws myException {
		LinkedHashMap<String, String> ret = this.getLHmWaehrungen(idAdress);
		
		dataToView dw = new dataToView(false, bibE2.get_CurrSession());
		if (emptyInFront) {
			dw.addPairOfValues("-", "", false);
		}
		
		ret.forEach((s1,s2)-> { 
			try {
				dw.addPairOfValues(s2, s1, false);
//				DEBUG._print(s2+" -> (val:)"+s1);
			} catch (myException e) {
				e.printStackTrace();
			} 
		});
		
		return dw;
	}
	
	
	/**
	 * erzeugt dataToView zum fuellen eines dropdowns (hier shadow-werte, komplementaere)
	 * @param idAdress
	 * @return
	 * @throws myException
	 */
	public dataToView getDataToViewWaehrungenNotInKunde(Long idAdress) throws myException {
		LinkedHashMap<String, String> ret = this.getLHmWaehrungenNotInKunde(idAdress);
		
		dataToView dw = new dataToView(false, bibE2.get_CurrSession());
		
		ret.forEach((s1,s2)-> { 
			try {
				dw.addPairOfValues(s2, s1, false);
			} catch (myException e) {
				e.printStackTrace();
			} 
		});
		
		return dw;
	}
	
	

	/**
	 * erzeugt dataToView zum fuellen eines dropdowns (alle waehrungen)
	 * @return
	 * @throws myException
	 */
	public dataToView getDataToViewAllWaehrungen(boolean emptyInFront) throws myException {
		VEK<Rec21> vAlle = this.getVEKWaehrungenAll();
		
		dataToView dw = new dataToView(false, bibE2.get_CurrSession());
		if (emptyInFront) {
			dw.addPairOfValues("-", "", false);
		}

		vAlle.forEach((r)-> { 
			try {
				dw.addPairOfValues(r.getFs(WAEHRUNG.kurzbezeichnung, "<?>", "<?>")+" ("+r.getFs(WAEHRUNG.waehrungssymbol, "<?>", "<?>")+")", r.getFs(WAEHRUNG.id_waehrung, "-1", "-1"), false);
			} catch (myException e) {
				e.printStackTrace();
			} 
		});
		
		return dw;
	}
	
	
	
	
	
}
