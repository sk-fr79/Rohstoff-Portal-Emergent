package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.__FU_Pruefer_auf_AVV_UND_NOTI;

public class FUHREN_PRUEFUNG_AVV_STRUCTURE extends XX_FUHREN_PRUEFUNG
{

	private RECORD_VPOS_TPA_FUHRE 		rec_Fuhre = null;
	private boolean  					bIsFuhrenORT = false;
	
	
	public FUHREN_PRUEFUNG_AVV_STRUCTURE(RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
	{
		super(recFuhre);
		this.rec_Fuhre = 				recFuhre; 
		if (S.isFull(this.rec_Fuhre.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT","")))
		{
			this.bIsFuhrenORT = true;
		}
		else
		{
			this.bIsFuhrenORT = false;
		}
	}

	
	
	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();

		RECORD_VPOS_TPA_FUHRE  recFuhre = this.get_recFuhre(); 

		// nicht-datenbank-komponente aus der maske
		boolean b_Ueberstimmen_EIN =    recFuhre.is_SPEICHERN_TROTZ_INKONSISTENZ_YES();
		
		if (b_Ueberstimmen_EIN)            // alles erlaubt
			return vRueck;

		
		try
		{
			
			vRueck.add_MESSAGE(
					FU__PruefeMaskeWegenNotifikation(	recFuhre.get_ID_ARTIKEL_cF_NN(""),
															recFuhre.get_ID_EAK_CODE_cF_NN(""),
															recFuhre.get_BASEL_CODE_cF_NN(""),
															recFuhre.get_EUCODE_cF_NN(""),
															recFuhre.get_NOTIFIKATION_NR_cF_NN(""),
															recFuhre.get_ID_ADRESSE_LAGER_START_lValue(new Long(-1)),
															recFuhre.get_ID_ADRESSE_LAGER_ZIEL_lValue(new Long(-1)),
															recFuhre.get_LAENDERCODE_TRANSIT1_cF_NN(""),
															recFuhre.get_LAENDERCODE_TRANSIT2_cF_NN(""),
															recFuhre.get_LAENDERCODE_TRANSIT3_cF_NN(""),
															recFuhre.is_PRINT_EU_AMTSBLATT_YES(),
															recFuhre.is_OHNE_AVV_VERTRAG_CHECK_YES()));

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			vRueck.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Validierung der AVV-codes !!!"), false);
			vRueck.add_MESSAGE(new MyE2_Alarm_Message(ex.getLocalizedMessage(),false), false);
		}
		return vRueck;

		
	}
	
	
	
	/**
	 * 
	 * @param cID_ARTIKEL
	 * @param cID_EAK_CODE
	 * @param cBASEL_CODE
	 * @param cEUCODE
	 * @param cNOTIFIKATION_NR
	 * @param l_ID_ADRESSE_LAGER_START
	 * @param l_ID_ADRESSE_LAGER_ZIEL
	 * @param cLAENDERCODE_TRANSIT1
	 * @param cLAENDERCODE_TRANSIT2
	 * @param cLAENDERCODE_TRANSIT3
	 * @param b_AVV_Blatt_EIN
	 * @throws myException
	 */
	private MyE2_MessageVector FU__PruefeMaskeWegenNotifikation(	String cID_ARTIKEL,
																String cID_EAK_CODE,
																String cBASEL_CODE,
																String cEUCODE,
																String cNOTIFIKATION_NR,
																long   l_ID_ADRESSE_LAGER_START,
																long   l_ID_ADRESSE_LAGER_ZIEL,
																String cLAENDERCODE_TRANSIT1, 
																String cLAENDERCODE_TRANSIT2, 
																String cLAENDERCODE_TRANSIT3,
																boolean b_AVV_Blatt_EIN,
																boolean b_OHNE_AVV_VERTRAGS_CHECK) throws myException
	{
	
		return new __FU_Pruefer_auf_AVV_UND_NOTI().FU__PruefeMaskeWegenNotifikation(
																cID_ARTIKEL, 
																cID_EAK_CODE, 
																cBASEL_CODE, 
																cEUCODE, 
																cNOTIFIKATION_NR, 
																l_ID_ADRESSE_LAGER_START, 
																l_ID_ADRESSE_LAGER_ZIEL, 
																cLAENDERCODE_TRANSIT1, 
																cLAENDERCODE_TRANSIT2, 
																cLAENDERCODE_TRANSIT3, 
																b_AVV_Blatt_EIN, 
																b_OHNE_AVV_VERTRAGS_CHECK, 
																this.bIsFuhrenORT,
																(!this.rec_Fuhre.get_TP_VERANTWORTUNG_cUF_NN("MANDANT").equals("MANDANT")));

	}

}
