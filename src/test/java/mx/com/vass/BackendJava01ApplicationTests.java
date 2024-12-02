package mx.com.vass;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import mx.com.vass.controllers.MetodosController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetodosController.class)
class BackendJava01ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetProgrammingLanguages() throws Exception {
		mockMvc.perform(get("http://localhost:8080/api/lenguajes-programacion")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().json("""
						        {
						            "head": "Lista de lenguajes",
						            "body": [
						                "Java",
						                "Python",
						                "JavaScript",
						                "C#",
						                "C++",
						                "Ruby",
						                "Go",
						                "Kotlin",
						                "Swift",
						                "PHP"
						            ]
						        }
						"""));
	}

	@Test
	public void testGetLanguageByName_Found() throws Exception {
		mockMvc.perform(get("http://localhost:8080/api/lenguajes-programacion/Java")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().json("""
						        {
						            "head": "Lenguaje Encontrado",
						            "body": "Java"
						        }
						"""));
	}

	@Test
	public void testGetLanguageByName_NotFound() throws Exception {
		mockMvc.perform(get("http://localhost:8080/api/lenguajes-programacion/Scala")).andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().json("""
						        {
						            "error": "Lenguaje no encontrado",
						            "message": "El lenjuage 'Scala' no existe en la DB."
						        }
						"""));
	}

}
