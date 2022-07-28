package pt.upskill.projeto2.financemanager.accounts;
import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.date.Date;

public class BeforeDateSelector {

    private Date date;
    public BeforeDateSelector(Date date)
    {
    this.date = date;
    }
    public boolean isSelected(StatementLine stt1)
    {
        if(stt1.getDate().before(date))
        {
            return true;
        }
        return false;
    }
}
