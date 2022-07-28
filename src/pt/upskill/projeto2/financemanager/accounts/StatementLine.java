package pt.upskill.projeto2.financemanager.accounts;
import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;


public class StatementLine implements Comparable<StatementLine>{
	private Date date, valueDate;
	private String description;
	private double draft, credit, accountingBalance, availableBalance;
	private  Category category;

	public StatementLine(Date date, Date valueDate, String description, double draft, double credit, double accountingBalance, double availableBalance, Category category) {
		if(date == null  || description == null || description.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		if(draft > 0 || credit < 0)
		{
			throw new IllegalArgumentException();
		}
		this.date = date;
		this.valueDate = valueDate;
		this.description = description;
		this.draft = draft;
		this.credit = credit;
		this.accountingBalance = accountingBalance;
		this.availableBalance = availableBalance;
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public String getDescription() {
		return description;
	}

	public double getCredit() {
		return credit;
	}

	public double getDraft() {
		return draft;
	}

	public double getAccountingBalance() {
		return accountingBalance;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category cat) {
		this.category = cat;
	}

	@Override
	public int compareTo(StatementLine o) {
		return date.compareTo(o.getDate());
	}

	@Override
	public String toString() {
		return "StatementLine{" +
				"date=" + date +
				", valueDate=" + valueDate +
				", description='" + description + '\'' +
				", draft=" + draft +
				", credit=" + credit +
				", accountingBalance=" + accountingBalance +
				", availableBalance=" + availableBalance +
				", category=" + category +
				'}';
	}
}
