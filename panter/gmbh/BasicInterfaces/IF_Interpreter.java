/**
 * panter.gmbh.BasicInterfaces
 * @author martin
 * @date 10.11.2020
 * 
 */
package panter.gmbh.BasicInterfaces;

/**
 * @author martin
 * @date 10.11.2020
 *
 */
public interface IF_Interpreter<T, R> {
	public R getResult(T value);
}
