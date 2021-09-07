package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class JSON<T> {

    private final Logger LOGGER = LogManager.getLogger(JSON.class.getName());

    public String toJSON(List<T> t) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            LOGGER.error(e);
        }
        return json;
    }

}
