package com.codegym.service;

import com.codegym.dao.model.ViewCountStatistic;

import java.util.Date;
import java.util.List;

public interface ViewCountStatisticService {
    void save(ViewCountStatistic viewCountStatistic);
    List<ViewCountStatistic> findAll();
    ViewCountStatistic findById(Long id);
    void delete(Long id);
    ViewCountStatistic findLastViewCountStatistic(Long postId);
    List<ViewCountStatistic> getListViewCountStatistic(Date startDay, Date endDay);
}
