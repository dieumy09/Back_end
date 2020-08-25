package com.codegym.dao.DTO;

public class DateStatisticDTO {
    private String startDay;
    private String endDay;

    public DateStatisticDTO() {
    }

    public DateStatisticDTO(String startDay, String endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
