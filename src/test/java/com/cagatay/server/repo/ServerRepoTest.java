package com.cagatay.server.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cagatay.server.enumeration.Status;
import com.cagatay.server.model.Server;

@DataJpaTest
public class ServerRepoTest {
	
	@Autowired
	private ServerRepo underTest;
	
	@Test
	void itShouldFindServerByIpaddress() {
		//given
		String ipAddress = "192.168.1.105";
		Server server = new Server(null,ipAddress,
				"Linux ubuntu",
				"64GB","Home pc",
				"Image1.png",
				Status.SERVER_UP
		);
		underTest.save(server);
		
		//when
		String expected = underTest.findByIpAddress(ipAddress).getIpAddress();
		//then
		assertThat(expected).isEqualTo("192.168.1.105");
	}
}

