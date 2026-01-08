package rohstoff.utils;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERADRESSE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class bibROHSTOFF
{
	
//	RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_LIEFER  + " > 999 ","",true
	

	/**
	 * Gibt einen Vector von Adress-IDs zurück, die nur die eigenen (Mandant)-Lagerorte incl. der Hauptadresse,
	 * beinhalten
	 * manfred 2013-06-07: die Sonderlager werden ausgeblendet
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get_vEigeneLieferadressen() throws myException
	{
		return get_vEigeneLieferadressen(false);
	}
	

	
	/**
	 * Gibt einen Vector von Adress-IDs zurück, die nur die eigenen (Mandant)-Lagerorte incl. der Hauptadresse
	 * beinhalten
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get_vEigeneLieferadressen(boolean bIncludeSonderlager) throws myException
	{
		Vector<String> vIDs = new Vector<String>();

		try {
			String sWhere = "";
			if (!bIncludeSonderlager){
				sWhere  = RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_LIEFER  + " > 999 " ;
			}
			RECORD_ADRESSE oRecHelp = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"));
			vIDs.addAll(bibALL.get_vBuildValueVectorFromHashmap(oRecHelp.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis(sWhere,"",true).get_ID_ADRESSE_LIEFER_hmString_UnFormated(null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		vIDs.add(bibALL.get_ID_ADRESS_MANDANT());
		return vIDs;	
	}	
	
	

	/**
	 * Erzeugt eine Liste von AdressIDs die die gewählten Sonderlager entsprechen
	 * @param bStrecke
	 * @param bLL
	 * @param bMixed
	 * @param bMengenKorrektur
	 * @param bKorekturHand
	 * @param bUmbuchungHand
	 * @param bSortenwechsel
	 * @return
	 */
	public static Vector<String> get_vSonderlagerAdressen(	boolean bStrecke, 
															boolean bLL, 
															boolean bMixed,
															boolean bMengenKorrektur, 
															boolean bKorekturHand,
															boolean bUmbuchungHand,
															boolean bSortenwechsel
															){
		Vector<String> vIDs = new Vector<String>();
		
		try {
			String sWhere = ",'**'";  // dummy, damit immer ein IN-Statement entsteht, der im notfall keine Werte zurückbringt.
			sWhere += bStrecke ? ",'STRECKE'": "";
			sWhere += bLL ? ",'LL'": "";
			sWhere += bMixed ? ",'MI'": "";
			sWhere += bMengenKorrektur ? ",'MK'": "";
			sWhere += bKorekturHand ? ",'KH'": "";
			sWhere += bUmbuchungHand ? ",'UH'": "";
			sWhere += bSortenwechsel ? ",'SW'": "";
			
			if (sWhere.startsWith(",")){
				sWhere = "(" + sWhere.substring(1) + ")";
				sWhere = RECORD_ADRESSE.FIELD__SONDERLAGER +  " in " + sWhere;
			}
			
			RECLIST_ADRESSE oReclist = new RECLIST_ADRESSE(sWhere,"");
			vIDs.addAll(bibALL.get_vBuildValueVectorFromHashmap(oReclist.get_ID_ADRESSE_hmString_UnFormated(null)));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vIDs;
	}
	
	
	
	
	/**
	 * Gibt die Adress-IDs der Eigenwaren-Lager zurück 
	 * @author manfred
	 * @date   25.11.2013
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get_vLieferadressenEigenwarenlager() throws myException
	{
		Vector<String> vIDs = new Vector<String>();
		
		try {
			String sWhere = RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_FREMDWARE +  " is null ";
			RECORD_ADRESSE oRecHelp = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"));
			vIDs.addAll(bibALL.get_vBuildValueVectorFromHashmap(oRecHelp.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis(sWhere,"",true).get_ID_ADRESSE_LIEFER_hmString_UnFormated(null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		vIDs.add(bibALL.get_ID_ADRESS_MANDANT());
		return vIDs;	
	}	

	
	/**
	 * Gibt die Adress-IDs der Fremdwaren-Lager zurück
	 * @author manfred
	 * @date   25.11.2013
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get_vLieferadressenFremdwarenlager() throws myException
	{
		Vector<String> vIDs = null;
		
		try {
			String sWhere = RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_FREMDWARE +  " is not null ";
			RECORD_ADRESSE oRecHelp = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"));
			vIDs = new Vector<String>();
			vIDs.addAll(bibALL.get_vBuildValueVectorFromHashmap(oRecHelp.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis(sWhere,"",true).get_ID_ADRESSE_LIEFER_hmString_UnFormated(null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vIDs;	
	}	

	/**
	 * Gibt die Adress-IDs der Sonderlager zurück
	 * @author manfred
	 * @date   25.11.2013
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get_vLieferadressenSonderlager() 
	{
		Vector<String> vIDs = null;
		String sWhere;
		RECORD_ADRESSE oRecHelp;
		
		try {
			sWhere = RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_LIEFER  +  " < 999 ";
			oRecHelp = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1"));
			vIDs = new Vector<String>();
			vIDs.addAll(bibALL.get_vBuildValueVectorFromHashmap(oRecHelp.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis(sWhere,"",true).get_ID_ADRESSE_LIEFER_hmString_UnFormated(null)));

		} catch (myException e) {
			e.printStackTrace();
		}
		
		return vIDs;	
	}	
	
	
// LAGER-Abfragen sind in bibSES definiert
	
//	/**
//	 * Gibt die Adress-ID des Streckenlagers zurück
//	 * @author manfred
//	 * @date   25.11.2013
//	 * @return
//	 * @throws myException
//	 */
//	public static String get_IDStreckenlager()
//	{
//		String sRet = null;
//		
//		try {
//			RECORD_ADRESSE oRecHelp = new RECORD_ADRESSE("trim(" + RECORD_ADRESSE.FIELD__SONDERLAGER + ") = 'STRECKE'");
//			sRet = oRecHelp.get_ID_ADRESSE_cUF();
//		} catch (myException e) {
//			e.printStackTrace();
//		}	
//		
//		return sRet;
//	}	
	
	
	
	/**
	 * gibt eine Liste von Lagerorten zurück, die mit dem Kennzeichen "LAGER_VERBUCHUNG" = "Y" gekennzeichnet sind,
	 * und nicht zum Mandanten gehören.
	 * 
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get_vFremdeLieferadressen() throws myException
	{
		
		RECLIST_LIEFERADRESSE list = new RECLIST_LIEFERADRESSE( RECORD_LIEFERADRESSE.FIELD__ID_ADRESSE_BASIS + " != " + bibALL.get_ID_ADRESS_MANDANT() + 
				" AND NVL(" + RECORD_LIEFERADRESSE.FIELD__LAGER_VERBUCHUNG + ",'N') = 'Y' " 
				, (String) null);
		
		return list.get_vKeyValues();	
	}	
	
}
