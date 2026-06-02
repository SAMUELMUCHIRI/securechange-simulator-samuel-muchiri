package org.example.structure;

public class ManagerApprovalStep implements WorkflowStep {
    private final AuditLogger auditLogger;
    private final String reason;
    private boolean approved ;

    public ManagerApprovalStep(AuditLogger auditLogger, boolean approved, String reason) {
        this.auditLogger = auditLogger;
        this.reason = reason;
        this.approved = approved;
    }

    @Override
    public void execute(AccessRequest request, String handlerName) {
        if(approved)
        {
            request.setStatus(TicketStatus.APPROVED);
        }
        else{
            request.setStatus(TicketStatus.REJECTED);
            request.setRejectionReason(this.reason);
        }
        auditLogger.record(request.getTicketId(),"ManagerApproval",handlerName ,request.getStatus());

    }
}