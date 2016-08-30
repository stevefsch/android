import java.io.File;

/**
 * 
 */

/**
 * @author Steve
 *
 */
public interface FileNameFilter {
	public abstract boolean accept(File direcoty, String filename);
}
