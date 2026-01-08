/**
 * rohstoff.Echo2BusinessLogic.WUT.HANDLER
 * @author manfred
 * @date 24.11.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.WUT.HANDLER;

import java.util.ArrayList;
import java.util.Vector;

import org.omg.CORBA.COMM_FAILURE;

/**
 * @author manfred
 * @date 24.11.2016
 *
 */
public abstract class WUT_Command {
	
	protected ENUM_WUT_Command   	_command = null;
	protected Vector<String> 		_commandParameter = null;
	protected String				_origResult = null;
	protected ArrayList<String>  	_arResult = null;
	protected Integer 				_pin = null;
	
	
	public WUT_Command( ENUM_WUT_Command command){
		_arResult = new ArrayList<>();
		_commandParameter = new Vector<>();
		_command = command;
	}

	public WUT_Command clearPin( ){
		_pin = null;
		return this;
	}

	public WUT_Command setPin ( Integer pin ){
		_pin = pin;
		return this;
	}
	
	public WUT_Command setPin( int pin ){
		_pin = Integer.valueOf(pin);
		return this;
	}
	
	
	/**
	 * Parameterwert in Form "State=ON" 
	 * @author manfred
	 * @date 25.11.2016
	 *
	 * @param param
	 * @return
	 */
	public WUT_Command addParameter(String param){
		_commandParameter.add(param);
		return this;
	}
	
	
	public String getParameterList(){
		if (_commandParameter.size() == 0) return "";
		
		String sParameters = "";
		for (String sCmd: _commandParameter){
			sParameters += sCmd + "&";
		}
		return sParameters;
	}
	
	/**
	 * setzen des Ergebnisses der WUT-WebIO
	 * @author manfred
	 * @date 28.11.2016
	 *
	 * @param result
	 */
	public void SetOrigResult(String result){
		_origResult = result;
		parseResult();
	}
	
	
	public String getResultOri(){
		return _origResult;
	}
	
	public ArrayList<String> getResultToken(){
		return _arResult;
	}
	
	
	public String createCommand(String Password) {
		return _command.get_command(Password, _pin);
	}
	
	
	public abstract void parseResult();
	
}
