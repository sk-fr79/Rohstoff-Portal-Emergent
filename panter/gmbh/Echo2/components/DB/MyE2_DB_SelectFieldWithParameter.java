package panter.gmbh.Echo2.components.DB;

import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.list.DefaultListModel;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataToView.zuOrdnung;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class MyE2_DB_SelectFieldWithParameter extends MyE2_SelectFieldWithParameters  implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	
	public MyE2_DB_SelectFieldWithParameter(SQLField osqlField, String[] aDefArray,boolean btranslate) throws myException
	{
		super(aDefArray, null, btranslate);
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");
		
		/*
		 * der erste wert muss leer sein / ZWINGEND
		 */
		if (aDefArray == null || aDefArray.length==0 || !aDefArray[0].equals(""))
			throw new myException("MyE2_DB_SelectField:Constructor:First Value MUST be empty !!");
		
		__define(osqlField);
	}


	
	
	public MyE2_DB_SelectFieldWithParameter(SQLField osqlField, String[][] aDefArray, boolean btranslate) throws myException
	{
		super(aDefArray, null, btranslate);
		if (osqlField == null)
			throw new myException("MyE2_DB_SelectField:Constructor:null-SQLField not allowed !");

		/*
		 * der erste wert muss leer sein / ZWINGEND
		 */
		if (aDefArray == null || aDefArray.length==0 || !aDefArray[0][1].equals(""))
			throw new myException("MyE2_DB_SelectField:Constructor:First Value MUST be empty ("+osqlField.get_cFieldName()+") !!");

		__define(osqlField);
	}


	public MyE2_DB_SelectFieldWithParameter(SQLField osqlField, dataToView oDataToView) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.set_oDataToViewForDatabase(oDataToView);

		__define(osqlField);
	}


	
	public MyE2_DB_SelectFieldWithParameter(SQLField osqlField) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		__define(osqlField);
	}

	
	
	/**
	 * @param osqlField
	 * @param cSQL_Query_For_LIST : Abfrage mit zwei spalten: Textanzeige und DatenbankWert zur anzeige in der liste !!! 
	 * 								WICHTIG ! Wert-Spalte muss formatiert sein
	 * @param bThirdColumnIS_STANDARD_MARKER  (kann als dritte spalte uebergeben werden, wenn die dropdown-tabelle einen 
	 * 								IST_STANDARD - marker beinhaltet
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldWithParameter(			SQLField 		osqlField, 
										String 			cSQL_Query_For_LIST, 
										boolean 		bThirdColumnIS_STANDARD_MARKER , 
										boolean 		btranslate) throws myException
	{
		super(cSQL_Query_For_LIST,bThirdColumnIS_STANDARD_MARKER,true,false,btranslate);
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cResult = oDB.EinzelAbfrageInArrayFormatiert(cSQL_Query_For_LIST,"");
		bibALL.destroy_myDBToolBox(oDB);
		
		if (cResult == null)
			throw new myException("MyE2_DB_SelectField:Constructor:Query has no result!");
		
		
		if (osqlField == null)
			throw new myException("MyE2_DB_SelectField:Constructor:null-SQLField not allowed !");

		if (cResult.length>0)
			if (bThirdColumnIS_STANDARD_MARKER && cResult[0].length<2)
				throw new myException("MyE2_DB_SelectField:Constructor:with bThirdColumnIS_STANDARD_MARKER = true there must be 3 query-cols !");
		
		/*
		 * der erste wert muss leer sein / ZWINGEND
		 */
		String[][] cWert = new String[cResult.length+1][2];
		cWert[0][0] = "-";		cWert[0][1] = "";
		for (int i=0;i<cResult.length;i++)
		{
			cWert[i+1][0] = cResult[i][0];		cWert[i+1][1] = cResult[i][1];
		}
		super.set_ListenInhalt(cWert,btranslate);

		/*
		 * standard einstellen, wenn noetig
		 */
		if (bThirdColumnIS_STANDARD_MARKER)
			for (int i=0;i<cResult.length;i++)
			{
				if (cResult[i][2].equals("Y"))
				{
					this.setSelectedIndex(i+1);
					break;
				}
				
			}
		
		__define(osqlField);
	}

	
	private void __define(SQLField osqlField)
	{
		super.set_cDefineAction_IF_ValueNotFound(MyE2_SelectField.SHOW_EXCEPTION_IF_VALUE_NOT_FOUND);

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
	}
	
	
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		
		String cText = "";

		if (!bSetDefault)
		{
			this.set_ActiveValue_OR_FirstValue("");
			this.EXT_DB().set_cLASTActualDBValueFormated(cText);
			this.EXT_DB().set_cLASTActualMaskValue(cText);
			return;
		}

		
		
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();
		
		this.set_ActiveValue_OR_FirstValue(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

	}

	public String get_cActualMaskValue() throws myException
	{
		return this.get_ActualView();
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return this.get_ActualWert();
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		this.set_ActiveValue(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);
	}

	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_SelectField:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");


		this.set_ActiveValue(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);

	}

	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	public boolean get_bIsComplexObject()
	{
		return false;
	}

	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}

	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)
	{
		this.oEXTDB = oEXT_DB;
	}

	
	
    public void set_oDataToViewForDatabase(dataToView odataToView) throws myException
    {
    	if (!odataToView.get_cValueAtPosition(0).equals(""))
    		throw new myException("MyE2_DB_SelectField:set_oDataToViewForDatabase:First Value MUST be empty !!");
    		
    	super.set_oDataToView(odataToView);
    }

	
	
	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		
		this.setEnabled(bVoraussetzung);
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
	}

	
	
	/*
	 * eine liste mit bestimmten werten hervorheben
	 */
	public void MarkValues(Vector<String> vValuesUnformated, String MARKER) throws myException
	{
		dataToView oDV = this.get_oDataToView();

		int iPosActual = this.getSelectedIndex();
		
		for (int i=0;i<oDV.size();i++)
		{
			String cValue = bibALL.ReplaceTeilString(oDV.get_cValueAtPosition(i),".","");     // value ist hier formatiert  
			if (vValuesUnformated.contains(cValue))
			{
				if (!oDV.get_cViewAtPosition(i).startsWith(MARKER))
				{
					oDV.set_cViewAtPosition(MARKER+oDV.get_cViewAtPosition(i),i);
				}
			}
		}
		this.set_oDataToViewForDatabase(oDV);

		if (iPosActual>=0)
			this.setSelectedIndex(iPosActual);

	}


	
	   /*
     * markiert einen aktuellen wert im werte-array
     */
    public int set_ActiveValue(String cWert) throws myException
    {
        this.removeErrorEintrag();
        this.cleanFromShadow();

        
        
        if (cWert == null)
            throw new myException("MyE2_DB_SelectField:set_ActiveValue:null-value not allowed !");
        
        int iRueck=this.get_oDataToView().get_PositionOfData(cWert);
        
        
        if (iRueck == -1)
        {
           	//dann erst in der schattenliste nachsehen
        	if (this.get_odataToViewShadow() != null)
        	{
        		// wenn der wert in der schattenliste vorhanden ist, dann diese zuordnung in die echte liste rueberholen und
        		// das SelectField neu bauen
        		int iPosShadow = this.get_odataToViewShadow().get_PositionOfData(cWert);
        		if (iPosShadow >=0 )
        		{
        			this.get_oDataToView().add((zuOrdnung)this.get_odataToViewShadow().get_zoAtPosition(iPosShadow).get_Copy(null));
        	        this.setModel(new DefaultListModel(this.get_oDataToView().get_ViewArray()));
        	        this.setSelectedIndex(this.get_oDataToView().size()-1);
        	        return (this.get_oDataToView().size()-1);
        		}
        	}

        	
        	
        	
            if (this.get_cDefineAction_IF_ValueNotFound().equals(MyE2_SelectField.SHOW_ERROR_STRING_IF_VALUE_NOT_FOUND))
            {
                this.get_oSelModel().add("@@@ERROR@@@"); // am ende einfügen und aktivieren           
                this.setSelectedIndex(this.get_oDataToView().size());
                this.set_bHasErrorEintrag(true);
            }
            if (this.get_cDefineAction_IF_ValueNotFound().equals(MyE2_SelectField.SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND))
            {
                this.get_oSelModel().add(""); // am ende einfügen und aktivieren           
                this.setSelectedIndex(this.get_oDataToView().size());
                this.set_bHasErrorEintrag(true);
            }
            if (this.get_cDefineAction_IF_ValueNotFound().equals(MyE2_SelectField.SHOW_EXCEPTION_IF_VALUE_NOT_FOUND))
            {
                throw new myException("mySelectField:set_ActiveValue:value not found ! (Field:"+this.EXT_DB().get_oSQLField().get_cFieldLabel()+" // Value: "+cWert);          
            }

        }
        else
        {
            this.setSelectedIndex(iRueck);
        }

        return iRueck;
    }


	
    
    /*
     * markiert einen aktuellen wert im anzeige-array
     */
    public int set_ActiveInhalt(String cWert) throws myException
    {
        this.removeErrorEintrag();
        this.cleanFromShadow();

        if (cWert == null)
            throw new myException("MyE2_DB_SelectField:set_ActiveValue:null-value not allowed !");
        
        int iRueck=this.get_oDataToView().get_PositionOfView(cWert);
        
        
        if (iRueck == -1)
        {
           	//dann erst in der schattenliste nachsehen
        	if (this.get_odataToViewShadow() != null)
        	{
        		// wenn der wert in der schattenliste vorhanden ist, dann diese zuordnung in die echte liste rueberholen und
        		// das SelectField neu bauen
        		int iPosShadow = this.get_odataToViewShadow().get_PositionOfView(cWert);
        		if (iPosShadow >=0 )
        		{
           			this.get_oDataToView().add((zuOrdnung)this.get_odataToViewShadow().get_zoAtPosition(iPosShadow).get_Copy(null));
        	        this.setModel(new DefaultListModel(this.get_oDataToView().get_ViewArray()));
        	        this.setSelectedIndex(this.get_oDataToView().size()-1);
        	        return (this.get_oDataToView().size()-1);
        		}
        	}

        	
            if (this.get_cDefineAction_IF_ValueNotFound().equals(MyE2_SelectField.SHOW_ERROR_STRING_IF_VALUE_NOT_FOUND))
            {
                this.get_oSelModel().add("@@@ERROR@@@"); // am ende einfügen und aktivieren           
                this.setSelectedIndex(this.get_oDataToView().size());
                this.set_bHasErrorEintrag(true);
            }
            if (this.get_cDefineAction_IF_ValueNotFound().equals(MyE2_SelectField.SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND))
            {
                this.get_oSelModel().add(""); // am ende einfügen und aktivieren           
                this.setSelectedIndex(this.get_oDataToView().size());
                this.set_bHasErrorEintrag(true);
            }
            if (this.get_cDefineAction_IF_ValueNotFound().equals(MyE2_SelectField.SHOW_EXCEPTION_IF_VALUE_NOT_FOUND))
            {
                throw new myException("MyE2_DB_SelectField:set_ActiveInhalt:value not found ! (Field:"+this.EXT_DB().get_oSQLField().get_cFieldLabel()+" // Value: "+cWert);            
            }
        }
        else
        {
            this.setSelectedIndex(iRueck);
        }

        return iRueck;
    }

	
	
	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.get_oDataToView() == null)
			throw new myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: SelectField not initialized !");
		
		MyE2_DB_SelectFieldWithParameter oSelField = null;
		
		try
		{
			this.get_SQL_Query_For_LIST();
			oSelField = new MyE2_DB_SelectFieldWithParameter(this.oEXTDB.get_oSQLField(), this.get_SQL_Query_For_LIST(), this.get_bThirdColumnIS_STANDARD_MARKER(), this.get_bTranslate());
			oSelField.RefreshComboboxFromSQL();
			oSelField.set_cDefineAction_IF_ValueNotFound(this.get_cDefineAction_IF_ValueNotFound());
			
//			oSelField = new MyE2_DB_SelectFieldWithParameter(this.oEXTDB.get_oSQLField(),this.get_oDataToView());
			oSelField.set_EXT_DB((MyE2EXT__DB_Component)this.oEXTDB.get_Copy(oSelField));
		}
		catch (myException ex)
		{
			throw new  myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
		oSelField.set_EXT((MyE2EXT__Component)((MyE2IF__Component)this).EXT().get_Copy(oSelField));
		oSelField.setStyle(this.getStyle());
		oSelField.set_oDataToView(this.get_oDataToView());
		oSelField.set_odataToViewShadow(this.get_odataToViewShadow());
		
		oSelField.setFont(this.getFont());
		
		
		
		
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oSelField.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oSelField.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oSelField.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oSelField.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));

		
		for(Entry<String, String> entry: this.m_htParameterList.entrySet()){
			oSelField.AddParameter(entry.getKey());
			try {
				oSelField.SetParameter(entry.getKey(), entry.getValue());
			} catch (myException e) {	}
		}
			
		
		oSelField.setFocusTraversalParticipant(this.isFocusTraversalParticipant());
		
		oSelField.setWidth(this.getWidth());

		return oSelField;
	}
	
	
	@Override
	protected void populateCombobox(String cSQLQueryForLIST,String cSQL_Query4InactiveMembers,
			boolean bThirdColumnISSTANDARDMARKER, boolean bEmtyValueInFront,
			boolean bValuesFormated, boolean btranslate) throws myException {
		if (m_htParameterList == null) return;
		
		String sSQL = this.replaceParameters();
		
		super.populateCombobox(sSQL,cSQL_Query4InactiveMembers, bThirdColumnISSTANDARDMARKER,
				bEmtyValueInFront, true, btranslate);
	}

	
}
