package rohstoff.Echo2BusinessLogic.FIBU.SUCHE;

import java.util.HashSet;
import java.util.Iterator;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.exceptions.myException;

public class VerbundenFibuIdsViaVposRg extends AbstractFibuIdSuche{
	
	public VerbundenFibuIdsViaVposRg(String fibuId) throws myException {
		super(fibuId); 
	}	

	public VerbundenFibuIdsViaVposRg() {
		super();
	}
	
	@Override
	public HashSet<String> sucheVerbundenVposIds() throws myException {
		HashSet<String> vposStornoesVektor = new HashSet<String>();
		RECLIST_VPOS_RG ergebnisReclist = new RECLIST_VPOS_RG("ID_VKOPF_RG=" + getvKopRgId(), "ID_VKOPF_RG");
		
		Iterator<RECORD_VPOS_RG> it = ergebnisReclist.iterator();
		
		while(it.hasNext()){
			
			RECORD_VPOS_RG ergebnisRecord = it.next();
			
			if(! ergebnisRecord.get_DELETED_cUF().equals('N')){
				String vorErg = sucheVposRgStornoVorgaenger(ergebnisRecord);
				String nachErg = sucheVposRgStornoNachfolger(ergebnisRecord);
				if(! vorErg.equals(NON_NULL_CHAR))vposStornoesVektor.add(vorErg);
				if(! nachErg.equals(NON_NULL_CHAR))vposStornoesVektor.add(nachErg);
			}
		}
		return vposStornoesVektor;
	}
	
	public String sucheVposRgStornoVorgaenger(RECORD_VPOS_RG vposRgRecord) throws myException{
		return vposRgRecord.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN(NON_NULL_CHAR);
	}
	
	public String sucheVposRgStornoNachfolger(RECORD_VPOS_RG vposRgRecord) throws myException{
		return vposRgRecord.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN(NON_NULL_CHAR);
	}
}
