package pt.upskill.projeto2.financemanager.gui;

import pt.upskill.projeto2.financemanager.PersonalFinanceManager;
import pt.upskill.projeto2.utils.Menu;

/**
 * @author upSkill 2020
 * <p>
 * ...
 */

public class PersonalFinanceManagerUserInterface {

    public PersonalFinanceManagerUserInterface(
            PersonalFinanceManager personalFinanceManager) {
        this.personalFinanceManager = personalFinanceManager;
    }

    private static final String OPT_GLOBAL_POSITION = "Posição Global";
    private static final String OPT_ACCOUNT_STATEMENT = "Movimentos Conta";
    private static final String OPT_LIST_CATEGORIES = "Listar categorias";
    private static final String OPT_ANALISE = "Análise";
    private static final String OPT_EXIT = "Sair";

    private static final String OPT_MONTHLY_SUMMARY = "Evolução global por mês";
    private static final String OPT_PREDICTION_PER_CATEGORY = "Previsão gastos totais do mês por categoria";
    private static final String OPT_ANUAL_INTEREST = "Previsão juros anuais";

    private static final String[] OPTIONS_ANALYSIS = {OPT_MONTHLY_SUMMARY, OPT_PREDICTION_PER_CATEGORY, OPT_ANUAL_INTEREST};
    private static final String[] OPTIONS = {OPT_GLOBAL_POSITION,
            OPT_ACCOUNT_STATEMENT, OPT_LIST_CATEGORIES, OPT_ANALISE, OPT_EXIT};

    private PersonalFinanceManager personalFinanceManager;

    public void execute() {
        try{
            Boolean on = true;
            while(on)
            {
                String requestSelection = Menu.requestSelection("Projecto Personal Finance Manager", OPTIONS);
                switch (requestSelection) {
                    case OPT_GLOBAL_POSITION:
                        personalFinanceManager.printPosicaoGlobal();
                        break;
                    case OPT_ACCOUNT_STATEMENT:
                        String opt = Menu.requestSelection("Escolhe o ID de uma das suas contas", personalFinanceManager.showAccountsIds());
                        personalFinanceManager.showAccountStatements(opt);
                        break;
                    case OPT_LIST_CATEGORIES:
                        personalFinanceManager.showAllCategories();
                        break;
                    case OPT_ANALISE:
                        String requestSelection2 = Menu.requestSelection("Escolhe a opção", OPTIONS_ANALYSIS);
                        String opt2 = Menu.requestSelection("Escolhe o ID de uma das suas contas", personalFinanceManager.showAccountsIds());
                        switch(requestSelection2)
                        {
                            case OPT_MONTHLY_SUMMARY:
                                break;
                            case OPT_PREDICTION_PER_CATEGORY:
                                break;
                            case OPT_ANUAL_INTEREST:
                                personalFinanceManager.showAnnualInterest(opt2);
                                break;
                        }
                        break;
                    case OPT_EXIT:
                        System.out.println("Sair");
                        System.exit(0);
                        on = false;
                        break;
                }
            }
        } catch (NullPointerException e)
        {
            System.exit(0);
        }
    }


}
