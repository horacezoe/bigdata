package com.example.bda_homework.repository;

import com.example.bda_homework.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByCategory(String category); // 按类别查找论文

    List<Paper> findByYear(int year); // 按年份查找论文

    List<Paper> findByTitleContaining(String keyword); // 按标题关键词查找

    Optional<Paper> findById(Long id); // 按ID查找论文

    List<Paper> findByIdIn(List<Long> paperIds);
}
