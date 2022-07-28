package pt.upskill.projeto2.financemanager;
import pt.upskill.projeto2.AuxiliarClassStatements;
import pt.upskill.projeto2.financemanager.accounts.*;
import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;
import pt.upskill.projeto2.utils.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonalFinanceManager {

    private List<Account> accountList = new ArrayList<>();
    private List<AuxiliarClassStatements> statementLineList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();

    public PersonalFinanceManager()
    {
    AccountListsFromFile();
    readStatementsList();
    }
    public void AccountListsFromFile()
    {
     File filepathname = new File("account_info");
     String[] pathnames = filepathname.list();
     for(String pathname : pathnames)
     {
      File account = new File("account_info/" + pathname);
      if(!pathname.equals("categories"))
      {
       accountList.add(Account.newAccount(account));
      }
      else {
          categoryList = Category.readCategories(account);
      }
     }
    }
    public void readStatementsList()
    {
        File filepathname = new File("statements");
        String[] pathnames = filepathname.list();
        for(String pathname : pathnames)
        {
            File account = new File("statements/" + pathname);
            statementLineList.add(convertStatementFromFile(account));
        }
    }
    public AuxiliarClassStatements convertStatementFromFile(File path) {
        List<StatementLine> statelist = new ArrayList<>();
        AuxiliarClassStatements aux = null;
        try {
            Scanner scan = new Scanner(path);
            String line;
            long id = 0;
            String accName = "";
            for(int i = 0; scan.hasNextLine(); i++)
            {
                line = scan.nextLine();
                if(line.equals("") || i == 0 || i == 6)
                {
                    continue;
                }
             String[] tokens = line.split(";");
                if(i == 2)
                {
                    id = Long.parseLong(tokens[1].trim());
                    accName = tokens[4].trim();
                }
                if(i >= 7)
                {
                    StatementLine statementLine = newStatementLine(line);
                    statelist.add(statementLine);
                }
            }
            scan.close();
            aux = new AuxiliarClassStatements(id, statelist);
            if(accName.equals("DraftAccount") && !checkForAccountId(id))
            {
                Account acc = new DraftAccount(id,accName);
                accountList.add(acc);
                acc.addStatementLines(statelist);
            }
            else if(accName.equals("SavingsAccount") && !checkForAccountId(id)){
                Account acc = new SavingsAccount(id,accName);
                accountList.add(acc);
                acc.addStatementLines(statelist);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    return aux;
    }
    public StatementLine newStatementLine(String s)
    {
     String[] tokens = s.split(";");
     for(int i = 0; i < tokens.length; i++)
     {
             if(tokens[i].equals(""))
             {
                tokens[i] = Menu.requestInput("Movimento incorrecto \n" + s);
             }
     }
     Date date = new Date(Integer.parseInt(tokens[0].split("-")[0]), Integer.parseInt(tokens[0].split("-")[1]), Integer.parseInt(tokens[0].split("-")[2].trim()));
     Date valueDate = new Date(Integer.parseInt(tokens[1].split("-")[0]), Integer.parseInt(tokens[1].split("-")[1]), Integer.parseInt(tokens[1].split("-")[2].trim()));
     String descrip = tokens[2];
     double draft = Double.parseDouble(tokens[3]);
     double credit = Double.parseDouble(tokens[4]);
     double accountingBalance = Double.parseDouble(tokens[5]);
     double availableBalance = Double.parseDouble(tokens[6]);
     return new StatementLine(date,valueDate,descrip,draft,credit,accountingBalance,availableBalance,null);
    }
    public void printPosicaoGlobal()
    {
        double total = 0;
        String nl = System.getProperty("line.separator");
        String formatted = "Posicao Global" + nl + "Numero de conta\tSaldo" + nl;
        for(Account account : accountList)
        {
         formatted += account.getId() + "\t" + account.getLastAvailableBalance() + nl;
         total += account.getLastAvailableBalance();
        }
        formatted += nl + "Saldo total: " +"\t"+ total + nl;
        System.out.println(formatted);
    }
    public String[] showAccountsIds()
    {
     String[] result = new String[accountList.size()];
    for(int i = 0; i < accountList.size(); i++)
    {
        result[i] =String.valueOf(accountList.get(i).getId());
    }
    return result;
    }
    public boolean checkForAccountId(long id)
    {
        for(Account account : accountList)
        {
            if(account.getId() == id)
            {
                return true;
            }
        }
        return false;
    }
    public void showAccountStatements(String s)
    {
        FileAccountFormat fileAccountFormat = new FileAccountFormat();
        String format = "";
        for(Account account : accountList)
        {
            if(s.equals(String.valueOf(account.getId())))
            {
              format = fileAccountFormat.format(account);
            }
        }
        System.out.println(format);
    }

    public void showAnnualInterest(String s)
    {
        double result = 0;
        for(Account account : accountList)
        {
            if(s.equals(String.valueOf(account.getId())))
            {
                result = account.estimatedAverageBalance() * account.getInterestRate();
            }
        }
        System.out.println("Previsão dos juros anuais da conta sao: " + result);
    }

    public void showAllCategories()
    {
        System.out.println("Todas as categorias");
        System.out.println(categoryList.toString());
    }

    /*public void predictionPerCategory(String s)
    {
        String[] category = new String[categoryList.size()];
        for(int i = 0; i < category.length; i++)
        {
            category[i] = categoryList.get(i).getName();
        }
        String input =
        for(Account account : accountList)
        {
            if(s.equals(String.valueOf(account.getId())))
            {
                result = account.estimatedAverageBalance() * account.getInterestRate();
            }
        }
    }
     */
}
