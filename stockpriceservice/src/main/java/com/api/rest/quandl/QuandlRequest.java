package com.api.rest.quandl;

import javax.ws.rs.client.WebTarget;

public interface QuandlRequest {

	WebTarget appendPathAndQueryParameter(WebTarget webTarget);
}
