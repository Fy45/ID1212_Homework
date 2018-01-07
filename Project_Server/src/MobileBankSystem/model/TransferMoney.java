package MobileBankSystem.model;



import MobileBankSystem.controller.SQL_connector;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static MobileBankSystem.model.DatabaseFunction.getString;


public class TransferMoney {
	public static String out;

	public static String Transfer(String card,String transmoney,String account, String payname) throws SQLException{


		Connection connection = SQL_connector.sqlconnector();
		connection.setAutoCommit(false);// 设置自动提交为false(不自动提交)  
		PreparedStatement pt;
		ResultSet set;
		String sql;
		try {

			BigDecimal money = null;
			BigDecimal transfer;
			sql="select * from bank where CardNum="+card;
			pt=(PreparedStatement) connection.prepareStatement(sql);
			transfer =new BigDecimal(transmoney);
			System.out.println(transfer);
			set= pt.executeQuery();

			//查询当前卡内余额
			set.next();
				money=set.getBigDecimal(5);
				System.out.println(money);
			
			if(-1==money.compareTo(transfer)) {
                                String addItem = getString(card);
				out="[transfer](<Fail>Insufficient balance in card )"+addItem;
				System.out.println(out);
                                connection.commit();//提交事务
				throw new RuntimeException("Insufficient balance!");  

			}else {

				//从当前卡内减去转账金额
				
				
                                
			
		
			//查询转入用户名与卡号是否匹配
			sql="select * from bank where CardNum="+account;
			set= pt.executeQuery(sql);
			String cname = null;
                            set.next();
				cname=set.getString(3);
			

			if(!cname.equals(payname)) {
				System.out.println(cname);
				System.out.println(payname);
                                String addItem = getString(card);
				out="[transfer](<Fail>Card number doesn't match with the username!)"+addItem;
				System.out.println("Card number doesn't match with the username!");
                                connection.commit();//提交事务

			}else {
                                sql="update bank set Balance=Balance-"+transfer+
						" where CardNum="+card;
				pt.executeUpdate(sql);
                        

				//向转入卡号内加上转账金额
				sql="update bank set Balance=Balance+"+transmoney+
						" where CardNum="+account;	
				pt.executeUpdate(sql);
                                connection.commit();//提交事务
				System.out.println("Transfer success!");
                                
                                String addItem = getString(card);
                                
                                System.out.println(addItem);
				out="[transfer](<Success>Transfer success!)"+addItem;
				
			}}
			
			
		}catch (Exception e) {
			e.printStackTrace();
                        
		}

		return out;
	}

}
