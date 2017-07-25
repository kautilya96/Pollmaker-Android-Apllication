package com.example.kautilya.pollmaker;

/**
 * Created by Kautilya on 9-4-17.
 */


public class ListItem2 {
    private String head;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int ans1;
    private int ans2;
    private int ans3;
    private int ans4;
    private int id;
    private int poll_id;
    private String date;
    public ListItem2(int poll_id,String head,String choice1, String choice2, String choice3, String choice4,int ans1,int ans2,int ans3,int ans4,int id,String date) {
        this.head = head;
        this.choice1=choice1;
        this.choice2=choice2;
        this.choice3=choice3;
        this.id=id;
        this.choice4=choice4;
        this.ans1=ans1;
        this.ans2=ans2;
        this.ans3=ans3;
        this.ans4=ans4;
        this.poll_id=poll_id;
        this.date=date;
    }
    public String getChoice1(){
        return choice1;
    }
    public String getChoice2(){
        return choice2;
    } public String getChoice3(){
        return choice3;
    } public String getChoice4(){
        return choice4;
    }
   public  String getDate(){
       return  date;
   }
    public int getAns1(){
        return ans1;
    }

    public int getAns2(){
        return ans2;
    }
    public int getAns3(){
        return ans3;
    }
    public int getAns4(){
        return ans4;
    }
    public int getId(){
        return id;
    }
    public int getPoll_id(){
        return poll_id;
    }
    public String getHead() {
        return head;
    }

}

