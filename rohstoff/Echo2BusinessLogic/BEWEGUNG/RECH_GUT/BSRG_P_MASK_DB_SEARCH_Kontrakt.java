package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskLoadHelper;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.My_VPos_KON;
import rohstoff.utils.ECHO2.DB_SEARCH_Kontrakt;

public class BSRG_P_MASK_DB_SEARCH_Kontrakt extends DB_SEARCH_Kontrakt 
{

	public BSRG_P_MASK_DB_SEARCH_Kontrakt(SQLField osqlField) throws myException 
	{
		super(osqlField);
		
		this.set_oMaskActionAfterMaskValueIsFound(new ownActionAfterFound());
		this.add_ValidatorStartSearch(new ownSettingValidator());
		 
	}

	
	private class  ownActionAfterFound extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			if (bibALL.isEmpty(cMaskValue))
				return;
			
			if (!bAfterAction)
				return;
			
			//felder fuellen 
			My_VPos_KON oVPOS_KON = new My_VPos_KON(cMaskValue);
			
			// maske füellen
			E2_ComponentMAP oMap = oSearchField.EXT().get_oComponentMAP();

			MaskLoadHelper oMaskLoader = new MaskLoadHelper(oMap,oVPOS_KON);

			oMaskLoader.addFieldToFill("ID_ARTIKEL");
			oMaskLoader.addFieldToFill("ID_ARTIKEL_BEZ",true);
			oMaskLoader.addFieldToFill("LIEFERBEDINGUNGEN");
			oMaskLoader.addFieldToFill("ARTBEZ1");
			oMaskLoader.addFieldToFill("ARTBEZ2");
			oMaskLoader.addFieldToFill("ANR1");
			oMaskLoader.addFieldToFill("ANR2");
			oMaskLoader.addFieldToFill("EINZELPREIS");
			oMaskLoader.addFieldToFill("EINZELPREIS_FW");
			oMaskLoader.addFieldToFill("ID_WAEHRUNG_FREMD");
			oMaskLoader.addFieldToFill("WAEHRUNGSKURS");
			oMaskLoader.addFieldToFill("EINHEITKURZ");
			oMaskLoader.addFieldToFill("EINHEIT_PREIS_KURZ");
			oMaskLoader.addFieldToFill("STEUERSATZ");
			oMaskLoader.addFieldToFill("MENGENDIVISOR");

			MaskLoadHelper oMaskLoader2 = new MaskLoadHelper(oMap,oVPOS_KON.get_oVKopfKON());
			oMaskLoader2.addFieldToFill("ID_ADRESSE");
			
			oMaskLoader.FillFields();
			oMaskLoader2.FillFields();

			
			//zahlungsbedingungen
			String cID_UF_Zahlungsbed = oVPOS_KON.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN");
			if (!bibALL.isEmpty(cID_UF_Zahlungsbed))
			{
				RECORD_ZAHLUNGSBEDINGUNGEN oMZ = new RECORD_ZAHLUNGSBEDINGUNGEN(cID_UF_Zahlungsbed);
				
				((MyE2_DB_SelectField)oMap.get__Comp("ID_ZAHLUNGSBEDINGUNGEN")).set_ActiveValue_OR_FirstValue(oMZ.get_ID_ZAHLUNGSBEDINGUNGEN_cF());
				((MyE2IF__DB_Component)oMap.get__Comp("ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(oMZ.get_BEZEICHNUNG_cF_NN(""));
				((MyE2IF__DB_Component)oMap.get__Comp("ZAHLTAGE")).set_cActualMaskValue(oMZ.get_ZAHLTAGE_cF_NN(""));
				((MyE2IF__DB_Component)oMap.get__Comp("FIXMONAT")).set_cActualMaskValue(oMZ.get_FIXMONAT_cF_NN(""));
				((MyE2IF__DB_Component)oMap.get__Comp("FIXTAG")).set_cActualMaskValue(oMZ.get_FIXTAG_cF_NN(""));
				((MyE2IF__DB_Component)oMap.get__Comp("SKONTO_PROZENT")).set_cActualMaskValue(oMZ.get_SKONTO_PROZENT_cF_NN(""));
				
			}
			else
			{
				((MyE2_DB_SelectField)oMap.get__Comp("ID_ZAHLUNGSBEDINGUNGEN")).set_ActiveValue_OR_FirstValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("ZAHLTAGE")).set_cActualMaskValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("FIXMONAT")).set_cActualMaskValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("FIXTAG")).set_cActualMaskValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("SKONTO_PROZENT")).set_cActualMaskValue("");
			}

			
			
			
		}
	}
	
	
	
	
	private class ownSettingValidator extends XX_ActionValidator
	{

		// anhand des lagervorzeichens rauskriegen, ob es eine EK- oder VK-Position ist
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException 
		{
			DB_SEARCH_Kontrakt oThis = BSRG_P_MASK_DB_SEARCH_Kontrakt.this;

			MyE2_MessageVector vRueck = new MyE2_MessageVector();
			E2_ComponentMAP oMap = oThis.EXT().get_oComponentMAP();

			/*
			 * bevor die suche beginnen kann, muss folgendes vorhanden sein:
			 * 1. eine definierte adresse (entweder ueber einen kopf oder ueber eine adress-id 
			 */
			String cID_ADRESSE = null;
			if (oMap.get_oSQLFieldMAP().get_("ID_VKOPF_RG") instanceof SQLFieldForRestrictTableRange)
			{
				String cID_VKOPF_RG = ((SQLFieldForRestrictTableRange)oMap.get_oSQLFieldMAP().get_("ID_VKOPF_RG")).get_cRestictionValue_IN_DB_FORMAT();
				if (bibALL.isLong(cID_VKOPF_RG))
				{
					cID_ADRESSE = new RECORD_VKOPF_RG(cID_VKOPF_RG).get_ID_ADRESSE_cUF();
				}
				else
				{
					throw new myException(this,"Error Querying ID_ADRESSE");
				}
			}
			else
			{
				Long lID_ADRESSE = oMap.get_LActualDBValue("ID_ADRESSE", true, true, null);
				if (lID_ADRESSE != null)
					cID_ADRESSE = ""+lID_ADRESSE.longValue();
			}
			
			if (cID_ADRESSE==null)
			{
				vRueck.add_MESSAGE(new MyE2_Alarm_Message("Eine Kontraktposition kann nur geladen werden, wenn es einen Kopfsatz oder eine Adresse gibt !"));
				return vRueck;
			}
			
			
			//ausserdem muss feststehen, ob es eine wareneingangs- oder warenausgangsposition ist
			Long lBelegTyp= oMap.get_LActualDBValue("LAGER_VORZEICHEN", true, true, null);
			if (lBelegTyp==null)
			{
				vRueck.add_MESSAGE(new MyE2_Alarm_Message("Eine Kontraktposition kann nur geladen werden, wenn das Lagervorzeichen definiert ist !"));
				return vRueck;
			}
			
			// ein leeres suchfeld ist nur erlaubt
			oThis.get_oSeachBlock().set_bAllowEmptySearchField(true);
			
			if (lBelegTyp.longValue()==1)
			{
				oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();
				oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add("JT_VKOPF_KON.VORGANG_TYP='"+myCONST.VORGANGSART_EK_KONTRAKT+"'");
				oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add("JT_VKOPF_KON.ID_ADRESSE="+cID_ADRESSE);
			}
			else if (lBelegTyp.longValue()==-1)
			{
				oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();
				oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add("JT_VKOPF_KON.VORGANG_TYP='"+myCONST.VORGANGSART_VK_KONTRAKT+"'");
				oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add("JT_VKOPF_KON.ID_ADRESSE="+cID_ADRESSE);
				
			}
			else
			{
				throw new myException(this,":Error setting Belegtype !");
			}
			
			return vRueck;
		}


		public MyE2_MessageVector isValid(String cID_Unformated)
		{
			return new MyE2_MessageVector();
		}

		
	}

}
