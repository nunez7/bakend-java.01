package mx.com.vass.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MetodosController {

	// Lista de lenguajes de programación
	private final List<String> languages = Arrays.asList("Java", "Python", "JavaScript", "C#", "C++", "Ruby", "Go", "Kotlin",
			"Swift", "PHP");

	@GetMapping("/lenguajes-programacion")
	public Map<String, Object> getLanjuagesProgramacion() {
		// Construir la respuesta
		Map<String, Object> response = new HashMap<>();
		response.put("head", "Lista de lenguajes");
		response.put("body", languages);
		return response;
	}

	@GetMapping("/lenguajes-programacion/{nombre}")
	public ResponseEntity<Object> getLenguajePorNombre(@PathVariable String nombre) {
		// Buscar el lenguaje en la lista (case insensitive)
		Optional<String> language = languages.stream().filter(lang -> lang.equalsIgnoreCase(nombre)).findFirst();
		
		if (language.isPresent()) {
			Map<String, Object> response = new HashMap<>();
			response.put("head", "Lenguaje Encontrado");
			response.put("body", language.get());
			return ResponseEntity.ok(response);
		} else {
			// Si no existe, devolver un 404
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", "Lenguaje no encontrado");
			errorResponse.put("message", "El lenjuage '" + nombre + "' no existe en la DB.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
}
