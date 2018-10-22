package org.jab.microservices;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SumControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenSumAPIWithPostAndFormData_whenMockMVC_thenResponseOK() throws Exception  {

        Integer param1 = 1;
        Integer param2 = 3;

        final SumRequest request = new SumRequest(param1, param2);
        SumResponse response = this.restTemplate.postForObject("/sum", request, SumResponse.class);

        Integer result = (param1 + param2);
        assertThat(response.getResult(), is(result));
    }


    @Test
    public void givenSumAPIWithPostAndFormData_whenOneParamIsEmpty_thenResponseOKAndShouldReturnTheFirstParamAsResult() throws Exception  {

        Integer param1 = 1;
        // Integer param2;

        final SumRequest request = new SumRequest(param1, 0);
        SumResponse response = this.restTemplate.postForObject("/sum", request, SumResponse.class);


        Integer result = (param1);
        assertThat(response.getResult(), is(result));
    }

    @Test
    public void givenSumAPIWithPostAndFormData_whenOneParamIsNull_thenResponseOKAndShouldReturnTheFirstParamAsResult() throws Exception  {

        Integer param1 = 1;
        // Integer param2;

        final SumRequest request = new SumRequest(param1, null);
        SumResponse response = this.restTemplate.postForObject("/sum", request, SumResponse.class);

        Integer result = (param1);
        assertThat(response.getResult(), is(result));
    }

    @Test
    public void givenSumAPIWithPostAndFormData_whenBothParamsAreNull_thenResponseOKAndShouldReturnZeroResult() throws Exception  {

        final SumRequest request = new SumRequest(null, null);
        SumResponse response = this.restTemplate.postForObject("/sum", request, SumResponse.class);

        assertThat(response.getResult(), is(0));
    }


    @Test
    public void givenSumAPIWithPostAndFormData_whenIsEmpty_thenResponseOKAndShouldReturnZeroAsResult() throws Exception  {

        final SumRequest request = new SumRequest(0, 0);
        SumResponse response = this.restTemplate.postForObject("/sum", request, SumResponse.class);

        Integer result = (0);
        assertThat(response.getResult(), is(result));
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void givenNegativeNumbers_whenMockMVC_thenShouldThrowException()  {

        Integer param1 = -1;
        Integer param2 = 3;


        final SumRequest request = new SumRequest(param1, param2);

        thrown.expect(Exception.class);
        thrown.expectMessage("Numbers are invalid");
            SumResponse response = this.restTemplate.postForObject("/sum", request, SumResponse.class);
            response.getResult();

//            Assert.fail();

//        }
  //      catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            assertThat(ex.getMessage(), is("Numbers are invalid"));
//        }

    }



}
