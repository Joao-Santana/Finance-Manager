package pt.upskill.projeto2;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;

import java.util.ArrayList;
import java.util.List;

public class AuxiliarClassStatements
{
    private long id;
    private List<StatementLine> statementLineList = new ArrayList<>();

    public AuxiliarClassStatements(long id, List<StatementLine> statementLineList)
    {
     this.id = id;
     this.statementLineList = statementLineList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
