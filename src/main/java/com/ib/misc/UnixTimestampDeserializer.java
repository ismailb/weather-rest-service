package com.ib.misc;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnixTimestampDeserializer extends JsonDeserializer<Date> {
	Logger logger = LoggerFactory.getLogger(UnixTimestampDeserializer.class);

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String timestamp = jp.getText().trim();

		try {
			return new DateTime(Long.valueOf(timestamp + "000")).toDate();
		} catch (NumberFormatException e) {
			logger.warn("Unable to deserialize timestamp: " + timestamp, e);
			return null;
		}
	}
}