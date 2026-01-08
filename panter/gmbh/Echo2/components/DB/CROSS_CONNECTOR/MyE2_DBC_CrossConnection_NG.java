package panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR;


import java.util.Vector;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;


public abstract class MyE2_DBC_CrossConnection_NG extends MyE2_Grid_With_CheckBoxes implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{
    // konstanten regeln, was bei einer cross-connection-beziehung beachtet werden muss
    public static int CROSSTYP_ALLOW_ALL = 100;
    public static int CROSSTYP_MAX_ONE = 101; // nur 1 knopf erlaubt
    public static int CROSSTYP_MUST_ONE = 102; // mindestens 1 knopf muss eingegeben werden
    public static int CROSSTYP_EXACT_ONE = 103; // mindestens 1 knopf/genau 1 knopf muss eingegeben werden
    private int CROSSTYP = MyE2_DBC_CrossConnection_NG.CROSSTYP_ALLOW_ALL; // standard: alles erlaubt
    
    
    private String cTO = ""; // tableowner
    private String cHauptTabelle = null;
    private String cMittlerTabelle = null;
    private String cNebenTabelle = null;
    private String cIndexHauptTabelle = null;
    private String cIndexMittlerTabelle = null;
    private String cIndexMittlerTabelleZuHaupt = null;
    private String cIndexMittlerTabelleZuNeben = null;
    private String cIndexNebenTabelle = null;
    private String cAbfrageAusdruckFuerBeschreibung = null;
    private String cAbfrageAusdruckFuerMERKMAL2 = null;
    private String cAbfrageAusdruckFuerMERKMAL3 = null;
    private Vector<MyE2_CheckBox> vCheckBoxKreuzTab = new Vector<MyE2_CheckBox>(); // hier werden die kreuztabellenbuttons hinterlegt
    private Vector<String> vWerteWarenAngekreuzt = new Vector<String>(); // array wird bei anzeige gefüllt,
                                                          // falls das speichern einer änderung erfolgt
                                                          // wird geprüft, ob sich überhaupt was geändert hat

    
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
    
    private MyE2_Message 			ErrorMessageForValidationAfterInput = null;			
	
    private MyString 				cErrorMessageString = null;
    
    private Font   					oFont = null;
    
    /*
     * sortierstatement fuer die anzeige
     */
    private String 					cORDER_STATEMENT = null;
 	
    
    
    private Vector<XX_ActionAgent>  vActionAgent4Checkboxes = new Vector<XX_ActionAgent>();
   
    
	public abstract Vector<String> build_InsertVector(String cHauptIDUnformated) throws myException;
	public abstract Vector<String> get_vSQL_WHEREBLOCKS_QUERYING_CROSSTABLE();
	public abstract Vector<String> get_vSQL_WHEREBLOCKS_VALUES_TO_FILL();






	/**
     * @param osqlField
     * @param cmittlerTabelle
     * @param cnebenTabelle
     * @param ckeyMittlerTabelle
     * @param ckeyMittlerTabelleZuHaupt
     * @param ckeyMittlerTabelleZuNeben
     * @param ckeyNebenTabelle
     * @param cabfrageAusdruckFuerBeschreibung
     * @param cabfrageAusdruckFuerMERKMAL2 
     * @param cabfrageAusdruckFuerMERKMAL3 
     * @param vzusatzWhereStatements
     * @param cORDERSTATEMENT 
     * @param iAnordnungSpaltenZahl
     * @param pFont
     * @param iCrossTyp
     * @throws myException
     */
    public MyE2_DBC_CrossConnection_NG(	SQLFieldForPrimaryKey 	osqlField, 
    									String 					cmittlerTabelle,
    									String 					cnebenTabelle, 
    									String 					ckeyMittlerTabelle, 
    									String 					ckeyMittlerTabelleZuHaupt, 
    									String 					ckeyMittlerTabelleZuNeben, 
    									String 					ckeyNebenTabelle, 
    									String 					cabfrageAusdruckFuerBeschreibung, 
    									String 					cabfrageAusdruckFuerMERKMAL2,
    									String 					cabfrageAusdruckFuerMERKMAL3, 
    									String 					cORDERSTATEMENT, 
    									int 					iAnordnungSpaltenZahl,
    									Font 					pFont, 
    									MyString 				errorMessageForValidationAfterInput, 
    									int 					iCrossTyp) throws myException
    {
        super();
        
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);

        this.cMittlerTabelle = 						cmittlerTabelle;
        this.cNebenTabelle = 						cnebenTabelle;
        this.cIndexMittlerTabelle = 				ckeyMittlerTabelle;
        this.cIndexMittlerTabelleZuHaupt = 			ckeyMittlerTabelleZuHaupt;
        this.cIndexMittlerTabelleZuNeben = 			ckeyMittlerTabelleZuNeben;
        this.cIndexNebenTabelle = 					ckeyNebenTabelle;
        this.cAbfrageAusdruckFuerBeschreibung = 	cabfrageAusdruckFuerBeschreibung;
        this.cAbfrageAusdruckFuerMERKMAL2 = 		cabfrageAusdruckFuerMERKMAL2;
        this.cAbfrageAusdruckFuerMERKMAL3 = 		cabfrageAusdruckFuerMERKMAL3;
        this.cORDER_STATEMENT=						cORDERSTATEMENT;
        this.CROSSTYP = 							iCrossTyp;
        this.cErrorMessageString =					errorMessageForValidationAfterInput;
        this.ErrorMessageForValidationAfterInput=	new MyE2_Message(errorMessageForValidationAfterInput,MyE2_Message.TYP_ALARM,false);
        
        this.cHauptTabelle = osqlField.get_cTableName();
        this.cIndexHauptTabelle =osqlField.get_cFieldName();

        this.oFont = pFont;
        
//        this.setSize(iAnordnungSpaltenZahl);
        this.set_NumOfColsHorizontalOrRowsVertical(iAnordnungSpaltenZahl);
        
        this.setOrientation(Grid.ORIENTATION_VERTICAL);

        // aufbau der auswahl - "varianten" - arrays
        String cSQL = "";

        String[][] aHelp = null;

        this.cTO = bibALL.get_TABLEOWNER();

        // erste Abfrage
        cSQL = "SELECT " + this.cAbfrageAusdruckFuerBeschreibung + " AS BESCHREIBUNG,";
        cSQL += (this.cIndexNebenTabelle + " AS WERT ,");
        if (S.isFull(this.cAbfrageAusdruckFuerMERKMAL2))
        {
        	cSQL += (this.cAbfrageAusdruckFuerMERKMAL2 + " AS MERKMAL2 ,");
        }
        if (S.isFull(this.cAbfrageAusdruckFuerMERKMAL3))
        {
        	cSQL += (this.cAbfrageAusdruckFuerMERKMAL3 + " AS MERKMAL3 ,");
        }
        //letztes komma weg
        cSQL = cSQL.substring(0,cSQL.length()-1);
        
        cSQL += (" FROM " + cTO + "." + cnebenTabelle + " " + cnebenTabelle);

        if (this.get_vSQL_WHEREBLOCKS_QUERYING_CROSSTABLE() != null && this.get_vSQL_WHEREBLOCKS_QUERYING_CROSSTABLE().size()>0)
            cSQL += " WHERE " + bibALL.Concatenate(this.get_vSQL_WHEREBLOCKS_QUERYING_CROSSTABLE()," AND ", null);

        if (this.cORDER_STATEMENT != null)
        	cSQL += " ORDER BY "+this.cORDER_STATEMENT;
        
        
		/*
		 * eine myConnection holen und direkt danach wieder freigeben
		 */
        aHelp = bibDB.EinzelAbfrageInArray(cSQL, "");

        if (aHelp == null)
        {
        	throw new myException("MyE2_DBC_CrossConnection:Constuctor:Error Querying Variants !");
        }
        else
        {
            for (int i = 0; i < aHelp.length; i++)
            {
                MyE2_CheckBox cbHelp = new MyE2_CheckBox(aHelp[i][0]);
                
                //2011-04-12: actionAgents zufuegen
                for (int l=0;l<this.vActionAgent4Checkboxes.size();l++)
                {
                	cbHelp.add_oActionAgent(this.vActionAgent4Checkboxes.get(l));
                }
                
                
                cbHelp.setFont(pFont);
                cbHelp.EXT().set_C_MERKMAL(aHelp[i][1]);         // der WERT kommt in das MERKMAL
                if (aHelp[i].length>2)
                {
                    cbHelp.EXT().set_C_MERKMAL2(aHelp[i][2]);    // zusatzinfo 1
                }
                if (aHelp[i].length>3)
                {
                    cbHelp.EXT().set_C_MERKMAL3(aHelp[i][3]);   // zusatzinfo 2
                }
                
                this.vCheckBoxKreuzTab.add(cbHelp);
            }
            this.set_vCheckBoxes(this.vCheckBoxKreuzTab);
        	this.build_selectorGrid();
         }
        
        /*
         * jetzt ein validator, der den kreuztabellentyp ueberwacht
         */
        this.EXT().add_FieldSetters_AND_Validator__BeforeReadInputMAP(new MyE2_DBC_CrossConnection_NG.ownFieldValidator_AfterInput(this));
        
    }

    

    
    
    
     

    // aufbau der abfrage für die anzeige des kreuztabellenfelds
    private boolean fuelle_KreuztabellenGrid(String cIndexWert) throws myException
    {
        boolean bRueck = true;
        String cSQL = "";

        cSQL = "SELECT " + cNebenTabelle + "." + this.cIndexNebenTabelle + " AS WERT ";
        cSQL += (" FROM " + cTO + "." + cMittlerTabelle + " " + cMittlerTabelle + ",");
        cSQL += (cTO + "." + cNebenTabelle + " " + cNebenTabelle);
        cSQL += (" WHERE " + cNebenTabelle + "." + cIndexNebenTabelle + "=" + cMittlerTabelle + "." + cIndexMittlerTabelleZuNeben);
        cSQL += (" AND  " + cMittlerTabelle + "." + cIndexMittlerTabelleZuHaupt + "=" + cIndexWert);

        if (this.get_vSQL_WHEREBLOCKS_VALUES_TO_FILL() != null && this.get_vSQL_WHEREBLOCKS_VALUES_TO_FILL().size()>0)
        {
            for (int i = 0; i < this.get_vSQL_WHEREBLOCKS_VALUES_TO_FILL().size(); i++)
            {
                cSQL += (" AND " + (String) this.get_vSQL_WHEREBLOCKS_VALUES_TO_FILL().get(i));
            }
        }

        Vector<String> vAngekreuzt = bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray(cSQL, ""));
        
        if (vAngekreuzt != null)
        {
            this.vWerteWarenAngekreuzt.removeAllElements();

            // jetzt haken an die zugeordneten elemente
            for (int i = 0; i < this.vCheckBoxKreuzTab.size(); i++)
            {
                this.vCheckBoxKreuzTab.get(i).setSelected(false);

                if (vAngekreuzt.contains(this.vCheckBoxKreuzTab.get(i).EXT().get_C_MERKMAL()))
                {
                	this.vCheckBoxKreuzTab.get(i).setSelected(true);
                    this.vWerteWarenAngekreuzt.add(this.vCheckBoxKreuzTab.get(i).EXT().get_C_MERKMAL());
                }
                
	        	//2011-04-12: formatieren
	        	if (this.get_oFormatCheckBoxes()!=null)
	        	{
	        		this.get_oFormatCheckBoxes().doFormat(this.vCheckBoxKreuzTab.get(i));
	        	}

                
            }
        }
        else
        {
           	throw new myException("MyE2_DBC_CrossConnection:Constuctor:Error Filling Variants !");
        }

        return bRueck;
    }

    
    
    
	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(true) && this.EXT().get_bCanBeEnabled();;
		this.setEnabled(bVoraussetzung);
	}


	
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
	       for (int i = 0; i < this.vCheckBoxKreuzTab.size(); i++)
	        {
	            ((MyE2_CheckBox) this.vCheckBoxKreuzTab.get(i)).setSelected(false);
	        }
	}





    public void 					set_CROSSTYP(int iWert)    							{ 	this.CROSSTYP = iWert;    }
    public String					get_cCROSSFIELD_HauptTabelle()    					{	return this.cHauptTabelle;    }
    public String					get_cCROSSFIELD_MittlerTabelle() 					{	return this.cMittlerTabelle;    }
    public String 					get_cCROSSFIELD_NebenTabelle()   					{	return this.cNebenTabelle;    }
    public String 					get_cCROSSFIELD_IndexHauptTabelle()					{	return this.cIndexHauptTabelle;    }
    public String 					get_cCROSSFIELD_IndexMittlerTabelle()				{	return this.cIndexMittlerTabelle;   }
    public String 					get_cCROSSFIELD_IndexMittlerTabelleZuHaupt()		{	return this.cIndexMittlerTabelleZuHaupt;    }
    public String 					get_cCROSSFIELD_IndexMittlerTabelleZuNeben() 		{	return this.cIndexMittlerTabelleZuNeben;    }
    public String 					get_cCROSSFIELD_IndexNebenTabelle()    				{	return this.cIndexNebenTabelle;    }
    public String 					get_cCROSSFIELD_AbfrageAusdruckFuerBeschreibung()	{	return this.cAbfrageAusdruckFuerBeschreibung;    }
    public Vector<MyE2_CheckBox> 	get_vCheckBoxKreuzTab()								{	return vCheckBoxKreuzTab;    }
    public int 						get_CROSSTYP()	    								{	return CROSSTYP;    }

    

	public String get_cActualMaskValue() throws myException
	{
		return null;
	}


	public String get_cActualDBValueFormated() throws myException
	{
		return null;
	}


	public void set_cActualMaskValue(String cText) throws myException
	{
	}


	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DBC_CrossConnection:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");
		
		 String cUnformated = this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_cStringForDataBase(cText, true, false);
		 
          if (!this.fuelle_KreuztabellenGrid(cUnformated))
        	  throw new myException("MyE2_DBC_CrossConnection:set_cActualDBValueFormated: Error Filling MATRIX !");
 	}


	public void set_bIsComplexObject(boolean bisComplex)
	{
	}


	public boolean get_bIsComplexObject()
	{
		return true;
	}


	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
	    String cHauptIDFormated = this.EXT_DB().get_oSQLField().get_cNewValueFormated();
	    String cNewHauptIDUnformated = this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_cStringForDataBase(cHauptIDFormated, true, false);
	    
	    return this.build_InsertVector(cNewHauptIDUnformated);
		
	}


	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
	       // prüfen, ob etwas veränder wurde, wenn ja sql-vector bauen
        Vector<String> vHelp = new Vector<String>();

        for (int i = 0; i < this.vCheckBoxKreuzTab.size(); i++)
        {
            if (((MyE2_CheckBox) this.vCheckBoxKreuzTab.get(i)).isSelected())
            {
                vHelp.add(this.vCheckBoxKreuzTab.get(i).EXT().get_C_MERKMAL());
            }
        }

        boolean bIstGleich = true;
        String cVgl1 = "";
        String cVgl2 = "";

        // jetzt vHelp und this.v
        if (vHelp.size() == this.vWerteWarenAngekreuzt.size())
        {
            for (int i = 0; i < vHelp.size(); i++)
            {
                cVgl1 = (String) vHelp.get(i);
                cVgl2 = (String) this.vWerteWarenAngekreuzt.get(i);
                bIstGleich = bIstGleich & cVgl1.equals(cVgl2);
            }
        }
        else
        {
            bIstGleich = false;
        }


        if (bIstGleich)
        {
            return new Vector<String>(); // keine updates in der kreuztabelle
        }
        else
        {
        	String cHauptIDUnformated = oE2_ComponentMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(this.EXT_DB().get_oSQLField().get_cFieldLabel());
            return this.build_InsertVector(cHauptIDUnformated);
        }
	}


	
	
	
	public MyE2EXT__DB_Component EXT_DB()							{		return this.oEXTDB;		}
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)			{		this.oEXTDB = oEXT_DB;	}

	public MyE2_Message get_ErrorMessageForValidationAfterInput()
	{
		return this.ErrorMessageForValidationAfterInput;
	}
	
	
	
	/*
	 * validator, der die einhaltung der regeln, was fuer ein kreuztabellentyp es ist, ueberwacht
	 */
	public static class ownFieldValidator_AfterInput extends XX_FieldSetter_AND_Validator
	{
		
		private MyE2_DBC_CrossConnection_NG oFieldCrossConnection = null;
		/*
		 *    public static int CROSSTYP_ALLOW_ALL = 100;
			    public static int CROSSTYP_MAX_ONE = 101; // nur 1 knopf erlaubt
			    public static int CROSSTYP_MUST_ONE = 102; // mindestens 1 knopf muss eingegeben werden
			    public static int CROSSTYP_EXACT_ONE = 103; // mindestens 1 knopf/genau 1 knopf muss eingegeben werden
		 * 
		 */

		public ownFieldValidator_AfterInput(MyE2_DBC_CrossConnection_NG connection)
		{
			super();
			oFieldCrossConnection = connection;

		}
		
		
		public MyE2_MessageVector isValid( String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException
		{
			MyE2_MessageVector vRueck = new MyE2_MessageVector();
			
			int 					iCrossTYP = this.oFieldCrossConnection.get_CROSSTYP();
			Vector<MyE2_CheckBox> 	vCheckBoxen	= this.oFieldCrossConnection.get_vCheckBoxKreuzTab();

			
	        // wieviele sind angekreuzt ?
			int iAuswahl = 0;
	        for (int i = 0; i < vCheckBoxen.size(); i++)
	        {
	            if (((MyE2_CheckBox) vCheckBoxen.get(i)).isSelected())
	                iAuswahl++;
	        }
	
	
	        if (iCrossTYP == MyE2_DBC_CrossConnection_NG.CROSSTYP_ALLOW_ALL)
	        {
	            // egal
	        }
	        else if (iCrossTYP == MyE2_DBC_CrossConnection_NG.CROSSTYP_EXACT_ONE)
	        {
	            if (iAuswahl != 1)
	            	if (this.oFieldCrossConnection.get_ErrorMessageForValidationAfterInput()!=null)
	            		vRueck.add_MESSAGE(this.oFieldCrossConnection.get_ErrorMessageForValidationAfterInput(), false);
	            	else
	            		vRueck.add_MESSAGE(new MyE2_Message(new MyE2_String("Bitte beim GENAU EIN Element ankreuzen !"),MyE2_Message.TYP_ALARM,false), false);
	            
	        }
	        else if (iCrossTYP == MyE2_DBC_CrossConnection_NG.CROSSTYP_MUST_ONE)
	        {
	            if (iAuswahl == 0)
	            	if (this.oFieldCrossConnection.get_ErrorMessageForValidationAfterInput()!=null)
	            		vRueck.add_MESSAGE(this.oFieldCrossConnection.get_ErrorMessageForValidationAfterInput(), false);
	            	else
	            		vRueck.add_MESSAGE(new MyE2_Message(new MyE2_String("Bitte beim MINDESTENS EIN Element ankreuzen !"),MyE2_Message.TYP_ALARM,false), false);
	            
	        }
	        else if (iCrossTYP == MyE2_DBC_CrossConnection_NG.CROSSTYP_MAX_ONE)
	        {
	            if (iAuswahl > 1)
	            	if (this.oFieldCrossConnection.get_ErrorMessageForValidationAfterInput()!=null)
	            		vRueck.add_MESSAGE(this.oFieldCrossConnection.get_ErrorMessageForValidationAfterInput(), false);
	            	else
	            		vRueck.add_MESSAGE(new MyE2_Message(new MyE2_String("Bitte beim HOECHSTENS EIN Element ankreuzen !"),MyE2_Message.TYP_ALARM,false), false);	       
	         }

			return vRueck;
		}
		
	}

	
	
   public Vector<XX_ActionAgent> get_vActionAgent4Checkboxes()
   {
		return this.vActionAgent4Checkboxes;
   }

	
   public void add_ActionAgentToCheckboxes(XX_ActionAgent oAgent)
   {
	   this.vActionAgent4Checkboxes.add(oAgent);
	   
	   for (MyE2_CheckBox oCB: this.vCheckBoxKreuzTab)
	   {
		   oCB.remove_AllActionAgents();
		   for (int k=0;k<this.vActionAgent4Checkboxes.size();k++)
		   {
			   oCB.add_oActionAgent(this.vActionAgent4Checkboxes.get(k));
		   }
	   }
   }

	
}
