package br.com.studies.pocwebsocket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaMessageModel {
    private String hostname;
    private String filial;
    private String filialTopic;
    private Boolean itensAssinados;
    private int idAssinatura;
}
