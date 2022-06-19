package manager.servermanagement.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import manager.servermanagement.enumeration.Status;
import manager.servermanagement.model.Server;
import manager.servermanagement.repo.ServerRepo;
import manager.servermanagement.service.ServerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
    private final ServerRepo serverRepo;
    @Override
    public Server create(Server server) {
        // log something into the console
        log.info("Saving new server: {}", server.getName());
        // set the image of the server because we will not ask user for the image
        server.setImageUrl(setServerImageUrl());
        // save server into the database and return it
        return serverRepo.save(server);
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP: Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();

    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        // log something into the console
        log.info("Updating server: {}", server.getName());
        // save server into the database and return it
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.jpg", "server2.jpg", "server3.jpg", "server4.jpg"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
