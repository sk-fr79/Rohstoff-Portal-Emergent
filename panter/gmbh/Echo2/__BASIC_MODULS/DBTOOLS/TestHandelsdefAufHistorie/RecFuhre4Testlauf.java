package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TestHandelsdefAufHistorie;

import java.math.BigDecimal;

import panter.gmbh.BasicInterfaces.Service.PdServiceFindHandelsdefSettings.StationsCreator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_WarenBewegungEinstufung;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_Station;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_Stationen;

public class RecFuhre4Testlauf extends RECORD_VPOS_TPA_FUHRE implements StationsCreator {
	
	private boolean      bSaveAllowed = true;   //hier heisst speichern: protokolleintrag


	public RecFuhre4Testlauf(RECORD_VPOS_TPA_FUHRE recordOrig) throws myException {
		super(recordOrig);
	}
	
	
	

	/**
	 * @param c_ID_or_WHEREBLOCK_OR_SQL
	 * @throws myException
	 */
	public RecFuhre4Testlauf(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}




	public BigDecimal  get_BD_AbrechnungsMenge(boolean bEK) throws myException
	{
		BigDecimal bdRueck = null;
		
		if (bEK)
		{
			if (this.is_LADEMENGE_GUTSCHRIFT_YES())
			{
				bdRueck = this.get_ANTEIL_LADEMENGE_LIEF_bdValue(null);
			}
			else
			{
				bdRueck = this.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(null);
			}
		}
		else
		{
			if (this.is_ABLADEMENGE_RECHNUNG_YES())
			{
				bdRueck = this.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null);
			}
			else
			{
				bdRueck = this.get_ANTEIL_LADEMENGE_ABN_bdValue(null);
			}
		}

		return bdRueck;
	}

	
	
	public HD_Station get_HD_StationEK() throws myException {
		String cName12 = 
				S.isEmpty(this.get_ID_ADRESSE_START_cUF_NN(""))?
						"<lieferant>":
						this.get_UP_RECORD_ADRESSE_id_adresse_start().get_NAME1_cUF_NN("<name1>")+" "+this.get_UP_RECORD_ADRESSE_id_adresse_start().get_NAME2_cUF_NN("<name2>");
		
		String cOrt = 
				S.isEmpty(this.get_ID_ADRESSE_START_cUF_NN(""))?
						"<ort>":
						this.get_UP_RECORD_ADRESSE_id_adresse_start().get_ORT_cUF_NN("<ort>");
		
		String cSorte = 
				S.isEmpty(this.get_ID_ARTIKEL_BEZ_EK_cUF_NN(""))?
						"<sorte-ek>":
						this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<anr1>")+"-"+
						this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ANR2_cUF_NN("<anr2>")+" "+this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ARTBEZ1_cUF_NN("<artbez1>");
		
		
		return new ownHdStation(
					true, true, 
					this.get_ID_ADRESSE_START_cUF_NN(""), 
					this.get_ID_ADRESSE_LAGER_START_cUF_NN(""),
					this.get_ID_ARTIKEL_BEZ_EK_cUF_NN(""), 
					this.get_BD_AbrechnungsMenge(true), 
					this.get_TP_VERANTWORTUNG_cUF_NN(TAX_CONST.TP_VERANTWORTUNG_MANDANT),
					this,
					null,
					cName12,
					cOrt,
					cSorte
					);
	}
	
	
	public HD_Station get_HD_StationVK() throws myException {
		String cName12 = 
				S.isEmpty(this.get_ID_ADRESSE_ZIEL_cUF_NN(""))?
						"<abnehmer>":
						this.get_UP_RECORD_ADRESSE_id_adresse_ziel().get_NAME1_cUF_NN("<name1>")+" "+this.get_UP_RECORD_ADRESSE_id_adresse_ziel().get_NAME2_cUF_NN("<name2>");
		
		String cOrt = 
				S.isEmpty(this.get_ID_ADRESSE_ZIEL_cUF_NN(""))?
						"<ort>":
						this.get_UP_RECORD_ADRESSE_id_adresse_ziel().get_ORT_cUF_NN("<ort>");
		
		String cSorte = 
				S.isEmpty(this.get_ID_ARTIKEL_BEZ_VK_cUF_NN(""))?
						"<sorte-vk>":
						this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<anr1>")+"-"+
						this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_ANR2_cUF_NN("<anr2>")+" "+this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_ARTBEZ1_cUF_NN("<artbez1>");

		
		
		return new ownHdStation(
						true, false, 
						this.get_ID_ADRESSE_ZIEL_cUF_NN(""), 
						this.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(""),
						this.get_ID_ARTIKEL_BEZ_VK_cUF_NN(""), 
						this.get_BD_AbrechnungsMenge(false), 
						this.get_TP_VERANTWORTUNG_cUF_NN(""),
						this,
						null,
						cName12,
						cOrt,
						cSorte);
	}

	
	
	
	
	
	
	private class ownHdStation extends HD_Station {

		public ownHdStation(boolean p_bHauptFuhre, boolean p_bQuelle,
							String p_cID_ADRESSE_JUR_UF, String p_cID_ADRESSE_GEO_UF,
							String p_cID_ARTIKEL_BEZ_UF, BigDecimal p_bdAbrechnungsMenge,
							String p_cTP_Verantwortung, RECORD_VPOS_TPA_FUHRE p_RecFuhre,
							RECORD_VPOS_TPA_FUHRE_ORT p_RecFuhreOrt,
							String p_cInfoTextName1_2, 
							String p_cInfoTextOrt,
							String p_cInfoTextSorte) throws myException {
			super();
			
			this.init(		p_bHauptFuhre, 
							p_bQuelle, 
							p_cID_ADRESSE_JUR_UF, 
							p_cID_ADRESSE_GEO_UF, 
							p_cID_ARTIKEL_BEZ_UF, 
							p_bdAbrechnungsMenge, 
							p_cTP_Verantwortung, 
							p_RecFuhre, 
							null, 
							p_cInfoTextName1_2, 
							p_cInfoTextOrt, 
							p_cInfoTextSorte,
							p_bQuelle?p_RecFuhre.get_EINZELPREIS_EK_bdValue(BigDecimal.ZERO):p_RecFuhre.get_EINZELPREIS_VK_bdValue(BigDecimal.ZERO),
							new MyDate(p_bQuelle?RecFuhre4Testlauf.this.get_DATUM_AUFLADEN_cF_NN(""):RecFuhre4Testlauf.this.get_DATUM_ABLADEN_cF_NN(""))
							);
		}

		@Override
		public MyE2_MessageVector applyResults(HD_WarenBewegungEinstufung 	oHD_Fuhreneinstufung, String cID_TAX_UF, String cIntrastat_YN,String cTransit_YN, boolean bEK_true__VK_false)	throws myException {
			MyE2_MessageVector oMV= new MyE2_MessageVector();
			return oMV;
		}

		@Override
		public boolean isUpdatingAllowd() throws myException {
			
			return RecFuhre4Testlauf.this.bSaveAllowed;
		}

			
	}




	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.Service.PdServiceFindHandelsdefSettings.StationsCreator#createStationen()
	 */
	@Override
	public HD_Stationen createStationen() throws myException {
		HD_Stationen s = new HD_Stationen(true);
		s.add(this.get_HD_StationEK());
		s.add(this.get_HD_StationVK());
		return s;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.Service.PdServiceFindHandelsdefSettings.StationsCreator#getRecordId()
	 */
	@Override
	public Long getRecordId() throws myException {
		return this.get_ID_VPOS_TPA_FUHRE_lValue(null);
	}
		
	
	
	
	
	
}
