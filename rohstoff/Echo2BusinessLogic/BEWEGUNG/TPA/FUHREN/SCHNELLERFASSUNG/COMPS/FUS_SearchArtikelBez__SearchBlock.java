package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MaskSearchField.XX_SearchBlockNonDB;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchSortenFields;

public class FUS_SearchArtikelBez__SearchBlock extends XX_SearchBlockNonDB
{

	private String 					cSQL_SelectBlock = "SELECT JT_ARTIKEL_BEZ.*, JT_ARTIKEL.ANR1 FROM " +
													   " JT_ARTIKEL_BEZ LEFT OUTER JOIN JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL) "+
													   " WHERE NVL(JT_ARTIKEL.AKTIV,'N')='Y' AND NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y'";
	
	
	
	private String 					cSQL_OrderBlock = "ANR1,ANR2";
	
	
	private String     				cSQL_WhereBlockForSelecting = 	" UPPER(JT_ARTIKEL.ARTBEZ1) 	LIKE UPPER('%#WERT#%') OR " +
																	" UPPER(JT_ARTIKEL_BEZ.ARTBEZ1) LIKE UPPER('%#WERT#%') OR " +
																	" UPPER(JT_ARTIKEL.ANR1) 		LIKE UPPER('%#WERT#%') OR " +
																	" UPPER(JT_ARTIKEL_BEZ.ANR2)	LIKE UPPER('#WERT#') OR " +
																	" TRIM(TO_CHAR(JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ))='#WERT#' OR " +
																	" TRIM(TO_CHAR(JT_ARTIKEL_BEZ.ID_ARTIKEL))='#WERT#'"; 
	
	
	private FUS_SearchArtikelBez 	oFUS_SearchArtikelBez  = 	null;         
	





	/**
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting
	 * @param cSQLOrderBlock
	 * @param iMaxresults
	 * @throws myException
	 */
	public FUS_SearchArtikelBez__SearchBlock() throws myException
	{
		super();
	}


	public void set_oFUS_SearchArtikelBez(FUS_SearchArtikelBez FUS_SearchArtikelBez)
	{
		this.oFUS_SearchArtikelBez = FUS_SearchArtikelBez;
	}

	
	
	/**
	 * @param cSearchText
	 * @return  Vector with MyE2_Button - Components, in EXT().cMERKMAL steht der wert der gefundenen ersten spalte
	 * 			normalerweise ist das eine ID (unformatiert)
	 * @throws myException
	 */
	public Vector<XX_Button4SearchResultList[]> get_vResultButtons(String SearchText) throws myException
	{
		Vector<XX_Button4SearchResultList[]> 	vRueck = new Vector<XX_Button4SearchResultList[]>();

		String cSearchText	= SearchText.trim();

		this.set_bAllowEmptySearchField(false);

		
		//mehrere varianten
		//1. EK-Seite, Fremdadresse
		if (this.oFUS_SearchArtikelBez.get_bIS_EK())
		{
			RECORD_ADRESSE recLadeFirma = new _SEARCH_SearchAdressFields().get_Found_EK_AdressField().get_ActualRecHauptAdresse();
			
			if (recLadeFirma!=null)
			{
				this.set_bAllowEmptySearchField(true);
				vRueck = this.__get_vResultButtons(cSearchText,true,recLadeFirma,recLadeFirma.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")));
			}
		}
		else
		{
			//wird auf der VK-Seite eine suche mit leerem feld gestartet, dann werden alle sortenbezeichungen gefunden, die zur linken sorte gehoeren 
			RECORD_ADRESSE recAbladeFirma = new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().get_ActualRecHauptAdresse();
			
			if (recAbladeFirma != null)
			{
				this.set_bAllowEmptySearchField(true);
				vRueck = this.__get_vResultButtons(cSearchText,false,recAbladeFirma,recAbladeFirma.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")));
			}
		}
		
		return vRueck;
	}
	
	
	private Vector<XX_Button4SearchResultList[]> __get_vResultButtons(String cSearchText, boolean bEK, RECORD_ADRESSE recFirmaQuelleZiel, boolean bAdresseIstMandant) throws myException
	{
		Vector<XX_Button4SearchResultList[]> 	vRueck = new Vector<XX_Button4SearchResultList[]>();
		
//		FUS_FastInputGrid  oFUS_InputGrid = new FUS_RecursiveSearchFastInputGrid(this.oFUS_SearchArtikelBez).get_found_InputGrid();

		
		this.get_vZusatzWhereBedingungen().removeAllElements();

		//fremde ladeadressen duerfen nur gelistete sorten
		if (bEK && !bAdresseIstMandant)
		{
			this.get_vZusatzWhereBedingungen().add("JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ IN (SELECT DISTINCT JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ FROM "+
					bibE2.cTO()+".JT_ARTIKELBEZ_LIEF " +
					" WHERE "+
					" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+recFirmaQuelleZiel.get_ID_ADRESSE_cUF()+")");
		}
		
		
		//wenn vk-seite und suchtext ist leer, dann alle sorten-bezeichungen, die mit der EK-sorte korrelieren
		if (!bEK && S.isEmpty(cSearchText))
		{
		    RECORD_ARTIKEL recArtikelEK =  new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().get_ActualRecArtikel();
			
			if (recArtikelEK==null)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler! Felder vorher MÜSSEN korrekt gefüllt sein !!"));
				return vRueck;
			}
			
			this.get_vZusatzWhereBedingungen().add("JT_ARTIKEL_BEZ.ID_ARTIKEL="+recArtikelEK.get_ID_ARTIKEL_cUF());
		}
		
		
		
		RECLIST_ARTIKEL_BEZ recListArtBez= this.get_Results(cSearchText);
		
		
		if (recListArtBez.get_vKeyValues().size()==0)
			return vRueck;     // nix gefunden
		
		
		for (int i=0;i<recListArtBez.get_vKeyValues().size();i++)
		{
			RECORD_ARTIKEL_BEZ recArtBez = recListArtBez.get(i);
			
			XX_Button4SearchResultList[] bZeile = new XX_Button4SearchResultList[2];
			

			Font oFontNormal = 	new E2_FontPlain();
			Font oFontBold = 	new E2_FontBold();
			//Font oFontSmall = 	new E2_FontPlain(-2);
			
			
			bZeile[0] = new ownButton(recArtBez.get___KETTE(bibALL.get_Vector("ANR1", "ANR2")), 				recArtBez.get_ID_ARTIKEL_BEZ_cUF(),oFontBold);
			bZeile[1] = new ownButton(recArtBez.get___KETTE(bibALL.get_Vector("ARTBEZ1")),						recArtBez.get_ID_ARTIKEL_BEZ_cUF(),oFontNormal);
			
			vRueck.add(bZeile);
		}

		
		return vRueck;
	}
	
	
	
	private class ownButton extends Button4SearchResultList
	{

		public ownButton(String cText, String cID_ADRESSE, Font oFont)
		{
			super(cText);
			this.EXT().set_C_MERKMAL(cID_ADRESSE);
			if (oFont!=null)
			{
				this.setFont(oFont);
			}
			this.setLineWrap(true);
		}
		
	}
	
	
	private RECLIST_ARTIKEL_BEZ get_Results(String SearchText) throws myException
	{
		String cSearchText = SearchText;
		
		String cSQL = null;

		Vector<String> vWheres = new Vector<String>();
		vWheres.add("1=1");
		
		
		// jetzt nachsehen, ob aktuell ein weiterer where-statement-vector vorhanden ist
		if (this.get_vZusatzWhereBedingungen() != null && this.get_vZusatzWhereBedingungen().size()>0)
			vWheres.addAll(this.get_vZusatzWhereBedingungen());
		
		
		if (!bibALL.isEmpty(cSearchText))
		{
			StringSeparator 	oSeparator = new StringSeparator(cSearchText," ");

			for (int i=0;i<oSeparator.size();i++)
			{
				if (oSeparator.get(i) != null)
				{
					vWheres.add( "("+bibALL.ReplaceTeilString(this.cSQL_WhereBlockForSelecting,"#WERT#",(String)oSeparator.get(i))+")");
				}
			}
		}
		
		String cWhereComplete = bibALL.Concatenate(vWheres," AND ", null);
		String cOrder = "";
		if (!bibALL.isEmpty(this.cSQL_OrderBlock))
			cOrder = " ORDER BY "+this.cSQL_OrderBlock;
		
		cSQL = this.cSQL_SelectBlock+" AND "+cWhereComplete+cOrder;

		return new RECLIST_ARTIKEL_BEZ(cSQL);
	}
	
	
//	@Override
//	public Component get_ContainerWithFoundButtons()
//	{
//		return null;
//	}
//	

	
	@Override
	public E2_BasicModuleContainer get_ContainerForShowResults()
	{
		
		return new ownBasicModuleContainer();
	}
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{

		public ownBasicModuleContainer()
		{
			super();
			
			this.set_oExtWidth(new Extent(600));
			this.set_oExtHeight(new Extent(600));

		}
		
	}

	
	

	
}
