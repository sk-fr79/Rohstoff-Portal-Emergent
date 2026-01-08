package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EK_VK_BEZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



public class BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt extends XX_List_EXPANDER_4_ComponentMAP
{
	
	private String 				   				 	cEK_VK = null;
	public  E2_ResourceIcon 						oIconLeer = E2_ResourceIcon.get_RI("leer.png");
	
	private BSKC_ModulContainerLIST   				oBSKC_ModulContainerLIST = 	null;
	private RECORD_VPOS_KON 						oActualRECORD_VPOS_KON = 	null; 

	
	public BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt(	BSKC_ModulContainerLIST		KopfModulcontainerList,
																String  					EK_VK) throws myException
	{
		super(KopfModulcontainerList.get_oNavigationList());
		this.set_iLeftColumnOffset(1);
		this.oBSKC_ModulContainerLIST = KopfModulcontainerList;
		this.cEK_VK = EK_VK;
		this.set_iLeftColumnOffset(3);
	}

	public Component get_ComponentDaughter(String cID_ROW_Unformated) throws myException 
	{
		return null;    //hier wird die variante 2 benutzt
	}

	
	@Override
	public ArrayList<HashMap<String, Component>>  get_ComponentListDaughter(String cID_VPOS_KON) throws myException
	{
		this.oActualRECORD_VPOS_KON = new RECORD_VPOS_KON(cID_VPOS_KON);
		
		ArrayList<HashMap<String,Component>> arrayRueck = new ArrayList<HashMap<String,Component>>();
		
		
		//jetzt die anzahl der oben eingeblendeten relevanten spalten:
		RECLIST_EK_VK_BEZUG recListZuordnung = null;
		Vector<ownRECORD_VPOS_KON> oRECLIST_VPOS_KON_GEGEN = new Vector<ownRECORD_VPOS_KON>();
		
		if (this.cEK_VK.equals("EK"))
		{
			recListZuordnung = this.oActualRECORD_VPOS_KON.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek();
			for (int i=0;i<recListZuordnung.get_vKeyValues().size();i++)
			{
				oRECLIST_VPOS_KON_GEGEN.add(new ownRECORD_VPOS_KON(	recListZuordnung.get(i).get_UP_RECORD_VPOS_KON_id_vpos_kon_vk(),
																	recListZuordnung.get(i).get_ANZAHL_bdValue(new BigDecimal(0))));
			}
		}
		else
		{
			recListZuordnung = this.oActualRECORD_VPOS_KON.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk();
			for (int i=0;i<recListZuordnung.get_vKeyValues().size();i++)
			{
	 			oRECLIST_VPOS_KON_GEGEN.add(new ownRECORD_VPOS_KON(	recListZuordnung.get(i).get_UP_RECORD_VPOS_KON_id_vpos_kon_ek(),
																	recListZuordnung.get(i).get_ANZAHL_bdValue(new BigDecimal(0))));
			}
		}

		GridLayoutData glLeft = new GridLayoutData();
		glLeft.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		glLeft.setInsets(E2_INSETS.I_2_2_10_2);
		GridLayoutData glRight = new GridLayoutData();
		glRight.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		glRight.setInsets(E2_INSETS.I_2_2_10_2);

		GridLayoutData glLeftLast = new GridLayoutData();
		glLeftLast.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		glLeftLast.setInsets(E2_INSETS.I_2_2_10_10);
		GridLayoutData glRightLast = new GridLayoutData();
		glRightLast.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		glRightLast.setInsets(E2_INSETS.I_2_2_10_10);


		//ueberschrift-HashMap definieren
		HashMap<String, Component> hmTitel = new HashMap<String, Component>();
		arrayRueck.add(hmTitel);
		
		this.put_mit_Layout(hmTitel, BSKC__CONST.HASH_BUTTON_ZUORDNUNG,			new ownTitleLabel("Z"),glRight);
		this.put_mit_Layout(hmTitel, BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS,	new ownTitleLabel("-"),glRight);
		this.put_mit_Layout(hmTitel, "GUELTIGKEITSZEITRAUM",					new ownTitleLabel("Gültigkeit"),glLeft);
		
		/*20170608: START incoterms einfuegen*/
		this.put_mit_Layout(hmTitel, VPOS_KON.lieferbedingungen.fn(),			new ownTitleLabel("Lf.Bd."),glLeft);
		/*20170608: ENDE incoterms einfuegen*/

		this.put_mit_Layout(hmTitel, "K_NAME1",									new ownTitleLabel(this.cEK_VK.equals("EK")?"Abnehmer":"Lieferant"),glLeft);
		this.put_mit_Layout(hmTitel, "K_ORT",									new ownTitleLabel("Ort"),glLeft);
		this.put_mit_Layout(hmTitel, "ANR1_ANR2",								new ownTitleLabel("Sorte"),glLeft);
		this.put_mit_Layout(hmTitel, "ARTBEZ1",									new ownTitleLabel("Sortenbez."),glLeft);
		this.put_mit_Layout(hmTitel, "ANZAHL",									new ownTitleLabel("Menge"),glRight);
		this.put_mit_Layout(hmTitel, "EINZELPREIS",								new ownTitleLabel("E-Preis"),glRight);
		this.put_mit_Layout(hmTitel, BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE,	new ownTitleLabel("Zuordnung"),glRight);
		this.put_mit_Layout(hmTitel, BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key(),new ownTitleLabel("Zuord.in Fuhre (Plan/Echt)"),glRight);
		this.put_mit_Layout(hmTitel, BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_REALMENGE.key(),new ownTitleLabel("Zuord.in Fuhre (Echt)"),glRight);
		
		
		for (int i=0;i<oRECLIST_VPOS_KON_GEGEN.size();i++)
		{
			HashMap<String, Component> hmZeile = new HashMap<String, Component>();
			arrayRueck.add(hmZeile);
			this.put_mit_Layout(hmZeile, BSKC__CONST.HASH_BUTTON_ZUORDNUNG,new BSKC_BT_ZUORDNUNG(oRECLIST_VPOS_KON_GEGEN.get(i).get_ID_VPOS_KON_cUF(),this),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
//			this.put_mit_Layout(hmZeile, BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS,new BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS(
//											oRECLIST_VPOS_KON_GEGEN.get(i).get_ID_VPOS_KON_cUF(),
//											cID_VPOS_KON,
//											oRECLIST_VPOS_KON_GEGEN.get(i).get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES(),
//											this.get_oNavigationList()
//											),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRight:glRight);

			this.put_ohne_Layout(hmZeile, BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS,new BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS(
											oRECLIST_VPOS_KON_GEGEN.get(i).get_ID_VPOS_KON_cUF(),
											cID_VPOS_KON,
											oRECLIST_VPOS_KON_GEGEN.get(i).get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_ABGESCHLOSSEN_YES(),
											this.get_oNavigationList()
											));

			
			this.put_mit_Layout(hmZeile, "GUELTIGKEITSZEITRAUM",new ownLabel(oRECLIST_VPOS_KON_GEGEN.get(i).get_GueltigZeitraum()),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glLeftLast:glLeft);
			
			/*20170608: START incoterms einfuegen*/
			this.put_mit_Layout(hmZeile, VPOS_KON.lieferbedingungen.fn(),new ownLabel(oRECLIST_VPOS_KON_GEGEN.get(i).get_LIEFERBEDINGUNGEN_cF_NN("-")),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glLeftLast:glLeft);
			/*20170608: ENDE incoterms einfuegen*/

			
			this.put_mit_Layout(hmZeile, "K_NAME1",new ownLabel(oRECLIST_VPOS_KON_GEGEN.get(i).get_Firma()),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glLeftLast:glLeft);
			this.put_mit_Layout(hmZeile, "K_ORT",new ownLabel(oRECLIST_VPOS_KON_GEGEN.get(i).get_Ort()),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glLeftLast:glLeft);
			this.put_mit_Layout(hmZeile, "ANR1_ANR2",new ownLabel(oRECLIST_VPOS_KON_GEGEN.get(i).get___KETTE(bibALL.get_Vector("ANR1", "ANR2"),"","",""," - ")),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glLeftLast:glLeft);
			this.put_mit_Layout(hmZeile, "ARTBEZ1",new ownLabel(oRECLIST_VPOS_KON_GEGEN.get(i).get___KETTE(bibALL.get_Vector("ARTBEZ1"))),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glLeftLast:glLeft);
			this.put_mit_Layout(hmZeile, "ANZAHL",new ownLabel(MyNumberFormater.formatDez(oRECLIST_VPOS_KON_GEGEN.get(i).get_ANZAHL_bdValue(new BigDecimal(0)),0,true)),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
			this.put_mit_Layout(hmZeile, "EINZELPREIS",new ownLabel(MyNumberFormater.formatDez(oRECLIST_VPOS_KON_GEGEN.get(i).get_EINZELPREIS_bdValue(new BigDecimal(0)),2,true)),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
			this.put_mit_Layout(hmZeile, BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE,new ownLabel(MyNumberFormater.formatDez(oRECLIST_VPOS_KON_GEGEN.get(i).get_bdZuordnungsmenge(),0,true)),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
			
//			//welche menge ist in den fuhren schon vorhanden
//			String cQuery = "SELECT " +
//									" CASE WHEN NVL(ABLADEMENGE_RECHNUNG,'N')='Y' THEN  NVL(SUM(NVL(ANTEIL_ABLADEMENGE_ABN,NVL(ANTEIL_PLANMENGE_ABN,0))),0) ELSE NVL(SUM(NVL(ANTEIL_LADEMENGE_ABN,NVL(ANTEIL_PLANMENGE_ABN,0))),0)  END AS MENGE," +
//									" CASE WHEN NVL(ABLADEMENGE_RECHNUNG,'N')='Y' THEN  NVL(SUM(NVL(ANTEIL_ABLADEMENGE_ABN,0)),0)                           ELSE NVL(SUM(NVL(ANTEIL_LADEMENGE_ABN,0)),0)  END   AS MENGE_ECHT " +
//									" FROM "+bibE2.cTO()+".V"+bibALL.get_ID_MANDANT()+"_FUHREN WHERE NVL(IST_STORNIERT,'N')='N' AND  NVL(DELETED,'N')='N' AND ID_VPOS_KON_VK="+
//							this.oActualRECORD_VPOS_KON.get_ID_VPOS_KON_cUF()+" AND ID_VPOS_KON_EK="+oRECLIST_VPOS_KON_GEGEN.get(i).get_ID_VPOS_KON_cUF();
//			
//			if (this.cEK_VK.equals("EK"))
//			{
//				cQuery = "SELECT " +
//									"CASE WHEN NVL(LADEMENGE_GUTSCHRIFT,'N')='Y' THEN  NVL(SUM(NVL(ANTEIL_LADEMENGE_LIEF,NVL(ANTEIL_PLANMENGE_LIEF,0))),0)   ELSE NVL(SUM(NVL(ANTEIL_ABLADEMENGE_LIEF,NVL(ANTEIL_PLANMENGE_LIEF,0))),0) END AS MENGE," +
//									"CASE WHEN NVL(LADEMENGE_GUTSCHRIFT,'N')='Y' THEN  NVL(SUM(NVL(ANTEIL_LADEMENGE_LIEF,0)),0)                              ELSE NVL(SUM(NVL(ANTEIL_ABLADEMENGE_LIEF,0)),0) END  AS MENGE_ECHT " +
//									" FROM "+bibE2.cTO()+".V"+bibALL.get_ID_MANDANT()+"_FUHREN WHERE NVL(IST_STORNIERT,'N')='N' AND  NVL(DELETED,'N')='N' AND ID_VPOS_KON_EK="+
//						this.oActualRECORD_VPOS_KON.get_ID_VPOS_KON_cUF()+" AND ID_VPOS_KON_VK="+oRECLIST_VPOS_KON_GEGEN.get(i).get_ID_VPOS_KON_cUF();
//			}
			
			

			String cQuery = this.baue_query_fuhre(	"ABLADEMENGE_RECHNUNG","ANTEIL_ABLADEMENGE_ABN","ANTEIL_LADEMENGE_ABN","ANTEIL_PLANMENGE_ABN","ABZUG_ABLADEMENGE_ABN",
													"ID_VPOS_KON_VK="+this.oActualRECORD_VPOS_KON.get_ID_VPOS_KON_cUF()+" AND ID_VPOS_KON_EK="+oRECLIST_VPOS_KON_GEGEN.get(i).get_ID_VPOS_KON_cUF());

			if (this.cEK_VK.equals("EK"))
			{
				cQuery = this.baue_query_fuhre(		"LADEMENGE_GUTSCHRIFT","ANTEIL_LADEMENGE_LIEF","ANTEIL_ABLADEMENGE_LIEF","ANTEIL_PLANMENGE_LIEF","ABZUG_LADEMENGE_LIEF",
													"ID_VPOS_KON_EK="+this.oActualRECORD_VPOS_KON.get_ID_VPOS_KON_cUF()+" AND ID_VPOS_KON_VK="+oRECLIST_VPOS_KON_GEGEN.get(i).get_ID_VPOS_KON_cUF());
			}
			

			
			
			
			MyRECORD  recSUM_FuhrenAufKontraktZuordnung = new MyRECORD(cQuery);

			BSK_GridWithNumber oFuhrenZuordnungErfuellungPlan_oder_Echt = new BSK_GridWithNumber(this.oBSKC_ModulContainerLIST.get_oNavigationList(),null, null);
			oFuhrenZuordnungErfuellungPlan_oder_Echt.set_Number(recSUM_FuhrenAufKontraktZuordnung.get("MENGE").getDoubleValue(), 0, recListZuordnung.get(i).get_ANZAHL_dValue(new Double(0)), false);
			
			BSK_GridWithNumber oFuhrenZuordnungErfuellung_Echt = new BSK_GridWithNumber(this.oBSKC_ModulContainerLIST.get_oNavigationList(),null, null);
			oFuhrenZuordnungErfuellung_Echt.set_Number(recSUM_FuhrenAufKontraktZuordnung.get("MENGE_ECHT").getDoubleValue(), 0, recListZuordnung.get(i).get_ANZAHL_dValue(new Double(0)), false);
			
			this.put_mit_Layout(hmZeile, BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key(),oFuhrenZuordnungErfuellungPlan_oder_Echt,i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
			this.put_mit_Layout(hmZeile, BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_REALMENGE.key(),oFuhrenZuordnungErfuellung_Echt,i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
			
			this.put_mit_Layout(hmZeile, "ID_VPOS_KON",	    new BSKC_List_EXPANDER_BT_OpenKontraktPOS(this,oRECLIST_VPOS_KON_GEGEN.get(i))	  ,i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
			this.put_mit_Layout(hmZeile, "K_ID_VKOPF_KON",	new BSKC_List_EXPANDER_BT_OpenKontraktKopf(this,oRECLIST_VPOS_KON_GEGEN.get(i).get_UP_RECORD_VKOPF_KON_id_vkopf_kon()),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
			this.put_mit_Layout(hmZeile, "AD_ID_ADRESSE",	new BSKC_List_EXPANDER_BT_OpenAdresse(this,oRECLIST_VPOS_KON_GEGEN.get(i).get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_cUF()),i==oRECLIST_VPOS_KON_GEGEN.size()-1?glRightLast:glRight);
			
		}
		
		return arrayRueck;
	}
	
	
	
	
	/**
	 * 
	 * @param cBedingungsFeld
	 * @param cRegulaereFeld
	 * @param cGegenFeld
	 * @param cPlanFeld
	 * @param cAbzugsFeld
	 * @param cSQL_WhereBlock
	 * @return
	 */
	private String baue_query_fuhre(	String cBedingungsFeld, String cRegulaereFeld, String cGegenFeld, String cPlanFeld, String cAbzugsFeld, 
										String cSQL_WhereBlock)
	{
		String cRueck = "";
		
		String cTABLE = bibE2.cTO()+".V"+bibALL.get_ID_MANDANT()+"_FUHREN  JT_VPOS_TPA_FUHRE";
		
		cRueck = "SELECT " +
				"NVL(SUM("+
				" CASE "+
			           " WHEN NVL(" +cBedingungsFeld+ ",'N')='Y' "+
			           " THEN NVL(" +cRegulaereFeld+ "," +cPlanFeld+ ")-NVL("+cAbzugsFeld+",0) "+
			           " ELSE NVL(" +cGegenFeld+ ",  " +cPlanFeld+ ")-  NVL("+cAbzugsFeld+",0) "+
			           " END),0)  AS MENGE, " +
	        	"NVL(SUM("+
				" CASE "+
			          " WHEN NVL(" +cBedingungsFeld+ ",'N')='Y' "+
			          " THEN NVL(" +cRegulaereFeld+ ",0)-NVL("+cAbzugsFeld+",0) "+
			          " ELSE NVL(" +cGegenFeld+ "    ,0)-NVL("+cAbzugsFeld+",0) "+
			          " END),0)  AS MENGE_ECHT " +
	        " FROM "+cTABLE+" WHERE NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' " +
	        	" AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' AND "+cSQL_WhereBlock;
	
		return cRueck;
	}
	

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt oCopy = new BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt(this.oBSKC_ModulContainerLIST,this.cEK_VK);
			return oCopy;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
	}


	public void refreshDaughterComponent() throws myException
	{
		this.get_oNavigationList()._REBUILD_ACTUAL_SITE(null);
	}
	

	
	private void put_mit_Layout(HashMap<String, Component> oHM, String cHashKey,Component oComp, LayoutData oLayout)
	{
		oComp.setLayoutData(oLayout);
		oHM.put(cHashKey, oComp);
	}
	
	private void put_ohne_Layout(HashMap<String, Component> oHM, String cHashKey,Component oComp)
	{
		oHM.put(cHashKey, oComp);
	}
	
	
	
	private class ownTitleLabel extends MyE2_Label
	{
		public ownTitleLabel(Object text)
		{
			super(text);
			this.setFont(new E2_FontItalic(-2));
		}
	}


	private class ownLabel extends MyE2_Label
	{
		public ownLabel(Object text)
		{
			super(text);
			this.setFont(new E2_FontPlain(-2));
		}
	}

	
	private class ownRECORD_VPOS_KON extends RECORD_VPOS_KON
	{
		private BigDecimal  bdZuordnungsmenge = null;
		
		public ownRECORD_VPOS_KON(RECORD_VPOS_KON recordOrig, BigDecimal ZuordnungsMenge)
		{
			super(recordOrig);
			this.bdZuordnungsmenge = ZuordnungsMenge;
		}

		public BigDecimal get_bdZuordnungsmenge()
		{
			return bdZuordnungsmenge;
		}
		
		public String get_GueltigZeitraum() throws myException
		{
			String cRueck = this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_cF_NN("------").substring(0, 6);
			cRueck += " - "+this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_cF_NN("------").substring(0, 6);
			return cRueck;
		}

		public String get_Firma() throws myException
		{
			String cRueck = this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_NAME1_cUF_NN("");
			return cRueck;
		}
		
		public String get_Ort() throws myException
		{
			String cRueck = this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ORT_cUF_NN("");
			return cRueck;
		}
		
	}


	public BSKC_ModulContainerLIST get_oBSKC_ModulContainerLIST()
	{
		return oBSKC_ModulContainerLIST;
	}


	
	
}
