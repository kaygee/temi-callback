package callback.deserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MultiDateDeserializer extends StdDeserializer<Date> {
  private static final long serialVersionUID = 1L;

  private static final String[] DATE_FORMATS =
      new String[] {"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'"};

  public MultiDateDeserializer() {
    this(null);
  }

  public MultiDateDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    final String date = node.textValue();

    for (String DATE_FORMAT : DATE_FORMATS) {
      try {
        return new SimpleDateFormat(DATE_FORMAT).parse(date);
      } catch (ParseException e) {
      }
    }
    throw new JsonParseException(
        jsonParser,
        "Unparseable date: \"" + date + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
  }
}
