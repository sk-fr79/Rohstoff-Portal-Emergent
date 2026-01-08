package panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant;

import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class E2_KaskadeSelectionSuche extends E2_Grid
{
	public static E2_ResourceIcon ICON_FOR_SEARCHBUTTON = E2_ResourceIcon.get_RI("suchkaskade.png");


	private LinkedHashMap<E2_Kaskade_SelectBlockGrid, String> hmSelectCols = new LinkedHashMap<E2_Kaskade_SelectBlockGrid, String>(); 

	private VEK<E2_Kaskade_SelectBlockGrid> vSelectCols = new VEK<E2_Kaskade_SelectBlockGrid>(); 

	private String    			 cWertFuerAusstieg = null;
	private XX_ActionAgent       oAgent_4_LastSelection = null;

	private boolean 			bShowSaveButton = false;
	private boolean    			bAutoSizeHeight = true;


	public E2_KaskadeSelectionSuche() throws myException
	{
		super();
		this._bo_no();
	}

	public void set_ActionAgentFuerLastSelection(XX_ActionAgent actionAgentFuerLastSelection)
	{
		oAgent_4_LastSelection = actionAgentFuerLastSelection;
	}


	public void START(String cStartWert, boolean lookForStoredValues)  throws myException
	{
		if (!lookForStoredValues)
		{
			this._s(vSelectCols.size());
			this.vSelectCols.get(0).initialize_Column(cStartWert);
		}
		else
		{
			/*boolean bLookWasDone = false;

			/*
			 * nachsehen, ob eine startreihenfolge gepeichert wurde
			 */
			/*try
			{
				if (new E2_UserSettingPreselection_MultiSelectRow_NG().prepareSelector(this)){
					bLookWasDone = true;
				}
			} catch (myException e){
				e.printStackTrace();
				bibMSG.MV()._addAlarm("Fehler beim Laden der Selektionsvorgaben !");
				bLookWasDone = false;
			}*/

			//			if (!bLookWasDone){
			this._s(vSelectCols.size());
			this.vSelectCols.get(0).initialize_Column(cStartWert);
			//			}
		}
	}

	public void START(Vector<String> vValues)  throws myException
	{
		for (int i=0;i<vValues.size();i++)
		{
			this._s(vSelectCols.size());
			this.vSelectCols.get(i).initialize_Column(vValues.get(i));
			if (this.vSelectCols.get(i).get_iActualButtonCount()==0)
			{
				break;
			}
		}
	}



	public VEK<E2_Kaskade_SelectBlockGrid> get_vSelectCols()
	{
		return this.vSelectCols;
	}

	public E2_KaskadeSelectionSuche _addCriteria(String queryStringMitPlatzhalter,String StringTitel, int iSpaltenBreite, int iGridSpaltenZahl ) throws myException
	{

		//die erste suche darf ohne #WERT# kommen
		if (this.vSelectCols.size()>0)
		{
			if (queryStringMitPlatzhalter.indexOf("#WERT")==-1)
			{
				throw new myException(this,"Definition MUST contain #WERT1 ... n !");
			}
		}

		E2_Kaskade_SelectBlockGrid oSelCol = new E2_Kaskade_SelectBlockGrid(this, queryStringMitPlatzhalter,StringTitel,iSpaltenBreite, iGridSpaltenZahl );
		oSelCol.setPositionInVector(iGridSpaltenZahl);

		this.vSelectCols.add(oSelCol);

		return this;
	}


	public String get_cWertFuerAusstieg()
	{
		return cWertFuerAusstieg;
	}

	public void set_cWertFuerAusstieg(String cWert)
	{
		this.cWertFuerAusstieg=cWert;
	}

	public XX_ActionAgent getLastSelectionAgent() {
		return  oAgent_4_LastSelection;
	}

	public void setLastSelectionAgent(XX_ActionAgent oAgent_4_LastSelection) {
		this.oAgent_4_LastSelection = oAgent_4_LastSelection;
	}

	public VEK<E2_Kaskade_SelectBlockGrid> getSelectCols() {
		return vSelectCols;
	}

	/*public String getWertFuerAusstieg() {
		return cWertFuerAusstieg;
	}*/

	public boolean showSaveButton() {
		return bShowSaveButton;
	}

	public boolean isAutoSizeHeight() {
		return bAutoSizeHeight;
	}

	public void setHeightInPx(int iHeight)
	{
		for (int i=0;i<this.vSelectCols.size();i++)
		{
			this.vSelectCols.get(i).get_oContainerEx().setHeight(new Extent(iHeight));
		}
	}
}
