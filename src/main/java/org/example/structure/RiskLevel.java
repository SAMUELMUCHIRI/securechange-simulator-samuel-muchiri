package org.example.structure;

public enum RiskLevel {
    LOW, MEDIUM, HIGH ;

    public boolean requiresManagerApproval()
    {
        return this.equals(RiskLevel.HIGH);
    }
}
