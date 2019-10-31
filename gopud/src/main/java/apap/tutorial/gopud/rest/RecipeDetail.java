package apap.tutorial.gopud.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeDetail {

    private Integer id;

    private String title;

    @JsonProperty("readyInMinutes")
    private Integer readyInServing;

    @JsonProperty("servings")
    private Integer servings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReadyInServing() {
        return readyInServing;
    }

    public void setReadyInServing(Integer readyInServing) {
        this.readyInServing = readyInServing;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }
}
