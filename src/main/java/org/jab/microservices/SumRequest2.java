package org.jab.microservices;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SumRequest2 {

    @NotNull
    @Pattern(regexp="^[0-9]{0,3}$")
    private String string1;
    @NotNull
    @Pattern(regexp="^[0-9]{0,3}$")
    private String string2;

    public SumRequest2() {
    }

    public SumRequest2(String string1, String string2) {
        this.string1 = string1;
        this.string2 = string2;
    }

    public String getstring1() {
        return string1;
    }

    public String getstring2() {
        return string2;
    }

}
