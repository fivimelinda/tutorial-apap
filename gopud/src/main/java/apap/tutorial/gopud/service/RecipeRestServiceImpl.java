package apap.tutorial.gopud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tutorial.gopud.rest.ResultDetail;
import apap.tutorial.gopud.rest.Setting;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class RecipeRestServiceImpl implements RecipeRestService{
	private final WebClient webClient;
	
	public RecipeRestServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(Setting.recipeUrl).build();
	}
	
	@Override
	public Mono<ResultDetail> getRecipe(String excludeIngredient){
		return this.webClient.get().uri("?excludeIngredients=" + excludeIngredient).retrieve().bodyToMono(ResultDetail.class);
	}
}
