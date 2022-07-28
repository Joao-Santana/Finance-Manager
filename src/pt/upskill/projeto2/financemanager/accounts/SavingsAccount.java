package pt.upskill.projeto2.financemanager.accounts;
import pt.upskill.projeto2.financemanager.categories.Category;

import java.util.Collections;

public class SavingsAccount extends Account {
    public static Category savingsCategory;
    public SavingsAccount(long id, String sav)
    {
        super(id, sav);
    }

    @Override
    public double estimatedAverageBalance() {
        double result = 0;
        for(StatementLine statementLine : getStateList())
        {
            result = statementLine.getAvailableBalance();
        }
        return result;
    }

    @Override
    public double getInterestRate() {
       return BanksConstants.savingsInterestRate();
    }
    @Override
    public void addStatementLine(StatementLine statementLine) {
        statementLine.setCategory(savingsCategory);
        super.getStateList().add(statementLine);
    }
}
