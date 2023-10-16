package com.mysite.todolist.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyStatistics {
    LocalDate day;
    List<Long> completedTaskIds;
    List<Long> createdTaskIds;  
    
    
}
