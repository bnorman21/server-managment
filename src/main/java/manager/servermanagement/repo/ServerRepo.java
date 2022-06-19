package manager.servermanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import manager.servermanagement.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long> {
    //want to select by parameter of the function
    //IpAddress will be unique so all good
    //Using JpaRepository will allow you to save, delete, and update data in the database
    Server findByIpAddress(String IpAddress);

}
