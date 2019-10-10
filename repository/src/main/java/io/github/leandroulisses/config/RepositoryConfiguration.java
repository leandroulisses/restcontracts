package io.github.leandroulisses.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application-repository.properties"})
public class RepositoryConfiguration {}
