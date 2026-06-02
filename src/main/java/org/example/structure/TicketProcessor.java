package org.example.structure;

import java.util.List;

public class TicketProcessor {
    List<WorkflowStep> workFlow;
    AuditLogger auditLogger ;
    public TicketProcessor(List<WorkflowStep> workFlow , AuditLogger auditLogger)
    {
        this.auditLogger=auditLogger;
        this.workFlow = workFlow;
    }

    public void process(AccessRequest request)
    {
        for(WorkflowStep workflowStep : workFlow)
        {
            if(request.getStatus() == TicketStatus.REJECTED)
            {
                break;
            }
            else
            {
                workflowStep.execute(request,"SYSTEM");
            }
        }
    }
}
