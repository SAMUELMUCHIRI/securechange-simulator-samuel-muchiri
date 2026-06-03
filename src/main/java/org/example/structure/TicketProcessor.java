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
            /* I agree with this suggestion . This checks for enum reference equality, which works for enums in
             Java—but it's using the wrong pattern. The safer practice is using
              .equals(), though enums are technically safe with ==.
             */
            try {
                /* Added  a try catch block to safely handle any execution error in workflow */
            if(request.getStatus().equals(TicketStatus.REJECTED))
            {
                break;
            }
            else
            {
                workflowStep.execute(request,"SYSTEM");
            }
            } catch (Exception e) {
                auditLogger.record(request.getTicketId(),"WorkflowExecutionStep","TicketProcessorError",request.getStatus());
            }
        }
    }
}
