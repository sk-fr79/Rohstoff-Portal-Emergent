package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DB_CheckBoxGrid;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DEF_CheckBox;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DEF_Element;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATIONS_TYP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * qualifiziererklasse, um zu definieren, wofuer eine mail-adresse gebraucht wird
 * @author martin
 *
 */
public class ConditionDaughterQualifierGrid extends Q_DB_CheckBoxGrid
{
	
	//ab hier die qualifizierer fuer die nummern
	public 	static 	String     	QU_TELEFON_TYP_4_MAHNUNG_FAX = "FAX_FUER_MAHNUNG";


	
	
	private 		String 		cTableName = null;

	
	
	public ConditionDaughterQualifierGrid(SQLField oSQLField, String TableName) throws myException
	{
		super(oSQLField, false, 3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS(),null);
		this.cTableName = TableName;
	}

	
	@Override
	public Vector<Q_DEF_Element> build_vQ_DEF_Elements(MyE2IF__Component oMotherComponent) throws myException
	{
		Vector<Q_DEF_Element>  vRueck = new Vector<Q_DEF_Element>();
		
		vRueck.add(new Q_DEF_Element(ConditionDaughterQualifierGrid.QU_TELEFON_TYP_4_MAHNUNG_FAX, 
										new MyE2_String("Mahnung-Fax"), 
										new MyE2_String("Mahnung-Fax"), 
										null,
										oMotherComponent,
										new MyE2_String("Diese Nummer wird eingeblendet, wenn diesem Kunden eine Mahnung zugeschickt wird")));
		return vRueck;
	}

	
	@Override
	public String get_cTABLE_NAME() throws myException
	{
		return this.cTableName;
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			ConditionDaughterQualifierGrid oCopy = new ConditionDaughterQualifierGrid(this.EXT_DB().get_oSQLField(), this.cTableName);
			oCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCopy));
			
			//jetzt evtl. validierer und ActionAgenten kopieren
			Iterator<String>  oIter = oCopy.get_hmQ_DEF_CheckBoxen().keySet().iterator();
			while (oIter.hasNext())
			{
				String cKey = oIter.next();
				Q_DEF_CheckBox oCB_orig = this.get_hmQ_DEF_CheckBoxen().get(cKey);
				Q_DEF_CheckBox oCB_copy = oCopy.get_hmQ_DEF_CheckBoxen().get(cKey);
				
				for (XX_ActionAgent oAgent : oCB_orig.get_vActionAgents())
				{
					oCB_copy.add_oActionAgent(oAgent);
				}
			}
			return oCopy;
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}


	@Override
	public HashMap<String, Vector<XX_ActionAgent>> get_hmZusatzActionAgents4Checkboxes(Q_DB_CheckBoxGrid oGrid) throws myException
	{
		return null;
	}

	@Override
	public HashMap<String, Vector<XX_ActionValidator>> get_hmZusatzGlobalValidators(Q_DB_CheckBoxGrid oGrid) throws myException
	{
		Vector<XX_ActionValidator> vValid = new Vector<XX_ActionValidator>();
		vValid.add(new ownValidatorFaxOnlyInFaxNumbers());
		
		HashMap<String, Vector<XX_ActionValidator>>  hmRueck = new HashMap<String, Vector<XX_ActionValidator>>();
		
		hmRueck.put(ConditionDaughterQualifierGrid.QU_TELEFON_TYP_4_MAHNUNG_FAX,vValid);
		
		return hmRueck;
	}
	
	
	/*
	 * eine eigene Ueberwachung, die dafuer sorgt, dass die selektion Fax-nummer fuer Mahnung nur verwendbar ist, wenn die
	 * Telefoneintragung als Fax-nummer definiert ist
	 */
	private class ownValidatorFaxOnlyInFaxNumbers extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector  oMV = 					new MyE2_MessageVector();
			E2_ComponentMAP  	oMap = 					ConditionDaughterQualifierGrid.this.EXT().get_oComponentMAP();
			long 				lID_KommunikationsTyp = oMap.get_LActualDBValue(RECORD_KOMMUNIKATION.FIELD__ID_KOMMUNIKATIONS_TYP, new Long(-1), new Long(-1)).longValue();
			
			if (lID_KommunikationsTyp>0)
			{
				RECORD_KOMMUNIKATIONS_TYP recKommunikations_TYP = new RECORD_KOMMUNIKATIONS_TYP(lID_KommunikationsTyp);
				
				if (recKommunikations_TYP.is_IST_FAX_NUMMER_NO())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Merkmal <Fax für Mahnungen> kann nur bei Faxeinträgen gesetzt werden !")));
				}
			}
			else
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst den Kommunikationstyp setzen !")));
			}
			return oMV;
		}
		
		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}
	}


	@Override
	public void Format_CB(Q_DEF_CheckBox oFormatedCheckBox) throws myException
	{
	}


	@Override
	public String get_cCLASS_KEY()
	{
		return Q_QUALIFIER_KEYS.QUALIFIER_KEY_KOMMUNIKATION_VERWENDUNG;
	}
	
}
