package org.example.structure;

public class ImplementationStep implements WorkflowStep{
    private final AuditLogger auditLogger;
    private final  SecureChangeApiClient apiClient;
    public ImplementationStep(AuditLogger auditLogger, SecureChangeApiClient apiClient)
    {
        this.auditLogger = auditLogger;
        this.apiClient = apiClient;

    }

    @Override
    public void execute(AccessRequest request, String handlerName)
    {
        if(request.getStatus() == TicketStatus.APPROVED)
        {
            apiClient.submitTicket(request);
            request.setStatus(TicketStatus.IMPLEMENTED);
            auditLogger.record(request.getTicketId(),"Implementation",handlerName ,request.getStatus());
        }
    }
}
