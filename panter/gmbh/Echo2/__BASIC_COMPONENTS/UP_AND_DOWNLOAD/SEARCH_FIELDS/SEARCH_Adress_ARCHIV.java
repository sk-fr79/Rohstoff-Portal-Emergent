package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Manipulator_Result;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class SEARCH_Adress_ARCHIV extends MaskSearchField_WithAdditionalConditions
{
	
	public static String           cAdressSearchQuery4LabelMainAdresse = "SELECT trim(trim(  NVL(NAME1,''))|| ' ' || trim(  NVL(NAME2,'')))|| ', ' || trim(  NVL(STRASSE,'')) || ', '|| trim(NVL(PLZ,''))||' '|| trim(NVL(ORT,'')) " +
																		" FROM " + bibE2.cTO() + ".JT_ADRESSE WHERE ID_ADRESSE=#WERT#";

	public static String           cAdressSearchQuery4LabelLagerAdress = "SELECT " +
																				" CASE " +
																				" WHEN JT_ADRESSE.ADRESSTYP=1  THEN " +
																				"     CSCONVERT('<Firmensitz>','NCHAR_CS') " +
																				" ELSE " +
																				"     trim(trim(  NVL(NAME1,''))|| ' ' || trim(  NVL(NAME2,'')))|| ', ' || trim(  NVL(STRASSE,'')) || ', '|| trim(NVL(PLZ,''))||' '||trim(NVL(ORT,''))" +
																				" END " +
																		" FROM " + bibE2.cTO() + ".JT_ADRESSE WHERE ID_ADRESSE=#WERT#";

	/**
	 * 
	 * @author manfred
	 * @date   08.04.2013
	 * @throws myException
	 */
	public SEARCH_Adress_ARCHIV(	) throws myException
	{
		this(false,false,E2_INSETS.I_0_0_2_0);
	}

	
	/**
	 * aenderung 2010-11-18: weiterer konstructor, der definiert, ob lieferanten oder abnehmer hervorgehoben werden sollen 	
	 * @author 
	 * @date   
	 * @param MarkLieferanten
	 * @param MarkAbnehmer
	 * @param oInsets4Components
	 * @throws myException
	 */
	public SEARCH_Adress_ARCHIV(	boolean MarkLieferanten, boolean MarkAbnehmer, Insets oInsets4Components) throws myException
	{
		super(		
					"JT_ADRESSE.ID_ADRESSE, NVL(VORNAME,'-')," +
					"CASE WHEN NAME1 IS NULL THEN CSCONVERT('<name1>','NCHAR_CS') ELSE CSCONVERT(NAME1||' '||NVL(NAME2,''),'NCHAR_CS')  END ," +
					"NVL(PLZ,'<plz>'),NVL(ORT,'<ort>'),nvl(STRASSE,'<straße>') || ' ' || nvl(HAUSNUMMER,''),'<typ>'", 
					bibE2.cTO()+".JT_ADRESSE, "+bibE2.cTO()+".JT_FIRMENINFO ", 
					"NAME1,ORT",
					"JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO +
					" AND NVL(JT_ADRESSE.AKTIV,'N')='Y' ", 
					"    UPPER(VORNAME) LIKE UPPER('%#WERT#%') " +
					" OR UPPER(NAME1) LIKE UPPER('%#WERT#%') " +
					" OR UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
					 "OR LIEF_NR='#WERT#'  " +
					 "OR ABN_NR='#WERT#'  " +
					 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
					 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'", 
					 null,
					 null,
					 SEARCH_Adress_ARCHIV.cAdressSearchQuery4LabelMainAdresse,
					 oInsets4Components, 
					 true);
		
		this.set_oPosX(null);
		this.set_oPosY(null);
		
		
		SearchBlockStandard_WithAdditionalConditions oSearchBlock = (SearchBlockStandard_WithAdditionalConditions)this.get_oSeachBlock();

		// Suche nach Adressen denen ein bestimmter Artikel zugeordnet wurde
		String sSearchBlock = "  JT_ADRESSE.ID_ADRESSE IN (SELECT DISTINCT ABL.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF ABL WHERE ABL.ID_ARTIKEL_BEZ IN (SELECT AB.ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB WHERE AB.ID_ARTIKEL = #ID_ART#) )" ;
		oSearchBlock.add_SearchConditionForARCHIVE(new DBSearchCondition(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ARTIKEL, 
																		"ID_ARTIKEL", 
																		"ID_ART", 
																		sSearchBlock,
																		"Die Suche wird eingeschränkt auf Adressen, die die Sorte mit der ID #ID_ART# zugewiesen haben.", false));
		

		// Manipulatoren setzen
		oSearchBlock.set_oManipulator(new ownSearchManipulator(MarkLieferanten,MarkAbnehmer));
		
	}
	
	
	

	/*
	 * eigener searchblock mit manipulator zur anzeige ob lieferant oder abnehmer
	 */
	private class ownSearchManipulator extends XX_Manipulator_Result
	{
		private     boolean   			bMarkLieferanten = false;
		private     boolean   			bMarkAbnehmer = false;

		
		public ownSearchManipulator(boolean MarkLieferanten,	boolean MarkAbnehmer) {
			super();
			this.bMarkLieferanten = MarkLieferanten;
			this.bMarkAbnehmer = 	MarkAbnehmer;
		}



		public void Manipulate(MyE2_Button oResultButton) throws myException 
		{
			if (oResultButton.getText().equals("<typ>"))
			{
				String cID_ADRESS = bibALL.ReplaceTeilString(oResultButton.EXT().get_C_MERKMAL(), ".", "");
				String QueryLieferant = "SELECT COUNT(JT_ADRESSE.ID_ADRESSE) FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_ADRESSKLASSE, "+bibE2.cTO()+".JT_ADRESSKLASSE_DEF "+ 
										 " WHERE " +
										 " JT_ADRESSE.ID_ADRESSE  = JT_ADRESSKLASSE.ID_ADRESSE AND JT_ADRESSKLASSE.ID_ADRESSKLASSE_DEF = JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF "+
							            " AND    NVL(JT_ADRESSKLASSE_DEF.IST_LIEFERANT,'N')='Y' AND JT_ADRESSE.ID_ADRESSE="+bibALL.ReplaceTeilString(cID_ADRESS, ".", "");
				
				String QueryKunde = "SELECT COUNT(JT_ADRESSE.ID_ADRESSE) FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_ADRESSKLASSE, "+bibE2.cTO()+".JT_ADRESSKLASSE_DEF "+ 
										 " WHERE " +
										 " JT_ADRESSE.ID_ADRESSE  = JT_ADRESSKLASSE.ID_ADRESSE AND JT_ADRESSKLASSE.ID_ADRESSKLASSE_DEF = JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF "+
							           " AND    NVL(JT_ADRESSKLASSE_DEF.IST_KUNDE,'N')='Y' AND JT_ADRESSE.ID_ADRESSE="+bibALL.ReplaceTeilString(cID_ADRESS, ".", "");
	
	
				String cTyp_Info = "";
				if (! bibDB.EinzelAbfrage(QueryLieferant).equals("0"))
						cTyp_Info += "<L>";
				
				if (! bibDB.EinzelAbfrage(QueryKunde).equals("0"))
						cTyp_Info += "<A>";
	
				if (S.isEmpty(cTyp_Info))
				{
					cTyp_Info = "<??>";
				}
			
				oResultButton.setText(cTyp_Info);
				oResultButton.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
				
				if (oResultButton.getText().equals("<??>"))    //ohne resultat weniger gut sichtbar machen
				{
					oResultButton.setForeground(new E2_ColorDDark());
					oResultButton.setToolTipText(new MyE2_String("Die Adresse ist WEDER Lieferant noch Abnehmer").CTrans());
				}
				else
				{
					oResultButton.setFont(new E2_FontPlain());           //zuerst mal standart fuer definierte lieferanten/abnehmer

					if (oResultButton.getText().equals("<L>"))
					{
						oResultButton.setToolTipText(new MyE2_String("Die Adresse ist ein Lieferant").CTrans());
						if (this.bMarkLieferanten)
						{
							oResultButton.setFont(new E2_FontBold(2));
						}
					}
					else if (oResultButton.getText().equals("<A>"))
					{
						oResultButton.setToolTipText(new MyE2_String("Die Adresse ist ein Abnehmer").CTrans());
						if (this.bMarkAbnehmer)
						{
							oResultButton.setFont(new E2_FontBold(2));
						}
					}
					else if (oResultButton.getText().equals("<LA>"))
					{
						oResultButton.setToolTipText(new MyE2_String("Die Adresse ist SOWOHL Lieferant als auch Abnehmer").CTrans());
						if (this.bMarkLieferanten || this.bMarkAbnehmer)
						{
							oResultButton.setFont(new E2_FontBold(2));
						}
					}
				}
			}
			
			if (	oResultButton.getText().equals("<vorname>") || 
					oResultButton.getText().equals("<name1>") || 
					oResultButton.getText().equals("<plz>") ||
					oResultButton.getText().equals("<ort>") ||
					oResultButton.getText().equals("<name1>"))    //ohne resultat weniger gut sichtbar machen
			{
				oResultButton.setForeground(new E2_ColorDDark());
			}

			
			//jetzt noch die spalte, nach der sortiert wurde, fett
			if (oResultButton.EXT().get_I_MERKMAL() != null)
			{
				if (oResultButton.EXT().get_I_MERKMAL()==2)   //name ist an 0-1-2-ter stelle
				{
					oResultButton.setFont(new E2_FontBold());
				}
			}
			
			
			//oResultButton.setText(oResultButton.getText()+" "+cZusatz);
			
		}

		
	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			SEARCH_Adress_ARCHIV oRueck = new SEARCH_Adress_ARCHIV();
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("DB_SEARCH_Adress:get_Copy:Error building copy-object: "+ex.get_ErrorMessage().get_cMessage().CTrans());
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


	
		

	@Override
	public String get_DBTableName() {
		return "ADRESSE";
	}
	
}
