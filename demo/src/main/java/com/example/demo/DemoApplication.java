package com.example.demo;

import com.example.demo.dto.ConversionDTO;
import com.example.demo.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication  {
		public static void main(String[] args) {
			SpringApplication.run(DemoApplication.class, args);
		}


}
