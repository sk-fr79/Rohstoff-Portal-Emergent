package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class CHECK_NurFertigeOderOffene {

	private long 				iAnzahlNEU = 0;
	private long 				iAnzahlBereitsGedruckt = 0;
	private long                iAnzahlLeeresLeistungsdatum = 0;
	
	private Vector<String>  	vID_VKOPF_RG =  new Vector<String>();
	
	public CHECK_NurFertigeOderOffene(Vector<String> vID_RechKopf) throws myException {
		super();
		this.vID_VKOPF_RG.addAll(vID_RechKopf);
		
		if (this.vID_VKOPF_RG.size() > 0)
		{
			String cInString = "("+bibALL.Concatenate(this.vID_VKOPF_RG, ",", "0")+")";
			
			String cAnzahl_N = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG IN "+cInString+" AND NVL(ABGESCHLOSSEN,'N')='N' AND NVL(DELETED,'N')='N'");
			String cAnzahl_Y = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG IN "+cInString+" AND NVL(ABGESCHLOSSEN,'N')='Y' AND NVL(DELETED,'N')='N'");
			String cAnzahl_ohne_Leistungsdatum = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG IN "+cInString+" AND NVL(DELETED,'N')='N' AND "+_DB.VPOS_RG$AUSFUEHRUNGSDATUM+" IS NULL");
			
			if (bibALL.isLong(cAnzahl_N) && bibALL.isLong(cAnzahl_Y) && bibALL.isLong(cAnzahl_ohne_Leistungsdatum))
			{
				iAnzahlNEU = new Long(cAnzahl_N).longValue();
				iAnzahlBereitsGedruckt = new Long(cAnzahl_Y).longValue();
				this.iAnzahlLeeresLeistungsdatum = new Long(cAnzahl_ohne_Leistungsdatum);
			}
			else
			{
				throw new myException(this,"Error quering ABGESCHLOSSEN-Status !");
			}
		}
	}


	public long get_iAnzahlSelektiert() {
		return this.vID_VKOPF_RG.size();
	}
	

	public long get_iAnzahlNEU() {
		return iAnzahlNEU;
	}

	public long get_iAnzahlBereitsGedruckt() {
		return iAnzahlBereitsGedruckt;
	}
	
	public long get_iAnzahlLeeresLeistungsdatum() {
		return iAnzahlLeeresLeistungsdatum;
	}

	public boolean get_bNurNeue() {
		return (this.iAnzahlNEU>0 && this.iAnzahlBereitsGedruckt==0);
	}
	
	public boolean get_bNurGedruckte() {
		return (this.iAnzahlNEU==0 && this.iAnzahlBereitsGedruckt>0);
	}

	public boolean get_bGemischteAuswahl() {
		return (this.iAnzahlNEU>0 && this.iAnzahlBereitsGedruckt>0);
	}

	public boolean get_bOhneLeistungsdatumVorhanden() {
		return (this.iAnzahlLeeresLeistungsdatum>0);
	}

	
	
	
}
