package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class BST_K_LIST_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

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
		
		
		MyE2_Label oLabLocked =   (MyE2_Label)oMAP.get__Comp(BST__CONST.HASH_KEY_ANZEIGE_LOCKED);

		
		// jetzt den locked-button verarzten
		if (oUsedResultMAP.get_FormatedValue("ABGESCHLOSSEN").equals("Y"))
			oLabLocked.setIcon(BST__CONST.LABEL_KOPF_LOCKED);
		else
			oLabLocked.setIcon(BST__CONST.LABEL_EMPTY);
		
		
		

		MyE2_Row oRow = (MyE2_Row)oMAP.get__Comp(BST__CONST.HASH_KEY_ANZEIGE_POSITIONS);
		oRow.removeAll();
		String cID_VKOPF = oUsedResultMAP.get_cUNFormatedROW_ID();
		
		String cQuery1 =   "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE   ID_VKOPF_TPA="+cID_VKOPF+" AND   NVL(DELETED,'N')='N' AND POSITION_TYP<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"'";
		String cQuery2 =   "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE   ID_VKOPF_TPA="+cID_VKOPF+" AND   NVL(DELETED,'N')='N' AND POSITION_TYP<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"' AND EINZELPREIS IS NOT NULL";
		 
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

	
		
		//NEU01
		// markierung wenn gefaehrlicher stoff im transport vorkommt (in die oben benutzte row mit einbauen)
		String cQueryGefahr = "SELECT COUNT(JT_VPOS_TPA.ID_VPOS_TPA) FROM "+
									bibE2.cTO()+".JT_VPOS_TPA_FUHRE, "+
									bibE2.cTO()+".JT_VPOS_TPA, "+
									bibE2.cTO()+".JT_EAK_CODE WHERE " +
											"JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA AND " +
											"JT_VPOS_TPA_FUHRE.ID_EAK_CODE = JT_EAK_CODE.ID_EAK_CODE (+)  " +
											"AND " +
												" (   NVL(JT_EAK_CODE.GEFAEHRLICH,'N') ='Y' OR " +
												"   UPPER(SUBSTR(  NVL(JT_VPOS_TPA_FUHRE.EUCODE,'-'),1,1))='A' OR  " +
												"   UPPER(SUBSTR(  NVL(JT_VPOS_TPA_FUHRE.BASEL_CODE,'-'),1,1))='A') " +
												" AND JT_VPOS_TPA.ID_VKOPF_TPA="+cID_VKOPF;
		
		
		boolean bWarnung = (!bibDB.EinzelAbfrage(cQueryGefahr).equals("0"));
		
		if (bWarnung)
		{
			MyE2_Column ocolHelp = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
			ocolHelp.setBackground(Color.WHITE);
			MyE2_Label oLabWarnung = new MyE2_Label(E2_ResourceIcon.get_RI("warnschild_16.png"));
			oLabWarnung.setToolTipText(new MyE2_String("Transportauftrag enthält gefährliche/begleitscheinpflichtige Sorte").CTrans());
			ocolHelp.add(oLabWarnung, E2_INSETS.I_1_1_1_1);
			oRow.add(ocolHelp, E2_INSETS.I_5_0_0_0);
		}
	
		
		
		
		
		
		//NEU_09  markierung, wenn stornierte fuhre
		String cQueryStorno = "SELECT COUNT(JT_VPOS_TPA.ID_VPOS_TPA) FROM "+
									bibE2.cTO()+".JT_VPOS_TPA_FUHRE, "+
									bibE2.cTO()+".JT_VPOS_TPA  WHERE " +
											" JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA AND " +
											" NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='Y' AND " +
											" JT_VPOS_TPA.ID_VKOPF_TPA="+cID_VKOPF+" AND POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"'";

		String cStornoZahl = bibDB.EinzelAbfrage(cQueryStorno);
		boolean bStorno = !cStornoZahl.equals("0");
		
		if (bStorno)
		{
			MyE2_Column ocolHelp = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
			MyE2_Label oLabStorno = null;
			if (cStornoZahl.equals(cWert1))       //alle storniert
			{
				oLabStorno = new MyE2_Label(E2_ResourceIcon.get_RI("storno_all.png"));
			}
			else
			{
				oLabStorno = new MyE2_Label(E2_ResourceIcon.get_RI("storno_part.png"));
			}
			oLabStorno.setToolTipText(new MyE2_String("Transportauftrag enthält stornierte Transport-Position(en)").CTrans());
			ocolHelp.add(oLabStorno, E2_INSETS.I_1_1_1_1);
			oRow.add(ocolHelp, E2_INSETS.I_5_0_0_0);
		}
		//NEU_09
		
		
		
		
		
		
		//NEU10
		// ladestatus der fuhren im tpa pruefen und anzeigen
		MyE2_Row oRowLS = (MyE2_Row)oMAP.get__Comp(BST__CONST.HASH_KEY_ANZEIGE_LADESTAUS);
		oRowLS.removeAll();

		String cQueryLadestatus = "SELECT 	  NVL(JT_VPOS_TPA_FUHRE.MENGE_VORGABE_KO,0)," +
										"	  NVL(JT_VPOS_TPA_FUHRE.MENGE_AUFLADEN_KO,0)," +
										"	  NVL(JT_VPOS_TPA_FUHRE.MENGE_ABLADEN_KO,0) FROM "+
									bibE2.cTO()+".JT_VPOS_TPA_FUHRE, "+
									bibE2.cTO()+".JT_VPOS_TPA  WHERE " +
											"JT_VPOS_TPA.ID_VPOS_TPA = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA AND " +
											" NOT ("+
											"   NVL(JT_VPOS_TPA.ANZAHL,-1)=0 AND "+
											"   NVL(JT_VPOS_TPA.EINZELPREIS,-1)=0 AND "+
											"   NVL(JT_VPOS_TPA.GESAMTPREIS,-1)=0 AND "+
											"   NVL(JT_VPOS_TPA_FUHRE.MENGE_VORGABE_KO,-1)=0 AND "+
											"   NVL(JT_VPOS_TPA_FUHRE.MENGE_AUFLADEN_KO,-1)=0 AND "+
											"   NVL(JT_VPOS_TPA_FUHRE.MENGE_ABLADEN_KO,-1)=0)  AND " +
											" JT_VPOS_TPA.ID_VKOPF_TPA="+cID_VKOPF+" AND POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"'";

		String[][] cLadestatus = bibDB.EinzelAbfrageInArray(cQueryLadestatus);
		if (cLadestatus != null && cLadestatus.length>0)
		{
			int iCountGeladen = 0;
			for (int i=0;i<cLadestatus.length;i++)
			{
				if (!cLadestatus[i][1].equals("0"))
					iCountGeladen++;
			}

			if (iCountGeladen==cLadestatus.length)
			{
				//voll geladen
				MyE2_Label oLabHelp = new MyE2_Label(E2_ResourceIcon.get_RI("ladestatus_voll.png"));
				oLabHelp.setToolTipText(new MyE2_String("Status: ALLE Transport-Positionen haben Lademengen").CTrans());
				oRowLS.add(oLabHelp, E2_INSETS.I_1_1_1_1);
			}
			else if (iCountGeladen==0)
			{
				//leer
				MyE2_Label oLabHelp = new MyE2_Label(E2_ResourceIcon.get_RI("ladestatus_leer.png"));
				oLabHelp.setToolTipText(new MyE2_String("Status: KEINE Transport-Position hat Lademenge").CTrans());
				oRowLS.add(oLabHelp, E2_INSETS.I_1_1_1_1);
			}
			else
			{
				//teil geladen
				MyE2_Label oLabHelp = new MyE2_Label(E2_ResourceIcon.get_RI("ladestatus_halb.png"));
				oLabHelp.setToolTipText(new MyE2_String("Status: EINIGE Transport-Positionen haben Lademengen").CTrans());
				oRowLS.add(oLabHelp, E2_INSETS.I_1_1_1_1);
			}
			
			
		}
		
		
	
		
		
		//NEU10

		
		
	}

}
