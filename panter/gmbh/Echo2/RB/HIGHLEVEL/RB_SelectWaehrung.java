/**
 * panter.gmbh.Echo2.RB.HIGHLEVEL
 * @author martin
 * @date 10.01.2019
 * 
 */
package panter.gmbh.Echo2.RB.HIGHLEVEL;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 10.01.2019
 *
 */
public class RB_SelectWaehrung extends RB_selField {

	public RB_SelectWaehrung() throws myException {
		super();
		this._setWaehrungenForAdresse(null);
	}

	
	/**
	 * 
	 * @author martin
	 * @date 10.01.2019
	 *
	 * shows only waehrungen of adresse, rest in hidden
	 *
	 * @param idAdress
	 * @return it own
	 * @throws myException 
	 */
	public RB_SelectWaehrung _setWaehrungenForAdresse(Long idAdress) throws myException {
		String oldDbValue = null;
		if (this.getSelectedIndex()>=0 && this.getSelectedIndex()<this.getVCompleteDBVals().size()) {
			oldDbValue = this.getActualDbVal();
		}
		
		
		this._clear();
		
		
		
		RecList21 alleWaehrungen = (RecList21)ENUM_MANDANT_SESSION_STORE.RECLIST21_WAEHRUNGEN.getValueFromSession();

		if (idAdress==null) {
			this._populate(alleWaehrungen, WAEHRUNG.waehrungssymbol, WAEHRUNG.id_waehrung, true);
			
		} else {
			Rec21_adresse ad = new Rec21_adresse()._fill_id(idAdress)._getMainAdresse();
			VEK<Rec21> waehrungenDerAdresse = new VEK<>();
			
			Rec21 basisWaehrung = ad.get_up_Rec21(WAEHRUNG.id_waehrung);
			if (basisWaehrung!=null && basisWaehrung.is_ExistingRecord()) {
				waehrungenDerAdresse._a(basisWaehrung);
			}
			

			
			RecList21 rlZusatz  = ad.get_down_reclist21(ADRESSE_WAEHRUNG.id_adresse);
			
			//evtl. dubetten aus den zusatzwaehrungen rausfiltern
			for (Rec21 r: rlZusatz) {
				Rec21 recW2 = r.get_up_Rec21(WAEHRUNG.id_waehrung);
				waehrungenDerAdresse._addValidated((p)->{
					boolean ret = true;
					for (Rec21 ri:waehrungenDerAdresse) {
						try {
							if (ri.getId()==p.getId()) {
								ret = false;
							}
						} catch (myException e) {
							e.printStackTrace();
						}
					}
					return ret;
				}, recW2);
			}
			
			DEBUG._print("Anzalhl Adresse-Währungen"+waehrungenDerAdresse.size());

			VEK<Rec21> alleAnderenWaehrungen = new VEK<>();
			
			for (Rec21 r: alleWaehrungen) {
				alleAnderenWaehrungen._addValidated((p)->{
					boolean ret = true;
					for (Rec21 ri:waehrungenDerAdresse) {
						try {
							if (ri.getId()==p.getId()) {
								ret = false;
								DEBUG._print("nicht in allenAnderen"+p.getUfs(WAEHRUNG.kurzbezeichnung));
							}
						} catch (myException e) {
							e.printStackTrace();
						}
					}
					if (ret) {
						try {
							DEBUG._print("IN allenAnderen"+p.getUfs(WAEHRUNG.kurzbezeichnung));
						} catch (myException e) {
							e.printStackTrace();
						}

					}
					return ret;
				}, r);
			}
			
			String[][] s_real = new String[waehrungenDerAdresse.size()][2];
			String[][] s_shadow = new String[alleAnderenWaehrungen.size()][2];
			
			DEBUG._print("Anzalhl Adresse-Währungen"+s_real.length);
			DEBUG._print("Anzalhl shadow-Währungen"+s_shadow.length);
			
			
			int i=0;
			for (Rec21 r : waehrungenDerAdresse) {
				s_real[i][0]= r.getFs(WAEHRUNG.kurzbezeichnung);
				s_real[i][1]= r.getFs(WAEHRUNG.id_waehrung);
				i++;
			}
			
			i=0;
			for (Rec21 r : alleAnderenWaehrungen) {
				s_shadow[i][0]= r.getFs(WAEHRUNG.kurzbezeichnung);
				s_shadow[i][1]= r.getFs(WAEHRUNG.id_waehrung);
				i++;
			}
			
			s_real = bibARR.add_array_inFront(s_real, bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT);
			
			this._populate(s_real);
			this._populateShadow(s_shadow);
			
			
		}
		if (idAdress==null && this.getVCompleteDBVals().size()>0) {
			this.setSelectedIndex(0);
		} else {
			if (S.isFull(oldDbValue)) {
				this._setActiveDBVal(oldDbValue);
			}
		}
		
		return this;
	}
	
	
	
	
	
	
	
}
