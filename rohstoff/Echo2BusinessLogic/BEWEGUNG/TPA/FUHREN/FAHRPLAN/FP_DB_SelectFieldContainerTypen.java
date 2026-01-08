package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FP_DB_SelectFieldContainerTypen extends MyE2_DB_SelectField  implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{
	public static String MARKER = "---> ";
	
	public FP_DB_SelectFieldContainerTypen(SQLField osqlField) throws myException
	{
		super(osqlField, "SELECT KURZBEZEICHNUNG,ID_CONTAINERTYP FROM "+bibE2.cTO()+".JT_CONTAINERTYP WHERE nvl(AKTIV,'N') = 'Y' ORDER BY KURZBEZEICHNUNG", 
				false,
				false);
		
		
		// Shadow-Daten laden
		String sqlShadow = "SELECT KURZBEZEICHNUNG || ' (*Inaktiv*)',ID_CONTAINERTYP FROM "+bibE2.cTO()+".JT_CONTAINERTYP WHERE nvl(AKTIV,'N') = 'N' ORDER BY KURZBEZEICHNUNG";
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cResultShadow = oDB.EinzelAbfrageInArrayFormatiert(sqlShadow,"");
		bibALL.destroy_myDBToolBox(oDB);
		this.set_odataToViewShadow(new dataToView(cResultShadow, false, bibE2.get_CurrSession()));
		
		this.setWidth(new Extent(214));

	}

	
	public FP_DB_SelectFieldContainerTypen(SQLField osqlField, dataToView oDV) throws myException
	{
		super(osqlField, oDV);
	}

	
	public void UnmarkAll() throws myException
	{
		dataToView oDV = this.get_oDataToView();
		
		int iPosActual = this.getSelectedIndex();
		
		for (int i=0;i<oDV.size();i++)
		{
			String cView = oDV.get_cViewAtPosition(i);
			if (cView.startsWith(MARKER))
			{
				cView = cView.substring(MARKER.length());
				oDV.set_cViewAtPosition(cView,i);
			}
		}
		this.set_oDataToViewForDatabase(oDV);
		
		if (iPosActual>=0)
			this.setSelectedIndex(iPosActual);
	}
	
	
	/*
	 * eine liste mit bestimmten werten hervorheben
	 */
	public void MarkValues(Vector<String> vValuesUnformated) throws myException
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
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.get_oDataToView() == null)
			throw new myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: SelectField not initialized !");
		
		FP_DB_SelectFieldContainerTypen oSelField = null;
		
		try
		{
			oSelField = new FP_DB_SelectFieldContainerTypen(this.EXT_DB().get_oSQLField(),this.get_oDataToView());
			oSelField.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oSelField));
			oSelField.set_EXT((MyE2EXT__Component)((MyE2IF__Component)this).EXT().get_Copy(oSelField));
			oSelField.setStyle(this.getStyle());
			oSelField.set_oDataToView((dataToView)this.get_oDataToView().get_Copy(null));
		}
		catch (myException ex)
		{
			throw new  myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: "+ex.get_ErrorMessage().get_cMessage().CTrans());
		}
		
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
		
		oSelField.setWidth(this.getWidth());

		return oSelField;
	}

	
}
