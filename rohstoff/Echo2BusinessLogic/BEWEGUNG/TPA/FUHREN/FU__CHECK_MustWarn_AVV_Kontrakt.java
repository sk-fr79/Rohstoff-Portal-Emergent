package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingleMyString;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FU__CHECK_MustWarn_AVV_Kontrakt
{
	 //2016-02-26: die pruefung wird inaktiv, weil durch neue decision-pruefung beim drucken ersetzt
	public static boolean ich_bin_aktiv = false;       
	
	
	//2014-08-01
	//parameter aus mandantenstamm
	private boolean bPruefeJurAdresseHandelsvertrag_laut_Mandantensetting = false;
	private boolean bPruefeGeoAdresseHandelsvertrag_laut_Mandantensetting = false;
	
	private String  cLAENDERCODE_MANDANT = bibSES.get_LAENDERCODE_MANDANT(); 
	

	public FU__CHECK_MustWarn_AVV_Kontrakt() throws myException
	{
		super();
		
		this.bPruefeJurAdresseHandelsvertrag_laut_Mandantensetting = __RECORD_MANDANT_ZUSATZ.IS__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.HANDELSVERTRAG_PRUEFE_AN_JUR_ORT.toString(), "N", "N");
		this.bPruefeGeoAdresseHandelsvertrag_laut_Mandantensetting = __RECORD_MANDANT_ZUSATZ.IS__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.HANDELSVERTRAG_PRUEFE_AN_GEO_ORT.toString(), "N", "N");
	}
	

	
	
	
	/**
	 * 
	 * @param recADRESSE (wenn null, dann wird die pruefung durchgelassen, kein fehler
	 * @param cEK_VK
	 * @param formatedDatumFuhre
	 * @param bPruefeJur
	 * @return Vector<MyString> wenn alles ok, dann leer
	 * @throws myException
	 */
	public VectorSingleMyString pruefe_Adresse_und_ReturnFehlerVector(RECORD_ADRESSE  recADRESSE, String cEK_VK, String formatedDatumFuhre, boolean bPruefeJur) throws myException {
		VectorSingleMyString vRueck = new VectorSingleMyString();
		
		//wenn die adresse die adresse des mandanten ist, dann keine pruefung
		if (recADRESSE == null || recADRESSE.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT())) {
			return vRueck;
		}
		
		
		//2016-02-26: inaktiv
		if (!FU__CHECK_MustWarn_AVV_Kontrakt.ich_bin_aktiv) {
			return vRueck;
		}
		
		boolean bPruefeGeo = !bPruefeJur;
		//vorpruefung wegen der moeglichen mandantensettings
		if ( 	(this.bPruefeJurAdresseHandelsvertrag_laut_Mandantensetting && bPruefeJur) ||
				(this.bPruefeGeoAdresseHandelsvertrag_laut_Mandantensetting && bPruefeGeo)
		   ) {
			
			boolean bUnterschiedlicheLaendercodes = !recADRESSE.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN("").equals(this.cLAENDERCODE_MANDANT);
			boolean bMustWarn = false;
			
			if (bUnterschiedlicheLaendercodes) {
				if (cEK_VK.equals("EK")) {
					bMustWarn =
						myDateHelper.get_Date1_Less_Date2(
								recADRESSE.get_EU_BEIBLATT_EK_VERTRAG_cF_NN("01.01.1910"),
								formatedDatumFuhre);
				}
				else if (cEK_VK.equals("VK")) {
					bMustWarn =
						myDateHelper.get_Date1_Less_Date2(
								recADRESSE.get_EU_BEIBLATT_VK_VERTRAG_cF_NN("01.01.1910"),
								formatedDatumFuhre);
				} else {
					throw new myException(this,"pruefe_Adresse_und_ReturnFehlerVector(RECORD_ADRESSE  recADRESSE): False EK-VK-deklaration !");
				}
			}		
			
			if (bMustWarn) {
				String cZusatz = cEK_VK.equals("EK")?" (Wareneinkauf) ":" (Warenverkauf) ";
				if (recADRESSE.get_ADRESSTYP_lValue(new Long(-1)).intValue()==myCONST.ADRESSTYP_FIRMENINFO) {
					vRueck.add(new MyE2_String( "Warnung! Bei der Firmenadresse: ",true,
												recADRESSE.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$ORT)),false,
												" ist keine zeitlich korrelierende Angabe zu einem EU-Vertrag "+cZusatz+" hinterlegt! ",true));
				} else {
					
					RECORD_ADRESSE recAdresseBasis = recADRESSE.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
					
					vRueck.add(new MyE2_String( "Warnung! Bei der Lieferadresse: ",true,
							    recADRESSE.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$ORT)),false,
							    " angelegt bei der Firma ",true,
							    recAdresseBasis.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$ORT)),false,
							    " ist keine zeitlich korrelierende Angabe zu einem EU-Vertrag "+cZusatz+" hinterlegt! ",true));
				}
			}
		}		
		return vRueck;
	}
	
	
	public MyE2_MessageVector  get_AlarmVector(VectorSingleMyString vMeldungen) {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		for (MyString oString: vMeldungen) {
			if (S.isFull(oString)) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(oString));
			}
		}
		return oMV;
	}
	
	
	
	public MyE2_Column get_ColumnWithMeldungen(VectorSingleMyString vMeldungen) {
		MyE2_Column  oColRueck = new MyE2_Column();
	
		for (MyString oString: vMeldungen) {
			if (S.isFull(oString)) {
				MyE2_Label oLabelError = new MyE2_Label(oString, MyE2_Label.STYLE_ERROR_NORMAL_WRAP(),true);
				oColRueck.setBackground(new E2_ColorHelpBackground());
				oColRueck.add(oLabelError, E2_INSETS.I(10,1,10,1));
			}
		}
		
		return oColRueck;
	}
	
	
}
