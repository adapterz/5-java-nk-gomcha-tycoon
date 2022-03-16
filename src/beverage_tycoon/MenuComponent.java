package beverage_tycoon;
import java.util.ArrayList;

public class MenuComponent {
    String originalTeaName;
    int originalTeaCost;
    String milkTeaName;
    int milkTeaCost;
    boolean needTopping;
    String fruitTeaName;
    int fruitTeaCost;
    String milkFoamName;
    int milkFoamCost;
    String tapiocaPearlName;
    int tapiocaPearlCost;
    String aloeName;
    int aloeCost;
    String ice;
    String[] iceAmount;
    String sugar;
    int[] sugarAmount;
    int iceCost;
    int sugarCost;


    public MenuComponent(){
        OriginalTea originalTea = new OriginalTea("오리지널티",3000);
        MilkTea milkTea = new MilkTea("밀크티",4000, true);
        Beverage fruitTea = new Beverage("과일티", 4500);
        Topping milkFoam = new Topping("밀크폼", 1000,true, 5);
        Topping tapiocaPearl = new Topping("타피오카펄", 2000,true, 10);
        Topping aloe = new Topping("알로에", 1500,true, 10);
        Ice iceLess = new Ice(ice,iceCost, "LESS");
        Ice iceRegular = new Ice(ice,iceCost, "REGULAR");
        Ice iceFull = new Ice(ice,iceCost, "FULL");
        Sugar sugarZero = new Sugar(sugar,sugarCost, 0);
        Sugar sugarThirty = new Sugar(sugar,sugarCost, 30);
        Sugar sugarFifty =new Sugar(sugar,sugarCost, 50);
        Sugar sugarSeventy = new Sugar(sugar,sugarCost, 70);
        Sugar sugarHundred = new Sugar(sugar,sugarCost, 100);


        originalTeaName = originalTea.menuName;
        originalTeaCost = originalTea.menuCost;
        milkTeaName = milkTea.menuName;
        milkTeaCost = milkTea.menuCost;
        needTopping = milkTea.needTopping;
        fruitTeaName = fruitTea.menuName;
        fruitTeaCost = fruitTea.menuCost;
        milkFoamName = milkFoam.menuName;
        milkFoamCost = milkFoam.menuCost;
        tapiocaPearlName = tapiocaPearl.menuName;
        tapiocaPearlCost = tapiocaPearl.menuCost;
        aloeName = aloe.menuName;
        aloeCost = aloe.menuCost;
        ice = "얼음";
        sugar = "당도";
        iceCost = 0;
        sugarCost = 0;
        iceAmount = new String[]{iceLess.iceAmount, iceRegular.iceAmount, iceFull.iceAmount};
        sugarAmount = new int[]{sugarThirty.sugarAmount, sugarFifty.sugarAmount, sugarSeventy.sugarAmount};

    }

    class Beverage { //음료 클래스
        String menuName;
        int menuCost;
        Beverage(String menuName, int menuCost){
            this.menuName = menuName;
            this.menuCost = menuCost;
        }
    }

    class OriginalTea extends Beverage { //음료 클래스의 자식 클래스 : 오리지널티
        public OriginalTea(String menuName, int menuCost){
            super(menuName, menuCost);
        }
    }

    class MilkTea extends Beverage { //음료 클래스의 자식 클래스 : 밀크티
        boolean needTopping; //밀크티 주문한 손님이 토핑을 추가 주문 했는지 판별(주문-true)
        public MilkTea(String menuName, int menuCost, boolean needTopping){
            super(menuName, menuCost);
            this.needTopping = needTopping;
        }
    }

    class Topping extends MilkTea { //음료 클래스의 손자 클래스 && 밀크티 클래스의 자식 클래스 : 토핑, 상속 이유 : 변수 추가
        int toppingStock; //토핑에 재고를 판별하는 변수 - 없으면 makeTopping 메소드 실행
        public Topping(String menuName, int menuCost, boolean needTopping, int toppingStock){
            super(menuName, menuCost, needTopping);
            this.toppingStock = toppingStock;
        }
    }

    class Ice extends Beverage {
        String iceAmount;
        public Ice(String menuName, int menuCost, String iceAmount){
            super(menuName, menuCost);
            this.iceAmount = iceAmount;
        }
    }

    class Sugar extends Beverage {
        int sugarAmount;
        public Sugar(String menuName, int menuCost, int sugarAmount){
            super(menuName, menuCost);
            this.sugarAmount = sugarAmount;
        }
    }

    public static void main(String arg[]){
        MenuComponent menuComponent = new MenuComponent();
    }
}
