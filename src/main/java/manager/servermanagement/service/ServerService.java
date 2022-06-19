package manager.servermanagement.service;
import manager.servermanagement.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;
//laying down functionality for the interface
public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
