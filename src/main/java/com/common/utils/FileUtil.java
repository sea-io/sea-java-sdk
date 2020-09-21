
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;


public class FileUtil {

	public static File touch(String fullFilePath) throws IOException {
		File file = new File(fullFilePath);
		file.getParentFile().mkdirs();
		if(!file.exists()) file.createNewFile();
		return file;
	}
	

	public static File mkdir(String dirPath){
		File dir = new File(dirPath);
		dir.mkdirs();
		return dir;
	}
	

	public static String getAbsolutePath(String path, Class<?> baseClass){
		if(path == null) return null;
		if(baseClass == null) {
			ClassLoader classLoader = FileUtil.class.getClassLoader();
			URL url = classLoader.getResource(path);
			if(url != null) {
				return url.getPath();
			}else {
				return classLoader.getResource("").getPath() + path;
			}
		}
		return baseClass.getResource(path).getPath();
	}
	

	public static String getAbsolutePath(String pathBaseClassLoader){
		return getAbsolutePath(pathBaseClassLoader, null);
	}

	public static boolean isExist(String path){
		return  new File(path).exists();
	}
	

	public static void close(Closeable closeable){
		if(closeable == null) return;
		try {
			closeable.close();
		} catch (IOException e) {
		}
	}
	

	public static BufferedWriter getBufferedWriter(String path, String charset, boolean isAppend) throws IOException {
		return new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(touch(path), isAppend), charset
					)
		);
	}
	

	public static PrintWriter getPrintWriter(String path, String charset, boolean isAppend) throws IOException {
		return new PrintWriter(getBufferedWriter(path, charset, isAppend));
	}


	public static OutputStream getOutputStream(String path) throws IOException {
		return new FileOutputStream(touch(path));
	}

	public static void cleanDir(String dirPath){
		File dir = new File(dirPath);
		if(dir.exists() && dir.isDirectory()){
			File[] files = dir.listFiles();
			for (File file : files) {
				if(file.isDirectory()) cleanDir(file.getAbsolutePath());
				file.delete();
			}
		}
	}

	public static BufferedReader getReader(String path, String charset) throws IOException{
		return new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
	}
	

	public static <T extends Collection<String>> T loadFileLines(String path, String charset, T collection) throws IOException{
		BufferedReader reader = getReader(path, charset);
		while(true){
			String line = reader.readLine();
			if(line == null) break;
			collection.add(line);
		}
		close(reader);
		return collection;
	}
	

	public static <T> T loadDataFromfile(ReaderHandler<T> readerHandler, String path, String charset) throws IOException {
		BufferedReader reader = null;
		T result = null;
		try {
			reader = getReader(path, charset);
			result = readerHandler.handle(reader);
		} catch (IOException e) {
			throw new IOException(e);
		}finally {
			FileUtil.close(reader);
		}
		return result;
	}
	

	public static String getExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			return "";
		} else {
			String ext = fileName.substring(index + 1);
			
			return (ext.contains("/") || ext.contains("\\")) ? "" : ext;
		}
	}
	
	public interface ReaderHandler<T> {
		public T handle(BufferedReader reader) throws IOException;
	}
}
