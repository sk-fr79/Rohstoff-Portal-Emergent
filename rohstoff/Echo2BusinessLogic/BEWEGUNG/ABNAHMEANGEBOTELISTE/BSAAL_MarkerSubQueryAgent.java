package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	private MyDBToolBox 	oDB = bibALL.get_myDBToolBox();
	
	// speicher fuer die dienstleistungen
	private Vector<String>		vDienstleistungen = new Vector<String>();
	private GridLayoutData		oGridHighlight = new GridLayoutData();
	private RowLayoutData		oRowHighlight = new RowLayoutData();
	
	private E2_NavigationList	 oNaviList = null;
	
	
	public BSAAL_MarkerSubQueryAgent(E2_NavigationList	NaviList)
	{
		super();
		// dienstleistungen sammeln
		String[][] cHelp = this.oDB.EinzelAbfrageInArray("SELECT ANR1 FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE DIENSTLEISTUNG='Y'");
		for (int i=0;i<cHelp.length;i++)
			this.vDienstleistungen.add(cHelp[i][0]);
		
		this.oNaviList = NaviList;
		
		this.oGridHighlight.setBackground(new E2_ColorHelpBackground());
		this.oGridHighlight.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		
		this.oRowHighlight.setBackground(new E2_ColorHelpBackground());
		this.oRowHighlight.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		
	}


	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}

	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
		MyE2_Label oLab = (MyE2_Label)oMAP.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_SYMBOL);
		oLab.setIcon(BSAAL__CONST.LABEL_EMPTY);
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		
		String cID_KOPF = oUsedResultMAP.get_UnFormatedValue("K_ID_VKOPF_STD");
		String cID_POS = oUsedResultMAP.get_cUNFormatedROW_ID();
		
		MyE2_Label oLab =		 (MyE2_Label)oMAP.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_SYMBOL);
		MyE2_Label oLabLocked =   (MyE2_Label)oMAP.get__Comp(BSAAL__CONST.HASH_KEY_ANZEIGE_LOCKED);
		
		
		
		String cQuery = "SELECT ID_VPOS_STD,  NVL(DELETED,'N'),  DEL_GRUND FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE JT_VPOS_STD.POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"' AND " +
						"  ID_VKOPF_STD="+cID_KOPF+ " ORDER BY ANR1,ANR2";
		
		String[][] cHelp = this.oDB.EinzelAbfrageInArray(cQuery,"");
		
		Vector<String> vHelpUndeleted = new Vector<String>();
		
		Vector<String> vHelpDeleted = 	new Vector<String>();
		Vector<String> vHelpDel_grund= 	new Vector<String>();
		
		for (int i=0;i<cHelp.length;i++)
		{
			if (cHelp[i][1].equals("Y"))
			{
				vHelpDeleted.add(cHelp[i][0]);		   // hier werden nur deleted-symbole gezeigt
				vHelpDel_grund.add(cHelp[i][2]);
			}
			else
				vHelpUndeleted.add(cHelp[i][0]);     // hier werden die klammern angezeigt
		}
		
		String[] cHelpUndel = new String[vHelpUndeleted.size()];
		for (int i=0;i<vHelpUndeleted.size();i++)
		{
			cHelpUndel[i]=((String)vHelpUndeleted.get(i));
		}
		
		/*
		 * es gibt zwei moeglichkeiten, wenn die klammer ausgeblendet werden soll:
		 * 1. es wurden teile der liste ausgeblendet (z.b. nur anzeige einer sorte)
		 * 2. es wurde eine sortierung gewaehlt, die die angebotspositionen nicht zusammenhaelt
		 *    Die natuerliche sortierung ist hier eine mehrfachsortierung.
		 *    bei allen standard-sortierungen in der liste ist der sortiervector nur 1 lang. 
		 */
		
		if ( (! this.oNaviList.get_vectorSegmentation().containsAll(vHelpUndeleted)) || 
			 ( this.oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_vOrderFields().size()==1) )  // bedeutet: es wurden positionen ausgeblendet, damit gibt es unvollstaendige angebote
		{
			oLab.setIcon(BSAAL__CONST.LABEL_EMPTY);  
		}
		else if (vHelpDeleted.contains(cID_POS))
		{
			oLab.setIcon(BSAAL__CONST.LABEL_POSITION_DELETED);
			oLab.setToolTipText((String)vHelpDel_grund.get(vHelpDeleted.indexOf(cID_POS)));    // den loeschgrund als tooltip anzeigen
		}
		else
		{
			if (cHelp==null)
			{
				oLab.setIcon(BSAAL__CONST.LABEL_EMPTY);            // fehlerfall
			}
			else if (cHelpUndel.length==0)
			{
				oLab.setIcon(BSAAL__CONST.LABEL_EMPTY);             
			}
			else if (cHelpUndel.length==1)
			{
				oLab.setIcon(BSAAL__CONST.LABEL_ONE_POSITION);
			}
			else
			{
				// feststellen, ob vorne, hinten oder in der mitte
				for (int i=0;i<cHelpUndel.length;i++)
				{
					if (cID_POS.equals(cHelpUndel[i]))
					{
						if (i==0)
							oLab.setIcon(BSAAL__CONST.LABEL_START_POSITION);
						else if (i==(cHelpUndel.length-1))
							oLab.setIcon(BSAAL__CONST.LABEL_END_POSITION);
						else
							oLab.setIcon(BSAAL__CONST.LABEL_MIDDLE_POSITION);
					
						break;
					}
				}
			}
		}
		
		// jetzt den locked-button verarzten
		if (oUsedResultMAP.get_FormatedValue("K_ABGESCHLOSSEN").equals("Y"))
			oLabLocked.setIcon(BSAAL__CONST.LABEL_POSITION_LOCKED);
		else
			oLabLocked.setIcon(BSAAL__CONST.LABEL_EMPTY);
		
		
		
		// jetzt dienstleistungen markieren
		String cANR1 = bibALL.null2leer((String)oUsedResultMAP.get_UnFormatedValue("ANR1"));
		if (!cANR1.equals(""))
		{
			if (this.vDienstleistungen.contains(cANR1))
			{
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ANR1")).get_RenderedComponent().setLayoutData(this.oGridHighlight);
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ANR2")).get_RenderedComponent().setLayoutData(this.oGridHighlight);
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ARTBEZ1")).get_RenderedComponent().setLayoutData(this.oGridHighlight);
				
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ANR1")).setLayoutData(this.oRowHighlight);
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ANR2")).setLayoutData(this.oRowHighlight);
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ARTBEZ1")).setLayoutData(this.oRowHighlight);
				
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ANR1")).get_oErsatzButton().setLayoutData(this.oRowHighlight);
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ANR2")).get_oErsatzButton().setLayoutData(this.oRowHighlight);
				((MyE2_DB_Label_INROW)oMAP.get__Comp("ARTBEZ1")).get_oErsatzButton().setLayoutData(this.oRowHighlight);
				
			}
		}
		
		
		/*
		 * jetzt geloeschte datensaetze markieren
		 */
		boolean bDeleted = oUsedResultMAP.get_FormatedValue("DELETED").equals("Y");
		if (bDeleted)
		{
			oMAP.set_AllComponentsAsDeleted();
		}
		
		
	}

}
