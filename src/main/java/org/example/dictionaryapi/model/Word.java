package org.example.dictionaryapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Word{
        @Id
        private String id;
        private String words;
        private String meaning;
        private String example;
        private String synonyms;

        public Word(String word, String meaning, String example, String synonyms) {
            this.words = word;
            this.meaning = meaning;
            this.example = example;
            this.synonyms = synonyms;
        }
}
