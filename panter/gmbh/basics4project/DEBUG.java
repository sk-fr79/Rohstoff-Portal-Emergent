package panter.gmbh.basics4project;

import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;


/**
 * Aenderungen:
 * Debug-Flags werden jetzt in der Web.XML angegeben im element
 * z.B.
 * 			<context-param>
 * 					<param-name>debug_flags</param-name> 
 * 					<param-value>SQL_ERROR|SQL_QUERYS|</param-value>
 *			</context-param>
 *
 * @author manfred
 * @date   19.01.2012
 */
public class DEBUG
{

	//2015-05-13: umstellung auf enum
	public enum DEBUG_FLAGS {
		 SQL_EXEC
		,SQL_QUERYS
		,SQL_STATEMENT_FROM_SEARCH
		,SQL_ERROR
		,DEBUG_FLAG_SQL_TIMESTAMP
		,DIVERS1
		,DIVERS2
		,DIVERS3
		,MARTINS_EIGENER
		,SQLFIELDMAP_BASE_QUERY
		,FUNCTION_NEW_SELECT
		,RB_SAVE_COMPLETE_SQL_STACK
		,SHOW_VIEW_TRANSLATED_SQL_QUERYS
		
	}
	
	public static  String DEBUG_FLAG_SQL_EXEC = 		DEBUG_FLAGS.SQL_EXEC.name(); 					//"SQL_EXEC";
	public static  String DEBUG_FLAG_SQL_QUERY = 		DEBUG_FLAGS.SQL_QUERYS.name(); 					//"SQL_QUERYS";
	public static  String DEBUG_FLAG_SQL_ERROR = 		DEBUG_FLAGS.SQL_ERROR.name(); 					//"SQL_ERROR";
	public static  String DEBUG_FLAG_SQL_TIMESTAMP = 	DEBUG_FLAGS.DEBUG_FLAG_SQL_TIMESTAMP.name(); 	//"DEBUG_FLAG_SQL_TIMESTAMP";
	
	public static  String DEBUG_FLAG_DIVERS1 =	DEBUG_FLAGS.DIVERS1.name();   				 			//"DIVERS1";
	public static  String DEBUG_FLAG_DIVERS2 =	DEBUG_FLAGS.DIVERS2.name();    							//"DIVERS2";
	public static  String DEBUG_FLAG_DIVERS3 =	DEBUG_FLAGS.DIVERS3.name();    							//"DIVERS3";
	
	
	/**
	 * Debug-Flags werden jetzt aus der Web-XML gelesen.
	 * 
	 * @date   19.01.2012
	 * @return
	 */
	public static String get_Actual_DEBUG_FLAG()
	{

		//String sDebugFlags = bibALL.get_DEBUG_FLAGS();
		return bibALL.get_DEBUG_FLAGS();
		
		
//		return 	
//				DEBUG.DEBUG_FLAG_SQL_EXEC+"|"+
//				DEBUG.DEBUG_FLAG_SQL_QUERY+"|"+
//				DEBUG.DEBUG_FLAG_SQL_ERROR+"|"+
//				DEBUG.DEBUG_FLAG_SQL_TIMESTAMP+"|"+
//				DEBUG.DEBUG_FLAG_DIVERS1+"|"+
//				DEBUG.DEBUG_FLAG_DIVERS2+"|"+
//				DEBUG.DEBUG_FLAG_DIVERS3+"|"+
//				"";

//		return 	
//			DEBUG.DEBUG_FLAG_SQL_ERROR+"|"+
//			DEBUG.DEBUG_FLAG_SQL_TIMESTAMP+"|"+
//			"";

//		return 	
//			DEBUG.DEBUG_FLAG_SQL_ERROR+"|"+
//			DEBUG.DEBUG_FLAG_SQL_EXEC+"|"+
//			"";
		

//		return 	
//			DEBUG.DEBUG_FLAG_SQL_ERROR+"|"+
//			"";


	}
	
	
	

	/**
	 * 
	 * @param cWert
	 * @param cDebugFlag (wenn Leer-String, dann wird immer angezeigt)
	 */
	public static void System_println(String cWert, String cDebugFlag)
	{
		if (bibALL.get_bDebugMode() && 
			(	
					S.isEmpty(cDebugFlag) || 
							( 
									DEBUG.get_Actual_DEBUG_FLAG() != null && DEBUG.get_Actual_DEBUG_FLAG().contains(cDebugFlag)
							)
			)
		)
		{
			System.out.println(cWert);
		}
	}

	
	
	/**
	 * 
	 * @param cWert
	 * @param cDebugFlag (wenn Leer-String, dann wird immer angezeigt)
	 */
	public static void System_println(String cWert)
	{
		if (bibALL.get_bDebugMode())
		{
			System.out.println(cWert);
		}
	}

	
	/**
	 * 
	 * @param cWert
	 * @param cDebugFlag (wenn Leer-String, dann wird immer angezeigt)
	 */
	public static void System_println(String cWert, boolean separate)
	{
		if (bibALL.get_bDebugMode())
		{
			if (separate) {
				System.out.println("");
				System.out.println("");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println(cWert);
			if (separate) {
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				System.out.println("");
			}
		}
	}

	

	public static void println(char separate, String ... vals) {
		if (bibALL.get_bDebugMode()) {
			String out = "";
			for (String s: vals) {
				out = out +separate+s;
			}
			System.out.println(out);
		}
	}
	

	public static void println(String ... vals) {
		if (bibALL.get_bDebugMode()) {
			String out = "";
			for (String s: vals) {
				out = out +" "+s;
			}
			System.out.println(out);
		}
	}
	
	
	
	/**
	 * 
	 * @param cWert
	 * @param cDebugFlag (wenn Leer-String, dann wird immer angezeigt)
	 */
	public static void System_print(String cWert, String cDebugFlag)
	{
		if (bibALL.get_bDebugMode() && 
			(	
					S.isEmpty(cDebugFlag) || 
							( 
									DEBUG.get_Actual_DEBUG_FLAG() != null && DEBUG.get_Actual_DEBUG_FLAG().contains(cDebugFlag)
							)
			)
		)
		{
			System.out.print(cWert);
		}
	}

	
	public static void MetaDef_print_infos(MyMetaFieldDEF  oMetaDef) {
		System.out.println("Metadef-Table: "+oMetaDef.get_TableName()+"/Metadef-Feld: "+
					oMetaDef.get_FieldName()+"/Nullable :"+oMetaDef.get_bFieldNullableBasic()+"/ID:"+oMetaDef.hashCode());
	}

	
	
	public static void System_print(MyE2_MessageVector oMV) {
		if (bibALL.get_bDebugMode())
		{
			for (MyE2_Message oMSG: oMV) {
				System.out.println(oMSG.get_cMessage().COrig());
			}
		}

	}
	
	public static void System_print(Vector<String> vStrings) {
		if (bibALL.get_bDebugMode())
		{
			for (String oMSG: vStrings) {
				System.out.println(oMSG);
			}
		}

	}
	
	
	
	public static void System_print(Vector<String> vStrings, boolean separate) {
		if (bibALL.get_bDebugMode()) {
			if (separate) {
				System.out.println("");
				System.out.println("");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				for (String oMSG: vStrings) {
					System.out.println(oMSG);
				}
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				System.out.println("");
			} else {
				for (String oMSG: vStrings) {
					System.out.println(oMSG);
				}
			}
			
		}

	}
	
	
	public static void System_print(Vector<String> vStrings, String separate) {
		if (bibALL.get_bDebugMode()) {
			if (S.isFull(separate)) {
				System.out.println("");
				System.out.println("");
				System.out.println("---------------START   "+separate+"--------------------------------------------------------------------------------------------------------------");
				for (String oMSG: vStrings) {
					System.out.println(oMSG);
				}
				System.out.println("---------------ENDE  "+separate+"--------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				System.out.println("");
			}
			
		}

	}


	public static void System_print(Vector<String> vStrings, String separate, DEBUG_FLAGS flag) {
		if (bibALL.get_bDebugMode()  &&  (S.isEmpty(flag.name()) || (DEBUG.get_Actual_DEBUG_FLAG() != null && DEBUG.get_Actual_DEBUG_FLAG().contains(flag.name())))	) {
			if (S.isFull(separate)) {
				System.out.println("");
				System.out.println("");
				System.out.println("---------------START   "+separate+"--------------------------------------------------------------------------------------------------------------");
				for (String oMSG: vStrings) {
					System.out.println(oMSG);
				}
				System.out.println("---------------ENDE  "+separate+"--------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				System.out.println("");
			}
		}
	}

	
	
	public static void System_print(Collection<String> vStrings) {
		if (bibALL.get_bDebugMode())
		{
			for (String oMSG: vStrings) {
				System.out.println(oMSG);
			}
		}

	}


	public static void System_print2(Vector<MyString> vStrings) {
		if (bibALL.get_bDebugMode())
		{
			for (MyString oMSG: vStrings) {
				System.out.println(oMSG.COrig());
			}
		}

	}
	
	

	public static void System_print(AbstractMap<String, String> hmToPrint) {
		if (bibALL.get_bDebugMode())
		{
			for (Entry<String, String> oEntry: hmToPrint.entrySet()) {
				
				System.out.println(oEntry.getKey()+" ---- "+oEntry.getValue());
			}
		}

	}
	

	
	public static Border  debug_border() {
		Border b = new Border(new Extent(1), Color.RED, Border.STYLE_SOLID);
		return b;
	}
	
	
	
	public static void System_print_layoutInfos(LayoutData  la, String add_on_info) {
		if (bibALL.get_bDebugMode()) {
			int i_left = 0;
			int i_top = 0;
			int i_right = 0;
			int i_bottom = 0;
			if (la!=null && la instanceof GridLayoutData ) {
				GridLayoutData gl = (GridLayoutData)la;
				if (gl.getInsets()!=null) {
					i_left=gl.getInsets().getLeft().getValue();
					i_top=gl.getInsets().getTop().getValue();
					i_right=gl.getInsets().getRight().getValue();
					i_bottom=gl.getInsets().getBottom().getValue();
				}
				System.out.println("LAYOUT-DATA:   "+add_on_info+" --> "+gl.getClass().getName()+" L:"+i_left+" / T:"+i_top+" / R: "+i_right+" /B: "+i_bottom);
			} else {
				System.out.println("LAYOUT-DATA:  "+add_on_info+" UNDEFINED LAYOUTDATA -->  L:"+i_left+" / T:"+i_top+" / R: "+i_right+" /B: "+i_bottom);
			}
		}
	}
	
	
	
	public static void System_PRINT_MY_RECORD(MyRECORD rec) {
		if (bibALL.get_bDebugMode()) {
		
			System.out.println("");
			System.out.println("");
			System.out.println("---------------START RECORD  --------------------------------------------------------------------------------------------------------------");
			for (String f: rec.keySet()) {
				try {
					System.out.println("Field: "+f+"          --> "+rec.fs(f));
				} catch (myException e) {
					System.out.println("Field: "+f+"          --> Fehler");
				}
			}
			System.out.println("---------------ENDE RECORD  --------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println("");
		}	
		
	}
	
	
	public static void System_PRINT_MY_RECORD(Rec20 rec) {
		if (bibALL.get_bDebugMode()) {

			System.out.println("");
			System.out.println("");
			System.out.println("---------------START RECORD  --------------------------------------------------------------------------------------------------------------");
			for (IF_Field f: rec.keySet()) {
				try {
					System.out.println("Field: "+f.fn()+"          --> "+rec.get_fs_dbVal(f));
				} catch (myException e) {
					System.out.println("Field: "+f.fn()+"          --> Fehler");
				}
			}
			System.out.println("---------------ENDE RECORD  --------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println("");
		}	
		
	}

	
	public static void System_print_keys_of(IF_RB_Component rb_Comp, String hinweis) {
		if (bibALL.get_bDebugMode()) {

			System.out.println("");
			System.out.println("");
			System.out.println("---------------keys "+S.NN(hinweis,"-")+" --------------------------------------------------------------------------------------------------------------");
			String cNameTabelle = 		"<>";
			String cFieldname =  		"<>";
			String cHashKey =  			"<>";
			String cMaskName =   		"<>";
			String cMaskKey = 			"<>";
			
			String outText = "";
			try {
				RB_ComponentMap  mask = rb_Comp.rb_ComponentMap_this_belongsTo();
				try {
					if (rb_Comp.rb_KF()!=null) {
						cFieldname = rb_Comp.rb_KF().FIELDNAME();
						cHashKey =  rb_Comp.rb_KF().HASHKEY();
					} else {
						DEBUG.System_println("kein key: "+rb_Comp.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (mask !=null) {
					cNameTabelle = mask.rb_TABLENAME();
					RB_KM maskKey = mask.getOwnMaskKey();
					if (maskKey!=null) {
						cMaskName = maskKey.get_REALNAME();
						cMaskKey = maskKey.get_HASHKEY();
					}
				}
				
				outText = 		"DESCRIPTION: "+"<tabelle>"+cNameTabelle+":"+"<feldname>"+cFieldname+":"+"<fieldkey>"+cHashKey+":"+"<maskname>"+cMaskName+":"+"<maskkey>"+cMaskKey;
			} catch (myException e) {
				outText = "   FEHLER !!";
				e.printStackTrace();
			}
			System.out.println(outText);
			System.out.println("---------------ende  --------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println("");
		}	
		
		
	}
	
	
	
	public static void _print(String info4console) {
		DEBUG.System_println(info4console);
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 10.07.2019
	 *
	 * @param info4console
	 * @param flag
	 */
	public static void _print(String info4console, DEBUG_FLAGS flag) {
		String flagname = (flag==null?"":flag.name());
		if (S.isEmpty(flagname) || 	(DEBUG.get_Actual_DEBUG_FLAG() != null && DEBUG.get_Actual_DEBUG_FLAG().contains(flagname))) {
			DEBUG.System_println(info4console);
		}
	}
	
	
	
	public static void _print(Collection<String> info4console) {
		DEBUG.System_println("--------------               @@@block start");
		for (String c: info4console) {
			DEBUG.System_println(c);
		}
		DEBUG.System_println("---");
	}
	
	/**
	 * ausgabe in einer zeile getrennt durch sep
	 * @param info4console
	 * @param sep
	 */
	public static void _print(Collection<String> info4console, String sep) {
		String all= S.NN(sep);
		for (String c: info4console) {
			all = (all+c+sep);
		}
		DEBUG.System_println(all);
	}

	
	public static void _printTimeStamp(String text) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		DEBUG.System_println(text+":: "+f.format(new Date()));
	}
	
}
