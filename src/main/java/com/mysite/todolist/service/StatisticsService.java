package com.mysite.todolist.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.todolist.model.DailyStatistics;
import com.mysite.todolist.model.Task;

@Service
public class StatisticsService {
    
    @Autowired
    TaskService taskService;

    public DailyStatistics getStatisticsByDay(LocalDate date) {
        List<Long> createdTaskIds = taskService
            .findAllCreatedByDay(date)
            .stream()
            .map(Task::getId)
            .toList();
        List<Long> completedTaskIds = taskService
            .findAllCompletedByDay(date)
            .stream()
            .map(Task::getId)
            .toList();
        return DailyStatistics.builder()
            .day(date)
            .completedTaskIds(completedTaskIds)
            .createdTaskIds(createdTaskIds)
            .build();
    }

    public List<DailyStatistics> getStatisticsByPeriod(LocalDate from, LocalDate to) {
        return from.datesUntil(to.plusDays(1)).map(this::getStatisticsByDay).toList();
    }

    public DailyStatistics getDailyStatistics() {
        LocalDate today = LocalDate.now();
        return getStatisticsByDay(today);
    }    

    public List<DailyStatistics> getWeeklyStatistics() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDay = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return getStatisticsByPeriod(firstDay, lastDay);
    }

    public List<DailyStatistics> getMonthlyStatistics() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        return getStatisticsByPeriod(firstDay, lastDay);
    }


}
