package panter.gmbh.Echo2.components;

import java.util.Hashtable;
import java.util.Map.Entry;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_SelectFieldWithParameters extends MyE2_SelectField {

	// die Liste der Parameter, die im SQL-Statement übergeben werden können
	protected Hashtable<String, String> 	m_htParameterList = new Hashtable<String, String>();
	protected String 						m_sqlQueryWithParametersFilled = null;

	
	public MyE2_SelectFieldWithParameters() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyE2_SelectFieldWithParameters(String cSQLQueryForLIST,
			boolean bThirdColumnISSTANDARDMARKER, boolean bEmtyValueInFront,
			boolean bValuesFormated, boolean btranslate) throws myException {
		super(cSQLQueryForLIST, bThirdColumnISSTANDARDMARKER, bEmtyValueInFront,
				bValuesFormated, btranslate);
		// TODO Auto-generated constructor stub
	}

	public MyE2_SelectFieldWithParameters(String[] aDefArray,
			String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		// TODO Auto-generated constructor stub
	}

	public MyE2_SelectFieldWithParameters(String[][] aDefArray,
			String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		// TODO Auto-generated constructor stub
	}

	/** fügt ein Parameter dazu
	 * 
	 * @param key
	 */
	public void AddParameter(String key){
		m_htParameterList.put(key, "");
	}
	
	/** setzt den dazugefügten Parameter
	 * 
	 * @param key
	 * @param value
	 * @throws myException 
	 */
	public void SetParameter(String key, String value) throws myException {
		if (m_htParameterList.containsKey(key)){
			m_htParameterList.put(key,value);
		} else {
			throw new myException(new MyE2_String("Der gewünschte Parameter ist nicht definiert").CTrans() + ": " + key);
		}
	}
	
	/**
	 * Ersetzt alle Parameter mit den übergebenen Werten
	 * @return
	 * @throws myException
	 */
	protected String replaceParameters() throws myException{
		String sSqlForSelection = get_SQL_Query_For_LIST();
		for (Entry<String, String> oEntry : m_htParameterList.entrySet()){
			
			if (oEntry.getValue() == null){
				throw new myException(new MyE2_String("Der Parameterwert darf nicht null sein!").CTrans() );
			}
			
			if (!sSqlForSelection.contains(oEntry.getKey())){
				throw new myException(new MyE2_String("Der Parameter ist nicht definiert: ").CTrans() + oEntry.getKey() );
			}
			sSqlForSelection = sSqlForSelection.replace(oEntry.getKey(), oEntry.getValue());
		}
		return sSqlForSelection;
	}

	
	@Override
	protected void populateCombobox(String cSQLQueryForLIST,String cSQL_Query4InactiveMembers,
			boolean bThirdColumnISSTANDARDMARKER, boolean bEmtyValueInFront,
			boolean bValuesFormated, boolean btranslate) throws myException {
		if (m_htParameterList == null) return;
		
		String sSQL = this.replaceParameters();
		
		super.populateCombobox(sSQL,cSQL_Query4InactiveMembers, bThirdColumnISSTANDARDMARKER,
				bEmtyValueInFront, bValuesFormated, btranslate);
	}
	
	
}
