package com.petclinic.client;

import com.petclinic.filters.AuthenticationFilters;
import com.petclinic.filters.LogFilter;
import com.petclinic.util.EnvReader;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {

    protected RequestSpecification getBasicRestConfig() {
        return given().filters(new AuthenticationFilters(), new LogFilter())
                .baseUri(EnvReader.getBaseUri())
                .port(EnvReader.getPort())
                .basePath(EnvReader.getPath());
    }
}
