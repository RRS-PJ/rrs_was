package com.korit.projectrrs;

import com.korit.projectrrs.component.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileUploadProperties.class)
public class ProjectRrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectRrsApplication.class, args);
	}

}
