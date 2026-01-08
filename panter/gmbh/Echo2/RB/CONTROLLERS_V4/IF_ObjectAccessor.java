/**
 * panter.gmbh.Echo2.RB.CONTROLLERS_V4
 * @author manfred
 * @date 30.09.2020
 * 
 */
package panter.gmbh.Echo2.RB.CONTROLLERS_V4;

/**
 * Funktionales Interface um die von den RB_BtV4... erzeugten Objekte zu manipulieren, 
 * Die Objekte müssen über Getter-Objekte zugreifbar sein. 
 * 
 * @author manfred
 * @date 30.09.2020
 *
 */
public interface IF_ObjectAccessor<T>{
	
	public void doAnythingWith(T o);

}
