package MobileBankSystem.controller;

import MobileBankSystem.controller.SQL_connector;
import MobileBankSystem.model.DatabaseFunction;
import MobileBankSystem.model.LoginCreateAc;
import MobileBankSystem.model.TransferMoney;
import static MobileBankSystem.model.DatabaseFunction.getCTX;
import static MobileBankSystem.model.DatabaseFunction.getString;


//所有的结果都应当回给client去做处理
public class Operation{
        private static String sql;
	public static String Cardnumber;
	public static String result = null;        
	public static String operationSystem(String input){

		Cardnumber=LoginCreateAc.cardnumber;
               


		String operationflag=getCTX(input,"<",":");
                System.out.println(operationflag);

		try {   
			if("Q".equals(operationflag)){
				result="log out successfully \n";
			}        
			else if("D".equals(operationflag)){

				DatabaseFunction.UserDelete(LoginCreateAc.cardnumber);
				result="unregister successfully \n";
				
			}else if(operationflag.equals("show details")){ 
				DatabaseFunction.GetUserInfo(Cardnumber);
				result=DatabaseFunction.out;

			}
			else if(operationflag.equals("deposit")){
				String money=getCTX(input,":",">");

				DatabaseFunction.Deposit(Cardnumber,money);
                                String addItem = getString(Cardnumber);

                                    result="[deposit](<Success>)"+addItem;
                        

			}
			else if(operationflag.equals("withdraw")){
				String money=getCTX(input,":",">");


				DatabaseFunction.Wtihdraw(Cardnumber, money);
				result=DatabaseFunction.out;

			} 
			else if(operationflag.equals("invest")){
				String money=getCTX(input,":",">");
				String invest=getCTX(input, ">", "*");

				//DatabaseFuction.Invest(Cardnumber,invest,money);
				int option = Integer.parseInt(invest);
                                if(DatabaseFunction.Invest(Cardnumber,invest,money)==0){
                                    option=0;
                                }
                                
				switch (option) {
				case 1:
                                    String addItem = getString(Cardnumber);
					result="[invest](<Success> You choose to invest"+money+" sek to funds! )"+addItem;
					break;
				case 2:
                                    addItem = getString(Cardnumber);
					result="[invest](<Success>You choose to invest "+money+" sek to stock! )"+ addItem;
					break;
				case 3:
                                    addItem = getString(Cardnumber);
					result="[invest](<Success> You choose to donate"+money+" sek to charity! )" +addItem;
					break;

				default:
                                    addItem = getString(Cardnumber);
                                        result="[invest](<Fail> Insufficient balance in card)"+addItem;
					break;
				}

			}
			else if(operationflag.equals("transfer")) {
				String money=getCTX(input,"/",">");
				String account=getCTX(input, "*", "$");
				String payname=getCTX(input, "$", "/");
				TransferMoney.Transfer(Cardnumber,money,account,payname);
				result=TransferMoney.out;
			}

		}catch (Exception e) {
		}


		return result;

	}

}
