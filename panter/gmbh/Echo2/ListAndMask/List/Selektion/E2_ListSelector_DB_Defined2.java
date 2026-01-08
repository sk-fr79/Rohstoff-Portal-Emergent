package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Grid;
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
 * Standard-dropdown-Selektor, der aus der Datenbanktabelle JT_SELECTOR aufgebaut wird
 * @author martin
 *
 */
public class E2_ListSelector_DB_Defined2 extends E2_ListSelectorMultiDropDown2 {
	
	private 	boolean 					bHasValues = 	false;
	private	 	MyE2_SelectField   			oSelectField = 	null;
	
	private  	HashMap<String, String>  	hashMapValueQueryPairs = new HashMap<String, String>();
	
	/**
	 * 
	 * @param cMODUL_KENNER_LISTMODULE  ist der modulkenner des listenmoduls, wo der selektor wirken soll
	 * @throws myException
	 */
	public E2_ListSelector_DB_Defined2(String cMODUL_KENNER_LISTMODULE) throws myException
	{
		super();
		
		String cModulKennerBasicList = cMODUL_KENNER_LISTMODULE;
		if (S.isFull(cModulKennerBasicList))
		{
			
			//abfrage aller Datenbank-gestuetzten selektoren dieses moduls  
			RECLIST_SELECTOR rlSelect = new RECLIST_SELECTOR(
					_DB.SELECTOR$MODULKENNER+"="+bibALL.MakeSql(cModulKennerBasicList)+" AND NVL("+_DB.SELECTOR$AKTIV+",'N')='Y'", _DB.SELECTOR$USER_TEXT);
			
			if (rlSelect.get_vKeyValues().size()>0) {
				String[][] arrSelector = new String[rlSelect.get_vKeyValues().size()+1][2];
				
				arrSelector[0][0] = "*"; arrSelector[0][1] = "";
				this.hashMapValueQueryPairs.put("", "");
				
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
				this.oSelectField = new MyE2_SelectField(arrSelector,"",true);
				this.oSelectField.setToolTipText(new MyE2_String("Beliebige definierbare Selektionen",true).CTrans());
			} else {
				String[][] arrSelector = new String[1][2];
				
				arrSelector[0][0] = "*"; arrSelector[0][1] = "";
				this.hashMapValueQueryPairs.put("", "");
				this.bHasValues =false;      					//d.h. es das selektfield ist nur ein dummy
				this.oSelectField = new MyE2_SelectField(arrSelector,"",true);
				this.oSelectField.setToolTipText(new MyE2_String("Beliebige definierbare Selektionen",true).CTrans());
			}
			this.INIT(this.oSelectField, null, this.hashMapValueQueryPairs);

		}

	}

	

	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	
	private class ownBasicContainer extends E2_BasicModuleContainer {}

	
	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.get_oComponentWithoutText();
	}

	
	public boolean get_bIsFilled()
	{
		if (this.oSelectField != null && this.bHasValues)
		{
			return true;
		}
		return false;
	}
	
	
	public Component get_oComponentForSelection(int iWidth) throws myException
	{
		if (this.oSelectField != null && this.bHasValues)
		{
			this.set_extOfSelectComponentDropDown(new Extent(iWidth));
			return this.get_oComponentWithoutText();
		}
		else
		{
			return new MyE2_Label("");
		}
	}

	
	
	public Component get_oComponentForSelection(MyE2_String cText_vorne, int iWidthSelektor, int[] arrBreiteSpalten) throws myException	{
		if (cText_vorne == null || iWidthSelektor<20 || arrBreiteSpalten==null || arrBreiteSpalten.length!=2) {
			throw new myException(this,"false definition !!");
		}
		
		if (this.oSelectField != null && this.bHasValues){
			
			this.set_extOfSelectComponentDropDown(new Extent(iWidthSelektor));
			return new E2_Grid()._setSize(arrBreiteSpalten)._a(new RB_lab()._t(cText_vorne))._a(this.get_oComponentWithoutText());
		} else {
			return new MyE2_Label("");
		}
	}



}
