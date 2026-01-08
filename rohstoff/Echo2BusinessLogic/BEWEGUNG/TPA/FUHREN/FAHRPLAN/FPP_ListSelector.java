package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.bibROHSTOFF;


public class FPP_ListSelector extends MyE2_Grid
{

	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public FPP_ListSelector(E2_NavigationList oNavigationList) throws myException
	{
		super();
		this.setSize(7);
		
		this.oSelVector = 				new E2_SelectionComponentsVector(oNavigationList);
		MyE2_CheckBox 					oCheckBoxNichtInFahrplan = 			new MyE2_CheckBox(new MyE2_String("Noch nicht in Fahrplan"));
		MyE2_CheckBox 					oCheckBoxFahrVonPlanerErzeugt = 	new MyE2_CheckBox(new MyE2_String("Fahrt wurde von Planer erzeugt"));
		MyE2_CheckBox 					oCheckBoxContainerIstAbsetzer = 	new MyE2_CheckBox(new MyE2_String("Absetzcontainer"));
		MyE2_CheckBox 					oCheckBoxContainerIstAbroller = 	new MyE2_CheckBox(new MyE2_String("Abrollcontainer"));
		MyE2_CheckBox  					oCB_ShowDeletedRows = 				new MyE2_CheckBox(new MyE2_String("Zeige gelöschte Sätze"));
		
		
		E2_SelektorDateFromTo 			oSelBereichDatumFahrplan = 			new E2_SelektorDateFromTo(new MyE2_String(""),"JT_VPOS_TPA_FUHRE.DAT_FAHRPLAN_FP",null);
		E2_SelektorDateFromTo 			oSelBereichDatumPlanung = 			new E2_SelektorDateFromTo(new MyE2_String(""),"JT_VPOS_TPA_FUHRE.DAT_VORGEMERKT_FP","JT_VPOS_TPA_FUHRE.DAT_VORGEMERKT_ENDE_FP");

		MyE2_CheckBox 					oCheckBoxWareneingangOhneFuhre = 	new MyE2_CheckBox(new MyE2_String("Wareneingang"));
		MyE2_CheckBox 					oCheckBoxWarenausgangOhneFuhre = 	new MyE2_CheckBox(new MyE2_String("Warenausgang"));
		
		ActionAgent_RadioFunction_CheckBoxList oRB = new ActionAgent_RadioFunction_CheckBoxList(true);
		oRB.add_CheckBox(oCheckBoxWareneingangOhneFuhre);
		oRB.add_CheckBox(oCheckBoxWarenausgangOhneFuhre);

		//		oRB.add_CheckBox(oCheckBoxNichtInFahrplan);
		
		
		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxNichtInFahrplan,"(JT_VPOS_TPA_FUHRE.DAT_FAHRPLAN_FP IS  NULL OR JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP IS NULL)",""));
		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxFahrVonPlanerErzeugt,"(JT_VPOS_TPA_FUHRE.IST_GEPLANT_FP = 'Y')",""));
		
		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxContainerIstAbsetzer,"(JT_VPOS_TPA_FUHRE.ID_CONTAINERTYP_FP IS NULL OR JT_VPOS_TPA_FUHRE.ID_CONTAINERTYP_FP IN (SELECT ID_CONTAINERTYP FROM "+bibE2.cTO()+".JT_CONTAINERTYP WHERE ABSETZ='Y'))",""));
		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxContainerIstAbroller,"(JT_VPOS_TPA_FUHRE.ID_CONTAINERTYP_FP IS NULL OR JT_VPOS_TPA_FUHRE.ID_CONTAINERTYP_FP IN (SELECT ID_CONTAINERTYP FROM "+bibE2.cTO()+".JT_CONTAINERTYP WHERE ABROLL='Y'))",""));
		
		oSelVector.add(oSelBereichDatumFahrplan);
		oSelVector.add(oSelBereichDatumPlanung);

		oSelVector.add(new E2_ListSelectorStandard(oCB_ShowDeletedRows,"","  NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'"));

		
		// selector fuer wareneingang ohne fuhre
		Vector<String> vEigeneLager = new Vector<String>();
		vEigeneLager.addAll(bibROHSTOFF.get_vEigeneLieferadressen());
		String cHelp = "("+bibALL.Concatenate(vEigeneLager,",","-1")+")";
		String cBedingWE = "(JT_VPOS_TPA_FUHRE.ID_ADRESSE_START NOT IN "+cHelp+
							" AND JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL IN "+cHelp+
							" AND   NVL(JT_VPOS_TPA_FUHRE.KENNER_ALTE_SAETZE_FP,'N') <> 'Y' )";
		String cBedingWA = "(JT_VPOS_TPA_FUHRE.ID_ADRESSE_ZIEL NOT IN "+cHelp+
							" AND JT_VPOS_TPA_FUHRE.ID_ADRESSE_START IN "+cHelp+
							" AND   NVL(JT_VPOS_TPA_FUHRE.KENNER_ALTE_SAETZE_FP,'N') <> 'Y' )";
		
		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxWareneingangOhneFuhre,cBedingWE,""));
		oSelVector.add(new E2_ListSelectorStandard(oCheckBoxWarenausgangOhneFuhre,cBedingWA,""));

		
		/*
		 * vordefiniert= nicht eingeplant
		 */
		oCheckBoxNichtInFahrplan.setSelected(true);

		// zeile 1
		// zeile 1
		// info dazu: selektion
		this.add(new MyE2_Label(new MyE2_String("Selektion:"),MyE2_Label.STYLE_SMALL_ITALIC()),E2_INSETS.I_2_2_10_2);
		this.add(new MyE2_Label(new MyE2_String("Fahrplan-Datum: ")));
		this.add(oSelBereichDatumFahrplan.get_oComponentForSelection());
		this.add(oCheckBoxNichtInFahrplan);
		this.add(oCheckBoxContainerIstAbroller);
		this.add(oCheckBoxWareneingangOhneFuhre);
		this.add(oCheckBoxWarenausgangOhneFuhre);
		
		
		// zeile 2
		this.add(new MyE2_Label(new MyE2_String(" ")));
		this.add(new MyE2_Label(new MyE2_String("Vormerk-Datum: ")));
		this.add(oSelBereichDatumPlanung.get_oComponentForSelection());
		this.add(oCheckBoxFahrVonPlanerErzeugt);
		this.add(oCheckBoxContainerIstAbsetzer);
		this.add(oCB_ShowDeletedRows);
	}

	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
