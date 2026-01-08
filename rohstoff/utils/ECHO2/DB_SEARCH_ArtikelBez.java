package rohstoff.utils.ECHO2;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.utils.ECHO2.ARTBEZ_Selector.XX_Tell_me_the_actual_adress_id;

public class DB_SEARCH_ArtikelBez extends MyE2_DB_MaskSearchField
{

	private Insets    	oINSETS_For_Components = null;
	private boolean 	bAddonMultiColSearch = false;
	
	
	/*
	 * soll das suchergebnis in den schnellzugriffs-popups wegen artikelbez-ek-zuordnung beschraenkt werden,
	 * dann muss ein myE2_textField mit dem aktuellen adress-id uebergeben werden
	 */
	private XX_Tell_me_the_actual_adress_id  oTell_Adress_ID = null;
	


	/**
	 * 
	 * @param osqlField
	 * @param INSETS_For_Components
	 * @param extWidth4InputField
	 * @param extWidth4Ergebnis
	 * @param oFont4InputField
	 * @param oFont4Ergebnis
	 * @throws myException
	 */
	public DB_SEARCH_ArtikelBez(	SQLField 		osqlField, 
									Insets	  		INSETS_For_Components, 
									Extent 			extWidth4InputField, 
									Extent 			extWidth4Ergebnis, 
									Font 			oFont4InputField, 
									Font 			oFont4Ergebnis) throws myException
	{
		super(		osqlField, 
					" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ,  JT_ARTIKEL_BEZ.ARTBEZ1||' ('||JT_ARTIKEL.ARTBEZ1||')', JT_ARTIKEL.ANR1, JT_ARTIKEL_BEZ.ANR2 ", 
					bibE2.cTO() + ".JT_ARTIKEL_BEZ, " + bibE2.cTO() + ".JT_ARTIKEL ", 
					"ANR1",
					" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL " +
					" AND   NVL(JT_ARTIKEL.AKTIV,'N')='Y' AND    NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y' ",
					"UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%') OR UPPER(JT_ARTIKEL_BEZ.ARTBEZ1) LIKE UPPER('%#WERT#%') " +
					" OR UPPER(ANR1) LIKE UPPER('%#WERT#%') OR UPPER(ANR2) = UPPER('#WERT#') OR  TRIM(TO_CHAR(JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ))='#WERT#'",      //NEU_09
					null,
					null, 
					 "SELECT  NVL(ANR1,'-') || ' - ' ||  NVL(ANR2,'-') || ' - ' || trim(  NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'-')) from " + 
					 bibE2.cTO() + ".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL WHERE " +
					 		"JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ=#WERT#",
					 INSETS_For_Components, false);
		
		this.oINSETS_For_Components = INSETS_For_Components;
		
		//this.setBorder(new Border(1,Color.RED,Border.STYLE_SOLID));
		
		if (extWidth4InputField != null) this.get_oTextFieldForSearchInput().setWidth(extWidth4InputField);
		if (extWidth4Ergebnis != null) this.get_oTextForAnzeige().setWidth(extWidth4Ergebnis);
		
		if (oFont4InputField != null) this.get_oTextFieldForSearchInput().setFont(oFont4InputField);
		if (oFont4Ergebnis != null) this.get_oTextForAnzeige().setFont(oFont4Ergebnis);
		
		this.set_oPosX(null);
		this.set_oPosY(null);
		this.set_oPopupHigh(new Extent(500));
		
	}

	
	/**
	 * 
	 * @param osqlField
	 * @param INSETS_For_Components
	 * @param extWidth4InputField
	 * @param extWidth4Ergebnis
	 * @param oFont4InputField
	 * @param oFont4Ergebnis
	 * @throws myException
	 */
	public DB_SEARCH_ArtikelBez(	SQLField 		osqlField, 
									Insets	  		INSETS_For_Components, 
									Extent 			extWidth4InputField, 
									Extent 			extWidth4Ergebnis, 
									Font 			oFont4InputField, 
									Font 			oFont4Ergebnis, 
									boolean 		AddMultiColSearch,
									XX_Tell_me_the_actual_adress_id  otell_adress_id) throws myException
	{
		super(		osqlField, 
					" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ,  JT_ARTIKEL_BEZ.ARTBEZ1||' ('||JT_ARTIKEL.ARTBEZ1||')', JT_ARTIKEL.ANR1, JT_ARTIKEL_BEZ.ANR2 ", 
					bibE2.cTO() + ".JT_ARTIKEL_BEZ, " + bibE2.cTO() + ".JT_ARTIKEL ", 
					"ANR1",
					" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL " +
					" AND   NVL(JT_ARTIKEL.AKTIV,'N')='Y' AND    NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y' ",
					"UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%') OR UPPER(JT_ARTIKEL_BEZ.ARTBEZ1) LIKE UPPER('%#WERT#%') " +
					" OR UPPER(ANR1) LIKE UPPER('%#WERT#%') OR UPPER(ANR2) = UPPER('#WERT#') OR  TRIM(TO_CHAR(JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ))='#WERT#'",      //NEU_09
					null,
					null, 
					 "SELECT  NVL(ANR1,'-') || ' ' ||  NVL(ANR2,'-') || ' ' || trim(  NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'-')) from " + 
					 bibE2.cTO() + ".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL WHERE " +
					 		"JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ=#WERT#",
					 INSETS_For_Components, false);
		
		this.oINSETS_For_Components = INSETS_For_Components;
		
		this.oTell_Adress_ID = otell_adress_id;
		
		this.bAddonMultiColSearch = AddMultiColSearch;
		
		if (extWidth4InputField != null) this.get_oTextFieldForSearchInput().setWidth(extWidth4InputField);
		if (extWidth4Ergebnis != null) this.get_oTextForAnzeige().setWidth(extWidth4Ergebnis);
		
		if (oFont4InputField != null) this.get_oTextFieldForSearchInput().setFont(oFont4InputField);
		if (oFont4Ergebnis != null) this.get_oTextForAnzeige().setFont(oFont4Ergebnis);
		
		this.set_oPosX(null);
		this.set_oPosY(null);
		this.set_oPopupHigh(new Extent(500));
		
		if (this.bAddonMultiColSearch)
		{
			this.set_AddOnComponent(new E2_ComponentGroupHorizontal(0,new popupButtonMit_Schnell_Selektor() ,E2_INSETS.I_0_0_0_0));
		}
		
	}

	
	
	
	/**
	 * 
	 * @param osqlField
	 * @param INSETS_For_Components
	 * @param extWidth4InputField
	 * @param extWidth4Ergebnis
	 * @param oFont4InputField
	 * @param oFont4Ergebnis
	 * @throws myException
	 */
	public DB_SEARCH_ArtikelBez(	SQLField 							osqlField, 
									Insets	  							INSETS_For_Components, 
									Extent 								extWidth4InputField, 
									Extent 								extWidth4Ergebnis, 
									Font 								oFont4InputField, 
									Font 								oFont4Ergebnis, 
									boolean 							AddMultiColSearch,
									XX_Tell_me_the_actual_adress_id  	otell_adress_id,
									boolean                             bLabel4AnzeigeStattText4Anzeige) throws myException
	{
		super(		osqlField, 
					" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ,  JT_ARTIKEL_BEZ.ARTBEZ1||' ('||JT_ARTIKEL.ARTBEZ1||')', JT_ARTIKEL.ANR1, JT_ARTIKEL_BEZ.ANR2 ", 
					bibE2.cTO() + ".JT_ARTIKEL_BEZ, " + bibE2.cTO() + ".JT_ARTIKEL ", 
					"ANR1",
					" JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL " +
					" AND   NVL(JT_ARTIKEL.AKTIV,'N')='Y' AND    NVL(JT_ARTIKEL_BEZ.AKTIV,'N')='Y' ",
					"UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%') OR UPPER(JT_ARTIKEL_BEZ.ARTBEZ1) LIKE UPPER('%#WERT#%') " +
					" OR UPPER(ANR1) LIKE UPPER('%#WERT#%') OR UPPER(ANR2) = UPPER('#WERT#') OR  TRIM(TO_CHAR(JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ))='#WERT#'",      //NEU_09
					null,
					null, 
					 "SELECT  NVL(ANR1,'-') || ' ' ||  NVL(ANR2,'-') || ' ' || trim(  NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'-')) from " + 
					 bibE2.cTO() + ".JT_ARTIKEL_BEZ,"+bibE2.cTO()+".JT_ARTIKEL WHERE " +
					 		"JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL AND JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ=#WERT#",
					 INSETS_For_Components, false);
		
		this.oINSETS_For_Components = INSETS_For_Components;
		
		this.oTell_Adress_ID = otell_adress_id;
		
		this.bAddonMultiColSearch = AddMultiColSearch;
		
		if (extWidth4InputField != null) this.get_oTextFieldForSearchInput().setWidth(extWidth4InputField);
		if (extWidth4Ergebnis != null) this.get_oTextForAnzeige().setWidth(extWidth4Ergebnis);
		
		if (oFont4InputField != null) this.get_oTextFieldForSearchInput().setFont(oFont4InputField);
		if (oFont4Ergebnis != null) this.get_oTextForAnzeige().setFont(oFont4Ergebnis);
		
		this.set_oPosX(null);
		this.set_oPosY(null);
		this.set_oPopupHigh(new Extent(500));
		
		if (this.bAddonMultiColSearch)
		{
			this.set_AddOnComponent(new E2_ComponentGroupHorizontal(0,new popupButtonMit_Schnell_Selektor() ,E2_INSETS.I_0_0_0_0));
		}
		
		if (bLabel4AnzeigeStattText4Anzeige)
		{
			this.set_bLabel4AnzeigeStattText4Anzeige(true);
			if (extWidth4Ergebnis != null) this.get_oLabel4Anzeige().setWidth(extWidth4Ergebnis);
			if (oFont4Ergebnis != null) this.get_oLabel4Anzeige().get_oLabel().setFont(oFont4Ergebnis);
		}
		
		
	}

	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			DB_SEARCH_ArtikelBez oRueck = new DB_SEARCH_ArtikelBez(	this.EXT_DB().get_oSQLField(),
																	this.oINSETS_For_Components, null, null, null, null, this.bAddonMultiColSearch, this.oTell_Adress_ID);
			
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			oRueck.get_oTextFieldForSearchInput().setWidth(this.get_oTextFieldForSearchInput().getWidth());
			
			oRueck.get_oTextForAnzeige().setFont(this.get_oTextForAnzeige().getFont());
			oRueck.get_oTextFieldForSearchInput().setFont(this.get_oTextFieldForSearchInput().getFont());
			
			oRueck.set_bTextForAnzeigeVisible(this.get_bTextForAnzeigeVisible());
			oRueck.set_oMaskActionAfterMaskValueIsFound(this.get_oMaskActionAfterMaskValueIsFound());
			
			oRueck.set_bLabel4AnzeigeStattText4Anzeige(this.get_bLabel4AnzeigeStattText4Anzeige());
			oRueck.get_oLabel4Anzeige().setWidth(this.get_oLabel4Anzeige().getWidth());
			oRueck.get_oLabel4Anzeige().get_oLabel().setFont(this.get_oLabel4Anzeige().get_oLabel().getFont());
			
			oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("Error Copying DB_SEARCH_Sorte: "+ex.get_ErrorMessage().get_cMessage().CTrans());
		}
		
	}
	
	
	public void set_oTell_Adress_ID(XX_Tell_me_the_actual_adress_id oTellAdressID) 
	{
		this.oTell_Adress_ID = oTellAdressID;
	}

	
	
	//zusatzkomponente: schnell-selektor ueber die artikelgruppe
	private class popupButtonMit_Schnell_Selektor extends MyE2_Button
	{
		
		public popupButtonMit_Schnell_Selektor()
		{
			super(E2_Selection_Row_With_Multi_Cols.ICON_FOR_SEARCHBUTTON);
			
			this.setToolTipText(new MyE2_String("Auswahl des Sorte ...").CTrans());
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					new ownPopupWindow();
				}
			});
			
		}
		
		private class ownPopupWindow extends E2_BasicModuleContainer
		{

			public ownPopupWindow() throws myException
			{
				super();
				ARTBEZ_Selector oSel =  new ownRowSelector();
				
				this.add(oSel,E2_INSETS.I_2_2_2_2);
				oSel.START("", true);

				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(350), new MyE2_String("Bitte wählen Sie einen Sortenbezeichnung aus !"));
			}

			
			private class ownRowSelector extends ARTBEZ_Selector
			{
				public ownRowSelector() throws myException
				{
					super(DB_SEARCH_ArtikelBez.this.oTell_Adress_ID);
					
					this.set_ActionAgentFuerLastSelection(new XX_ActionAgent()
					{
						@Override
						public void executeAgentCode(ExecINFO execInfo) 	throws myException
						{
							DB_SEARCH_ArtikelBez.this.set_cActualMaskValue(ownRowSelector.this.get_cWertFuerAusstieg(),true,true,true);
							ownPopupWindow.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						}
						
					});
				}
			}
		}
	}

	
	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}
	

	
	
}
