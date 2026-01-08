package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

public class MESSAGE_Entry_Target {

	String _IDTarget 			= null;
	String _ModulTarget 		= null;
	String _ModulTargetZusatz 	= null;
	String _Target_Button_Text 	= null;
	
	public MESSAGE_Entry_Target (){
	}
	
	public MESSAGE_Entry_Target(String _IDTarget, String _ModulTarget,
			String _ModulTargetZusatz, String _Target_Button_Text) {
		super();
		this._IDTarget = _IDTarget;
		this._ModulTarget = _ModulTarget;
		this._ModulTargetZusatz = _ModulTargetZusatz;
		this._Target_Button_Text = _Target_Button_Text;
	}

	/**
	 * @return the _IDTarget
	 */
	public String get_IDTarget() {
		return _IDTarget;
	}

	/**
	 * @param _IDTarget the _IDTarget to set
	 */
	public MESSAGE_Entry_Target set_IDTarget(String _IDTarget) {
		this._IDTarget = _IDTarget;
		return this;
	}

	/**
	 * @return the _ModulTarget
	 */
	public String get_ModulTarget() {
		return _ModulTarget;
	}

	/**
	 * @param _ModulTarget the _ModulTarget to set
	 */
	public MESSAGE_Entry_Target set_ModulTarget(String _ModulTarget) {
		this._ModulTarget = _ModulTarget;
		return this;
	}

	/**
	 * @return the _ModulTargetZusatz
	 */
	public String get_ModulTargetZusatz() {
		return _ModulTargetZusatz;
	}

	/**
	 * @param _ModulTargetZusatz the _ModulTargetZusatz to set
	 */
	public MESSAGE_Entry_Target set_ModulTargetZusatz(String _ModulTargetZusatz) {
		this._ModulTargetZusatz = _ModulTargetZusatz;
		return this;
	}

	public String get_Target_Button_Text() {
		return _Target_Button_Text;
	}

	public MESSAGE_Entry_Target set_Target_Button_Text(String _Target_Button_Text) {
		this._Target_Button_Text = _Target_Button_Text;
		return this;
	}

	
	
}
