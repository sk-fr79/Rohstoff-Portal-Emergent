package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_SELECTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SELECTOR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.exceptions.myException;

/**
 * Standard-dropdown-Selektor, der aus der Datenbanktabelle J_SELECTOR aufgebaut wird
 * @author martin
 *
 */
public class E2_ListSelector_DB_Defined extends XX_ListSelektor
{
	
	private 	boolean 					bHasValues = 	false;
	private	 	MyE2_SelectField   			oSelectField = 	null;
	
	private  	HashMap<String, String>  	hashMapValueQueryPairs = new HashMap<String, String>();
	
	/**
	 * 
	 * @param cMODUL_KENNER_LISTMODULE  ist der modulkenner des listenmoduls, wo der selektor wirken soll
	 * @throws myException
	 */
	public E2_ListSelector_DB_Defined(String cMODUL_KENNER_LISTMODULE) throws myException
	{
		super();
		
		String cModulKennerBasicList = cMODUL_KENNER_LISTMODULE;
		if (S.isFull(cModulKennerBasicList))
		{
			
			//abfrage aller Datenbank-gestuetzten selektoren dieses moduls  
			RECLIST_SELECTOR rlSelect = new RECLIST_SELECTOR(
					_DB.SELECTOR$MODULKENNER+"="+bibALL.MakeSql(cModulKennerBasicList)+" AND NVL("+_DB.SELECTOR$AKTIV+",'N')='Y'", _DB.SELECTOR$USER_TEXT);
			
			if (rlSelect.get_vKeyValues().size()>0)
			{
				String[][] arrSelector = new String[rlSelect.get_vKeyValues().size()+1][2];
				
				arrSelector[0][0] = "*"; arrSelector[0][1] = "ALLE";
				this.hashMapValueQueryPairs.put("ALLE", "");
				
				int i=1;
				//2014-07-02: sortierung nach Feldnamen
				//for (RECORD_SELECTOR  recSEL: rlSelect.values())
				for (int l=0;l<rlSelect.get_vKeyValues().size();l++)
				{
					RECORD_SELECTOR  recSEL = rlSelect.get(l);
					
					//ersetzen der Abfragevariable
					String cUserTextimDropDown = recSEL.get_USER_TEXT_cUF_NN("<??>");
					String cDatenbankQuery = recSEL.get_WHEREBLOCK_cF_NN("1=1");
					
					//jetzt beide auf interne variable-ersetzungen durchsuchen
					cUserTextimDropDown = bibReplacer.ReplaceSysvariablesInStrings(cUserTextimDropDown);
					cDatenbankQuery = bibReplacer.ReplaceSysvariablesInStrings(cDatenbankQuery);
					
					
					arrSelector[i][0]=cUserTextimDropDown; arrSelector[i][1]="NUM"+i;
					this.hashMapValueQueryPairs.put(arrSelector[i][1], cDatenbankQuery);
					i++;
				}
				
				this.bHasValues =true;      //d.h. es gibt selektoren und damit muss der selektor eingeblendet werden
				this.oSelectField = new MyE2_SelectField(arrSelector,"ALLE",true);
				this.oSelectField.setToolTipText(new MyE2_String("Beliebige definierbare Selektionen",true).CTrans());
				
				this.set_oNeutralisator(new ownNeutralisator());
			}
		}

		
		
	}

	
	/**
	 * 
	 * @param cMODUL_KENNER_LISTMODULE  ist der modulkenner des listenmoduls, wo der selektor wirken soll
	 * @param width_selektor   breite des (falls vorhanden) selektors
	 * @throws myException
	 */
	public E2_ListSelector_DB_Defined(String cMODUL_KENNER_LISTMODULE, int width_selektor) throws myException 	{
		this(cMODUL_KENNER_LISTMODULE);
		if (this.oSelectField != null) {
			this.oSelectField.setWidth(new Extent(width_selektor));
		}
	}

	


	@Override
	public String get_WhereBlock() throws myException
	{
		if (this.oSelectField != null && this.bHasValues)
		{
			return this.hashMapValueQueryPairs.get(this.oSelectField.get_ActualWert());
		}
		else
		{
			return "";
		}
	}


	@Override
	public Component get_oComponentForSelection() throws myException
	{
		if (this.oSelectField != null && this.bHasValues)
		{
			return oSelectField;
		}
		else
		{
			return new MyE2_Label("");
		}
	}

	
	
	public Component get_oComponentForSelection(int iWidth) throws myException
	{
		if (this.oSelectField != null && this.bHasValues)
		{
			this.oSelectField.setWidth(new Extent(iWidth));
			return oSelectField;
		}
		else
		{
			return new MyE2_Label("");
		}
	}


	
	public boolean get_bIsFilled()
	{
		if (this.oSelectField != null && this.bHasValues)
		{
			return true;
		}
		return false;
	}
	
	
	
	public Component get_oComponentForSelection(MyE2_String cText_vorne, int iWidthSelektor) throws myException
	{
		if (this.oSelectField != null && this.bHasValues)
		{
			this.oSelectField.setWidth(new Extent(iWidthSelektor));
			
			if (cText_vorne != null)
			{
				MyE2_Grid oGridRueck = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				oGridRueck.add(new MyE2_Label(cText_vorne),E2_INSETS.I_0_0_5_0);
				oGridRueck.add(this.oSelectField,E2_INSETS.I_0_0_0_0);
				return oGridRueck;
			}
			else
			{
				return oSelectField;
			}
		}
		else
		{
			return new MyE2_Label("");
		}
	}


	
	
	public Component get_oComponentForSelection(MyE2_String cText_vorne, int iWidthSelektor, int[] arrBreiteSpalten) throws myException
	{
		if (cText_vorne == null || iWidthSelektor<20 || arrBreiteSpalten==null || arrBreiteSpalten.length!=2) {
			throw new myException(this,"false definition !!");
		}
		
		if (this.oSelectField != null && this.bHasValues)
		{
			this.oSelectField.setWidth(new Extent(iWidthSelektor));
			
			MyE2_Grid oGridRueck = new MyE2_Grid(arrBreiteSpalten,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGridRueck.add(new MyE2_Label(cText_vorne),E2_INSETS.I_0_0_5_0);
			oGridRueck.add(this.oSelectField,E2_INSETS.I_0_0_0_0);
			return oGridRueck;
		}
		else
		{
			return new MyE2_Label("");
		}
	}


	
	

	@Override
	public Component get_oComponentWithoutText() throws myException
	{
		return this.get_oComponentForSelection();
	}


	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
	{
		if (this.oSelectField != null && this.bHasValues)
		{
			this.oSelectField.add_oActionAgent(oAgent);
		}
	}


	@Override
	public void doInternalCheck()
	{
	}

	
	
	//2012-02-14: neutralisatoren
	private class ownNeutralisator extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			if (E2_ListSelector_DB_Defined.this.oSelectField != null && E2_ListSelector_DB_Defined.this.bHasValues)
			{
				(E2_ListSelector_DB_Defined.this.oSelectField).set_ActiveValue("ALLE");
			}
		}
	}

	
}
