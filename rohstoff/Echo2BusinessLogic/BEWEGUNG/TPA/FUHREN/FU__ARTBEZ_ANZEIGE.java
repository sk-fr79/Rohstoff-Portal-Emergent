package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic._TAX.PRUEFKLASSEN.Check_RC_Status_Sorte;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EAK_CODE_ext;

public class FU__ARTBEZ_ANZEIGE extends MyE2_Grid_InLIST 
{
	
	private MyE2_Grid   oGrid_4_Border = null;
	private MyE2_Grid   oGrid_Innen = null;
	
	
	public FU__ARTBEZ_ANZEIGE(int iWidth) 
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.setWidth(new Extent(iWidth));
		
		MutableStyle  oStyle_Grid_4_Border = new MutableStyle();
		oStyle_Grid_4_Border.setProperty(Grid.PROPERTY_BORDER, new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle_Grid_4_Border.setProperty(Grid.PROPERTY_INSETS, E2_INSETS.I_1_1_1_1);

		this.oGrid_4_Border = new MyE2_Grid(1, oStyle_Grid_4_Border);
		this.oGrid_Innen = new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oGrid_4_Border.add(this.oGrid_Innen);
		
		//2011-11-22: rahmen mit fester breite
		this.oGrid_4_Border.setColumnWidth(0, new Extent(460));

		this.add(this.oGrid_4_Border,E2_INSETS.I_0_0_0_0);
	}
	
	
	
	/**
	 * 
	 * anzeige auf direkte aenderung der ID im suchfeld (wird benutzt in FU__Mask_ZEIGE_GEFAHRGUT_UND_SORTENBEZ_Label)
	 *
	 * @param cID_ARTBEZ
	 * @param cID_ADRESSE_LAGER
	 * @param cEK_VK
	 * @throws myException
	 */
	public void baue_anzeige(String cID_ARTBEZ, String cID_ADRESSE_LAGER, String cEK_VK) throws myException
	{
		this.oGrid_Innen.removeAll();
		
		this.baue_anzeige(null,null,null,true);
		
		if (S.isFull(cID_ARTBEZ))
		{
			RECORD_ARTIKEL_BEZ  recArtBez = new RECORD_ARTIKEL_BEZ(cID_ARTBEZ);
			
			String cANR1_ANR2 = recArtBez.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("<ANR1>")+"-"+recArtBez.get_ANR2_cUF_NN("<ANR2>");
			String cARTBEZ    = recArtBez.get_ARTBEZ1_cUF_NN("<Artbez1>")+  (recArtBez.get_ARTBEZ2_cUF_NN("").equals("")?"":" // "+recArtBez.get_ARTBEZ2_cUF_NN(""));
			
			String cRC = this.get_RC_String(cID_ARTBEZ, S.NN(cID_ADRESSE_LAGER));
			
//			if (recArtBez.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer()!=null)
//			{
//				cRC= recArtBez.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer().is_REVERSE_CHARGE_YES()?"RC":"";
//			}

			this.baue_anzeige(cANR1_ANR2, cARTBEZ, cRC, cEK_VK.equals("EK"));
			
		}
	}
	

	/*
	 * wird beim laden der maske benutzt, damit die werte aus der fuhre (evtl. veraendert im vergleich zu der ARTBEZ1/ARTBEZ2 - Basis) in der anzeige stehen
	 */
	public void baue_anzeige(RECORD_VPOS_TPA_FUHRE recFuhre, String cEK_VK) throws myException
	{
		this.baue_anzeige(null,null,null,true);
		
		if (recFuhre!=null)
		{
			
			this.oGrid_4_Border.setVisible(false);
			
			String cRC = 		"";
			String cANR1_ANR2 = "";
			String cARTBEZ  = 	"";
			
			if (cEK_VK.equals("EK"))
			{
				cANR1_ANR2 = recFuhre.get_ANR1_EK_cUF_NN("")+"-"+recFuhre.get_ANR2_EK_cUF_NN("");
				cARTBEZ    = recFuhre.get_ARTBEZ1_EK_cUF_NN("")+  (recFuhre.get_ARTBEZ2_EK_cUF_NN("").equals("")?"":" // "+recFuhre.get_ARTBEZ2_EK_cUF_NN(""));
				
				cRC = this.get_RC_String(recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF_NN(""), recFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN(""));
				
//				if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek()!=null)
//				{
//					if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer()!=null)
//					{
//						cRC= recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer().is_REVERSE_CHARGE_YES()?"RC":"";
//					}
//				}
			}
			else
			{
				cANR1_ANR2 = recFuhre.get_ANR1_VK_cUF_NN("")+"-"+recFuhre.get_ANR2_VK_cUF_NN("");
				cARTBEZ    = recFuhre.get_ARTBEZ1_VK_cUF_NN("")+  (recFuhre.get_ARTBEZ2_VK_cUF_NN("").equals("")?"":" // "+recFuhre.get_ARTBEZ2_VK_cUF_NN(""));
					
				cRC = this.get_RC_String(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF_NN(""), recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF_NN(""));
				
//				if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk()!=null)
//				{
//					if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer()!=null)
//					{
//						cRC= recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer().is_REVERSE_CHARGE_YES()?"RC":"";
//					}
//				}
			}
			
			this.baue_anzeige(cANR1_ANR2, cARTBEZ, cRC, cEK_VK.equals("EK"));
				
		}
		
	}

	
	/*
	 * diese version baut die anzeige beim wechsel der tabs auf; holt die werte aus der map
	 */
	public void baue_anzeige(boolean bEK_VK)  throws myException
	{
		E2_ComponentMAP  oMap = this.EXT().get_oComponentMAP();
		
		String cANR1="";
		String cANR2="";
		String cARTBEZ1="";
		String cARTBEZ2="";
		String cRC = "";
	
		try 
		{
		if (bEK_VK)
		{
			cANR1 = 	oMap.get_cActualDBValueFormated("ANR1_EK", "");
			cANR2 = 	oMap.get_cActualDBValueFormated("ANR2_EK", "");
			cARTBEZ1 = 	oMap.get_cActualDBValueFormated("ARTBEZ1_EK", "");
			cARTBEZ2 = 	oMap.get_cActualDBValueFormated("ARTBEZ2_EK", "");
			String cID_ARTBEZ1_EK = bibALL.ReplaceTeilString(oMap.get_cActualDBValueFormated("ID_ARTIKEL_BEZ_EK",""),".","");
			
			String cID_ADRESSE_LAGER_START = "";
			long iADRESSE_LAGER_START = oMap.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_START, -1l, -1l).longValue();
			if (iADRESSE_LAGER_START>0) {
				cID_ADRESSE_LAGER_START = ""+iADRESSE_LAGER_START;
			}
			cRC = this.get_RC_String(cID_ARTBEZ1_EK,cID_ADRESSE_LAGER_START);
			
			
//			if (S.isFull(cID_ARTBEZ1_EK) && bibALL.isLong(cID_ARTBEZ1_EK))
//			{
//				if (new RECORD_ARTIKEL_BEZ(cID_ARTBEZ1_EK).get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer()!=null)
//				{
//					cRC=new RECORD_ARTIKEL_BEZ(cID_ARTBEZ1_EK).get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer().is_REVERSE_CHARGE_YES()?"RC":"";
//				}
//			}
		}
		else
		{
			cANR1 = 	oMap.get_cActualDBValueFormated("ANR1_VK", "");
			cANR2 = 	oMap.get_cActualDBValueFormated("ANR2_VK", "");
			cARTBEZ1 = 	oMap.get_cActualDBValueFormated("ARTBEZ1_VK", "");
			cARTBEZ2 = 	oMap.get_cActualDBValueFormated("ARTBEZ2_VK", "");
			String cID_ARTBEZ1_VK = bibALL.ReplaceTeilString(oMap.get_cActualDBValueFormated("ID_ARTIKEL_BEZ_VK",""),".","");
			
			String cID_ADRESSE_LAGER_ZIEL = "";
			long iADRESSE_LAGER_ZIEL = oMap.get_LActualDBValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL, -1l, -1l).longValue();
			if (iADRESSE_LAGER_ZIEL>0) {
				cID_ADRESSE_LAGER_ZIEL = ""+iADRESSE_LAGER_ZIEL;
			}
			cRC = this.get_RC_String(cID_ARTBEZ1_VK,cID_ADRESSE_LAGER_ZIEL);

			
//			if (S.isFull(cID_ARTBEZ1_VK) && bibALL.isLong(cID_ARTBEZ1_VK))
//			{
//				if (new RECORD_ARTIKEL_BEZ(cID_ARTBEZ1_VK).get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer()!=null)
//				{
//					cRC=new RECORD_ARTIKEL_BEZ(cID_ARTBEZ1_VK).get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer().is_REVERSE_CHARGE_YES()?"RC":"";
//				}
//			}
		}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		String cANR1_2 = cANR1+"-"+cANR2;
		String cArtbez12 = 	cARTBEZ1 +  (cARTBEZ2.trim().equals("")?"":" // "+cARTBEZ2);
		
		this.baue_anzeige(cANR1_2, cArtbez12, cRC, bEK_VK);
		
	}
	
	
	private void baue_anzeige(String cANR1_ANR2, String cArtbez12, String cRC, boolean bEK) throws myException
	{
		E2_ComponentMAP  oMap = this.EXT().get_oComponentMAP();
		
		
		Insets  oIn =   new Insets(2,0,8,0);
		Font    oFont = new E2_FontPlain(); 
		
		String ANR1_ANR2 = S.NN(cANR1_ANR2);
		String ARTBEZ12 = S.NN(cArtbez12);
		String RC = S.NN(cRC);
		
		GridLayoutData    oGL_Norm = MyE2_Grid.GRID_LAYOUTDATA(oIn, 1, new Alignment(Alignment.LEFT, Alignment.CENTER));
		GridLayoutData    oGL_RC = MyE2_Grid.GRID_LAYOUTDATA(oIn, 1, new Alignment(Alignment.CENTER, Alignment.CENTER));
			
		if (ANR1_ANR2.trim().length()>1 || ARTBEZ12.trim().length()>1)
		{
			this.oGrid_Innen.removeAll();
			
			this.oGrid_Innen.add(new MyE2_Label(new MyE2_String(ANR1_ANR2,false),oFont,false),oGL_Norm);
			this.oGrid_Innen.add(new MyE2_Label(new MyE2_String(ARTBEZ12,false),oFont,true),oGL_Norm);
			
			if (bEK)
			{
				//dann wird noch der AVV-Code von der Maske geholt
				long lID_EAK_CODE = oMap.get_LActualDBValue("ID_EAK_CODE", true, true, 0l);
				if (lID_EAK_CODE>0)
				{
					RECORD_EAK_CODE_ext  recCode = new RECORD_EAK_CODE_ext(lID_EAK_CODE);
					
					//2019-11-04: weitere ausdifferenzierung fuer gemischt gefaehrliche stoffe
					if (ENUM_MANDANT_DECISION.MARKIERUNG_AVV_IN_FUHRE_ROT_GELB.is_YES()) {
						boolean avvGefahr = recCode.get_GEFAEHRLICH_cF_NN("N").equals("Y");

						// es werden nur vorhandene codes zur pruefung herangezogen
						VEK<Boolean> baselOderOecdFallsVorhanden = new VEK<>();
						if (S.isFull(oMap.get_cActualDBValueFormated(VPOS_TPA_FUHRE.eucode.fn(), ""))) {
							baselOderOecdFallsVorhanden._a(oMap.get_cActualDBValueFormated(VPOS_TPA_FUHRE.eucode.fn(), "").toUpperCase().startsWith("A"));
						}
						if (S.isFull(oMap.get_cActualDBValueFormated(VPOS_TPA_FUHRE.basel_code.fn(), ""))) {
							baselOderOecdFallsVorhanden._a(oMap.get_cActualDBValueFormated(VPOS_TPA_FUHRE.basel_code.fn(), "").toUpperCase().startsWith("A"));
						}

						if (avvGefahr && (baselOderOecdFallsVorhanden.size()==0 || hatGefahr(baselOderOecdFallsVorhanden))) {
							this.oGrid_Innen._add(new RB_lab()._t(recCode.get_AVV_Code_Gesamt()),new RB_gld()._col_back_alarm());
						} else if ( baselOderOecdFallsVorhanden.size()>0 && hatGefahr(baselOderOecdFallsVorhanden) && !avvGefahr) {
							this.oGrid_Innen._add(new RB_lab()._t(recCode.get_AVV_Code_Gesamt()),new RB_gld()._col_back_help());
						} else if (baselOderOecdFallsVorhanden.size()>0 && keineGefahr(baselOderOecdFallsVorhanden) && avvGefahr) {
							this.oGrid_Innen._add(new RB_lab()._t(recCode.get_AVV_Code_Gesamt()),new RB_gld()._col_back_help());
						} else {
							this.oGrid_Innen._add(new RB_lab()._t(recCode.get_AVV_Code_Gesamt()),new RB_gld());
						}
					} else {
						//altes verhalten
						this.oGrid_Innen.add(recCode.get_Label_AVV_Code(oIn));
					}
				}
				else
				{
					this.oGrid_Innen.add(new MyE2_Label(""));
				}
			}
			else
			{
				this.oGrid_Innen.add(new MyE2_Label(""));
			}
			this.oGrid_Innen.add(new MyE2_Label(new MyE2_String(RC,false), new E2_FontBold(2), false),oGL_RC);
			
			this.oGrid_4_Border.setVisible(true);
			
		}
		else
		{
			this.oGrid_Innen.removeAll();
			
			this.oGrid_4_Border.setVisible(false);
		}
	}
	
	
//	private boolean isAllGefahr(VEK<Boolean> v) { 
//		boolean allTrue = true;
//		for (Boolean b: v) {
//			if (b!=null) {
//				allTrue=allTrue&&b;
//			}
//		}
//		return allTrue;
//	}
	
	private boolean hatGefahr(VEK<Boolean> v) { 
		boolean hatGefahr = false;
		for (Boolean b: v) {
			if (b!=null) {
				hatGefahr=hatGefahr || b;
			}
		}
		return hatGefahr;
	}
	
	
	private boolean keineGefahr(VEK<Boolean> v) { 
		boolean keineGefahr = true;
		for (Boolean b: v) {
			if (b!=null) {
				if (b) {
					keineGefahr=false;
				}
			}
		}
		return keineGefahr;
	}
	
	/**
	 * 
	 * @param cID_ARTIKEL_BEZ
	 * @param cID_ADRESSE_LAGER
	 * @return
	 * @throws myException
	 */
	private String get_RC_String(String cID_ARTIKEL_BEZ, String cID_ADRESSE_LAGER) throws myException {
		
		boolean bSteuerViaHandelsdef = 		__RECORD_MANDANT_ZUSATZ.IS__Value("STEUERERMITTLUNG_MIT_HANDELSDEF", "N", "N");

		String cRC = "";
		if (!bSteuerViaHandelsdef) {
			if (S.isFull(cID_ARTIKEL_BEZ) && bibALL.isLong(cID_ARTIKEL_BEZ))
			{
				if (new RECORD_ARTIKEL_BEZ(cID_ARTIKEL_BEZ).get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer()!=null)
				{
					cRC=new RECORD_ARTIKEL_BEZ(cID_ARTIKEL_BEZ).get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_ZOLLTARIFNUMMER_id_zolltarifnummer().is_REVERSE_CHARGE_YES()?"RC":"";
				}
			}
		} else {
			if (S.isFull(cID_ARTIKEL_BEZ) && bibALL.isLong(cID_ARTIKEL_BEZ) && S.isFull(cID_ADRESSE_LAGER) && bibALL.isLong(cID_ADRESSE_LAGER)) {
				
				String cID_LAND = new RECORD_ADRESSE(cID_ADRESSE_LAGER).get_ID_LAND_cUF_NN("");
				String cID_ARTIKEL = new RECORD_ARTIKEL_BEZ(cID_ARTIKEL_BEZ).get_ID_ARTIKEL_cUF();
				if (S.isFull(cID_LAND)) {
					try {
						Check_RC_Status_Sorte oCheckRcSorte = new Check_RC_Status_Sorte(cID_LAND, cID_ARTIKEL);
						
						cRC = oCheckRcSorte.is_RC()?"RC":"";
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return cRC;
	}
	
}
