package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.myCONST;

public class FU_SelektorWelcheTypenEinblenden extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	
//	private Vector<MyE2_CheckBox>  vCheckBoxTypen = new Vector<MyE2_CheckBox>();
	
	/*
	 *
	 * public static int     	STATUS_FUHRE__IST_STORNIERT = 					-2;
	 * public static int     	STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT = 		-1;
	 * public static int     	STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG = 	1;
	 * public static int     	STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS = 		2;
	 * public static int     	STATUS_FUHRE__UNGEBUCHT= 						3;
	 * public static int     	STATUS_FUHRE__TEILSGEBUCHT= 					4;
	 * public static int     	STATUS_FUHRE__GANZGEBUCHT = 					5;

	 * 
	 */
	
	
	public FU_SelektorWelcheTypenEinblenden()
	{
		super(4,true,new MyE2_String("Zeige: "),new Extent(95));

		this.ADD_STATUS_TO_Selector(false,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT+")",		new MyE2_String("Archiv"),   		new MyE2_String("Alte Fuhren aus Archiv, nicht abrechenbar"));
		this.ADD_STATUS_TO_Selector(false,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__IST_STORNIERT+")",					new MyE2_String("Storno"),   	new MyE2_String("Stornierte Fuhren aus dem AKTIVEN Bereich"));

		//2013-01-24: fuhrenstatus ohne buchungspositionen wird aufgeteilt in ohne buchungspos.offen und geschlossen
		//this.ADD_STATUS_TO_Selector(true,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS+"')",		new MyE2_String("Ohne BP"),   		new MyE2_String("Fuhre besitzt keine Rechnungs- und Gutschriftspositionen"));
		this.ADD_STATUS_TO_Selector(true,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS+" AND NVL(JT_VPOS_TPA_FUHRE.SCHLIESSE_FUHRE,'N')='N')",		new MyE2_String("Ohne BP/offen"),  new MyE2_String("Fuhre besitzt keine Rechnungs- und Gutschriftspositionen und ist noch ungeprüft"));
		this.ADD_STATUS_TO_Selector(false,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS+" AND NVL(JT_VPOS_TPA_FUHRE.SCHLIESSE_FUHRE,'N')='Y')",		new MyE2_String("Ohne BP/zu"), 	   new MyE2_String("Fuhre besitzt keine Rechnungs- und Gutschriftspositionen und wurde geprüft und abgeschlossen"));
		
		this.ADD_STATUS_TO_Selector(true,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG+")",	new MyE2_String("Unvollst."),   	new MyE2_String("Fuhre ist noch nicht komplett gefüllt"));
		this.ADD_STATUS_TO_Selector(true,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__UNGEBUCHT+")",						new MyE2_String("Ungeb."),   		new MyE2_String("Fuhre ist bereit zum Erstellen von Rechnungs- und Gutschriftspositionen"));
		this.ADD_STATUS_TO_Selector(true,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__TEILSGEBUCHT+")",					new MyE2_String("Teilgeb."),  	 	new MyE2_String("Fuhre wurde teilweise schon in Rechnungs- und Gutschriftspositionen überführt"));
		this.ADD_STATUS_TO_Selector(false,	"(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG="+myCONST.STATUS_FUHRE__GANZGEBUCHT+")",						new MyE2_String("Fertig"),   		new MyE2_String("Fuhre ist bereits komplett in Rechnungs- und Gutschriftspositionen überführt"));

		
		this.set_cConditionWhenAllIsSelected("");
	}

	
	
}
