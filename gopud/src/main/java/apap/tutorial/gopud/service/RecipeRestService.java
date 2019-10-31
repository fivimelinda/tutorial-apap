package apap.tutorial.gopud.service;

import apap.tutorial.gopud.rest.ResultDetail;
import reactor.core.publisher.Mono;

public interface RecipeRestService {
	Mono<ResultDetail> getRecipe(String excludeIngredients);
}
