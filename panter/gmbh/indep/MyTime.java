package panter.gmbh.indep;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;

public class MyTime  implements IF_BasicTypeWrapper{

	
	public static String ALL_OK = 		"ALL_OK";
	public static String ERROR = 		"ERROR";


	private String  cTimeOrig = null;
	private String  cTimeKorrekt = null;
	
	private String  cErrorStatus = MyTime.ERROR;
	
	public MyTime(String TimeString) {
		super();
		this.cTimeOrig = TimeString.trim();

		String cHour = "";
		String cMin  = "";
		
		if (S.isFull(this.cTimeOrig)) {
			if (this.cTimeOrig.contains(":"))	{
				cHour = this.cTimeOrig.substring(0,this.cTimeOrig.indexOf(":"));
				cMin  = this.cTimeOrig.substring(this.cTimeOrig.indexOf(":")+1);
				
				if (cHour.length()==0) {cHour="00";}
				if (cHour.length()==1) {cHour="0"+cHour;}
				
				if (cMin.length()==0) {cMin="00";}
				if (cMin.length()==1) {cMin="0"+cMin;}
				
				MyInteger  intHour = 	new MyInteger(cHour);
				MyInteger  intMin = 	new MyInteger(cMin);
				
				if (intHour.get_bOK() && intMin.get_bOK())
				{
					if 	(
							(intHour.get_iValue()>=0 && intHour.get_iValue()<=23) && 
							(intMin.get_iValue()>=0 && intMin.get_iValue()<=59)
						)
					{
						this.cErrorStatus = MyTime.ALL_OK;
						this.cTimeKorrekt = cHour+":"+cMin;
					}
				}
				
			}
		}
		
		

	}
	
	public String get_cTimeOrig() {
		return cTimeOrig;
	}
	
	public String get_cTimeKorrekt() {
		return cTimeKorrekt;
	}

	
	public boolean get_bOK()
	{
		return this.cErrorStatus.equals(MyTime.ALL_OK);
	}

	
	
	@Override
	public String get_formated_string(MyMetaFieldDEF def) throws myException {
		if (def == null) {
			throw new myException("MyMetaFieldDEF def MUST NOT BE NULL");
		}
		
		return (S.isFull(this.cTimeKorrekt)?this.cTimeKorrekt:null);
		
	}

	@Override
	public Object getBasicObject() throws DataTypeException {
		if (this.isOK()) {
			return cTimeKorrekt;
		} else {
			if (S.isFull(cErrorStatus)) {
				throw new DataTypeExceptionInterpreterError(cErrorStatus, "Error interpreting Time-String-Data!");
			} else {
				throw new DataTypeExceptionInterpreterError("UNKNOWN", "Error interpreting Time-String-Data!");
			}
		}
	}
	
	
}
