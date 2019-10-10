package io.github.leandroulisses.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryServiceTest {

    @Autowired
    private RepositoryService service;

    @Test
    public void should_download_repository() {
        service.download();
    }

}