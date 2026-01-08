package rohstoff.Echo2BusinessLogic.LAGER;

import java.util.Hashtable;
import java.util.TreeMap;

import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class LAG_SelectBuchungsgewichte extends MyE2_SelectField {
	
	
	TreeMap<String, ENUM_SelektorLagerBuchungsgewichte> m_Entries = new TreeMap<String, ENUM_SelektorLagerBuchungsgewichte>();
	
	
	
	public LAG_SelectBuchungsgewichte() throws myException {
		this.fill_dropDown();	
	}

	
	private void fill_dropDown() throws myException
	{
		int countSelektor = ENUM_SelektorLagerBuchungsgewichte.values().length;
		
		
		String[][] cListenInhalt = new String[countSelektor][2];
		int count = 0;
		for (ENUM_SelektorLagerBuchungsgewichte e : ENUM_SelektorLagerBuchungsgewichte.values()){
			cListenInhalt[count][0] = e._Description;
			cListenInhalt[count][1] = e.name();
			count++;
		}
		
		this.set_ListenInhalt(cListenInhalt, false);
		
	}

	/**
	 * Gibt den selektierten EnumWert zurück
	 * @return
	 * @throws myException
	 */
	public ENUM_SelektorLagerBuchungsgewichte getSelected() throws myException{
		String sEnum = this.get_ActualWert();
		ENUM_SelektorLagerBuchungsgewichte enumRet = ENUM_SelektorLagerBuchungsgewichte.valueOf(sEnum);
		return enumRet;
	}
	
	
	
	
	public enum ENUM_SelektorLagerBuchungsgewichte {
		
		neutral("Keine",0,1,null),
		buchhalterisch("Nur Buchhalterisch",1,0,1),
		lager("Nur Lager",2,1,0);
		
		
		private String 		_Description = "default";
		private int 		_Buchungstyp = 0;
		private Integer		_multiplikator_lager = 1;
		private Integer     _multiplikator_buchhalterisch = 1;
		
		ENUM_SelektorLagerBuchungsgewichte(String Description, int Buchungstyp, Integer multiplikator_lager, Integer multiplikator_buchhalterisch){
			this._Buchungstyp = Buchungstyp;
			this._Description = Description;
			this._multiplikator_buchhalterisch = multiplikator_buchhalterisch;
			this._multiplikator_lager = multiplikator_lager;
		}
		
		public String getDescription(){
			return _Description;
		}
		
		public int getType(){
			return _Buchungstyp;
		}
		
		public Integer getMultiplikatorLager(){
			return _multiplikator_lager;
		}
		
		public Integer getMultiplikatorBuchhalterisch(){
			return _multiplikator_buchhalterisch;
		}
		
	}
	
}

