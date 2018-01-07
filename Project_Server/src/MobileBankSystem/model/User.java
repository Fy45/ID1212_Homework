package MobileBankSystem.model;



import java.io.Serializable;
import java.math.BigDecimal;
/**
 * Created by yfan.
 */
public class User implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ac;
	private String password;
	private String name;
	private BigDecimal money;	
	private String type;
	private int invest;

	public User(String ac, String password, String name, String type, BigDecimal money, int invest) {
		this.ac = ac;
		this.password = password;
		this.name = name;
		this.type = type;
		this.money = money;
		this.setInvest(invest);

	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getInvest() {
		return invest;
	}

	public void setInvest(int invest) {
		this.invest = invest;
	}
}


