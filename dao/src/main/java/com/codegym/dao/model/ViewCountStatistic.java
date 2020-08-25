package com.codegym.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "view_count_statistic")
public class ViewCountStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String dateStatistic;

    @Column(nullable = false)
    private Long viewCount = 1L;

    public ViewCountStatistic() {
    }

    public ViewCountStatistic(String dateStatistic, Long viewCount) {
        this.dateStatistic = dateStatistic;
        this.viewCount = viewCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateStatistic() {
        return dateStatistic;
    }

    public void setDateStatistic(String dateStatistic) {
        this.dateStatistic = dateStatistic;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
}
