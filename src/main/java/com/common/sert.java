package stest.tron.wallet.common.client.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ZenUtils {

  public static List<String> getListFromFile(final String fileName) {
    List<String> list = new ArrayList<>();
    try {
      FileInputStream inputStream = new FileInputStream(fileName);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        System.out.println(str);
        list.add(str);
      }
      inputStream.close();
      bufferedReader.close();
    } catch (Exception e) {
      if (e.getMessage() != null) {
        System.out.println(e.getMessage());
      } else {
        System.out.println(e.getClass());
      }
    }
    return list;
  }
