package iteach.eaap.assignments.apigateway;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Assignment {
    private String deadline;
    private String id;
    private String status;
}
