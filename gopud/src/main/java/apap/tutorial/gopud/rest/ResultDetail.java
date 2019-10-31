package apap.tutorial.gopud.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDetail {

    @JsonProperty("results")
    private List<RecipeDetail> listRecipeDetails;

    public List<RecipeDetail> getListRecipeDetails() {
        return listRecipeDetails;
    }

    public void setListRecipeDetails(List<RecipeDetail> listRecipeDetails) {
        this.listRecipeDetails = listRecipeDetails;
    }
}
