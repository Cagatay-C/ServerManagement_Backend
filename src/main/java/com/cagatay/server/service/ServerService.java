package com.cagatay.server.service;

import java.io.IOException;
import java.util.Collection;

import com.cagatay.server.model.Server;

public interface ServerService {
	Server create(Server server);
	Server ping(String ipAddress) throws IOException;
	Collection<Server> list();
	Server get(Long id);
	Server update(Server server);
	Boolean delete(Long id);
}
