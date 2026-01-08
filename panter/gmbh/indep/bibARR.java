package panter.gmbh.indep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class bibARR
{

	public static String[][]  ARR_EMPTY_DB_VALUE_IN_FRONT = {{"-",""}};
	
	
	public static int[] get_Array(int i1)
	{
		int[] iRueck = new int[1];
		iRueck[0]=i1;
		return iRueck;
	}
	public static int[] get_Array(int i1,int i2)
	{
		int[] iRueck = new int[2];
		iRueck[0]=i1;
		iRueck[1]=i2;
		return iRueck;
	}
	public static int[] get_Array(int i1,int i2,int i3)
	{
		int[] iRueck = new int[3];
		iRueck[0]=i1;
		iRueck[1]=i2;
		iRueck[2]=i3;
		return iRueck;
	}
	public static int[] get_Array(int i1,int i2,int i3,int i4)
	{
		int[] iRueck = new int[4];
		iRueck[0]=i1;
		iRueck[1]=i2;
		iRueck[2]=i3;
		iRueck[3]=i4;
		return iRueck;
	}
	public static int[] get_Array(int i1,int i2,int i3,int i4,int i5)
	{
		int[] iRueck = new int[5];
		iRueck[0]=i1;
		iRueck[1]=i2;
		iRueck[2]=i3;
		iRueck[3]=i4;
		iRueck[4]=i5;
		return iRueck;
	}
	public static int[] get_Array(int i1,int i2,int i3,int i4,int i5,int i6)
	{
		int[] iRueck = new int[6];
		iRueck[0]=i1;
		iRueck[1]=i2;
		iRueck[2]=i3;
		iRueck[3]=i4;
		iRueck[4]=i5;
		iRueck[5]=i6;
		return iRueck;
	}
	public static int[] get_Array(int i1,int i2,int i3,int i4,int i5,int i6,int i7)
	{
		int[] iRueck = new int[7];
		iRueck[0]=i1;
		iRueck[1]=i2;
		iRueck[2]=i3;
		iRueck[3]=i4;
		iRueck[4]=i5;
		iRueck[5]=i6;
		iRueck[6]=i7;
		return iRueck;
	}


	
	/**
	 * @param hmHashMap
	 * @return erzeugt aus einem HashMap<String, String> ein String[][] - Array - in [0] steht der key
	 */
	public static String[][] get_Array_KeyValue(HashMap<String, String> hmHashMap, String[][] valuesInFront) {
		if (hmHashMap==null) {
			return null;
		}
		
		int iZusatz = 0;
		if (valuesInFront!=null) {
			iZusatz=valuesInFront.length;
		}
		
		String[][] cRueck = new String[hmHashMap.size()+iZusatz][2]; 
		
		Vector<String> vKeys = new Vector<String>();
		vKeys.addAll(hmHashMap.keySet());
		
		Collections.sort(vKeys);
		
		if (valuesInFront!=null) {
			for (int i=0;i<valuesInFront.length;i++){
				cRueck[i][0]=valuesInFront[i][0];
				cRueck[i][1]=valuesInFront[i][1];
			}
		}		
		
		int iTerator = iZusatz;
		for (String cKEY: vKeys) {
			cRueck[iTerator][0] = cKEY;
			cRueck[iTerator][1] = hmHashMap.get(cKEY);
			iTerator ++;
		}
		
		return cRueck;
	}
	
	/**
	 * @param hmHashMap
	 * @return erzeugt aus einem HashMap<String, String> ein String[][] - Array - in [0] steht der key
	 */
	public static String[][] get_Array_KeyValue(ArrayList<HashMap<String, String>> hmHashMap, String[][] valuesInFront) {
		if (hmHashMap==null) {
			return null;
		}
		
		int iZusatz = 0;
		if (valuesInFront!=null) {
			iZusatz=valuesInFront.length;
		}
		
		String[][] cRueck = new String[hmHashMap.size()+iZusatz][2]; 
		
		Vector<String> vKeys = new Vector<String>();
		
		if (valuesInFront!=null) {
			for (int i=0;i<valuesInFront.length;i++){
				cRueck[i][0]=valuesInFront[i][0];
				cRueck[i][1]=valuesInFront[i][1];
			}
		}		
		
		Iterator<HashMap<String, String>> it = hmHashMap.iterator();
		
		int iTerator = iZusatz;
		while (it.hasNext()) {
			HashMap<String, String> entry = it.next();
			Iterator<String> it2 = entry.keySet().iterator();
			while (it2.hasNext()) {
				String key = it2.next();
				String val = entry.get(key);
				cRueck[iTerator][0] = key;
				cRueck[iTerator][1] = val;
				iTerator ++;
			}
			
		}
		
		return cRueck;
	}	
	
	/**
	 * @param hmHashMap
	 * @return erzeugt aus einem HashMap<String, String> ein String[][] - Array- in [0] steht der value
	 */
	public static String[][] get_Array_ValueKey(HashMap<String, String> hmHashMap, String[][] valuesInFront) {
		if (hmHashMap==null) {
			return null;
		}
		
		int iZusatz = 0;
		if (valuesInFront!=null) {
			iZusatz=valuesInFront.length;
		}

		String[][] cRueck = new String[hmHashMap.size()+iZusatz][2]; 
		
		TreeMap<String, String>  trMAP = new TreeMap<String, String>(new bibARR.Sortierer(hmHashMap));
		trMAP.putAll(hmHashMap);
		
		Vector<String> vKeys = new Vector<String>();
		vKeys.addAll(trMAP.keySet());
		
		if (valuesInFront!=null) {
			for (int i=0;i<valuesInFront.length;i++){
				cRueck[i][0]=valuesInFront[i][0];
				cRueck[i][1]=valuesInFront[i][1];
			}
		}		
		
		int iTerator = iZusatz;
		for (String cKEY: vKeys) {
			cRueck[iTerator][1] = cKEY;
			cRueck[iTerator][0] = trMAP.get(cKEY);
			iTerator ++;
		}
		
		return cRueck;
	}

	
	private static class Sortierer  implements Comparator<String> {

		private HashMap<String, String> hmHashMap = null;
		public Sortierer(HashMap<String, String> hm_HashMap) {
			super();
			this.hmHashMap = hm_HashMap;
		}

		@Override
		public int compare(String o1, String o2)
		{
			return this.hmHashMap.get(o1).compareTo(this.hmHashMap.get(o2));
		}
		
	}
	
	
	/**
	 * haengt an beliebiges [n][2] - array den eintrag [][-] fuer DB-Dropdowns voran, das arrOrig darf nicht leer sein.
	 * @param arrOrig
	 * @return
	 * @throws myException
	 */
	public static String[][] add_emtpy_db_value_inFront(String[][] arrOrig) throws myException{
		return add_emtpy_db_value_inFront(arrOrig, false);
	}
	
	/**
	 * haengt an beliebiges [n][2] - array den eintrag [][-] fuer DB-Dropdowns voran
	 * @param arrOrig
	 * @param bAllowEmtyBaseArray - true wenn der Eintrag an ein leeres Array angehaengt werden soll, sonst false
	 * @return
	 * @throws myException
	 * 
	 */
	public static String[][] add_emtpy_db_value_inFront(String[][] arrOrig, boolean bAllowEmtyBaseArray) throws myException{
		// 
		if (!bAllowEmtyBaseArray && (arrOrig.length==0 || arrOrig[0].length!=2 )) {
			throw new myException("bibARR.add_emtpy_db_value_inFront: only NOT-Empty-Array with size [n][2] is allowed");
		}
		
		String[][] arrRueck = new String[arrOrig.length+1][2];
		
		arrRueck[0][0] = "-";
		arrRueck[0][1] = "";
		
		for (int i=0;i<arrOrig.length;i++) {
			arrRueck[i+1][0] = arrOrig[i][0];
			arrRueck[i+1][1] = arrOrig[i][1];
		}

		return arrRueck;
		
	}
	
	

	/**
	 * 
	 * @param arrOrig
	 * @param arrInFront
	 * @return
	 * @throws myException
	 */
	public static String[][] add_array_inFront(String[][] arrOrig, String[][] arrInFront) throws myException{
		
		if (arrOrig == null || arrInFront == null || arrOrig.length==0 || arrOrig[0].length!=2 || arrInFront.length==0 || arrInFront[0].length!=2 ) {
			throw new myException("bibARR.add_emtpy_db_value_inFront: only NOT-Empty-Array with size [n][2] is allowed");
		}
		
		String[][] arrRueck = new String[arrOrig.length+arrInFront.length][2];
		
		int z=0;
		for (int i=0;i<arrInFront.length;i++) {
			arrRueck[z][0] = arrInFront[i][0];
			arrRueck[z][1] = arrInFront[i][1];
			z++;
		}
		
		for (int i=0;i<arrOrig.length;i++) {
			arrRueck[z][0] = arrOrig[i][0];
			arrRueck[z][1] = arrOrig[i][1];
			z++;
		}

		return arrRueck;
		
	}

	
	
	/**
	 * weitere variante, laesst leeres original zu und gibt dann nur das frontArray zurueck  
	 * @param arrOrig
	 * @param arrInFront
	 * @return
	 * @throws myException
	 */
	public static String[][] add_array_inFront2(String[][] arrOrig, String[][] arrInFront) throws myException{

		
		if (arrInFront == null || arrInFront.length==0 || arrInFront[0].length!=2 ) {
			throw new myException("bibARR.add_emtpy_db_value_inFront: only NOT-Empty-Array in Front with size [n][2] is allowed");
		}

		if (arrOrig == null  || arrOrig.length==0 || arrOrig[0]==null) {
			return arrInFront;
		}

		if (arrOrig[0].length!=2) {
			throw new myException("bibARR.add_emtpy_db_value_inFront: only arrays [n][2] can be extended");
		}
		
		String[][] arrRueck = new String[arrOrig.length+arrInFront.length][2];
		
		int z=0;
		for (int i=0;i<arrInFront.length;i++) {
			arrRueck[z][0] = arrInFront[i][0];
			arrRueck[z][1] = arrInFront[i][1];
			z++;
		}
		
		for (int i=0;i<arrOrig.length;i++) {
			arrRueck[z][0] = arrOrig[i][0];
			arrRueck[z][1] = arrOrig[i][1];
			z++;
		}

		return arrRueck;
		
	}

	
	
	
	/**
	 * 
	 * @param cArrayBase
	 * @param iCountToReturn
	 * @return die ersten iCountToReturn-Zeilen eines Arrays
	 */
	public static String[][] get_First_N_ArrayPositions(String[][] cArrayBase, int i_N_CountToReturn) {
		if (cArrayBase.length<=i_N_CountToReturn) {
			return cArrayBase;
		} else {
			String[][] cRueck = new String[i_N_CountToReturn][];
			
			for (int i=0;i<i_N_CountToReturn;i++) {
				cRueck[i]=cArrayBase[i];
			}
			return cRueck;
		}
	}
	
	
	public static int[] ia(int... i) {
		int[] i_rueck = new int[i.length];
		
		int i_pos =0;
		for (int l: i) {
			i_rueck[i_pos++]=l;
		}
		return i_rueck;
	}
	
	
	
	
	public static String[] concatenate(String[] a1, String[] a2) {
		
		String[] arr = new String[a1.length+a2.length];
		
		int i = 0;
		for (String c: a1) {
			arr[i++]=c;
		}
		for (String c: a2) {
			arr[i++]=c;
		}
		
		return arr;
		
	}
	
	
	/**
	 * 
	 * @param arrayOld
	 * @param c
	 * @return
	 */
	public static String[][] append(String[][] arrayOld, String ... c) {
		String[][] c_ret = new String[arrayOld.length+1][];
		
		for (int i=0;i<arrayOld.length;i++) {
			c_ret[i] = new String[arrayOld[i].length];
			for (int k=0;k<arrayOld[i].length;k++) {
				c_ret[i][k]=arrayOld[i][k];
			}
		}
		c_ret[arrayOld.length] = new String[c.length];
		for (int k=0;k<c.length;k++) {
			c_ret[arrayOld.length][k]=c[k];
		}
		return c_ret;
	}
	
	
	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static String[][] get1x2Array(String c1, String c2) {
		String[][] c = new String[1][2];
		c[0][0]=c1;
		c[0][1]=c2;
		return c;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 06.11.2018
	 *
	 * @param s
	 * @param valueToRemove
	 * @return s array without rows, where valueToRemove is contained anywhere
	 * 
	 */
	public static String[][] removeLinesContaining(String[][] s, String valueToRemove) {
		if (s==null) {
			return s;
		}
		
		if (S.isEmpty(valueToRemove)) {
			return s;
		}
		
		try {
			VEK<Integer> vLinesOk = new VEK<>();
			
			for (int i=0;i<s.length;i++) {
				boolean ok = true;
				for (int k=0;k<s[i].length;k++) {
					if (s[i][k].equals(valueToRemove)) {
						ok = false;
					}
				}
				
				if (ok) {
					vLinesOk.add(i);
				} else {
					DEBUG._print("rausgelasen: "+i);
				}
				
			}
			
			String[][] ret = new String[vLinesOk.size()][s[0].length];
			
			int count = 0;
			for (int i=0;i<s.length;i++) {
				if (vLinesOk.contains(new Integer(i))){
					ret[count++]=s[i];
				}
			}
			
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
