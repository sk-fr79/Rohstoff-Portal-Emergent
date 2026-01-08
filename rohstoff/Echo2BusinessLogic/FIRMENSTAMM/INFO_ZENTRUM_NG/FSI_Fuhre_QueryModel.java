package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.util.Vector;

import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.FSI_Const_and_help.SORT_BY;

public class FSI_Fuhre_QueryModel {
	
	private String query="";
	
	private String orderby="";
	
	private boolean isEk=false;
	
	private boolean isOrt=false;
	
	private Vector<String> vectorId=new Vector<String>();

	private SORT_BY sortingWay;
	
	public FSI_Fuhre_QueryModel(String query, String orderby, boolean isEk, boolean isOrt, SORT_BY way) {
		super();
		this.query = query;
		this.orderby = orderby;
		this.isEk = isEk;
		this.isOrt = isOrt;
		this.sortingWay = way;
	}
	
	public String getQuery() {
		return query;
	}
	
	public FSI_Fuhre_QueryModel setQuery(String query) {
		this.query = query;
		return this;
	}
	
	public String getOrderby() {
		return orderby;
	}
	
	public FSI_Fuhre_QueryModel setOrderby(String orderby) {
		this.orderby = orderby;
		return this;
	}
	
	public boolean isEk() {
		return isEk;
	}
	
	public FSI_Fuhre_QueryModel setEk(boolean isEk) {
		this.isEk = isEk;
		return this;
	}
	
	public boolean isOrt() {
		return isOrt;
	}
	
	public FSI_Fuhre_QueryModel setOrt(boolean isOrt) {
		this.isOrt = isOrt;
		return this;
	}

	public Vector<String> getVectorId() {
		return vectorId;
	}

	public SORT_BY getSortingWay() {
		return sortingWay;
	}

	public FSI_Fuhre_QueryModel setSortingWay(SORT_BY sortingWay) {
		this.sortingWay = sortingWay;
		return this;
	}

	public FSI_Fuhre_QueryModel recoverData() throws myException{
		String finalQuery = getQuery()+getOrderby()+" "+ sortingWay.getSortingWay();
		vectorId.addAll(bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray(finalQuery)));
		return this;
	}
	
}
