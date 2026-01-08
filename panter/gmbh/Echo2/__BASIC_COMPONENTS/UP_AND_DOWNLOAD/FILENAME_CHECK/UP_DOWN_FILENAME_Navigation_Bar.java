package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.exceptions.myException;

public class UP_DOWN_FILENAME_Navigation_Bar extends E2_Grid{

	private E2_Button previousButton;
	private E2_Button nextButton;
	private E2_Button firstButton;
	private E2_Button lastButton;
//	private E2_Button reloadButton;

	private RB_selField	pageInd;
	private RB_lab		pageTotals;

	private int 			nbOfItemVisible = 32;

	private int				numberOfPage = 0;

	private int 			page =1;

	private HashMap<String, UP_DOWN_FILENAME_Row_Model> hm;
	private ArrayList<String> keyList;


	private UP_DOWN_FILENAME_ListView parent;

	public UP_DOWN_FILENAME_Navigation_Bar(UP_DOWN_FILENAME_ListView p_parent, int p_nbVisibleItems) throws myException {
		super();
		this._s(7)._bo_no()._gld(new RB_gld()._ins(2));

		this.nbOfItemVisible = p_nbVisibleItems;

		this.parent = p_parent;

		this.previousButton = new E2_Button().__setImages(E2_ResourceIcon.get_RI("left.png"), 	E2_ResourceIcon.get_RI("left__.png")); 
		this.previousButton._aaa(()->goPreviousPage());
		this.previousButton.set_bEnabled_For_Edit(false);

		this.nextButton 	= new E2_Button().__setImages(E2_ResourceIcon.get_RI("right.png"), 	E2_ResourceIcon.get_RI("right__.png")) ;
		this.nextButton._aaa(()->goNextPage());

		this.firstButton = new E2_Button().__setImages(E2_ResourceIcon.get_RI("left_end.png"), 	E2_ResourceIcon.get_RI("left_end_.png"));
		this.firstButton._aaa(()->goFirstPage());
		this.firstButton.set_bEnabled_For_Edit(false);

		this.lastButton = new E2_Button().__setImages(E2_ResourceIcon.get_RI("right_end.png"), 	E2_ResourceIcon.get_RI("right_end_.png"));
		this.lastButton._aaa(()->goLastPage());

//		this.reloadButton = new E2_Button()._image(E2_ResourceIcon.get_RI("reload.png"));
//		this.reloadButton._aaa(()->reload());

		this.pageTotals		= new RB_lab("");
		
		this.pageInd		= new RB_selField()._width(50);//"", 50, 5);		
		this.pageInd._aaa(()->reload());
		
		this._a(firstButton, 	new RB_gld()._ins(2,1,0,1)._left_mid())
		._a(previousButton,		new RB_gld()._ins(2,1,2,1)._left_mid())
		._a("Seite:", 			new RB_gld()._ins(10,1,0,1)._left_mid())
		._a(pageInd,  			new RB_gld()._ins(2,1,0,1)._left_mid())
		._a(pageTotals, 		new RB_gld()._ins(10,1,0,1)._left_mid())
//		._a(reloadButton, 		new RB_gld()._ins(5,1,0,1)._left_mid())
		._a(nextButton, 		new RB_gld()._ins(2,1,2,1)._left_mid())
		._a(lastButton, 		new RB_gld()._ins(2,1,2,1)._left_mid())
		;
	}

	public void loadItems(LinkedHashMap<String, UP_DOWN_FILENAME_Row_Model> p_fileHashMap) throws myException{
		this.hm = p_fileHashMap;
		this.numberOfPage = hm.size()/nbOfItemVisible;

		this.pageTotals.setText("/" + numberOfPage);

		this.keyList = new ArrayList<String>(hm.keySet());
		Collections.sort(keyList);

		for(int i=1;i<=numberOfPage;i++) {
			this.pageInd._addPair(new PairS(""+i, ""+i));
		}
		
		refreshList(1);
	}

	private void refreshList(int pageIndex) throws myException{

		int start = (pageIndex-1)*nbOfItemVisible;
		int stop = ((pageIndex-1)*nbOfItemVisible)+nbOfItemVisible;
		this.parent.getElementGrid()._clear();
		if(keyList.size()>nbOfItemVisible){
			
			for(int i=start;i<stop;i++){
				new UP_DOWN_FILENAME_RowComponent(this.parent.getElementGrid(), hm.get(keyList.get(i))) ;
			}
				
			nextButton.set_bEnabled_For_Edit(true);
			lastButton.set_bEnabled_For_Edit(true);
//			reloadButton.set_bEnabled_For_Edit(true);
			
		}else{
			this.pageInd.setSelectedItem("1");
			numberOfPage = 1;
			previousButton.set_bEnabled_For_Edit(false);
			nextButton.set_bEnabled_For_Edit(false);
			firstButton.set_bEnabled_For_Edit(false);
			lastButton.set_bEnabled_For_Edit(false);
//			reloadButton.set_bEnabled_For_Edit(false);
			
			for(int i=0;i<keyList.size();i++){
				new UP_DOWN_FILENAME_RowComponent(this.parent.getElementGrid(), hm.get(keyList.get(i))) ;
			}
		}
		this.pageInd.setSelectedItem(""+pageIndex);
	}


	private void goPreviousPage() throws myException {
		page--;
		refreshList(page);
		if(page==1){
			previousButton.set_bEnabled_For_Edit(false);
			firstButton.set_bEnabled_For_Edit(false);
		}else if(page==numberOfPage){
			nextButton.set_bEnabled_For_Edit(false);
			lastButton.set_bEnabled_For_Edit(false);
		}else{
			nextButton.set_bEnabled_For_Edit(true);
			lastButton.set_bEnabled_For_Edit(true);
		}
	}
	
	
	private void goNextPage() throws myException {
		page++;
		refreshList(page);
		if(page==2){
			previousButton.set_bEnabled_For_Edit(true);
			firstButton.set_bEnabled_For_Edit(true);
		}else if(page==(numberOfPage-1)){
			nextButton.set_bEnabled_For_Edit(true);
			lastButton.set_bEnabled_For_Edit(true);
		}else if(page==numberOfPage){
			nextButton.set_bEnabled_For_Edit(false);
			lastButton.set_bEnabled_For_Edit(false);
		}
	}
	

	private void goFirstPage() throws myException {
		refreshList(1);

		previousButton.set_bEnabled_For_Edit(false);
		firstButton.set_bEnabled_For_Edit(false);

		nextButton.set_bEnabled_For_Edit(true);
		lastButton.set_bEnabled_For_Edit(true);

		page = 1;
	}

	
	private void goLastPage() throws myException {
		refreshList(numberOfPage);

		nextButton.set_bEnabled_For_Edit(false);
		lastButton.set_bEnabled_For_Edit(false);

		previousButton.set_bEnabled_For_Edit(true);
		firstButton.set_bEnabled_For_Edit(true);

		page = numberOfPage;
	}

	
	private void reload() throws myException {
		int a = Integer.parseInt(pageInd.getActualVisibleVal());
		if(a>0 && a<(numberOfPage+1)){
			page = a;
			refreshList(page);
		}
		if(a>1){
			previousButton.set_bEnabled_For_Edit(true);
			firstButton.set_bEnabled_For_Edit(true);
			
			nextButton.set_bEnabled_For_Edit(true);
			lastButton.set_bEnabled_For_Edit(true);
		}else{
			previousButton.set_bEnabled_For_Edit(false);
			firstButton.set_bEnabled_For_Edit(false);
		}
		if(a>=numberOfPage){
			nextButton.set_bEnabled_For_Edit(false);
			lastButton.set_bEnabled_For_Edit(false);
			
			previousButton.set_bEnabled_For_Edit(true);
			firstButton.set_bEnabled_For_Edit(true);
		}else{
			nextButton.set_bEnabled_For_Edit(true);
			lastButton.set_bEnabled_For_Edit(true);
		}
	}
}
