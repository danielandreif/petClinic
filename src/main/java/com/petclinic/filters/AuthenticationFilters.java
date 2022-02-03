package com.petclinic.filters;

import com.petclinic.util.EnvReader;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthenticationFilters implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification reqSpec, FilterableResponseSpecification resSpec, FilterContext context) {
        if (EnvReader.getAdminUserName() != null && EnvReader.getAdminPassword() != null )
            reqSpec.auth().preemptive().basic(EnvReader.getAdminUserName(), EnvReader.getAdminPassword());
        return context.next(reqSpec, resSpec);
    }
}
