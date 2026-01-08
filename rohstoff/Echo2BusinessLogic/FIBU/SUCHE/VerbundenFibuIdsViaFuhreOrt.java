package rohstoff.Echo2BusinessLogic.FIBU.SUCHE;

import java.util.HashSet;
import java.util.Iterator;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.exceptions.myException;

public class VerbundenFibuIdsViaFuhreOrt extends AbstractFibuIdSuche{

	public VerbundenFibuIdsViaFuhreOrt(String fibuId) throws myException {
		super(fibuId);
	}	

	public VerbundenFibuIdsViaFuhreOrt() {
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
				String erg = ergebnisRecord.get_ID_VPOS_TPA_FUHRE_ORT_ZUGEORD_cUF_NN(NON_NULL_CHAR);

				if(! equals(NON_NULL_CHAR))vposStornoesVektor.add(erg);
			}
		}
		return vposStornoesVektor;
	}

	public HashSet<String> sucheVerbundenVkopfRgId(){
		HashSet<String> vKopfSet = new HashSet<String>();
		Iterator<String> it = getIntermediarErgebnisVektor().iterator();

		while(it.hasNext()){
			String vposRgId = it.next();
			if(! vposRgId.equals(NON_NULL_CHAR)){
				try {
					RECLIST_VPOS_RG recordList = new  RECLIST_VPOS_RG("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD="+vposRgId, "ID_VKOPF_RG");
					for(RECORD_VPOS_RG rec: recordList){
						String erg = rec.get_ID_VKOPF_RG_cF_NN(NON_NULL_CHAR);

						vKopfSet.add(erg);
					}
				} catch (myException e) {}
			}
		}
		return vKopfSet;
	}

}
