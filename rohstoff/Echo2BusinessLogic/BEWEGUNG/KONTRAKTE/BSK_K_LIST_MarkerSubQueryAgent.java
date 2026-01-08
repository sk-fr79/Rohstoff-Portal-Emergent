package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class BSK_K_LIST_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	private MyDBToolBox oDB = bibALL.get_myDBToolBox();
	
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}

	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		
		
		MyE2_Label oLabLocked =   (MyE2_Label)oMAP.get__Comp(BSK__CONST.HASH_KEY_ANZEIGE_LOCKED);

		
		// jetzt den locked-button verarzten
		if (oUsedResultMAP.get_FormatedValue("ABGESCHLOSSEN").equals("Y"))
		{
			oLabLocked.setIcon(BSK__CONST.LABEL_KOPF_LOCKED);
		}
		else
		{
			oLabLocked.setIcon(BSK__CONST.LABEL_EMPTY);
		}
		
		
		

		MyE2_Row oRow = (MyE2_Row)oMAP.get__Comp(BSK__CONST.HASH_KEY_ANZEIGE_POSITIONS);
		oRow.removeAll();
		String cID_VKOPF = oUsedResultMAP.get_cUNFormatedROW_ID();
		
		String cQuery1 =   "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE   NVL(DELETED,'N')='N' AND   ID_VKOPF_KON="+cID_VKOPF+" AND POSITION_TYP<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"'";
		String cQuery2 =   "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE   NVL(DELETED,'N')='N' AND   ID_VKOPF_KON="+cID_VKOPF+" AND POSITION_TYP<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"' AND EINZELPREIS IS NOT NULL";
		 
		String cWert1 = this.oDB.EinzelAbfrage(cQuery1,"@@@","@@@","@@@");
		String cWert2 = this.oDB.EinzelAbfrage(cQuery2,"@@@","@@@","@@@");
		
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
	

		
		
	}

}
