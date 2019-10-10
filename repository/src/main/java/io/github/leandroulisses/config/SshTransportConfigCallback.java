package io.github.leandroulisses.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.util.FS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class SshTransportConfigCallback implements TransportConfigCallback {

  @Value("${secret.pass.phrase}")
  private String secretPassPhrase;

  private final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {

    @Override
    protected void configure(OpenSshConfig.Host hc, Session session) {
      session.setConfig("StrictHostKeyChecking", "no");
    }

    @Override
    protected JSch createDefaultJSch(FS fs) throws JSchException {
      InputStream is = new ByteArrayInputStream(secretPassPhrase.getBytes());
      try {
        File file = StreamUtil.stream2file(is);
        JSch jSch = super.createDefaultJSch(fs);
        jSch.addIdentity(file.getPath());
        return jSch;
      } catch (IOException e) {
        throw new RuntimeException("Error to find [secret-passphrase] file");
      }
    }
  };

  @Override
  public void configure(Transport transport) {
    SshTransport sshTransport = (SshTransport) transport;
    sshTransport.setSshSessionFactory(sshSessionFactory);
  }

}