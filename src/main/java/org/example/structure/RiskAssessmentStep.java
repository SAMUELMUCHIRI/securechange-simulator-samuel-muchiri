package org.example.structure;

import java.util.ArrayList;
import java.util.List;

public class RiskAssessmentStep implements WorkflowStep{
    private final AuditLogger auditLogger;

    public RiskAssessmentStep(AuditLogger auditLogger){
        this.auditLogger = auditLogger;
    }
    @Override
    public void execute(AccessRequest request, String handlerName)
    {

        if(request.getPort() < 1024)
        {
            request.setRiskLevel(RiskLevel.HIGH);
        } else if (request.getPort() >= 1024 && request.getPort()<= 8080) {
            request.setRiskLevel(RiskLevel.MEDIUM);
        }
        else
        {
            request.setRiskLevel(RiskLevel.LOW);
        }
        request.setStatus(TicketStatus.UNDER_REVIEW);
        auditLogger.record(request.getTicketId(), "RiskAssessment",handlerName,request.getStatus());

    }
}
