package strategy.pricing;

import java.time.LocalDate;

/**
 * 가격 정책을 결정하기 위한 상황 정보를 담는 클래스
 */
public class PricingContext {
    private LocalDate startDate;      // 대여 시작일
    private LocalDate endDate;        // 대여 종료일
    private int days;                 // 대여 기간 (일수)
    private boolean isPeakSeason;     // 성수기 여부
    private String equipmentGrade;    // 장비 등급 ("HIGH", "NORMAL", "LOW")
    
    public PricingContext(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        if (startDate != null && endDate != null) {
            this.days = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        }
    }
    
    public PricingContext(int days) {
        this.days = days;
    }
    
    // Getters and Setters
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        if (startDate != null && endDate != null) {
            this.days = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        }
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        if (startDate != null && endDate != null) {
            this.days = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        }
    }
    
    public int getDays() {
        return days;
    }
    
    public void setDays(int days) {
        this.days = days;
    }
    
    public boolean isPeakSeason() {
        return isPeakSeason;
    }
    
    public void setPeakSeason(boolean peakSeason) {
        isPeakSeason = peakSeason;
    }
    
    public String getEquipmentGrade() {
        return equipmentGrade;
    }
    
    public void setEquipmentGrade(String equipmentGrade) {
        this.equipmentGrade = equipmentGrade;
    }
}

