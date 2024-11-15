package com.example.bda_homework.service;

import com.example.bda_homework.model.Paper;
import com.example.bda_homework.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    public static List<Paper> getReferencedPapers(Long paperId) {
        return null;
    }

    public List<Paper> searchPapersByKeyword(String keyword) {
        return paperRepository.findByTitleContaining(keyword);
    }

    public List<Paper> getPapersByCategory(String category) {
        return paperRepository.findByCategory(category);
    }

    public List<Paper> getPapersByYear(int year) {
        return paperRepository.findByYear(year);
    }

    public Paper getPaperById(Long id) {
        return paperRepository.findById(id).orElse(null);
    }
}
