package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MaskSearchField.XX_SearchBlockNonDB;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;

public class FUS_SearchAngebote__SearchBlock extends XX_SearchBlockNonDB
{

	private String 	cSQL_SelectBlock = "SELECT JT_VPOS_STD.*," +
								" NVL(JT_VKOPF_STD.BUCHUNGSNUMMER,TO_CHAR(JT_VKOPF_STD.ID_VKOPF_STD))||'-'||NVL(TO_CHAR(JT_VPOS_STD.POSITIONSNUMMER),'?') AS BUCHNR, " +
							"    NVL(TO_CHAR(DRUCKDATUM,'dd.mm.yy'),'<datum>') AS DRUCKDATUM,"+
							"    NVL(JT_VKOPF_STD.NAME1,'')||' '||NVL(JT_VKOPF_STD.ORT,'') AS FIRMA,"+
							"    to_char(NVL(JT_VPOS_STD.ANZAHL,0),'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.''')||' '||NVL(JT_VPOS_STD.EINHEITKURZ,'EH')  AS MENGE, "+
							"    NVL(JT_VPOS_STD.ANR1,'')||'-'||NVL(JT_VPOS_STD.ANR2,'') AS SORTE , "+
							"    NVL(JT_VPOS_STD.ARTBEZ1,'')||' '||CASE WHEN JT_VPOS_STD.ARTBEZ2 IS NOT NULL THEN CSCONVERT(' // '||JT_VPOS_STD.ARTBEZ2,'NCHAR_CS') ELSE CSCONVERT('','NCHAR_CS') END AS ARTBEZ , "+
							"    NVL(to_char(JT_VPOS_STD.EINZELPREIS_FW,'fm999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'-')||' '||NVL(JD_WAEHRUNG.WAEHRUNGSSYMBOL,'??') AS PREIS , "+
							"    NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'dd.mm.yy'),'-')||' - '||NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'dd.mm.yy'),'-') AS GUELTIG "+
							" FROM JT_VPOS_STD " +
							" LEFT OUTER JOIN JT_VKOPF_STD ON (JT_VPOS_STD.ID_VKOPF_STD=JT_VKOPF_STD.ID_VKOPF_STD) "+
							" LEFT OUTER JOIN JT_VPOS_STD_ANGEBOT ON (JT_VPOS_STD_ANGEBOT.ID_VPOS_STD=JT_VPOS_STD.ID_VPOS_STD) "+
							" LEFT OUTER JOIN JD_WAEHRUNG ON (JT_VKOPF_STD.ID_WAEHRUNG_FREMD= JD_WAEHRUNG.ID_WAEHRUNG) "+ 
							" WHERE NVL(JT_VPOS_STD.DELETED,'N')='N'";
	

	private String 					cSQL_OrderBlock = "ANR1,ANR2";
	
	
	
	private FUS_SearchAngebote    	oFUS_SearchAngebote = null;
	
	

	


	/**
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting
	 * @param cSQLOrderBlock
	 * @param iMaxresults
	 * @throws myException
	 */
	public FUS_SearchAngebote__SearchBlock() throws myException
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
		
		RECLIST_VPOS_STD   recListVPOS_STD = this.get_Results(cSearchText);
		
		
		if (recListVPOS_STD.get_vKeyValues().size()==0)
			return vRueck;     // nix gefunden
		
		
		for (int i=0;i<recListVPOS_STD.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_STD recVPOS_STD = recListVPOS_STD.get(i);
			
			XX_Button4SearchResultList[] bZeile = new XX_Button4SearchResultList[7];

			Font oFontNormal = 	new E2_FontPlain();
			
			bZeile[0] = new ownButton(recVPOS_STD.get___KETTE(bibALL.get_Vector("BUCHNR")), 		recVPOS_STD.get_ID_VPOS_STD_cUF(),oFontNormal);
			bZeile[1] = new ownButton(recVPOS_STD.get___KETTE(bibALL.get_Vector("DRUCKDATUM")),		recVPOS_STD.get_ID_VPOS_STD_cUF(),oFontNormal);
			bZeile[2] = new ownButton(recVPOS_STD.get___KETTE(bibALL.get_Vector("FIRMA")), 			recVPOS_STD.get_ID_VPOS_STD_cUF(),oFontNormal);
			bZeile[3] = new ownButton(recVPOS_STD.get___KETTE(bibALL.get_Vector("MENGE")), 			recVPOS_STD.get_ID_VPOS_STD_cUF(),oFontNormal);
			bZeile[4] = new ownButton(recVPOS_STD.get___KETTE(bibALL.get_Vector("SORTE")), 			recVPOS_STD.get_ID_VPOS_STD_cUF(),oFontNormal);
			bZeile[5] = new ownButton(recVPOS_STD.get___KETTE(bibALL.get_Vector("PREIS")), 			recVPOS_STD.get_ID_VPOS_STD_cUF(),oFontNormal);
			bZeile[6] = new ownButton(recVPOS_STD.get___KETTE(bibALL.get_Vector("GUELTIG")), 		recVPOS_STD.get_ID_VPOS_STD_cUF(),oFontNormal);
			
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
	
	
	private RECLIST_VPOS_STD get_Results(String SearchText) throws myException
	{
		
		String cSQL_WhereBlockForSelecting = 
			" UPPER(JT_VPOS_STD.ARTBEZ1) LIKE UPPER('%#WERT#%')" +
			" OR UPPER(JT_VPOS_STD.ARTBEZ1) LIKE UPPER('%#WERT#%') " +
			" OR UPPER(JT_VPOS_STD.ANR1) LIKE UPPER('%#WERT#%')" +
			" OR UPPER(JT_VPOS_STD.ANR2) = UPPER('#WERT#')" +
			" OR  TRIM(TO_CHAR(JT_VPOS_STD.ID_VPOS_STD))='#WERT#' "+
			" OR NVL(UPPER(JT_VKOPF_STD.BUCHUNGSNUMMER),'-') LIKE UPPER('%#WERT#%') ";      

		
		String cSQL = null;

		Vector<String> vWheres = new Vector<String>();
		
		// jetzt nachsehen, ob aktuell ein weiterer where-statement-vector vorhanden ist
		if (this.get_vZusatzWhereBedingungen() != null && this.get_vZusatzWhereBedingungen().size()>0)
			vWheres.addAll(this.get_vZusatzWhereBedingungen());

		//gesucht wird hier nur alle kontrakte einer Firma, kein suchfeld
		
		FUS_SearchAdresse  oSearchAdresse = this.oFUS_SearchAngebote.get_bIS_EK()?new _SEARCH_SearchAdressFields().get_Found_EK_AdressField():new _SEARCH_SearchAdressFields().get_Found_VK_AdressField();

		RECORD_ADRESSE  recKunde = oSearchAdresse.get_ActualRecHauptAdresse();
		
		if (recKunde!=null)
		{
			vWheres.add("JT_VKOPF_STD.ID_ADRESSE="+recKunde.get_ID_ADRESSE_cUF());
			vWheres.add("JT_VKOPF_STD.VORGANG_TYP='"+(this.oFUS_SearchAngebote.get_bIS_EK()?myCONST.VORGANGSART_ABNAHMEANGEBOT:myCONST.VORGANGSART_ANGEBOT)+"'");
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

		return new RECLIST_VPOS_STD(cSQL);
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

	
	public void set_oFUS_SearchAngebote(FUS_SearchAngebote FUS_SearchAngebote)
	{
		this.oFUS_SearchAngebote = FUS_SearchAngebote;
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
