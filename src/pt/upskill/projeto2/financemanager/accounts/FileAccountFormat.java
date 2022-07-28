package pt.upskill.projeto2.financemanager.accounts;
import pt.upskill.projeto2.financemanager.date.Date;
import java.util.Arrays;
public class FileAccountFormat {
    public String format(Account a1) {
        String nl = System.getProperty("line.separator");
            String formatted = "Account Info - " + new Date() + nl
                    + "Account " + " ;" + a1.getId() + " ; " + "EUR "  + " ;" + a1.getName() + " ;" + nl
                    + "Start Date" + " ;" + a1.getStartDate() + nl + "End Date"
                    + " ;" + a1.getEndDate() + nl + a1.additionalInfo() + "Date ;Value Date ;Description ;Draft ;Credit ;Accounting balance ;Available balance" + nl;
            for(StatementLine stateList : a1.getStateList())
            {
                formatted += stateList.getDate().toString() + " ;" + stateList.getValueDate()
                        + " ;" + stateList.getDescription() + ";" + stateList.getDraft()  + " ;" + stateList.getCredit()  + " ;" + stateList.getAccountingBalance()  + " ;" + stateList.getAvailableBalance()  + nl;
            }

            return formatted;
    }
}
