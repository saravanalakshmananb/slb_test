package com.example.demo;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.properties.DemoProperties;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@RestController
class HelloController {
	@Autowired 
private DemoProperties properties1;

	@GetMapping("/hello")
	public String hello(@RequestParam(defaultValue="Azure Power Launch") String name){
		return String.format("<h1> Hello %s is from Azure Spring Cloud </h1>",name +" "+ properties1.getName());
		
	}
	
	@GetMapping("/CopytoTemp")
public void copy2Temp() {
    
		Path tempDir;
    try {
        tempDir = Files.createTempDirectory("FedEx");
        List<String> fileNames = Arrays.asList("File1.txt", "File2.txt", "File3.txt");
        for (String fileName : fileNames) {
            try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream("FedEx/" + fileName)) {
                Files.copy(Objects.requireNonNull(stream), tempDir.resolve(fileName));
                           }
            System.out.println(tempDir.toAbsolutePath().toString().replace("\\", "/"));
        }
    } catch (Exception exception) {
        System.out.println(exception.getMessage());
                //throw new InventoryServiceException(exception);
    }
	}
}

