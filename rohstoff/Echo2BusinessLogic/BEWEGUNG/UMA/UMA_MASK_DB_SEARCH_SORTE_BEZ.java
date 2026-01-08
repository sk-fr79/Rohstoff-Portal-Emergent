package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_TAG;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class UMA_MASK_DB_SEARCH_SORTE_BEZ extends DB_SEARCH_ArtikelBez
{
	private boolean bAusgangsSorte = false;

	public UMA_MASK_DB_SEARCH_SORTE_BEZ(SQLField osqlField, boolean IstAusgangssorte) throws myException
	{
		super(osqlField,  null, null, null, null, null);
		
		this.bAusgangsSorte = IstAusgangssorte;
		
		this.add_ValidatorStartSearch(new VALID_ADRESS_VORHANDEN());
		
		this.get_oSeachBlock().set_bAllowEmptySearchField(true);
		
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		this.get_oTextFieldForSearchInput().set_iWidthPixel(80);
		
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontPlain(-2));
		this.get_oLabel4Anzeige().set_iWidth(250);

		this.set_oMaskActionAfterMaskValueIsFound(new ownActionAfterFound());
		
		
	}
	
	private class ownActionAfterFound extends XX_MaskActionAfterFound
	{
		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException
		{
			UMA_MASK_DB_SEARCH_SORTE_BEZ oThis = UMA_MASK_DB_SEARCH_SORTE_BEZ.this;
			
			MyLong  lValue = new MyLong(cMaskValue);
			
			if (lValue.get_bOK())
			{
				UMA_MASK_EINHEITEN_LABEL  oLabel = null;
				if (oThis.bAusgangsSorte)
				{
					oLabel = new _SEARCH_EINHEITENLABEL_AUSGANGSSORTE().get_foundLabel();
				}
				else
				{
					oLabel = new _SEARCH_EINHEITENLABEL_ZIELSORTE().get_foundLabel();
				}
				
				if (oLabel!=null)
				{
					RECORD_ARTIKEL  recArtAusgang = new RECORD_ARTIKEL_BEZ(lValue.get_lValue()).get_UP_RECORD_ARTIKEL_id_artikel();
					
					if (recArtAusgang.get_UP_RECORD_EINHEIT_id_einheit()!=null)
					{
						oLabel.set_Text(recArtAusgang.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN("<eh>"));
					}
				}
			}
			
		}
		
	}
	
	

	
	
	/**
	 * @author martin
	 * Validator hat folgende aufgabe:
	 * 1. muss sicherstellen, die hauptsorte vorhanden ist
	 * 2. fuegt zusatz-bedingung bei der suche ein, die nur noch sorten zulaesst, die in der kundenspezifischen
	 *    artikelbezeichnung vorhanden sind.
	 */
	private class VALID_ADRESS_VORHANDEN extends XX_ActionValidator
	{
		
	
		public VALID_ADRESS_VORHANDEN()
		{
			super();
		}


		public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			String cError = "";
			
			UMA_MASK_DB_SEARCH_SORTE_BEZ oThis = UMA_MASK_DB_SEARCH_SORTE_BEZ.this;
			
			try
			{
				//E2_ComponentMAP oMap = oThis.EXT().get_oComponentMAP();
				MyE2IF__DB_Component otfAdresse = (MyE2IF__DB_Component)new E2_RecursiveSearch_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.MODUL_UMA_KONTRAKT_MASKENFELD_ID_ADRESSE).get_SingleFoundComponent();

				if (otfAdresse!=null)
				{
				
					String cID_ADRESSE = bibALL.ReplaceTeilString(otfAdresse.get_cActualMaskValue(),".","");
	
					if (!bibALL.isInteger(cID_ADRESSE))
					{
						cError = "Bitte geben Sie zuerst die Firma ein !!";
					}
					else
					{
						String cARTBEZ_TYP="'VK'";
						if (oThis.bAusgangsSorte) { cARTBEZ_TYP="'EK'";}

						
						String cWhereZusatz = 
									"JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ IN (SELECT DISTINCT JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ FROM "+
									bibE2.cTO()+".JT_ARTIKELBEZ_LIEF " +
									" WHERE "+
									" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+cID_ADRESSE+" AND NVL(JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP,'EK')="+cARTBEZ_TYP+")";
						
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add(cWhereZusatz);
					}
				}
				else
				{
					cError = "Programmfehler: Adress-Prüfung ist nicht möglich !!";
				}
				
			}
			catch (myException ex)
			{
				cError = "Fehler beim Pruefen der Adressenfelder !!";
			}
			
			if (S.isFull(cError))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(cError));
			}
			return oMV;
		}

		public MyE2_MessageVector isValid(String cID_Unformated)
		{
			return new MyE2_MessageVector();
		}
		
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			UMA_MASK_DB_SEARCH_SORTE_BEZ  oCopy = new UMA_MASK_DB_SEARCH_SORTE_BEZ(this.EXT_DB().get_oSQLField(), this.bAusgangsSorte);
			return oCopy;
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy("UMA_MASK_DB_SEARCH_SORTE_BEZ: copy-Error");
		}
		
	}


}
