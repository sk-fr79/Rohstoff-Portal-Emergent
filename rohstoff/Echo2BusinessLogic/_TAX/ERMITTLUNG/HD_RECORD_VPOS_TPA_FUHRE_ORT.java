package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.math.BigDecimal;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class HD_RECORD_VPOS_TPA_FUHRE_ORT extends RECORD_VPOS_TPA_FUHRE_ORT
{
	
	private boolean      bEK = false;
	
	private boolean      bSaveAllowed = true;

	public HD_RECORD_VPOS_TPA_FUHRE_ORT(RECORD_VPOS_TPA_FUHRE_ORT recordOrig, boolean SaveAllowed) throws myException
	{
		super(recordOrig);
		
		this.bEK = this.get_DEF_QUELLE_ZIEL_cUF().equals("EK");

		this.bSaveAllowed = SaveAllowed;

	}

	
	
	
	


	public boolean get_bEK() {
		return bEK;
	}
	public boolean get_bVK() {
		return !bEK;
	}

	
	public BigDecimal  get_BD_AbrechnungsMenge() throws myException
	{
		BigDecimal bdRueck = null;
		
		if (this.get_bEK())
		{
			if (this.is_LADEMENGE_GUTSCHRIFT_YES())
			{
				bdRueck = this.get_ANTEIL_LADEMENGE_bdValue(null);
			}
			else
			{
				bdRueck = this.get_ANTEIL_ABLADEMENGE_bdValue(null);
			}
		}
		else
		{
			if (this.is_ABLADEMENGE_RECHNUNG_YES())
			{
				bdRueck = this.get_ANTEIL_ABLADEMENGE_bdValue(null);
			}
			else
			{
				bdRueck = this.get_ANTEIL_LADEMENGE_bdValue(null);
			}
		}

		return bdRueck;
	}

	
	
	
	public HD_Station get_HD_Station() throws myException
	{
		String cName12 = 
				S.isEmpty(this.get_ID_ADRESSE_cUF_NN(""))?
						(this.get_bEK()?"<lieferant>":"<abnehmer>"):
						this.get_UP_RECORD_ADRESSE_id_adresse().get_NAME1_cUF_NN("<name1>")+" "+this.get_UP_RECORD_ADRESSE_id_adresse().get_NAME2_cUF_NN("<name2>");
		
		String cOrt = 
				S.isEmpty(this.get_ID_ADRESSE_cUF_NN(""))?
						"<ort>":
						this.get_UP_RECORD_ADRESSE_id_adresse().get_ORT_cUF_NN("<ort>");
		
		String cSorte = 
				S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF_NN(""))?
						(this.get_bEK()?"<sorte-ek>":"<sorte-vk>"):
						this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<anr1>")+"-"+
						this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ANR2_cUF_NN("<anr2>")+" "+this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ARTBEZ1_cUF_NN("<artbez1>");

		
		
		return new ownHdStation(
						false, this.get_bEK(), 
						this.get_ID_ADRESSE_cUF_NN(""), 
						this.get_ID_ADRESSE_LAGER_cUF_NN(""),
						this.get_ID_ARTIKEL_BEZ_cUF_NN(""), 
						this.get_BD_AbrechnungsMenge(), 
						this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_TP_VERANTWORTUNG_cUF_NN(""),
						null,
						this,
						cName12,
						cOrt,
						cSorte);
	}
	
	
	
	public HD_Station get_HD_Station(boolean bErsetzeLeerVerantwortungDurchMandant) throws myException
	{
		String cName12 = 
				S.isEmpty(this.get_ID_ADRESSE_cUF_NN(""))?
						(this.get_bEK()?"<lieferant>":"<abnehmer>"):
						this.get_UP_RECORD_ADRESSE_id_adresse().get_NAME1_cUF_NN("<name1>")+" "+this.get_UP_RECORD_ADRESSE_id_adresse().get_NAME2_cUF_NN("<name2>");
		
		String cOrt = 
				S.isEmpty(this.get_ID_ADRESSE_cUF_NN(""))?
						"<ort>":
						this.get_UP_RECORD_ADRESSE_id_adresse().get_ORT_cUF_NN("<ort>");
		
		String cSorte = 
				S.isEmpty(this.get_ID_ARTIKEL_BEZ_cUF_NN(""))?
						(this.get_bEK()?"<sorte-ek>":"<sorte-vk>"):
						this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<anr1>")+"-"+
						this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ANR2_cUF_NN("<anr2>")+" "+this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ARTBEZ1_cUF_NN("<artbez1>");

		
		
		return new ownHdStation(
						false, this.get_bEK(), 
						this.get_ID_ADRESSE_cUF_NN(""), 
						this.get_ID_ADRESSE_LAGER_cUF_NN(""),
						this.get_ID_ARTIKEL_BEZ_cUF_NN(""), 
						this.get_BD_AbrechnungsMenge(), 
						this.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre().get_TP_VERANTWORTUNG_cUF_NN(bErsetzeLeerVerantwortungDurchMandant?TAX_CONST.TP_VERANTWORTUNG_MANDANT:""),
						null,
						this,
						cName12,
						cOrt,
						cSorte);
	}

	
	
	
	

	private class ownHdStation extends HD_Station
	{

		public ownHdStation(boolean p_bHauptFuhre, boolean p_bQuelle,
							String p_cID_ADRESSE_JUR_UF, String p_cID_ADRESSE_GEO_UF,
							String p_cID_ARTIKEL_BEZ_UF, BigDecimal p_bdAbrechnungsMenge,
							String p_cTP_Verantwortung, RECORD_VPOS_TPA_FUHRE p_RecFuhre,
							RECORD_VPOS_TPA_FUHRE_ORT p_RecFuhreOrt,
							String p_cInfoTextName1_2, String p_cInfoTextOrt,
							String p_cInfoTextSorte) throws myException {
			super();
			
			this.init(p_bHauptFuhre, p_bQuelle, p_cID_ADRESSE_JUR_UF, p_cID_ADRESSE_GEO_UF,
					p_cID_ARTIKEL_BEZ_UF, p_bdAbrechnungsMenge, p_cTP_Verantwortung,
					null, p_RecFuhreOrt, p_cInfoTextName1_2, p_cInfoTextOrt,
					p_cInfoTextSorte, p_RecFuhreOrt.get_EINZELPREIS_bdValue(BigDecimal.ZERO),
					new MyDate(HD_RECORD_VPOS_TPA_FUHRE_ORT.this.get_DATUM_LADE_ABLADE_cF_NN(""))
					);
			
		}

		@Override
		public MyE2_MessageVector applyResults(HD_WarenBewegungEinstufung 	oHD_Fuhreneinstufung, String cID_TAX_UF, String cIntrastat_YN,String cTransit_YN, boolean bEK_true__VK_false)	throws myException {
//			HD_RECORD_VPOS_TPA_FUHRE_ORT oThis = HD_RECORD_VPOS_TPA_FUHRE_ORT.this;
//			RECORD_TAX  recTax = new RECORD_TAX(cID_TAX_UF);
//			
//			oThis.set_NEW_VALUE_ID_TAX(cID_TAX_UF);
//			oThis.set_NEW_VALUE_STEUERSATZ(recTax.get_STEUERSATZ_cF_NN(""));
//			oThis.set_NEW_VALUE_EU_STEUER_VERMERK(recTax.get_STEUERVERMERK_cF_NN(""));
//			oThis.set_NEW_VALUE_ID_TAX(cID_TAX_UF);
//			oThis.set_NEW_VALUE_INTRASTAT_MELD(cIntrastat_YN);
//			oThis.set_NEW_VALUE_TRANSIT(cTransit_YN);
//
//			MyE2_MessageVector oMV= oThis.UPDATE(null, true);
//			if (oMV.get_bIsOK()) {
//				if (this.get_bEK()) {
//					oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fuhren-Zusatzort/Ladestation: Steuer/EU-Steuertext/Intrastat und Transit wurde gesetzt")));
//				} else {
//					oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fuhren-Zusatzort/Abladestation: Steuer/EU-Steuertext/Intrastat und Transit wurde gesetzt")));
//				}
//			}
//			
//			return oMV;
			
			return new MyE2_MessageVector();
		}

		@Override
		public boolean isUpdatingAllowd() throws myException {
			return HD_RECORD_VPOS_TPA_FUHRE_ORT.this.bSaveAllowed;
		}
			
	}
		

}
