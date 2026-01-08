package rohstoff.Echo2BusinessLogic._4_ALL;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_FormaterForFoundButtons;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class DB_SEARCH_AdressBaseAndLager extends MyE2_DB_MaskSearchField
{
	private 		SQLField 		osqlField = null;
	
	public static 	String 		  	KONSTANTE_HAUPTADRESSE = "Hauptadresse";
	public static 	String 		  	KONSTANTE_LAGERADRESSE = "Lageradresse";
	
	
	
	private 	static String           cAdressSearchQuery4LabelAdress = 
				"SELECT " +
						" CASE " +
						" WHEN JT_ADRESSE.ADRESSTYP=1  THEN " +
						   bibDB.get_Constant4SQL(DB_SEARCH_AdressBaseAndLager.KONSTANTE_HAUPTADRESSE)+"||'  --  '|| trim(trim(  NVL(NAME1,''))|| ' ' || trim(  NVL(NAME2,'')))|| ', ' || trim(  NVL(STRASSE,'')) || ', '|| trim(NVL(PLZ,''))||' '||trim(NVL(ORT,''))" +
						" ELSE " +
							bibDB.get_Constant4SQL(DB_SEARCH_AdressBaseAndLager.KONSTANTE_LAGERADRESSE)+"||'  --  '|| trim(trim(  NVL(NAME1,''))|| ' ' || trim(  NVL(NAME2,'')))|| ', ' || trim(  NVL(STRASSE,'')) || ', '|| trim(NVL(PLZ,''))||' '||trim(NVL(ORT,''))" +
							"||'  ('||(" +
							" select trim(trim(  NVL(BA.NAME1,''))|| ' ' || trim(  NVL(BA.NAME2,'')))||' ('||TO_CHAR(BA.ID_ADRESSE)||')'  from JT_LIEFERADRESSE  LA  INNER JOIN JT_ADRESSE BA ON (LA.ID_ADRESSE_BASIS=BA.ID_ADRESSE) WHERE LA.ID_ADRESSE_LIEFER=JT_ADRESSE.ID_ADRESSE" +
							")||')'"+ 
						" END " +
				" FROM " + bibE2.cTO() + ".JT_ADRESSE WHERE ID_ADRESSE=#WERT#";

	
	
	/**
	 * 
	 * @param osqlfield
	 * @throws myException
	 */
	public DB_SEARCH_AdressBaseAndLager(SQLField osqlfield )throws myException
	{
		this(osqlfield,false);
		this.set_FormatterForButtons(new ownFoundButtonFormater());
		
		RowLayoutData  oRowLayout = MyE2_Row.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,2,2), null);
		this.get_oLabel4Anzeige().setLayoutData(oRowLayout);
		this.get_oTextFieldForSearchInput().setLayoutData(oRowLayout);
		this.get_buttonStartSearch().setLayoutData(oRowLayout);
		
	}

	
	/**
	 * 
	 * @param osqlfield
	 * @param bShowEraser
	 * @throws myException
	 */
	public DB_SEARCH_AdressBaseAndLager(SQLField osqlfield, boolean bShowEraser ) throws myException
	{
		super(		osqlfield, 

					"JT_ADRESSE.ID_ADRESSE, NVL(VORNAME,"+bibDB.get_Constant4SQL("<vorname>")+ " )," +
					"CASE WHEN NAME1 IS NULL THEN "+bibDB.get_Constant4SQL("<name1>")+ " ELSE CSCONVERT(NAME1||' '||NVL(NAME2,''),'NCHAR_CS')  END ," +
					"NVL(PLZ,'<plz>'),NVL(ORT,'<ort>'), " +
					" CASE WHEN JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO+" THEN "+
						bibDB.get_Constant4SQL(DB_SEARCH_AdressBaseAndLager.KONSTANTE_HAUPTADRESSE)+ 
					" ELSE "+bibDB.get_Constant4SQL(DB_SEARCH_AdressBaseAndLager.KONSTANTE_LAGERADRESSE)+ " END AS TYP ," +
					" CASE WHEN JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO+" THEN "+
						bibDB.get_Constant4SQL(" -- ")+ 
					" ELSE (select trim(trim(  NVL(BA.NAME1,''))|| ' ' || trim(  NVL(BA.NAME2,'')))||' ('||TO_CHAR(BA.ID_ADRESSE)||')'  from JT_LIEFERADRESSE  LA  INNER JOIN JT_ADRESSE BA ON (LA.ID_ADRESSE_BASIS=BA.ID_ADRESSE) WHERE LA.ID_ADRESSE_LIEFER=JT_ADRESSE.ID_ADRESSE) END AS HAUPT ," +
					" ID_ADRESSE" , 
					
					bibE2.cTO()+".JT_ADRESSE ",
					
					"NAME1,ORT",
					
					" (JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO +" OR JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_LIEFERADRESSE +")"+ 
					" AND NVL(JT_ADRESSE.AKTIV,'N')='Y' ", 
					
					"    UPPER(VORNAME) LIKE UPPER('%#WERT#%') " +
					" OR UPPER(NAME1) LIKE UPPER('%#WERT#%') " +
					" OR UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
					 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'",
					 
					null,
					null, 
					DB_SEARCH_AdressBaseAndLager.cAdressSearchQuery4LabelAdress,
					null, bShowEraser);
		
		
		
		this.osqlField = 		osqlfield;
		
		this.set_oPosX(null);
		this.set_oPosY(null);

		this.set_oPopupWidth(new Extent(600));
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
	
		this.set_FormatterForButtons(new ownFoundButtonFormater());

		RowLayoutData  oRowLayout = MyE2_Row.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,2,2), null);
		this.get_oLabel4Anzeige().setLayoutData(oRowLayout);
		this.get_oTextFieldForSearchInput().setLayoutData(oRowLayout);
		this.get_buttonStartSearch().setLayoutData(oRowLayout);

	}


	
	/**
	 * ueberschreiben, damit eine unterschiedliche Label-anzeige fuer gefundene haupt- oder lageradressen moeglich ist 
	 */
	public void FillLabelWithDBQuery(String cID_Adresse) throws myException
	{
		// zuerst die uebergabe eines leeren wertes abfangen
		if (bibALL.isEmpty(cID_Adresse))
		{
			this.get_oTextForAnzeige().setText("");
			this.get_oTextForAnzeige().setToolTipText("");
			
			this.get_oLabel4Anzeige().set_Text("");
			this.get_oLabel4Anzeige().get_oLabel().setToolTipText("");

			return;
		}
		
		String cQuery = bibALL.ReplaceTeilString(this.get_cCOMPLETE_SQL_FOR_Label(),"#WERT#",cID_Adresse);
		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cQuery);
		
		if (cErgebnis == null || cErgebnis.length!=1)
			throw new myException("MyE2_MaskSearchField:FillLabelWithDBQuery:Error Filling Field "+this.EXT_DB().get_oSQLField().get_cFieldName()+": "+cQuery);
		
		this.get_oTextForAnzeige().setText(cErgebnis[0][0]);
		this.get_oTextForAnzeige().setToolTipText(cErgebnis[0][0]);
		
		this.get_oLabel4Anzeige().set_Text(cErgebnis[0][0]);
		this.get_oLabel4Anzeige().get_oLabel().setToolTipText(cErgebnis[0][0]);
			
	}
	

	

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			DB_SEARCH_AdressBaseAndLager oRueck = new DB_SEARCH_AdressBaseAndLager(this.osqlField);
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("DB_SEARCH_Adress:get_Copy:Error building copy-object: "+ex.get_ErrorMessage().get_cMessage().CTrans());
		}
		
	}




	public SQLField get_oSqlField()
	{
		return osqlField;
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
	

	private class ownFoundButtonFormater extends XX_FormaterForFoundButtons {

		private RECORD_ADRESSE  recADRESSE = null;

		
		@Override
		public void DO_Format(XX_Button4SearchResultList button) throws myException {
			// beim ersten aufruf in der zeile wird die adresse abgefragt
			
			if (this.recADRESSE==null) {
				this.recADRESSE=new RECORD_ADRESSE(button.EXT().get_C_MERKMAL());    //hier steht die ID_ADRESSE
			}

			//platzhalter grau
			if (S.NN(button.getText()).trim().startsWith("<") && S.NN(button.getText()).trim().endsWith(">")) {
				button.setForeground(Color.DARKGRAY);
			}
			
			//hauptadressen dunkler und fett
			if (this.recADRESSE.get_ADRESSTYP_lValue(-1l).intValue()==myCONST.ADRESSTYP_FIRMENINFO) {
				button.setBackground(new E2_ColorDDark());
				button.setFont(new E2_FontBold());
			}
			
			//wenn es die 7. spalte ist, dann rechtsbuendig setzen
			if (button.EXT().get_I_MERKMAL()!=null && button.EXT().get_I_MERKMAL().intValue()==7) {
				button.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
			}
			
			
		}

		@Override
		public void RESET() throws myException
		{
		}


		@Override
		public void RESET_ROW(XX_Button4SearchResultList[] buttonsInRow) throws myException 
		{
			this.recADRESSE=null;
		}
		
	}
	
	
}
