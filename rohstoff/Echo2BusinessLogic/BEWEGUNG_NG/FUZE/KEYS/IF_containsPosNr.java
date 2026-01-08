package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS;

import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public interface IF_containsPosNr<T> {

	public T 		setPosNr(Long posNr);
	public Long 	getPosNr();
	public Rec20    getRec20() throws myException;
	
	
}
