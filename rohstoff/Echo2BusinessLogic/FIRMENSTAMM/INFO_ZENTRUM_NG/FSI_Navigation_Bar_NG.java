package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FSI_Navigation_Bar_NG extends MyE2_Grid{


	private MyE2_Button 		previousButton;
	private MyE2_Button 		nextButton;
	private MyE2_Button 		firstButton;
	private MyE2_Button 		lastButton;
	private MyE2_Button 		sprungToVkButton;

	private MyE2_SelectField	seiteLaengeSelectField;
	private MyE2_SelectField	pageInd;
	private MyE2_Label			pageTotals;

	private RB_lab				nbOfFuhre;

	private int 				iDisplayedItemsPerPage = 20;

	private INFO_BLOCK_Fuhren_NG parent;

	private Vector<FSI_storageObject> vectorId = new Vector<>();

	private String[] seiteLaengListe = {"5","10","20","30","40","50","70","100", "150","200", "Alle (lange Wartezeit)"};

	private int firstVk;

	public FSI_Navigation_Bar_NG(INFO_BLOCK_Fuhren_NG p_parent) throws myException {
		super();
		this._s(11);
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.setLayoutData(MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,2,2,2)));


		this.parent = p_parent;

		_build();
	}

	public FSI_Navigation_Bar_NG(INFO_BLOCK_Fuhren_NG p_parent, int item_displayed_per_page) throws myException{
		super();
		this._s(11);
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER());
		this.setLayoutData(MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,2,2,2)));

		this.parent = p_parent;
		this.iDisplayedItemsPerPage  =item_displayed_per_page;

		_build();
	}

	public void init_navigationBar_with_idsList(String[][] abfrage_ergebnis_tabelle) throws myException{
		this.vectorId = new Vector<>();

		int countEk =0;
		

		for(String[] row: abfrage_ergebnis_tabelle){

			String id = row[0];

			boolean is_ek_fuhre =false;

			if(row[2].equals("EK")){
				is_ek_fuhre=true;
				countEk++;

			}else if(row[2].equals("VK")){
				is_ek_fuhre=false;
			}

			FSI_storageObject storageObject = null;

			if(!row[1].equals("0")){
				storageObject = new FSI_storageObject(row[0], row[1], is_ek_fuhre);
			}else{
				storageObject = new FSI_storageObject(id, "0", is_ek_fuhre);
			}

			vectorId.add(storageObject);
		}

		nbOfFuhre._t(bibALL.convertID2FormattedID(""+vectorId.size()));

		if(parent.isEkChecked() && parent.isVkChecked() && countEk>0){
			firstVk = countEk+1;
		}else firstVk = 0;
		if(! (this.seiteLaengeSelectField.getModel().size()>0) ){
			this.seiteLaengeSelectField.set_ListenInhalt(seiteLaengListe, false);
		}
		this.seiteLaengeSelectField.setWidth(new Extent(150));
		if(iDisplayedItemsPerPage>200){
			iDisplayedItemsPerPage = 30;
		}

		if(iDisplayedItemsPerPage == vectorId.size()){
			iDisplayedItemsPerPage = 30;
		}

		if(bibVECTOR.get_VectorFromArray(seiteLaengListe).contains(Integer.toString(iDisplayedItemsPerPage))){
			this.seiteLaengeSelectField.set_ActiveInhalt(Integer.toString(iDisplayedItemsPerPage));
		}else {
			this.seiteLaengeSelectField.set_ActiveInhalt("30");
			iDisplayedItemsPerPage = 30;
		}

		fillSeiteComboBox();
	}
	

	private void fillSeiteComboBox() throws myException {
		int it = 0; 
		int reste = 0;
		int total = 0;

		if(iDisplayedItemsPerPage==0){
			it = 1;
		}else{
			it = vectorId.size()/iDisplayedItemsPerPage;
			reste = vectorId.size()%iDisplayedItemsPerPage;
		}

		String[] valueMap;

		if(reste == 0){
			valueMap = new String[it];
			for(int i=0;i<it;i++){
				valueMap[i]=""+(i+1);
			}
			total = it;
		}else{
			valueMap = new String[it+1];
			for(int i=0;i<it;i++){
				valueMap[i]=""+(i+1);
			}
			valueMap[it] = ""+ (it+1);
			total = it+1;
		}

		this.pageTotals.setText("/"+(total));
		this.pageTotals.EXT().set_C_MERKMAL(""+(it+1));
		this.pageInd.set_ListenInhalt(valueMap, false);
	}

	public void setVisible(boolean isVisible){
		this.removeAll();

		this.add(this.firstButton,1,1,						E2_INSETS.I(4,2,4,2), E2_ALIGN.LEFT_MID);
		this.add(this.previousButton,1,1,					E2_INSETS.I(4,2,4,2), E2_ALIGN.LEFT_MID);
		this.add(new RB_lab("Seite"),1,1,					E2_INSETS.I(4,2,4,2), E2_ALIGN.CENTER_MID);
		this.add(this.pageInd,1,1, 							E2_INSETS.I(4,2,4,2), E2_ALIGN.CENTER_MID);
		this.add(this.pageTotals,1,1, 						E2_INSETS.I(4,2,4,2), E2_ALIGN.LEFT_MID); 
		this.add(this.nextButton,1,1, 						E2_INSETS.I(4,2,4,2), E2_ALIGN.RIGHT_MID);
		this.add(this.lastButton,1,1, 						E2_INSETS.I(4,2,4,2), E2_ALIGN.RIGHT_MID);
		this.add(new RB_lab("Anz. Zeilen (Datensätze):"),1,1,	E2_INSETS.I(4,2,4,2), E2_ALIGN.RIGHT_MID);
		this.add(nbOfFuhre, 1,1,E2_INSETS.I(4,2,4,2), E2_ALIGN.LEFT_MID);
		this.add(this.sprungToVkButton,1,1,					E2_INSETS.I(50,2,50,2),E2_ALIGN.CENTER_MID);

		this.add(new RB_lab("Seitenlänge:"),1 ,1,			E2_INSETS.I(4,2,4,2),E2_ALIGN.RIGHT_MID);
		this.add(this.seiteLaengeSelectField,1,1,			E2_INSETS.I(4,2,4,2), E2_ALIGN.LEFT_MID);
		this.set_Spalten(new int[] {30,30,40,75,40,30,30,300,90,300,90,300});

	}


	private void _build() throws myException{

		this.previousButton = new MyE2_Button(E2_ResourceIcon.get_RI("left.png"), 	E2_ResourceIcon.get_RI("left.png")); 
		this.previousButton.add_oActionAgent(new ownPreviousAction());
		this.previousButton.set_bEnabled_For_Edit(false);

		this.nextButton 	= new MyE2_Button(E2_ResourceIcon.get_RI("right.png"), 	E2_ResourceIcon.get_RI("right.png")) ;
		this.nextButton.add_oActionAgent(new ownNextAction());

		this.firstButton = new MyE2_Button(E2_ResourceIcon.get_RI("left_end.png"), 	E2_ResourceIcon.get_RI("left_end.png"));
		this.firstButton.add_oActionAgent(new ownFirstAction());
		this.firstButton.set_bEnabled_For_Edit(false);

		this.lastButton = new MyE2_Button(E2_ResourceIcon.get_RI("right_end.png"), 	E2_ResourceIcon.get_RI("right_end.png"));
		this.lastButton.add_oActionAgent(new ownLastAction());

		this.pageTotals		= new MyE2_Label("");
		this.pageInd		= new MyE2_SelectField();
		this.pageInd.add_oActionAgent(new ownSelectAction());		

		this.sprungToVkButton = new MyE2_Button("Sprung zur ersten VK-Fuhre", null, new goToVerkaufenInfos_actionAgent());

		this.seiteLaengeSelectField = new MyE2_SelectField();
		this.seiteLaengeSelectField.add_oActionAgent(new seiteLaengeActionAgent());

		this.nbOfFuhre = new RB_lab();

		setVisible(true);
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
		if(!enabled){

			this.nbOfFuhre._t("0");
			this.pageInd.set_ListenInhalt(new String[]{"1"}, false);
			this.pageInd.set_ActiveInhalt_or_FirstInhalt("1");
			this.pageTotals.setText("/1");

			this.sprungToVkButton.set_bEnabled_For_Edit(enabled);

			this.seiteLaengeSelectField.set_ListenInhalt(seiteLaengListe, false);
			this.seiteLaengeSelectField.set_ActiveInhalt("30");
		}else{
			if(pageInd.getModel().size()>1){
				nextButton.set_bEnabled_For_Edit(true);
				lastButton.set_bEnabled_For_Edit(true);
				previousButton.set_bEnabled_For_Edit(true);
				firstButton.set_bEnabled_For_Edit(true);

				this.pageInd.set_bEnabled_For_Edit(true);
				this.seiteLaengeSelectField.set_bEnabled_For_Edit(true);
			}
		}
	}

	public void selectRecordFromTo_ids(int from, int to) throws myException{

		int end = 0;

		if(iDisplayedItemsPerPage == 0){
			end = vectorId.size();
			pageInd.set_bEnabled_For_Edit(true);
		}else{
			end = to;
			pageInd.set_bEnabled_For_Edit(true);
		}

		int start = from;

		if(firstVk==0){
			sprungToVkButton.set_bEnabled_For_Edit(false);
		}else{
			sprungToVkButton.set_bEnabled_For_Edit(true);
		}

		if(pageInd.get_ActualView().equals(pageTotals.EXT().get_C_MERKMAL())){
			end = vectorId.size();
			lastButton.set_bEnabled_For_Edit(false);
			nextButton.set_bEnabled_For_Edit(false);
			firstButton.set_bEnabled_For_Edit(true);
			previousButton.set_bEnabled_For_Edit(true);
		}else if(vectorId.size()<to && iDisplayedItemsPerPage > 0){
			end = vectorId.size();
			lastButton.set_bEnabled_For_Edit(false);
			nextButton.set_bEnabled_For_Edit(false);
			firstButton.set_bEnabled_For_Edit(false);
			previousButton.set_bEnabled_For_Edit(false);
		}else if(vectorId.size()==to && iDisplayedItemsPerPage == 0){
			lastButton.set_bEnabled_For_Edit(false);
			nextButton.set_bEnabled_For_Edit(false);
			firstButton.set_bEnabled_For_Edit(false);
			previousButton.set_bEnabled_For_Edit(false);
			sprungToVkButton.set_bEnabled_For_Edit(false);
		}else{
			firstButton.set_bEnabled_For_Edit(true);
			previousButton.set_bEnabled_For_Edit(true);
			lastButton.set_bEnabled_For_Edit(true);
			nextButton.set_bEnabled_For_Edit(true);
		}

		if(vectorId.size()==to){
			lastButton.set_bEnabled_For_Edit(false);
			nextButton.set_bEnabled_For_Edit(false);
			firstButton.set_bEnabled_For_Edit(true);
			previousButton.set_bEnabled_For_Edit(true);
		}

		if(pageInd.get_ActualWert().equals("1")){
			lastButton.set_bEnabled_For_Edit(true);
			nextButton.set_bEnabled_For_Edit(true);
			firstButton.set_bEnabled_For_Edit(false);
			previousButton.set_bEnabled_For_Edit(false);
		}

		Vector<FSI_storageObject> vIdDisplayed = new Vector<>();

		for(int i=start;i<end;i++){
//			String id = vectorId.get(i).getId();
//			boolean[] annex = new boolean[]{vectorId.get(i).isEk(), vectorId.get(i).isOrt()};
//			
			
			vIdDisplayed.add(new FSI_storageObject(vectorId.get(i).getId(), vectorId.get(i).getIdOrt(), vectorId.get(i).isEk()));
		}
//			FSI_RECORD_VPOS_TPA_FUHRE_own record = null;
//
//			if(annex[1]){
//
//				id = vectorId.get(i).getIdOrt();
//
//				RECORD_VPOS_TPA_FUHRE_ORT sRecord = new RECORD_VPOS_TPA_FUHRE_ORT(id); 
//				RECORD_VPOS_TPA_FUHRE anrRec = new RECORD_VPOS_TPA_FUHRE(sRecord.get_ID_VPOS_TPA_FUHRE_cUF());
//
//				String anr = "";
//				if(annex[0]){
//					anr=anrRec.get_ANR1_EK_cUF_NN("")+"-"+anrRec.get_ANR2_EK_cUF_NN("");
//				}else{
//					anr=anrRec.get_ANR1_VK_cUF_NN("")+"-"+anrRec.get_ANR2_VK_cUF_NN("");
//				}
//				record = new FSI_RECORD_VPOS_TPA_FUHRE_own(
//						sRecord,
//						anr, 
//						annex[0]);
//
//			}else{
//				RECORD_VPOS_TPA_FUHRE srecord = new RECORD_VPOS_TPA_FUHRE(id);
//				String anr = "";
//				if(annex[0]){
//					anr=srecord.get_ANR1_EK_cUF_NN("")+"-"+srecord.get_ANR2_EK_cUF_NN("");
//				}else{
//					anr=srecord.get_ANR1_VK_cUF_NN("")+"-"+srecord.get_ANR2_VK_cUF_NN("");
//				}
//				record = new FSI_RECORD_VPOS_TPA_FUHRE_own(
//						srecord, 
//						anr, 
//						annex[0]);
//
//			}
//
//			if(record != null){
//				returnedVect.add(record);
//			}
//
//		}
		vIdDisplayed.size();
		parent._grid_aufbau(vIdDisplayed, vectorId);

	}


	public int getDisplayedItemsPerPage() {
		return iDisplayedItemsPerPage;
	}


	public String getSeiteLaenge() throws myException{

		if(this.parent.isLeerListe()){
			return "30";
		}

		String rawSeiteLaenge 	= seiteLaengeSelectField.get_ActualWert();
		String returnValue 		= "";

		if(rawSeiteLaenge.equals("Alle (lange Wartezeit)")){
			return "30";
		}

		switch(Integer.parseInt(rawSeiteLaenge)){
		case 5:
			returnValue="5";
			break;
		case 10:
			returnValue = "10";
			break;
		case 20:
			returnValue = "20";
			break;
		case 30:
			returnValue = "30";
			break;
		default:
			returnValue = "30";
		}
		return returnValue;
	}

	public void addActionAgent_onSeiteLaengeComboBox(XX_ActionAgent agent){
		this.seiteLaengeSelectField.add_oActionAgent(agent);
	}

	public void setSeiteLaenge(String laenge) throws myException{
		iDisplayedItemsPerPage = Integer.parseInt(laenge);
	}

	private class ownSelectAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			int start = 0;
			int end = start+iDisplayedItemsPerPage;
			int pageIndex = Integer.parseInt(pageInd.get_ActualWert());

			int maxVectorSize = (vectorId.size()/iDisplayedItemsPerPage)+1;

			if(pageIndex == maxVectorSize){
				start = (pageInd.get_oSelModel().size()-1)*iDisplayedItemsPerPage;
				end = vectorId.size();
			}else{
				start = (Integer.parseInt(pageInd.get_ActualWert())*iDisplayedItemsPerPage-(iDisplayedItemsPerPage));
				end = start + iDisplayedItemsPerPage;
			}

			if(pageIndex==maxVectorSize){
				nextButton.set_bEnabled_For_Edit(false);
				lastButton.set_bEnabled_For_Edit(false);
				previousButton.set_bEnabled_For_Edit(true);
				firstButton.set_bEnabled_For_Edit(true);	
			}else if(start==1){
				nextButton.set_bEnabled_For_Edit(true);
				lastButton.set_bEnabled_For_Edit(true);
				previousButton.set_bEnabled_For_Edit(false);
				firstButton.set_bEnabled_For_Edit(false);	
			}else{
				nextButton.set_bEnabled_For_Edit(true);
				lastButton.set_bEnabled_For_Edit(true);
				previousButton.set_bEnabled_For_Edit(true);
				firstButton.set_bEnabled_For_Edit(true);
			}

			selectRecordFromTo_ids(start, end);
		}
	}

	private class ownPreviousAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			int pageIndex = Integer.parseInt(pageInd.get_ActualWert())-1; 

			int start = (pageIndex*iDisplayedItemsPerPage)-iDisplayedItemsPerPage;
			int end = pageIndex*iDisplayedItemsPerPage;

			int maxVectorSize = (vectorId.size()/iDisplayedItemsPerPage)+1;

			if(pageInd.getModel().size()>1){
				
				pageInd.set_ActiveInhalt(""+(pageIndex));
			}else{
				previousButton.set_bEnabled_For_Edit(false);
				firstButton.set_bEnabled_For_Edit(false);
				nextButton.set_bEnabled_For_Edit(false);
				lastButton.set_bEnabled_For_Edit(false);
			}

			if(pageIndex >= maxVectorSize){
				nextButton.set_bEnabled_For_Edit(false);
				lastButton.set_bEnabled_For_Edit(false);
				end = vectorId.size();
				start = (pageInd.get_oSelModel().size()-1)*iDisplayedItemsPerPage;
			}else{
				nextButton.set_bEnabled_For_Edit(true);
				lastButton.set_bEnabled_For_Edit(true);
				start = end - iDisplayedItemsPerPage;
			}


			if(pageIndex>0){
				previousButton.set_bEnabled_For_Edit(true);
				firstButton.set_bEnabled_For_Edit(true);
				selectRecordFromTo_ids(start, end);
			}else{
				previousButton.set_bEnabled_For_Edit(false);
				firstButton.set_bEnabled_For_Edit(false);
			}


		}

	}

	private class ownNextAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			int start = new Integer(pageInd.get_ActualWert())*iDisplayedItemsPerPage;

			int end = start+iDisplayedItemsPerPage;
			int pageIndex = Integer.parseInt(pageInd.get_ActualWert())+1; 


			int maxVectorSize = (vectorId.size()/iDisplayedItemsPerPage)+1;
			if(pageInd.getModel().size()==1){
				nextButton.set_bEnabled_For_Edit(false);
				lastButton.set_bEnabled_For_Edit(false);
				previousButton.set_bEnabled_For_Edit(false);
				firstButton.set_bEnabled_For_Edit(false);
			}else
				pageInd.set_ActiveInhalt(""+(pageIndex));
				
			if(pageIndex >= maxVectorSize){
					end = vectorId.size();
					start = (pageInd.get_oSelModel().size()-1)*iDisplayedItemsPerPage;
					nextButton.set_bEnabled_For_Edit(false);
					lastButton.set_bEnabled_For_Edit(false);
					previousButton.set_bEnabled_For_Edit(true);
					firstButton.set_bEnabled_For_Edit(true);
				}else{
					nextButton.set_bEnabled_For_Edit(true);
					lastButton.set_bEnabled_For_Edit(true);
					previousButton.set_bEnabled_For_Edit(true);
					firstButton.set_bEnabled_For_Edit(true);
					start = end - iDisplayedItemsPerPage;
				}


			if(pageIndex>1 && pageIndex<maxVectorSize){
				previousButton.set_bEnabled_For_Edit(true);
				firstButton.set_bEnabled_For_Edit(true);
				nextButton.set_bEnabled_For_Edit(true);
				lastButton.set_bEnabled_For_Edit(true);
			}

			if(pageIndex<=maxVectorSize){
				nextButton.set_bEnabled_For_Edit(false);
				lastButton.set_bEnabled_For_Edit(false);
				previousButton.set_bEnabled_For_Edit(true);
				firstButton.set_bEnabled_For_Edit(true);

				selectRecordFromTo_ids(start, end);
			}

		}

	}

	private class ownFirstAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			int end = iDisplayedItemsPerPage;
			int start = 0;
			pageInd.set_ActiveInhalt("1");

			previousButton.set_bEnabled_For_Edit(false);
			firstButton.set_bEnabled_For_Edit(false);
			lastButton.set_bEnabled_For_Edit(true);
			nextButton.set_bEnabled_For_Edit(true);

			selectRecordFromTo_ids(start, end);
		}

	}


	private class ownLastAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			int end = vectorId.size();

			int start = (pageInd.get_oSelModel().size()-1)*iDisplayedItemsPerPage;

			pageInd.set_ActiveInhalt(""+pageInd.get_oSelModel().size());

			previousButton.set_bEnabled_For_Edit(true);
			firstButton.set_bEnabled_For_Edit(true);
			lastButton.set_bEnabled_For_Edit(false);
			nextButton.set_bEnabled_For_Edit(false);

			selectRecordFromTo_ids(start, end);
		}

	}

	private class goToVerkaufenInfos_actionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			int indexToGo = firstVk;
			if(indexToGo > 0){
				int seiteToGo = (indexToGo/iDisplayedItemsPerPage)+1;
				int start = (seiteToGo*iDisplayedItemsPerPage-(iDisplayedItemsPerPage));
				int end = start + iDisplayedItemsPerPage;

				pageInd.set_ActiveInhalt(""+(seiteToGo));
				if(seiteToGo==1){
					firstButton.set_bEnabled_For_Edit(false);
					previousButton.set_bEnabled_For_Edit(false);
					lastButton.set_bEnabled_For_Edit(true);
					nextButton.set_bEnabled_For_Edit(true);
				}else if(seiteToGo<=Integer.parseInt(pageTotals.EXT().get_C_MERKMAL())){
					firstButton.set_bEnabled_For_Edit(true);
					previousButton.set_bEnabled_For_Edit(true);
					lastButton.set_bEnabled_For_Edit(false);
					nextButton.set_bEnabled_For_Edit(false);
				}else{
					firstButton.set_bEnabled_For_Edit(true);
					previousButton.set_bEnabled_For_Edit(true);
					lastButton.set_bEnabled_For_Edit(true);
					nextButton.set_bEnabled_For_Edit(true);
				}
				selectRecordFromTo_ids(start, end);
			}
		}
	}

	private class seiteLaengeActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String wert = seiteLaengeSelectField.get_ActualWert();
			int start = 0;
			int end = 0;
			if(wert.equals("Alle (lange Wartezeit)")){
				iDisplayedItemsPerPage = 0;
				fillSeiteComboBox();
				end = vectorId.size();
			}else{
				iDisplayedItemsPerPage = Integer.parseInt(wert);
				fillSeiteComboBox();
				end = iDisplayedItemsPerPage;
			}

			selectRecordFromTo_ids(start, end);

		}

	}
}
