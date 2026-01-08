package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Locale;

import nextapp.echo2.app.layout.GridLayoutData;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;

public class BSRG_K_LIST_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {


	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		
		
		MyE2_Label oLabLocked =   (MyE2_Label)oMAP.get__Comp(BSRG__CONST.HASH_KEY_ANZEIGE_LOCKED);

		
		// jetzt den locked-button verarzten
		if (oUsedResultMAP.get_FormatedValue("ABGESCHLOSSEN").equals("Y"))
			oLabLocked.setIcon(BSRG__CONST.LABEL_POSITION_LOCKED);
		else
			oLabLocked.setIcon(BSRG__CONST.LABEL_EMPTY);
		
		
		

		MyE2_Row oRow = (MyE2_Row)oMAP.get__Comp(BSRG__CONST.HASH_KEY_ANZEIGE_POSITIONS);
		oRow.removeAll();
		String cID_VKOPF = oUsedResultMAP.get_cUNFormatedROW_ID();
		
		String cQuery1 =   "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE   NVL(DELETED,'N')='N' AND  ID_VKOPF_RG="+cID_VKOPF+" AND POSITION_TYP<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"'";
		String cQuery2 =   "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE   NVL(DELETED,'N')='N' AND   ID_VKOPF_RG="+cID_VKOPF+" AND POSITION_TYP<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"' AND EINZELPREIS IS NOT NULL";
		 
		String cWert1 = bibDB.EinzelAbfrage(cQuery1,"@@@","@@@","@@@");
		String cWert2 = bibDB.EinzelAbfrage(cQuery2,"@@@","@@@","@@@");
		
		if (cWert1.equals("@@@") || cWert2.equals("@@@"))
		{
			oRow.setBackground(new E2_ColorAlarm());
			oRow.add(new MyE2_Label("ERROR",MyE2_Label.STYLE_SMALL_ITALIC()));
		}
		else
		{
			if (cWert1.equals(cWert2))
			{
				oRow.setBackground(new E2_ColorBase());
				oRow.add(new MyE2_Label(cWert1,MyE2_Label.STYLE_SMALL_ITALIC()));
				oRow.add(new MyE2_Label("/",MyE2_Label.STYLE_SMALL_ITALIC()));
				oRow.add(new MyE2_Label(cWert2,MyE2_Label.STYLE_SMALL_ITALIC()));
			}
			else
			{
				oRow.setBackground(new E2_ColorAlarm());
				oRow.add(new MyE2_Label(cWert1,MyE2_Label.STYLE_SMALL_ITALIC()));
				oRow.add(new MyE2_Label("/",MyE2_Label.STYLE_SMALL_ITALIC()));
				oRow.add(new MyE2_Label(cWert2,MyE2_Label.STYLE_SMALL_ITALIC()));
			}
		}
		
		boolean bDeleted = oUsedResultMAP.get_FormatedValue("DELETED").equals("Y");
		
		if (bDeleted)
		{
			oMAP.set_AllComponentsAsDeleted();
		}
	
	
		MyE2_Row  oRowStorno = (MyE2_Row)oMAP.get__Comp(BSRG__CONST.HASH_KEY_ANZEIGE_STORNO_STATUS);
		oRowStorno.removeAll();
		if 	(S.isFull(oUsedResultMAP.get_UnFormatedValue("ID_VKOPF_RG_STORNO_NACHFOLGER")))
		{
			MyE2_Label  oLabel = new MyE2_Label(E2_ResourceIcon.get_RI("storno_beleg.png"));
			oLabel.setToolTipText(new MyE2_String("Beleg wurde storniert - BelegID des Storno-Satzes: ",true,oUsedResultMAP.get_UnFormatedValue("ID_VKOPF_RG_STORNO_NACHFOLGER"),false).CTrans());
			oRowStorno.add(oLabel, E2_INSETS.I_0_0_0_0);
		}
		else if (S.isFull(oUsedResultMAP.get_UnFormatedValue("ID_VKOPF_RG_STORNO_VORGAENGER")))
		{
			MyE2_Label  oLabel = new MyE2_Label(E2_ResourceIcon.get_RI("storno_gegenbeleg.png"));
			oLabel.setToolTipText(new MyE2_String("Beleg ist Gegenbuchung eines Stornos - BelegID des Ursprungs-Satzes: ",true,oUsedResultMAP.get_UnFormatedValue("ID_VKOPF_RG_STORNO_VORGAENGER"),false).CTrans());
			oRowStorno.add(oLabel, E2_INSETS.I_0_0_0_0);
		}
		else
		{
			String iAnzahl = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' AND ID_VKOPF_RG="+cID_VKOPF+" AND "+
					"(NVL(ID_VPOS_RG_STORNO_VORGAENGER,0)<>0 OR NVL(ID_VPOS_RG_STORNO_NACHFOLGER,0)<>0)").trim();
			
			if (!iAnzahl.equals("0"))
			{
				MyE2_Label  oLabel = new MyE2_Label(E2_ResourceIcon.get_RI("storno_teil.png"));
				oLabel.setToolTipText(new MyE2_String("In diesem Beleg befinden sich Positionen aus einem Storno-Zyklus ! ").CTrans());
				oRowStorno.add(oLabel, E2_INSETS.I_0_0_0_0);
			}
		}
		
		
		//2011-02-17: das subquery-summenfeld formatieren
		MyE2_DB_Label_INROW  oLabel = (MyE2_DB_Label_INROW)oMAP.get__Comp("SUBQUERY__ENDBETRAG_RG");
		
		//standard:
		oLabel.EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP());

		
		DotFormatter oDF = new DotFormatter(oLabel.get_cActualMaskValue(), 2, Locale.GERMAN, true, 3);
		if (oDF.doFormat())
		{
			oLabel.set_cActualMaskValue(oDF.getStringFormated());
			if (oDF.get_oDouble()<0)
			{
				//ROT
				GridLayoutData  oGL = LayoutDataFactory.get_GL_Copy(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutRightTOP(), new E2_ColorAlarm());
				oLabel.EXT().set_oLayout_ListElement(oGL);
			}
			
		}
		
		
		
	}

}
