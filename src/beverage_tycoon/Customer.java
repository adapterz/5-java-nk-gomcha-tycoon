package beverage_tycoon;
import java.util.Random;
import java.util.ArrayList;

public class Customer {
    String orderNumber;


    public Customer(){ //손님의 이름, 주문번호, 주문리스트 정보를 담은 생성자
        orderNumber = getOrderNumber();
    }

    public ArrayList<String> getOrderNameLists(){ //손님의 주문리스트를 랜덤으로 리턴하는 메소드
        Random random = new Random();
        ArrayList<String> orderNameLists = new ArrayList<String>();
        String[] beverageMenuLists = {"오리지널티", "밀크티", "과일티"};
        String[] toppingOptions = {"true", "false"};
        String[] toppingMenuLists = {"밀크폼", "타피오카펄", "알로에"};
        String iceOption = "얼음";
        String[] iceOptionAmount = {"LESS", "REGULAR", "FULL"};
        String sugarOption = "당도";
        String[] sugarOptionAmount = {"30", "50", "70"};

        int[] beverageArrays = new int[1];
        int[] iceAmountArray =  new int[1];
        int[] sugarAmountArray = new int[1];

        beverageArrays[0] = random.nextInt(beverageMenuLists.length);
        iceAmountArray[0] = random.nextInt(iceOptionAmount.length);
        sugarAmountArray[0] = random.nextInt(sugarOptionAmount.length);

        if(beverageMenuLists[beverageArrays[0]].equals("밀크티")){ //밀크티인 경우 토핑 선택 유무를 결정해야 하기 때문에 조건문 사용
            int[] toppingOptionArrays = new int[1];
            toppingOptionArrays[0] = random.nextInt(toppingOptions.length);
            if(toppingOptions[toppingOptionArrays[0]].equals("true")){ //밀크티 손님이 토핑 선택 시 토핑 종류 선택 조건문
                int[] toppingArrays = new int[1];
                toppingArrays[0]= random.nextInt(toppingMenuLists.length);
                orderNameLists.add(beverageMenuLists[beverageArrays[0]]);
                orderNameLists.add(toppingMenuLists[toppingArrays[0]]);
            } else {
                orderNameLists.add(beverageMenuLists[beverageArrays[0]]);
            }
        } else {
            orderNameLists.add(beverageMenuLists[beverageArrays[0]]);
        }
        orderNameLists.add(iceOption);
        orderNameLists.add(iceOptionAmount[iceAmountArray[0]]);
        orderNameLists.add(sugarOption);
        orderNameLists.add(sugarOptionAmount[sugarAmountArray[0]]);


        return(orderNameLists);
    }

    String getOrderNumber(){ //손님의 주문번호를 랜덤으로 리턴하는 메소드
        String[] alphabetCode = {"A", "B", "C"};
        String[] numberCode = {"1", "2", "3"};
        Random random = new Random();
        int[] arrays = new int[3];
        arrays[0] = random.nextInt(alphabetCode.length);
        arrays[1] = random.nextInt(numberCode.length);
        arrays[2] = random.nextInt(numberCode.length);
        return alphabetCode[arrays[0]]+numberCode[arrays[1]]+numberCode[arrays[2]];
    }

    String getCustomerName(){ //손님의 이름을 랜덤으로 리턴하는 메소드
        String[] customerCode = {"손님1", "손님2", "손님3", "손님4", "손님5", "손님6"};
        Random random = new Random();
        int[] arrays = new int[1];
        arrays[0] = random.nextInt(customerCode.length);
        return customerCode[arrays[0]];
    }

    public static void main(String arg[]){
        Customer customer = new Customer();
    }
}
