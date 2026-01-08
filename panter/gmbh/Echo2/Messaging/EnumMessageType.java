/**
 * panter.gmbh.Echo2.Messaging
 * @author martin
 * @date 20.08.2021
 * 
 */
package panter.gmbh.Echo2.Messaging;

/**
 * @author martin
 * @date 20.08.2021
 *
 */
public enum EnumMessageType {
	
	 INFO(MyE2_Message.TYP_INFO)
	,WARNING(MyE2_Message.TYP_WARNING)
	,ALARM(MyE2_Message.TYP_ALARM)
	;
	
	
	private int value = MyE2_Message.TYP_ALARM;

	/**
	 * @author martin
	 * @date 20.08.2021
	 *
	 * @param value
	 */
	private EnumMessageType(int value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

}
