package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__CONST;

public class BSFP_LIST_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		
		boolean bStrecke = S.NN(oUsedResultMAP.get_UnFormatedValue("STRECKENGESCHAEFT")).equals("Y");

//		((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG)).setIcon(BSRG__CONST.ICON_LEER);

		MyE2_Grid_InLIST oGridInfo = (MyE2_Grid_InLIST)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG);
		oGridInfo.removeAll();
		oGridInfo.setSize(1);   //vorgabe
		
		MyE2_Label  oLabelFuerGrid = new MyE2_Label(BSRG__CONST.ICON_LEER);
		
		/*
		 * den anzeiger fuer zugang/abgang einblenden
		 * Ursprungstyp des belegs einblenden
		 */
		if (oUsedResultMAP.get_LActualDBValue("ID_VKOPF_RG", true).longValue()!=0)
		{
			RECORD_VKOPF_RG recVKOPF = new RECORD_VKOPF_RG(oUsedResultMAP.get_LActualDBValue("ID_VKOPF_RG",true));
			
			BSFP_LIST_Button_Jump_ToRechGut  oJumpButton = new BSFP_LIST_Button_Jump_ToRechGut(bibALL.get_Vector(recVKOPF.get_ID_VKOPF_RG_cUF()), recVKOPF.get_VORGANG_TYP_cUF());

			if 		(	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("1") && !bStrecke)
			{
				oLabelFuerGrid.setIcon(BSRG__CONST.ICON_ZUGANG_MK);
				oLabelFuerGrid.setToolTipText(new MyE2_String("Lagerzugangsbuchung (GUTSCHRIFT) -- bereits einem Gutschrift-Beleg zugeordnet").CTrans());
			}
			else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("-1") && !bStrecke)
			{
				oLabelFuerGrid.setIcon(BSRG__CONST.ICON_ABGANG_MK);
				oLabelFuerGrid.setToolTipText(new MyE2_String("Lagerabgangsbuchung (RECHNUNG) -- bereits einem Rechnungs-Beleg zugeordnet").CTrans());
			}
			else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("1") && bStrecke)
			{
				oLabelFuerGrid.setIcon(BSRG__CONST.ICON_STRECKE_GUTSCHRIFT_MK);
				oLabelFuerGrid.setToolTipText(new MyE2_String("Streckengeschäft (GUTSCHRIFT) -- bereits einem Gutschrift-Beleg zugeordnet").CTrans());
			}
			else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("-1") && bStrecke)
			{
				oLabelFuerGrid.setIcon(BSRG__CONST.ICON_STRECKE_RECHNUNG_MK);
				oLabelFuerGrid.setToolTipText(new MyE2_String("Streckengeschäft (RECHNUNG) -- bereits einem Rechnungs-Beleg zugeordnet").CTrans());
			}
			oGridInfo.setSize(2);
			oGridInfo.add(oLabelFuerGrid);
			oGridInfo.add(oJumpButton);
		}
		else
		{
			
			if 		(	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("1") && !bStrecke)
			{
				oLabelFuerGrid.setIcon(BSRG__CONST.ICON_ZUGANG);
				oLabelFuerGrid.setToolTipText(new MyE2_String("Lagerzugangsbuchung (GUTSCHRIFT)").CTrans());
			}
			else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("-1") && !bStrecke)
			{
				oLabelFuerGrid.setIcon(BSRG__CONST.ICON_ABGANG);
				oLabelFuerGrid.setToolTipText(new MyE2_String("Lagerabgangsbuchung (RECHNUNG)").CTrans());
			}
			else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("1") && bStrecke)
			{
				oLabelFuerGrid.setIcon(BSRG__CONST.ICON_STRECKE_GUTSCHRIFT);
				oLabelFuerGrid.setToolTipText(new MyE2_String("Streckengeschäft (GUTSCHRIFT)").CTrans());
			}
			else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("-1") && bStrecke)
			{
				oLabelFuerGrid.setIcon(BSRG__CONST.ICON_STRECKE_RECHNUNG);
				oLabelFuerGrid.setToolTipText(new MyE2_String("Streckengeschäft (RECHNUNG)").CTrans());
			}
			oGridInfo.add(oLabelFuerGrid);
		}
		
		
		((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_IST_STORNO_POS)).setIcon(BSRG__CONST.ICON_LEERKLEIN);
		if (oUsedResultMAP.get_LActualDBValue("ID_VPOS_RG_STORNO_VORGAENGER", true).longValue()!=0)
		{
			((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_IST_STORNO_POS)).setIcon(E2_ResourceIcon.get_RI("storno_gegenbeleg.png"));
			((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_IST_STORNO_POS)).setToolTipText(new MyE2_String("Position ist eine Gegenposition zu einer stornierten Position").CTrans());
		}
		else if (oUsedResultMAP.get_LActualDBValue("ID_VPOS_RG_STORNO_NACHFOLGER", true).longValue()!=0)
		{
			((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_IST_STORNO_POS)).setIcon(E2_ResourceIcon.get_RI("storno_beleg.png"));
			((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_IST_STORNO_POS)).setToolTipText(new MyE2_String("Position ist eine stornierte Position").CTrans());
		}

		
		
		// jetzt die info-spalte fuellen. angezeigt wird, ob aktiv oder nicht und
		// auch, ob die position abzuege hat 
		boolean bDeleted = 	oUsedResultMAP.get_FormatedValue("DELETED").equals("Y");
		boolean bAbzuege =  !oUsedResultMAP.get_UnFormatedValue("EINZELPREIS").equals(oUsedResultMAP.get_UnFormatedValue("EINZELPREIS_RESULT"));
		
		
		((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_HAT_ABZUEGE)).setIcon(BSRG__CONST.ICON_LEERKLEIN);
		if (bAbzuege)
		{
			((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_HAT_ABZUEGE)).setIcon(E2_ResourceIcon.get_RI("abzuege.png"));
			((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_HAT_ABZUEGE)).setToolTipText(new MyE2_String("Position hat Abzüge").CTrans());
		}
		
		
		if (bDeleted)
		{
			oMAP.set_AllComponentsAsDeleted();
		}
	
		GridLayoutData oGLRED = new GridLayoutData();
		oGLRED.setAlignment(new Alignment(Alignment.RIGHT,Alignment.DEFAULT));
		oGLRED.setBackground(new E2_ColorAlarm());
		
		if (!bDeleted)
		{
			((BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM)oMAP.get__Comp("EINZELPREIS")).setBackground(null);
			((BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM)oMAP.get__Comp("STEUERSATZ")).setBackground(null);
			((BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM)oMAP.get__Comp("AUSFUEHRUNGSDATUM")).setBackground(null);
			
			
			if (bibALL.isEmpty(oUsedResultMAP.get_UnFormatedValue("EINZELPREIS")))
			{
				((BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM)oMAP.get__Comp("EINZELPREIS")).setBackground(new E2_ColorAlarm());
			}
				
			if (bibALL.isEmpty(oUsedResultMAP.get_UnFormatedValue("STEUERSATZ")))
			{
				((BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM)oMAP.get__Comp("STEUERSATZ")).setBackground(new E2_ColorAlarm());
			}
			
			if (bibALL.isEmpty(oUsedResultMAP.get_UnFormatedValue("AUSFUEHRUNGSDATUM")))
			{
				((BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM)oMAP.get__Comp("AUSFUEHRUNGSDATUM")).setBackground(new E2_ColorAlarm());
			}

		}
		
		
		//2011-02-18: jump in fuhre
		MyE2_Grid_InLIST oGrid = (MyE2_Grid_InLIST)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_JUMP_TO_FUHRE);
		oGrid.removeAll();
		if (oUsedResultMAP.get_LActualDBValue("ID_VPOS_TPA_FUHRE_ZUGEORD", true).longValue()!=0)
		{
			oGrid.add(new BSFP_LIST_Button_Jump_To_Fuhre(bibALL.get_Vector(""+oUsedResultMAP.get_LActualDBValue("ID_VPOS_TPA_FUHRE_ZUGEORD", true).longValue())),E2_INSETS.I_0_0_0_0);
		}
		
	}

}
