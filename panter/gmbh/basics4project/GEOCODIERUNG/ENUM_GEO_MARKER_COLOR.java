package panter.gmbh.basics4project.GEOCODIERUNG;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;


/**
 * 
 * @author manfred
 * @date 29.11.2018
 *
 *                 switch (col) {
                    case 'r': ic = redIcon;
                        break;
                    case 'g': ic = greenIcon;
                        break;
                    case 'y': ic = yellowIcon;
                        break;
                    case 'o': ic = orangeIcon;
                        break;
                    case 'v': ic = violetIcon;
                        break;
                    case 'gr': ic = greyIcon;
                        break;
                    case 'b': ic = blackIcon;
                        break;
                    case "bl": ic = blueIcon;
                        break;
                    default: ic = blueIcon;
                }
 *
 */

public enum ENUM_GEO_MARKER_COLOR implements IF_enum_4_db{
	GREEN("g"),
	RED("r"),
	YELLOW("y"),
	ORANGE("o"),
	VIOLETT("v"),
	GREY("gr"),
	BLACK("b"),
	BLUE("bl"),
	;

	private String value;
	
	private ENUM_GEO_MARKER_COLOR(String p_value) {
		value = p_value;
	}
	
	@Override
	public String db_val() {
		return value;
	}

	@Override
	public String user_text() {
		return value;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_GEO_MARKER_COLOR.values(),false);
	}
}
