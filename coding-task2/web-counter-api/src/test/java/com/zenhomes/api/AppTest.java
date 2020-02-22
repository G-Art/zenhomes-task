package com.zenhomes.api;


import com.zenhomes.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class AppTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void tesstSendConsumptionForValidCounter() throws Exception {
		mvc.perform(post("/counter_callback")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\n" +
				"\"counter_id\": \"14\",\n" +
				"\"amount\": 100.123\n" +
				"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testSendConsumptionForInvalidCounter() throws Exception {
		mvc.perform(post("/counter_callback")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\n" +
						"\"counter_id\": \"000\",\n" +
						"\"amount\": 100.123\n" +
						"}"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testReportRequest() throws Exception {
		loadTestData();

		mvc.perform(get("/consumption_report?duration=24h"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("villages[0].consumption").value(100.123d));
	}

	private void loadTestData() throws Exception {
		mvc.perform(post("/counter_callback")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\n" +
						"\"counter_id\": \"1\",\n" +
						"\"amount\": 100.123\n" +
						"}"))
				.andExpect(status().isOk());


	}
}