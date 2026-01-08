package panter.gmbh.indep;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * 
 * klasse um eine uebersetzung zwischen angezeigten und datenbankwerten zu
 * unterscheiden. besteht aus einem Vector aus paaren von zuordnungsobjekten
 * 
 */
public class dataToView extends Vector<dataToView.zuOrdnung> implements E2_IF_Copy
{

	private boolean bTranslate = false;

	private HttpSession oSES = null;;

	/**
	 * @param translate
	 */
	public dataToView(boolean translate, HttpSession oses)
	{
		this.bTranslate = translate;
		this.oSES = oses;
	}

	public dataToView(String[][] cViewValueArray, boolean translate, HttpSession oses) throws myException
	{
		this.bTranslate = translate;
		this.oSES = oses;

		if (cViewValueArray == null)
			throw new myException(
					"dataToView: null or empty array not allowed! Must be an array[n][2]");

		for (int i = 0; i < cViewValueArray.length; i++)
		{
			if (cViewValueArray[i].length != 2)
				throw new myException(
						"dataToView: null or empty array not allowed! Must be an array[n][2]");

			this.addPairOfValues(cViewValueArray[i][0], cViewValueArray[i][1]);
		}
	}
	
	public dataToView(HashMap<String, String> cViewValueArray, boolean translate,
			HttpSession oses) throws myException
	{
		this.bTranslate = translate;
		this.oSES = oses;

		if (cViewValueArray == null || cViewValueArray.size() == 0)
			throw new myException(
					"dataToView: null or empty array not allowed! Must be an array[n]");
		
		Iterator<Map.Entry<String, String>> it = cViewValueArray.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			this.addPairOfValues(entry.getKey(), entry.getValue());
		}
	}
	
	
	public dataToView(LinkedHashMap<String, String> cViewValueArray, boolean translate) throws myException
	{
		this.bTranslate = translate;
		this.oSES = bibE2.get_CurrSession();

		if (cViewValueArray == null || cViewValueArray.size() == 0)
			throw new myException(
					"dataToView: null or empty array not allowed! Must be an array[n]");
		
		Iterator<Map.Entry<String, String>> it = cViewValueArray.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			this.addPairOfValues(entry.getKey(), entry.getValue());
		}
	}

	
	public dataToView(TreeMap<String, String> cViewValueArray, boolean translate) throws myException
	{
		this.bTranslate = translate;
		this.oSES = bibE2.get_CurrSession();

		if (cViewValueArray == null || cViewValueArray.size() == 0)
			throw new myException(
					"dataToView: null or empty array not allowed! Must be an array[n]");
		
		Iterator<Map.Entry<String, String>> it = cViewValueArray.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			this.addPairOfValues(entry.getKey(), entry.getValue());
		}
	}

	
	
	public dataToView(String[] cViewValueArray, boolean translate, 	HttpSession oses) throws myException
	{
		this.bTranslate = translate;
		this.oSES = oses;

		if (cViewValueArray == null || cViewValueArray.length == 0)
			throw new myException(
					"dataToView: null or empty array not allowed! Must be an array[n]");

		for (int i = 0; i < cViewValueArray.length; i++)
		{
			this.addPairOfValues(cViewValueArray[i], cViewValueArray[i]);
		}

	}

	
	
	private void addPairOfValues(String cView, String cData) throws myException
	{
//		///DEBUG
//		DEBUG.System_println("View: "+cView+" ----> "+"DATA: "+cData);
//		if (cView.startsWith("VK200381-6 [0080-00]")) {
//			DEBUG.System_println("Break");
//		}
//		//DEBUG
		
		if (this.bTranslate) {
			this.add(new zuOrdnung(new MyString(cView), cData));
		} else {
			this.add(new zuOrdnung(cView, cData));
		}

	}


	/**
	 * 
	 * 2013-10-01: moeglichkeit, haendische was zuzufuegen
	 * @param cView
	 * @param cData
	 * @param bInFront
	 * @throws myException
	 */
	public void addPairOfValues(String cView, String cData, boolean bInFront) throws myException
	{
		if (!bInFront)  {
			if (this.bTranslate){
				this.add(new zuOrdnung(new MyString(cView), cData));
			} else { 
				this.add(new zuOrdnung(cView, cData));
			}
		} else {
			if (this.bTranslate){
				this.add(0, new zuOrdnung(new MyString(cView), cData));
			} else { 
				this.add(0, new zuOrdnung(cView, cData));
			}
		}
	}

	
	
	public String get_ViewStringToData(String cSearchValue, boolean bViewTranslated)
	{
		String cRueck = null;
		for (int i = 0; i < this.size(); i++)
		{
			zuOrdnung oZo = (zuOrdnung) this.get(i);
			if (oZo.get_cData().equals(cSearchValue))
			{
				cRueck = bViewTranslated ? oZo.get_cViewTranslated() : oZo
						.get_cViewUnTranslated();
			}
		}
		return cRueck;

	}

	public String get_DataStringToView(String cSearchView,	boolean bSearchViewIsTranslated) throws myException
	{
		String cRueck = null;
		for (int i = 0; i < this.size(); i++)
		{
			zuOrdnung oZo = (zuOrdnung) this.get(i);
			String cHelp = bSearchViewIsTranslated ? oZo.get_cViewTranslated()
					: oZo.get_cViewUnTranslated();

			if (cHelp.equals(cSearchView))
			{
				cRueck = oZo.get_cData();
			}
		}
		if (cRueck == null)
			throw new myException(
					"dataToView:get_DataStringToView:cSearchView  "
							+ cSearchView + " not found !!");
		else
			return cRueck;

	}

	public Vector<String> get_vDataValues()
	{
		Vector<String> vRueck = new Vector<String>();

		for (int i = 0; i < this.size(); i++)
		{
			vRueck.add(new String(((zuOrdnung) this.get(i)).get_cData()));
		}

		return vRueck;
	}

	public Vector<String> get_vViewValuesUntranslated()
	{
		Vector<String> vRueck = new Vector<String>();

		for (int i = 0; i < this.size(); i++)
		{
			vRueck.add(new String(((zuOrdnung) this.get(i))
					.get_cViewUnTranslated()));
		}

		return vRueck;
	}

	public Vector<String> get_vViewValuesTranslated()
	{
		Vector<String> vRueck = new Vector<String>();

		for (int i = 0; i < this.size(); i++)
		{
			vRueck.add(new String(((zuOrdnung) this.get(i))
					.get_cViewTranslated()));
		}

		return vRueck;
	}

	public String[] get_ViewArray()
	{
		String[] cRueck = new String[this.size()];
		for (int i = 0; i < this.size(); i++)
		{
			cRueck[i] = new String(((zuOrdnung) this.get(i))
					.get_cViewTranslated());
		}

		return cRueck;
	}

	public int get_PositionOfData(String cData)
	{
		int iRueck = -1;
		for (int i = 0; i < this.size(); i++)
		{
			if (((zuOrdnung) this.get(i)).get_cData().equals(cData))
			{
				iRueck = i;
				break;
			}
		}

		return iRueck;
	}

	public int get_PositionOfView(String cView)
	{
		int iRueck = -1;
		for (int i = 0; i < this.size(); i++)
		{
			zuOrdnung oZo = (zuOrdnung) this.get(i);
			String cVergleich = this.bTranslate ? oZo.get_cViewTranslated()
					: oZo.get_cViewUnTranslated();
			if (cVergleich.equals(cView))
			{
				iRueck = i;
				break;
			}
		}

		return iRueck;
	}

	public String get_cValueAtPosition(int iPos) throws myException
	{
		return this.get_zoAtPosition(iPos).get_cData();
	}

	public String get_cViewAtPosition(int iPos) throws myException
	{
		if (this.bTranslate)
			return this.get_zoAtPosition(iPos).get_cViewTranslated();
		else
			return this.get_zoAtPosition(iPos).get_cViewUnTranslated();
	}

	/**
	 * @param cNewView
	 *            zuOrdnung oZo = (zuOrdnung)this.get(iPos);
	 * @param iPos
	 * @throws myException
	 *             Funktion zum manipulieren von listeneintraegen
	 */
	public void set_cViewAtPosition(String cNewView, int iPos)	throws myException
	{
		if (iPos >= this.size())
			throw new myException(
					"dataToView:set_cViewAtPosition:Index out of range !!");

		zuOrdnung oZo = (zuOrdnung) this.get(iPos);

		oZo.set_cView(cNewView);
	}

	public zuOrdnung get_zoAtPosition(int iPos) throws myException
	{
		if (iPos >= this.size())
			throw new myException(
					"dataToView:get_zoAtPosition:Index out of range !!");

		zuOrdnung oZo = (zuOrdnung) this.get(iPos);

		return oZo;
	}


	public boolean get_bTranslate()
	{
		return bTranslate;
	}

	
	/**
	 * 2012-02-09: neue methode, um  festzustellen, ob eine collection einen bestimmten wert enthaelt
	 * @param cValue
	 * @return
	 */
	public boolean get_bHasData(String cValue)
	{
		boolean bRueck = false;
		
		for (dataToView.zuOrdnung oZO: this)
		{
			if (oZO.cData.equals(cValue))
			{
				bRueck = true;
				break;
			}
		}
		
		return bRueck;
	}
	
	
	/**
	 * 2014-03-11: einen wert entfernen, wenn vorhanden
	 */
	public dataToView.zuOrdnung remove_DataToView_Zuordnung(String cDATAValueToRemove) {
		dataToView.zuOrdnung oRueck = null;
		
		for (int i=0; i<this.size();i++) {

			dataToView.zuOrdnung oZO = this.get(i);
			
			if (oZO.cData.equals(cDATAValueToRemove)) {
				oRueck = oZO;
				this.remove(i);
			}
		}
		return oRueck;
	}
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		dataToView oDV_Rueck = new dataToView(this.bTranslate, this.oSES);

		try
		{
			for (int i = 0; i < this.size(); i++)
			{
				oDV_Rueck.add((dataToView.zuOrdnung) (this.get_zoAtPosition(i))
						.get_Copy(null));
			}
		} catch (myException ex)
		{
			throw new myExceptionCopy("dataToView:get_Copy:Error Copying: "
					+ ex.get_ErrorMessage().get_cMessage().COrig());
		}

		return oDV_Rueck;
	}

	
	
	
	
	
	public static class zuOrdnung implements E2_IF_Copy, Comparable<zuOrdnung>
	{
		private Object View = null;

		private String cData = null;

		/**
		 * @param view
		 * @param data
		 */
		public zuOrdnung(Object view, String data) throws myException
		{
			super();

			if (view == null || data == null)
				throw new myException(
						"dataToView:zuOrdnung:nullvalue in Contructor not allowed !");

			if (view instanceof String || view instanceof MyString)
			{
				View = view;
				cData = data;
			} else
				throw new myException(
						"dataToView:zuOrdnung:only type string or type MyString in Contructor allowed !");

			View = view;
			cData = data;
		}

		public void set_cView(Object view) throws myException
		{
			if (view instanceof String || view instanceof MyString)
			{
				View = view;
			} else
				throw new myException(
						"dataToView:set_cView:only type string or type MyString in Contructor allowed !");

		}

		/**
		 * @return Returns the cData.
		 */
		public String get_cData()
		{
			return cData;
		}

		/**
		 * @return Returns the view.
		 */
		public String get_cViewTranslated()
		{
			if (this.View instanceof String)
				return (String) View;
			else
				return ((MyString) View).CTrans();
		}

		/**
		 * @return Returns the view.
		 */
		public String get_cViewUnTranslated()
		{
			if (this.View instanceof String)
				return (String) View;
			else
				return ((MyString) View).COrig();
		}

		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				return new zuOrdnung(this.View, this.cData);
			} catch (myException ex)
			{
				throw new myExceptionCopy(
						"dataToView:zuOrdnung:get_Copy:Error Copying: "
								+ ex.get_ErrorMessage().get_cMessage().COrig());
			}
		}

		/**
		 * gleich heist: datenbank-eintrag (wert) ist gleich,
		 * die reihenfolge wird con der anzeige bestimmt
		 */
		@Override
		public int compareTo(zuOrdnung cCompared)
		{
			
			
	//		DEBUG.System_println(this.cData+" <> "+cCompared.get_cData()+"    ------------   "+this.get_cViewTranslated()+" <> "+cCompared.get_cViewTranslated(), "");
			
			
			if (this.cData.equals(cCompared.get_cData()))
			{
				return 0;            //gleich
			}
			else
			{
				if (this.get_cViewTranslated().equals(cCompared.get_cViewTranslated()))
				{
					return this.cData.compareTo(cCompared.get_cData());
				}
				else
				{
					String ccOwn = this.get_cViewTranslated();
					String ccCompared = cCompared.get_cViewTranslated();
					return ccOwn.toUpperCase().compareTo(ccCompared.toUpperCase());
				}
			}
		}
		
		
		public void showViewInConsole()
		{
			System.out.println(this.get_cViewTranslated());
		}
	}

}
