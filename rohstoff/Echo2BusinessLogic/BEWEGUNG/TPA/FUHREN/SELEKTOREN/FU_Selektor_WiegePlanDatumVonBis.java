package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selektor_WiegePlanDatumVonBis extends E2_SelektorDateFromTo_NG
{

	public FU_Selektor_WiegePlanDatumVonBis() throws myException
	{
		super(	new MyE2_String("Wiege/Plan:"), 
				"NVL(JT_VPOS_TPA_FUHRE.DATUM_AUFLADEN,JT_VPOS_TPA_FUHRE.DATUM_ABHOLUNG)", 
				"NVL(JT_VPOS_TPA_FUHRE.DATUM_ABLADEN,JT_VPOS_TPA_FUHRE.DATUM_ANLIEFERUNG)",
				new Extent(100));
		
		this.get_oTFDatumVon().get_oTextField().set_iWidthPixel(80);
		this.get_oTFDatumBis().get_oTextField().set_iWidthPixel(80);

		this.set_ToolTips(new MyE2_String("Selektiert Fuhre aus, deren Zeitbereich Ladedatum-Abladedatum sich mit dem Eingabe-Zeitbereich überschneiden!"));
	}

	
	//ueberschreibbare-methode um weitere bedingungen anzuhaengen
	//hier werden zusaetzlich noch die Fuhrenorte mit in die Auswahl einbezogen, die im selektionszeitrau einen fuhrenort besitzen 
	public String get_extended_whereStatement(String cRueck, String cEingabeVon_20121231, String cEingabeBis_20121231) throws myException
	{
		if (S.isFull(cRueck))
		{
		
			String cZusatz=" JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IN (SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO WHERE "+
			                E2_SelektorDateFromTo_NG.Generate_SelectBlock("FUO.DATUM_LADE_ABLADE", "FUO.DATUM_LADE_ABLADE", cEingabeVon_20121231, cEingabeBis_20121231)+")";
		
		
			cRueck = "("+cRueck+" OR ("+cZusatz+"))";
			
		}
		return cRueck;
	}

}
