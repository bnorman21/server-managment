package manager.servermanagement;

import manager.servermanagement.enumeration.Status;
import manager.servermanagement.model.Server;
import manager.servermanagement.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static manager.servermanagement.enumeration.Status.*;

@SpringBootApplication
public class ServerManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerManagementApplication.class, args);
    }
    @Bean
    CommandLineRunner run(ServerRepo serverRepo) {
        return args -> {
            serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC",
                    "http://localhost:8080/server/image/server1.png", SERVER_UP));
            serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "16 GB", "Dell Tower",
                    "http://localhost:8080/server/image/server1.png", SERVER_UP));
            serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "32 GB", "Web Server",
                    "http://localhost:8080/server/image/server1.png", SERVER_UP));
            serverRepo.save(new Server(null, "192.168.1.14", "Red Hat Enterprise Linux", "64 GB", "Mail Server",
                    "http://localhost:8080/server/image/server1.png", SERVER_UP));
        };
    }

}
