package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_AbzugsKalkulator;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_Daughter_Abzuege;

public class __FU_FUO_ABZUGSLISTE extends 	BL_Daughter_Abzuege
{
	
	private E2_ComponentMAP    oMAP_from_Mother = null;
	private String             cNAME_OF_DBFIELD_ABZUG = null;
	private String             cNAME_OF_DBFIELD_RESULT_EPREIS = null;
	private String             cNAME_OF_DBFIELD_MENGE = null;
	private String             cNAME_OF_DBFIELD_PREIS = null;
	
	
	public __FU_FUO_ABZUGSLISTE( 	SQLFieldMAP 			oFM, 
									String 					cConnectFieldToMotherTable,
									String 					cTablename, 
									E2_ComponentMAP 		ocomponentMAP_from_Mother,
									String             		NAME_OF_DBFIELD_MENGE,
									String             		NAME_OF_DBFIELD_PREIS,				
									String          		NAME_OF_DBFIELD_ABZUG,
									String             		NAME_OF_DBFIELD_RESULT_EPREIS)	throws myException
	{
		super(	oFM.get_oSQLFieldPKMainTable(), oFM, cConnectFieldToMotherTable, cTablename, ocomponentMAP_from_Mother,null);
		this.oMAP_from_Mother = 				ocomponentMAP_from_Mother;
		this.cNAME_OF_DBFIELD_MENGE = 			NAME_OF_DBFIELD_MENGE;
		this.cNAME_OF_DBFIELD_PREIS = 			NAME_OF_DBFIELD_PREIS;
		this.cNAME_OF_DBFIELD_ABZUG = 			NAME_OF_DBFIELD_ABZUG;
		this.cNAME_OF_DBFIELD_RESULT_EPREIS = 	NAME_OF_DBFIELD_RESULT_EPREIS;
		
		this.fuege_titelblock_ein();            //anzeige des titelblocks
	}


	
	@Override
	public Component get__TitleComponentWithButtons() throws myException
	{
		
		Vector<MyString>   	vTexte = 		new Vector<MyString>();
		Vector<Component> 	vComponents = 	new Vector<Component>();
		
		vTexte.add(new MyE2_String("Neuer Abzug"));
		vTexte.add(new MyE2_String("Neu rechnen"));
		vTexte.add(new MyE2_String("Mengenabzüge (reduzieren Lagermenge)"));
		vTexte.add(new MyE2_String("result.E-Preis"));
		
		vComponents.add(this.get_oButNew());
		vComponents.add(this.get_oButRefresh());
		vComponents.add(this.oMAP_from_Mother.get_Comp(cNAME_OF_DBFIELD_ABZUG));
		vComponents.add(this.oMAP_from_Mother.get_Comp(cNAME_OF_DBFIELD_RESULT_EPREIS));
		

		GridLayoutData  glCenterTitle = 	MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_5_2_5_2, new E2_ColorDark(), 1);
		GridLayoutData  glRightTitle = 		MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_5_2_5_2, new E2_ColorDark(), 1);
		GridLayoutData  glCenterContent = 	MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_5_2_5_2, new E2_ColorBase(), 1);
		GridLayoutData  glRightContent = 	MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_5_2_5_2, new E2_ColorBase(), 1);
		
		Vector<GridLayoutData> vTitle = new Vector<GridLayoutData>();
		Vector<GridLayoutData> vContent = new Vector<GridLayoutData>();
		vTitle.add(glCenterTitle);		vTitle.add(glCenterTitle);		vTitle.add(glRightTitle);		vTitle.add(glRightTitle);
		vContent.add(glCenterContent);	vContent.add(glCenterContent);	vContent.add(glRightContent);	vContent.add(glRightContent);
		
		E2_Subgrid_4_Mask oSubgrid = new E2_Subgrid_4_Mask(	vTexte, 
															vComponents,
															vTitle,  
															vContent, 
															true,
															new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID),
															true);
		
		return oSubgrid;

	}

	
	@Override
	public RECORD_WAEHRUNG get__RECORD_FREMDWAEHRUNG() throws myException
	{
		RECORD_WAEHRUNG  recWaehrung = null;
		long lFremdWaehrung = bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_lValue(new Long(0));
		if (lFremdWaehrung>0) {	recWaehrung = new RECORD_WAEHRUNG(lFremdWaehrung);}
		return recWaehrung;
	}

	
	@Override
	public RECORD_ARTIKEL get__RECORD_ARTIKEL() throws myException
	{
		RECORD_ARTIKEL  recArtikel = null;
		long lArtikel = this.oMAP_from_Mother.get_LActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__ID_ARTIKEL, new Long(-1),  new Long(-1));
		
		if (lArtikel>0)	{ recArtikel = new RECORD_ARTIKEL(lArtikel);}
		return recArtikel;
	}

	 
	@Override
	public String get__ActualFormatedValueMenge() throws myException
	{
		String cRueck = S.NN(this.oMAP_from_Mother.get_cActualDBValueFormated(this.cNAME_OF_DBFIELD_MENGE));
		if (S.isEmpty(cRueck))
		{
			//2011-10-10: abzuege auch bei leeren mengen
			//wenn das feld leer ist, dann 0 zurueck als wert
			return "0";
		}
		if (!bibALL.isNumber(cRueck, true))
		{
			return "";
		}
		return cRueck;
	}

	@Override
	public String get__ActualFormatedValuePreis() throws myException
	{
		String cRueck = S.NN(this.oMAP_from_Mother.get_cActualDBValueFormated(this.cNAME_OF_DBFIELD_PREIS));
		
		if (S.isEmpty(cRueck))
		{
			//2011-08-03: wird in der Fuhre ein abzug berechnet, bevor ein preis feststeht, dann
			// wird dieser nicht mehr abgewiesen, sondern als 0 behandelt
			//return "";
			return "0";
		}
		if (!bibALL.isNumber(cRueck, true))
		{
			return "";
		}
		return cRueck;
	}

	@Override
	public String get__ActualFormatedValuePreis_FW() throws myException
	{
		return this.get__ActualFormatedValuePreis();
	}

	@Override
	public String get__ActualFormatedWaehrungsKurs() throws myException
	{
		return "1";
	}

	@Override
	public void fill__MaskWithCalcResults(BL_Daughter_Abzuege DaughterAbzuege,BL_AbzugsKalkulator oBL_Kalk) throws myException
	{
		//2011-10-11: abzugsmenge 3-stellig anzeigen
		this.oMAP_from_Mother.get__DBComp(this.cNAME_OF_DBFIELD_ABZUG).set_cActualMaskValue(MyNumberFormater.formatDez(oBL_Kalk.get_bdGESAMTER_MENGENABZUG_Lager(),3,true));
		this.oMAP_from_Mother.get__DBComp(this.cNAME_OF_DBFIELD_RESULT_EPREIS).set_cActualMaskValue(MyNumberFormater.formatDez(oBL_Kalk.get_bdRESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE(),2,true));
	}

	@Override
	public void fill__MaskWithCalcResults_ON_EMPTY_LIST(BL_Daughter_Abzuege DaughterAbzuege) throws myException
	{
		this.oMAP_from_Mother.get__DBComp(this.cNAME_OF_DBFIELD_ABZUG).set_cActualMaskValue("0");
		this.oMAP_from_Mother.get__DBComp(this.cNAME_OF_DBFIELD_RESULT_EPREIS).set_cActualMaskValue(this.oMAP_from_Mother.get_cActualDBValueFormated(this.cNAME_OF_DBFIELD_PREIS));
	}



	@Override
	public boolean get_bSperreFremdWaehrung() throws myException
	{
		return true;    //in der fuhre duerfen nur eigenwaehrungsabzuege verwendet werden
	}



	/**
	 * 2016-12-20: bei der fuhre muss die kalkulation auf das gueltige abrechnungsgewicht umgestellt werden
	 * bei der fuhre muss auf der lagerseite so gerechnet werden, wie vordefiniert (WE ablademenge_abn, WA lademenge_lief),
	 * bei fremden lagern das gewicht der abrechnungsseite
	 * 
	 * @param cNAME_OF_DBFIELD_MENGE
	 */
	public void set_NAME_OF_DBFIELD_MENGE(IF_Field field) {
		this.cNAME_OF_DBFIELD_MENGE = field.fieldName();
	}

}
