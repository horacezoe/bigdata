package com.example.bda_homework.service;

import com.example.bda_homework.model.Paper;
import com.example.bda_homework.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class PythonScriptService {

    @Value("${python.backend.url}")
    private String pythonBackendUrl;

    @Autowired
    private PaperRepository paperRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Paper> getSimilarPapers(Long paperId) {
        String url = pythonBackendUrl + "/similar_papers?paper_id=" + paperId + "&num_similar=5";
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("data")) {
                List<Long> similarPaperIds = (List<Long>) ((Map<String, Object>) responseBody.get("data")).get("similar_paper_ids");
                return fetchPapersByIds(similarPaperIds);
            }
        }
        return null;
    }

    private List<Paper> fetchPapersByIds(List<Long> paperIds) {
        return paperRepository.findByIdIn(paperIds);
    }
}