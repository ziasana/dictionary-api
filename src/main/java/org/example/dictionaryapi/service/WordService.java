package org.example.dictionaryapi.service;

import org.example.dictionaryapi.exception.NotFoundException;
import org.example.dictionaryapi.model.Word;
import org.example.dictionaryapi.repository.WordRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Cacheable(value = "words", key = "#word")
    public Word findByWord(String word) throws NotFoundException {
        return  wordRepository.findByWords(word)
                .orElseThrow(() -> new NotFoundException("Word"));
    }

    @CachePut(value = "words", key= "#word")
    public Word update(String word, Word newWord) throws NotFoundException {
        Word oldWord = wordRepository.findByWords(word)
                .orElseThrow(() -> new NotFoundException("Word"));

        oldWord.setWords(newWord.getWords());
        oldWord.setMeaning(newWord.getMeaning());
        oldWord.setExample(newWord.getExample());
        oldWord.setSynonyms(newWord.getSynonyms());

        return wordRepository.save(oldWord);
    }

    @CacheEvict(value = "words", key= "#word")
    public void deleteByWord(String word) throws NotFoundException {
        if (!wordRepository.existsByWords(word)) {
            throw new NotFoundException("Word not found");
        }
        wordRepository.deleteByWords(word);
    }
}
