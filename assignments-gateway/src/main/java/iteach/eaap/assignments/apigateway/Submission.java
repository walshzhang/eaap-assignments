package iteach.eaap.assignments.apigateway;

import lombok.Data;

@Data
public class Submission {
    private String id;
    private String code;
    private String assignmentId;

    private Assignment assignment;
}
