package com.example.demo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoProperties {

@Value("${name}")
	private String name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
}
