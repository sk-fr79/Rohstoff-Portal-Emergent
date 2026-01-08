package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_MultiCheckboxArray;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSKLASSE_DEF;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FS_SelectMultiOptionKlasse extends E2_ListSelector_MultiCheckboxArray
{
	
	
	public FS_SelectMultiOptionKlasse() throws myException
	{
		super(new MyE2_String("Adressklassenselektor (ausführlich)"), new MyE2_String("Klasse (Kombi)"));
	}

	@Override
	public Vector<String[]> generate_Key_Value_Pairs() throws myException
	{
		Vector<String[]>  		vRueck = new Vector<String[]>();
		RECLIST_ADRESSKLASSE_DEF  	rlAKDef = new RECLIST_ADRESSKLASSE_DEF("", _DB.ADRESSKLASSE_DEF$KURZBEZEICHNUNG);
		
		for (int i=0;i<rlAKDef.get_vKeyValues().size();i++)
		{
			String[] cArr=new String[2];
			cArr[0] = rlAKDef.get(i).get_ID_ADRESSKLASSE_DEF_cUF();
			cArr[1] = rlAKDef.get(i).get_KURZBEZEICHNUNG_cUF_NN("<id_adressklasse_def>");
			vRueck.add(cArr);
		}
		
		return vRueck;
	}

	@Override
	public E2_BasicModuleContainer generate_ModuleContainer() throws myException
	{
		return new ownBasicModuleContainer();
	}

	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{
		public ownBasicModuleContainer()
		{
			super();
		}
	}

	@Override
	public String generate_ToolTipText4StartSelectButton() throws myException {
		String cRueck = "Ausgewählte Bedingungen: \n";
		
		for (E2_ListSelector_MultiCheckboxArray.ownCheckboxTriple oTrip: this.get_vCheckBoxTriple())
		{
			if (!oTrip.get_bIsIgnore())
			{
				cRueck += oTrip.get_cValue4User()+": <"+(oTrip.get_bIsYes()?"Ja":"Nein")+">"+"\n";
			}
		}
		return cRueck;
	}


	@Override
	public void fill_InfoGrid_4_Selector(MyE2_Grid oGrid4Infos_Status) throws myException {
		oGrid4Infos_Status.removeAll();
		oGrid4Infos_Status.setBackground(null);
		oGrid4Infos_Status.setColumnWidth(0, new Extent(30));
		
		int iAnzahlNotIgnore = 0;
		for (E2_ListSelector_MultiCheckboxArray.ownCheckboxTriple oTrip: this.get_vCheckBoxTriple())
		{
			if (!oTrip.get_bIsIgnore()) 	{
				iAnzahlNotIgnore++;
			}
		}

		if (iAnzahlNotIgnore>0)		{
			oGrid4Infos_Status.setBackground(new E2_ColorHelpBackground());
			oGrid4Infos_Status.add(new MyE2_Label(""+iAnzahlNotIgnore),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0,new E2_ColorHelpBackground(),1));
		} else {
			oGrid4Infos_Status.setBackground(new E2_ColorDark());
			oGrid4Infos_Status.add(new MyE2_Label("0"),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_0_0,new E2_ColorDark(),1));
		}
	}

	
	
	
	@Override
	public String get_WhereBlock() throws myException {
		
		Vector<String> vStatements = new Vector<String>();
		
		for (E2_ListSelector_MultiCheckboxArray.ownCheckboxTriple oTrip: this.get_vCheckBoxTriple())
		{
			if (!oTrip.get_bIsIgnore()) 	{
				String cStatement = "SELECT " +_DB.ADRESSKLASSE$ID_ADRESSE+" FROM "+
										bibE2.cTO()+"."+_DB.ADRESSKLASSE+" AK  WHERE AK."+_DB.ADRESSKLASSE$ID_ADRESSKLASSE_DEF+"="+oTrip.get_cKey_UF();
				if (oTrip.get_bIsYes())		{
					cStatement = "(JT_ADRESSE.ID_ADRESSE IN ("+cStatement+"))";
				} else {
					cStatement = "(JT_ADRESSE.ID_ADRESSE NOT IN ("+cStatement+"))";
				}
				
				vStatements.add(cStatement);
			}
			
		}

		if (vStatements.size()==0) 	{
			return "";
		} else if (vStatements.size()==1) 	{
			return vStatements.get(0);
		} else {
			return "("+bibALL.Concatenate(vStatements, " AND ", "1=1")+")";
		}
	}

	
	
	@Override
	public Component get_oComponentForSelection() throws myException
	{
		int[] iSpalten = {60,102,30};
		MyE2_Grid oGridRueck = new MyE2_Grid(iSpalten, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.add(new MyE2_Label(""),E2_INSETS.I_0_0_0_0);
		oGridRueck.add(this.get_oButtonStartInSelector(),E2_INSETS.I_0_0_2_0);
		oGridRueck.add(this.get_oGrid4Infos_Status(),E2_INSETS.I_0_0_5_0);
		//oGridRueck.setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));

		return oGridRueck;
	}


	
}
