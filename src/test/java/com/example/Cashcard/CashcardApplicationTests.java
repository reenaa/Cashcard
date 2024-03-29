package com.example.Cashcard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.Document;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashcardApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnACashCardWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext =JsonPath.parse(response.getBody());
        Number id =  documentContext.read("$.id");
        assertThat(id).isEqualTo(99);
        Double value = documentContext.read("$.amount");
        assertThat(value).isEqualTo(123.45);
//        assertThat(value).isNotNull();
//        assertThat(id).isNotNull();
    }
    @Test
    void shouldNotReturnACashCardWithAnUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/1000",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }
}
