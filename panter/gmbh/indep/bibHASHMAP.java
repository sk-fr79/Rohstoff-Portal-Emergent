package panter.gmbh.indep;

import java.util.HashMap;
import java.util.Map.Entry;

import panter.gmbh.basics4project.DEBUG;

public class bibHASHMAP {

	public static boolean Are_Equal_HASHMAPS(HashMap<String, String> hm1, HashMap<String, String> hm2) {
		boolean bRueck = false;
		
		if (hm1==null && hm2==null) {
			bRueck = true;
		} else if (hm1!=null && hm2==null){
			bRueck = false;
		} else if (hm1==null && hm2!=null){
			bRueck = false;
		} else if (hm1.size()!=hm2.size()) {
			bRueck = false;
		} else {
			boolean bGleich = true;
			
			for (Entry<String, String> oEntry: hm1.entrySet()) {
				if (hm2.containsKey(oEntry.getKey())) {
					if (!(S.NN(hm1.get(oEntry.getKey())).equals(S.NN(hm2.get(oEntry.getKey()))))) {
						bGleich = false;
						break;
					}
				} else {
					bGleich = false;
					break;
				}
			}
			bRueck = bGleich;
		}
		return bRueck;
	}
	
	
	/**
	 * 
	 * @param hm1
	 * @param hm2
	 * @return true, wenn in beiden hashmaps die gleichen elemente den gleichen inhalt haben,
	 *         damit wird verhindert, dass die nach jedem Klick veraenderten elemente den vergleich torpedieren
	 */
	public static boolean Are_Equal_HASHMAPS_INTERSECT(HashMap<String, String> hm1, HashMap<String, String> hm2) {
		boolean bRueck = false;
		
		if (hm1==null && hm2==null) {
			bRueck = true;
		} else if (hm1!=null && hm2==null){
			bRueck = false;
		} else if (hm1==null && hm2!=null){
			bRueck = false;
		} else {
			boolean bGleich = true;
			
			for (Entry<String, String> oEntry: hm1.entrySet()) {
				if (hm2.containsKey(oEntry.getKey())) {
					if (!(S.NN(hm1.get(oEntry.getKey())).equals(S.NN(hm2.get(oEntry.getKey()))))) {
						bGleich = false;
						
						//test
						DEBUG.System_println("<@HashmapsCompare>"+oEntry.getKey()+":"+S.NN(hm1.get(oEntry.getKey()))+" <-->"+S.NN(hm2.get(oEntry.getKey())));
						//test
						
						break;
					}
				} 
			}
			bRueck = bGleich;
		}
		return bRueck;
	}
	


	
	
	public static HashMap<String, String> get_Diff_HASHMAP_Entrys(HashMap<String, String> hm1, HashMap<String, String> hm2) {
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		if (hm1==null && hm2==null) {
		} else if (hm1!=null && hm2==null){
		} else if (hm1==null && hm2!=null){
		} else if (hm1.size()!=hm2.size()) {
		} else {
			for (Entry<String, String> oEntry: hm1.entrySet()) {
				if (hm2.containsKey(oEntry.getKey())) {
					if (!(S.NN(hm1.get(oEntry.getKey())).equals(S.NN(hm2.get(oEntry.getKey()))))) {
						hmRueck.put(oEntry.getKey(), oEntry.getValue()+" ("+hm2.get(oEntry.getKey())+")");
					}
				} else {
					hmRueck.put(oEntry.getKey(), oEntry.getValue()+" (nicht vorhanden in 2)");
				}
			}
		}
		return hmRueck;
	}
	
	
	public static HashMap<String, String> get_Diff_HASHMAP_Entrys_INTERSECT(HashMap<String, String> hm1, HashMap<String, String> hm2) {
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		if (hm1==null && hm2==null) {
		} else if (hm1!=null && hm2==null){
		} else if (hm1==null && hm2!=null){
		} else if (hm1.size()!=hm2.size()) {
		} else {
			for (Entry<String, String> oEntry: hm1.entrySet()) {
				if (hm2.containsKey(oEntry.getKey())) {
					if (!(S.NN(hm1.get(oEntry.getKey())).equals(S.NN(hm2.get(oEntry.getKey()))))) {
						hmRueck.put(oEntry.getKey(), oEntry.getValue()+" ("+hm2.get(oEntry.getKey())+")");
					}
				}
			}
		}
		return hmRueck;
	}
	
	/**
	 * baut eine HashMap<String, String> aus den array-eintragen, key=[n][pos_key], value=[n][pos_val] weitere werden ignoriert
	 * @param s_array
	 * @return
	 */
	public static HashMap<String, String> get_HashMap(String[][] s_array , int pos_key, int pos_val) {
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		if (s_array!=null && s_array.length>0 && s_array[0].length>pos_key && s_array[0].length>pos_val)
		for (int i=0; i<s_array.length;i++) {
			hmRueck.put(s_array[i][pos_key], s_array[i][pos_val]);
		}
		return hmRueck;
	}
	

	
}
