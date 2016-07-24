package fr.sysf.sample.eurekaconfig

import fr.sysf.sample.eurekadiscovery.ApplicationConfig
import org.junit.runner.RunWith
import org.junit.{Assert, Test}
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.{IntegrationTest, SpringApplicationConfiguration, TestRestTemplate}
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

/**
 * @author Florent Peyron at 27/03/2016
 */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[ApplicationConfig]))
@WebAppConfiguration
@IntegrationTest(Array("server.port=0"))
class DiscoveryServiceApplicationTest {

  @Value("${local.server.port}")
  private var port = 0

  @Test
  def catalogLoads() {
    val entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/eureka/apps", classOf[String])
    Assert.assertEquals(HttpStatus.OK, entity.getStatusCode)
  }

  @Test
  def adminLoads() {
    val entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/env", classOf[String])
    Assert.assertEquals(HttpStatus.OK, entity.getStatusCode)
  }
}
