package br.com.studies.pocwebsocket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaRequest {
    private String filial;
    private int idAssinatura;
    private String hostname;

}
