package com.movieShop

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.containers.PostgreSQLContainer
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.equalTo
import org.springframework.http.MediaType
import com.google.gson.Gson
import com.movieShop.dto.ProducerDto
import com.movieShop.dto.MovieDto

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MovieShopApplicationTests(@Autowired val mockMvc: MockMvc) {

	val producerPath = "/producers"
	val moviePath = "/movies"

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
	fun shouldSaveProducerAndMovie() {
		val gson = Gson()
		val p = ProducerDto(1,"testeName")
		val m = MovieDto(1,"testeName","testeDesc",120.1,p)
		mockMvc.perform(post(producerPath).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(p)))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.name", equalTo("testeName")))
		mockMvc.perform(post(moviePath).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(m)))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.name", equalTo("testeName")))
		mockMvc.perform(get(moviePath).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$", hasSize<Int>(1)))
			.andExpect(jsonPath("$[0].name", equalTo("testeName")))	
	}

	@Test
	fun shouldReturnErrorWhenUpdateMovieNonexistent() {
		val gson = Gson()
		val p = ProducerDto(2,"testeName1")
		val m = MovieDto(2,"testeName1","testeDesc1",120.1,p)
		mockMvc.perform(post(producerPath).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(p)))
			.andExpect(status().is2xxSuccessful())
		mockMvc.perform(put("$moviePath/${m.id}").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(m)))
			.andExpect(status().is5xxServerError())
	}

	@Test
	fun shouldReturnErrorWhenUpdateProducerNonexistent() {
		val gson = Gson()
		val p = ProducerDto(3,"testeName1")
		mockMvc.perform(put("$producerPath/${p.id}").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(p)))
			.andExpect(status().is5xxServerError())
	}

	@Test
	fun shouldReturnErrorWhenSaveMovieWithProducerNonexistent() {
		val gson = Gson()
		val p = ProducerDto(4,"testeName2")
		val m = MovieDto(3,"testeName2","testeDesc2",120.1,p)
		mockMvc.perform(post(moviePath).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(m)))
			.andExpect(status().is5xxServerError())
	}
}
