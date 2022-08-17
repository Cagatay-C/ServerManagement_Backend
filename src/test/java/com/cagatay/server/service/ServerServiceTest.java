package com.cagatay.server.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.cagatay.server.enumeration.Status;
import com.cagatay.server.model.Server;
import com.cagatay.server.repo.ServerRepo;
import com.cagatay.server.service.implementation.ServerServiceImpl;

@ExtendWith(MockitoExtension.class)
class ServerServiceTest {
	
	@Mock
	private ServerRepo serverRepo;
	private ServerService underTest;

	@BeforeEach
	void setUp() {
		underTest = new ServerServiceImpl(serverRepo);
	}

	@Test
	void testCreate() {
		//given
		Server server = new Server(null,"192.168.1.4",
				"Windows 2010",
				"64GB","Home pc",
				"Image1.png",
				Status.SERVER_UP
		);
		//when
		underTest.create(server);
		//then
		ArgumentCaptor<Server> serverArgumentCapture = 
				ArgumentCaptor.forClass(Server.class);
		
		verify(serverRepo).save(serverArgumentCapture.capture());
		
		Server capturedServer = serverArgumentCapture.getValue();
		
		assertThat(capturedServer).isEqualTo(server);
	}

	@Test
	void testList() {
		//when
		underTest.list();
		//then
		verify(serverRepo).findAll();
	}

	@Test
	void testGet() {
		long serverID = new Random().nextLong();
		Server serverOffice = new Server(serverID,"192.168.1.19",
				"Linux",
				"16GB","Office",
				"Image1.png",
				Status.SERVER_DOWN
		);
		given(serverRepo.findById(serverID)).willReturn(Optional.of(serverOffice));
		
		Optional<Server> serverOptional = Optional.of(underTest.get(serverID));
		
		assertThat(serverOptional.isPresent()).isTrue();
		Server server = serverOptional.get();
		
		assertServerFields(server);
	}
	
	private void assertServerFields(Server server) {
		assertThat(server.getIpAddress()).isEqualTo("192.168.1.19");
		assertThat(server.getName()).isEqualTo("Linux");
		assertThat(server.getMemory()).isEqualTo("16GB");
		assertThat(server.getType()).isEqualTo("Office");
		assertThat(server.getImageUrl()).isEqualTo("Image1.png");
		assertThat(server.getStatus()).isEqualTo(Status.SERVER_DOWN);
		assertThat(server.getId()).isNotNull();
	}
	
	@Test
	void testUpdate() {
		//given
		Server server = new Server(null,"192.168.1.7",
				"Linux",
				"16GB","Office",
				"Image1.png",
				Status.SERVER_UP
		);
		//when
		underTest.update(server);
		//then
		ArgumentCaptor<Server> serverArgumentCaptor =
				ArgumentCaptor.forClass(Server.class);
		
		verify(serverRepo).save(serverArgumentCaptor.capture());
		
		Server capturedServer = serverArgumentCaptor.getValue();
		
		assertThat(capturedServer).isEqualTo(server);
	}

	@Test
	void testDelete() {
		//when
		underTest.delete(1l);
		//then
		verify(serverRepo).deleteById(1l);
	}

	@Test
	void testPing() {
	}

}
