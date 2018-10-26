package org.jab.microservices;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumRequest2Test {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void givenNullNumbers_result_ShouldBeInvalidWith2NullViolation() {
        SumRequest2 request = new SumRequest2();

        Set<ConstraintViolation<SumRequest2>> constraintViolations =
                validator.validate( request );

        assertEquals( 2, constraintViolations.size() );
        assertEquals( "must not be null", constraintViolations.iterator().next().getMessage() );
        assertEquals( "must not be null", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void givenBigAndValidNumbers_result_ShouldBeInvalidWith1BigViolation() {
        SumRequest2 request = new SumRequest2("3333", "10");

        Set<ConstraintViolation<SumRequest2>> constraintViolations =
                validator.validate( request );

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "must match \"^[0-9]{0,3}$\"", constraintViolations.iterator().next().getMessage() );
    }

}
