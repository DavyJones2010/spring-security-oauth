/*
 * Cloud Foundry 2012.02.03 Beta
 * Copyright (c) [2009-2012] VMware, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product includes a number of subcomponents with
 * separate copyright notices and license terms. Your use of these
 * subcomponents is subject to the terms and conditions of the
 * subcomponent's license, as noted in the LICENSE file.
 */
package org.springframework.security.oauth2.common.util;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * JSON deserializer for Jackson to handle regular date instances as timestamps in ISO format.
 * 
 * @author Dave Syer
 *
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {
	 
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
 
	@Override
	public Date deserialize(com.fasterxml.jackson.core.JsonParser parser, DeserializationContext context) throws IOException,
		JsonProcessingException {
		try {
			synchronized (dateFormat) {				
				return dateFormat.parse(parser.getText());
			}
		}
		catch (ParseException e) {
			throw new JsonParseException("Could not parse date", parser.getCurrentLocation(), e);
		}
	}

}