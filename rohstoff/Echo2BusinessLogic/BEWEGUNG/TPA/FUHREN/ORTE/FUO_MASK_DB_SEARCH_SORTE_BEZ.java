package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MaskSettingsAfterInput__Plausibilitaet;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___Fill_EAK_Code;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.utils.MyArtikelbezeichung_NG;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class FUO_MASK_DB_SEARCH_SORTE_BEZ extends DB_SEARCH_ArtikelBez
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	private String EK_VK = null;
	
	public FUO_MASK_DB_SEARCH_SORTE_BEZ(SQLField osqlField, String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(osqlField,  null, null, null, null, null);

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		this.EK_VK = cEK_VK;
		
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound());
		
		if(this.EK_VK.equals("EK"))
		{
			this.get_oSeachBlock().set_bAllowEmptySearchField(true);
		}
		
		
		//validierer bestimmt, dass suche erst moeglich, wenn eine adress-id vorliegt und redutziert die moeglichen
		//sortenbezeichner auf die gelisteten (im falle der EK-Seite)
		this.add_ValidatorStartSearch(new XX_ActionValidator()
		{
			@Override
			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
				FUO_MASK_DB_SEARCH_SORTE_BEZ oThis = FUO_MASK_DB_SEARCH_SORTE_BEZ.this;
				
				Long lID_ADRESSE = oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_ADRESSE", true, true, null);

				if (lID_ADRESSE==null)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst die Basis-Adresse laden !")));
				}
				else
				{
					oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();

					//im EK-fall muss die sortenbezeichnung gelistet sein 
					
//CODE:AVWRTZ					
					if (oThis.EK_VK.equals("EK"))
					{
						String cZusatz = null;
						//aenderung 2010-12-17: falls der lieferant der mandant ist, dann alle sorten zulassen
						if ((""+lID_ADRESSE).equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
						{
							String cZusatz1 = "JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ IN (" +
									"	SELECT ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE NVL(JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP,'-')='EK' AND ID_ADRESSE="+lID_ADRESSE+")";
							
							String cZusatz2 = "JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ IN (" +
									"	SELECT ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ " +
											" LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL = JT_ARTIKEL.ID_ARTIKEL) " +
													" WHERE NVL(JT_ARTIKEL.AKTIV,'N')='Y' AND  NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y' " +
													" AND  " +
													" (JT_ARTIKEL.ID_EAK_CODE_EX_MANDANT IS NOT NULL OR " +
													"  NVL(JT_ARTIKEL.IST_PRODUKT,'N')='Y' OR " +
													"  NVL(JT_ARTIKEL.DIENSTLEISTUNG,'N')='Y' OR " +
													"  NVL(JT_ARTIKEL.END_OF_WASTE,'N')='Y')  )";

							
							cZusatz = "("+cZusatz1+"OR "+cZusatz2+")";
							
						}
						else
						{
							cZusatz = "JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ IN (" +
									"   SELECT ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE NVL(JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP,'-')='EK' AND ID_ADRESSE="+lID_ADRESSE+")"; 
						}
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add(cZusatz);
					}
					
					
					
				}
				return oMV;
			}

			@Override
			protected MyE2_MessageVector isValid(String unformated)	throws myException
			{
				return null;
			}
			
		});

		
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		this.get_oTextForAnzeige().setWidth(new Extent(240));
	}

	
	
	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}

	
	
	
	/*
	 * mask-setting-agent fuer das laden der artikelbez in die maske, getrennt nach EK-Bezeichnung oder VK-Bezeichnung
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		public ownMaskActionAfterFound()
		{
			super();
		}



		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			FUO_MASK_DB_SEARCH_SORTE_BEZ oThis = FUO_MASK_DB_SEARCH_SORTE_BEZ.this;

			if (bAfterAction)
			{
				E2_ComponentMAP 			oMap = 				oSearchField.EXT().get_oComponentMAP();
			
				
				// lieferanten-Sorte muss den EAK_COD mitabfragen,
				// zielsorte nicht
				MyArtikelbezeichung_NG oArtBez = null;
				Long lID_ADRESSE = FUO_MASK_DB_SEARCH_SORTE_BEZ.this.EXT().get_oComponentMAP().get_LActualDBValue("ID_ADRESSE", true, true, null);
				
				if (oThis.EK_VK.equals("EK"))
				{
					boolean bMustBeListed = !(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("").equals(""+lID_ADRESSE));
					oArtBez = new MyArtikelbezeichung_NG(cMaskValue,(""+lID_ADRESSE),"EK",bMustBeListed);
				}
				else
				{
					oArtBez = new MyArtikelbezeichung_NG(cMaskValue,(""+lID_ADRESSE),"VK",false);
				}

				
				HashMap<String,MyE2IF__Component> oHMReal = oMap.get_REAL_ComponentHashMap();
					
				((MyE2IF__DB_Component)oHMReal.get("ANR1")).set_cActualMaskValue(oArtBez.get_ANR1());
				((MyE2IF__DB_Component)oHMReal.get("ANR2")).set_cActualMaskValue(oArtBez.get_ANR2());

				((MyE2IF__DB_Component)oHMReal.get("ARTBEZ1")).set_cActualMaskValue(oArtBez.get_ARTBEZ1());
				((MyE2IF__DB_Component)oHMReal.get("ARTBEZ2")).set_cActualMaskValue(oArtBez.get_ARTBEZ2());
					
				// allgemeine werte, unabhaengig von lieferant/abnehmer
				((MyE2IF__DB_Component)oHMReal.get("EUCODE")).set_cActualMaskValue(oArtBez.get_EUCODE());
				((MyE2IF__DB_Component)oHMReal.get("EUNOTIZ")).set_cActualMaskValue(oArtBez.get_EUNOTIZ());
				((MyE2IF__DB_Component)oHMReal.get("ZOLLTARIFNR")).set_cActualMaskValue(oArtBez.get_ZOLLTARIFNR());

				((MyE2IF__DB_Component)oHMReal.get("BASEL_CODE")).set_cActualMaskValue(oArtBez.get_BASEL_CODE());
				((MyE2IF__DB_Component)oHMReal.get("BASEL_NOTIZ")).set_cActualMaskValue(oArtBez.get_BASEL_NOTIZ());

				((MyE2IF__DB_Component)oHMReal.get("EINHEIT_MENGEN")).set_cActualMaskValue(oArtBez.get_cEINHEIT());

				((MyE2IF__DB_Component)oHMReal.get("ID_ARTIKEL")).set_cActualMaskValue(oArtBez.get_ID_ARTIKEL());
				
				// ---------------------------
				//2013-09-26: auch die steuerinfos loeschen
				oMap.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$STEUERSATZ).set_cActualMaskValue("");
				oMap.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$EU_STEUER_VERMERK).set_cActualMaskValue("");
				oMap.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$ID_TAX).set_cActualMaskValue("");

				
				
				
				if (FUO_MASK_DB_SEARCH_SORTE_BEZ.this.EK_VK.equals("EK"))
				{

					//aenderung 2010-12-17: der mandant als lieferant darf alle sorten laden
					((MyE2IF__DB_Component)oHMReal.get("ID_EAK_CODE")).set_cActualMaskValue("");
					((MASK_COMPONENT_SEARCH_EAK_CODES)oHMReal.get("ID_EAK_CODE")).FillLabelWithDBQuery("");
					
					boolean bLieferantIstMandant = bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--").equals(""+lID_ADRESSE);
					
					//laden des AVV-codes
					new FU___Fill_EAK_Code(oArtBez,(MASK_COMPONENT_SEARCH_EAK_CODES)oHMReal.get("ID_EAK_CODE"),bLieferantIstMandant);

					
					
					
				}
				
				
				//((FUO_MASK_ComponentMAP)oMap).PruefeGefahrgutStatus();
				
				if (bIS_PrimaryCall)
				{
					new FU__MaskSettingsAfterInput__Plausibilitaet(oThis);
				}
				
			}
		}
		
	}

	
}
