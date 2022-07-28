package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Account {
    private List<StatementLine> stateList = new ArrayList<>();
    private long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String additionalInfo;

    public Account(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Account() {

    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static Account newAccount(File file) {
        Account acc = null;
        try {
            Scanner scan = new Scanner(file);
            String line;
            for (int i = 0; scan.hasNextLine(); i++) {
                line = scan.nextLine();
                if (line.equals("")) {
                    continue;
                }
                if (i == 0 || i == 4) {
                    continue;
                }
                String[] tokens = line.split(";", -1);
                if (i == 1) {
                    if(tokens.length > 4)
                    {
                        long id = (Long.parseLong(tokens[1].trim()));
                        String name = tokens[3].trim();
                        if (tokens[4].equals("DraftAccount")) {
                            acc = new DraftAccount(id, name);
                        } else
                        {
                            acc = new SavingsAccount(id, name);
                        }
                        if(tokens[5] == null || tokens[5].equals(""))
                        {
                            acc.setAdditionalInfo(null);
                        }
                        else {
                            acc.setAdditionalInfo(tokens[5].trim());
                        }
                    }
                    else {
                        long id = (Long.parseLong(tokens[1].trim()));
                        String name = tokens[3].trim();
                        if (tokens[4].equals("DraftAccount")) {
                            acc = new DraftAccount(id, name);
                        } else
                        {
                            acc = new SavingsAccount(id, name);
                        }
                    }
                }
                if (i == 2) {
                    if (tokens[1].equals("")) {
                        acc.setStartDate(null);
                    } else {
                        Date d = new Date(Integer.parseInt(tokens[1].split("-")[0]), Integer.parseInt(tokens[1].split("-")[1]), Integer.parseInt(tokens[1].split("-")[2].trim()));
                        acc.setStartDate(d);
                    }
                }
                if (i == 3) {
                    if (tokens[1].equals("")) {
                        acc.setEndDate(null);
                    } else {
                        Date d = new Date(Integer.parseInt(tokens[1].split("-")[0]), Integer.parseInt(tokens[1].split("-")[1]), Integer.parseInt(tokens[1].split("-")[2]));
                        acc.setEndDate(d);
                    }
                }
                if (i > 4) {
                    Date d = null, d1 = null;
                    if (tokens[0].equals("")) {
                        d = null;
                    } else if (tokens[1].equals("")) {
                        d1 = null;
                    } else {
                        d = new Date(Integer.parseInt(tokens[0].split("-")[0]), Integer.parseInt(tokens[0].split("-")[1]), Integer.parseInt(tokens[0].split("-")[2].trim()));
                        d1 = new Date(Integer.parseInt(tokens[1].split("-")[0]), Integer.parseInt(tokens[1].split("-")[1]), Integer.parseInt(tokens[1].split("-")[2].trim()));
                    }
                    StatementLine statementLine = new StatementLine(d, d1, tokens[2].trim(), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]),
                            Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]),null);
                    acc.addStatementLine(statementLine);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return acc;
    }

    public List<StatementLine> getStateList() {
        return stateList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addStatementLine(StatementLine statementLine) {
        stateList.add(statementLine);
        Collections.sort(stateList);
    }
    public void addStatementLines(List<StatementLine> statementLineList)
    {
        stateList.addAll(statementLineList);
    }
    public String getName() {
        return name;
    }

    public String additionalInfo() {
        String addInfo = getAdditionalInfo();
        if(addInfo == null || addInfo.isEmpty())
        {
            return "";
        }
       return addInfo;
    }

    public double currentBalance() {
        double result = 0;
        for(StatementLine statementLine : stateList)
        {
         result = statementLine.getAvailableBalance();
        }
        return result;
    }

    public abstract double estimatedAverageBalance();

    public Date getStartDate() {
        for (StatementLine statementLine : stateList) {
            return statementLine.getDate();
        }
        return null;
    }

    public Date getEndDate() {
        for (int i = stateList.size() - 1; i >= 0; i--) {
            return stateList.get(i).getDate();
        }
        return null;
    }

    public double totalDraftsForCategorySince(Category savingsCategory, Date date) {
        double result = 0;
      for(StatementLine statementLine : stateList)
      {
          if(statementLine.getDate().after(date) && statementLine.getCategory()== savingsCategory){
           result += statementLine.getDraft();
          }
      }
      return result;
    }

    public abstract double getInterestRate();
    public double totalForMonth(int i, int i1)
    {
        double result = 0.0;
        for (StatementLine statementLine : stateList) {
           if(statementLine.getDate().getMonth().ordinal() == i && statementLine.getDate().getYear() == i1)
           {
            result += statementLine.getDraft();
           }
        }
        return result;
    }
    public void removeStatementLinesBefore(Date date) {
        List<StatementLine> removeStatementLines = new ArrayList<>();
        for (StatementLine statementLine : stateList)
        {
            if(statementLine.getDate().after(date))
            {
                removeStatementLines.add(statementLine);
            }
        }
        stateList.removeAll(removeStatementLines);
        stateList = removeStatementLines;
    }
    public void autoCategorizeStatements(List<Category> categories)
    {
     for(StatementLine statementLine : stateList)
     {
         for(Category category : categories)
         {
             if(category.hasTag(statementLine.getDescription()))
             {
                 statementLine.setCategory(category);
             }
         }
     }
    }
    public void setName(String other) {
        name = other;
    }
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    public String getAdditionalInfo() {
        return additionalInfo;
    }
    public double getLastAvailableBalance()
    {
        double result = 0;
        for(int i = 0; i < stateList.size(); i++)
        {
            if(i == stateList.size()-1)
            {
                result = stateList.get(i).getAvailableBalance();
            }
        }
        return result;
    }
}
