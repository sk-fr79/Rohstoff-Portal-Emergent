package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.VorgangTableNames;

public class BS__SETTING
{
	/*
	 * klasse, die je nach typ ABNAHMEANGEBOT oder LIEFERANGEBOT
	 * Strings usw enthaelt, die benoetigt werden, die beiden belegtypen auseinanderzuhalten
	 */
	private String 				cBELEGTYP = null;
	private String 				cMODULCONTAINER_LIST_IDENTIFIER = null;
	private String 				cMODULCONTAINER_MASK_IDENTIFIER = null;
	private String 				cMODULCONTAINER_POS_MASK_IDENTIFIER = null;
	
	private String 				cNameOfJasperMitMengen = null;
	private String 				cNameOfJasperOhneMengen = null;
	private String 				cBELEGNAME = null;
	private String 				cVORGANGSART = null;
	private String 				cVORGANGSART4User = null;
	private String 				cTEXTKENNER_POPUP_MAILBETREFF = null;
	private String 				cTEXTKENNER_POPUP_MAILBLOCK = null;
	private String 				cTextFuerAdressArt = null;
	private String 				cKeyNameForID_JasperHapmap = null;
	private MyE2_String 		cTitleForMailPopup = null;
	
	private String   			cBuchungsNrVorsatz = "";                          //z.b. bei Rechnungen "R-"  und bei Gutschriften "G-"
	
	private VorgangTableNames	oVorgangTableNames = null;

	public BS__SETTING(String cbelegtyp) throws myException
	{
		super();
		cBELEGTYP = cbelegtyp;
		
		if (! (	cBELEGTYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT) || 
				cBELEGTYP.equals(myCONST.VORGANGSART_ANGEBOT)        || 
				cBELEGTYP.equals(myCONST.VORGANGSART_EK_KONTRAKT)    || 
				cBELEGTYP.equals(myCONST.VORGANGSART_VK_KONTRAKT)    || 
				cBELEGTYP.equals(myCONST.VORGANGSART_TRANSPORT)      ||
				cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG)    || 
				cBELEGTYP.equals(myCONST.VORGANGSART_GUTSCHRIFT) ))
		{
			throw new myException("Only types VORGANGSART_ ... Types allowed !");
		}
		
		
		
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG))
		{
			this.cMODULCONTAINER_LIST_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_RECHNUNGEN_LIST;
			this.cMODULCONTAINER_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_RECHNUNGEN_MASK;
			this.cMODULCONTAINER_POS_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_RECHNUNGEN_POS_MASK;
			
			this.cVORGANGSART = this.cBELEGTYP;
			this.cVORGANGSART4User = myCONST.VORGANGSART_RECHNUNG_FOR_USER;
			
			this.cNameOfJasperOhneMengen = "rechnung_gutschrift.jasper";
			this.cNameOfJasperMitMengen = "rechnung_gutschrift.jasper";
			this.cBELEGNAME = "RECHNUNG";
			this.cTEXTKENNER_POPUP_MAILBETREFF = 	"RECHNUNG_MAIL_BETREFF";
			this.cTEXTKENNER_POPUP_MAILBLOCK = 		"RECHNUNG_MAIL_TEXTBLOCK"; 
			this.cTextFuerAdressArt = "Kunde";
			this.cKeyNameForID_JasperHapmap = "id_vkopf_rg";
			this.cTitleForMailPopup = new MyE2_String("Versenden von Rechnungen");
			
			this.cBuchungsNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_RECH_cUF_NN("");
			
		}
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_GUTSCHRIFT))
		{
			this.cMODULCONTAINER_LIST_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_GUTSCHRIFTEN_LIST;
			this.cMODULCONTAINER_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_GUTSCHRIFTEN_MASK;
			this.cMODULCONTAINER_POS_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_GUTSCHRIFTEN_POS_MASK;

			this.cVORGANGSART = this.cBELEGTYP;
			this.cVORGANGSART4User = myCONST.VORGANGSART_GUTSCHRIFT_FOR_USER;

			this.cNameOfJasperOhneMengen = "rechnung_gutschrift.jasper";
			this.cNameOfJasperMitMengen = "rechnung_gutschrift.jasper";
			this.cBELEGNAME = "GUTSCHRIFT";
			this.cTEXTKENNER_POPUP_MAILBETREFF = 	"GUTSCHRIFT_MAIL_BETREFF";
			this.cTEXTKENNER_POPUP_MAILBLOCK = 		"GUTSCHRIFT_MAIL_TEXTBLOCK"; 
			this.cTextFuerAdressArt = "Lieferant";
			this.cKeyNameForID_JasperHapmap = "id_vkopf_rg";
			this.cTitleForMailPopup = new MyE2_String("Versenden von Gutschriften");

			this.cBuchungsNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_GUT_cUF_NN("");

		}
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_ABNAHMEANGEBOT))
		{
			this.cMODULCONTAINER_LIST_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_ABNAHMEANGEBOT_LIST;
			this.cMODULCONTAINER_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_ABNAHMEANGEBOT_MASK;
			this.cMODULCONTAINER_POS_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_ABNAHMEANGEBOT_POS_MASK;

			this.cVORGANGSART = this.cBELEGTYP;
			this.cVORGANGSART4User = myCONST.VORGANGSART_ABNAHMEANGEBOT_FOR_USER;

			this.cNameOfJasperOhneMengen = "abnahmeangebot.jasper";
			this.cNameOfJasperMitMengen = "abnahmeangebot_menge.jasper";
			this.cBELEGNAME = "ABNAHMEANGEBOT";
			this.cTEXTKENNER_POPUP_MAILBETREFF = 	"AA_MAIL_BETREFF";
			this.cTEXTKENNER_POPUP_MAILBLOCK = 		"AA_MAIL_TEXTBLOCK"; 
			this.cTextFuerAdressArt = "Lieferant";
			this.cKeyNameForID_JasperHapmap = "id_vkopf_std";
			this.cTitleForMailPopup = new MyE2_String("Versenden von Abnahmeangeboten");
			
			this.cBuchungsNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_ABANGEB_cUF_NN("");
			
		}
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_ANGEBOT))
		{
			this.cMODULCONTAINER_LIST_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_VERKAUFSANGEBOT_LIST;
			this.cMODULCONTAINER_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_VERKAUFSANGEBOT_MASK;
			this.cMODULCONTAINER_POS_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_VERKAUFSANGEBOT_POS_MASK;

			this.cVORGANGSART = this.cBELEGTYP;
			this.cVORGANGSART4User = myCONST.VORGANGSART_ANGEBOT_FOR_USER;
			
			this.cNameOfJasperOhneMengen = "verkaufsangebot.jasper";
			this.cNameOfJasperMitMengen = "verkaufsangebot_menge.jasper";
			this.cBELEGNAME = "ANGEBOT";
			this.cTEXTKENNER_POPUP_MAILBETREFF = 	"VA_MAIL_BETREFF";
			this.cTEXTKENNER_POPUP_MAILBLOCK = 		"VA_MAIL_TEXTBLOCK"; 
			this.cTextFuerAdressArt = "Kunde";
			this.cKeyNameForID_JasperHapmap = "id_vkopf_std";
			this.cTitleForMailPopup = new MyE2_String("Versenden von Verkaufsangeboten");
			
			this.cBuchungsNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_LIEFANGEB_cUF_NN("");
		}
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_EK_KONTRAKT))
		{
			this.cMODULCONTAINER_LIST_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST;
			this.cMODULCONTAINER_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_MASK;
			this.cMODULCONTAINER_POS_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_POS_MASK;

			this.cVORGANGSART = this.cBELEGTYP;
			this.cVORGANGSART4User = myCONST.VORGANGSART_EK_KONTRAKT_FOR_USER;

			
			this.cNameOfJasperMitMengen = "kontrakt_ek.jasper";
			this.cBELEGNAME = "EK_KONTRAKT";
			this.cTEXTKENNER_POPUP_MAILBETREFF = 	"EK_KONTRAKT_MAIL_BETREFF";
			this.cTEXTKENNER_POPUP_MAILBLOCK = 		"EK_KONTRAKT_MAIL_TEXTBLOCK"; 
			this.cTextFuerAdressArt = "Lieferant";
			this.cKeyNameForID_JasperHapmap = "id_vkopf_kon";
			this.cTitleForMailPopup = new MyE2_String("Versenden von EK-Kontrakten");
			
			this.cBuchungsNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_EKK_cUF_NN("");
			
		}
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_VK_KONTRAKT))
		{
			this.cMODULCONTAINER_LIST_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST;
			this.cMODULCONTAINER_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_MASK;
			this.cMODULCONTAINER_POS_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_POS_MASK;

			this.cVORGANGSART = this.cBELEGTYP;
			this.cVORGANGSART4User = myCONST.VORGANGSART_VK_KONTRAKT_FOR_USER;

			
			this.cNameOfJasperMitMengen = "kontrakt_vk.jasper";
			this.cBELEGNAME = "VK_KONTRAKT";
			this.cTEXTKENNER_POPUP_MAILBETREFF = 	"VK_KONTRAKT_MAIL_BETREFF";
			this.cTEXTKENNER_POPUP_MAILBLOCK = 		"VK_KONTRAKT_MAIL_TEXTBLOCK"; 
			this.cTextFuerAdressArt = "Kunde";
			this.cKeyNameForID_JasperHapmap = "id_vkopf_kon";
			this.cTitleForMailPopup = new MyE2_String("Versenden von VK-Kontrakten");
			
			this.cBuchungsNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_VKK_cUF_NN("");
			
		}
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_TRANSPORT))
		{
			this.cMODULCONTAINER_LIST_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_TPA_LIST;
			this.cMODULCONTAINER_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_TPA_MASK;
			this.cMODULCONTAINER_POS_MASK_IDENTIFIER = E2_MODULNAMES.NAME_MODUL_TPA_POS_MASK;
			
			this.cVORGANGSART = this.cBELEGTYP;
			this.cVORGANGSART4User = myCONST.VORGANGSART_TRANSPORT_FOR_USER;

			
			this.cNameOfJasperMitMengen = "transportauftrag.jasper";
			this.cBELEGNAME = "TRANSPORT";
			this.cTEXTKENNER_POPUP_MAILBETREFF = 	"TPA_MAIL_BETREFF";
			this.cTEXTKENNER_POPUP_MAILBLOCK = 		"TPA_MAIL_TEXTBLOCK"; 
			this.cTextFuerAdressArt = "Spedition";
			this.cKeyNameForID_JasperHapmap = "id_vkopf";
			this.cTitleForMailPopup = new MyE2_String("Versenden von Transportaufträgen");
			
			this.cBuchungsNrVorsatz = bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_TPA_cUF_NN("");
		}
		
		this.oVorgangTableNames = new VorgangTableNames(this.cBELEGTYP);
		
	}



	/**
	 * 2017-05-19: variante mit enum
	 * @param belegtyp
	 * @throws myException
	 */
	public BS__SETTING(VORGANGSART belegtyp) throws myException {
		this(belegtyp.get_DBValue());
	}

	
	

	public String get_cMODULCONTAINER_LIST_IDENTIFIER()
	{
		return cMODULCONTAINER_LIST_IDENTIFIER;
	}

	public String get_cMODULCONTAINER_MASK_IDENTIFIER()
	{
		return cMODULCONTAINER_MASK_IDENTIFIER;
	}

	public String get_cMODULCONTAINER_POS_MASK_IDENTIFIER()
	{
		return cMODULCONTAINER_POS_MASK_IDENTIFIER;
	}



	public String get_cBELEGTYP()
	{
		return cBELEGTYP;
	}



	public String get_cNameOfJasperMitMengen()
	{
		return cNameOfJasperMitMengen;
	}



	public String get_cNameOfJasperOhneMengen()
	{
		return cNameOfJasperOhneMengen;
	}





	public String get_cBELEGNAME()
	{
		return cBELEGNAME;
	}



	public String get_cTEXTKENNER_POPUP_MAILBETREFF()
	{
		return cTEXTKENNER_POPUP_MAILBETREFF;
	}



	public String get_cTEXTKENNER_POPUP_MAILBLOCK()
	{
		return cTEXTKENNER_POPUP_MAILBLOCK;
	}



	public String get_cTextFuerAdressArt()
	{
		return cTextFuerAdressArt;
	}



	public VorgangTableNames get_oVorgangTableNames()
	{
		return oVorgangTableNames;
	}



	public String get_cKeyNameForID_JasperHapmap()
	{
		return cKeyNameForID_JasperHapmap;
	}



	public MyE2_String get_cTitleForMailPopup()
	{
		return cTitleForMailPopup;
	}






	public String get_cVORGANGSART()
	{
		return cVORGANGSART;
	}






	public String get_cVORGANGSART4User()
	{
		return cVORGANGSART4User;
	}






	public String get_cBuchungsNrVorsatz()
	{
		if (S.isEmpty(this.cBuchungsNrVorsatz))
		{
			//aenderung 2010-12-20: leerer buchungsnummer-prefix erlaubt
			return "";
		}
		return cBuchungsNrVorsatz;
	}
	
	
	/**
	 * liefert fuer den jeweiligen beleg einen kompletten update-block, beginnend mit BUCHUNGSNUMMER=..."
	 */
	public String get_SQL_UPDATE_Block_Fuer_Buchungsnummer()
	{
		
		String cBuchungsvorsatz = this.get_cBuchungsNrVorsatz();
		if (S.isFull(cBuchungsvorsatz))
		{
			cBuchungsvorsatz = bibALL.MakeSql(cBuchungsvorsatz);
		}
    
		String cSQL_Block = " BUCHUNGSNUMMER="+(S.isFull(cBuchungsvorsatz)?cBuchungsvorsatz+"||":"")+"TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_"+this.get_cBELEGTYP()+".NEXTVAL) ";
		
		return cSQL_Block;
		
	}
	
	
}
