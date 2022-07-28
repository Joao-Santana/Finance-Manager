package pt.upskill.projeto2.financemanager.accounts.formats.unittests;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.accounts.formats.StatementLineFormat;

public class SimpleStatementFormat implements StatementLineFormat {
    @Override
    public String format(StatementLine objectToFormat) {
        return objectToFormat.getDate().toString() + " \t" + objectToFormat.getDescription() + " \t" +  objectToFormat.getDraft() + " \t" + objectToFormat.getCredit() + " \t" + objectToFormat.getAvailableBalance();
    }

    @Override
    public String fields() {
        return "Date \tDescription \tDraft \tCredit \tAvailable balance ";
    }
}
