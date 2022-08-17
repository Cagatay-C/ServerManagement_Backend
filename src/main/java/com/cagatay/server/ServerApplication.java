package com.cagatay.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cagatay.server.enumeration.Status;
import com.cagatay.server.model.Server;
import com.cagatay.server.repo.ServerRepo;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null,"192.168.1.160","Ubuntu Linux","16 GB","Personal Pc","",
					Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.50","Fedora Linux","16 GB","Dell Tower","",
					Status.SERVER_DOWN));
			serverRepo.save(new Server(null,"192.168.1.45","MS 2010","32 GB","Web Server","",
					Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.63","Red Hat Linux","64 GB","Mail Server","",
					Status.SERVER_DOWN));
		};
	}
}
