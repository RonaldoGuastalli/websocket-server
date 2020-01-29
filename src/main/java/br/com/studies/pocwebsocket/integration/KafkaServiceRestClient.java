package br.com.studies.pocwebsocket.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class KafkaServiceRestClient {

    public void sendToKafkaService(String msg) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(msg, headers);

            String format = "http://localhost:8715/kafka";
            String url = String.format(format);

            new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);

        } catch (Exception ex) {
            System.out.println("erro: " + ex);
        }

    }

}
