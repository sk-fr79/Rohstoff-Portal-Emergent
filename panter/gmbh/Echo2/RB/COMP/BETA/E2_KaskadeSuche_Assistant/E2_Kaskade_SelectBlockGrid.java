/**
 * panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant
 * @author sebastien
 * @date 20.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class E2_Kaskade_SelectBlockGrid extends E2_Grid
{
	private String 	cInitValue 				= null;
	private String 	cQueryStringMitPlatzhalter = null;

	private int iPos 						= 0;          
	private int iActualButtonCount 			= 0;      

	private E2_Grid  		 oColumn 		= new E2_Grid();
	private MyE2_ContainerEx oContainerEx 	= new MyE2_ContainerEx();

	private IF_KaskadeButtonBuilder   	oButtonBuilder = null;
	private E2_Kaskade_SelectBlockGrid 	oPreviousColumn = null;
	private E2_Kaskade_SelectBlockGrid  oNextColumn = null;

//	private  ButtonToSaveActualSelectionValues  oButtonToSaveStatus = new ButtonToSaveActualSelectionValues();

	private E2_KaskadeSelectionSuche 			mParent;

	public E2_Kaskade_SelectBlockGrid(E2_KaskadeSelectionSuche oParent, String queryStringMitPlatzhalter,String oTitel, int iSpaltenBreite, int iGridSpaltenZahl)
	{
		super();

		this._s(1)._bo_no();
		this.setWidth(new Extent(98,Extent.PERCENT));                      //muss den ganzen platz in der ContainerEX benutzen
		this.setOrientation(E2_Grid.ORIENTATION_HORIZONTAL);

		this.iPos = iGridSpaltenZahl;
		this.mParent = oParent;

		this.cQueryStringMitPlatzhalter = queryStringMitPlatzhalter;
		this.oButtonBuilder 			= new E2_StandardButtonBuilder();
		this.oColumn._s(1).setOrientation(E2_Grid.ORIENTATION_HORIZONTAL);

		this.oContainerEx.add(this);

		E2_Grid titelRow = new E2_Grid()._bo_no()._a(new RB_lab()._fsa(-2)._bi()._t(oTitel));

		/*if (mParent.showSaveButton())
		{
			titelRow._a(this.oButtonToSaveStatus, new RB_gld()._ins(10,0,0,0));
		}*/

		this.oColumn._a(titelRow,			new RB_gld()._left_top()._ins(4));
		this.oColumn._a(this.oContainerEx,	new RB_gld()._left_top()._ins(1));

		//wenn es der erste ist, dann ist der linke = null;
		if (mParent.getSelectCols().size()>0)
		{
			this.oPreviousColumn = mParent.getSelectCols().get(mParent.getSelectCols().size()-1);
			this.oPreviousColumn.setNextColumn(this);
		}
	}

	public MyE2_ContainerEx get_oContainerEx() 
	{
		return oContainerEx;
	}


	public void reset_selection()
	{
		this._clear();
		this.cInitValue=null;
		this.iActualButtonCount=0;
	}

	/**
	 * 
	 * @param cWert
	 * Baut die Column und die Buttons neu auf
	 * @throws myException
	 */
	public void initialize_Column(String cWert) throws myException
	{
		
		this.cInitValue = cWert; 

		String cQueryMitValues = this.cQueryStringMitPlatzhalter;

		for (int i=this.iPos;i>=0;i--)
		{
			cQueryMitValues = bibALL.ReplaceTeilString(cQueryMitValues, "#WERT"+i+"#", mParent.get_vSelectCols().get(i).getInitialValue());
		}

		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cQueryMitValues, "");

		if (cErgebnis == null) {
			throw new myException(this,"FEHLER : cac73169-352d-4b14-ba65-4e1a3b303dfe : Error building Row: ("+bibALL.ReplaceTeilString(cQueryMitValues, "#WERT#", S.NN(cWert))+")");
		}

		//jetzt den Vector mit den Buttons aufbauen
		this._clear();

		this.iActualButtonCount = cErgebnis.length;

		for (int i=0;i<cErgebnis.length;i++)
		{
			this.oButtonBuilder.build_Buttons_In_Grid(mParent, cErgebnis[i], this);
		}

		//die selectionRow neu aufbauen, je nach situation
		this.mParent._clear();
		DEBUG.System_println("**** Position: "+ "[*" + (this.hasPreviousColumn()) + "]" + "  [" + iPos + "]  "+"[" + (this.hasNextColumn()) + "*]");

		
		if(this.hasPreviousColumn()) {
			if(this.getPreviousColumn().hasPreviousColumn()) {
				this.mParent._a(this.getPreviousColumn().getPreviousColumn().getColumn());
			}
			this.mParent._a(this.getPreviousColumn().getColumn());
		}
		this.mParent._a(this.getColumn());
		

		//alle folgenden SelectColumn's leermachen
		for (int i=this.getPositionInVector()+1; i<mParent.get_vSelectCols().size() ; i++){
			this.mParent.get_vSelectCols().get(i).reset_selection();
		}
		
		//jetzt den button in der vorgaengerliste markieren
		if (this.iPos>0)
		{
			E2_Kaskade_SelectBlockGrid oSelVorgaenger = this.oPreviousColumn;

			//alle buttons auf normalen hintergrund setzen
			VEK<Component>  arrayComponents = new VEK<Component>()._a(oSelVorgaenger.getComponents());
			
			for (Component comp: arrayComponents){
				if (comp instanceof E2_Button){
					E2_Button oButt = (E2_Button)comp;
					if (oButt.EXT().get_C_MERKMAL().equals(this.cInitValue)){
						((E2_Button)comp)._backDDark();
					}
					else{
						((E2_Button)comp)._bc(new E2_ColorBase());
					}
				}
			}
		}
	}

	public E2_Kaskade_SelectBlockGrid getPreviousColumn(){
		return this.oPreviousColumn;
	}

	public void setPreviousColumn(E2_Kaskade_SelectBlockGrid columnLeft){
		this.oPreviousColumn = columnLeft;
	}

	public E2_Kaskade_SelectBlockGrid getNextColumn(){
		return this.oNextColumn;
	}

	public void setNextColumn(E2_Kaskade_SelectBlockGrid columnRight){
		this.oNextColumn = columnRight;
	}

	public boolean isFirstColumn(){
		return (this.oPreviousColumn==null);
	}

	public boolean hasPreviousColumn() {
		return !(this.oPreviousColumn==null);
	}
	
	public boolean hasNextColumn() {
		return !(this.oNextColumn==null);
	}

	public boolean isLastColumn(){
		return (this.oNextColumn==null);
	}

	public E2_Grid getColumn(){
		return oColumn;
	}

	public String getInitialValue(){
		return cInitValue;
	}

	public int getPositionInVector(){
		return this.iPos;
	}

	public void setPositionInVector(int position){
		this.iPos = position;
	}


	public int get_iActualButtonCount(){
		return iActualButtonCount;
	}



	/*private class ButtonToSaveActualSelectionValues extends E2_Button{
		public ButtonToSaveActualSelectionValues()
		{
			super();
			this._image("save_status.png")._ttt(S.ms("Auswahl bis zu dieser Spalte speichern !"))._aaa(()->saveStatus());
		}
	}


	private void saveStatus() throws myException {
		Vector<String> vValuesToThisRow = new Vector<String>();

		E2_Kaskade_SelectBlockGrid	oThis = E2_Kaskade_SelectBlockGrid.this;    
		E2_KaskadeSelectionSuche 	OTHIS = oThis.mParent;   

		for (int i=0;i<OTHIS.get_vSelectCols().size();i++)
		{
			vValuesToThisRow.add(OTHIS.get_vSelectCols().get(i).getInitialValue());
			if (OTHIS.get_vSelectCols().get(i)==oThis)
			{
				break;
			}
		}

		if (new E2_UserSettingPreselection_MultiSelectRow_NG().storeSelector(OTHIS,vValuesToThisRow)>0)
		{
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Selektionskette gespeichert: ",true,bibALL.Concatenate(vValuesToThisRow, " - ", "-"),false)));
		}
	}*/
}