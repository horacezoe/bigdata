// PaperServiceTest.java
package com.example.bda_homework;

import com.example.bda_homework.model.Paper;
import com.example.bda_homework.repository.PaperRepository;
import com.example.bda_homework.service.PaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PaperServiceTest {

    @Mock
    private PaperRepository paperRepository;

    @InjectMocks
    private PaperService paperService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReferencedPapers() {
        Long paperId = 1L;
        String referencedPaperIds = "2,3";
        Paper paper = new Paper();
        paper.setPaperIdList(referencedPaperIds);

        Paper referencedPaper1 = new Paper();
        referencedPaper1.setId(2L);
        Paper referencedPaper2 = new Paper();
        referencedPaper2.setId(3L);

        when(paperRepository.findById(paperId)).thenReturn(Optional.of(paper));
        when(paperRepository.findAllById(Arrays.asList(2L, 3L))).thenReturn(Arrays.asList(referencedPaper1, referencedPaper2));

        List<Paper> result = paperService.getReferencedPapers(paperId);

        assertEquals(2, result.size());
        assertEquals(2L, result.get(0).getId());
        assertEquals(3L, result.get(1).getId());
    }

    @Test
public void testGetPapersByCategory() {
    String category = "Machine Learning";
    Paper paper1 = new Paper();
    paper1.setId(1L);
    paper1.setCategory(category);
    Paper paper2 = new Paper();
    paper2.setId(2L);
    paper2.setCategory(category);
    Paper paper3 = new Paper();
    paper3.setId(3L);
    paper3.setCategory(category);

    when(paperRepository.findByCategory(category)).thenReturn(Arrays.asList(paper1, paper2,paper3));

    List<Paper> result = paperService.getPapersByCategory(category);

    assertEquals(3, result.size());
    assertEquals(1L, result.get(0).getId());
    assertEquals(2L, result.get(1).getId());
}
}