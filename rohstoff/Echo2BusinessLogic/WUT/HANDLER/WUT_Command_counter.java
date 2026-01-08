/**
 * rohstoff.Echo2BusinessLogic.WUT.HANDLER
 * @author manfred
 * @date 25.11.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.WUT.HANDLER;

import java.util.ArrayList;
import java.util.StringTokenizer;

import panter.gmbh.indep.bibALL;

/**
 * @author manfred
 * @date 25.11.2016
 *
 */
public class WUT_Command_counter extends WUT_Command {

	/**
	 * @author manfred
	 * @date 25.11.2016
	 *
	 * @param command
	 */
	public WUT_Command_counter() {
		super(ENUM_WUT_Command.counter);
	}


	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.WUT.HANDLER.WUT_Command#parseResult()
	 */
	@Override
	public void parseResult() {
		_arResult = new ArrayList<>();
		if (!bibALL.isEmpty(_origResult )){
			StringTokenizer st = new StringTokenizer(_origResult,";");
			while (st.hasMoreTokens()){
				_arResult.add(st.nextToken());
			}
		}
	}




}
