package org.example.dictionaryapi.controller;

import org.example.dictionaryapi.exception.NotFoundException;
import org.example.dictionaryapi.model.Word;
import org.example.dictionaryapi.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/word")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public List<Word> findAll() {
        return wordService.findAll();
    }

    @GetMapping("/{word}")
    public Word findByWord(@PathVariable String word) throws NotFoundException {
        return wordService.findByWord(word);
    }

    @PostMapping
    public Word save(@RequestBody Word word) {
        return wordService.save(word);
    }

    @PutMapping("/{word}")
    public Word update(@PathVariable String word, @RequestBody Word payload) throws NotFoundException {
        return wordService.update(word, payload);
    }

    @DeleteMapping("/{word}")
    public void deleteByWord(@PathVariable String word) throws NotFoundException {
       wordService.deleteByWord(word);
    }
}
