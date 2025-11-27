package org.example.dictionaryapi.service;

import org.example.dictionaryapi.exception.NotFoundException;
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

    public Word findByWord(String word) throws NotFoundException {
        return  wordRepository.findByWords(word)
                .orElseThrow(() -> new NotFoundException("Word"));
    }

    public Word update(String word, Word newWord) throws NotFoundException {
        Word oldWord = wordRepository.findByWords(word).orElseThrow(() -> new NotFoundException("Word"));
        Word update = null;
        if(oldWord != null){
           update= new Word(newWord.getWords(), newWord.getMeaning(), oldWord.getExample(), oldWord.getSynonyms());
        }
        assert update != null;
        return wordRepository.save(update);
    }

    public void deleteByWord(String word) throws NotFoundException {
        Optional<Word> w = Optional.ofNullable(wordRepository.findByWords(word).orElseThrow(() -> new NotFoundException("Word")));
        wordRepository.delete(w.orElseThrow(() -> new NotFoundException("Word")));
    }
}
