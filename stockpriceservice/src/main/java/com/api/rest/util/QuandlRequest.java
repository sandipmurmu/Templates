package com.api.rest.util;

import javax.ws.rs.client.WebTarget;

public interface QuandlRequest {

	WebTarget appendPathAndQueryParameter(WebTarget webTarget);
}
