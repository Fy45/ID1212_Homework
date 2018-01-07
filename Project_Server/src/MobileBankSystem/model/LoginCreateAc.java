package MobileBankSystem.model;



import MobileBankSystem.controller.SQL_connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static MobileBankSystem.model.DatabaseFunction.getCTX;

public class LoginCreateAc {

	public static String cardnumber;
        public static String out;
	public static String login(String user) {

		Connection connection =SQL_connector.sqlconnector();
		String sql;
		Statement statement;
		ResultSet set;
		/*
		 * 初始了一些值 需要更改为传入的值
		 */

		String cardnum = getCTX(user, "@", "~");
		//String name;
		String pswd = getCTX(user, "~", "?");;
		//String type;
		//BigDecimal money = new BigDecimal(500000);
		//int invest=1;




		try {
			statement =connection.createStatement();
			sql="select * from bank";  //这里应该用* 因为你要两个列取做比较 不能只写cardNum
			set =statement.executeQuery(sql);

			while(set.next()){
				//检查卡号和密码是否匹配
				if(set.getString(1).equals(cardnum) && set.getString(2).equals(pswd)) {
                                        String userName = set.getString(3);
                                        String userType = set.getString(4);
                                        String userBalance = set.getBigDecimal(5).toString();
                                        
					out="[login]<Success>"+"("+"!"+userName+"@"+userType+"&"+userBalance+"*"+")";
					System.out.println("user: "+cardnum+ " login");
					cardnumber=cardnum;
					break;

					//在这里因该思考一下 需要传输回去一个成功的标志去做判断


				}else {

					out ="card number or password is wrong, try again! \n";
					System.out.println("error");

					//错误情况也一样

				}



			}
		} catch (Exception e) {
			System.out.println("login error");
		}
		return out;

	}
        
	public static String createAc(String user) {
            boolean breakFLG = true;
		Connection connection =SQL_connector.sqlconnector();
		String sql;
		Statement statement;
		ResultSet set;
		/*
		 * 初始了一些值 提取传入字符串中相应信息
		 */

		String cardnum = getCTX(user, "@", "~");
		String pswd=getCTX(user, "~", "?");
		String name=getCTX(user, "?", "#");
		String type=getCTX(user, "#", "%");
	
		//BigDecimal openmoney = new BigDecimal(0);
		//int invest=0;




		try {
			statement =connection.createStatement();
			sql="select * from bank";
			set =statement.executeQuery(sql);

			while(set.next()){

				if(set.getString(1).equals(cardnum)) {
					out="[register]<Fail>"+"(Card number must unique! try another)";
					System.out.println("duplicate card number");
                                        breakFLG = false;
					break;

				}
                                
                        }
                        if(breakFLG)   {
                                   //这里是否成功需要回传一个标志给client

                                   DatabaseFunction.dataInsert(cardnum,pswd,name,type);
                                   set =statement.executeQuery(sql);
                                   while(set.next()){
                                       if(set.getString(1).equals(cardnum)) {
                                           String userName = set.getString(3);
                                           String userType = set.getString(4);
                                           String userBalance = set.getBigDecimal(5).toString();

                                           out="[register]<Success>"+"("+"!"+userName+"@"+userType+"&"+userBalance+"*"+")";
                                           System.out.println("new user: "+cardnum);

                                           cardnumber=cardnum;
                                           DatabaseFunction.GetUserInfo(cardnumber);
                                       }
                                   }
                           }



			
		} catch (Exception e) {
			e.printStackTrace();
		}


		return out;

	}

}

