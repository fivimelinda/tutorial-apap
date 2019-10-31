package apap.tutorial.gopud.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDetail {

    @JsonProperty("results")
    private List<ResultDetail> listResultDetails;

    public List<ResultDetail> getListResultDetails() {
        return listResultDetails;
    }

    public void setListResultDetails(List<ResultDetail> listResultDetails) {
        this.listResultDetails = listResultDetails;
    }
}
