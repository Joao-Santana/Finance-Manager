package pt.upskill.projeto2.financemanager.accounts.formats.unittests;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;

public class LongStatementFormat {
    public String format(StatementLine s1) {
        return s1.getDate().toString() + " \t" + s1.getValueDate().toString()  + " \t" + s1.getDescription()  + " \t" + s1.getDraft()  + " \t" + s1.getCredit()  + " \t" + s1.getAccountingBalance()  + " \t" + s1.getAvailableBalance();
    }
    public String fields() {
        return "Date \tValue Date \tDescription \tDraft \tCredit \tAccounting balance \tAvailable balance ";
    }
}
