package beverage_tycoon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;



public class Game extends JFrame {
    JButton originalTeaBtn;
    JButton milkTeaBtn;
    JButton fruitTeaBtn;
    JButton milkFoamBtn;
    JButton tapiocaPearlBtn;
    JButton aloeBtn;
    JButton iceNameBtn;
    JButton iceLessBtn;
    JButton iceRegularBtn;
    JButton iceFullBtn;
    JButton sugarNameBtn;
    JButton sugarThirtyBtn;
    JButton sugarFiftyBtn;
    JButton sugarSeventyBtn;
    JLabel mission;
    JLabel userAnswer;
    JTextField calculationInput;
    JPanel userLifeSection;

    MenuComponent menuComponent = new MenuComponent();
    String getUserActionList;
    Customer customer = new Customer();
    ArrayList<String> getOrder;
    String customerName;
    boolean submitBtnPressed = false;
    int lifeImgNum = 4;

    //이미지 변수명 1,2,3,4인 이유 : 유저 목숨값이 4개인데 각 이미지별 기능 차이가 없으므로 1,2,3,4로 하였습니다.
    Intro.ImagePanel userLifeImg1 = new Intro.ImagePanel(new ImageIcon("src/images/tycoonUserLife.png").getImage());
    Intro.ImagePanel userLifeImg2 = new Intro.ImagePanel(new ImageIcon("src/images/tycoonUserLife.png").getImage());
    Intro.ImagePanel userLifeImg3 = new Intro.ImagePanel(new ImageIcon("src/images/tycoonUserLife.png").getImage());
    Intro.ImagePanel userLifeImg4 = new Intro.ImagePanel(new ImageIcon("src/images/tycoonUserLife.png").getImage());
    Intro.ImagePanel[] userLifeImg = new Intro.ImagePanel[]{userLifeImg1, userLifeImg2, userLifeImg3, userLifeImg4};


    public Game(){
        missionSection(); // 유저가 입력한 메뉴값의 출력 공간
        buttonSections(); // 유저가 누를 버튼 공간
        calculateSection(); // 음료 값을 계산하는 공간
        userLifeSection(); //유저의 목숨값이 보이는 공간
        defaultScreen(); //기본 프레임 세팅
    }

    public void defaultScreen(){
        Intro.ImagePanel roofImage = new Intro.ImagePanel(new ImageIcon("src/images/tycoonRoof.png").getImage());
        roofImage.setBounds(0,-10,478,111);
        getContentPane().add(roofImage);
        roofImage.setLayout(null);

        setTitle("GOMCHA TYCOON");
        setSize(478,888);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    void missionSection(){
        JPanel missionSection = new JPanel();
        mission = new JLabel("",JLabel.CENTER);
        userAnswer = new JLabel("",JLabel.CENTER);
        Font missionFont = new Font("S-Core Dream", Font.BOLD, 22);
        mission.setFont(missionFont);
        userAnswer.setFont(missionFont);
        missionSection.add(userAnswer);
        missionSection.add(mission);
        missionSection.setBounds(0,140,478,60);
        missionSection.setBackground(Color.white);
        add(missionSection);

        mission.setText("<html><body style='text-align:center'>어서오세요! 곰차입니다.<br> 손님을 기다려볼까요?</body></html>");
        missionAlert();
        checkConsumerLeft(submitBtnPressed);
    }


    public void missionAlert(){
        Timer missionTimer = new Timer();
        TimerTask missionAlert = new TimerTask() {
            @Override
            public void run() {
                getOrder = customer.getOrderNameLists();
                customerName = customer.getCustomerName();
                calculationInput.setText("");
                userAnswer.setText("");
                mission.setText("");
                JOptionPane.showMessageDialog(null,
                        "<html><body style='text-align:center'>"+ customerName + "이 도착했습니다!<br>"+
                                "주문: " + getOrder +"</body></html>","주문 접수 완료",JOptionPane.PLAIN_MESSAGE);
            }
        };
        missionTimer.schedule(missionAlert,2500);
    }


    Timer leftTimer;
    void checkConsumerLeft(boolean type){
        if(type){
            leftTimer.cancel();
            submitBtnPressed = false;
        } else {
            leftTimer = new Timer();
            TimerTask consumerLeft = new TimerTask() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, "손님이 기다리다가 떠났어요ㅠ.ㅠ ",
                            "웨이팅 시간 초과", JOptionPane.PLAIN_MESSAGE);
                    userAnswer.setText("");
                    calculationInput.setText("");
                    lifeImgNum--;
                    deleteUserLife(lifeImgNum);
                }
            };
            leftTimer.schedule(consumerLeft, 20000);
        }
    }

    class SubmitBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            checkConsumerLeft(true);
            getUserActionList = userAnswer.getText().trim();
            String[] splitList = getUserActionList.split("\\s+");
            ArrayList<String> userActionList = new ArrayList<>(Arrays.asList(splitList));
            System.out.println(getOrder);
            System.out.println(userActionList);

            CalculationCost calculationCost = new CalculationCost(getOrder);
            int customerOrderCost = calculationCost.sumOfOrderCost(getOrder);

            boolean result = Arrays.equals(getOrder.toArray(), userActionList.toArray());


            try {
                if (result && customerOrderCost == Integer.parseInt(calculationInput.getText())) {
                    JOptionPane.showMessageDialog(null, customerName + ": 오늘의 곰차는 맛있군요!",
                            "판매 성공", JOptionPane.PLAIN_MESSAGE);
                    missionAlert();
                    checkConsumerLeft(submitBtnPressed);
                } else if (result && customerOrderCost != Integer.parseInt(calculationInput.getText())) {
                    JOptionPane.showMessageDialog(null, "앗! 결제가 틀렸어요!",
                            "결제 오류", JOptionPane.PLAIN_MESSAGE);
                    lifeImgNum--;
                    deleteUserLife(lifeImgNum);
                } else if (!result && customerOrderCost == Integer.parseInt(calculationInput.getText())) {
                    JOptionPane.showMessageDialog(null, "앗! 주문한 음료가 아니에요!",
                            "주문 오류", JOptionPane.PLAIN_MESSAGE);
                    lifeImgNum--;
                    deleteUserLife(lifeImgNum);
                } else if(userAnswer.getText().equals("") || calculationInput.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "앗! 손님에게 그냥 음료를/돈을 줘버렸다..",
                        "미기입 오류",JOptionPane.PLAIN_MESSAGE);
                    lifeImgNum--;
                    deleteUserLife(lifeImgNum);
                } else {
                    JOptionPane.showMessageDialog(null, "앗! 주문과 가격이 모두 틀렸어요!",
                            "결제 및 주문 오류", JOptionPane.PLAIN_MESSAGE);
                    lifeImgNum--;
                    deleteUserLife(lifeImgNum);
                }
            } catch (NumberFormatException notNumber){
                JOptionPane.showMessageDialog(null, "가격을 숫자로 입력해주세요!",
                        "입력 오류", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }


    void buttonSections(){
        JPanel buttonSection = new JPanel();
        JPanel beverageSection = new JPanel();
        JPanel beverageCostSection = new JPanel();
        JPanel toppingSection = new JPanel();
        JPanel toppingCostSection = new JPanel();
        JPanel iceSection = new JPanel();
        JPanel sugarSection = new JPanel();

        JLabel originalTeaCost = new JLabel(menuComponent.originalTeaCost +"원",JLabel.CENTER);
        JLabel milkTeaCost = new JLabel(menuComponent.milkTeaCost +"원",JLabel.CENTER);
        JLabel fruitTeaCost = new JLabel(menuComponent.fruitTeaCost +"원",JLabel.CENTER);
        JLabel milkFoamCost = new JLabel(menuComponent.milkFoamCost +"원",JLabel.CENTER);
        JLabel tapiocaPearlCost = new JLabel(menuComponent.tapiocaPearlCost +"원",JLabel.CENTER);
        JLabel aloeCost = new JLabel(menuComponent.aloeCost +"원",JLabel.CENTER);

        originalTeaBtn = new JButton("오리지널티");
        milkTeaBtn = new JButton("밀크티");
        fruitTeaBtn = new JButton("과일티");
        milkFoamBtn = new JButton("밀크폼");
        tapiocaPearlBtn = new JButton("타피오카펄");
        aloeBtn = new JButton("알로에");
        iceNameBtn = new JButton("얼음");
        iceLessBtn = new JButton("LESS");
        iceRegularBtn = new JButton("REGULAR");
        iceFullBtn = new JButton("FULL");
        sugarNameBtn = new JButton("당도");
        sugarThirtyBtn = new JButton("30");
        sugarFiftyBtn = new JButton("50");
        sugarSeventyBtn = new JButton("70");

        Font btnFont = new Font("S-Core Dream", Font.BOLD, 15);
        originalTeaBtn.setFont(btnFont);
        originalTeaCost.setFont(btnFont);
        milkTeaBtn.setFont(btnFont);
        milkTeaCost.setFont(btnFont);
        fruitTeaBtn.setFont(btnFont);
        fruitTeaCost.setFont(btnFont);
        milkFoamBtn.setFont(btnFont);
        milkFoamCost.setFont(btnFont);
        tapiocaPearlBtn.setFont(btnFont);
        tapiocaPearlCost.setFont(btnFont);
        aloeBtn.setFont(btnFont);
        aloeCost.setFont(btnFont);
        iceNameBtn.setFont(btnFont);
        iceLessBtn.setFont(btnFont);
        iceRegularBtn.setFont(btnFont);
        iceFullBtn.setFont(btnFont);
        sugarNameBtn.setFont(btnFont);
        sugarThirtyBtn.setFont(btnFont);
        sugarFiftyBtn.setFont(btnFont);
        sugarSeventyBtn.setFont(btnFont);

        originalTeaBtn.setPreferredSize(new Dimension(150,90));
        originalTeaCost.setPreferredSize(new Dimension(150,15));
        milkFoamBtn.setPreferredSize(new Dimension(150,90));
        milkFoamCost.setPreferredSize(new Dimension(150,15));
        iceNameBtn.setPreferredSize(new Dimension(115,90));
        sugarNameBtn.setPreferredSize(new Dimension(115,90));

        GridLayout threeBtnLayout = new GridLayout(1,3);
        GridLayout fourBtnLayout = new GridLayout(1,4);
        beverageSection.setLayout(threeBtnLayout);
        beverageCostSection.setLayout(threeBtnLayout);
        toppingSection.setLayout(threeBtnLayout);
        toppingCostSection.setLayout(threeBtnLayout);
        iceSection.setLayout(fourBtnLayout);
        sugarSection.setLayout(fourBtnLayout);

        beverageSection.add(originalTeaBtn);
        beverageSection.add(milkTeaBtn);
        beverageSection.add(fruitTeaBtn);
        beverageCostSection.add(originalTeaCost);
        beverageCostSection.add(milkTeaCost);
        beverageCostSection.add(fruitTeaCost);
        toppingSection.add(milkFoamBtn);
        toppingSection.add(tapiocaPearlBtn);
        toppingSection.add(aloeBtn);
        toppingCostSection.add(milkFoamCost);
        toppingCostSection.add(tapiocaPearlCost);
        toppingCostSection.add(aloeCost);
        iceSection.add(iceNameBtn);
        iceSection.add(iceLessBtn);
        iceSection.add(iceRegularBtn);
        iceSection.add(iceFullBtn);
        sugarSection.add(sugarNameBtn);
        sugarSection.add(sugarThirtyBtn);
        sugarSection.add(sugarFiftyBtn);
        sugarSection.add(sugarSeventyBtn);

        buttonSection.add(beverageSection);
        buttonSection.add(beverageCostSection);
        buttonSection.add(toppingSection);
        buttonSection.add(toppingCostSection);
        buttonSection.add(iceSection);
        buttonSection.add(sugarSection);
        add(buttonSection);

        buttonSection.setBounds(0,210,478,450);
        buttonSection.setBackground(Color.white);
        beverageSection.setBackground(Color.white);
        beverageCostSection.setBackground(Color.white);
        toppingSection.setBackground(Color.white);
        toppingCostSection.setBackground(Color.white);
        iceSection.setBackground(Color.white);
        sugarSection.setBackground(Color.white);

        originalTeaBtn.addActionListener(new MenuBtnListener());
        milkTeaBtn.addActionListener(new MenuBtnListener());
        fruitTeaBtn.addActionListener(new MenuBtnListener());
        milkFoamBtn.addActionListener(new MenuBtnListener());
        tapiocaPearlBtn.addActionListener(new MenuBtnListener());
        aloeBtn.addActionListener(new MenuBtnListener());
        iceNameBtn.addActionListener(new MenuBtnListener());
        iceLessBtn.addActionListener(new MenuBtnListener());
        iceRegularBtn.addActionListener(new MenuBtnListener());
        iceFullBtn.addActionListener(new MenuBtnListener());
        sugarNameBtn.addActionListener(new MenuBtnListener());
        sugarThirtyBtn.addActionListener(new MenuBtnListener());
        sugarFiftyBtn.addActionListener(new MenuBtnListener());
        sugarSeventyBtn.addActionListener(new MenuBtnListener());

    }

    class MenuBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String userAction = e.getActionCommand();
            userAnswer.setText(userAnswer.getText()+ " " + userAction);
            }
    }


    void calculateSection(){
        JPanel calculationSection = new JPanel();
        JPanel calculationSubmit = new JPanel();
        JLabel calculationText = new JLabel("총 주문 금액 : ");
        calculationInput = new JTextField(4);
        JLabel wonText = new JLabel(" 원");
        JButton submitBtn = new JButton("손님~ 음료 나왔습니다!");

        Font textFont = new Font("S-Core Dream", Font.BOLD, 20);
        calculationText.setFont(textFont);
        wonText.setFont(textFont);
        Font btnFont = new Font("S-Core Dream", Font.BOLD, 13);
        submitBtn.setFont(btnFont);

        calculationInput.setPreferredSize(new Dimension(40, 35));
        submitBtn.addActionListener(new SubmitBtnListener());

        calculationSection.add(calculationText);
        calculationSection.add(calculationInput);
        calculationSection.add(wonText);
        calculationSection.setBackground(Color.white);
        calculationSection.setBounds(0,650,478,40);
        calculationSubmit.add(submitBtn);
        calculationSubmit.setBackground(Color.white);
        calculationSubmit.setBounds(0,695,478,30);

        add(calculationSection);
        add(calculationSubmit);

    }


    void userLifeSection(){
        userLifeSection = new JPanel();
        userLifeSection.setBounds(5,740,478,120);
        for(int i = 0; i < 4; i++){
            userLifeSection.add(userLifeImg[i]);
        }
        GridLayout userLifeLayout = new GridLayout(1,4);
        userLifeSection.setLayout(userLifeLayout);
        userLifeSection.setBackground(Color.white);
        add(userLifeSection);
    }

    void deleteUserLife(int imgNum){
        userLifeSection.removeAll();
        if(imgNum != 0){
            for(int i = 0; i< imgNum; i++){
                userLifeSection.add(userLifeImg[i]);
                userLifeSection.repaint();
                userLifeSection.revalidate();
                GridLayout userLifeLayout = new GridLayout(1,4);
                userLifeSection.setLayout(userLifeLayout);
                userLifeSection.setBackground(Color.white);
                add(userLifeSection);
            }
            missionAlert();
            checkConsumerLeft(submitBtnPressed);
        } else {
            System.out.println("게임오버~");
            new GameOver();
            setVisible(false);
        }
    }

    class CalculationCost{  //컴퓨터가 손님의 주문한 내용을 계산하는 클래스
        public CalculationCost(ArrayList<String> actionList){
            sumOfOrderCost(actionList);
        }

        int sumOfOrderCost(ArrayList<String> al){
            int beverageCost = 0;
            int toppingCost = 0;
            int costSum = 0;

            for(int i = 0; i < al.size(); i++){
                if(Objects.equals(al.get(i), menuComponent.originalTeaName)){
                    beverageCost += menuComponent.originalTeaCost;
                } else if(Objects.equals(al.get(i), menuComponent.fruitTeaName)){
                    beverageCost += menuComponent.fruitTeaCost;
                } else if(Objects.equals(al.get(i), menuComponent.milkTeaName)){
                    beverageCost += menuComponent.milkTeaCost;
                } else if(Objects.equals(al.get(i), menuComponent.milkFoamName)){
                    toppingCost += menuComponent.milkFoamCost;
                } else if(Objects.equals(al.get(i), menuComponent.tapiocaPearlName)){
                    toppingCost += menuComponent.tapiocaPearlCost;
                } else if(Objects.equals(al.get(i), menuComponent.aloeName)){
                    toppingCost += menuComponent.aloeCost;
                }
                costSum = beverageCost + toppingCost;
            }
            return costSum;
        }
    }


}


