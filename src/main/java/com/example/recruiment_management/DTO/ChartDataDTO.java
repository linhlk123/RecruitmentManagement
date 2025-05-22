package com.example.recruiment_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChartDataDTO {
    private int month;
    private long recruiterCount;
    private long candidateCount;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getRecruiterCount() {
        return recruiterCount;
    }

    public void setRecruiterCount(long recruiterCount) {
        this.recruiterCount = recruiterCount;
    }

    public long getCandidateCount() {
        return candidateCount;
    }

    public void setCandidateCount(long candidateCount) {
        this.candidateCount = candidateCount;
    }
}