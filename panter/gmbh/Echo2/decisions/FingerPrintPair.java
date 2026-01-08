package panter.gmbh.Echo2.decisions;

import java.util.HashMap;

import panter.gmbh.indep.bibHASHMAP;

public class FingerPrintPair
{
	private FingerPrint hm_Fingerprint1 = null;
	private FingerPrint hm_Fingerprint2 = null;
	
	public HashMap<String, String> get_hmFingerprint1() {
		return hm_Fingerprint1;
	}
	public void set_hmFingerprint1(FingerPrint hmFingerprint1) {
		this.hm_Fingerprint1 = hmFingerprint1;
	}
	public HashMap<String, String> get_hmFingerprint2() {
		return hm_Fingerprint2;
	}
	public void set_hmFingerprint2(FingerPrint hmFingerprint2) {
		this.hm_Fingerprint2 = hmFingerprint2;
	}

	public boolean get_ARE_Fingerprints_Equal() {
		if (this.hm_Fingerprint1==null || this.hm_Fingerprint2==null) {
			return false;
		} else {
			return bibHASHMAP.Are_Equal_HASHMAPS_INTERSECT(this.hm_Fingerprint1, this.hm_Fingerprint2);
		}
	}
	
	
	
	
	public boolean get_ARE_Fingerprints_Equal(HashMap<String, String> hmFingerPrintVergleich) {
		if (this.hm_Fingerprint1==null || this.hm_Fingerprint2==null) {
			return false;
		} else {
			return (bibHASHMAP.Are_Equal_HASHMAPS_INTERSECT(this.hm_Fingerprint1, hmFingerPrintVergleich) &&
					bibHASHMAP.Are_Equal_HASHMAPS_INTERSECT(this.hm_Fingerprint2, hmFingerPrintVergleich));
		}
	}
	
}
