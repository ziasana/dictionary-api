package org.example.dictionaryapi.service;

import org.example.dictionaryapi.model.Word;
import org.example.dictionaryapi.repository.WordRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    public Word save(Word word) {
        return wordRepository.save(word);
    }

    public Word findByWord(String word) {
        return  wordRepository.findByWord(word)
                .orElseThrow(() -> new RuntimeException("Word not found: " + word));
    }

    public Word update(Word word) {
        return wordRepository.save(word);
    }

    public void deleteByWord(String word) {
        Word w = findByWord(word);
        wordRepository.delete(w);
    }
}
