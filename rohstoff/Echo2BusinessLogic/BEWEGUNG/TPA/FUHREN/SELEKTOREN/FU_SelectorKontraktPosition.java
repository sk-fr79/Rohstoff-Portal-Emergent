package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class FU_SelectorKontraktPosition extends E2_ListSelectorStandard implements IF_CanBePopulated
{
	private String EK_VK = null;
	private String[][] arrayLeer =  {{"-",""}};

	public FU_SelectorKontraktPosition(String cEK_VK) throws myException
	{
		super(new __SelectFieldKontrakt(), 
				"(" +
				" JT_VPOS_TPA_FUHRE.ID_VPOS_KON_" +cEK_VK+ "=#WERT# OR " +
				"  (" +
				"      JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE IN " +
				"      (SELECT FUO.ID_VPOS_TPA_FUHRE FROM JT_VPOS_TPA_FUHRE_ORT FUO WHERE NVL(FUO.DELETED,'N')='N' AND FUO.ID_VPOS_KON=#WERT#)" +
				"   )"+
				")", 
				new MyE2_String(cEK_VK+"-Kontrakt:"), new Integer(10));
		
		this.EK_VK = cEK_VK; 
		
		this.set_oExtendForTextRahmen(new Extent(100));
		
	}
	
	//wird immer von der darueberliegenden firmenadresse gefuellt
	public void populate(String cID_ADRESSE) throws myException
	{
		if (S.isEmpty(cID_ADRESSE))
		{
			((MyE2_SelectField)this.get_oComponentWithoutText()).set_ListenInhalt(this.arrayLeer, false);
		}
		else
		{
		
			String cSQL = "SELECT NVL(VK.BUCHUNGSNUMMER,'??')||'-'||NVL(VP.POSITIONSNUMMER,'')||' '||"+
							" '['||NVL(VP.ANR1,'')||'-'||NVL(VP.ANR2,'')||'] '|| "+
							" TO_CHAR(VP.ANZAHL,'999G999G999', 'NLS_NUMERIC_CHARACTERS='',.''')||' '||NVL(VP.EINHEITKURZ,'??') ||' '|| NVL(VP.ARTBEZ1,'')||''||'   ('|| "+
							" NVL(TO_CHAR(KT.GUELTIG_VON,'dd.mm.'),'-')||' - '|| "+
							" NVL(TO_CHAR(KT.GUELTIG_BIS,'dd.mm.'),'-')||')', "+
							" VP.ID_VPOS_KON "+ 
							" FROM JT_VPOS_KON  VP "+
							" LEFT OUTER JOIN JT_VKOPF_KON VK ON (VP.ID_VKOPF_KON=VK.ID_VKOPF_KON) "+
							" INNER JOIN JT_VPOS_KON_TRAKT  KT ON (KT.ID_VPOS_KON=VP.ID_VPOS_KON) "+
							" WHERE VK.VORGANG_TYP='"+this.EK_VK+"_KONTRAKT' "+
							" AND      NVL(VK.DELETED,'N')='N' "+
							" AND      NVL(VP.DELETED,'N')='N' "+
							" AND     VK.ID_ADRESSE ="+cID_ADRESSE+
							" ORDER BY KT.GUELTIG_BIS DESC,VP.ANR1 ";
			
			String[][] arID_VPOS_KON = bibDB.EinzelAbfrageInArray(cSQL,"-");
			
			if (arID_VPOS_KON==null)
			{
				throw new myException(this,"Error populating the contracts: Error in Query: "+cSQL);
			}

			String[][] cFuellArray = bibARR.get_First_N_ArrayPositions(arID_VPOS_KON, 500);
			cFuellArray = bibARR.add_emtpy_db_value_inFront(cFuellArray,true);
			
//			String[][] cFuellArray = new String[arID_VPOS_KON.length+1][2];
//			
//			cFuellArray[0][0]="-";
//			cFuellArray[0][1]="";
//			
//			int ABBRUCH = 500;     //nur max 500 neueste kontrakte einlesen
//			
//			
//			
//			for (int i=0;i<arID_VPOS_KON.length;i++)
//			{
//				cFuellArray[i+1][0]=arID_VPOS_KON[i][0];
//				cFuellArray[i+1][1]=arID_VPOS_KON[i][1];
//				if (i>ABBRUCH)
//				{
//					break;
//				}
//			}
//			
			((MyE2_SelectField)this.get_oComponentWithoutText()).set_ListenInhalt(cFuellArray, false);
		}
	}
	
	
	
}



