package com.example.bda_homework.controller;

import com.example.bda_homework.model.Paper;
import com.example.bda_homework.service.PaperService;
import com.example.bda_homework.service.PythonScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private PythonScriptService pythonScriptService;

    @GetMapping("/search")
    public ResponseEntity<List<Paper>> searchPapers(@RequestParam String keyword) {
        List<Paper> papers = paperService.searchPapersByKeyword(keyword);
        return ResponseEntity.ok(papers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paper> getPaperById(@PathVariable Long id) {
        Paper paper = paperService.getPaperById(id);
        return ResponseEntity.ok(paper);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Paper>> getPapersByCategory(@RequestParam String category) {
        List<Paper> papers = paperService.getPapersByCategory(category);
        return ResponseEntity.ok(papers);
    }

    @GetMapping("/similar")
    public ResponseEntity<List<Paper>> getSimilarPapers(@RequestParam Long paperId) {
        List<Paper> similarPapers = pythonScriptService.getSimilarPapers(paperId);
        return ResponseEntity.ok(similarPapers);
    }

    @GetMapping("/reference")
    public ResponseEntity<List<Paper>> getReferencedPapers(@RequestParam Long paperId) {
        List<Paper> referencedPapers = paperService.getReferencedPapers(paperId);
        return ResponseEntity.ok(referencedPapers);
    }
}