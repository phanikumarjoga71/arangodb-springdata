package com.demo.arangodbtest.config;

import org.springframework.context.annotation.Configuration;
import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;

@Configuration
@EnableArangoRepositories(basePackages = "com.demo.arangodbtest.repository")
public class ArangoDbConfiguration implements ArangoConfiguration{
	
	@Override
	public ArangoDB.Builder arango() {
	    return new ArangoDB.Builder()
	      .host("127.0.0.1", 8529)
	      .user("root").password("");
	}
	
	@Override
	public String database() {
	    return "demo";
	}
}
