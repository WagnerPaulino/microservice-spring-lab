package com.movieSchedule.movieSchedule

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.containers.PostgreSQLContainer
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.equalTo
import com.movieSchedule.movieSchedule.repository.MovieOrderRepository
import com.movieSchedule.movieSchedule.domain.MovieOrder
import java.time.LocalDate
import springfox.documentation.service.MediaTypes

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MovieScheduleApplicationTests(@Autowired val movieOrderRepository: MovieOrderRepository, @Autowired val mockMvc: MockMvc) {

	companion object {
 
		@Container
		val container = PostgreSQLContainer<Nothing>("postgres:9.6.23").apply {
		  withDatabaseName("test")
		  withUsername("admin")
		  withPassword("1")
		}

		@JvmStatic
		@DynamicPropertySource
		fun properties(registry: DynamicPropertyRegistry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
		}
	  }

	@Test
	fun shouldSaveAndReturnAllMovieOrders() {
		movieOrderRepository.save(MovieOrder(1,LocalDate.now(),LocalDate.now().plusDays(3),1))
		mockMvc.perform(get("/movie-orders")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$", hasSize<Int>(1)))
			.andExpect(jsonPath("$[0].movieId", equalTo(1)))
	}

}
