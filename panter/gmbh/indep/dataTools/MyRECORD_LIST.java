package panter.gmbh.indep.dataTools;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MyRECORD_LIST extends HashMap<String,MyRECORD>
{

	private MyConnection  oConn = bibALL.get_oConnectionNormal();

	private String        	cQueryString = null;
	private String        	cKeyField = null;
	private Vector<String> 	vKeyValues = new Vector<String>();
	private MyDBToolBox     oDB = null;
	
	/**
	 * 2016-04-14: maxresults zugefuegt
	 */
	private long max_results = 0;

	/**
	 * 
	 * @param QueryString
	 * @param KeyField (MUSS als Feld im QueryString vorhanden sein !!)
	 * @throws myException
	 */
	public MyRECORD_LIST(String QueryString, String KeyField) throws myException
	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = KeyField;
		
		this.REFRESH();
	}

	
	/**
	 * 2016-04-14: max_results 
	 * @param QueryString
	 * @param KeyField (MUSS als Feld im QueryString vorhanden sein !!)
	 * @param maxResults
	 * @throws myException
	 */
	public MyRECORD_LIST(String QueryString, String KeyField, long maxResults) throws myException	{
		super();
		this.cQueryString = QueryString;
		this.cKeyField = KeyField;
		this.max_results = maxResults;
		
		this.REFRESH();
	}


	/**
	 * 
	 * @param QueryString
	 * @param KeyField (MUSS als Feld im QueryString vorhanden sein !!)
	 * @param Conn
	 * @throws myException
	 */
	public MyRECORD_LIST(String QueryString, String KeyField, MyConnection Conn) throws myException
	{
		super();
		this.oConn = Conn;
		
		this.cQueryString = QueryString;
		this.cKeyField = KeyField;

		this.REFRESH();
	}


	
	protected void finalize()
	{
		if (this.oDB!=null)
			bibALL.destroy_myDBToolBox(oDB);
	}

	
	public void REFRESH() throws myException
	{
		this.clear();
		this.vKeyValues.removeAllElements();

		if (this.oDB == null)
			this.oDB = 	bibALL.get_myDBToolBox(this.oConn);
		
		MyDBResultSet oRS = oDB.OpenResultSet(this.cQueryString);
		
		if (oRS.RS != null)
		{
            try
            {
                int iAnzahlSpalten = oRS.RS.getMetaData().getColumnCount();

                int iCount = 0;
                if (iAnzahlSpalten > 0)
                {
                    while (oRS.RS.next())
                    {
                    	String 		cHASHKEY = 	null;     //wird waehrend des lesens ermittelt
                      	MyRECORD  	oRec = 		new MyRECORD();
                      	
                      	for (int i = 0; i < iAnzahlSpalten; i++)
                        {
                        	MyResultValue oRSV = new MyResultValue(new MyMetaFieldDEF(oRS.RS,i, null),oRS.RS,false);
                        	
                        	if (oRSV.get_cFieldLABEL().equals(this.cKeyField))
                        	{
                        		cHASHKEY = oRSV.get_FieldValueUnformated();    //oRSV ist in der Regel ein INDEXFELD
                        	}
                        	
                        	oRec.ADD(oRSV,false);
                        }
                        iCount++;

                        if (this.max_results>0l && iCount>max_results) {
                        	break;
                        }
                        
                        if (S.isEmpty(cHASHKEY))
                        {
                        	throw new myException(this,"Field "+ this.cKeyField+" is not in the ResultSet or contains no Value. This is not allowed !!");
                        }
                        else if (this.containsKey(cHASHKEY)) 
                        {
                        	throw new myException(this,"Field "+ this.cKeyField+" has the Value "+cHASHKEY+" more than one time in the resultSet !!");
                        }
                        else
                        {
                        	this.vKeyValues.add(cHASHKEY);
                        	this.put(cHASHKEY, oRec);
                        }
                    }
                }
            }
            catch (myException ex)
            {
            	ex.printStackTrace();
            	oRS.Close();
            	bibALL.destroy_myDBToolBox(oDB);
            	throw ex;
            }
            catch (Exception e)
            {
            	e.printStackTrace();
            	oRS.Close();
            	bibALL.destroy_myDBToolBox(oDB);
            	e.printStackTrace();
            	throw new myException(e.getLocalizedMessage());
            }
 		}
		else
		{
        	DEBUG.System_println("--------------------------------------------------",DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("MyDataRecordHashMap: Error SQL  ---------------------------------------", DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println(this.cQueryString, DEBUG.DEBUG_FLAG_SQL_ERROR);
        	DEBUG.System_println("--------------------------------------------------",DEBUG.DEBUG_FLAG_SQL_ERROR);
        	bibALL.destroy_myDBToolBox(oDB);
           	throw new myException(myException.TYPE_ERROR_SQL_QUERY_IS_NOT_CORRECT,"MyDataRecordHashMap:__build_Hash: Cannnot open resultset !"+this.cQueryString);
		}

        oRS.Close();
        bibALL.destroy_myDBToolBox(oDB);
	}
	

	public MyRECORD get_(String cIndexKeyOfRecord) throws myException
	{
		if (!this.containsKey(cIndexKeyOfRecord))
		{
			throw new myException(this,"The Key "+cIndexKeyOfRecord+" is not in this MyRECORD_LIST!");			
		}
		
		return this.get(cIndexKeyOfRecord);
	}
	
	
	
	public MyRECORD get_(int iNumberInVector) throws myException
	{
		if (iNumberInVector >= this.vKeyValues.size() || iNumberInVector<0)
		{
			throw new myException(this,"The Index-Number "+iNumberInVector+" is not allowed !");			
		}
		return this.get_(this.vKeyValues.get(iNumberInVector));
	}
	
	
	
	
	public double get_d_Summe(String cFieldHash, MyRECORD_LIST.ValidSummation oValidator) throws myException
	{
		Iterator<Map.Entry<String,MyRECORD>>  oIter = this.entrySet().iterator(); 
		
		double dRueck = 0;
		
		while (oIter.hasNext())
		{
			MyRECORD 	oRec = oIter.next().getValue();
			Double 		oD = oRec.get_doubleValue(cFieldHash);
			
			if (oValidator == null || oValidator.isValidForSummation(oRec))
			{
				if (oD != null)
				{
					dRueck += oD.doubleValue();
				}
			}
		}
		
		return dRueck;
	}
	

	
	public long get_l_Summe(String cFieldHash, MyRECORD_LIST.ValidSummation oValidator) throws myException
	{
		Iterator<Map.Entry<String,MyRECORD>>  oIter = this.entrySet().iterator(); 
		
		long lRueck = 0;
		
		while (oIter.hasNext())
		{
			MyRECORD 	oRec = oIter.next().getValue();
			Long 		oL = oRec.get_longValue(cFieldHash);
			
			if (oValidator == null || oValidator.isValidForSummation(oRec))
			{
				if (oL != null)
				{
					lRueck += oL.longValue();
				}
			}
		}
		return lRueck;
	}	

	
	
	
	public static abstract class ValidSummation
	{
   		public abstract boolean isValidForSummation(MyRECORD oRECORD);
	}




	public Vector<String> get_vKeyValues()
	{
		return vKeyValues;
	}

	
	
	/**
	 * 2016-06-02: martin : records iterierbar in reihenfolge der id
	 * erzeugt nach ids sortierte arraylist der records 
	 * @return
	 */
	public ArrayList<MyRECORD> al_MainIdSorted() {
		Vector<String>  v_help = new Vector<>();
		v_help.addAll(this.vKeyValues);
		
		v_help.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				Integer i1 = new Integer(o1);
				Integer i2 = new Integer(o2);
				
				return i1.compareTo(i2);
			}
		});
		
		//jetzt ist der v_help numerisch sortiert 
		
		ArrayList<MyRECORD> al_rueck = new ArrayList<>();
		
		for (String s: v_help) {
			al_rueck.add(this.get(s));
		}
		
		return al_rueck;
	}
	
	
	
	
}
