/**
 * rohstoff.businesslogic.bewegung2.list
 * @author martin
 * @date 23.11.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;

/**
 * @author martin
 * @date 23.11.2018
 *
 */
public enum EnTransportTyp implements IF_enumForDb<EnTransportTyp> {
	
	WE("Wareneingang (Einkauf)",							true, false, 	true, 		false, 	EnBesitzerTyp.QUELLFIRMA, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)
	,WA("Warenausgang (Verkauf)",							true, false, 	false, 		true, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.ZIELFIRMA)
	,STRECKE("Strecke (Einkauf-Verkauf)",					true, false, 	true, 		true, 	EnBesitzerTyp.QUELLFIRMA, EnBesitzerTyp.MANDANT, EnBesitzerTyp.ZIELFIRMA)
	,LAGER_LAGER("Umlagerung Eigenware",					true, true, 	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)
	,FREMDWARENTRANSPORT("Fremdwarentransport",				true, true, 	false, 		false, 	EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA)
	,TESTSTELLUNG("Teststellung", 							true, false, 	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)	
	,LEERGUTRANSPORT("Leerguttransport", 					true, true, 	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)
	,WE_L("Einkauf Fremdware (bereits im Lager)", 			true, true, 	true, 		false, 	EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)
	,WA_L("Verkauf Eigenware (bleibt im Lager)", 			true, true, 	false, 		true, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.FREMDFIRMA)
	,UMBUCHUNG("Umbuchung",									true, false,	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)
	,EINBUCHUNG("Einbuchung",								true, true,		false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)
	,AUSBUCHUNG("Ausbuchung",								true, true, 	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)
	,EINBUCHUNG_F("Einbuchung Fremdware",					false, true, 	false, 		false, 	EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA)
	,AUSBUCHUNG_F("Ausbuchung Fremdware",					false, true, 	false, 		false, 	EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA)
	;

	
	private String		m_text4DropDown = 	null;
	private boolean 	typ4Mask = 			true;
	private boolean 	sortenGleichheit = false;
	
	private boolean     allowKontraktAngebotLeft = false;
	private boolean     allowKontraktAngebotRight = false;
	
	
	
	private EnBesitzerTyp besitzerStart = 	null;
	private EnBesitzerTyp besitzerMitte = 	null;
	private EnBesitzerTyp besitzerZiel = 	null;
	
	
	private EnTransportTyp(	String 			maskType, 
							boolean 		p_maskentyp, 
							boolean 		p_sortenGleichheit, 
							boolean 		p_allowKontraktAngebotLeft, 
							boolean 		p_allowKontraktAngebotRight, 
							EnBesitzerTyp 	p_besitzerStart, 
							EnBesitzerTyp 	p_besitzerMitte, 
							EnBesitzerTyp 	p_besitzerZiel) {
		
		this.m_text4DropDown=					maskType;
		this.typ4Mask=							p_maskentyp;
		this.sortenGleichheit = 				p_sortenGleichheit;
		this.allowKontraktAngebotLeft = 		p_allowKontraktAngebotLeft;
		this.allowKontraktAngebotRight = 		p_allowKontraktAngebotRight;
		this.besitzerStart = 					p_besitzerStart;
		this.besitzerMitte = 					p_besitzerMitte;
		this.besitzerZiel = 					p_besitzerZiel;
	}

	@Override
	public EnTransportTyp[] getValues() {
		return EnTransportTyp.values();
	}

	@Override
	public String userText() {
		return this.m_text4DropDown;
	}

	/**
	 * 
	 * @author martin
	 * @date 26.11.2018
	 *
	 * @return s true, wenn used in mask 
	 */
	public boolean isMaskType() {
		return typ4Mask;
	}
	
	public static int getNumberMaskType() {
		int i=0;
		for (EnTransportTyp e: EnTransportTyp.values()) {
			if (e.isMaskType()) {
				i++;
			}
		}
		return i;
	}
	

	
	
	@SuppressWarnings("rawtypes")
	public String[][] getArray4Selfield4Mask(boolean emptyInFront) throws myException {
		
		String[][] arr = new String[EnTransportTyp.getNumberMaskType()][2];
		
		for (int i=0;i<this.getValues().length;i++) {
			if (this.getValues()[i].isMaskType()) {
				arr[i][0]=((IF_enumForDb)this.getValues()[i]).userText();
				arr[i][1]=((IF_enumForDb)this.getValues()[i]).dbVal();
			}
		}
		
		if (emptyInFront) {
			arr = bibARR.add_emtpy_db_value_inFront(arr);
		}
		
		return arr;
	}

	/**
	 * 
	 * @param dbVal
	 * @return Enum korresponding to dbVal
	 */
	@Override
	public EnTransportTyp getEnum(String dbVal) {
		EnTransportTyp ret = null;
		if (S.isFull(dbVal)) {
			for (EnTransportTyp t: this.getHmTextEnum().keySet()) {
				if ( t.dbVal().equals(dbVal)) {
					ret = t;
				}
			}
		}
		return ret;
	}

	public EnBesitzerTyp getBesitzerStart() {
		return besitzerStart;
	}

	public EnBesitzerTyp getBesitzerMitte() {
		return besitzerMitte;
	}

	public EnBesitzerTyp getBesitzerZiel() {
		return besitzerZiel;
	}
	

	public boolean needsFremdbesitzer() {
		if (this.besitzerStart==EnBesitzerTyp.FREMDFIRMA ||this.besitzerMitte==EnBesitzerTyp.FREMDFIRMA || this.besitzerZiel==EnBesitzerTyp.FREMDFIRMA) {
			return true;
		}
		return false;
	}
	
	
	public Long findBesitzerStart(HMAP<EnBesitzerTyp, Long> map) {
		return map.get(this.besitzerStart);
	}
	
	
	public Long findBesitzerMid(HMAP<EnBesitzerTyp, Long> map) {
		return map.get(this.besitzerMitte);
	}
	
	public Long findBesitzerZiel(HMAP<EnBesitzerTyp, Long> map) {
		return map.get(this.besitzerZiel);
	}
	

	
	public String getSBesitzerStart(HMAP<EnBesitzerTyp, Long> map) {
		Long l = this.findBesitzerStart(map);
		if (l==null) {
			return "";
		} else {
			return l.toString();
		}
	}
	
	
	public String getSBesitzerMid(HMAP<EnBesitzerTyp, Long> map) {
		Long l = this.findBesitzerMid(map);
		if (l==null) {
			return "";
		} else {
			return l.toString();
		}
	}
	
	public String getSBesitzerZiel(HMAP<EnBesitzerTyp, Long> map) {
		Long l = this.findBesitzerZiel(map);
		if (l==null) {
			return "";
		} else {
			return l.toString();
		}
	}
	
	
	public String getBesitzerStrukturText() {
		String ret = "";
		ret = ret+"Startseite: "+	this.besitzerStart.userTextLang()+", ";
		ret = ret+"Mitte: "+		this.besitzerMitte.userTextLang()+", ";
		ret = ret+"Zielseite: "+	this.besitzerZiel.userTextLang()+"";
		return ret;
	}

	public boolean isSortenGleichheit() {
		return sortenGleichheit;
	}

	public boolean isAllowKontraktAngebotLeft() {
		return allowKontraktAngebotLeft;
	}

	public boolean isAllowKontraktAngebotRight() {
		return allowKontraktAngebotRight;
	}
	
	
}
