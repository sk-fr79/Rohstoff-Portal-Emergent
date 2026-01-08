package panter.gmbh.indep.dataTools.query;

/**
 * Datatools.query
 * 
 * Purpose:
 * This package offers functionality to write SQL statements ({@link SELECT},
 * {@link INSERT}, {@link UPDATE} and {@link DELETE}) in an object-oriented 
 * manner, using a chained syntax known from the Builder pattern or the 
 * jQuery API.
 * 
 * The four basic operations all inherit from a {@link Query} class.
 * 
 * The main idea is that one can build queries by chaining arguments, and 
 * finally applying the {@code toString()} method, yielding an SQL query string.
 * 
 * The basic usage is 
 * <code>
 * SELECT sel = new SELECT("column_1", "column_2", "column_n")
 *              .from("tablename", "tableAlias")
 *              .join("joinTable", "joinedAlias")
 *              	.on("tableAlias.column_i", "=", "joinedAlias.other_column")
 *              .where("column_3", "=", 100)
 *              .groupBy("column_6")
 *              .orderBy("column_9").desc();
 * String sqlResult = sel.toString();
 * </code>
 * Resulting in that {@code sqlResult} is something the like
 * <code>
 * SELECT column_1, column_2, column_n 
 * 	  FROM tableName tableAlias
 *    JOIN joinTable joinedAlias ON tableAlias.column1 = joinedAlias.other_column
 *    WHERE column_3 = 100
 *    GROUP BY column_6
 *    ORDER BY column_9 DESC
 * </code>
 * <p>
 * Many more examples can be found in the test class {@link NiceSQLTest}, and more
 * documentation can be found in the formerly mentioned classes.
 * <p>
 * Note that the strings provided in the specific functions are <b>interpreted</b> 
 * in their specific context, because they can have different meanings according to their
 * usage's context. <b>As what they are interpreted is indicated in the JavaDoc
 * on the specific method</b>. 
 * <p>
 * Basically, there are three ways in which the arguments to methods interprete them:
 * (1) as a <b>column/field identifier</b> (@link ID} in the context where column 
 *     identifiers occur, specifically in the {@link SELECT#select(Object...)} method, 
 *     the {@link SELECT#on(Object, Object)} method, or the left handside
 *     of the {@link UPDATE#set(String, String)} method.
 * (2) as a <b>table name identifier</b> (@link TID} in the context where a table is 
 *     specified, for example in the {@link SELECT#from(Object...)} method, in the
 *     {@link panter.gmbh.indep.dataTools.query.UPDATE} constructor.
 * (3) as a <b>value</b> object, like in the right handsides of any {@link SELECT#where}
 *     method's argument, or the right handside of the {@link UPDATE#set(String, String)} 
 *     method.
 * Anytime, one of the three cases apply, and if one wants to change this, one must
 * explicitly encapsulate the string into an {@link ID}, {@link TID} or {@link V}
 * object, like in the following example:
 * <code>
 * SELECT sel = new SELECT(new V("1").as("one_number_constant"), "t1.col_x")
 * 					.from("table t1")
 * 					.where("t1.column_a", "=", new ID("t1.column_b")
 * </code>
 * Resulting in (when toString()'ed) 
 * <code>
 * SELECT 1 AS one_number_constant, t1.col_x FROM table t1 WHERE t1.column_a = t1.column_b
 * </code>
 * A conveniant and conststent way is to use the {@link Term} factory class, which 
 * provides the methods 
 * - {@link panter.gmbh.indep.dataTools.query.Term#field(String)} for colum/field names
 * - {@link panter.gmbh.indep.dataTools.query.Term#field(String, String)} for aliased field names
 * - {@link panter.gmbh.indep.dataTools.query.Term#table(String)} for table names
 * - {@link panter.gmbh.indep.dataTools.query.Term#field(String, String)} for aliased field names
 * - {@link panter.gmbh.indep.dataTools.query.Term#value(String)} for values
 * - {@link panter.gmbh.indep.dataTools.query.Term#value(String, String)} for aliased values
 * - {@link panter.gmbh.indep.dataTools.query.Term#unquoted(String)} for unquoted values
 * - {@link panter.gmbh.indep.dataTools.query.Term#unquoted(String, String)} for aliased unquoted values
 * <p>
 * Every child of {@link Query} can be constructed with an empty constructor
 * (for creating the object itself), and then be chained whe constructor's name
 * in lowercase, to specify more arguments that would have been specified by 
 * the constructor. Consider
 * <code>
 * SELECT sel = new SELECT();
 * sel.select("a", "b", "c").from("t");
 * </code>
 * Is essentially the same as
 * <code>
 * SELECT sel = new SELECT("a", "b", "c").from(t); 
 * </code>
 * <p>
 * 
 */