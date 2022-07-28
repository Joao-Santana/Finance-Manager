package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.date.Date;

public class DraftAccount extends Account {
    public DraftAccount(long id, String name) {
        super(id, name);
    }

    @Override
    public double estimatedAverageBalance() {
        int diferencaEntreDias = 0;
        double result = 0;
        if(getStateList().size() == 0)
        {
            return 0;
        }
        for (int i = 0; i < getStateList().size(); i++)
        {
            int diaEntreStatements = 0;
            if(i == getStateList().size()-1)
                {
                    diaEntreStatements += getStateList().get(i).getDate().diffInDays(getStateList().get(i-1).getDate());
                }
                else {
                    diaEntreStatements += getStateList().get(i + 1).getDate().diffInDays(getStateList().get(i).getDate());
                }
            result += getStateList().get(i).getAvailableBalance() * diaEntreStatements;
                diferencaEntreDias += diaEntreStatements;
        }
        return result / diferencaEntreDias;
    }
    @Override
    public double getInterestRate() {
        return BanksConstants.normalInterestRate();
    }
}
