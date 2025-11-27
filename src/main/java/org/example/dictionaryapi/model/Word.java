package org.example.dictionaryapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;


@Document (collation = "words")
public record Word(
        @Id
        String id,
        String word,
        String meaning,
        String example,
        String synonyms// comma-separated
) {
}
