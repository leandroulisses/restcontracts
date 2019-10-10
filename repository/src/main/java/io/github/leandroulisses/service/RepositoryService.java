package io.github.leandroulisses.service;

import io.github.leandroulisses.config.SshTransportConfigCallback;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

    @Value("${git.repository.uri.ssh}")
    private String repository;
    private SshTransportConfigCallback sshTransportConfigCallback;

    public RepositoryService(SshTransportConfigCallback sshTransportConfigCallback) {
        this.sshTransportConfigCallback = sshTransportConfigCallback;
    }

    public void download() {
        try {
            Git.cloneRepository()
                    .setURI(repository)
                    .setTransportConfigCallback(sshTransportConfigCallback)
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

}
