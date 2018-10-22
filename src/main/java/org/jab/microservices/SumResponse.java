package org.jab.microservices;

public class SumResponse {

    private Integer result;

    public SumResponse() {
    }


    public SumResponse(Integer result) {
        this.result = result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }
}
