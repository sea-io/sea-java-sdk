
public class FileUtil {
  public static File mkdir(String dirPath){
      File dir = new File(dirPath);
      dir.mkdirs();
      return dir;
    }
}
