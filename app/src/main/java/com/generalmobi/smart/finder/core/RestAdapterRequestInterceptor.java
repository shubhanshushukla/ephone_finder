package com.generalmobi.smart.finder.core;


import retrofit.RequestInterceptor;

import static  com.generalmobi.smart.finder.core.Constants.Http.HEADER_PARSE_APP_ID;
import static com.generalmobi.smart.finder.core.Constants.Http.PARSE_APP_ID;

public class RestAdapterRequestInterceptor implements RequestInterceptor {

    private UserAgentProvider userAgentProvider;

    public RestAdapterRequestInterceptor(UserAgentProvider userAgentProvider) {
        this.userAgentProvider = userAgentProvider;
    }

    @Override
    public void intercept(RequestFacade request) {

        // Add header to set content type of JSON
        request.addHeader("Content-Type", "application/json");

        // Add auth info for PARSE, normally this is where you'd add your auth info for this request (if needed).
         request.addHeader(HEADER_PARSE_APP_ID, PARSE_APP_ID);

        // Add the user agent to the request.
        request.addHeader("User-Agent", userAgentProvider.get());
    }
}
