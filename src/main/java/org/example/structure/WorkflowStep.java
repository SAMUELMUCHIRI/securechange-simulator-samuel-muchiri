package org.example.structure;

@FunctionalInterface
public interface WorkflowStep {
    void execute(AccessRequest request, String handlerName);
}
