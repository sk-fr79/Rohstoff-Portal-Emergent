package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MaskSearchField.XX_SearchBlockNonDB;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.__RECORD_VPOS_KON_EXT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchKontraktFields;

public class FUS_SearchKontrakte__SearchBlock extends XX_SearchBlockNonDB
{

	private String 	cSQL_SelectBlock = "SELECT JT_VPOS_KON.*," +
								" NVL(JT_VKOPF_KON.BUCHUNGSNUMMER,TO_CHAR(JT_VKOPF_KON.ID_VKOPF_KON))||'-'||NVL(TO_CHAR(JT_VPOS_KON.POSITIONSNUMMER),'?') AS BUCHNR, " +
							"    NVL(TO_CHAR(DRUCKDATUM,'dd.mm.yy'),'<datum>') AS DRUCKDATUM,"+
							"    NVL(JT_VKOPF_KON.NAME1,'')||' '||NVL(JT_VKOPF_KON.ORT,'') AS FIRMA,"+
							"    to_char(NVL(JT_VPOS_KON.ANZAHL,0),'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.''')||' '||NVL(JT_VPOS_KON.EINHEITKURZ,'EH')  AS MENGE, "+
							"    0 AS REST, "+
							"    NVL(JT_VPOS_KON.ANR1,'')||'-'||NVL(JT_VPOS_KON.ANR2,'') AS SORTE , "+
							"    NVL(JT_VPOS_KON.ARTBEZ1,'')||' '||CASE WHEN JT_VPOS_KON.ARTBEZ2 IS NOT NULL THEN CSCONVERT(' // '||JT_VPOS_KON.ARTBEZ2,'NCHAR_CS') ELSE CSCONVERT('','NCHAR_CS') END AS ARTBEZ , "+
							"    NVL(to_char(JT_VPOS_KON.EINZELPREIS_FW,'fm999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'-')||' '||NVL(JD_WAEHRUNG.WAEHRUNGSSYMBOL,'??') AS PREIS , "+
							"    NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'dd.mm.yy'),'-')||' - '||NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'dd.mm.yy'),'-') AS GUELTIG "+
							" FROM JT_VPOS_KON " +
							" LEFT OUTER JOIN JT_VKOPF_KON ON (JT_VPOS_KON.ID_VKOPF_KON=JT_VKOPF_KON.ID_VKOPF_KON) "+
							" LEFT OUTER JOIN JT_VPOS_KON_TRAKT ON (JT_VPOS_KON_TRAKT.ID_VPOS_KON=JT_VPOS_KON.ID_VPOS_KON) "+
							" LEFT OUTER JOIN JD_WAEHRUNG ON (JT_VKOPF_KON.ID_WAEHRUNG_FREMD=JD_WAEHRUNG.ID_WAEHRUNG) "+ 
							" WHERE NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' " +
							" AND   NVL(JT_VPOS_KON.DELETED,'N')='N'";
	

	
	private String 					cSQL_OrderBlock = "ANR1,ANR2";
	
	
//	private String     cSQL_WhereBlockForSelecting = "    UPPER(VORNAME) LIKE UPPER('%#WERT#%') " +
//														" OR UPPER(NAME1) LIKE UPPER('%#WERT#%') " +
//														" OR UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
//														 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
//														 "OR LIEF_NR='#WERT#'  " +
//														 "OR ABN_NR='#WERT#'  " +
//														 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
//														 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
//														 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'"; 

	
	private FUS_SearchKontrakte    oFUS_SearchKontrakte = null;
	
	



	/**
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting
	 * @param cSQLOrderBlock
	 * @param iMaxresults
	 * @throws myException
	 */
	public FUS_SearchKontrakte__SearchBlock() throws myException
	{
		super();
	}

	
	
	/**
	 * @param cSearchText
	 * @return  Vector with MyE2_Button - Components, in EXT().cMERKMAL steht der wert der gefundenen ersten spalte
	 * 			normalerweise ist das eine ID (unformatiert)
	 * @throws myException
	 */
	public Vector<XX_Button4SearchResultList[]> get_vResultButtons(String SearchText) throws myException
	{
		String cSearchText	= SearchText.trim();
		
		Vector<XX_Button4SearchResultList[]> 	vRueck = new Vector<XX_Button4SearchResultList[]>();
		
		RECLIST_VPOS_KON   recListVPOS_KON = this.get_Results(cSearchText);
		
		
		if (recListVPOS_KON.get_vKeyValues().size()==0)
			return vRueck;     // nix gefunden
		
		//auch die moeglichen zugeordneten kontrakte kenntlichmachen
//		RECORD_VPOS_KON  recVPOS_KON_DieseSeite = this.oFUS_SearchKontrakte.get;
		RECORD_VPOS_KON  recVPOS_KON_AndereSeite = null;
		boolean          bThisIsEK = this.oFUS_SearchKontrakte.get_bIS_EK();
		
		if (bThisIsEK)
		{
			recVPOS_KON_AndereSeite = new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField().get_Actual_RECORD_VPOS_KON();
		}
		else
		{
			recVPOS_KON_AndereSeite = new _SEARCH_SearchKontraktFields().get_Found_EK_KontraktField().get_Actual_RECORD_VPOS_KON();
		}
		
		for (int i=0;i<recListVPOS_KON.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_KON recVPOS_KON = recListVPOS_KON.get(i);
			
			XX_Button4SearchResultList[] bZeile = new XX_Button4SearchResultList[8];

			
			//restmenge feststellen
			__RECORD_VPOS_KON_EXT  oRecKonExt = new __RECORD_VPOS_KON_EXT(recVPOS_KON);
			String cRestMenge = MyNumberFormater.formatDez(oRecKonExt.get_bdRestMenge(), 0, true);
			
			boolean bEsBestehtZuordnung = false;
			if (recVPOS_KON_AndereSeite != null)
			{
				if (bThisIsEK)
				{
					//dann ist die andere Seite die VK-Seite
					if (recVPOS_KON_AndereSeite.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk(
							"ID_VPOS_KON_EK="+recVPOS_KON.get_ID_VPOS_KON_cUF(), null, true).get_vKeyValues().size()>0)
					{
						bEsBestehtZuordnung = true;
					}
				}
				else
				{
					//dann ist die andere Seite die EK-Seite
					if (recVPOS_KON_AndereSeite.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek(
							"ID_VPOS_KON_VK="+recVPOS_KON.get_ID_VPOS_KON_cUF(), null, true).get_vKeyValues().size()>0)
					{
						bEsBestehtZuordnung = true;
					}
				}
							
			}
			
			Font   oFontBold = bEsBestehtZuordnung?new E2_FontBold():new E2_FontPlain(); 
			
			bZeile[0] = new ownButton(recVPOS_KON.get___KETTE(bibALL.get_Vector("BUCHNR")), 			recVPOS_KON.get_ID_VPOS_KON_cUF(),oFontBold,E2_ALIGN.LEFT_MID);
			bZeile[1] = new ownButton(recVPOS_KON.get___KETTE(bibALL.get_Vector("DRUCKDATUM")),			recVPOS_KON.get_ID_VPOS_KON_cUF(),oFontBold,E2_ALIGN.LEFT_MID);
			bZeile[2] = new ownButton(recVPOS_KON.get___KETTE(bibALL.get_Vector("FIRMA")), 				recVPOS_KON.get_ID_VPOS_KON_cUF(),oFontBold,E2_ALIGN.LEFT_MID);
			bZeile[3] = new ownButton(recVPOS_KON.get___KETTE(bibALL.get_Vector("MENGE")), 				recVPOS_KON.get_ID_VPOS_KON_cUF(),oFontBold,E2_ALIGN.RIGHT_MID);
			bZeile[4] = new ownButton(cRestMenge+" "+oRecKonExt.get_EINHEITKURZ_cUF_NN("<EH>"),			recVPOS_KON.get_ID_VPOS_KON_cUF(),new E2_FontItalic(),E2_ALIGN.RIGHT_MID);
			bZeile[5] = new ownButton(recVPOS_KON.get___KETTE(bibALL.get_Vector("SORTE")), 				recVPOS_KON.get_ID_VPOS_KON_cUF(),oFontBold,E2_ALIGN.LEFT_MID);
			bZeile[6] = new ownButton(recVPOS_KON.get___KETTE(bibALL.get_Vector("PREIS")), 				recVPOS_KON.get_ID_VPOS_KON_cUF(),oFontBold,E2_ALIGN.RIGHT_MID);
			bZeile[7] = new ownButton(recVPOS_KON.get___KETTE(bibALL.get_Vector("GUELTIG")), 			recVPOS_KON.get_ID_VPOS_KON_cUF(),oFontBold,E2_ALIGN.CENTER_MID);
			
			vRueck.add(bZeile);
		}
		
		return vRueck;
	}
	
	
	private class ownButton extends Button4SearchResultList
	{

		public ownButton(String cText, String cID_VPOS_KON, Font oFont, Alignment oAlign)
		{
			super(cText);
			this.EXT().set_C_MERKMAL(cID_VPOS_KON);
			
			this.setAlignment(oAlign);
			
			if (oFont!=null)
			{
				this.setFont(oFont);
			}
			this.setLineWrap(true);
		}
		
	}
	
	
	private RECLIST_VPOS_KON get_Results(String SearchText) throws myException
	{
		
		String cSQL_WhereBlockForSelecting = 
					" UPPER(JT_VPOS_KON.ARTBEZ1) LIKE UPPER('%#WERT#%')" +
					" OR UPPER(JT_VPOS_KON.ARTBEZ1) LIKE UPPER('%#WERT#%') " +
					" OR UPPER(JT_VPOS_KON.ANR1) LIKE UPPER('%#WERT#%')" +
					" OR UPPER(JT_VPOS_KON.ANR2) = UPPER('#WERT#')" +
					" OR  TRIM(TO_CHAR(JT_VPOS_KON.ID_VPOS_KON))='#WERT#' "+
					" OR NVL(UPPER(JT_VKOPF_KON.BUCHUNGSNUMMER),'-') LIKE UPPER('%#WERT#%') ";      

		
		String cSQL = null;

		Vector<String> vWheres = new Vector<String>();
		
		// jetzt nachsehen, ob aktuell ein weiterer where-statement-vector vorhanden ist
		if (this.get_vZusatzWhereBedingungen() != null && this.get_vZusatzWhereBedingungen().size()>0)
			vWheres.addAll(this.get_vZusatzWhereBedingungen());

		//gesucht wird hier nur alle kontrakte einer Firma, kein suchfeld
		
		FUS_SearchAdresse  oSearchAdresse = this.oFUS_SearchKontrakte.get_bIS_EK()?new _SEARCH_SearchAdressFields().get_Found_EK_AdressField():new _SEARCH_SearchAdressFields().get_Found_VK_AdressField();

		RECORD_ADRESSE  recKunde = oSearchAdresse.get_ActualRecHauptAdresse();
		
		if (recKunde!=null)
		{
			vWheres.add("JT_VKOPF_KON.ID_ADRESSE="+recKunde.get_ID_ADRESSE_cUF());
			vWheres.add("JT_VKOPF_KON.VORGANG_TYP='"+(this.oFUS_SearchKontrakte.get_bIS_EK()?myCONST.VORGANGSART_EK_KONTRAKT:myCONST.VORGANGSART_VK_KONTRAKT)+"'");
		}
		else
		{
			vWheres.add("1=2");
		}
		
		
		
		//jetzt die sucheingaben verarbeiten (falls vorhande)
		String cSearchText = SearchText;
		if (!bibALL.isEmpty(cSearchText))
		{
			StringSeparator 	oSeparator = new StringSeparator(cSearchText," ");

			for (int i=0;i<oSeparator.size();i++)
			{
				if (oSeparator.get(i) != null)
				{
					vWheres.add( "("+bibALL.ReplaceTeilString(cSQL_WhereBlockForSelecting,"#WERT#",(String)oSeparator.get(i))+")");
				}
			}
		}

		
		
		String cWhereComplete = bibALL.Concatenate(vWheres," AND ", null);
		String cOrder = "";
		if (!bibALL.isEmpty(this.cSQL_OrderBlock))
			cOrder = " ORDER BY "+this.cSQL_OrderBlock;
		
		cSQL = this.cSQL_SelectBlock+" AND "+cWhereComplete+cOrder;

		return new RECLIST_VPOS_KON(cSQL);
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

	
	public void set_oFUS_SearchKontrakte(FUS_SearchKontrakte FUS_SearchKontrakte)
	{
		this.oFUS_SearchKontrakte = FUS_SearchKontrakte;
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
