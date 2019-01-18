package com.example.ett15084.week8_bottledispenser;

import java.util.ArrayList;

public class BottleDispenser {

    private static BottleDispenser BD = new BottleDispenser();

    private int bottles;
    private float money;
    ArrayList<Bottle> bottles_array = new ArrayList();
    private Bottle bottle_help;
    private float round;

    private BottleDispenser() {
        bottles = 6;
        money = 0.0f;

        bottles_array.add(new Bottle("ED", 0.5f, 1.8f ));
        bottles_array.add(new Bottle("Pepsi Max", 1.5f, 2.2f ));
        bottles_array.add(new Bottle("7upa", 0.5f, 2.0f ));
        bottles_array.add(new Bottle("Coca-Cola Zero", 1.5f, 2.5f ));
        bottles_array.add(new Bottle("Sprite", 0.5f, 1.95f ));
        bottles_array.add(new Bottle("Fanta Zero", 1.5f, 3.95f ));

        //bottles = bottles_array.size();

    }

    public static BottleDispenser getInstance(){
        return BD;
    }

    public float addMoney(int m){
        money = money + m;
        return money;

    }

    public String returnMoney(){
        round = Math.round((money * 100.0f)/100.0f);
        float moneytemp = round;
        money = 0;
        return ("Rahat palautettu! Rahaa tuli " + moneytemp + "€");

    }

    public String buyBottle(int pick) {
        //pick = pick + 1;
        System.out.println("Picki: " + pick);
        if (money == 0) {
            round = Math.round((money * 100.0f)/100.0f);
            return ("Lisää rahaa! Koneessa " + round + "€");
        }

        else if (money < bottles_array.get(pick).getPrice()) {
            round = Math.round((money * 100.0f)/100.0f);
            return ("Lisää rahaa! Koneessa " + round + "€");
        }
        else {
            for (int i = 0; i < bottles_array.size(); i++) {
                if(i == pick) {
                    money = money - bottles_array.get(i).getPrice();
                    round = Math.round((money * 100.0f) / 100.0f);
                    bottles -= 1;
                    System.out.println("Pulloja " + bottles);
                    Bottle bottleTemp = bottles_array.get(i); //Koska pullo poistetaan ENNEN ostoa, tällä se saadaan oikein return lauseeseen
                    bottles_array.remove(i); //poistetaan ostettu pullo arraylististä
                    //return (bottles_array.get(i).getType() + " tipahti koneesta!\nRahaa jäljellä " + round + "€");
                    return (bottleTemp.getType() + " tipahti koneesta!\nRahaa jäljellä " + round + "€" + ", \npulloja jäljellä " + bottles);
                }

                else{
                    continue;
                }

            }
        }
        return "";
    }
    }







    /*public void addMoney() {
        money += 1;
        System.out.println("Klink! Lisää rahaa laitteeseen!");
    }

    public void buyBottle(String p) {

        if(money == 0){
            System.out.println("Syötä rahaa ensin!");
        }
        else if(bottles == 0){
            System.out.println("Pullot on loppu!");
        }
        else{

            int pick = Integer.parseInt(p);
            for (int i = 0; i < bottles_array.size();i++){
                bottle_help = bottles_array.get(pick-1);
                //if (money < Bottle.getPrice()) {
                if (money < bottle_help.getPrice()){
                    System.out.println("Syötä rahaa ensin!");
                    break;
                }
                else{
                    System.out.println("KACHUNK! " + bottle_help.getType() + " tipahti masiinasta!");
                    bottles -= 1;
                    bottles_array.remove(i); //poistetaan ostettu pullo arraylististä
                    money = money - bottle_help.getPrice();
                    break;
                }
            }
        }
    }

    public void returnMoney() {
        float round = Math.round((money * 100.0)/100.0);
        //String round1 = round.toString;
        //float round2 = round1.replace(".", ",");
        System.out.println("Klink klink. Sinne menivät rahat! Rahaa tuli ulos " + round + "€");
        money = 0;
    }

    public void listProducts(){
        //For-loopillahan tässä ne käydään yksi kerrallaan läpi
        for(int i = 0; i < bottles_array.size();i++){
            bottle_help = bottles_array.get(i);
            System.out.println(i+1 + ". Nimi: " + bottle_help.getType());
            System.out.println("\tKoko: " + bottle_help.getAmount() + "\tHinta: " + bottle_help.getPrice());

        }
    }*/



