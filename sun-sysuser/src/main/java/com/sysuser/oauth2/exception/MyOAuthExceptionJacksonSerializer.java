package com.sysuser.oauth2.exception;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class MyOAuthExceptionJacksonSerializer extends StdSerializer<MyOAuth2Exception>{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7989025959165586911L;

	protected MyOAuthExceptionJacksonSerializer() {
 	   super(MyOAuth2Exception.class);
 	}

	@Override
	public void serialize(MyOAuth2Exception value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		// TODO Auto-generated method stub
		jgen.writeStartObject();
        jgen.writeObjectField("status", value.getHttpErrorCode());
        String errorMessage = value.getOAuth2ErrorCode();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        jgen.writeStringField("msg", errorMessage);
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
	}



}
