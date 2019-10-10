package io.github.leandroulisses.config;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

  public static final String PREFIX = "passphrase";
  public static final String SUFFIX = ".tmp";

  public static File stream2file(InputStream in) throws IOException {
    final File tempFile = File.createTempFile(PREFIX, SUFFIX);
    tempFile.deleteOnExit();
    try (FileOutputStream out = new FileOutputStream(tempFile)) {
      IOUtils.copy(in, out);
    }
    return tempFile;
  }

}