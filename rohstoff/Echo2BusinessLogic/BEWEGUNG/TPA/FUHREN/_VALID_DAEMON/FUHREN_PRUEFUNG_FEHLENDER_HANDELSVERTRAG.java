package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__CHECK_MustWarn_AVV_Kontrakt;



/**
 * 2012-03-27:
 * die pruefung eines handelsvertrages erfolgt nur, wenn der Mandantenschalter VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG auf Y steht,
 * sonst greift die maskenpruefung mit warnhinweis und ueberstimmungsmoeglichkeit. diese ueberstimmungsmoeglichkeit kann fuer die sonderfaelle
 *  <handel mit produkten> benutzt werden.
 *  Hier ist der pruefung mit check auf den produktstatus einer sorte hinterlegt, der in den masken-popup-funktionen nicht greift
 * @author martin
 *
 */
public class FUHREN_PRUEFUNG_FEHLENDER_HANDELSVERTRAG  extends XX_FUHREN_PRUEFUNG
{
	
	public FUHREN_PRUEFUNG_FEHLENDER_HANDELSVERTRAG(RECLIST_VPOS_TPA_FUHRE rec_ListFuhre)
	{
		super(rec_ListFuhre);
	}

	
	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		//diese pruefung wird hier nur gemacht, wenn das ueberstimmen der handelsvertraege verboten wurde, sonst greift der ueberstimmen-dialog in der maske
		boolean bVerbieteUeberstimmen = __RECORD_MANDANT_ZUSATZ.IS__Value("VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG", "N", "N");
		
		boolean bPruefeJurAdresseHandelsvertrag_laut_Mandantensetting = __RECORD_MANDANT_ZUSATZ.IS__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.HANDELSVERTRAG_PRUEFE_AN_JUR_ORT.toString(), "N", "N");
		boolean bPruefeGeoAdresseHandelsvertrag_laut_Mandantensetting = __RECORD_MANDANT_ZUSATZ.IS__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.HANDELSVERTRAG_PRUEFE_AN_GEO_ORT.toString(), "N", "N");

		
		if (bVerbieteUeberstimmen && (bPruefeJurAdresseHandelsvertrag_laut_Mandantensetting||bPruefeGeoAdresseHandelsvertrag_laut_Mandantensetting))
		{

			//2014-08-04: pruefobjekt fuer die Handelsvertragspruefung instanzieren
			FU__CHECK_MustWarn_AVV_Kontrakt  oPruefeVertrag = new FU__CHECK_MustWarn_AVV_Kontrakt();
			
			String cLaenderCodeMandant= S.NN(bibSES.get_LAENDERCODE_MANDANT()).trim();
			
			if (S.isEmpty(cLaenderCodeMandant))	{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("System-Stammdaten-Fehler: Der Mandant hat keinen Ländercode hinterlegt !!! Bitte korrigieren!!")));
			} else 	{

			//	DEBUG.System_println("anzahl: "+this.get_recListFuhre().get_vKeyValues().size());
				
				for (int i=0;i<this.get_recListFuhre().get_vKeyValues().size();i++)
				{
					//record aus dem view v1_fuhren
					RECORD_VPOS_TPA_FUHRE  	recFuhre = 			this.get_recListFuhre().get(i);
					RECORD_ADRESSE  		recADRESSE_LINKS = 			recFuhre.get_UP_RECORD_ADRESSE_id_adresse_start();
					RECORD_ADRESSE  		recADRESSE_RECHTS = 		recFuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel();
					
					RECORD_ADRESSE  		recADRESSE_LAGER_LINKS =  	recADRESSE_LINKS;
					RECORD_ADRESSE  		recADRESSE_LAGER_RECHTS = 	recADRESSE_RECHTS;
					
					//jetzt pruefen, ob es auf das hauptlager geliefert wird:
					if (!recFuhre.get_ID_ADRESSE_START_cUF_NN("-1").equals(recFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-2"))) {
						recADRESSE_LAGER_LINKS = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_start();
					}
					if (!recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-1").equals(recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN("-2"))) {
						recADRESSE_LAGER_RECHTS = recFuhre.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel();
					}
					
					if (recADRESSE_LINKS!=null && this.get_bArtikelMustBeChecked(recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek())) {
						String cTYP="EK";
						
						String cDatumFormated = recFuhre.get_DATUM_AUFLADEN_cF_NN(recFuhre.get_DATUM_ABHOLUNG_cF_NN(""));
						if (S.isEmpty(cDatumFormated)) 	{
							//bei Fuhrenorten: korrekte record_fuhre der basisfuhre aufbauen, da bei der pruefung der fuhrenorte keine plandaten vorhanden sind 
							RECORD_VPOS_TPA_FUHRE  recFuhreBASE = new RECORD_VPOS_TPA_FUHRE(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),recFuhre.get_oConn());
							cDatumFormated = recFuhreBASE.get_DATUM_ABHOLUNG_cF_NN("");
						}
						
						if (S.isFull(cDatumFormated)) {
//							
//							DEBUG.System_print(oPruefeVertrag.get_AlarmVector(
//									oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_LINKS, cTYP, cDatumFormated, true)));
//							
//							DEBUG.System_print(oPruefeVertrag.get_AlarmVector(
//										oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_LAGER_LINKS, cTYP, cDatumFormated, false)));
//
							
							
							
							//dann EK-Pruefung
							oMV.add_MESSAGE(oPruefeVertrag.get_AlarmVector(
									oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_LINKS, cTYP, cDatumFormated, true)));
							
							//2014-05-23: lageradresse pruefen (mandantenschalter werden im objekt FU__CHECK_MustWarn_AVV_Kontrakt geprueft)
							oMV.add_MESSAGE(oPruefeVertrag.get_AlarmVector(
										oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_LAGER_LINKS, cTYP, cDatumFormated, false)));
							
						} else 	{
							//duerfte eigentlich nie kommen
							oMV.add_MESSAGE(new MyE2_Alarm_Message(
									new MyE2_String("ACHTUNG! Die Prüfung auf EU-Vertrag mit der Firma:  ",true,
											recADRESSE_LINKS.get___KETTE(bibVECTOR.get_Vector("NAME1", "NAME2")),false,
											" kann wegen fehlendem Ladedatum nicht ausgeführt werden: Bitte Datum eingeben !!",true)));
						
						}
					}
	
					if (recADRESSE_RECHTS!=null && this.get_bArtikelMustBeChecked(recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk())) {
						String cTYP="VK";

						String cDatumFormated = recFuhre.get_DATUM_ABLADEN_cF_NN(recFuhre.get_DATUM_ANLIEFERUNG_cF_NN(""));
						if (S.isEmpty(cDatumFormated)) 	{
							//bei Fuhrenorten: korrekte record_fuhre der basisfuhre aufbauen, da bei der pruefung der fuhrenorte keine plandaten vorhanden sind 
							RECORD_VPOS_TPA_FUHRE  recFuhreBASE = new RECORD_VPOS_TPA_FUHRE(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),recFuhre.get_oConn());
							cDatumFormated = recFuhreBASE.get_DATUM_ANLIEFERUNG_cF_NN("");
						}
						
						if (S.isFull(cDatumFormated)) {
				
//							
//							DEBUG.System_print(oPruefeVertrag.get_AlarmVector(
//									oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_RECHTS, cTYP, cDatumFormated, true)));
//							
//							DEBUG.System_print(oPruefeVertrag.get_AlarmVector(
//									oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_LAGER_RECHTS, cTYP, cDatumFormated, false)));
//							
							
							
							//dann VK-Pruefung
							oMV.add_MESSAGE(oPruefeVertrag.get_AlarmVector(
									oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_RECHTS, cTYP, cDatumFormated, true)));
							
							//2014-05-23: lageradresse pruefen (mandantenschalter werden im objekt FU__CHECK_MustWarn_AVV_Kontrakt geprueft)
							oMV.add_MESSAGE(oPruefeVertrag.get_AlarmVector(
									oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_LAGER_RECHTS, cTYP, cDatumFormated, false)));
							
						} else 	{
							//duerfte eigentlich nie kommen
							oMV.add_MESSAGE(new MyE2_Alarm_Message(
									new MyE2_String("ACHTUNG! Die Prüfung auf EU-Vertrag mit der Firma:  ",true,
											recADRESSE_RECHTS.get___KETTE(bibVECTOR.get_Vector("NAME1", "NAME2")),false,
											" kann wegen fehlendem Abladedatum nicht ausgeführt werden: Bitte Datum eingeben !!",true)));
						
						}
					}
				}
			}
		}
		return oMV;
	}
	
	
	private boolean get_bArtikelMustBeChecked(RECORD_ARTIKEL_BEZ recArtikelBez) throws myException {
		if (recArtikelBez!=null && 
			recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel()!=null &&
			recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().is_IST_PRODUKT_NO() &&
			recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().is_END_OF_WASTE_NO()) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
//	private class FU__CHECK_MustWarn_AVV_Kontrakt
//	{
//		
//		private boolean bMustWarn = false;
//		
//		/**
//		 * 
//		 * @param cEK_VK   erlaubt ist : "EK" oder "VK"
//		 * @param ID_ADRESSE (unformated)
//		 * @param formatedDatumFuhre (vergleichsdatum, i.e. aktuelles Datum)
//		 */
//		public FU__CHECK_MustWarn_AVV_Kontrakt(String cEK_VK, RECORD_ADRESSE recADRESSE, String formatedDatumFuhre, String cLaendercodeMandant) throws myException
//		{
//			super();
//			
//			//2014-05-23: unterscheiden zwischen basis und lager, vorgabe, das lager = basis
//			RECORD_ADRESSE recADRESSE_JUR = recADRESSE;
//			RECORD_ADRESSE recADRESSE_GEO = recADRESSE;
//			
//			if (recADRESSE.get_ADRESSTYP_lValue(-1l).intValue()==myCONST.ADRESSTYP_LIEFERADRESSE) {
//				recADRESSE_JUR = recADRESSE.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
////				DEBUG.System_println("ID_ADRESSE_LAGER="+recADRESSE_GEO.get_ID_ADRESSE_cF()+"           ID_ADRESSE_BASIS="+recADRESSE_JUR.get_ID_ADRESSE_cF());
//			}
//			
//			if (!recADRESSE_GEO.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN("").trim().equals(cLaendercodeMandant))
//			{
//			
//				if (cEK_VK.equals("EK"))
//				{
//					this.bMustWarn =
//						myDateHelper.get_Date1_Less_Date2(
//								recADRESSE_JUR.get_EU_BEIBLATT_EK_VERTRAG_cF_NN("01.01.1910"),
//								formatedDatumFuhre);
//				}
//				else if (cEK_VK.equals("VK"))
//				{
//					this.bMustWarn =
//						myDateHelper.get_Date1_Less_Date2(
//								recADRESSE_JUR.get_EU_BEIBLATT_VK_VERTRAG_cF_NN("01.01.1910"),
//								formatedDatumFuhre);
//				}
//				else
//				{
//					throw new myException(this,"False EK-VK-deklaration !");
//				}
//			}		
//			
//		}
//
//		public boolean get_bMustWarn()
//		{
//			return this.bMustWarn;
//		}
//	}
	
}
