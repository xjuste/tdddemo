package org.jab.microservices;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class SumController {

    @PostMapping(value = "/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody SumResponse sum(@RequestBody SumRequest sumRequest) throws Exception {
        if (sumRequest.getNumber1() == null && sumRequest.getNumber2() == null) {
            return new SumResponse(0);
        }

        if ((sumRequest.getNumber1() < 0 || sumRequest.getNumber1() > 1000)
        || (sumRequest.getNumber1() < 0 || sumRequest.getNumber1() > 1000)) {
            throw new Exception("Numbers are invalid");
        }

        if (sumRequest.getNumber1() == null) {
            if (sumRequest.getNumber2() != null) {
                return new SumResponse(sumRequest.getNumber2());
            }
            else {
                return new SumResponse(0);
            }
        }
        if (sumRequest.getNumber2() == null) {
            if (sumRequest.getNumber1() != null) {
                return new SumResponse(sumRequest.getNumber1());
            }
            else {
                return new SumResponse(0);
            }
        }

        return new SumResponse(sumRequest.getNumber1() + sumRequest.getNumber2());
    }


}
