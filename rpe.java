
import java.util.HashMap;
import java.util.Vector;



/*
 * statische klasse zur benutzung in jasper-reports
 * steht im root-pfad und enthaelt nur statische methoden oder klassen.
 * muss via jar-file in die jasper-report-umgebung eingebunden werden 
 */

public class rpe 
{
	
	public static String NN(String cInput) 
	{
		if (cInput==null)
		{
			return "";
		}
		return cInput;
	} 
	
	/**
	 * 
	 * @param cInput1
	 * @param cInput2
	 * @param cTrenner
	 * @param cWerteWhenAlleNull
	 * @return
	 */
	public static String NN(String cInput1, String cInput2, String cTrenner, String cWerteWhenAlleNull) 
	{
		if (rpe.isEmpty(cInput1) && rpe.isEmpty(cInput2)) {	
			return cWerteWhenAlleNull;
		} else if (rpe.isEmpty(cInput1))	{
			return cInput2;
		} else if (rpe.isEmpty(cInput2))	{
			return cInput1;
		} else {	
			return cInput1+cTrenner+cInput2;
		}
	} 
	
	
	

	/**
	 * 
	 * @param cInput1
	 * @param cInput2
	 * @param cInput3
	 * @param cTrenner
	 * @param cWerteWhenAlleNull
	 * @return
	 */
	public static String NN(String cInput1, String cInput2, String cInput3, String cTrenner, String cWerteWhenAlleNull) 
	{
		Vector<String>  vValues = new Vector<String>();
		if (rpe.isFull(cInput1)) {vValues.add(cInput1);}
		if (rpe.isFull(cInput2)) {vValues.add(cInput2);}
		if (rpe.isFull(cInput3)) {vValues.add(cInput3);}

		if (vValues.size()==0) {
			return cWerteWhenAlleNull;
		} else {
			String cRueck = "";
			for (int i=0; i<vValues.size();i++) {
				if (i==0) {
					cRueck += vValues.get(i);
				} else {
					cRueck += (cTrenner+vValues.get(i));
				}
			}
			return cRueck;
		}
	} 

	
	
	

	/**
	 * 
	 * @param cInput1
	 * @param cInput2
	 * @param cInput3
	 * @param cInput4
	 * @param cTrenner
	 * @param cWerteWhenAlleNull
	 * @return
	 */
	public static String NN(String cInput1, String cInput2, String cInput3, String cInput4, String cTrenner, String cWerteWhenAlleNull) 
	{
		Vector<String>  vValues = new Vector<String>();
		if (rpe.isFull(cInput1)) {vValues.add(cInput1);}
		if (rpe.isFull(cInput2)) {vValues.add(cInput2);}
		if (rpe.isFull(cInput3)) {vValues.add(cInput3);}
		if (rpe.isFull(cInput4)) {vValues.add(cInput4);}

		if (vValues.size()==0) {
			return cWerteWhenAlleNull;
		} else {
			String cRueck = "";
			for (int i=0; i<vValues.size();i++) {
				if (i==0) {
					cRueck += vValues.get(i);
				} else {
					cRueck += (cTrenner+vValues.get(i));
				}
			}
			return cRueck;
		}
	} 

	

	/**
	 * 
	 * @param cInput1
	 * @param cInput2
	 * @param cInput3
	 * @param cInput4
	 * @param cInput5
	 * @param cTrenner
	 * @param cWerteWhenAlleNull
	 * @return
	 */
	public static String NN(String cInput1, String cInput2, String cInput3, String cInput4, String cInput5, String cTrenner, String cWerteWhenAlleNull) 
	{
		Vector<String>  vValues = new Vector<String>();
		if (rpe.isFull(cInput1)) {vValues.add(cInput1);}
		if (rpe.isFull(cInput2)) {vValues.add(cInput2);}
		if (rpe.isFull(cInput3)) {vValues.add(cInput3);}
		if (rpe.isFull(cInput4)) {vValues.add(cInput4);}
		if (rpe.isFull(cInput5)) {vValues.add(cInput5);}

		if (vValues.size()==0) {
			return cWerteWhenAlleNull;
		} else {
			String cRueck = "";
			for (int i=0; i<vValues.size();i++) {
				if (i==0) {
					cRueck += vValues.get(i);
				} else {
					cRueck += (cTrenner+vValues.get(i));
				}
			}
			return cRueck;
		}
	} 


	

	/**
	 * 
	 * @param cInput1
	 * @param cInput2
	 * @param cInput3
	 * @param cInput4
	 * @param cInput5
	 * @param cInput6
	 * @param cTrenner
	 * @param cWerteWhenAlleNull
	 * @return
	 */
	public static String NN(String cInput1, String cInput2, String cInput3, String cInput4, String cInput5, String cInput6,  String cTrenner, String cWerteWhenAlleNull) 
	{
		Vector<String>  vValues = new Vector<String>();
		if (rpe.isFull(cInput1)) {vValues.add(cInput1);}
		if (rpe.isFull(cInput2)) {vValues.add(cInput2);}
		if (rpe.isFull(cInput3)) {vValues.add(cInput3);}
		if (rpe.isFull(cInput4)) {vValues.add(cInput4);}
		if (rpe.isFull(cInput5)) {vValues.add(cInput5);}
		if (rpe.isFull(cInput6)) {vValues.add(cInput6);}

		if (vValues.size()==0) {
			return cWerteWhenAlleNull;
		} else {
			String cRueck = "";
			for (int i=0; i<vValues.size();i++) {
				if (i==0) {
					cRueck += vValues.get(i);
				} else {
					cRueck += (cTrenner+vValues.get(i));
				}
			}
			return cRueck;
		}
	} 


	

	

	/**
	 * prueft zwei datumswerte, wenn einer leer ist, dann null, sonst von bis oder bei gleichheit das datum
	 * @param cDatum1_formated
	 * @param cDatum2_formated
	 * @return
	 */
	public static String DAT_VON_BIS(String cDatum1_formated, String cDatum2_formated) 
	{
		if (rpe.isEmpty(cDatum1_formated) || rpe.isEmpty(cDatum2_formated)) {	
			return null;
		} else if (!cDatum1_formated.equals(cDatum2_formated))	{
			return cDatum1_formated+" - "+cDatum2_formated;
		} else {	
			return cDatum1_formated;
		}
	} 
	

	
	/**
	 * Reporting-Methode, uebernimmt HashMap<String, String>, einen key und gibt den gefundenen wert zurueck oder valWhenEmpty
	 * @param hm
	 * @param key
	 * @param valWhenEmpty
	 * @return
	 */
	public static String read(HashMap<String, String> hm, String key, String valWhenEmpty) {
		String c_rueck = rpe.NN(valWhenEmpty); 
		
		if (hm!=null && rpe.isFull(key)) {
			if (rpe.isFull(hm.get(key))) {
				c_rueck = hm.get(key);
			}
		}
		
		return c_rueck;
	}
	
	


	public static boolean isEmpty(String cTestWert)
	{
		return (cTestWert == null || cTestWert.trim().equals(""));
	}
	
	public static boolean isFull(String cTestWert)
	{
		return (!rpe.isEmpty(cTestWert));
	}

	
}
