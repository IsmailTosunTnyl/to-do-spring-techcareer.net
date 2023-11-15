package com.todotechcareer.techcareer;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
class TechcareerApplicationTests {
	private static MockHttpServletRequest request;

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private MockMvc mockMvc;

	@Value("${sql.script.create.todos}")
	private String sqlAddToDos;

	@Value("${sql.script.delete.todos}")
	private String sqlDeleteToDos;

	@Value("${sql.script.create.todo_table}")
	private String sqlCreateToDoTable;

	@BeforeAll
	static void setUpAll() {
		request = new MockHttpServletRequest();
	}
	@BeforeEach
	void setUp() throws Exception {
		jdbc.execute(sqlAddToDos);

	}

	@AfterEach
	void tearDown() throws Exception {
		jdbc.execute("DROP TABLE IF EXISTS todo_table");
		jdbc.execute(sqlCreateToDoTable);


	}
	@Test
	@DisplayName("Test Hello from Spring API")
	void helloWorld() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Hello from Spring API, this API used with React frontend for ToDo App"))
				.andExpect(jsonPath("$.author").value("Ismail Tosun"));
	}

	@Test
	@DisplayName("Test Get all ToDos")
	void getAllTodos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].content").value("ToDo 1"))
				.andExpect(jsonPath("$[0].status").value(false))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].content").value("ToDo 2"))
				.andExpect(jsonPath("$[1].status").value(true));
	}

	@Test
	@DisplayName("Post a new ToDo")
	void postNewToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
				.content("{\"content\":\"ToDo 4\",\"status\":false}")
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(4))
				.andExpect(jsonPath("$.content").value("ToDo 4"))
				.andExpect(jsonPath("$.status").value(false));


	}

	@Test
	@DisplayName("Delete a ToDo")
	void deleteToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.content").value("ToDo 1"))
				.andExpect(jsonPath("$.status").value(false));
	}

	@Test
	@DisplayName("Update a ToDo")
	void updateToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/api/todo/1")
				.content("{\"content\":\"ToDo 1 Updated\",\"status\":true}")
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.content").value("ToDo 1 Updated"))
				.andExpect(jsonPath("$.status").value(true));
	}

	@Test
	@DisplayName("Delete Done ToDos")
	void deleteDoneTodos () throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/done"))
				.andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].content").value("ToDo 1"))
				.andExpect(jsonPath("$[0].status").value(false))
				.andExpect(jsonPath("$[1].id").value(3))
				.andExpect(jsonPath("$[1].content").value("ToDo 3"))
				.andExpect(jsonPath("$[1].status").value(false));
	}

	@Test
	@DisplayName("Delete All ToDos")
	void deleteAllTodos () throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo"))
				.andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

}
