package rohstoff.Echo2BusinessLogic.FIBU.SUCHE;

import java.util.HashSet;
import java.util.Iterator;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.exceptions.myException;

public abstract class AbstractFibuIdSuche {
	
	private String fibuId;
	private String vKopRgId;
	
	private HashSet<String> ergebnisFibuId;
	protected HashSet<String> intermediarErgebnisVektor;
	
	public final static String NON_NULL_CHAR = "?";
	
	public AbstractFibuIdSuche(String pfibuId) throws myException {
		this.fibuId = pfibuId;
		this.ergebnisFibuId = new HashSet<String>();
		this.intermediarErgebnisVektor = new HashSet<String>();
		
	}
	
	public AbstractFibuIdSuche() {
		this.ergebnisFibuId = new HashSet<String>();
		this.intermediarErgebnisVektor = new HashSet<String>();
	}

	public abstract HashSet<String> sucheVerbundenVposIds() throws myException;
	
	public HashSet<String> suche() throws myException{
		getErgebnisFibuId().clear();
		
		if(!sucheVKopfRg().equals("")){
			intermediarErgebnisVektor = sucheVerbundenVposIds();
			HashSet<String> vKopfIdsVector = sucheVerbundenVkopfRgId();
			sucheFibuIds(vKopfIdsVector);
		}
		return getErgebnisFibuId();
	}

	public String sucheVKopfRg() throws myException{
		RECORD_FIBU fibuRecord = new RECORD_FIBU(fibuId);
		if(fibuRecord.get_VORLAEUFIG_cUF().equals("N")){
			vKopRgId = fibuRecord.get_ID_VKOPF_RG_cUF_NN(NON_NULL_CHAR);
			
			return vKopRgId;
		}else return "";
		
	}
	
	public HashSet<String> sucheVerbundenVkopfRgId(){
		HashSet<String> vKopfSet = new HashSet<String>();
		Iterator<String> it = intermediarErgebnisVektor.iterator();
		
		while(it.hasNext()){
			String vposRgId = it.next();
			if(! vposRgId.equals(NON_NULL_CHAR)){
				try {
					RECORD_VPOS_RG record = new  RECORD_VPOS_RG("ID_VPOS_RG="+vposRgId);
					vKopfSet.add(record.get_ID_VKOPF_RG_cUF_NN(NON_NULL_CHAR));
				} catch (myException e) {}
			}
		}
		return vKopfSet;
	}
	
	public HashSet<String> sucheFibuIds(HashSet<String> vKopfIdsVector){
		Iterator<String> it = vKopfIdsVector.iterator();
		while(it.hasNext()){
			String vkopfId = it.next();
			if(!vkopfId.equals(NON_NULL_CHAR)){
				RECORD_FIBU fibuRecord;
				try {
					fibuRecord = new RECORD_FIBU("ID_VKOPF_RG =" + vkopfId);
					
					getErgebnisFibuId().add(fibuRecord.get_ID_FIBU_cUF_NN(NON_NULL_CHAR));
				} catch (myException e){}
			}
		}		
		return getErgebnisFibuId();
	}

	public String getvKopRgId() {
		return vKopRgId;
	}

	public void setvKopRgId(String vKopRgId) {
		this.vKopRgId = vKopRgId;
	}

	public String getFibuId() {
		return fibuId;
	}

	public void setFibuId(String fibuId) {
		this.fibuId = fibuId;
	}

	public HashSet<String> getErgebnisFibuId() {
		return ergebnisFibuId;
	}

	public void setErgebnisFibuId(HashSet<String> ergebnisFibuId) {
		this.ergebnisFibuId = ergebnisFibuId;
	}

	public HashSet<String> getIntermediarErgebnisVektor() {
		return intermediarErgebnisVektor;
	}

	public void setIntermediarErgebnisVektor(HashSet<String> intermediarErgebnisVektor) {
		this.intermediarErgebnisVektor = intermediarErgebnisVektor;
	}
}
