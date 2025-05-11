package utils;

/**
 * A simple tuple class
 *
 * @param <X> the type of the first element
 * @param <Y> the type of the second element
 * @author Amit Joseph, Maya heilborn
 */
public class Tuple<X, Y> {
	/**
	 * the first item in the tuple
	 */
	public X first;
	/**
	 * the second item in the tuple
	 */
	public Y second;

	/**
	 * Constructor
	 *
	 * @param first  the first element
	 * @param second the second element
	 */
	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
}