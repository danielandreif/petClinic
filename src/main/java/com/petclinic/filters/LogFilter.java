package com.petclinic.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class LogFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(LogFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification reqSpec, FilterableResponseSpecification resSpec, FilterContext context) {

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);

        RequestPrinter.print(reqSpec, reqSpec.getMethod(),
                reqSpec.getURI(), LogDetail.ALL,
                reqSpec.getConfig().getLogConfig().blacklistedHeaders(),
                stream, true );

        Response response = context.next(reqSpec, resSpec);

        ResponsePrinter.print(response, response.body(), stream,
                LogDetail.ALL, true,
                reqSpec.getConfig().getLogConfig().blacklistedHeaders());

        LOGGER.info(outputStream);

        return response;
    }
}
