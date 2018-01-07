package MobileBankSystem.model;



import MobileBankSystem.controller.SQL_connector;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class DatabaseFunction {
	static String sql;
	public static String out;
	//添加用户
	public static int dataInsert(String card, String pswd, String nString, String t) {
		Connection conn = SQL_connector.sqlconnector();
		int i = 0;
		BigDecimal openmoney = new BigDecimal(0);
		int invest =0;
		sql = "insert into bank (CardNum,password,name,type,Balance,Invest) values(?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {

			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, card);
			pstmt.setString(2, pswd);
			pstmt.setString(3, nString);
			pstmt.setString(4, t);
			pstmt.setBigDecimal(5, openmoney);
			pstmt.setInt(6, invest);
			i = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//提供用户数据
	public static String GetUserInfo(String  userinfo) {
		String sql = "select * from bank where CardNum="+userinfo;
	
		Connection conn = SQL_connector.sqlconnector();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			//行数?
			while (rs.next()) {
				for (int i = 1; i <= col; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				String card=rs.getString(1);
				String password=rs.getString(2);
				String name=rs.getString(3);
				String type=rs.getString(4);
				String money=rs.getBigDecimal(5).toString();
				int invest=rs.getInt(6);
				out=card+"\t"+password+"\t"+name+"\t"+type+"\t"+money+"\t"+invest+"\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	//删除卡用户
	public static int UserDelete(String cardnumber) {
		Connection conn = SQL_connector.sqlconnector();
		int i = 0;
		String sql = "delete from bank where CardNum=" + cardnumber;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//存款
	public static int Deposit (String card,String c) {
		Connection conn =SQL_connector.sqlconnector();
		int i=0;
		//存款金额指定
		//String c1= CreateAccount.getCTX(c, "%", "$");
		BigDecimal deposit=new BigDecimal(c);

		sql="update bank set Balance=Balance+"+deposit+
				" where CardNum="+card;	
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;

	}

	//取款
	public static String Wtihdraw (String card,String val) {
		Connection conn =SQL_connector.sqlconnector();
		String result = null;
		PreparedStatement pstmt;
		ResultSet set;


		BigDecimal money = null;

		//取钱金额指定
		//String val1= CreateAccount.getCTX(val, "%", "$");
		BigDecimal withdraw = new BigDecimal(val);
		try {
			sql="select * from bank where CardNum="+card;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			set= pstmt.executeQuery();

			//查询当前卡内余额
                                set.next();
				money=set.getBigDecimal("Balance");
                                String type=set.getString(4);  
                                
                                String userName = set.getString(3);
           
                                String rua = money.toString();
                                System.out.println("rua old : "+rua);
                       if(type.equals("debt")){
			
			if(-1==money.compareTo(withdraw)) {

				result="[withdraw](Insufficient balance in card)"+"!"+userName+"@"+type+"&"+rua+"*";
				out=result;

			}else {

				//从当前卡内减去取出金额
				sql="update bank set Balance=Balance-"+withdraw+
						" where CardNum="+card;
				pstmt.executeUpdate(sql);

				String current = null;
				sql="select * from bank where CardNum="+card;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			set= pstmt.executeQuery();

			//查询当前卡内余额
                                set.next();
				money=set.getBigDecimal("Balance");
                                current = money.toString();
                                

                                userName = set.getString(3);
                                String userType = set.getString(4);
                                String userBalance = set.getBigDecimal(5).toString();
                                System.out.println("owowowowow rururura");
                                System.out.println("rua new : "+ current+"rua"+userName+"rua"+userType+"rua"+userBalance);
					
				
				result="[withdraw](<Success>Your current balance is"+current +")"+"!"+userName+"@"+userType+"&"+userBalance+"*";
				out=result;
			}
                       }
                       else{
                           //从当前卡内减去取出金额
				sql="update bank set Balance=Balance-"+withdraw+
						" where CardNum="+card;
				pstmt.executeUpdate(sql);

				String current = null;
				sql="select * from bank where CardNum="+card;
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			set= pstmt.executeQuery();

			//查询当前卡内余额
                                set.next();
				money=set.getBigDecimal("Balance");
                                current = money.toString();
                                

                                userName = set.getString(3);
                                String userType = set.getString(4);
                                String userBalance = set.getBigDecimal(5).toString();
     
                                System.out.println("rua new : "+ current+"rua"+userName+"rua"+userType+"rua"+userBalance);
					
				
				result="[withdraw](<Success>Your current balance is"+current +")"+"!"+userName+"@"+userType+"&"+userBalance+"*";
				out=result;                          
                       }


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return out;

	}

	//投资
	public static int Invest(String card,String d,String money) { //投资也应当扣钱的其实 可以复制上面那段提款操作
		Connection conn = SQL_connector.sqlconnector();
		int i = -1;
		//设置传入投资类型
		int invest = Integer.parseInt(d);
		BigDecimal imoney =null;
                BigDecimal oldmoney;
		imoney = new BigDecimal(money);
		
		PreparedStatement pstmt;
                ResultSet set;
                sql="select * from bank where CardNum="+card;
		
		try {
                        pstmt = (PreparedStatement) conn.prepareStatement(sql);
                        String sql = "update bank set Invest="+invest+", Balance=Balance-"+imoney+" where CardNum=" + card;
                        pstmt.executeUpdate(sql);
                        set=pstmt.executeQuery();
                        set.next();
                        oldmoney=set.getBigDecimal("Balance");
                                String type=set.getString(4);                               
                                String rua = oldmoney.toString();
                                System.out.println("rua old : "+rua);
                       if(type.equals("debt")){
			
			if(-1==oldmoney.compareTo(imoney)) {

				i=0;
                        }
                        else{
                  //          i = pstmt.executeUpdate();
                        pstmt.execute();
			pstmt.close();
			conn.close();
                        }}
                       else{
			//i = pstmt.executeUpdate();
                        pstmt.execute();
			pstmt.close();
			conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;

	}

        	public static String getCTX(String originalCTX,String firstSplit,String secondSplit){
		String resultCTX = originalCTX.substring(originalCTX.lastIndexOf(firstSplit), 
				originalCTX.lastIndexOf(secondSplit));
		resultCTX = resultCTX.substring(1,resultCTX.length());
		return resultCTX;
	}



public static String getString(String Number){
     Connection conn =SQL_connector.sqlconnector();
		String result = null;
		PreparedStatement pstmt;
                ResultSet set;
        try {
                sql="select * from bank where CardNum="+Number;
                pstmt = (PreparedStatement) conn.prepareStatement(sql);
                set= pstmt.executeQuery();

                //查询当前卡内余额
                set.next();

                String userName = set.getString(3);
                String userType = set.getString(4);
                String userBalance = set.getBigDecimal(5).toString();


                result="!"+userName+"@"+userType+"&"+userBalance+"*";
                } catch (SQLException e) {
			e.printStackTrace();
		}

		return result;	
}



}
