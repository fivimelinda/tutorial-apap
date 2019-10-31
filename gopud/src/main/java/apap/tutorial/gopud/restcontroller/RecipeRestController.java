package apap.tutorial.gopud.restcontroller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tutorial.gopud.service.RecipeRestService;
import reactor.core.publisher.Mono;
import apap.tutorial.gopud.rest.ResultDetail;

@RestController
@RequestMapping("/api/v1")
public class RecipeRestController {
	@Autowired
	private RecipeRestService recipeRestService;
	
	@GetMapping(value="/recipe")
	private Mono<ResultDetail> retrieveRecipe(@RequestParam("excludeIngredients") String excludeIngredients) {
		try {
			return recipeRestService.getRecipe(excludeIngredients);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Recipe " +  excludeIngredients +" Not Found");
		}
	}
}
