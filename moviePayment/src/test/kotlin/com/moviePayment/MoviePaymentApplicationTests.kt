package com.moviePayment

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.Assert
import org.springframework.http.MediaType
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.containers.PostgreSQLContainer
import com.moviePayment.repository.MoviePaymentRepository
import com.moviePayment.service.MoviePaymentService
import com.moviePayment.domain.MoviePayment

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MoviePaymentApplicationTests(@Autowired val moviePaymentRepository: MoviePaymentRepository,
	@Autowired val moviePaymentService: MoviePaymentService,
	@Autowired val mockMvc: MockMvc) {

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

	fun getMoviePayment(): MoviePayment {
		return MoviePayment(1L, 5.0, 1L, false)
	}

	@Test
	fun shouldSaveMoviePayment() {
		val movie = moviePaymentRepository.save(getMoviePayment())
		Assert.notNull(movie.id, "id movie shouldn't be null")
		Assert.notNull(movie.movieId, "movieId movie shouldn't be null")
		moviePaymentRepository.deleteById(movie.id!!)
	}

	@Test
	fun shouldProcessPayment() {
		val movie = moviePaymentRepository.save(getMoviePayment())
		moviePaymentService.processPayment(movie)
		Assert.isTrue(moviePaymentRepository.getById(movie.id!!).paymentCompleted!!, "payment completed should be true")
		moviePaymentRepository.deleteById(movie.id!!)
	}

	@Test
	fun shouldSaveAndReturnOneMoviePayments() {
		moviePaymentRepository.deleteAll()
		val movie = moviePaymentRepository.save(getMoviePayment())
		mockMvc.perform(get("/movie-payments/${movie.movieId}")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.movieId", equalTo(Integer(movie.movieId.toString()))))
	}

}
