package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.FieldValidator_FieldDisabled;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterPrepareContentForNew;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_SearchBlock;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Component;

public class FU_MASK_DB_SEARCH_PREIS extends MyE2_DB_MaskSearchField
{
	
	private String cEK_VK = null;
	
	public FU_MASK_DB_SEARCH_PREIS(SQLField osqlField, String EK_VK) throws myException
	{
		super(osqlField, null, "SELECT"+ 
				"    '<'||TRIM(NVL("+bibE2.cTO()+" .JT_VPOS_STD.ANR1,''))||'-'||"+ 
				"    TRIM(NVL("+bibE2.cTO()+".JT_VPOS_STD.ANR2,''))||'> '||"+ 
				"    '<'||TRIM(NVL("+bibE2.cTO()+".JT_VPOS_STD.ARTBEZ1,''))||'> '||"+ 
				"    '<'||TRIM(NVL("+bibE2.cTO()+".JT_VKOPF_STD.NAME1,''))||' '||"+
				"    TRIM(NVL("+bibE2.cTO()+".JT_VKOPF_STD.PLZ,''))||' '||"+ 
				"    TRIM(NVL("+bibE2.cTO()+".JT_VKOPF_STD.ORT,''))||'> --->   <'||"+ 
				"    TRIM(NVL(TO_CHAR("+bibE2.cTO()+".JT_VPOS_STD.EINZELPREIS,'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),''))||'>'"+
				" FROM "+ 
				"    "+bibE2.cTO()+".JT_VPOS_STD"+ 
				" INNER JOIN "+ 
				"    "+bibE2.cTO()+".JT_VKOPF_STD"+ 
				"    ON"+ 
				"    ("+
				"        "+bibE2.cTO()+".JT_VPOS_STD.ID_VKOPF_STD = "+bibE2.cTO()+".JT_VKOPF_STD.ID_VKOPF_STD"+
				"    )"+ 
				" WHERE JT_VPOS_STD.ID_VPOS_STD=#WERT#", null,true);

		//2014-08-25: wenn kein angebot gefunden wurde, dann auch einen Preis loeschen ueber die doMaskSettingsAfterPrepareContentForNew-action
		this.set_bCleanAfterFoundNothing(true);
		
		
		//2012-05-24: radierer bei der suche nach angeboten einblenden
		
		this.cEK_VK = EK_VK;
		
		this.set_oSeachBlock(new ownSearchBlock());
		this.get_oTextFieldForSearchInput().EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskSettings());
		this.set_oMaskActionAfterPrepareContentForNew(new ownActionCleanLieferbed());   //2014-05-04
		this.set_oPopupWidth(new Extent(700));
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		this.get_oTextFieldForSearchInput().set_iWidthPixel(100);

		this.add_ValidatorStartSearch(new XX_ActionValidator()
		{
			@Override
			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
				//nachsehen, ob eine Firma angegeben ist
				FU_MASK_DB_SEARCH_PREIS oThis = FU_MASK_DB_SEARCH_PREIS.this;
				
				boolean bEK = oThis.cEK_VK.equals("EK");
				String cID_Adresse = null;
				String cID_Artikel_Bez = null;
				
				if (oThis.cEK_VK.equals("EK"))
				{
					cID_Adresse = ""+oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_ADRESSE_START", true,true,new Long(0)).longValue();
					cID_Artikel_Bez = ""+oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_ARTIKEL_BEZ_EK", true,true,new Long(0)).longValue();
				}
				else
				{
					cID_Adresse = ""+oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_ADRESSE_ZIEL", true,true,new Long(0)).longValue();
					cID_Artikel_Bez = ""+oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_ARTIKEL_BEZ_VK", true,true,new Long(0)).longValue();
				}
					   
				if (cID_Adresse.equals("0"))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst eine Firma suchen !"));
				}
				if (cID_Artikel_Bez.equals("0"))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst eine Sorte/Sortenbezeichnung suchen !"));
				}

				if (cID_Adresse.equals(bibALL.get_ID_ADRESS_MANDANT()))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Auf der Lagerseite gibt es keinen Preis !!!"));

					E2_ComponentMAP oMap = FU_MASK_DB_SEARCH_PREIS.this.EXT().get_oComponentMAP();

					//einzelpreis gleich reinholen, manuell raus
					oThis.prepare_ContentForNew(false);
					((MyE2_DB_TextField) oMap.get_hmRealComponents().get("EINZELPREIS_"+(bEK?"EK":"VK"))).setText("0");
					((MyE2_DB_CheckBox) oMap.get_hmRealComponents().get("MANUELL_PREIS_"+(bEK?"EK":"VK"))).setSelected(true);
				}
				return oMV;
			}

			@Override
			protected MyE2_MessageVector isValid(String unformated)	throws myException
			{
				return null;
			}
			
		});
		
	}

	private class ownSearchBlock extends XX_SearchBlock
	{
		

		public ownSearchBlock()
		{
			super();
		}

		public Vector<XX_Button4SearchResultList[]> get_vResultButtons(String SearchText) throws myException
		{
			Vector<XX_Button4SearchResultList[]>  vRueck = new Vector<XX_Button4SearchResultList[]> ();

			FU_MASK_DB_SEARCH_PREIS oThis = FU_MASK_DB_SEARCH_PREIS.this;
			
			boolean bEK = oThis.cEK_VK.equals("EK");
			
			long lID_ADRESSE = oThis.EXT().get_oComponentMAP().get_LActualDBValue(
								bEK?"ID_ADRESSE_START":"ID_ADRESSE_ZIEL", true, true, new Long(-1)).longValue();

			
			long lID_ARTBEZ = oThis.EXT().get_oComponentMAP().get_LActualDBValue(
								bEK?"ID_ARTIKEL_BEZ_EK":"ID_ARTIKEL_BEZ_VK", true, true, new Long(-1)).longValue();

			MyDate mdLieferdatum = oThis.EXT().get_oComponentMAP().get_ActualMyDate(
											bEK?"DATUM_AUFLADEN":"DATUM_ABLADEN", true, true, null);
			
			
			String cStartDatum = null;
			String cEndDatum = null;
			
			if (mdLieferdatum != null)
			{
				cStartDatum=mdLieferdatum.get_cUmwandlungsergebnis();
				cEndDatum=mdLieferdatum.get_cUmwandlungsergebnis();
			}
			
			
			if (lID_ADRESSE>0 && lID_ARTBEZ>0 && S.isFull(cStartDatum) && S.isFull(cEndDatum))
			{
				String cID_ADRESSE	= ""+lID_ADRESSE;
				String cID_ARTBEZ 		= ""+lID_ARTBEZ;
				
				String cID_ARTIKEL_UF = new RECORD_ARTIKEL_BEZ(cID_ARTBEZ).get_ID_ARTIKEL_cUF();
				
				
				String cSQL_DB_VON = "TO_DATE('"+cStartDatum+"','DD.MM.YYYY')";
				String cSQL_DB_BIS = "TO_DATE('"+cEndDatum+"','DD.MM.YYYY')";

				FU___RECLIST_VPOS_STD_FOR_PREIS recListAngebote = new FU___RECLIST_VPOS_STD_FOR_PREIS(cSQL_DB_VON,cSQL_DB_BIS,cID_ADRESSE,cID_ARTIKEL_UF,bEK,null);

				if (recListAngebote.size() == 0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Kein passendes Angebot vorhanden !"));
				}
				else
				{
					Vector<String> vKettePos = bibALL.get_Vector("ANR1", "ANR2", "ARTBEZ1");
					Vector<String> vKetteKopf = bibALL.get_Vector("NAME1", "PLZ", "ORT");
					
					for (int i=0;i<recListAngebote.get_vKeyValues().size();i++)
					{
						XX_Button4SearchResultList[] butZeile = new XX_Button4SearchResultList[3];
						butZeile[0] = new Button4SearchResultList(recListAngebote.get(i).get___KETTE(vKettePos,"-","",""," - "));
						butZeile[1] = new Button4SearchResultList(recListAngebote.get(i).get_UP_RECORD_VKOPF_STD_id_vkopf_std().get___KETTE(vKetteKopf,"-","",""," - "));
						butZeile[2] = new Button4SearchResultList(recListAngebote.get(i).get_EINZELPREIS_cF_NN("--"));
						butZeile[0].EXT().set_C_MERKMAL(recListAngebote.get(i).get_ID_VPOS_STD_cUF());
						butZeile[1].EXT().set_C_MERKMAL(recListAngebote.get(i).get_ID_VPOS_STD_cUF());
						butZeile[2].EXT().set_C_MERKMAL(recListAngebote.get(i).get_ID_VPOS_STD_cUF());
						
						if (i==0)  //hauptadresse
						{
							for (int l=0;l<3;l++)
							{
								butZeile[l].setFont(new E2_FontBold());
							}
						}
						vRueck.add(butZeile);
					}
				}
			}
			else
			{
				throw new myExceptionForUser(new MyE2_String("Bitte zuerst die Maske füllen: ADRESSE, SORTE, LADE-/ABLADE-Datum !").CTrans());
			}
			
			return vRueck;
		}

//		@Override
//		public Component get_ContainerWithFoundButtons()
//		{
//			return null;
//		}

		@Override
		public E2_BasicModuleContainer get_ContainerForShowResults()
		{
			
			return new ownBasicModuleContainer();
		}
		
		private class ownBasicModuleContainer extends E2_BasicModuleContainer
		{
			
		}
		
	}
	
	
	//eigener actionagent der die anzeige der lieferbedingung resettet, falls der kontrakt geleert wird
	private class ownActionCleanLieferbed extends  XX_MaskActionAfterPrepareContentForNew  {
		@Override
		public void doMaskSettingsAfterPrepareContentForNew(MyE2_DB_MaskSearchField oDB_SearchField, boolean bSetDefaults) throws myException {
			//2014-06-02: die sonderlieferbedingung wieder entfernen
			FU_MASK_DB_SEARCH_PREIS oThis = FU_MASK_DB_SEARCH_PREIS.this;
			E2_ComponentMAP  oMAPFuhre = oThis.EXT().get_oComponentMAP();

//			FU_AL_Component oFU_AL_Component = (FU_AL_Component)oMAPFuhre.get__Comp(cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK);
			
			//oMAPFuhre.get__DBComp(oThis.cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK:_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK).set_cActualMaskValue("");
			((FU_AL_Component)oMAPFuhre.get__Comp(cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).clear_Lieferbedingungen();
			((FU_AL_Component)oMAPFuhre.get__Comp(cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).refreshLieferbedingung();
			
			//2014-08-25: den preis auch leermachen, da diese methode beim nichtfinden des angebotes aufgerufen wird
			boolean bEK = oThis.cEK_VK.equals("EK");
			boolean bManuellPreis = bEK?oMAPFuhre.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$MANUELL_PREIS_EK):oMAPFuhre.get_bActualDBValue(_DB.VPOS_TPA_FUHRE$MANUELL_PREIS_VK);
			if (!bManuellPreis) {
				((MyE2_DB_TextField) oMAPFuhre.get_hmRealComponents().get("EINZELPREIS_"+(bEK?"EK":"VK"))).setText("");
			}
		}
	}

	
	
	private class ownMaskSettings extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException
		{
			if (!bAfterAction) return;

			FU_MASK_DB_SEARCH_PREIS oThis = FU_MASK_DB_SEARCH_PREIS.this;

			boolean bEK = oThis.cEK_VK.equals("EK");
						
			E2_ComponentMAP oMap = FU_MASK_DB_SEARCH_PREIS.this.EXT().get_oComponentMAP();

			String cID_VPOS_STD = bibALL.ReplaceTeilString(cMaskValue, ".", "");
			
			RECORD_VPOS_STD recVPOS = new RECORD_VPOS_STD(cID_VPOS_STD);

			//einzelpreis gleich reinholen, manuell raus
			((MyE2_DB_TextField) oMap.get_hmRealComponents().get("EINZELPREIS_"+(bEK?"EK":"VK"))).setText(recVPOS.get_EINZELPREIS_cF_NN(""));
			((MyE2_DB_CheckBox) oMap.get_hmRealComponents().get("MANUELL_PREIS_"+(bEK?"EK":"VK"))).setSelected(false);
			

			//2014-04-28: wenn das angebot aus einem nicht abgeschlossen kopf stammt, dann die felder wieder loeschen
			if (recVPOS.get_UP_RECORD_VKOPF_STD_id_vkopf_std().is_ABGESCHLOSSEN_NO() && bibALL.get_RECORD_MANDANT().is_PREISFIND_ANGEB_NUR_GEDRUCKT_YES()) {
				((MyE2_DB_TextField) oMap.get_hmRealComponents().get("EINZELPREIS_"+(bEK?"EK":"VK"))).setText("");
				FU_MASK_DB_SEARCH_PREIS.this.prepare_ContentForNew(false);
			}
			
			//2014-06-05: die sonderlieferbedingung wieder entfernen
			((FU_AL_Component)oMap.get__Comp(oThis.cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).clear_Lieferbedingungen();
			((FU_AL_Component)oMap.get__Comp(oThis.cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).refreshLieferbedingung();
			//2014-06-02

		}
	}

	
	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}
	

}
